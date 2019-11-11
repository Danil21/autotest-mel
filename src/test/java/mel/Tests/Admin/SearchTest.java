package mel.Tests.Admin;

import MelAppium.resources.config;
import mel.AdminTestClasses.AdminBlogs;
import mel.AdminTestClasses.AdminLogin;
import mel.AdminTestClasses.AdminSearch;
import mel.Helper.GetUrl;
import mel.Helper.SetDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.sleep;

public class SearchTest extends SetDriver {
    private AdminSearch search = new AdminSearch();
    private GetUrl getUrl = new GetUrl();
    private AdminLogin adminLogin = new AdminLogin();
    private AdminBlogs blogs = new AdminBlogs();

    @Test
    public void checkSearch() {
        getUrl.driverGetAdminUrl();
        adminLogin.adminAuthorisation(config.getTestProperty("adminLogin"),config.getTestProperty("adminPass"));

        search.checkSearchPublication("publication");

        getUrl.driverGetAdminUrl();
        String incorrectText = "incorrectTestText";
        search.insertText(incorrectText);
        sleep(3000);
        Assert.assertEquals(search.getTextInResultText(), "По запросу «" + incorrectText + "» совпадений не найдено");

        getUrl.driverGetAdminUrl();
        blogs.clickInBlogsButton();
        search.checkSearchPublication("blog");

        //проверка что красная кнопка в поиске работает
        search.clickAndCheckClearInputSearsh();
        search.insertText(incorrectText);
        sleep(1000);
        Assert.assertEquals(search.getTextInBlogResultText(), "По запросу «" + incorrectText + "» совпадений не найдено");
    }
}
