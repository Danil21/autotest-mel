package mel.Tests.Site;

import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.SetDriver;
import mel.TestClasses.PopularTags;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.title;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;

public class PopularTagsTest extends SetDriver {

    private AdditionalMethods methods;
    private GetUrl getUrl = new GetUrl();
    private PopularTags popularTags;
    private String popularUrl = "/tags/popular/1";

//    @AfterSuite
//    public void browserLogs() throws IOException {
//        AdditionalMethods methods = new AdditionalMethods();
//
//        ArrayList errors = new ArrayList();
//        errors.add("yandex");
//        errors.add("onthe.io");
//        errors.add("adriver");
//        methods.getBrowserLogs(errors, "PopularTagsTest");
//    }

    @Test
    public void PopularTags() {
        methods = new AdditionalMethods();
        popularTags = new PopularTags();

        // check footer link from main page
        getUrl.driverGet();
        //methods.closeNotificationCookie();
        popularTags.checkFooterLink();
        Assert.assertTrue(url().contains("/tags/popular/1"));
        // check search input with dropdown
        popularTags.searchTagWithDropdown("новости");
        Assert.assertTrue(title().contains("Новости"));
        getWebDriver().navigate().back();

        // check search input with Enter
        popularTags.searchTagWithEnter();
        sleep(2000);
        title().contains("Новости");

        // check pagination, compare part of button link with url
        popularTags.checkPagination();

        // move from card block to tag page and compare tag's title
        getUrl.driverGetCurrentUrl(popularUrl);
        String popularCardTitle = methods.getTextFromSelector(popularTags.popularCardTitle);
        popularTags.checkTagCard();
        String tagPageTitle = methods.getTextFromSelector(popularTags.tagPageTitle).toLowerCase();
        Assert.assertEquals(popularCardTitle, tagPageTitle);

        // move from card block to author page and compare author's title
        getUrl.driverGetCurrentUrl(popularUrl);
        String popularCardAuthor = methods.getTextFromSelector(popularTags.popularCardAuthor);
        popularTags.checkAuthorBadge();
        String authorPageTitle = methods.getTextFromSelector(popularTags.authorPageTitle);
        Assert.assertEquals(popularCardAuthor, authorPageTitle);
    }
}