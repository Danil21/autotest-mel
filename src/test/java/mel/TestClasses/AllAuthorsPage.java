package mel.TestClasses;

import com.codeborne.selenide.WebDriverRunner;
import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.SetDriver;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.title;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.apache.commons.lang.StringUtils.containsIgnoreCase;

public class AllAuthorsPage extends SetDriver {

    private By firstAuthor = By.xpath("//*[@class='l-all-authors__authors']/div[1]/a/div[1]/div//div[@class='b-information-card__title']");
    private By searchInput = By.xpath("//*[@class='b-pb-suggest__emitter']//input");
    private By suggestWithAuthor = By.xpath("//*[@class='b-pb-suggest__output-agent']/div/a");
    private By createPostButton = By.xpath("//*[@class='b-announcement__create-post-button']");
    private By firstAuthorTag = By.xpath("//*[@class='l-all-authors__authors']/div[1]//object[1]//span");
    private By secondPageButton = By.xpath("//*[@class='l-all-authors__pagination']/div/a[1]");
    private By lastPageButton = By.xpath("//*[@class='l-all-authors__pagination']/div/a[last()-1]");
    private By authorTag = By.xpath("//*[@class='l-tag__tag-name']");
    private By authorisationTitle = By.cssSelector(".g-auth-social__header");

    private String authorsUrl = "authors/1";
    private GetUrl getUrl = new GetUrl();

    public void checkSearchWithAuthor() {

        getUrl.driverGetCurrentUrl(authorsUrl);
        String firstAuthorName = $(firstAuthor).text();
        if (firstAuthorName.contains("Блогер")) {
            firstAuthorName = firstAuthorName.substring(0, firstAuthorName.length() - 6);
        }
        $(searchInput).sendKeys(firstAuthorName);
        $(suggestWithAuthor).click();
        Assert.assertTrue(title().contains(firstAuthorName));
        WebDriverRunner.getWebDriver().navigate().back();
    }

    public void checkCreatePost() {
        Login login = new Login();
        AdditionalMethods methods = new AdditionalMethods();

        getUrl.driverGetCurrentUrl(authorsUrl);
        $(createPostButton).click();
        Assert.assertEquals(methods.getTextFromSelector(authorisationTitle), "Авторизация");
        login.automationIsNotMain("test153153153@mail.ru", "12345678");

        getUrl.driverGetCurrentUrl(authorsUrl); // переделать,  нет проверки кнопки "написать" в блог на странице блогеров
        Assert.assertTrue(title().contains("Новая запись в блог"));
    }

    public void checkTagsInAuthorCard() {
        getUrl.driverGetCurrentUrl(authorsUrl);
        String firstTag = $(firstAuthorTag).text().trim();
        $(firstAuthorTag).click();
        String secondTag = $(authorTag).text();

        if (!(containsIgnoreCase(secondTag, firstTag))) {
            Assert.fail("tag not contains in title");
        }
    }

    public void checkPagination() {
        getUrl.driverGetCurrentUrl(authorsUrl);
        $(secondPageButton).scrollIntoView(true).click();
        Assert.assertTrue(url().contains("2"));

        String numberLastPage = $(lastPageButton).scrollIntoView(true).getAttribute("href");
        $(lastPageButton).click();
        Assert.assertTrue(url().contains(numberLastPage));
    }
}
