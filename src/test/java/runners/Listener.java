package runners;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import java.util.Date;
import static utils.CommonMethods.*;

public class Listener extends RunListener {

    @Override
    public void testRunStarted(Description description) {
        Date date = new Date();
        System.out.println("Test started on: " + date);
        System.out.println("Test cases to execute: " + description.testCount());
    }

    @Override
    public void testRunFinished(Result result) {
        System.out.println("Test cases executed: " + result.getRunCount());
    }

    @Override
    public void testSuiteStarted(Description description) {
        System.out.println("testSuiteStarted: " + description.getDisplayName());
    }

    @Override
    public void testSuiteFinished(Description description) {
        System.out.println("testSuiteFinished: End of Tests! Total Test Cases: " + description.testCount());
    }

    @Override
    public void testStarted(Description description) {
        System.out.println("Execution Started: " + description.getMethodName());
    }

    @Override
    public void testFinished(Description description) {
        System.out.println("Execution Finished: " + description.getMethodName());
    }

    @Override
    public void testFailure(Failure failure) {
        System.out.println("Execution Failed: " + failure.getException());
        takeScreenshot("FAIL/" + failure.getTestHeader());
    }

    @Override
    public void testAssumptionFailure(Failure failure) {
        System.out.println("Assumption Failure: " + failure.getMessage());
    }

    @Override
    public void testIgnored(Description description) {
        System.out.println("Execution Ignored: " + description.getMethodName());
    }
}
