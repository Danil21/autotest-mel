package mel.TestClasses;

import com.codeborne.selenide.Condition;
import mel.Helper.AdditionalMethods;
import mel.Helper.SetDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.*;

public class AddComment extends SetDriver {
    // есть повторяющиеся локаторы так как на странице публикаци другие локаторы
    private By article = By.cssSelector(".b-pb-cover__title");
    private By fieldForComment = By.xpath("//*[@class='g-textarea__textarea']");
    private By commentPublicButton = By.xpath("//*[contains(text(),'тправить')]");
    private By answerButton = By.xpath("//*[text()='Ответить']");
    private By deleteComment = By.xpath("//*[text()='Удалить']");
    private By fieldForAnswerComment = By.xpath("//textarea[@placeholder='Оставить комментарий']");
    private By enterAnswerCommentButton = By.xpath("//*[@class='reply-form__send-button' and text()='Отправить']");
    private By deleteAnswerCommentButton = By.xpath("//*[@class='simple-comment__action comment-action' and text()='Удалить']");
    private By complaintButton = By.xpath("//*[contains(text(),'Пожаловаться')]");
    public By simpleCommentGratitude = By.xpath("//*[text()='Спасибо']");
    public By textComment = By.xpath("//*[contains(@class,'comment-text')]");
    private By showMoreButton = By.xpath("//*[contains(@class,'full-text-opener')]");
    public By allCommentsOpener = By.xpath("//div[text()='Показать все комментарии']");
    public By commentClass = By.xpath("//*[contains(@class,'comment-text')]");
    private By textInComment = By.xpath("//div[contains(text(),'test-comment')]");
    private By textInAnswerComment = By.xpath("//div[contains(text(),'test-answer')]");
    public By commentHeader = By.xpath("//div[contains(@class,'comments-header')]");
    private By buttonToСomments = By.xpath("//*[text()='К комментариям']");

    public By textInEmail = By.xpath("//*[contains(text(),'Такой \"кудрявый\" текст читать просто невозможно')]");

    public void insertAndAddComment(String comment, String answerComment) {
        $(fieldForComment).click();
        $(fieldForComment).sendKeys(comment);
        $(commentPublicButton).click();
        sleep(1000);
        $(answerButton).click();
        $(fieldForAnswerComment).sendKeys(answerComment);
        $(enterAnswerCommentButton).click();
        $(commentHeader).shouldBe(Condition.text("Комментарии(2)")); //переделать
    }

    public void checkButtonTocomments() {
        $(buttonToСomments).click();
    }
    public void deleteComment() {
        $(deleteAnswerCommentButton).click();
        $(deleteComment).click();
    }

    public void clickInComplainButton() {
        $(complaintButton).scrollTo().click();
    }

    public void clickInShowMoreButton() {
        AdditionalMethods methods = new AdditionalMethods();
        $(showMoreButton).scrollTo();

        methods.closeSheareTop();
        $(showMoreButton).click();
    }

    public int countComments() {
        return $$(commentClass).size();
    }

    public void checkShowAllComments() {
        int commentsBeforeOpenAll = countComments();
        $(allCommentsOpener).scrollIntoView(false).shouldHave(Condition.text("Показать все комментарии")).click();
        int commentsAfterOpenAll = countComments();
        if (commentsAfterOpenAll <= commentsBeforeOpenAll) {
            Assert.fail("Не раскрылись комментарии после нажатия на кнопку показа всех комментариев");
        }
    }

    public boolean showComments() {
        try {
            return ($(textInComment).isDisplayed() && $(textInAnswerComment).isDisplayed());
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean commentHeader() {
        try {
            return ($(commentHeader).isDisplayed());
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
