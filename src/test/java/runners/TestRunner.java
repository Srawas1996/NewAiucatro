package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features", glue = "Steps"
        ,tags = "@Regression"
//        ,plugin = {"pretty","html:target/reports/report.html", "json:target/report/jsons/report.json"}
//        ,dryRun = true
       )
public class TestRunner {

}
