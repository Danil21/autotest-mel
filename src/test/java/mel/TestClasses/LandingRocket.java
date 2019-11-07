package mel.TestClasses;

import mel.Helper.AdditionalMethods;
import mel.Helper.SetDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Set;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.title;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LandingRocket extends SetDriver{
    private AdditionalMethods methods;
    private SharingArticle article;

    private By headerLogo = By.xpath("//*[@class='b-header__logo']");
    public By introText = By.cssSelector(".l-rocket-test__intro-text");
    private By facebookSharingButton = By.cssSelector(".g-share__share.g-share__share_fb");
    private By vkSharingButton = By.cssSelector(".g-share__share.g-share__share_vk");
    private By twitterSharingButton = By.cssSelector(".g-share__share.g-share__share_tw");
    private By okSharingButton = By.cssSelector(".g-share__share.g-share__share_ok");
    private By facebookSharingText = By.cssSelector("._6m7._3bt9");
    private By twitterSharingText = By.cssSelector("#status");
    private By okSharingText = By.cssSelector(".media-link_tx");
    private By firstCard = By.xpath("//*[contains(@href,'rockettest1')]");
    private By secondCard = By.xpath("//*[contains(@href,'rockettest2')]");
    private By thirdCard = By.xpath("//*[contains(@href,'rockettest3')]");
    private By reedArticleButton = By.cssSelector(".l-rocket-test__article-button > div");
    private By aboutRocketCard = By.xpath("//*[text()='Подробнее о карте']");
    private By footerSchool = By.xpath("//*[@class='i-control g-list g-list_pablo_mel g-list_pablo ']/a[1]");
    private By footerHighSchool = By.xpath("//*[@class='i-control g-list g-list_pablo_mel g-list_pablo ']/a[2]");
    private By footerFun = By.xpath("//*[@class='i-control g-list g-list_pablo_mel g-list_pablo ']/a[3]");
    private By footerFamily = By.xpath("//*[@class='i-control g-list g-list_pablo_mel g-list_pablo ']/a[4]");
    private By footerBlogs = By.xpath("//*[@class='i-control g-list g-list_pablo_mel g-list_pablo ']/a[5]");
    private By footerAfisha = By.xpath("//*[@class='i-control g-list g-list_pablo_mel g-list_pablo ']/a[6]");
    private By footerSkills = By.xpath("//*[@class='i-control g-list g-list_pablo_mel g-list_pablo ']/a[7]");
    private By vkSharingTitle = By.cssSelector("#share_title");
    private By rocketArticleAdv = By.cssSelector(".l-rocket-test__article-adv > div");

    public void clickOnHeaderLogo() {$(headerLogo).click();}

    public void clickOnReedArticleButton() {$(reedArticleButton).click();}

    public void clickOnAboutRocketCard() {$(aboutRocketCard).click();}

    public void sharing(String mail, String password, String article,
                        By sharingButton, By emailInput, By passwordInput, By loginButton, By textSelector) {
        methods = new AdditionalMethods();

        String parentWindowId = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();
        $(sharingButton).click();
        methods.moveFocusToTheNewWindow(oldWindowsSet);
        $(emailInput).sendKeys(mail);
        $(passwordInput).sendKeys(password);
        $(loginButton).click();
        Assert.assertTrue($(textSelector).getText().contains(article));
        driver.close();
        driver.switchTo().window(parentWindowId);
    }
    public void sharingFb(ArrayList<String> expectedResult){
        article = new SharingArticle();

        sharing(expectedResult.get(0),expectedResult.get(1), expectedResult.get(2), facebookSharingButton, article.fbEmailInput,
                article.fbPasswordInput, article.fbLoginButton, facebookSharingText);
    }

    public void sharingOk(ArrayList<String> expectedResult){
        article = new SharingArticle();

        sharing(expectedResult.get(5),expectedResult.get(4), expectedResult.get(2), okSharingButton, article.okEmailInput,
               article.okPasswordInput, article.okLoginButton, okSharingText);
    }

    public void checkTwitterSharingButton(String TwitterText) {
        methods = new AdditionalMethods();

        String parentWindowId = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();
        methods.Wait(200);
        $(twitterSharingButton).click();
        methods.Wait(200);
            methods.moveFocusToTheNewWindow(oldWindowsSet);
        methods.Wait(200);

        String str = $(twitterSharingText).getText();
        Assert.assertTrue(str.contains(TwitterText));

        driver.close();
        driver.switchTo().window(parentWindowId);
    }

    public void checkArticleCards(ArrayList<String> expectedResultArticle) {
        methods = new AdditionalMethods();

        ArrayList articleButtons = new ArrayList();
        articleButtons.add(firstCard);
        articleButtons.add(secondCard);
        articleButtons.add(thirdCard);

        for (int i = 0; i < articleButtons.size(); i++) {
            $((By) articleButtons.get(i)).click();
            methods.Wait(200);
            Assert.assertEquals(driver.getTitle(), expectedResultArticle.get(i));
            driver.navigate().back();
        }
    }

    public void checkRocketBackgroundImage(String imgPath) {
        String imageUrl = $(rocketArticleAdv).getCssValue("background-image");
        Assert.assertTrue(imageUrl.contains(imgPath));
    }

    public void checkFooterButtons(ArrayList<String> expectedResultFooter) {
        methods = new AdditionalMethods();

        ArrayList buttons = new ArrayList();
        buttons.add(footerSchool);
        buttons.add(footerHighSchool);
        buttons.add(footerFun);
        buttons.add(footerFamily);
        buttons.add(footerBlogs);
        buttons.add(footerAfisha);
        buttons.add(footerSkills );
        JavascriptExecutor jse1 = (JavascriptExecutor) driver;

        for (int i = 0; i < buttons.size(); i++) {

            jse1.executeScript("scroll(0,1000)");
            $((By) buttons.get(i)).click();
            methods.Wait(200);
            Assert.assertEquals(title(), expectedResultFooter.get(i));
            getWebDriver().navigate().back();
        }
    }
}