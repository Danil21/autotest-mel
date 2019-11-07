package mel.Tests.Admin;

import mel.AdminTestClasses.AdminBranding;
import mel.AdminTestClasses.AdminLogin;
import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.SetDriver;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BrandingTest extends SetDriver {

    private AdditionalMethods methods;
    private GetUrl url = new GetUrl();
    private AdminBranding adminBranding;
    private AdminLogin adminLogin;


    @Test
    public void AdminBranding() {
        methods = new AdditionalMethods();
        adminBranding = new AdminBranding();
        adminLogin = new AdminLogin();
        url = new GetUrl();

        String brandingTitle = "brandingTitle" + methods.generateNumber();
        String link = "https://mel.fm";
        String editedLink = "https://yandex.ru";
        String article = "https://qa" + url.choiceStand() + ".mel.fm/obrazovaniye/751928-british_design_marketing";
        String tag = "важный разговор";

        url.driverGetAdminUrl();
        adminLogin.adminAuthorisation("test@example.com", "123qwe11");

        // переходим на страницу брендирования и проверям имеются ли активные подложки. Если да, то удаляем все
        adminBranding.openBrandingTab();
        if (methods.getDisplayedElement(adminBranding.brandingButtonIcon)) {
            adminBranding.deleteActiveCovers();
        }

        /* проверяем валидацию при сохранении с незаполненным тергетированием
         * проверяется, появляется ли предупреждающий поп-ап при сохранении */

        adminBranding.checkValidation();
        if (!methods.getDisplayedElement(adminBranding.notificationModal)) {
            Assert.fail("Не отображается поп-ап при валидации");
        }
        adminBranding.closeNotificationModal();
        sleep(1000);
        adminBranding.checkCoverPreview();
        sleep(3000);
        adminBranding.createCoverDraft(brandingTitle, link, article, tag);

        Assert.assertEquals(adminBranding.getValueFromNameInput(), brandingTitle);
        Assert.assertEquals(adminBranding.getValueFromLinkInput(), link);

        $(adminBranding.tagButton).scrollIntoView(false);
        Assert.assertEquals(adminBranding.getValueFromTagInput(), tag);

        // проверка публикации подложки на главной странице, странице рубрики, страницы статьи
        adminBranding.enableCover();
        sleep(1000);

        // открываем главную страницу и проверяем подложку
        url.driverGet();
        // проверяем наличие подложки и ссылки на главной странице
        if (!methods.getDisplayedElement(adminBranding.layoutBranding) & (!methods.getDisplayedElement(By.xpath("//*[@class='i-layout i-layout_branding']/a[@href='" + link + "']")))) {
            Assert.fail("Не отображается подложка на главной странице");
        }

        // открываем страницу рубрики и проверяем подложку
        url.driverGetCurrentUrl("/rubric/school");
        // проверяем наличие подложки и ссылки на странице рубрики
        if (!methods.getDisplayedElement(adminBranding.layoutBranding) & (!methods.getDisplayedElement(By.xpath("//*[@class='i-layout i-layout_branding']/a[@href='" + link + "']")))) {
            Assert.fail("Не отображается подложка на странице рубрики");
        }

        // открываем страницу статьи и проверяем подложку

        getWebDriver().get(article);
        // проверяем наличие подложки и ссылки на странице статьи
        if (!methods.getDisplayedElement(adminBranding.layoutBranding) & (!methods.getDisplayedElement(By.xpath("//*[@class='i-layout i-layout_branding']/a[@href='" + link + "']")))) {
            Assert.fail("Не отображается подложка на странице статьи");
        }

        // открываем страницу статьи по тегу "важный разговор"
        url.driverGetCurrentUrl("vazhny_razgovor/2539804-change_school");
        // проверяем наличие подложки и ссылки на странице статьи по тегу "важный разговор"
        if (!methods.getDisplayedElement(adminBranding.layoutBranding) & (!methods.getDisplayedElement(By.xpath("//*[@class='i-layout i-layout_branding']/a[@href='" + link + "']")))) {
            Assert.fail("Не отображается подложка на странице статьи по определенному тегу");
        }

        // открываем брендирование в админке
        url.driverGetAdminUrl();
        adminBranding.openBrandingTab();

        // редактируем ссылку у подложки
        adminBranding.editCover(editedLink);
        sleep(2000);
        // открываем главную страницу и проверяем что изменилсь ссылка, а подложка по-прежнему активна
        url.driverGet();
        if (!methods.getDisplayedElement(adminBranding.layoutBranding) & (!methods.getDisplayedElement(By.xpath("//*[@class='i-layout i-layout_branding']/a[@href='" + editedLink + "']")))) {
            Assert.fail("Не отображается подложка на главной или не изменилась ссылка после редактирования");
        }

        // открываем брендирование в админке
        url.driverGetAdminUrl();
        adminBranding.openBrandingTab();
        sleep(1000);
        // удаляем созданную подложку и проверяем, что она удалилась
        adminBranding.deleteCover();
        if (methods.getDisplayedElement(By.xpath("//span[@class='branding-button__label' and text()='" + brandingTitle + "']"))) {
            Assert.fail("Подложка не удалилась");


        }


   /* @Test
    public void AdminBrandingEdit() {
        methods = new AdditionalMethods();
        adminBranding = new AdminBranding();
        adminLogin = new AdminLogin();
        url = new GetUrl();


        String brandingTitle = "brandingTitle" + methods.generateNumber();
        String link = "https://mel.fm";
        String editedLink = "https://yandex.ru";
        String article = "https://qa" + url.choiceStand() + ".mel.fm/obrazovaniye/751928-british_design_marketing";
        String tag = "важный разговор";


        url.driverGetAdminUrl();
        adminLogin.adminAuthorisation("test@example.com", "123qwe11");
        adminBranding.openBrandingTab();

        // проверка создания черновика подложки с включенной главной, рубрикой, статьей, тегом
        adminBranding.createCoverDraft(brandingTitle, link, article, tag);

        Assert.assertEquals(adminBranding.getValueFromNameInput(), brandingTitle);
        Assert.assertEquals(adminBranding.getValueFromLinkInput(), link);

        $(adminBranding.tagButton).scrollIntoView(false);
        Assert.assertEquals(adminBranding.getValueFromTagInput(), tag);

        // проверка публикации подложки на главной странице, странице рубрики, страницы статьи
        adminBranding.enableCover();
        sleep(1000);

        // открываем главную страницу и проверяем подложку
        url.driverGet();
        // проверяем наличие подложки и ссылки на главной странице
        if (!methods.getDisplayedElement(adminBranding.layoutBranding) & (!methods.getDisplayedElement(By.xpath("//*[@class='i-layout i-layout_branding']/a[@href='" + link + "']")))) {
            Assert.fail("Не отображается подложка на главной странице");
        }

        // открываем страницу рубрики и проверяем подложку
        url.driverGetCurrentUrl("/rubric/school");
        // проверяем наличие подложки и ссылки на странице рубрики
        if (!methods.getDisplayedElement(adminBranding.layoutBranding) & (!methods.getDisplayedElement(By.xpath("//*[@class='i-layout i-layout_branding']/a[@href='" + link + "']")))) {
            Assert.fail("Не отображается подложка на странице рубрики");
        }

        // открываем страницу статьи и проверяем подложку

        getWebDriver().get(article);
        // проверяем наличие подложки и ссылки на странице статьи
        if (!methods.getDisplayedElement(adminBranding.layoutBranding) & (!methods.getDisplayedElement(By.xpath("//*[@class='i-layout i-layout_branding']/a[@href='" + link + "']")))) {
            Assert.fail("Не отображается подложка на странице статьи");
        }

        // открываем страницу статьи по тегу "важный разговор"
        url.driverGetCurrentUrl("vazhny_razgovor/2539804-change_school");
        // проверяем наличие подложки и ссылки на странице статьи по тегу "важный разговор"
        if (!methods.getDisplayedElement(adminBranding.layoutBranding) & (!methods.getDisplayedElement(By.xpath("//*[@class='i-layout i-layout_branding']/a[@href='" + link + "']")))) {
            Assert.fail("Не отображается подложка на странице статьи по определенному тегу");
        }

        // открываем брендирование в админке
        url.driverGetAdminUrl();
        adminBranding.openBrandingTab();

        // редактируем ссылку у подложки
        adminBranding.editCover(editedLink);
        sleep(2000);
        // открываем главную страницу и проверяем что изменилсь ссылка, а подложка по-прежнему активна
        url.driverGet();
        if (!methods.getDisplayedElement(adminBranding.layoutBranding) & (!methods.getDisplayedElement(By.xpath("//*[@class='i-layout i-layout_branding']/a[@href='" + editedLink + "']")))) {
            Assert.fail("Не отображается подложка на главной или не изменилась ссылка после редактирования");
        }

        // открываем брендирование в админке
        url.driverGetAdminUrl();
        adminBranding.openBrandingTab();
        sleep(1000);
        // удаляем созданную подложку и проверяем, что она удалилась
        adminBranding.deleteCover();
        if (methods.getDisplayedElement(By.xpath("//span[@class='branding-button__label' and text()='" + brandingTitle + "']"))) {
            Assert.fail("Подложка не удалилась");
        }*/
    }
}