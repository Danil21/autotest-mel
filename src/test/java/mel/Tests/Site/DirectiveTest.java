package mel.Tests.Site;

import mel.AdminTestClasses.AdminLogin;
import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.SetDriver;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.$;

public class DirectiveTest extends SetDriver{

    private AdditionalMethods methods;
    private GetUrl getUrl;
    private AdminLogin adminLogin;

    private By siteMapText = By.cssSelector(".html-attribute-value");
    private By robotTxtText = By.cssSelector("pre");
    private By rssText = By.cssSelector("pre");
    private By adminRobotText = By.cssSelector("pre");


//    @AfterClass
//    public void browserLogs() throws IOException {
//        methods = new AdditionalMethods();
//
//        ArrayList errors = new ArrayList();
//        methods.getBrowserLogs(errors, "DirectiveTest");
//    }

    @Test
    public void directiveTest() {
        methods = new AdditionalMethods();
        getUrl = new GetUrl();
        adminLogin = new AdminLogin();
        getUrl.driverGet();
        getUrl.driverGetCurrentUrl("sitemap");
        Assert.assertTrue($(siteMapText).isDisplayed());
        Assert.assertEquals(methods.getTextFromSelector(siteMapText), "http://www.sitemaps.org/schemas/sitemap/0.9");

        getUrl.driverGetCurrentUrl("rss/default-news");
        Assert.assertTrue($(rssText).isDisplayed());

        getUrl.driverGetCurrentUrl("rss/default-all");
        Assert.assertTrue($(rssText).isDisplayed());

        getUrl.driverGetCurrentUrl("rss/yandex-news");
        Assert.assertTrue($(rssText).isDisplayed());

        getUrl.driverGetCurrentUrl("rss/flipboard/author/gramotnost-na-mele");
        Assert.assertTrue($(rssText).isDisplayed());

        getUrl.driverGetCurrentUrl("rss/flipboard/tag/chto-pochitat");
        Assert.assertTrue($(rssText).isDisplayed());

        getUrl.driverGetCurrentUrl("rss/flipboard/tag/vospitaniye");
        Assert.assertTrue($(rssText).isDisplayed());

        getUrl.driverGetCurrentUrl("rss/flipboard/tag/inostrannyye_yazyki");
        Assert.assertTrue($(rssText).isDisplayed());

        getUrl.driverGetCurrentUrl("rss/flipboard/tag/chto_posmotret");
        Assert.assertTrue($(rssText).isDisplayed());

        getUrl.driverGetCurrentUrl("rss/flipboard/rubric/school");
        Assert.assertTrue($(rssText).isDisplayed());

        getUrl.driverGetCurrentUrl("rss/zen");
        Assert.assertTrue($(rssText).isDisplayed());

        getUrl.driverGetCurrentUrl("rss/my-widget/all-links");
        Assert.assertTrue($(rssText).isDisplayed());

        getUrl.driverGetCurrentUrl("rss/my-widget/last-day-links");
        Assert.assertTrue($(rssText).isDisplayed());

        getUrl.driverGetCurrentUrl("rss/instant-articles");
        Assert.assertTrue($(rssText).isDisplayed());

        getUrl.driverGetCurrentUrl("rss/yandex-turbo");
        Assert.assertTrue($(rssText).isDisplayed());

        getUrl.driverGetCurrentUrl("rss/mailru-pulse");
        Assert.assertTrue($(rssText).isDisplayed());

        getUrl.driverGetCurrentUrl("robots.txt");
        Assert.assertTrue($(robotTxtText).isDisplayed());
        Assert.assertEquals(methods.getTextFromSelector(robotTxtText), "User-agent: Yandex\n" +
                "Disallow: /*?amp\n" +
                "Disallow: /*?nomr\n" +
                "Disallow: /*?ext\n" +
                "Disallow: /*&amp\n" +
                "Disallow: /*&nomr\n" +
                "Disallow: /*&ext\n" +
                "Disallow: /*?ogimage\n" +
                "Disallow: /draft/\n" +
                "Clean-param: #!\n" +
                "Crawl-delay: 1\n" +
                "Host: mel.fm\n" +
                "\n" +
                "User-agent: *\n" +
                "Disallow: /*?amp\n" +
                "Disallow: /*?nomr\n" +
                "Disallow: /*?ext\n" +
                "Disallow: /*&amp\n" +
                "Disallow: /*&nomr\n" +
                "Disallow: /*&ext\n" +
                "Disallow: /*?ogimage\n" +
                "Disallow: /draft/\n" +
                "\n" +
                "Sitemap: https://mel.fm/sitemap");
        getUrl.driverGetCurrentAdminUrl("robots.txt");
        Assert.assertTrue($(robotTxtText).isDisplayed());
        Assert.assertEquals(methods.getTextFromSelector(robotTxtText), "User-agent: *\nDisallow: /");

    }
}
