package mel.Tests.Admin;

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

    private AdditionalMethods methods;
    private GetUrl getUrl;
    private AdminLogout logout;
    private AdminLogin login;

    @Test
    public void authorisationAndLogout() {
        methods = new AdditionalMethods();
        getUrl = new GetUrl();
        logout = new AdminLogout();
        login = new AdminLogin();

        getUrl.driverGetAdminUrl();
        login.adminAuthorisation("str@str.com", "123qwe11");
        sleep(1000);
        Assert.assertEquals(methods.getTextFromSelector(login.incorrectEmailAndPasswordText), "Неверный email или пароль");
        login.clearFields();
        login.adminAuthorisation("test@example.com", "123qwe11");
        logout.adminLogout();
        Assert.assertEquals(title(), "Публикации");
    }
}
