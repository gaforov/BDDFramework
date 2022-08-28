package steps;

import base.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import static utils.CommonMethods.takeScreenshotAsBytes;

public class Hooks {

    @Before
    public void start(Scenario scenario) {
        System.out.println("Starting test: " + scenario.getName());
        BaseClass.setUp();
    }

    @After
    public void end(Scenario scenario) {
        System.out.println("Ending test: " + scenario.getName());

        byte[] screenshot = new byte[0];
        if (scenario.isFailed()) {
            screenshot = takeScreenshotAsBytes("Failed/" + scenario.getName());
        }
        // No need to take screenshots of passed test cases.
//        else {
//            screenshot = takeScreenshotAsBytes("Passed/" + scenario.getName());
//        }
        scenario.attach(screenshot, "image/png", scenario.getName());

        BaseClass.tearDown();
    }

}
