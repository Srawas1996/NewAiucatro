package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/resources/features", glue = "Steps"
        ,plugin = {"pretty","html:target/reports/report.html"}
//        ,dryRun = true
       )
public class RunCucumberTest {

}
