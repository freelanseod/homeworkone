package addressbook.bdd;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "classpath:bdd", plugin = {"pretty", "html:build/cucumberreport"})
public class GroupTests extends AbstractTestNGCucumberTests {
}
