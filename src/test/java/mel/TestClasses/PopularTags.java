package mel.TestClasses;

import mel.Helper.GetUrl;
import mel.Helper.SetDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;

public class PopularTags extends SetDriver {

    // footer
    private By footerPopularLink = By.xpath("//*[@class='b-footer__links']//*[@href='/tags/popular']");
    // popular page
    private By searchInput = By.xpath("//*[@class='b-pb-suggest__emitter']/descendant::input[@class='g-input__input']");
    private By searchInputFirstSuggest = By.xpath("//*[@class='b-pb-suggest__output-agent']//a[1]");
    private By secondPageButton = By.xpath("//*[@class='l-all-tags__pagination']/div/a[1]");
    private By lastPageButton = By.xpath("//*[@class='l-all-tags__pagination']/div/a[last()-1]");
    public By popularCardTitle = By.xpath("//*[@class='l-all-tags__tags']/div[1]/descendant::div[@class='b-information-card__title']");
    public By popularCardAuthor = By.xpath("//*[@class='l-all-tags__tags']/div[1]/a/div[2]/object[1]");
    // tag's page
    public By tagPageTitle = By.xpath("//*[@class='l-tag__tag-name']");
    // author's page
    public By authorPageTitle = By.xpath("//*[@class='b-pb-author__name']");

    private GetUrl getUrl = new GetUrl();

    public void searchTagWithEnter() {
        $(searchInput).click();
        $(searchInput).sendKeys(Keys.ENTER);
    }

    public void checkFooterLink() {
        $(footerPopularLink).scrollIntoView(true);
        $(footerPopularLink).click();
    }

    public void searchTagWithDropdown(String tag) {
        $(searchInput).sendKeys(tag);
        $(searchInputFirstSuggest).click();
    }

    public void checkPagination() {
        getUrl.driverGet();
        String popularUrl = "/tags/popular/1";
        getUrl.driverGetCurrentUrl(popularUrl);
        $(secondPageButton).scrollTo().click();
        Assert.assertTrue(url().contains("2"));
        String numberLastPage = $(lastPageButton).getAttribute("href");
        $(lastPageButton).scrollTo().click();
        Assert.assertTrue(url().contains(numberLastPage));
    }

    public void checkTagCard() {
        $(popularCardTitle).click();
    }

    public void checkAuthorBadge() {
        $(popularCardAuthor).click();
    }
}
