package mel.Helper;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BrowserDate {

    protected WebDriver driver;

    @Parameters("browserName")
    @BeforeTest
    protected WebDriver getDriver(String browserName) {
        if (browserName.equals("chrome")) {
            Configuration.browser = "chrome";
        } else if (browserName.equals("firefox")) {
            Configuration.browser = "firefox";
        }

        return driver;
    }

    @AfterTest
    public static void tearDown() {
        Selenide.close();

    }

}
