package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features", glue = {"stepDefinitions"},
        ,plugin = {"pretty","html:target/reports/report.html"}
//        ,dryRun = true
        ,monochrome = true
       )
public class TestRunner {

}
