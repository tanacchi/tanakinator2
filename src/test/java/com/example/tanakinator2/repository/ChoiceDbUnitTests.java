package com.example.tanakinator2.repository;

import com.example.tanakinator2.Tanakinator2Application;
import com.example.tanakinator2.domain.Choice;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.AbstractTestExecutionListener;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.List;

public class ChoiceDbUnitTests {

    @SpringBootTest(classes = Tanakinator2Application.class)
    @TestExecutionListeners({DependencyInjectionTestExecutionListener.class, FindTestExecutionLister.class})
    @Nested
    public class FindDbTest extends AbstractTestExecutionListener {
        @Autowired
        private ChoiceRepository target;

        @Test
        public void testChoiceFindAll() throws Exception {
            List<Choice> choices = target.findChoices(null);
            assertEquals(choices.size(), 5);
            assertEquals( -1.0, choices.get(0).getChoiceValue());
            assertEquals( -0.5, choices.get(1).getChoiceValue());
            assertEquals(  0.0, choices.get(2).getChoiceValue());
            assertEquals( +0.5, choices.get(3).getChoiceValue());
            assertEquals( +1.0, choices.get(4).getChoiceValue());
        }

        @Test
        public void testChoiceGet() throws  Exception {
            Choice choice = target.getChoice(0);
            assertEquals(0, choice.getChoiceId());
            assertEquals("NO", choice.getChoiceName());
            assertEquals(-1.0, choice.getChoiceValue());
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
