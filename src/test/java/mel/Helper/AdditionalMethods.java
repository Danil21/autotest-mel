package mel.Helper;

import com.codeborne.selenide.WebDriverRunner;
import mel.TestClasses.Logout;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;
import java.util.logging.Level;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.driver;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AdditionalMethods extends SetDriver {

    private By closeNotificationCookie = By.xpath("//div[@data-params='{\"type\":\"close-notification\"}']");
    private By closeNotificationCookieReact = By.xpath("//button[contains(@class,'cookie-notification__close-button_desktop')]");
    private By closeShearTop = By.xpath("//div[contains(@class, 'floatbar__close-icon')]");

    public void Wait(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void outputFromAnAccountSocialLogin() {
        Logout logout = new Logout();
        logout.exitToAuthorisationSocial();
    }

    public int generateNumber() {
        int a = 0;
        int b = 10000;
        return a + (int) (Math.random() * b);
    }

    public String generateTitleRandom(){
        int a = 0;
        int b = 10000;
        int randomNumber = a + (int) (Math.random() * b);
        return "Title" + randomNumber;
    }


    public String generateRandomCharSequence(int AmmountOfLetters) {
        return RandomStringUtils.randomAlphabetic(AmmountOfLetters);
    }

    public String generateStr() {
        String str1 = "testpablo";
        String str2 = "@p33.org";

        int a = 0;
        int b = 1000;
        int FirstRandomNumber = a + (int) (Math.random() * b);
        int SecondRandomNumber = a + (int) (Math.random() * b);

        return FirstRandomNumber + str1 + SecondRandomNumber + str2;
    }



    public void imageDownload(String str) {
        StringSelection stringSelection = new StringSelection(str);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.delay(3000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }


    public void moveFocusToTheNewWindow(final Set<String> oldWindowsSet) {
        String newWindows = (new WebDriverWait(getWebDriver(), 10)).until(new ExpectedCondition<String>() {
            @Override
            public String apply(WebDriver webDriver) {
                Set<String> newWindowsSet = webDriver.getWindowHandles();
                newWindowsSet.removeAll(oldWindowsSet);
                return newWindowsSet.size() > 0 ?
                        newWindowsSet.iterator().next() : null;
            }
        });
        switchTo().window(newWindows);
    }


    public void getBrowserLogs(ArrayList errors, String className) throws IOException {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String fileName = "MEL-" + format1.format(cal.getTime());

        WebDriver driver = driver().getWebDriver();
        if (!errors.isEmpty()) {
            FileWriter file = new FileWriter(fileName + ".txt");

            LoggingPreferences logs = new LoggingPreferences();
            logs.enable(LogType.BROWSER, Level.FINEST);
            logs.enable(LogType.BROWSER, Level.FINER);
            logs.enable(LogType.DRIVER, Level.WARNING);

            // logs.enable(LogType.CLIENT, Level.SEVERE);
            //logs.enable(LogType.PERFORMANCE, Level.INFO);
            // logs.enable(LogType.SERVER, Level.ALL);

            DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
            desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);

            for (LogEntry logEntry : driver.manage().logs().get(LogType.BROWSER)) {
                String str = String.valueOf(logEntry);
                boolean flag = Boolean.parseBoolean(null);
                for (int i = 0; i < errors.size(); i++) {
                    if (str.contains((CharSequence) errors.get(i))) {
                        flag = true;
                        break;
                    } else {
                        flag = false;
                    }
                }
                if (flag == false) {
                    file.write(className + ": " + str + "\n");
                }
            }
            file.close();
        }
    }

    public String getTextFromSelector(By selector) {
        return element(selector).getText();
    }

    public String getTextInsideElement(By selector) {
        return $(selector).innerText();
    }

    public String getHrefFromSelector(By selector) {
        return element(selector).getAttribute("href");
    }

    public String getNameAttributeElement(By name) {
        return element(name).getAttribute("name");
    }


    public String getCSSFromSelector(By selector, String[] cssValues) {
        String str = null;
        for (int i = 0; i < cssValues.length; i++) {
            if (i == 0) {
                str = $(selector).getCssValue(cssValues[i]);
            } else {
                str = str + " | " + $(selector).getCssValue(cssValues[i]);
            }
        }
        return str;
    }

    public void closeSheareTop() {
        $(closeShearTop).click();
    }


    public void closeNotificationCookie() {
        boolean closure = $(closeNotificationCookie).isDisplayed();
        boolean hidentNottif = $(closeNotificationCookie).isDisplayed() && $(closeNotificationCookieReact).isDisplayed();
        if (!hidentNottif) {
            scroll("300");
        }
         else if (!closure) {
            $(closeNotificationCookieReact).click();
        }
         else {
            $(closeNotificationCookie).click();
        }
    }

    public void deleteCookie() {
        getWebDriver().manage().deleteAllCookies();
    }

    public void scroll(String pixels) {
        JavascriptExecutor jse1 = (JavascriptExecutor) getWebDriver();
        jse1.executeScript("scroll(0," + pixels + ")");
    }


    public void sendKeyboardEnter() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public void openNewTab() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_T);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_T);
    }


    public void openChromeInIncognito() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        options.addArguments("--window-size=1280,1080");
        options.addArguments("--disable-gpu");
        options.addArguments("homepage");
        options.addArguments("new-window");

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        ChromeDriver chromeDriver = new ChromeDriver(options);
        WebDriverRunner.setWebDriver(chromeDriver);
        getWebDriver().quit();

    }


    public boolean getDisplayedElement(By element) {
        try {
            return ($(element).isDisplayed());
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
