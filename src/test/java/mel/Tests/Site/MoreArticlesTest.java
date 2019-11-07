package mel.Tests.Site;

import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.SetDriver;
import mel.TestClasses.DownloadArticles;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class MoreArticlesTest extends SetDriver {

    private AdditionalMethods methods;
    private DownloadArticles article;
    private GetUrl getUrl;

    @AfterClass
    public void browserLogs() throws IOException {
        methods = new AdditionalMethods();

        ArrayList errors = new ArrayList();
        errors.add("yandex");
        errors.add("tracker.rareru");
        errors.add("aidata.io");
        errors.add("counter.yadro.ru");
        errors.add("rubiconproject");
        methods.getBrowserLogs(errors, "MoreArticlesTest");
    }

    @Test
    public void moreArticleMane() {
        article = new DownloadArticles();
        methods = new AdditionalMethods();
        getUrl = new GetUrl();

        getUrl.driverGet();
        //methods.closeNotificationCookie();

        ArrayList articles = new ArrayList();
        articles.add(article.getRandomArticle());
        articles.add(article.getRandomArticle());
        articles.add(article.getRandomArticle());
        articles.add(article.getRandomArticle());

        for (int i = 0; i < articles.size(); i++) {
            article.pressInArticleMore();
            article.pressInArticle((By) articles.get(i));
            String title = getWebDriver().getTitle();
            if (title.contains("| Мел")) {
                Assert.assertTrue($(article.articles).isDisplayed());
                getWebDriver().navigate().back();
            } else {
                getWebDriver().navigate().back();
            }
        }

        getUrl.driverGetCurrentUrl("/rubric/school");
        article.checkButtonInPageRubric();
        getUrl.driverGetCurrentUrl("2016/04/26/9_phrase");
        article.checkButtonInPagePublication();
        getUrl.driverGetCurrentUrl("blog/darya-nizamova/19067-kogda-v-moyey-zhizni-poyavilsya-vtoroy-rebenok-ya-priznalas-sebe-chto-ne-spravlyayus");
        article.checkButtonInPagePublication();
    }
}
