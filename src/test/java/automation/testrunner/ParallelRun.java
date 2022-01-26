package automation.testrunner;

import framework.driver.DriverUtilities;
import framework.exception.ConfigFileReaderException;
import framework.exception.ReadExcelException;
import framework.utilities.ConfigFileReader;
import framework.utilities.ReadExcel;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

@CucumberOptions(features = {"src\\test\\resources\\com\\feature\\"},
        monochrome = true,
        glue ={"automation.stepdefinitions","automation.hooks"},
       plugin =  {"pretty",
        "timeline:test-output-thread/","html:target/cucumber-reports/cucumber.html",
        "json:target/cucumber-reports/cucumber.json"
})

public class ParallelRun extends AbstractTestNGCucumberTests {
    public static Map<String, String> map;
    private ConfigFileReader config;
    private DriverUtilities driverUtilities;
    private WebDriver driver;
    public static Properties properties = new Properties();
    public static String browser =null;

    @Override
    @DataProvider(parallel = false )
    public Object[][] scenarios(){
        return super.scenarios();
    }


    @BeforeSuite
    public void projectEnvSetup(){
        System.out.println("========================BeforeSuite==============================");
        try {
            config = new ConfigFileReader();
            properties = config.configFile();

        } catch (ConfigFileReaderException e) {
            e.printStackTrace();
        }

        ReadExcel readExcel = new ReadExcel();
        try {
            map = readExcel.readExcelDataBasedOnEnvironment();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (ReadExcelException e) {
            e.printStackTrace();
        }

    }

    @AfterSuite
    public void afterProjecEnvSetup(){
        System.out.println("========================AfterSuite==============================");
    }
}
