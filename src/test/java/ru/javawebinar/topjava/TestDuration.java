package ru.javawebinar.topjava;

import org.junit.AssumptionViolatedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;

import java.util.concurrent.TimeUnit;

import static org.slf4j.LoggerFactory.getLogger;

public class TestDuration extends Stopwatch {
    private static final Logger log = getLogger(TestDuration.class);
    private static final StringBuilder allTestsResult = new StringBuilder();

    private static void logInfo(Description description, String status, long nanos) {
        String testName = description.getMethodName();
        String testResult = String.format("Test %s %s, spent %d microseconds",
                testName, status, TimeUnit.NANOSECONDS.toMicros(nanos));
        allTestsResult.append(testResult)
                .append(System.lineSeparator());
        log.info(testResult);
    }


    public static void printAll() {
        StringBuilder starter = new StringBuilder();

        log.info(starter.append("Tests Results:")
                .append(System.lineSeparator())
                .append(allTestsResult).toString());
    }

    @Override
    protected void succeeded(long nanos, Description description) {
        logInfo(description, "succeeded", nanos);
    }

    @Override
    protected void failed(long nanos, Throwable e, Description description) {
        logInfo(description, "failed", nanos);
    }

    @Override
    protected void skipped(long nanos, AssumptionViolatedException e, Description description) {
        logInfo(description, "skipped", nanos);
    }

    @Override
    protected void finished(long nanos, Description description) {
        logInfo(description, "finished", nanos);
    }
}