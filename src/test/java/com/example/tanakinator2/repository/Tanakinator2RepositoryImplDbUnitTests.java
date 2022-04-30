package com.example.tanakinator2.repository;

import com.example.tanakinator2.Tanakinator2Application;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.AbstractTestExecutionListener;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.List;

public class Tanakinator2RepositoryImplDbUnitTests {

    @SpringBootTest(classes = Tanakinator2Application.class)
    @TestExecutionListeners({DependencyInjectionTestExecutionListener.class, FindTestExecutionLister.class})
    @Nested
    public class FindDbTest extends AbstractTestExecutionListener {
        @Autowired
        private Tanakinator2Repository target;

        @Test
        public void testChoiceFindAll() throws Exception {
        }
    }

    static class FindTestExecutionLister extends AbstractTestExecutionListener {
        @Override
        public void beforeTestClass(TestContext testContext) throws Exception {
//            super.beforeTestClass(testContext);
        }

        @Override
        public void afterTestClass(TestContext testContext) throws Exception {
//            super.afterTestClass(testContext);
        }
    }
}
