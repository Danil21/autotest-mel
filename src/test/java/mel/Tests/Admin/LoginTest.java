package mel.Tests.Admin;

import MelAppium.resources.config;
import mel.AdminTestClasses.AdminLogin;
import mel.AdminTestClasses.AdminLogout;
import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.SetDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.title;

public class LoginTest extends SetDriver {

    @Test
    public void authorisationAndLogout() {
        AdditionalMethods methods = new AdditionalMethods();
        GetUrl getUrl = new GetUrl();
        AdminLogout logout = new AdminLogout();
        AdminLogin adminLogin = new AdminLogin();

        getUrl.driverGetAdminUrl();
        adminLogin.adminAuthorisation("str@str.com", "123qwe11");
        sleep(1000);
        Assert.assertEquals(methods.getTextFromSelector(adminLogin.incorrectEmailAndPasswordText), "Неверный email или пароль");
        adminLogin.clearFields();
        adminLogin.adminAuthorisation(config.getTestProperty("adminLogin"),config.getTestProperty("adminPass"));
        logout.adminLogout();
        Assert.assertEquals(title(), "Публикации");
    }
}
