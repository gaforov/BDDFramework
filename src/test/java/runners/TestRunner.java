package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features"
        , glue = "steps"
        , dryRun = false
        , monochrome = true
        , publish = true
        , tags = "@smoke"
        , plugin = {
        "pretty"
        , "html:target/cucumber-reports/cucumberReports.html"
}
)

public class TestRunner {

}
