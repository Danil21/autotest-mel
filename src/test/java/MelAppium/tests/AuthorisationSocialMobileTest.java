package MelAppium.tests;

import MelAppium.TestClass.LogoutMobile;
import MelAppium.TestClass.SocialMobileAuthorisation;
import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.SetDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class AuthorisationSocialMobileTest extends SetDriver {

    private AdditionalMethods methods = new AdditionalMethods();
    private SocialMobileAuthorisation socialMobileAuthorisation = new SocialMobileAuthorisation();
    private GetUrl getUrl = new GetUrl();
    private LogoutMobile logoutMobile = new LogoutMobile();


    @Test(priority = 1)
    public void facebookAuthorisation() {
        getUrl.driverGet();
        socialMobileAuthorisation.fbAuthorisation("easy2rider2@gmail.com", "knock705b");
        sleep(3000);
        logoutMobile.mobileProfile();
        sleep(2000);
        Assert.assertEquals(methods.getTextInsideElement(socialMobileAuthorisation.homePageNameMobile), "Eero Ettala");
        Assert.assertEquals(socialMobileAuthorisation.checkAvatarMobile(), "img");
        logoutMobile.mobileExit();
        $(logoutMobile.headerMenu).click();
        Assert.assertEquals(methods.getTextInsideElement(logoutMobile.comeInSiteButton), "Войти");
    }

    @Test(priority = 2)
    public void vkAuthorisation() {
        getUrl.driverGet();
        socialMobileAuthorisation.vkAuthorisation("89774128696", "knock705b");
        sleep(3000);
        Assert.assertEquals(methods.getTextInsideElement(socialMobileAuthorisation.homePageNameMobile), "Aleksandr Makedonskiy");
        sleep(2000);
        Assert.assertEquals(socialMobileAuthorisation.checkAvatarMobile(), "img");
        logoutMobile.mobileExit();
        $(logoutMobile.headerMenu).click();
        Assert.assertEquals(methods.getTextInsideElement(logoutMobile.comeInSiteButton), "Войти");
    }


    @Test(priority = 3)
    public void googleAuthorisation() {
        getUrl.driverGet();
        socialMobileAuthorisation.googleAuthorisation("easy3rider3@gmail.com", "knock705b");
        sleep(3000);
        Assert.assertEquals(methods.getTextInsideElement(socialMobileAuthorisation.homePageNameMobile), "Easy Rider");
        sleep(2000);
        Assert.assertEquals(socialMobileAuthorisation.checkAvatarMobile(), "img");
        logoutMobile.mobileExit();
        $(logoutMobile.headerMenu).click();
        Assert.assertEquals(methods.getTextInsideElement(logoutMobile.comeInSiteButton), "Войти");
    }

}
