package mel.TestClasses;

import mel.Helper.SetDriver;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;


public class TagSubscribe extends SetDriver {

    //Кнопка подписки на тег на странице тега
    public By tagSubscribeButtonOff = By.cssSelector(".l-tag__tag-subscriber");
    //Кнопка перехода на страницу новости с главной страницы
    private By newsButton = By.cssSelector(".b-pb-article-fresh__article-all-news > a");
    //Кнопка закрытия окна авторизации
    private By socialModalCloseButton = By.cssSelector(".g-modal__close-icon");
    //Кнопка отписки от тега на странице тега
    public By tagSubscribeButtonOn = By.cssSelector(".l-tag__tag-subscriber");
    //Кнопка перехода на страницу подписок пользователя
    private By mySubscribers = By.xpath("//a[@href='/feed']");
    //Текст с названием тега на странице подписок пользователя
    public By mySubscribersTagName = By.cssSelector(".b-article-preview__source-title > a");
    //Заголовок на странице подписок
    public By mySubscribersTitle = By.cssSelector(".b-welcome__welcome-caption");
    //Кнопка обновления списка тегов на странице подписок
    private By moreTags = By.cssSelector(".b-suggestion-feed__tags-refresh-button");
    //нопка обновления списка авторов на стронице подписок
    private By moreTagsAuthors = By.cssSelector(".b-suggestion-feed__authors-refresh-button > div");
    //Первая кнопка в списке тегов на странице подписок
    public By firstTagButton = By.cssSelector(".b-suggestion-feed__tag-manager-frame > div > div > div:nth-child(1)"); // работает для пользователей без подписок
    //
    public By tagInPublication = By.xpath("//*[@class='l-feed__article-feed-frame']//div[1]//span[@class='g-button__label']");
    //Первая кнопка в блоке рекомендуемых тегов
    public By firstTagButtonRecommended = By.xpath("//*[@class='b-tag-cloud__tag-list']/a[1]");
    //Кнопка "Продолжить" на странице /feed
    private By confirmButtonAtFeedPage = By.xpath("//*[@class='g-button__label' and text()='продолжить']");
    //кнопка причины подписки на странице /feed
    public By feedTagReasonButton = By.xpath("//*[@class='b-article-preview__reason-tag']");
    //текст с названием тега на странице тега
    public By tagNameAtTagPage = By.xpath("//*[@class='l-tag__tag-name']");

    public void clickOnSubscribeButtonOff() {
        $(tagSubscribeButtonOff).click();
    }

    //function to compare text between two tag buttons
    public void isStringEquals(String firstString, String lastString) {
        if (firstString.equals(lastString)) {
            Assert.fail("text between two tag buttons is equal");
        }
    }

    public void subscribeOnTagGuest() {
        $(newsButton).scrollIntoView(true).click();
        clickOnSubscribeButtonOff();
        $(socialModalCloseButton).click(); //  проверка на то что можно подписаться не авторизовываясь
    }

    public void moveToMySubscribersPage() {
        $(By.xpath("//*[@class='user-info']")).click(); //для перехода в подписки пользователя
        $(mySubscribers).click();
    }

    public void unsubscribeFromTagUser() {
        $(mySubscribersTagName).click();
        $(tagSubscribeButtonOn).click();
    }

    public void clickInTagAndUnsubscribe() {
        $(tagInPublication).click();
        $(tagSubscribeButtonOn).click();
    }

    // используется только в подписках на автора/блогера
    public void checkUnsubscribe() {
        $(mySubscribers).click();
        $(By.xpath("//*[contains(text(),'СТАТЬИ')]")).click();
    }


    public void checkTagUpdate() {
        $(moreTags).click();
        // $(moreTagsAuthors).click();
    }

    public void checkSubscribeFromFeed() {
        $(firstTagButton).click();
        $(confirmButtonAtFeedPage).scrollTo().click();
    }

    public void checkMoveToRecommendedTag() {
        $(firstTagButtonRecommended).click();
    }
}