package mel.TestClasses;

import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.SetDriver;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.title;
import static com.codeborne.selenide.WebDriverRunner.url;

public class AllBlogsPage extends SetDriver {

    private By writeTextButton = By.xpath("//*[contains(@class, 'right-buttons__create-post-button')]");
    private By secondPageButton = By.xpath("//*[@class='l-all-posts__pagination']/div/a[1]");
    private By lastPageButton = By.xpath("//*[@class='l-all-posts__pagination']/div/a[last()-1]");
    private By firstBlog = By.xpath("//*[@class='l-all-posts__main-column']/div[1]//div[@class='b-all-posts-page-item__title']");
    private By authorisationTitle = By.cssSelector(".g-auth-social__header");

    private String blogsUrl = "page/posts/1";
    private GetUrl getUrl = new GetUrl();
    private AdditionalMethods methods;

    public void checkCreatePost() {
        Login login = new Login();
        AdditionalMethods methods = new AdditionalMethods();

        getUrl.driverGetCurrentUrl(blogsUrl);
        $(writeTextButton).click();
        Assert.assertEquals(methods.getTextFromSelector(authorisationTitle), "Авторизация");

        getUrl.driverGet();
        login.authorisation("test153153153@mail.ru", "12345678");
        getUrl.driverGetCurrentUrl(blogsUrl);
        $(writeTextButton).click();
        Assert.assertTrue(title().contains("Новая запись в блог"));
    }

    public void checkPagination() {
        methods = new AdditionalMethods();

        getUrl.driverGetCurrentUrl(blogsUrl);
        methods.scroll("5500");
        $(secondPageButton).click();
        Assert.assertTrue(url().contains("2"));

        methods.scroll("5500");
        String numberLastPage = $(lastPageButton).getAttribute("href");
        $(lastPageButton).click();
        Assert.assertTrue(url().contains(numberLastPage));
    }

    public void checkFirstBlog() {
        methods = new AdditionalMethods();
        Blogs blogs = new Blogs();

        getUrl.driverGetCurrentUrl(blogsUrl);
        String blogTitle = $(firstBlog).text();
        $(firstBlog).scrollIntoView(true).click();
        Assert.assertEquals(methods.getTextFromSelector(blogs.titleTextInBlogPage), blogTitle);
    }
}
