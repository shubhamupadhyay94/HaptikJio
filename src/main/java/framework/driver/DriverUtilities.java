package framework.driver;

import framework.exception.DriverUtilitiesException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.logging.Level;

public class DriverUtilities {

    private WebDriver driver;

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    /*
     * This is used to create a driver for different browser
     * @return WebDriver
     * */
    public WebDriver initDriver(String browser) throws DriverUtilitiesException {

        try {
            System.out.println("Browser name is " + browser);
            if (browser.trim().equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();

                ChromeOptions option = new ChromeOptions();
                option.addArguments("--test-type");
                option.addArguments("--disable-popup-bloacking");

                DesiredCapabilities chrome = new DesiredCapabilities();
                chrome.setJavascriptEnabled(true);
                chrome.setPlatform(Platform.ANY);
                chrome.setBrowserName("chrome");
                LoggingPreferences logPrefs = new LoggingPreferences();

                logPrefs.enable(LogType.PERFORMANCE, Level.ALL);

                chrome.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

                option.setCapability(ChromeOptions.CAPABILITY, option);
                //Create driver object for Chrome

              tlDriver.set(new ChromeDriver(option));
              //  tlDriver.set(new RemoteWebDriver(new URL("http://localhost:4444/"),option));

            } else if (browser.trim().equalsIgnoreCase("firefox")) {
                FirefoxOptions ffOptions = new FirefoxOptions();
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("intl.accept_languages", "fr");  // Setting the locale language to accept French
                profile.setPreference("plugin.state.flash", 0);  // Disabling Flash using Firefox profile
                profile.setAcceptUntrustedCertificates(true);
                ffOptions.setProfile(profile);
                ffOptions.setCapability("browser", "Firefox");

                ffOptions.setPlatformName(String.valueOf(Platform.ANY));
                ffOptions.setCapability("os", "Windows");

            WebDriverManager.firefoxdriver().setup();
                tlDriver.set(new FirefoxDriver(ffOptions));
            } else if (browser.trim().equalsIgnoreCase("edge")) {
                EdgeOptions edgeOptions = new EdgeOptions();
                DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                desiredCapabilities.setPlatform(Platform.ANY);
                desiredCapabilities.setBrowserName("edge");
                edgeOptions.setCapability(EdgeOptions.CAPABILITY,edgeOptions);
                WebDriverManager.edgedriver().setup();
                tlDriver.set(new EdgeDriver(desiredCapabilities));
            } else if (browser.trim().equalsIgnoreCase("ie")) {

                WebDriverManager.iedriver().setup();
                tlDriver.set(new InternetExplorerDriver());
            }  else if (browser.trim().equalsIgnoreCase("headlessChrome")) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                 options.setPlatformName(String.valueOf(Platform.WINDOWS));


                options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
                tlDriver.set(new ChromeDriver(options));
            } else {
                System.out.println("Browser is not supported.");
            }

            getDriver().manage().deleteAllCookies();
            getDriver().manage().window().maximize();
            getDriver().get("https://www.amazon.in/");
            Thread.sleep(2000);
            return getDriver();
        } catch (Exception e) {
            throw new DriverUtilitiesException(e.getMessage(), e);
        }
    }

    /*
     * This is used to get the driver with ThreadLocal
     * @return
     * */
    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void main(String args[]) throws DriverUtilitiesException {

        DriverUtilities driverUtilities = new DriverUtilities();
        WebDriver driver = driverUtilities.initDriver("ie");
        driver.get("https://www.upwork.com/");
    }

}
