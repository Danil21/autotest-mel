package mel.Tests.Admin;

import mel.AdminTestClasses.AdminLogin;
import mel.AdminTestClasses.LockEditingPublication;
import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.SetDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;


public class LockEdititngPublicationTest extends SetDriver {

    // tests dont work in the parallel stream

    AdditionalMethods methods = new AdditionalMethods();
    GetUrl getUrl = new GetUrl();
    AdminLogin adminLogin = new AdminLogin();
    private LockEditingPublication lockEditingPublication = new LockEditingPublication();


    @Test
    public void LockEditUsers() {

        getUrl.driverGetAdminUrl();
        adminLogin.adminAuthorisation("test@example.com", "123qwe11");
        lockEditingPublication.clickInFirstPublication();

        lockEditingPublication.editPublicationFirsAdmin("Edit Title", "новости", "тут изменили текст для чего то");
        closeWebDriver();
        getUrl.driverGetAdminUrl();
        adminLogin.adminAuthorisation("d.vagin@mel.fm", "qvgarunet1");
        methods.Wait(2000);
        Assert.assertTrue(lockEditingPublication.getDisplayedLockImg());
        lockEditingPublication.clickInFirstPublication();
        Assert.assertEquals(lockEditingPublication.getTextFromUserInModal(), "Admin Admin");
        lockEditingPublication.clickInInterruptEditing();
        lockEditingPublication.clickInStopEditingButton();

    }
}
