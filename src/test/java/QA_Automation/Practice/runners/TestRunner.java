package QA_Automation.Practice.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.checkerframework.checker.units.qual.t;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = "QA_Automation.Practice.stepdefinitions", plugin = {
                "pretty",
                "html:target/cucumber-report.html",
                "json:target/cucumber-report.json",
                "junit:target/cucumber-report.xml"
}, monochrome = true, dryRun = false)
public class TestRunner {
}
