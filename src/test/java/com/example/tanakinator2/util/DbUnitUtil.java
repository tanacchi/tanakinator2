package com.example.tanakinator2.util;

import org.dbunit.Assertion;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.Disabled;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

@Disabled
public class DbUnitUtil {
    public static void loadData(DataSource source, File dataFile) throws Exception {
        IDatabaseConnection connection = null;
        try {
            connection = dbUnitConnection(source);
            FileInputStream inFile = new FileInputStream(dataFile);
            XlsDataSet dataSet = new XlsDataSet(inFile);
            DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static void backup(DataSource source, File backup, String... tableNames) throws Exception {
        IDatabaseConnection connection = null;
        try {
            connection = dbUnitConnection(source);
            IDataSet partialDataSet = connection.createDataSet(tableNames);
            XlsDataSet.write(partialDataSet, new FileOutputStream(backup));
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static void restoreBackup(DataSource source, File backup) throws Exception {
        restoreBackup(source, backup, false);
    }

    public static void restoreBackup(DataSource source, File backup, boolean deleteOnExit) throws Exception {
        loadData(source, backup);
        if (deleteOnExit) {
            backup.deleteOnExit();
        } else {
            backup.delete();
        }
    }

    public static void assertMutateResult(DataSource source, String tableName, File expected, List<String> skipCols) throws Exception {
        IDatabaseConnection connection = null;
        try {
            connection = dbUnitConnection(source);

            ITable expectedTable = getExcelITable(expected.getPath(), tableName);
            ITable actualTable = getCurrentTable(connection, tableName);

            Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, skipCols.toArray(new String[0]));
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    static ITable getExcelITable(String path, String tableName) throws Exception {
        XlsDataSet dataSet = new XlsDataSet((new FileInputStream(path)));
        return dataSet.getTable(tableName);
    }

    static ITable getCurrentTable(IDatabaseConnection connection, String tableName) throws Exception {
        try {
            IDataSet dbDataSet = connection.createDataSet();
            return dbDataSet.getTable(tableName);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    static IDatabaseConnection dbUnitConnection(DataSource dataSource) throws Exception {
        IDatabaseConnection connection = new DatabaseConnection((dataSource.getConnection()));
        DatabaseConfig config = connection.getConfig();
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
        return connection;
    }
}
