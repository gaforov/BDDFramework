package steps;

import base.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void start() {
        BaseClass.setUp();
    }

    @After
    public void end() {
        BaseClass.tearDown();
    }
}
