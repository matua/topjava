package ru.javawebinar.topjava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.TestContext;

public class TestExecutionListener implements org.springframework.test.context.TestExecutionListener {
    private static final Logger logger = LoggerFactory.getLogger(TestExecutionListener.class);

    public void afterTestClass(TestContext testContext) throws Exception {
        logger.info(TestDuration.printAll());
    }
}