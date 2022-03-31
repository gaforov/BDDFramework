package steps;

import base.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import static utils.CommonMethods.takeScreenshotAsBytes;

public class Hooks {

    @Before
    public void start() {
        BaseClass.setUp();
    }

    @After
    public void end(Scenario scenario) {

        byte[] screenshot;
        if (scenario.isFailed()) {
            screenshot = takeScreenshotAsBytes("Failed/" + scenario.getName());
        }else {
            screenshot = takeScreenshotAsBytes("Passed/" + scenario.getName());
        }
        scenario.attach(screenshot, "image/png", scenario.getName());


        BaseClass.tearDown();
    }
}
