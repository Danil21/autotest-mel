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

    private AdditionalMethods methods = new AdditionalMethods();
    private GetUrl getUrl = new GetUrl();
    private AdminLogout logout = new AdminLogout();
    private AdminLogin adminLogin = new AdminLogin();

    @Test
    public void authorisationAndLogout() {
        getUrl.driverGetAdminUrl();
        adminLogin.adminAuthorisation("str@str.com", "123qwe11");
        sleep(1000);
        Assert.assertEquals(methods.getTextFromSelector(adminLogin.incorrectEmailAndPasswordText), "Неверный email или пароль");
        adminLogin.clearFields();
        adminLogin.adminAuthorisation(config.getTestProperty("adminLogin"), config.getTestProperty("adminPass"));
        logout.adminLogout();
        Assert.assertEquals(title(), "Публикации");
    }
}
