package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "@target/failed.txt"
        , glue = "steps"
        , monochrome = true
        , plugin = {
        "pretty"
//        , "html:target/cucumber-reports/cucumberReports.html"
}
)

public class FailedRunner {

}
