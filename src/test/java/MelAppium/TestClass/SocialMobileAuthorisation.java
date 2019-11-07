package MelAppium.TestClass;

import mel.Helper.AdditionalMethods;
import mel.Helper.SetDriver;
import mel.TestClasses.SharingArticle;
import mel.TestClasses.SocialNetworksAuthorisation;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.util.Set;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class SocialMobileAuthorisation extends SetDriver {
    private AdditionalMethods methods;
    private SocialNetworksAuthorisation social = new SocialNetworksAuthorisation();

    private By headerMenu = By.xpath("//button[contains(@class,'hamburger-button')]");
    private By comeInSiteButton = By.xpath("//button[contains(@class,'top-mobile-menu__login-button')]");
    //vk
    private By vkEmailInput = By.xpath("//input[@name='email']");
    private By vkPasswordInput = By.xpath("//input[@name='pass']");
    private By vkLoginButton = By.xpath("//*[@value='Войти']");
    //fb
    private By fbEmailInput = By.cssSelector("#m_login_email");
    private By fbPasswordInput = By.cssSelector("#m_login_password");
    private By fbLoginButton = By.xpath("//*[@value='Войти']");

    public By homePageNameMobile = By.cssSelector(".user-mobile-info__name");
    public By userAvatarMobile = By.xpath("//img[@class='user-mobile-info__photo']");


    private void authorisation(By menu, By loginButton, By socialNetworkButton, By emailInput, By passwordInput,
                               String email, String password, By enterButton) {
        methods = new AdditionalMethods();
        $(menu).click();
        $(loginButton).click();
        String parentWindowId = getWebDriver().getWindowHandle();
        final Set<String> oldWindowsSet = getWebDriver().getWindowHandles();
        $(socialNetworkButton).click();
        methods.moveFocusToTheNewWindow(oldWindowsSet);
        $(emailInput).sendKeys(email);

        if (checkGoogleButton()) {
            $(social.googleNextButton).click();
        }

        $(passwordInput).sendKeys(password);
        $(enterButton).click();
        getWebDriver().switchTo().window(parentWindowId);
    }

    public void fbAuthorisation(String email, String password) {
        authorisation(headerMenu, comeInSiteButton, social.facebookLoginButton, fbEmailInput,
                fbPasswordInput, email, password, fbLoginButton);
    }

    public void vkAuthorisation(String email, String password) {
        authorisation(headerMenu, comeInSiteButton, social.vkLoginButton, vkEmailInput,
                vkPasswordInput, email, password, vkLoginButton);
    }

    public void googleAuthorisation(String email, String password) {
        authorisation(headerMenu, comeInSiteButton, social.googleLoginButton, social.googleEmailInput,
                social.googlePasswordInput, email, password, social.googleEnterLoginButton);
    }

    private boolean checkGoogleButton() {
        try {
            return $(social.googleNextButton).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String checkAvatarMobile(){
        String str = $(userAvatarMobile).getTagName();
        return str;
    }
}
