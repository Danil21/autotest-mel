package mel.Helper;

import MelAppium.helper.AdditionalMethodsMobile;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.remote.CapabilityType.BROWSER_NAME;


public abstract class SetDriver extends AdditionalMethodsMobile {

    WebDriver driver;
    public static WebDriverWait wait;

    @Parameters("browserName")
    @BeforeTest
    WebDriver getDriver(String browserName) {
        if (browserName.equals("firefox")) {
            Configuration.browser = "firefox";
        } else if (browserName.equals("chrome")) {
            Configuration.browser = "chrome";
        }

        return driver;
    }

    @BeforeClass
    public static void setUpBeforeClass() throws MalformedURLException {
        WebDriver driver;

        int testRunArea = 1;

        switch (testRunArea) {
            case 1:
                // Configuration.browser = "chrome";
                //  WebDriverManager.getInstance(CHROME).setup();
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
                browser.setBrowserName("firefox");
                browser.setVersion("77");
                browser.setCapability("enableVNC", true);
                // browser.setCapability("enableVideo", true);
                Configuration.remote = "http://167.71.83.66:4444/wd/hub";
                //  driver = new RemoteWebDriver(URI.create("http://167.71.83.66:4444/wd/hub").toURL(), browser);
                // setWebDriver(driver);

                Configuration.browserSize = "1280x1024";
                Configuration.savePageSource = false;
                Configuration.reportsFolder = "test-result/desktop";
                break;

            case 3:
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


    @AfterTest
    public static void tearDownAfterClass() {
        getWebDriver().close();

//        AdditionalMethods methods = new AdditionalMethods();
//        methods.onExecutionFinish();
    }

}
