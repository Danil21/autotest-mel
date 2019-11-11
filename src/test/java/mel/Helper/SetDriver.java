package mel.Helper;

import MelAppium.helper.AdditionalMethodsMobile;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;
import static org.openqa.selenium.remote.CapabilityType.BROWSER_NAME;


public abstract class SetDriver extends AdditionalMethodsMobile {

    private static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeClass
    public static void setUpBeforeClass() throws MalformedURLException {

        int testRunArea = 1;

        switch (testRunArea) {
            case 1:
                Configuration.browser = "chrome";
                WebDriverManager.getInstance(CHROME).setup();
                // System.setProperty("webdriver.chrome.driver", ".//chromedriver.exe");
                Configuration.timeout = 10000;
                Configuration.browserSize = "1280x1024";
                Configuration.savePageSource = false;
                Configuration.reportsFolder = "test-result/desktop";
                //Configuration.fastSetValue = true;
                break;
            case 2:
                DesiredCapabilities browser = new DesiredCapabilities();
                browser.setBrowserName("chrome");
                browser.setVersion("76");
                browser.setCapability("enableVNC", true);
                driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), browser);
                setWebDriver(driver);
                // Configuration.remote = "";
                //Configuration.browserVersion ="";
                break;

            case 3:
                AndroidDriver<MobileElement> driver;
                Configuration.reportsFolder = "test-result/mobile";
                Configuration.savePageSource = false;
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("deviceName", "520001c4b8a365b7");//Samsung A5
                capabilities.setCapability("platformName", "Android");
                capabilities.setCapability("automationName", "UiAutomator2");
                capabilities.setCapability(BROWSER_NAME, "Chrome");
                capabilities.setCapability("chromedriverExecutable", "C:/Users/User/Desktop/123/chromedriver.exe");
                //capabilities.setCapability("appPackage", "com.android.chrome");
                //capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");
                AdditionalMethods methods = new AdditionalMethods();
                methods.onExecutionStart();
                driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:1234/wd/hub"), capabilities);
                driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                WebDriverRunner.setWebDriver(driver);

        }


        // запуск без UI, в режиме headless chrome
        /* ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--test-type");
        options.addArguments("--ash-estimated-presentation-delay");
        options.addArguments("--enable-offline-auto-reload-visible-only");
        options.addArguments("--disable-gpu");  */
    }

    @AfterClass
    public static void tearDownAfterClass() {
        getWebDriver().close();
//        AdditionalMethods methods = new AdditionalMethods();
//        methods.onExecutionFinish();
    }

}
