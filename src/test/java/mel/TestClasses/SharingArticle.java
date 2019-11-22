package mel.TestClasses;

import com.codeborne.selenide.WebDriverRunner;
import mel.Helper.AdditionalMethods;
import mel.Helper.SetDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Set;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class SharingArticle extends SetDriver{
    private AdditionalMethods methods;

    private By fbSharingButton = By.cssSelector(".sharing-panel__buttons_publication > a:nth-child(1)");
    private By vkSharingButton = By.cssSelector(".sharing-panel__buttons_publication > a:nth-child(2");
    private By twitterSharingButton = By.cssSelector(".sharing-panel__buttons_publication > a:nth-child(3)");
    private By okSharingButton = By.cssSelector(".sharing-panel__buttons_publication > a:nth-child(4)");
    private By pinterestSharingButton = By.cssSelector(".sharing-panel__buttons_publication > a:nth-child(5)");//a[contains(@class,'publication_pinterest')]
    //Fb
    public By fbEmailInput = By.cssSelector("#email");
    public By fbPasswordInput = By.xpath("//*[@type='password']");
    public By fbLoginButton = By.cssSelector("#loginbutton");
    //VK
    public By vkEmailInput = By.cssSelector("#login_submit > div > div > input:nth-child(7)");
    public By vkPasswordInput = By.cssSelector("#login_submit > div > div > input:nth-child(9)");
    public By vkLoginButton = By.cssSelector("#install_allow");
    //Twitter
    private By twitterEnterButton = By.cssSelector("#update-form > div.ft > fieldset.submit > input.button.selected.submit");
    private By twitterTextArticle = By.cssSelector("#status");
    // Ok
    public By okEmailInput = By.name("fr.email");
    public By okPasswordInput = By.name("fr.password");
    public By okLoginButton = By.cssSelector(".form-actions > input");

    //flip
    private By flipLogIn = By.xpath("//span[contains(text(),'Войти')]");
    private By clickFbLogin = By.xpath("//*[contains(text(),'Continue with Facebook')]");
    private By flipSharing = By.xpath("//button[contains(text(),'Флип')]");

    private By textFb = By.cssSelector("._5s6c");
    private By textVk = By.cssSelector("#share_title");
    private By textOk = By.cssSelector(".media-link_h");
    private By relapList = By.xpath("//div[contains(@class,'b-pb-article__body')]");


    private void sharing(String mail, String password, String article,
                        By sharingButton, By emailInput, By passwordInput, By loginButton, By textSelector){

        methods = new AdditionalMethods();
        String parentWindowId = getWebDriver().getWindowHandle();
        final Set<String> oldWindowsSet = getWebDriver().getWindowHandles();

        $(relapList).scrollIntoView(false);
        $(sharingButton).click();
        methods.moveFocusToTheNewWindow(oldWindowsSet);
        $(emailInput).sendKeys(mail);
        $(passwordInput).sendKeys(password);
        $(loginButton).click();
        Assert.assertEquals(methods.getTextFromSelector(textSelector), article);
        getWebDriver().close();
        getWebDriver().switchTo().window(parentWindowId);
    }

    public void twitterSharing(ArrayList<String> expectedResult){
        WebDriver driver = WebDriverRunner.driver().getWebDriver();

        methods = new AdditionalMethods();
        driver.navigate().refresh();
        String parentWindowId2 = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();
        $(twitterSharingButton).click();
        methods.moveFocusToTheNewWindow(oldWindowsSet);
        Assert.assertTrue(methods.getTextFromSelector(twitterTextArticle).contains(expectedResult.get(4)),
                "Article title isn't displayed on a twitter popup");
        $(twitterEnterButton).click();
        getWebDriver().close();
        driver.switchTo().window(parentWindowId2);
    }

    public void sharingFb(ArrayList<String> expectedResult){

        sharing(expectedResult.get(0), expectedResult.get(3), expectedResult.get(4), fbSharingButton, fbEmailInput,
                fbPasswordInput, fbLoginButton, textFb);
    }

    public void sharingVk(ArrayList<String> expectedResult){
        WebDriver driver = WebDriverRunner.driver().getWebDriver();
        driver.navigate().refresh();
        sharing(expectedResult.get(1), expectedResult.get(3), expectedResult.get(4), vkSharingButton, vkEmailInput,
                vkPasswordInput, vkLoginButton, textVk);
    }

    public void sharingOk(ArrayList<String> expectedResult){
        WebDriver driver = WebDriverRunner.driver().getWebDriver();
        driver.navigate().refresh();
        sharing(expectedResult.get(2), expectedResult.get(3), expectedResult.get(4), okSharingButton, okEmailInput,
                okPasswordInput, okLoginButton, textOk);
    }

    public void sharingPinterest() {
        WebDriver driver = WebDriverRunner.driver().getWebDriver();
        driver.navigate().refresh();
        String parentWindowId = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();


//        $(flipSharingButton).click(); //*[@data-test-id='seeItNow']
//        methods.moveFocusToTheNewWindow(oldWindowsSet);
//        $(flipLogIn).click();
//        $(clickFbLogin).click();
//        $(textFlip).shouldBe(Condition.visible);
//        $(flipSharing).click();
//        getWebDriver().close();
//        driver.switchTo().window(parentWindowId);
    }
}
