package runners;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import java.util.Date;

public class MyTestListener extends RunListener {
    long startTime;
    long endTime;

    @Override
    public void testRunStarted(Description description) throws Exception {
        startTime = new Date().getTime();
        System.out.println("Tests started! Number of Test case: " + description.getDisplayName());
        System.out.println(description.isTest());
        System.out.println(description.testCount());
    }

    @Override
    public void testRunFinished(Result result) throws Exception {
    }

    @Override
    public void testSuiteStarted(Description description) throws Exception {
    }

    @Override
    public void testSuiteFinished(Description description) throws Exception {
    }

    @Override
    public void testStarted(Description description) throws Exception {
    }

    @Override
    public void testFinished(Description description) throws Exception {
    }

    @Override
    public void testFailure(Failure failure) throws Exception {
    }

    @Override
    public void testAssumptionFailure(Failure failure) {
    }

    @Override
    public void testIgnored(Description description) throws Exception {
    }

}
