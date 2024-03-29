package mel.Tests.Admin;

import MelAppium.resources.config;
import mel.AdminTestClasses.AdminLogin;
import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.SetDriver;
import mel.TestClasses.AuthorBloggerSubscribe;
import mel.TestClasses.Registration;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class TagsTest extends SetDriver {

    private AdditionalMethods methods = new AdditionalMethods();
    private AuthorBloggerSubscribe.AdminTags tags = new AuthorBloggerSubscribe.AdminTags();
    private GetUrl getUrl = new GetUrl();
    private AdminLogin adminLogin = new AdminLogin();
    private Registration registration = new Registration();

    @Test
    public void addNewTags() {
        getUrl.driverGetAdminUrl();
        adminLogin.adminAuthorisation(config.getTestProperty("adminLogin"), config.getTestProperty("adminPass"));
        sleep(2000);

        int a = 0;
        int b = 10000;
        int FirstRandomNumber = a + (int) (Math.random() * b);
        int SecondRandomNumber = a + (int) (Math.random() * b);
        int randomNumber = FirstRandomNumber + SecondRandomNumber;
        final String tagName = Integer.toString(randomNumber);
        String tagUrl = Integer.toString(randomNumber);

        tags.openTagsTab();
        sleep(1000);
        tags.addNewTag(tagName, tagUrl, "Description", "SeoTitleTag", "SeoDescriptionTag");

        //проверка наличия тега на странице списка тегов после его создания
        tags.searchNewTag(tagName);
        String searchResultTag = methods.getTextFromSelector(tags.searchResultTag);
        Assert.assertEquals(searchResultTag, tagName);
        tags.clearTextInSearchInput();

        //Сортировка тегов по названию
        String titleFirstTag = methods.getTextFromSelector(tags.firstTagInList);
        sleep(1000);
        tags.sortTagsByName();
        sleep(500);
        String titleSecondTag = methods.getTextFromSelector(tags.secondTagInList);
        tags.tagsCompareByName(titleFirstTag, titleSecondTag);
        sleep(500);

        //Сортировака тегов по количеству постов
        tags.sortTagsByPosts();
        sleep(500);
        String countPostsFirstTag = methods.getTextFromSelector(tags.firstTagPostsCount);
        //Преобразование строки в число
        int FirstTagPostsCount = Integer.parseInt(countPostsFirstTag.replace(" ", ""), 10);
        String countPostsSecondTag = methods.getTextFromSelector(tags.secondTagPostsCount);
        //Преобразование строки в число
        int SecondTagPostsCount = Integer.parseInt(countPostsSecondTag.replace(" ", ""), 10);
        tags.tagsCompare(FirstTagPostsCount, SecondTagPostsCount);

        //Сортировка тегов по количеству подписчиков
        tags.sortTagsBySubscriptions();

        sleep(500);
        String countSubscriptionFirstTag = methods.getTextFromSelector(tags.firstTagSubscriptionsCount);
        int FirstTagSubscriptionsCount = Integer.parseInt(countSubscriptionFirstTag.replace(" ", ""), 10);
        String countSubscriptionSecondTag = methods.getTextFromSelector(tags.secondTagSubscriptionsCount);
        int SecondTagSubscriptionsCount = Integer.parseInt(countSubscriptionSecondTag.replace(" ", ""), 10);
        tags.tagsCompare(FirstTagSubscriptionsCount, SecondTagSubscriptionsCount);

        //Сортировка тегов по количеству публикаций
        tags.sortTagsByPublications();
        sleep(500);
        String countPublicationsFirstTag = methods.getTextFromSelector(tags.firstTagPublicationsCount);
        int FirstTagPublicationsCount = Integer.parseInt(countPublicationsFirstTag.replace(" ", ""), 10);
        String countPublicationsSecondTag = methods.getTextFromSelector(tags.secondTagPublicationsCount);
        int SecondTagPublicationsCount = Integer.parseInt(countPublicationsSecondTag.replace(" ", ""), 10);
        tags.tagsCompare(FirstTagPublicationsCount, SecondTagPublicationsCount);
        sleep(500);

        //Проверка закрытия попапа по крестику
        tags.openPopupEditingTag();
        tags.closePopupEditTag();
        //Проверка редактирования тега
        tags.openPopupEditingTag();

        getUrl.driverGetCurrentUrl(tagUrl);
         //  Проверка отображения seo нового тега на сайте
        Assert.assertEquals(tags.getSeoTitleOnSite(), "SeoTitleTag");
        Assert.assertEquals(tags.getSeoDescriptionOnSite(), "SeoDescriptionTag");

    }

        //
    @Test
    public void CheckingPerformanceMetricCounters() {
        getUrl.driverGetAdminUrl();
        adminLogin.adminAuthorisation(config.getTestProperty("adminLogin"), config.getTestProperty("adminPass"));
        sleep(2000);

        //Преобразование селектора url тега в строку
        String urlTagInAdmin = getUrl.driverGetStr() + "novosti";

        tags.openTagsTab();
        sleep(1000);
        tags.sortTagsByPublications();
        tags.sortTagsByPublications();
        sleep(1000);
        //Сохранение названия тега
        String titleTag = methods.getTextFromSelector(tags.firstTagInList);
        //Сохранение значений кол-ва постов, подписчиков, публикаций для проверки их изменения
        String countPublications = methods.getTextFromSelector(tags.firstTagPublicationsCount);
        String countPosts = methods.getTextFromSelector(tags.firstTagPostsCount);
        String countSubscriptions = methods.getTextFromSelector(tags.firstTagSubscriptionsCount);

        String parentWindow = getWebDriver().getWindowHandle();
        final Set<String> oldWindows = getWebDriver().getWindowHandles();
        tags.openTagOnSite();
        methods.moveFocusToTheNewWindow(oldWindows);

        //Проверка изменения описания тега после редактирования
        //  Assert.assertEquals(methods.getTextFromSelector(tags.descriptionTagOnSite), "Test");

        //Сравнение url тега в админке с url тега на сайте
        Assert.assertEquals(getWebDriver().getCurrentUrl(), urlTagInAdmin);

        //Регистрация и подписка на тег
        registration.userRegistrationWithLoginButton("testname", "testlastname", methods.generateStr(), "12345678");
        sleep(1000);
        tags.subscribeOnTag();
        //Добавление тега в пост
        getUrl.driverGetCurrentUrl("post/2269/editor");
        methods.closeNotificationCookie();
        tags.addNewTagInPost(titleTag);

        sleep(1000);
        //Добавление тега в публикацию
        getWebDriver().close();
        getWebDriver().switchTo().window(parentWindow);

        getUrl.driverGetCurrentAdminUrl("publication/1035"); //хуево работает
        tags.addNewTagInPublication(titleTag);

        //Проверка изменения значений кол-ва постов, подписчиков, публикаций у тега
        sleep(5000);
        tags.openTagsTab();
        tags.sortTagsByPublications();
        sleep(5000);

        tags.isStringEquals(countPublications, methods.getTextFromSelector(tags.firstTagPublicationsCount));
        sleep(1000);
        tags.isStringEquals(countPosts, methods.getTextFromSelector(tags.firstTagPostsCount));
        sleep(1000);
        tags.isStringEquals(countSubscriptions, methods.getTextFromSelector(tags.firstTagSubscriptionsCount));

        //Удаление тега у поста и публикации
        getUrl.driverGetCurrentAdminUrl("publication/1035");
        tags.deleteNewTagInPublication();
        getUrl.driverGetCurrentUrl("post/2269/editor");
        tags.deleteNewTagInPost();
    }

}
