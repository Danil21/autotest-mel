package mel.Tests.Site;

import MelAppium.resources.config;
import mel.AdminTestClasses.AdminAddingTestContent;
import mel.AdminTestClasses.AdminLogin;
import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.SetDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Set;

import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.title;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class OpenInAdminTest extends SetDriver {
    AdditionalMethods methods = new AdditionalMethods();
    GetUrl getUrl = new GetUrl();
    AdminAddingTestContent admin = new AdminAddingTestContent();
    AdminLogin adminLogin = new AdminLogin();

    @Test
    public void openPublicationInAdmin() {
        getUrl.driverGetAdminUrl();
        adminLogin.adminAuthorisation(config.getTestProperty("adminLogin"),config.getTestProperty("adminPass"));

        ((JavascriptExecutor)getWebDriver()).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(getWebDriver().getWindowHandles());
        getWebDriver().switchTo().window(tabs.get(1));

        getUrl.driverGetCurrentUrl("otnosheniya_s_detmi/8147692-without_fear");
        methods.closeNotificationCookie();
        final Set<String> oldWindowsSet = getWebDriver().getWindowHandles();

        admin.clickOnOpenInAdmin();
        sleep(2000);
        methods.moveFocusToTheNewWindow(oldWindowsSet);
        Assert.assertTrue(title().contains("Редактирование публикации"), "Link to admin doesn't work,");


    }
}