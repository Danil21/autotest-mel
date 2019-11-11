package mel.Tests.Admin;

import MelAppium.resources.config;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import mel.AdminTestClasses.*;
import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.SetDriver;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;

public class AddingPublicationTest extends SetDriver {
    // если тест будет длится меньше минуты не произойдет автосейва
    private AdditionalMethods methods;
    private AdminLogin adminLogin;
    private AdminAddingPublication addingPublication;
    private GetUrl getUrl;
    private AdminSearch search;
    private AdminAutosave autosave;
    private AdminFrontPage frontPage;
    private AdminBlogs blogs;

    @AfterClass
    public void browserLogs() throws IOException {
        methods = new AdditionalMethods();
        getUrl = new GetUrl();

        ArrayList errors = new ArrayList();
        errors.add("[SEVERE] https://admin-qa" + getUrl.choiceStand() + ".mel.fm/api/login/ - Failed to load resource: the server responded with a status of 404 ()");
        errors.add("[SEVERE] https://qa" + getUrl.choiceStand() + ".mel.fm/draft/null - Failed to load resource: the server responded with a status of 404 ()");
        errors.add("yandex");
        errors.add("dmp.adx");
        errors.add("tracker.rareru");
        errors.add("revsci.net");
        errors.add("mel.fm/draft");
        errors.add("aidata");
        methods.getBrowserLogs(errors, "AddingPublicationTest");
    }

    @Test
    public void addingPublication() {
        addingPublication = new AdminAddingPublication();
        methods = new AdditionalMethods();
        getUrl = new GetUrl();
        adminLogin = new AdminLogin();
        search = new AdminSearch();
        autosave = new AdminAutosave();
        frontPage = new AdminFrontPage();
        blogs = new AdminBlogs();


        getUrl.driverGetAdminUrl();
        adminLogin.adminAuthorisation(config.getTestProperty("adminLogin"),config.getTestProperty("adminPass"));
        sleep(2000);
        addingPublication.clickInNewPublication();
        autosave.clickInPublicationSaveButton();
        // запись в строку времени при нажатии на кнопку сохранения
        String firstTime = methods.getTextFromSelector(autosave.publicationSaveTime);
        // заполнения полей при создании публикации
        addingPublication.fillingFields(methods.generateTitleRandom(), "Subtitle", "The Question", "Annoucement", "Covertag", "Addingtag", " Text in block");//TODO: режет первый символ, непонятно почему
        sleep(1000);
        Assert.assertEquals(title(), "Новая публикация");

        // запись в строку текущего url на странице заполнения обложек
        String draftUrl = WebDriverRunner.currentFrameUrl();
        // заполнение обложек
        addingPublication.addingCovers();
        sleep(5000);
        // переход по url для создаваемого черновика
        getWebDriver().get(draftUrl);
        sleep(2000);
        // проверка на соответствие данных при создании публикации и перехода в черновик
        Assert.assertEquals(methods.getTextInsideElement(addingPublication.publicationTitle), methods.generateTitleRandom());
        Assert.assertEquals(methods.getTextFromSelector(addingPublication.publicationSubtitle), "Subtitle");

        String parentWindowId = getWebDriver().getWindowHandle();
        final Set<String> oldWindowsSet = getWebDriver().getWindowHandles();

        // нажатие на кнопку просмотра превью публикации
        addingPublication.showPreviewPublication();
        methods.moveFocusToTheNewWindow(oldWindowsSet);

        // проверка соответствия данных при создании публикации и в превью
        getWebDriver().switchTo().frame($(By.tagName("iframe")));
        Assert.assertEquals(methods.getTextInsideElement(addingPublication.publicationPreviewTitle), methods.generateTitleRandom());
        Assert.assertEquals(methods.getTextFromSelector(addingPublication.publicationPreviewSubtitle), "Subtitle");
        Assert.assertEquals(methods.getTextFromSelector(addingPublication.publicationPreviewText), "Text in block");
        addingPublication.checkPublicationPreviewTag();

        // переход на окно создания публикации
        getWebDriver().switchTo().window(parentWindowId);
        // нажатие второй раз на кнопку сохранения
        autosave.clickInPublicationSaveButton();
        // запись в строку второго времени при нажатии на кнопку сохранения
        String secondTime = methods.getTextFromSelector(autosave.publicationSaveTime);
        // проверка на то, что время отличается при первом и втором нажатии на кнопку сохранения
        sleep(10000);
        autosave.comparisonPublicationTime(firstTime, secondTime);
        addingPublication.clickInPublicateButtons();

        //клик на первую статью за "сегодня" в списке публикаций
        addingPublication.clickInFirstPostToday();

        final String adminPublicationUrl = WebDriverRunner.currentFrameUrl();

        addingPublication.clickInPublicationSettings();
        // получение url публикации для сайта
        String publicationUrl = getUrl.driverGetStr() + addingPublication.getPublicationUrl() + "title";
        getWebDriver().get(publicationUrl);
        // проверка соответствия данных созданной публикации и публикации на сайте
        Assert.assertEquals(methods.getTextInsideElement(blogs.publicationTitle), methods.generateTitleRandom());
        Assert.assertEquals(methods.getTextFromSelector(blogs.publicationSubtitle), "Subtitle");
        Assert.assertEquals(methods.getTextFromSelector(blogs.publicationTagOnTheCover), "COVERTAG");
        Assert.assertEquals(methods.getTextFromSelector(blogs.publicationAuthor), "The Question");
        Assert.assertEquals(methods.getTextFromSelector(blogs.publicationText), "Text in block");
        Assert.assertTrue($(blogs.publicationImage).isDisplayed());

        // переход на страницу первой полосы
        sleep(1000);
        getUrl.driverGetCurrentAdminUrl("frontpage");
        String addingPublication1 = methods.getTextFromSelector(frontPage.publicationToAdd);
        String mainPublication = methods.getTextFromSelector(frontPage.titleMainPublication);
        // сравнение заголовка двух публикаций до нажатия на кнопку добавления на первую полосу
        frontPage.comprasionPublicationsInFrontPage(addingPublication1, mainPublication);
        // нажатие на кнопку добавления на первую полосу
        frontPage.clickInPublicationSwitcher();
        sleep(2000);
        // нажатие на кнопку сохранения изменений
        frontPage.clickInFrontPageSaveButton();
        sleep(2000);
        getUrl.driverGet();
        sleep(2000);
        // сравенение на соответствия созданной публикации и первой публикации, отображающейся на сайте
        Assert.assertEquals(methods.getTextInsideElement(blogs.mainPagePublicationTitle), methods.generateTitleRandom());
        Assert.assertEquals(methods.getTextFromSelector(blogs.mainPagePublicationSubtitle), "Subtitle");
        Assert.assertEquals(methods.getTextFromSelector(blogs.mainPagePublicationCoverTag), "COVERTAG");

        //редактирование публикации
        final String newTitle = "newTitle" + methods.generateNumber();

        getWebDriver().get(adminPublicationUrl);
        addingPublication.clearFields();
        addingPublication.fillingFields(newTitle, "newSubtitle", "WorldSkills", "newAnnoucement", "newCovertag", "newAddingtag", " new");
        addingPublication.clickInSaveButtons();
        sleep(5000);
        Assert.assertEquals(url(), getUrl.driverGetAdminUrlStr() + "publication-list");
        sleep(1000);
        getWebDriver().get(publicationUrl);
        Assert.assertEquals(methods.getTextFromSelector(blogs.publicationTitle), newTitle);
        $(blogs.publicationSubtitle).shouldHave(Condition.text("newSubtitle"));
        $(blogs.publicationTagOnTheCover).shouldHave(Condition.text("NEWCOVERTAG"));
        $(blogs.publicationAuthor).shouldHave(Condition.text("WorldSkills"));
        $(blogs.publicationText).shouldHave(Condition.text("Text in blocknew"));

        //снятие с публикации
        getWebDriver().get(adminPublicationUrl);
        addingPublication.clickInUnpublishingButtons();
        sleep(1000);
        final String urlAfterUnpublishing = WebDriverRunner.currentFrameUrl();

        getUrl.driverGetCurrentAdminUrl("publication-list");
        String[] publicationTitles = addingPublication.publicationsTitles();

        for (String str : publicationTitles) {
            if (str.contains(newTitle)) {
                Assert.fail("publication displayed");
            }
        }
        sleep(3000);
        //TODO: раскоментить когда починят баг с падающей страницей из за "/"
//        getWebDriver().get(publicationUrl);
//        Assert.assertEquals(title(), "Страница не найдена | Мел");

        //удаление снятой публикации
        getWebDriver().get(urlAfterUnpublishing);
        addingPublication.clickInDeleteButtons();
        sleep(1000);

        String[] draftTitles = addingPublication.publicationsTitles();
        for (String str : draftTitles) {
            if (str.contains(newTitle)) {
                Assert.fail("draft don`t deleted");
            }
        }

    }

    @Test
    public void externalPublication() {
        addingPublication = new AdminAddingPublication();
        methods = new AdditionalMethods();
        getUrl = new GetUrl();
        adminLogin = new AdminLogin();
        search = new AdminSearch();
        autosave = new AdminAutosave();
        frontPage = new AdminFrontPage();
        blogs = new AdminBlogs();

        // получение рандомной строки для заголовка публикации
        int a = 0;
        int b = 10000;
        int randomNumber = a + (int) (Math.random() * b);
        String title = "Title" + randomNumber;

        getUrl.driverGetAdminUrl();
        adminLogin.adminAuthorisation("test@example.com", "123qwe11");
        sleep(3000);
        addingPublication.clickInNewPublication();
        sleep(3000);
        // заполнения полей при создании публикации
        addingPublication.fillingFields(title, "Subtitle", "The Question", "Annoucement",
                "Covertag", "Addingtag", " Text in block");
        Assert.assertEquals(title(), "Новая публикация");
        // заполнение обложек
        addingPublication.addingCovers();

        addingPublication.clickInPublicationSettings();
        $(addingPublication.switchTypePublication).click();
        $(addingPublication.buttonTypePublication).click();
        $(addingPublication.confirmationPublicationChange).click();
        $(addingPublication.textInputUrlExist).shouldHave(Condition.visible);
        $(addingPublication.InputUrlExist).sendKeys("https://google.com");
        $(addingPublication.publicationTextBlock).shouldHave(Condition.not(Condition.visible));
        addingPublication.clickInPublicButton();
        Assert.assertEquals(url(), getUrl.driverGetAdminUrlStr()+"publication-list");
    }

}
