package mel.Tests.Site;

import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.SetDriver;
import mel.TestClasses.AuthorBloggerSubscribe;
import mel.TestClasses.Registration;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.element;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getSelenideDriver;

public class AuthorBloggerSubscribeTest extends SetDriver {


//    @AfterClass
//    public void browserLogs() throws IOException {
//        AdditionalMethods methods = new AdditionalMethods();
//
//        ArrayList errors = new ArrayList();
//        methods.getBrowserLogs(errors, "AuthorBloggerSubscribeTest");
//    }

    @Test
    public void authorSubscribe() {
        AdditionalMethods methods = new AdditionalMethods();
        AuthorBloggerSubscribe authorSubscribe = new AuthorBloggerSubscribe();
        GetUrl getUrl = new GetUrl();
        Registration registration = new Registration();

        getUrl.driverGet();
        authorSubscribe.singIn();
        authorSubscribe.registrationAfterGuestSubscribe();
        registration.userRegistrationWithoutLoginButton("FirstName", "LastName", methods.generateStr(), "12345678");

        //methods.closeNotificationCookie();
        authorSubscribe.moveToFeedPage();

        String authorNameInWidget = authorSubscribe.getTextFromAuthorWidget();
        authorSubscribe.authorSubscribe();
        Assert.assertEquals(methods.getTextFromSelector(authorSubscribe.mySubscribersAuthorName), authorNameInWidget);

        sleep(800);
        authorSubscribe.clickOnAuthorPreview();
        Assert.assertEquals(methods.getTextFromSelector(authorSubscribe.authorSubscribeButtonChecked), "Подписан");

        authorSubscribe.moveToFeedPage();
        authorSubscribe.unsubscribeAuthor();
        sleep(500);
        Assert.assertEquals(methods.getTextFromSelector(authorSubscribe.authorSubscribeButtonChecked), "Подписаться");

        authorSubscribe.moveToFeedPage();
        Assert.assertEquals(methods.getTextFromSelector(authorSubscribe.mySubscribersTitle), "Мой Мел – ваша персональная лента материалов об образовании");
    }

    @Test
    public void bloggerSubscribe() {
        AdditionalMethods methods = new AdditionalMethods();
        AuthorBloggerSubscribe bloggerSubscribe = new AuthorBloggerSubscribe();
        GetUrl getUrl = new GetUrl();
        Registration registration = new Registration();

        getUrl.driverGet();
        getUrl.driverGetCurrentUrl("blog/anastasiya-mironova");
        getSelenideDriver().clearCookies();//delete cookies to check subscription with unauthorised user

        bloggerSubscribe.subscribeToBloggerAtBlog();
        sleep(1000);
        bloggerSubscribe.registrationAfterGuestSubscribe();
        registration.userRegistrationWithoutLoginButton("FirstName", "LastName", methods.generateStr(), "12345678");

        Assert.assertTrue(bloggerSubscribe.checkPublicationInfeed(),
                "Subscribe button didn't change its state after click.");

        sleep(1500);

        getUrl.driverGetCurrentUrl("feed");
        //Main section in feed is present only if user subscribed to anyone, so this asserts valid subscription
        Assert.assertTrue(bloggerSubscribe.checkMainSectionPresenceInFeed(),
                "Subscription wasn't successfully completed. There's no updates in feed.");

        bloggerSubscribe.clickOnArticleTitleInFeed();
        bloggerSubscribe.clickOnUpperSubscribeButton();
        bloggerSubscribe.clickOnBottomSubscribeButton();
        sleep(500);
        Assert.assertTrue(element(bloggerSubscribe.bloggerSubscribeButtonChecked).isDisplayed(),
                "Subscribe button didn't change its state after click.");

        bloggerSubscribe.clickOnBottomSubscribeButton();
        //Go to feed to check unsubscription
        getUrl.driverGetCurrentUrl("feed");
        //Welcome Caption in feed is present only if user has no subscriptions, so this asserts valid unsubscription
        Assert.assertTrue(bloggerSubscribe.checkWelcomeCaptionPresenceInFeed(),
                "Unsubscription wasn't successfully completed. There's no updates in feed.");
    }
}
