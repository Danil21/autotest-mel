package mel.Tests.Admin;

import MelAppium.resources.config;
import mel.AdminTestClasses.*;
import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.SetDriver;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Set;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class DraftTest extends SetDriver {

    private AdditionalMethods methods;
    private  GetUrl getUrl;
    private AdminPublicationDraft draft;
    private AdminAddingPublication addingPublication;
    private AdminBlogs blogs;
    private  AdminLogin adminLogin;
    private  AdminLogout adminLogout;

    @Test
    public void checkDraft() throws IOException, UnsupportedFlavorException {
        getUrl = new GetUrl();
        adminLogin = new AdminLogin();
        draft = new AdminPublicationDraft();
        methods = new AdditionalMethods();
        adminLogout = new AdminLogout();
        addingPublication = new AdminAddingPublication();
        blogs = new AdminBlogs();

        getUrl.driverGetAdminUrl();
        adminLogin.adminAuthorisation(config.getTestProperty("adminLogin"), config.getTestProperty("adminPass"));

        draft.clickInNewPublication();
        sleep(1000);
        final String draftUrl = draft.getDraftUrl();

        draft.clickInPublicButton();
        sleep(1000);

        boolean[] errorsList = draft.displayedErrors();
        for (boolean flag : errorsList) {
            if (flag == false) {
                Assert.fail("errors not displayed");
            }
        }

        final String title = "title" + methods.generateNumber();
        final String subtitle = "subtitle";
        final String author = "The Question";
        final String annoucement = "Annoucement";
        final String covertag = "Covertag";
        final String tag = "Addingtag";
        final String text = "Text in block";

        draft.clickInMainTab();
        draft.fillingFields(title, subtitle, author, annoucement, covertag, tag, text);
        sleep(3000);
        draft.clickInSaveDraftButton();
        sleep(3000);
        Assert.assertTrue(draft.getDisplayedSavingTime());
        draft.clickInDraftButton();
        sleep(3000);
        draft.clickInFirstDraft();

        String[] expectedFilledFields = {title, subtitle, author, annoucement, covertag, tag, text};
        String[] actualFilledFields = draft.filledFieldsPublished();
        for (int i = 0; i < expectedFilledFields.length; i++) {
            if (!(expectedFilledFields[i].contains(actualFilledFields[i])) && actualFilledFields[i] != null) {
                Assert.fail("expected word: " + expectedFilledFields[i] + " not contains actual: " + actualFilledFields[i]);
            }
        }

        draft.clickInDeleteButton();
        draft.clickInCancelDeleteButton();
        draft.clickInDeleteButton();
        draft.clickInConfirmDeleteButton();

        if (draft.getFirstTitleInDraftPage().contains(title)) {
            Assert.fail("draft with title " + title + "not deleted");
        }

        getUrl.driverGetCurrentAdminUrl(draftUrl);
        Assert.assertEquals(draft.getTextInErrorPage(), "Страница не найдена");
        getUrl.driverGetAdminUrl();
        adminLogout.adminLogout();
    }

    @Test
    public void linkInDraft() {
        getUrl = new GetUrl();
        adminLogin = new AdminLogin();
        draft = new AdminPublicationDraft();
        methods = new AdditionalMethods();
        adminLogout = new AdminLogout();

        //Создание ссылки на черновик
        getUrl.driverGetAdminUrl();
        adminLogin.adminAuthorisation(config.getTestProperty("adminLogin"), config.getTestProperty("adminPass"));
        addingPublication.clickInNewPublication();

        addingPublication.clickInSettingsTab();
        addingPublication.clickInGenerateDraftLink();
        sleep(500);
        addingPublication.clickInCopyGeneratedLink();
        addingPublication.sendGeneratedLinkToInput();
        sleep(500);
        addingPublication.clickOnEnableLinkCheck();

        //Сравнение на соответствие сгенерированной и скопированной через кнопку ссылки
        Assert.assertEquals(addingPublication.getClipboardData(), methods.getTextFromSelector(addingPublication.generatedLink));
        sleep(2000);
        getWebDriver().get(addingPublication.getClipboardData());
        sleep(500);

        String parentWindowId = getWebDriver().getWindowHandle();
        final Set<String> oldWindowsSet = getWebDriver().getWindowHandles();

        // нажатие на кнопку просмотра превью публикации
        addingPublication.showPreviewPublication();
        methods.moveFocusToTheNewWindow(oldWindowsSet);

        // проверка соответствия данных при создании публикации и в превью
        getWebDriver().switchTo().frame($(By.tagName("iframe")));
        Assert.assertEquals(methods.getTextFromSelector(blogs.mainPagePublicationTitle), "Здесь будет заголовок");

        // переход на окно создания публикации
        getWebDriver().switchTo().window(parentWindowId);

        //Проверяем, что при снятом чекбоксе "Разрешить доступ" ссылка ведёт на 404
        addingPublication.clickOnEnableLinkCheck();
        addingPublication.clickInCopyGeneratedLink();
        draft.checkNotificationOldSave();
        draft.clickInSaveDraftButton();

        sleep(2000);

        //вставка url в поисковую строку
        Robot r = null;
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        r.keyPress(KeyEvent.VK_F6);
        sleep(1000);
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_V);
        r.keyPress(KeyEvent.VK_ENTER);

        r.keyRelease(KeyEvent.VK_F6);
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_V);
        r.keyRelease(KeyEvent.VK_ENTER);

        Assert.assertEquals(title(), "Страница не найдена | Мел");
    }

//    @Test
//    public void variationPreview() {
//        AdditionalMethods methods = new AdditionalMethods();
//        GetUrl getUrl = new GetUrl();
//        AdminPublicationDraft draft = new AdminPublicationDraft();
//        AdminAddingPublication addingPublication = new AdminAddingPublication();
//        AdminBlogs blogs = new AdminBlogs();
//        AdminLogin adminLogin = new AdminLogin();
//        getUrl.driverGetAdminUrl();
//        adminLogin.adminAuthorisation("test@example.com", "123qwe11");
//        draft.clickInDraftButton();
//        draft.clickInFirstDraft();
//        draft.clickInSettingsTab();
//
//    }

//        @Test
//        public void coversInsideArticle () {
//            AdditionalMethods methods = new AdditionalMethods();
//            GetUrl getUrl = new GetUrl();
//            AdminPublicationDraft draft = new AdminPublicationDraft();
//            AdminAddingPublication addingPublication = new AdminAddingPublication();
//            AdminBlogs blogs = new AdminBlogs();
//            AdminLogin adminLogin = new AdminLogin();
//
//            getUrl.driverGetAdminUrl();
//            adminLogin.adminAuthorisation("test@example.com", "123qwe11");
//            draft.clickInDraftButton();
//            draft.clickInFirstDraft();
//            $(addingPublication.publicationCovers).click();
//
//        }
}
