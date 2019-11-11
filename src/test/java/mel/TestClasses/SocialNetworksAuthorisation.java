package mel.TestClasses;

import mel.Helper.AdditionalMethods;
import mel.Helper.SetDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.util.Set;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class SocialNetworksAuthorisation extends SetDriver{
    private SharingArticle article;

    // Button Enter on main
    private By headerLoginButton = By.xpath("//*[contains(text(),'вход')]");
    // Button Facebook
    public By facebookLoginButton = By.cssSelector(".g-button_fb");
    // Button Vk
    public By vkLoginButton = By.cssSelector(".g-button_vk");
    // Button Google
    public By googleLoginButton = By.cssSelector(".g-button_g");
    // Fields for Google:
    public By googleEmailInput = By.cssSelector("#identifierId");
    public By googleNextButton = By.cssSelector("#identifierNext");
    public By googlePasswordInput = By.cssSelector("#password > div.aCsJod.oJeWuf > div > div.Xb9hP > input");
    public By googleEnterLoginButton = By.cssSelector("#passwordNext");

    public By  homePageName = By.cssSelector(".user-info > div");
    private By userAvatar = By.cssSelector(".user-info > img");

    private void authorisation(By loginButton, By socialNetworkButton, By emailInput, By passwordInput,
                              String email, String password, By enterButton){
        AdditionalMethods methods = new AdditionalMethods();

        $(loginButton).click();

        String parentWindowId = getWebDriver().getWindowHandle();
        final Set<String> oldWindowsSet = getWebDriver().getWindowHandles();
        $(socialNetworkButton).click();
        methods.moveFocusToTheNewWindow(oldWindowsSet);
        $(emailInput).sendKeys(email);

        if(checkGoogleButton()){
            $(googleNextButton).click();
        }

        $(passwordInput).sendKeys(password);
        $(enterButton).click();
        getWebDriver().switchTo().window(parentWindowId);
    }

    public void fbAuthorisation(String email, String password){
        article = new SharingArticle();
        authorisation(headerLoginButton, facebookLoginButton, article.fbEmailInput, article.fbPasswordInput, email, password, article.fbLoginButton);
    }

    public void vkAuthorisation(String email, String password){
        article = new SharingArticle();
        authorisation(headerLoginButton, vkLoginButton, article.vkEmailInput, article.vkPasswordInput, email, password, article.vkLoginButton);
    }

    public void googleAuthorisation(String email, String password){
        authorisation(headerLoginButton, googleLoginButton, googleEmailInput, googlePasswordInput, email, password, googleEnterLoginButton);
    }

    private boolean checkGoogleButton() {
        try {
            return $(googleNextButton).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String checkAvatar(){
        return $(userAvatar).getTagName();
    }
}
