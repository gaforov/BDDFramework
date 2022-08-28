package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features"
        , glue = "api/steps/practice"
        , dryRun = false
        , monochrome = true
        , publish = true
        , tags = "@workflow"
        , plugin = {
        "pretty"
        , "html:target/cucumber-reports/cucumberReports.html"
        , "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        , "json:target/cucumber.json"
        , "rerun:target/failed.txt"
}
)


public class APIWorkflowRunner {

}
