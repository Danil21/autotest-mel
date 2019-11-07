package mel.Tests.Site;

import com.codeborne.selenide.Condition;
import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.MailAuthorisation;
import mel.Helper.SetDriver;
import mel.TestClasses.AddComment;
import mel.TestClasses.Login;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;


public class CommentTest extends SetDriver {
    private Login autoLogin = new Login();
    private AdditionalMethods methods = new AdditionalMethods();
    private AddComment comment = new AddComment();
    private GetUrl getUrl = new GetUrl();
    private MailAuthorisation mailAuthorisation = new MailAuthorisation();

    @AfterClass
    public void browserLogs() throws IOException {
        methods = new AdditionalMethods();

        ArrayList errors = new ArrayList();
        errors.add("revsci.net");
        errors.add("yandex");
        errors.add("tracker.rareru");
        errors.add("mail.ru");
        errors.add("aidata.io");
        errors.add("adriver");
        errors.add("rx.io");
        errors.add("adx");
        methods.getBrowserLogs(errors, "CommentTest");
    }

    @Test
    public void addAndDeleteComment() {
        getUrl.driverGet();

        getUrl.driverGetCurrentUrl("novosti/5408613-siryskaya-bezhenka-sdala-yege-po-russkomu-na-91-ball");
        autoLogin.authorisationInPageReact("test153153153@mail.ru", "12345678");

        methods.closeNotificationCookie();
        // add and delete comment
        methods.scroll("1500");
        comment.checkButtonTocomments();

        comment.insertAndAddComment("test-comment", "test-answer");
        sleep(1000);
        Assert.assertTrue(comment.showComments());
        comment.deleteComment();
        // check "no comment text"
        Assert.assertTrue(comment.commentHeader());

        // check "show all comments" button
        getUrl.driverGetCurrentUrl("uchitelya/2471396-uchitel-v-sotsialnykh-setyakh");//mneniye/4908165-military_uniform
        comment.checkShowAllComments();

        // check "show more" button in comment
        getUrl.driverGetCurrentUrl("chto-pochitat/9462308-jerusalem");
        Assert.assertEquals(methods.getTextFromSelector(comment.textComment), "Я не смогла дочит" +
                "ать дальше середины. Такой \"кудрявый\" текст читать просто невозможно: в одном предложении столько" +
                " информации упаковано, что поневоле останавливаешься, чтобы его обдумать, прежде чем читать дальше. " +
                "Сюжет становится при этом уже неинтересен. На какую аудиторию рассчитана эта книга? Она понравится " +
                "людям в...\nПоказать полностью");

        comment.clickInShowMoreButton();
        Assert.assertEquals(methods.getTextFromSelector(comment.textComment), "Я не смогла" +
                " дочитать дальше середины. Такой \"кудрявый\" текст читать просто невозможно: в одном предложении" +
                " столько информации упаковано, что поневоле останавливаешься, чтобы его обдумать, прежде чем читать" +
                " дальше. Сюжет становится при этом уже неинтересен. На какую аудиторию рассчитана эта книга? Она" +
                " понравится людям вроде Анатолия Вассермана, обладающим академическими знаниями и привыкшими изучать" +
                " энциклопедии. Интересуюсь историей Иерусалима, обожаю читать и имею высшее гуманитарное образование," +
                " но эту книгу я не куплю.");

        // check complain button
        comment.clickInComplainButton();
        $(comment.simpleCommentGratitude).shouldHave(Condition.text("Спасибо"));

        // check message in mail.ru
        open("https://mail.google.com");
        mailAuthorisation.emailAuthorisation("test153153153@gmail.com", "knock705b");
        Assert.assertEquals(methods.getTextFromSelector(mailAuthorisation.textInComplaintReviewLetter), "Пользователь mail test" +
                        " пожаловался на комментарий пользователя Ирина Зорина в публикации Как капитан Монти Паркер искал главную святыню иудеев и обхитрил всех.\n");

                Assert.assertEquals(methods.getTextFromSelector(mailAuthorisation.textMessageReviewLetter),
                        "Текст комментария: \"Я не смогла дочитать дальше середины. Такой \"кудрявый\" текст читать просто невозможно: в одном предложении столько" +
                        " информации упаковано, что поневоле останавливаешься, чтобы его обдумать, прежде чем читать дальше. Сюжет становится при этом " +
                        "уже неинтересен. На какую аудиторию рассчитана эта книга? Она понравится людям вроде Анатолия Вассермана, обладающим академическими " +
                        "знаниями и привыкшими изучать энциклопедии. Интересуюсь историей Иерусалима, обожаю читать и имею высшее гуманитарное образование, " +
                        "но эту книгу я не куплю.\".");
    }
}
