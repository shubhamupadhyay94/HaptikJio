package automation.testrunner;

import automation.hooks.ApplicationHooks;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src\\test\\resources\\com\\feature\\"},
glue ={"automation.stepdefinitions", "automation.hooks"}
, plugin = {"pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        "timeline:test-output-thread/"
         })

//"html:target/cucumber-reports/cucumber.html",
//        "json:target/cucumber-reports/cucumber.json",

public class StoreTestRunner {


}

