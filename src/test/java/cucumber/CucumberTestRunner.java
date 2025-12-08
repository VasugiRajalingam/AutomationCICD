package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/cucumber", glue = "stepdefinition",
        monochrome = true, tags = "@Login", plugin = {"html:target/cucumber.html"})
public class CucumberTestRunner extends AbstractTestNGCucumberTests {
}
