package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/Login.feature"
        ,glue = "steps"
        ,dryRun = false
        ,monochrome = true
        ,publish = true
        ,tags = "@smoke and @regression"
)
public class TestRunner {

}
