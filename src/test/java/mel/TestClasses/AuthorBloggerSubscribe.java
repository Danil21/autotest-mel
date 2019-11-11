package mel.TestClasses;

import com.codeborne.selenide.Condition;
import mel.Helper.AdditionalMethods;
import mel.Helper.SetDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;


public class AuthorBloggerSubscribe extends SetDriver {

    private By login = By.xpath("//*[contains(text(),'вход')]");
    // переход в свои подписки
    private By subscriptions = By.cssSelector(".right-buttons__user-info-button > div");
    private By mySubscribersButton = By.xpath("//*[contains(text(),'Подписки')]");

//    //кнопка автора Инна Прибора на странице своих подписок, ведет на страницу автора
//    private By innaPriboraButton = By.cssSelector(".b-suggestion-feed__author-list > div:nth-child(1) > div > div > div");

    //неактивная кнопка подписки на автора на странице автора
    public By authorSubscribeButton = By.cssSelector(".b-pb-author__subscribe-control > div");
    //активная кнопка подписки на автора на странице автора
    public By authorSubscribeButtonChecked = By.cssSelector(".b-pb-author__subscribe-control > div");
    //имя автора на странице своих подписок, располагается слева от статей
    public By mySubscribersAuthorName = By.cssSelector(".b-article-preview__source-title > a");
    //кнопка входа через email в pop-up'е авторизации
    private By emailAuthButton = By.cssSelector(".g-auth-social__email-button");
    //таб с переключением на вкладку регистрации
    private By registrationWindow = By.cssSelector(".g-tab__tabs > div:nth-child(2)");
    //заголовок на странице своих подписок, в случае их отсутствия
    public By mySubscribersTitle = By.cssSelector(".b-welcome__welcome-caption");
    //Подписаться на кнопку блоггера в блоге блоггера
    private By bloggerSubscribeButtonAtBlog = By.xpath("//div[contains(@class,'subscribe-control')and not(contains(@class,'mobile'))]/descendant::div[contains(@class,'checkbox__icon')]");
    //элемент для проверки состояния кнопки подписки после подписки
    public By bloggerSubscribeButtonChecked = By.xpath("//div[@class='b-pb-author__subscribe-control']//div[@class='b-checkbox__checked-label']");
    // Элементы Public By (основные разделы в ленте) для проверки успешности подписки
    public By articlesMainSectionInFeed = By.xpath("//div[contains(@class,'main-section')]");
    //Название статьи в feed
    private By articleTitleInFeed = By.xpath("//div[contains(@class,'preview__title')]");
    //Subscribe to blogger button in post under blogger's pic
    private By upperBloggerSubscribeButtonInPost = By.xpath("//div[contains(@class,'about-article')]/descendant::div[contains(@class,'checked-icon')]");
    //Subscribe to blogger button at the bottom of the post
    private By bottomBloggerSubscribeButtonInPost = By.xpath("//div[contains(@class,'author-bottom')]/descendant::div[contains(@class,'checkbox__icon')]");
    private By bottomBloggerSubscribeButtonChecked = By.xpath("//div[contains(@class,'author-bottom')]/descendant::div[contains(@class,'checkbox__checked-icon')]");
    //Приветственная подпись в ленте для пользователей без подписок
    public By welcomeCaptionInFeed = By.xpath("//div[contains(@class,'welcome-caption')]");
    private By relapList = By.xpath("//div[contains(@class,'relap')]");
    private By authorPreview = By.xpath("//*[@class='l-feed__article-feed-frame']/div[1]/div//*[contains(@class, 'b-article-preview__author-photo-wrapper')]");
    private By authorNameInWidget = By.xpath("//*[@class='b-pb-author__name']");
    //кнопка продолжить
    private By bottomNext = By.xpath("//*[contains(text(),'продолжить')]");

    public void singIn() {
        $(login).click();
    }

    public void moveToFeedPage() {
        sleep(500);
        $(subscriptions).shouldBe(Condition.visible).click();
        $(mySubscribersButton).click();
    }

    public void authorSubscribe() {
        $(authorSubscribeButton).click();
        sleep(1000);
        $(bottomNext).shouldBe(Condition.visible).click();
    }

    public void registrationAfterGuestSubscribe() {
        $(emailAuthButton).click();
        $(registrationWindow).click();
    }

    public void unsubscribeAuthor() {
        AdditionalMethods methods = new AdditionalMethods();
        $(mySubscribersAuthorName).click();
        methods.Wait(800);
        $(authorSubscribeButtonChecked).click();

    }

    public void subscribeToBloggerAtBlog() {
        $(bloggerSubscribeButtonAtBlog).click();
    }

    public void clickOnArticleTitleInFeed() {
        $(articleTitleInFeed).click();
    }

    public void clickOnUpperSubscribeButton() {
        $(upperBloggerSubscribeButtonInPost).scrollIntoView(false).click();
    }

    public void clickOnBottomSubscribeButton() {
        if ($(bottomBloggerSubscribeButtonInPost).isDisplayed()) {
            $(relapList).scrollIntoView(true);
            $(bottomBloggerSubscribeButtonInPost).click();
        } else $(bottomBloggerSubscribeButtonChecked).click();
    }

    public boolean checkPublicationInfeed() {
        boolean flag = true;
        try {
            $(bloggerSubscribeButtonChecked);
        } catch (NoSuchElementException e) {
            flag = false;
        }
        return flag;
    }


    public boolean checkMainSectionPresenceInFeed() {
        boolean flag = true;
        try {
            $(articlesMainSectionInFeed);
        } catch (NoSuchElementException e) {
            flag = false;
        }
        return flag;
    }

    public boolean checkWelcomeCaptionPresenceInFeed() {
        boolean flag = true;
        try {
            $(welcomeCaptionInFeed);
        } catch (NoSuchElementException e) {
            flag = false;
        }
        return flag;
    }

    public void clickOnAuthorPreview() {
        $(authorPreview).shouldBe(Condition.visible).click();
    }

    public String getTextFromAuthorWidget() {
        return $(authorNameInWidget).getText();
    }

    public static class AdminTags extends SetDriver {


        public By tagsTab = By.xpath("//a[contains(@class,'g-list__item') and text()='Теги']");
        private By addNewTag = By.cssSelector(".i-layout__content > div.i-layout__subheader > div");
        private By searchInput = By.xpath("//*[@class='g-input__input' and @placeholder='Поиск']");
        public By searchResultTag = By.xpath("//*[@class='i-layout__search-results']/descendant::div[@class='b-table-row__name']");
        private By searchClearButton = By.xpath("//*[contains(@class,'g-icon_img_search-cancel') and @data-params='{\"type\":\"search-cancel\"}']");
        private By titleTagInput = By.cssSelector(".b-tag-dialog__name-input > div > input");
        private By urlInput = By.cssSelector(".b-tag-dialog__address-input > div > input");
        private By descriptionInput = By.cssSelector(".b-tag-dialog__description-input > div > textarea");
        private By seoTitleTag = By.cssSelector(".b-tag-dialog__title-seo-input > div > input");
        private By seoDescriptionTag = By.cssSelector(".b-tag-dialog__description-seo-input > div > textarea");
        private By saveButtonInPopup = By.cssSelector(".b-tag-dialog__save-button > div");
        //сортировка
        private By sortByNameButton = By.cssSelector(".b-table__sort-by-name-button > div");
        private By sortByPublicationsButton = By.cssSelector(".b-table__sort-by-publications-button > div");
        private By sortByPostsButton = By.cssSelector(".b-table__sort-by-posts-button > div");
        private By sortBySubscriptionsButton = By.cssSelector(".b-table__sort-by-subscriptions-button > div");

        public By firstTagInList = By.cssSelector(".b-table__items > div:nth-child(1) > div:nth-child(1)");
        public By secondTagInList = By.cssSelector(".b-table__items > div:nth-child(2) > div:nth-child(1)");

        public By firstTagPublicationsCount = By.cssSelector(".b-table__items > div:nth-child(1) > div:nth-child(2)");
        public By secondTagPublicationsCount = By.cssSelector(".b-table__items > div:nth-child(2) > div:nth-child(2)");

        public By firstTagPostsCount = By.cssSelector(".b-table__items > div:nth-child(1) > div:nth-child(3)");
        public By secondTagPostsCount = By.cssSelector(".b-table__items > div:nth-child(2) > div:nth-child(3)");

        public By firstTagSubscriptionsCount = By.cssSelector(".b-table__items > div:nth-child(1) > div:nth-child(4)");
        public By secondTagSubscriptionsCount = By.cssSelector(".b-table__items > div:nth-child(2) > div:nth-child(4)");

        private By openingTagOnSiteButton = By.cssSelector("#\\34 428 > div.b-table-row__controls > a > div");
        private By tagUrlInAdmin = By.xpath("//a[contains(@href,'mel.fm/novosti')]");
        //три точки, вызывающие дропдаун с кнопкой "Редактировать"
        private By editTagDropdownButton = By.cssSelector("#\\34 428 > .b-table-row__controls > div > div > .g-dropdown__opener > div");
        //кнопка "Редатировать" в дропдауне
        private By editTagButton = By.cssSelector("#\\34 428 > .b-table-row__controls > div > div > .g-dropdown__content > div");
        private By closePopupEditTagButton = By.cssSelector(".g-modal__close-icon > div");
        //сео на сайте
        private By seoTitleTagOnSite = By.xpath("//meta[name='title']");
        private By seoDescriptionTagOnSite = By.cssSelector("//meta[name='description']");
        public By  descriptionTagOnSite = By.cssSelector(".i-layout__over-content-flow > div > div:nth-child(5)");
        //подписка на тег на сайте после регистрации
        private By subscribeOnTagButton = By.xpath("//div[@class='b-checkbox__label' and text()='Подписаться']");
        //добавление тега к посту
        private By titleTagInputInPost = By.cssSelector(".b-pb-suggest__emitter > div > input");
        private By savePostButton = By.xpath("//*[contains(text(),'Сохранить')]");
        private By deleteTagInPostButton = By.cssSelector(".b-tag__remover");
        //добавление тега к публикации
        private By addingTagInputInPublication = By.cssSelector(".b-main-tab__tag-select > div > .b-pb-suggest__emitter > div > input");
        private By savePublicationButton = By.cssSelector(" .b-control-panel-published__save-button > div");
        private By savePublicationButtonInPopup = By.xpath("//div[contains(@class,'b-confirm-modal__confirm-button')]/descendant::div[text()='Сохранить']");
        private By deleteTagInPublicationButton = By.cssSelector(".b-tag__icon > div");

        public String getSeoTitleOnSite() {
            return $(seoTitleTagOnSite).getAttribute("content");
        }

        public String getSeoDescriptionOnSite() {
            return $(seoDescriptionTagOnSite).getAttribute("content");
        }

        public String getUrlTagInAdmin() {
            StringBuffer str = new StringBuffer($(tagUrlInAdmin).innerText());
            str.delete(0, 14);
            return String.valueOf(str);
        }

        public void tagsCompareByName(String str1, String str2) {
            if (str2.compareTo(str1) < 0) {
                Assert.fail("Не работает сортировака по названию тегов");
            }
        }

        public void tagsCompare(int firstNumber, int lastNumber) {
            if (firstNumber < lastNumber) {
                Assert.fail("Не работает сортировка тегов по количеству постов/публикаций/подписчиков");
            }
        }

        public void isStringEquals(String str1, String str2) {
            if (str1.equals(str2)) {
                Assert.fail("Не увеличился счетчик постов/публикаций/подписчиков у тега");
            }
        }

        public void openTagsTab() {
            $(tagsTab).click();
        }

        public void addNewTag(String titleTag, String url, String descriptionTag, String seoTitle, String seoDescription) {
            $(addNewTag).click();
            $(titleTagInput).sendKeys(titleTag);
            $(urlInput).sendKeys(url);
            $(descriptionInput).sendKeys(descriptionTag);
            $(seoTitleTag).sendKeys(seoTitle);
            $(seoDescriptionTag).sendKeys(seoDescription);
            $(saveButtonInPopup).click();
        }

        public void searchNewTag(String titleTag) {
            $(searchInput).sendKeys(titleTag);
            $(searchInput).sendKeys(Keys.ENTER);

        }

        public void clearTextInSearchInput() {
            $(searchClearButton).click();
        }

        public void sortTagsByName() {
            $(sortByNameButton).click();
        }

        public void sortTagsByPublications() {
            $(sortByPublicationsButton).click();
        }

        public void sortTagsByPosts() {
            $(sortByPostsButton).click();
        }

        public void sortTagsBySubscriptions() {
            $(sortBySubscriptionsButton).click();
        }

        public void openTagOnSite() {
            $(openingTagOnSiteButton).click();
        }

        public void openPopupEditingTag() {
            $(editTagDropdownButton).click();
            $(editTagButton).click();
        }

        public void closePopupEditTag() {
            $(closePopupEditTagButton).click();
        }

        public void editTag(String tagDescription) {
            $(descriptionInput).clear();
            $(descriptionInput).sendKeys(tagDescription);
            $(saveButtonInPopup).click();
        }

        public void subscribeOnTag() {
            $(subscribeOnTagButton).click();
        }

        public void addNewTagInPost(String titleTag) {
            $(titleTagInputInPost).sendKeys(titleTag);
            $(titleTagInputInPost).sendKeys(Keys.ENTER);
            $(savePostButton).click();
        }

        public void addNewTagInPublication(String titleTagInPublication) {
            $(addingTagInputInPublication).sendKeys(titleTagInPublication);
            $(addingTagInputInPublication).sendKeys(Keys.ENTER);
            $(savePublicationButton).click();
            $(savePublicationButtonInPopup).click();

        }

        public void deleteNewTagInPublication() {
            $(deleteTagInPublicationButton).click();
            $(savePublicationButton).click();
            $(savePublicationButtonInPopup).click();
        }

        public void deleteNewTagInPost() {
            $(deleteTagInPostButton).scrollTo().click();
            $(savePostButton).scrollTo().click();
        }
    }
}
