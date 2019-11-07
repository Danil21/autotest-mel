package mel.Tests.Site;

import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.SetDriver;
import mel.TestClasses.Registration;
import mel.TestClasses.TagSubscribe;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.sleep;

public class TagSubscribtionTest extends SetDriver {

    private Registration registration;
    private AdditionalMethods methods;
    private TagSubscribe tagSubscribe;
    private GetUrl getUrl;

//    @AfterClass
//    public void browserLogs() throws IOException {
//        methods = new AdditionalMethods();
//
//        ArrayList errors = new ArrayList();
//        errors.add("static");
//        methods.getBrowserLogs(errors, "TagSubscribtionTest");
//    }

    @Test
    public void tagSubscribe() {
        methods = new AdditionalMethods();
        tagSubscribe = new TagSubscribe();
        getUrl = new GetUrl();
        registration = new Registration();

        getUrl.driverGet();
        //methods.closeNotificationCookie();
        tagSubscribe.subscribeOnTagGuest();
        registration.userRegistrationWithLoginButton("FirstName", "LastName", methods.generateStr(), "12345678");

        tagSubscribe.clickOnSubscribeButtonOff();
        sleep(1000);
        Assert.assertEquals(methods.getTextFromSelector(tagSubscribe.tagSubscribeButtonOff), "Подписан");

        tagSubscribe.moveToMySubscribersPage();

        if (methods.getDisplayedElement(tagSubscribe.tagInPublication)) {
            Assert.assertEquals(methods.getTextFromSelector(tagSubscribe.tagInPublication), "новости");
            tagSubscribe.clickInTagAndUnsubscribe();
        } else {
            Assert.assertEquals(methods.getTextFromSelector(tagSubscribe.mySubscribersTagName), "Новости");
            tagSubscribe.unsubscribeFromTagUser();
        }
        methods.Wait(1500);
        Assert.assertEquals(methods.getTextFromSelector(tagSubscribe.tagSubscribeButtonOn), "Подписаться");
        sleep(500);

        tagSubscribe.moveToMySubscribersPage();
        Assert.assertEquals(methods.getTextFromSelector(tagSubscribe.mySubscribersTitle), "Мой Мел – ваша персональная лента материалов об образовании");
        String firstTagTextFeedPage = methods.getTextFromSelector(tagSubscribe.firstTagButton);
        tagSubscribe.checkTagUpdate();
        methods.Wait(700);
        String secondTagTextFeedPage = methods.getTextFromSelector(tagSubscribe.firstTagButton);
        tagSubscribe.isStringEquals(firstTagTextFeedPage, secondTagTextFeedPage);

        //проверяется подписка на тег из фида, заодно кнопка причины подписки в превью публикации
        String tagTextBeforeSubscribe = methods.getTextFromSelector(tagSubscribe.firstTagButton);
        tagSubscribe.checkSubscribeFromFeed();
        Assert.assertEquals(methods.getTextFromSelector(tagSubscribe.feedTagReasonButton), tagTextBeforeSubscribe);

        //проверка перехода на страницу тега из блока рекомендуемых тегов
        String firstTagTextRecommended = methods.getTextFromSelector(tagSubscribe.firstTagButtonRecommended);
        tagSubscribe.checkMoveToRecommendedTag();
        String textWithTagTitle = methods.getTextFromSelector(tagSubscribe.tagNameAtTagPage).toLowerCase();
        Assert.assertEquals(textWithTagTitle, firstTagTextRecommended);

    }
}
