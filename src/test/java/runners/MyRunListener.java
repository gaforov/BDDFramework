package runners;

import junit.runner.TestRunListener;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import java.util.Date;
import static utils.CommonMethods.*;
import static base.PageInitializer.*;

public class MyRunListener extends RunListener {

    @Override
    public void testRunStarted(Description description) throws Exception {
        Date date = new Date();
        System.out.println("Test started on: " + date);
        System.out.println("Test cases to execute: " + description.testCount());
    }

    @Override
    public void testRunFinished(Result result) throws Exception {
        System.out.println("Test cases executed: " + result.getRunCount());
    }

    @Override
    public void testSuiteStarted(Description description) throws Exception {
        System.out.println("testSuiteStarted: " + description.getDisplayName());
    }

    @Override
    public void testSuiteFinished(Description description) throws Exception {
        System.out.println("testSuiteFinished: End of Tests! Total Test Cases: " + description.testCount());
    }

    @Override
    public void testStarted(Description description) throws Exception {
        System.out.println("Execution Started: " + description.getMethodName());
    }

    @Override
    public void testFinished(Description description) throws Exception {
        System.out.println("Execution Finished: " + description.getMethodName());
    }

    @Override
    public void testFailure(Failure failure) throws Exception {
        System.out.println("Execution Failed: " + failure.getException());
        takeScreenshot("FAIL/" + failure.getTestHeader());
    }

    @Override
    public void testAssumptionFailure(Failure failure) {
        System.out.println("Assumption Failure: " + failure.getMessage());
    }

    @Override
    public void testIgnored(Description description) throws Exception {
        System.out.println("Execution Ignored: " + description.getMethodName());
    }
}
