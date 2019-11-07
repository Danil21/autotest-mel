package mel.Tests.Admin;

import mel.AdminTestClasses.AdminBlogs;
import mel.AdminTestClasses.AdminFrontPage;
import mel.AdminTestClasses.AdminLogin;
import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.SetDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BlogsTest extends SetDriver {

    private AdditionalMethods methods;
    private AdminLogin adminLogin;
    private GetUrl getUrl;
    private AdminFrontPage frontPage;
    private AdminBlogs blogs;


    @AfterClass
    public void browserLogs() throws IOException {
        methods = new AdditionalMethods();

        ArrayList errors = new ArrayList();
        errors.add("yandex");
        errors.add("yadro");
        errors.add("tracker.rareru");
        errors.add("relap.io");
        errors.add("aidata");
        methods.getBrowserLogs(errors, "BlogsTest");
    }

    @Test
    public void checkBlogs() {
        methods = new AdditionalMethods();
        getUrl = new GetUrl();
        adminLogin = new AdminLogin();
        frontPage = new AdminFrontPage();
        blogs = new AdminBlogs();

        getUrl.driverGetAdminUrl();
        adminLogin.adminAuthorisation("test@example.com", "123qwe11");
        blogs.clickInBlogsButton();

        String parentWindowId = getWebDriver().getWindowHandle();
        final Set<String> oldWindowsSet = getWebDriver().getWindowHandles();
        // запись в строку заголовка блога в админке
        String blogInAdmin = methods.getTextFromSelector(blogs.blogTitleInAdmin);
        String blogId = blogs.getBlogId();

        // нажатие на кнопку открытия на сайте публикации
        blogs.clickInOpenAtSiteButton();
        methods.moveFocusToTheNewWindow(oldWindowsSet);

        // запись в строку заголовка блога на сайте
        String blogInSite = methods.getTextInsideElement(blogs.blogTitleInSite);
        // сравнение заголовков
        blogInSite = blogInSite.replace("—", "–")
                .replace("«", "")
                .replace("»", "");                 //executes only if post has "-" or "«,", »" in title
        blogInAdmin = blogInAdmin.replace("\"", "");

        blogs.comprasionTitleBlogs(blogInAdmin, blogInSite);

        // переход на окно в адмикне
        getWebDriver().switchTo().window(parentWindowId);
        sleep(1000);

        // нажатие на выпадающее меню в зависимости добавлена ли запись на первую полосу
        if (!blogs.checkFlagAddToFrontPage()) {
            blogs.clickInDropDownMenu();
        } else {
            blogs.clickInDropDownMenu();
            blogs.clickInPostFutureButton();
            blogs.clickInDropDownMenu();
        }

        sleep(1000);
        // нажатие на кнопку блокировки публикации
        blogs.clickInPostBlockingButton();
        sleep(1000);
        // проверка отображения значка блорировки
        org.testng.Assert.assertTrue($(blogs.iconImgHiddenBlog).isDisplayed());
        blogs.clickInDropDownMenu();
        // разблокировка публикации
        $(blogs.postBlockingButton).click();
        sleep(1000);
        blogs.clickInDropDownMenu();
        sleep(1000);
        // нажатие на кнокпку добавление на первую полосу
        blogs.clickInPostFutureButton();
        sleep(2000);
        // проверка отображения флага
        org.testng.Assert.assertTrue($(blogs.flagAddToFrontPage).isDisplayed());
        // переход на первую полосу
        getUrl.driverGetCurrentAdminUrl("frontpage");
        sleep(5000);

        for (int i = 1; i < 15; i++) {
            By blogInFirstPage = By.cssSelector(".b-order-manager__switcher-list-container > div > div:nth-child(" + i + ")");
            String compareId = $(blogInFirstPage).getAttribute("data-params").substring(6, 10);

            if (compareId.equals(blogId)) {
                By firstPageONButton = By.cssSelector(".b-order-manager__switcher-list-container > div > div:nth-child(" + i + ") > div > div.b-switcher__icon > div");
                String actualAttribute = $(firstPageONButton).getAttribute("data-params");
                String expectedAttribute = "{\"type\":\"off\"}";

                // нажатие на кнопку добавления блога на первую полосу
                if (actualAttribute.equals(expectedAttribute) == true) {
                    By switcher = By.cssSelector(".b-order-manager__switcher-list-container > div > div:nth-child(" + i + ") > div > div.b-switcher__icon > div");
                    $(switcher).click();
                    sleep(2000);
                    break;
                }
                break;
            }
            break;
        }

        for (int i = 1; i < 15; i++) {
            By blogInFirstPage = By.cssSelector(".b-order-manager__switcher-list-container > div > div:nth-child(" + i + ")");
            String compareId = $(blogInFirstPage).getAttribute("data-params").substring(6, 10);
            if (compareId.equals(blogId)) {
                WebElement switcher = $(By.cssSelector(".b-order-manager__switcher-list-container > div > div:nth-child(" + i + ") > div > div.b-switcher__drag-handle"));
                int yOffset;
                if (i == 1) {
                    yOffset = 0;
                } else {
                    yOffset = i * 65;
                }

                new Actions(getWebDriver())
                        .dragAndDropBy(switcher, 0, -yOffset) // смещение в пикселях
                        .build()
                        .perform();
                break;
            }
        }

        sleep(1000);
        frontPage.clickInFrontPageSaveButton();
        // проверка на соответствия данных созданного блога и блога, отображающегося на сайте
        sleep(5000);
        getUrl.driverGet();

        if (methods.getDisplayedElement(blogs.mainPagePublicationTitle)) {
            Assert.assertEquals(methods.getTextFromSelector(blogs.mainPagePublicationTitle)
                    .replace("«", "")
                    .replace("»", ""), blogInSite);
        } else if (methods.getDisplayedElement(blogs.mainPageBlogTitle)) {
            Assert.assertEquals(methods.getTextFromSelector(blogs.mainPageBlogTitle)
                    .replace("«", "")
                    .replace("»", ""), blogInSite);
        }

        if (methods.getDisplayedElement(blogs.mainPagePublicationCoverTag)) {
            Assert.assertEquals(methods.getTextFromSelector(blogs.mainPagePublicationCoverTag), "БЛОГИ");
        } else if (methods.getDisplayedElement(blogs.mainPageBlogCoverTag)) {
            Assert.assertEquals(methods.getTextFromSelector(blogs.mainPageBlogCoverTag), "БЛОГИ");
        }
    }
}
