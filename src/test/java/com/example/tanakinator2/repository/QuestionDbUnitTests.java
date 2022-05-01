package com.example.tanakinator2.repository;

import com.example.tanakinator2.Tanakinator2Application;
import com.example.tanakinator2.configuration.DataSourceConfiguration;
import com.example.tanakinator2.domain.Question;
import com.example.tanakinator2.util.DbUnitUtil;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.AbstractTestExecutionListener;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import javax.sql.DataSource;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionDbUnitTests {

    @SpringBootTest(classes = Tanakinator2Application.class)
    @TestExecutionListeners({DependencyInjectionTestExecutionListener.class, FindTestExecutionLister.class})
    @Nested
    public class FindDbTest extends AbstractTestExecutionListener {
        @Autowired
        private QuestionRepository target;

        @Test
        public void testQuestionGet() throws  Exception {
        }
    }

    static class FindTestExecutionLister extends AbstractTestExecutionListener {
        private static final String DATA_DIR = QuestionDbUnitTests.class.getResource("").getFile() + "data" + File.separator;
        private static File INIT_DATA = new File(DATA_DIR + "question_init.xlsx");
        private static File backup;

        @Override
        public void beforeTestClass(TestContext testContext) throws Exception {
//            DataSource source = getDataSource(testContext.getApplicationContext());
//            backup = new File(DATA_DIR + "question_back.xlsx");
//            DbUnitUtil.backup(source, backup, "question");
//            DbUnitUtil.loadData(source, INIT_DATA);
        }

        @Override
        public void afterTestClass(TestContext testContext) throws Exception {
//            DbUnitUtil.restoreBackup(getDataSource(testContext.getApplicationContext()), backup);
        }

        private DataSource getDataSource(ApplicationContext applicationContext) throws Exception {
            return applicationContext.getBean(DataSourceConfiguration.class).dataSource();
        }
    }

    @SpringBootTest(classes = Tanakinator2Application.class)
    @TestExecutionListeners({DependencyInjectionTestExecutionListener.class, InsertTestExecutionListener.class})
    @Nested
    public class InsertDbTest extends AbstractTestExecutionListener {
        @Autowired
        private QuestionRepository target;

        @Autowired
        private DataSourceConfiguration dataSourceConfiguration;

        @Test
        public void testInsert() throws Exception {
            Question question = new Question();
            question.setMessage("人間？");
            target.insert(question);
            DbUnitUtil.assertMutateResult(
                    dataSourceConfiguration.dataSource(),
                    "question",
                    InsertTestExecutionListener.EXPECTED_DATA,
                    Arrays.asList("question_id")
            );
        }
    }

    static class InsertTestExecutionListener extends AbstractTestExecutionListener {
        static final String DATA_DIR = QuestionDbUnitTests.class.getResource("").getFile() + "data" + File.separator;
        static final File INIT_DATA = new File(DATA_DIR + "question_insert_before.xlsx");
        static final File EXPECTED_DATA = new File(DATA_DIR + "question_insert_after.xlsx");

        @Override
        public void beforeTestClass(TestContext testContext) throws Exception {
            DataSource source = getDataSource(testContext.getApplicationContext());
            DbUnitUtil.loadData(source, INIT_DATA);
        }

        @Override
        public void afterTestClass(TestContext testContext) throws Exception {
            super.afterTestClass(testContext);
        }

        private DataSource getDataSource(ApplicationContext applicationContext) throws Exception {
            return applicationContext.getBean(DataSourceConfiguration.class).dataSource();
        }
    }
}
