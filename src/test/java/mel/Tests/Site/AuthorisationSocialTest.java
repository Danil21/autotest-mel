package mel.Tests.Site;

import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.SetDriver;
import mel.TestClasses.Logout;
import mel.TestClasses.SocialNetworksAuthorisation;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

public class AuthorisationSocialTest extends SetDriver {

    private AdditionalMethods methods;
    private SocialNetworksAuthorisation authorisationSocial;
    private GetUrl getUrl;
    private Logout logout;

    @AfterClass
    public void browserLogs() throws IOException {
        methods = new AdditionalMethods();

        ArrayList errors = new ArrayList();
        methods.getBrowserLogs(errors, "AuthorisationSocialTest");
    }

    @Test
    public void facebookAuthorisation() {
        authorisationSocial = new SocialNetworksAuthorisation();
        getUrl = new GetUrl();
        logout = new Logout();
        methods = new AdditionalMethods();

        getUrl.driverGet();
        methods.deleteCookie();
        authorisationSocial.fbAuthorisation("easy2rider2@gmail.com", "knock705b");
        Assert.assertEquals(methods.getTextInsideElement(authorisationSocial.homePageName), "Eero Ettala");
        Assert.assertEquals(authorisationSocial.checkAvatar(), "img");
        methods.outputFromAnAccountSocialLogin();
        Assert.assertEquals(methods.getTextInsideElement(logout.enterButton), "вход");
    }

    @Test
    public void vkAuthorisation() {
        methods = new AdditionalMethods();
        authorisationSocial = new SocialNetworksAuthorisation();
        getUrl = new GetUrl();
        logout = new Logout();

        getUrl.driverGet();
        methods.deleteCookie();
        authorisationSocial.vkAuthorisation("89774128696", "knock705b");//можно написать условие которое проверяет забанен пользователь или нет
        Assert.assertEquals(methods.getTextInsideElement(authorisationSocial.homePageName), "Aleksandr Makedonskiy");
        Assert.assertEquals(authorisationSocial.checkAvatar(), "img");
        methods.outputFromAnAccountSocialLogin();
        Assert.assertEquals(methods.getTextInsideElement(logout.enterButton), "вход");
    }

    @Test
    public void googleAuthorisation() {
        methods = new AdditionalMethods();
        authorisationSocial = new SocialNetworksAuthorisation();
        getUrl = new GetUrl();
        logout = new Logout();

        getUrl.driverGet();
        methods.deleteCookie();
        authorisationSocial.googleAuthorisation("easy3rider3@gmail.com", "knock705b");
        Assert.assertEquals(methods.getTextInsideElement(authorisationSocial.homePageName), "Easy Rider");
        Assert.assertEquals(authorisationSocial.checkAvatar(), "img");
        methods.outputFromAnAccountSocialLogin();
        Assert.assertEquals(methods.getTextInsideElement(logout.enterButton), "вход");
    }
}
