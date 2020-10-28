package ru.javawebinar.topjava;

import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;

import java.util.concurrent.TimeUnit;

import static org.slf4j.LoggerFactory.getLogger;

public class TestDuration extends Stopwatch {
    private static final Logger log = getLogger(TestDuration.class);
    private static final StringBuilder allTestsResult = new StringBuilder();

    private static void logInfo(Description description, long nanos) {
        String testName = description.getMethodName();
        String testResult = String.format("\"Test %s\" - duration: %d microseconds",
                testName, TimeUnit.NANOSECONDS.toMicros(nanos));
        allTestsResult.append(testName)
                .append(" - duration: ")
                .append(nanos)
                .append(System.lineSeparator());
        log.debug(testResult);
    }

    public static String printAll() {
        StringBuilder starter = new StringBuilder();

        return starter
                .append(System.lineSeparator())
                .append("Tests Results:")
                .append(System.lineSeparator())
                .append(allTestsResult).toString();
    }

    @Override
    protected void succeeded(long nanos, Description description) {
        logInfo(description, nanos);
    }
}