package mel.Tests.Site;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import mel.AdminTestClasses.AdminBlogs;
import mel.AdminTestClasses.AdminLogin;
import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.MailAuthorisation;
import mel.Helper.SetDriver;
import mel.TestClasses.Blogs;
import mel.TestClasses.Login;
import mel.TestClasses.Logout;
import mel.TestClasses.Registration;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BloggerTest extends SetDriver {

    private Registration registration = new Registration();
    private AdditionalMethods methods;
    private Blogs blogger = new Blogs();
    private GetUrl getUrl;
    private AdminLogin adminLogin;
    private String bloggerUrl;
    private String bloggerEmail;

//    @AfterClass
//    public void browserLogs() throws IOException {
//        methods = new AdditionalMethods();
//
//        ArrayList errors = new ArrayList();
//        errors.add("image.mel.fm");
//        errors.add("aidata");
//        errors.add("ADRIVER");
//        errors.add("advombat");
//        errors.add("otm-r");
//        errors.add("adx");
//        errors.add("fqtag");
//        errors.add("facebook");
//        errors.add("adriver");
//        methods.getBrowserLogs(errors, "BloggerTest");
//    }

    @Test(priority = 1)
    public void sendMessageInBlog() {
        methods = new AdditionalMethods();
        getUrl = new GetUrl();
        registration = new Registration();
        adminLogin = new AdminLogin();
        AdminBlogs blogs = new AdminBlogs();
        Logout logout = new Logout();

        getUrl.driverGet();
        //methods.closeNotificationCookie();
        registration.userRegistrationWithLoginButton("FirstName" + methods.generateRandomCharSequence(4), "LastName",
                methods.generateStr(), "12345678");

        // create new message in blog
        blogger.pressInBlogButtonInUserDropdown();
        bloggerUrl = WebDriverRunner.currentFrameUrl();
        blogger.pressInBlogButton();
        sleep(500);
        Assert.assertTrue(WebDriverRunner.currentFrameUrl().contains("/post/editor"), "WriteInBlogButton is not working,");
        blogger.enterBlogText("title", "subtitle", "announcement", " text", "школа");
        methods.sendKeyboardEnter();//press keyboard Enter to add tag
        blogger.pressInOfComments();
        Assert.assertTrue(blogger.getDisplayedAddedTag());

        blogger.deleteAndAddedTag("школа");
        Assert.assertTrue(blogger.getDisplayedAddedTag());

        blogger.checkOfComents();
        blogger.pressInPublicButton();
        $(blogger.titleTextInBlogPage).shouldHave(Condition.text("title"));
        $(blogger.subtitleTextInBlogPage).shouldHave(Condition.text("subtitle"));
        $(blogger.textInBlogPage).shouldHave(Condition.text("text"));
        methods.scroll("2800");
        Assert.assertTrue(blogger.checkOfComents());
        blogger.checkImage();
        methods.Wait(3500);
        Assert.assertEquals(blogger.getImageClass(), "img");

        // edit message
        blogger.pressInEditBlogButtons();
        $(blogger.textInTitle).shouldHave(Condition.text("Редактирование записи в блоге"));

        blogger.clearBlogFields();
        blogger.enterBlogText("FirstMessage", "SecondMessage", "ThirdMessage", " Message", "вуз");
        methods.sendKeyboardEnter();//press keyboard Enter to add tag

        blogger.pressInSaveButton();

        $(blogger.titleTextInBlogPage).shouldHave(Condition.text("FirstMessage"));
        $(blogger.subtitleTextInBlogPage).shouldHave(Condition.text("SecondMessage"));
        $(blogger.textInBlogPage).shouldHave(Condition.text("textMessage"));
        String currentUrl = WebDriverRunner.currentFrameUrl();

        // check blog in admin`
        getUrl.driverGetAdminUrl();
        adminLogin.adminAuthorisation("test@example.com", "123qwe11");
        blogs.clickInBlogsButton();
        List<String> postTitles = blogger.getPostTitles();
        if (!postTitles.contains("FirstMessage")) {
            Assert.fail("post isn`t displayed in admin");
        }

        // delete message
        getWebDriver().get(currentUrl);
        blogger.pressInEditBlogButton();
        blogger.pressInDeleteButton();
        $(blogger.noPublicationText).shouldHave(Condition.text("У вас пока нет постов"));
        // check button "New text"
        blogger.pressInWriteTextInProfileButton();
        $(blogger.textInTitle).shouldHave(Condition.text("Новая запись в блог"));

        getUrl.driverGet();
        blogger.pressInWriteTextInDropdown();
        $(blogger.textInTitle).shouldHave(Condition.text("Новая запись в блог"));

        getUrl.driverGetCurrentUrl("page/posts/1");
        blogger.pressInWriteTextInPagePosts();
        $(blogger.textInTitle).shouldHave(Condition.text("Новая запись в блог"));

        // create message in blog to check ban later
        blogger.enterBlogText("BanPost", "SecondMessage", "ThirdMessage", " This blogger will be banned soon", "образование");
        methods.sendKeyboardEnter();//press keyboard Enter to add tag
        blogger.pressInPublicButton();
        getUrl.driverGetCurrentUrl("profile");
        bloggerEmail = blogger.getEmailValueInProfile();

        logout.exitFromAccount();
        blogger.pressInBlogButton();
        $(blogger.authorisationText).shouldHave(Condition.text("Авторизация"));
    }

    @Test(priority = 2)
    public void banBlogger() {
        Login login = new Login();
        MailAuthorisation authorisation = new MailAuthorisation();
        getUrl = new GetUrl();

        getUrl.driverGetAdminUrl();
        sleep(1000);
        blogger.banBlogger(bloggerUrl);
        sleep(2000);
        login.authorisation(bloggerEmail, "12345678");
        sleep(500);
        Assert.assertTrue($(blogger.banErrorLoginMessage).isDisplayed(),
                "Error message after banned user attempted to login is not displaying");
        authorisation.recoveryPasswordForUser(bloggerEmail);
        sleep(1000);
        Assert.assertTrue($(blogger.recoveryErrorMessage).isDisplayed(),
                "Recovery error message for banned email is not displaying");
        getWebDriver().get(bloggerUrl);
        Assert.assertEquals(title(), "Страница не найдена | Мел");
    }

    @Test(priority = 3)
    public void checkBlogger() {
        getUrl = new GetUrl();
        methods = new AdditionalMethods();

        getUrl.driverGet();
        blogger.checkDownloadCoverImage();
        blogger.checkDraftMessage();
        blogger.checkEditingPrivileges();
    }

    @Test(priority = 4)
    public void editMassage() {
        getUrl = new GetUrl();
        methods = new AdditionalMethods();

        getUrl.driverGet();
        getUrl.driverGetCurrentUrl("post/2269/editor");
        blogger.ckeckMessage();
        Assert.assertEquals(title(),"Синдром идеального родителя, или почему в прекрасных семьях вырастают несчастные дети | Мел");
    }

}
