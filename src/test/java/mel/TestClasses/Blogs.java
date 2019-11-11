package mel.TestClasses;

import com.codeborne.selenide.Condition;
import mel.AdminTestClasses.AdminLogin;
import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.SetDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Blogs extends SetDriver {

    private Logout logout = new Logout();
    private AdditionalMethods methods = new AdditionalMethods();
    private GetUrl getUrl = new GetUrl();


    private By writeInBlogButton = By.xpath("//*[@class='button button_theme_transparent-borderless right-buttons__create-post-button']");
    private By titleInPageWriteInBlog = By.cssSelector(".b-post-editor__title-textarea > div > textarea");
    private By subtitleInPageWriteInBlog = By.cssSelector(".b-post-editor__subtitle-textarea > div > textarea");
    private By anonsInPageWriteInBlog = By.cssSelector(".b-post-editor__announcement-textarea > div > textarea");
    private By textInPageWriteInBlog = By.cssSelector("#cke_1_contents > iframe");
    private By tagInPageWriteInBlog = By.cssSelector(".b-pb-suggest__emitter > div > input");
    private By publicButton = By.xpath("//*[text()='Опубликовать']");
    public By titleTextInBlogPage = By.xpath("//*[@class='b-pb-article__body']/div/h1");
    public By subtitleTextInBlogPage = By.cssSelector(".b-pb-article__header > div.b-pb-article__subtitle");
    public By textInBlogPage = By.cssSelector("p");
    private By image = By.cssSelector(".b-post-editor__cover-placeholder");
    private By imageClass = By.cssSelector(".b-article-preview__clickable-area > img");
    private By myBlogButton = By.xpath("//a[@href='/blog']");
    private By tagName = By.cssSelector(".b-tag__name");
    private By deleteTagButton = By.cssSelector(".b-tag__remover");

    private By editBlogButton = By.xpath("//*[text()='Читать дальше']");
    private By editButton = By.xpath("//*[contains(text(), 'Редактировать')]");
    private By saveButton = By.xpath("//div[contains(@class,'save-button')]/descendant::div[contains(@class,'button')]");
    private By deleteBlogButton = By.xpath("//*[contains(text(), 'Удалить')]");
    private By confirmDeleteButton = By.cssSelector(".b-confirm-modal__confirm-button > div");
    public By noPublicationText = By.cssSelector(".b-blog__no-publications-title");

    public By textInTitle = By.cssSelector(".b-post-editor__header");
    private By writeTextInProfile = By.cssSelector(".b-blog__create-post-button > div");
    private By writeTextInHeader = By.cssSelector(".right-buttons__create-post-button");
    private By writeTextInPagePosts = By.cssSelector(".l-all-posts__create-post-button > div");
    public By authorisationText = By.cssSelector(".g-auth-social__header");
    private By deleteImageButton = By.cssSelector(".b-post-editor__cover > div > div");

    private By userDropdownOpener = By.cssSelector(".right-buttons__user-info-button > div");
    private By BlogButtonInUserDropdown = By.xpath("//a[@href='/blog']");
    private By editButtonAtBlogPage = By.xpath("//div[contains(@class,'edit-menu')]");
    private By banBloggerButton = By.xpath("//div[@data-id='banUser']");
    private By cancelButtonInBanConfirmation = By.xpath("//div[contains(@class,'confirm-actions')]/descendant::div[contains(@class,'cancel-button')]");
    private By confirmButtonInBanConfirmation = By.xpath("//div[contains(@class,'confirm-actions')]/descendant::div[contains(@class,'i-control') and parent::div[contains(@class,'confirm-button')]]");

    private By emailInputInProfile = By.xpath("//div[contains(@data-params,'email')]/descendant::input");

    public By banErrorLoginMessage = By.xpath("//div[contains(text(),'Вы заблокированы')]");
    public By recoveryErrorMessage = By.xpath("//div[@class='b-password-recovery-request__error-message']");
    private By textInBlockPopap = By.cssSelector(".b-confirm-modal__confirm-message");

    private By userName = By.xpath("//*[@class='user-info']");
    private By blogButtonInMenu = By.xpath("//a[@href='/blog']");
    private By editMenuButton = By.xpath("//*[contains(@class, 'g-icon_img_edit-menu')]");
    private By downloadCoverButton = By.xpath("//*[@data-id='uploadCover']");
    private By downloadImage = By.xpath("//*[@class='b-blog__cover-photo-image']");
    private By deleteCoverButton = By.xpath("//*[@data-id='removeCover']");
    //страница мой блог
    private By draftButtonInMenu = By.xpath("//a[@href='/blog']");
    //скрытие коментариев
    private By hideСomments = By.cssSelector(".b-post-editor__hide-comments-checkbox > div > div");
    private By nameplateHideComents = By.className("l-post__disabled-comments");
    private By checkAdminHideComents = By.xpath("//div[2]/a[@class='b-post__clickable-area' and 1]/div[@class='b-post__comments-count' and 5]");

    private By saveAndContinueButton = By.xpath("//*[contains(text(),'Сохранить и продолжить')]");
    private By saveDraftButton = By.xpath("//*[contains(@class, 'b-control-panel_pablo')]/div[3]");
    private By draftTitle = By.xpath("//*[@class='b-post-editor__header-label']");
    private By draftTitleInBlogerPage = By.xpath("//*[contains(@class, 'b-article-feed_pablo')]/div[1]//div[@class='b-article-preview__title']");
    private By confirmModalButton = By.xpath("//*[@class='b-confirm-modal__confirm-button']");
    private By firstData = By.xpath("//*[contains(@class, 'b-article-preview__publication-date_highlighted')]");

    private By postTitlesInAdmin = By.xpath("//div[@class='b-post__title']");

    private By editMassage = By.xpath("//*[contains(@class,'dialog__message')]");
    private By OkButton = By.cssSelector(".b-post-featured-dialog__ok-button > div");

    public void pressInBlogButton() {
        $(writeInBlogButton).click();
    }

    public void pressInBlogButtonInUserDropdown() {
        $(userDropdownOpener).click();
        $(BlogButtonInUserDropdown).click();
    }


    public void pressInPublicButton() {
        $(publicButton).scrollTo().click();
    }

    public void enterBlogText(String title, String subtitle, String announcement, String text, String tag) {
        methods = new AdditionalMethods();
        Robot r = null;
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        $(titleInPageWriteInBlog).sendKeys(title);
        $(subtitleInPageWriteInBlog).sendKeys(subtitle);
        $(anonsInPageWriteInBlog).sendKeys(announcement);
        methods.Wait(500);
        $(image).click();
        methods.Wait(1000);
        methods.imageDownload("C:\\1.jpg");
        $(textInPageWriteInBlog).click();
        $(textInPageWriteInBlog).sendKeys(text);
        $(tagInPageWriteInBlog).sendKeys(tag);
        methods.Wait(800);
        assert r != null;
        r.keyPress(java.awt.event.KeyEvent.VK_ENTER);
        r.keyRelease(java.awt.event.KeyEvent.VK_ENTER);
    }

    public void deleteAndAddedTag(String tag) {
        Robot r = null;
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        $(deleteTagButton).click();
        if (getDisplayedAddedTag()) {
            Assert.fail("added tag not deleted");
        }
        $(tagInPageWriteInBlog).sendKeys(tag);
        Objects.requireNonNull(r).keyPress(java.awt.event.KeyEvent.VK_ENTER);
        r.keyRelease(java.awt.event.KeyEvent.VK_ENTER);
        methods.sendKeyboardEnter();
    }

    public String getImageClass() {
        return $(imageClass).getTagName();
    }

    public void checkImage() {
        logout = new Logout();
        $(logout.headerDropdown).click();
        $(myBlogButton).click();
    }

    public void pressInEditBlogButtons() {
        $(editBlogButton).click();
        $(editButton).click();
    }

    public void pressInEditBlogButton() {
        $(editButton).scrollIntoView(false).click();
    }

    public String getTextInEditBlogPage() {
        return $(imageClass).getTagName();
    }

    public void clearBlogFields() {
        $(titleInPageWriteInBlog).clear();
        $(subtitleInPageWriteInBlog).clear();
        $(deleteImageButton).click();
        $(anonsInPageWriteInBlog).clear();
        $(tagInPageWriteInBlog).clear();
    }

    public void pressInSaveButton() {
        sleep(800);
        $(saveButton).click();
    }

    public void pressInDeleteButton() {
        sleep(500);
        $(deleteBlogButton).scrollTo().click();
        $(confirmDeleteButton).click();
    }

    public void pressInWriteTextInProfileButton() {
        $(writeTextInProfile).click();
    }

    public void pressInWriteTextInDropdown() {
        $(userDropdownOpener).shouldHave(Condition.visible).click();
        $(writeTextInHeader).click();
    }

    public void pressInWriteTextInPagePosts() {
        $(writeTextInPagePosts).click();
    }

    public void banBlogger(String BloggerUrl) {
        getUrl = new GetUrl();

        getWebDriver().get(BloggerUrl);
        $(editButtonAtBlogPage).click();
        $(banBloggerButton).scrollTo().click();
        $(textInBlockPopap).shouldHave(Condition.text(
                "После блокировки пользователя все его посты и ветки с его комментариями будут удалены. " +
                        "Восстановить пользователя и его блог будет невозможно. Вы уверены, что хотите его заблокировать?"));
        $(cancelButtonInBanConfirmation).click();
        $(editButtonAtBlogPage).click();
        $(banBloggerButton).click();
        $(confirmButtonInBanConfirmation).click();
    }

    public String getEmailValueInProfile() {
        return $(emailInputInProfile).getAttribute("value");
    }

    public void checkDownloadCoverImage() {
        Login login = new Login();

        login.authorisation("test153153153@mail.ru", "12345678");
        $(userName).click();
        $(draftButtonInMenu).click();
        Assert.assertEquals(title(), "mail test | Мел");

        $(userName).click();
        $(blogButtonInMenu).click();

        String imageSrcBeforeDownload = $(downloadImage).getAttribute("src");
        if (!imageSrcBeforeDownload.contains("image")) {
            Assert.fail("cover image not displayed");
        }

        $(editMenuButton).click();
        $(downloadCoverButton).click();
        sleep(500);
        methods.imageDownload("C:\\1.jpg");
        sleep(2000);
        String imageSrc = $(downloadImage).getAttribute("src");
        if (imageSrc.contains("cover-image")) {
            Assert.fail("image don`t download");
        }
        getWebDriver().navigate().refresh();
        if (imageSrc.contains("cover-image")) {
            Assert.fail("image don`t download");
        }
        $(editMenuButton).click();
        $(deleteCoverButton).click();
        sleep(1000);
        String imageSrcAfterDelete = $(downloadImage).getAttribute("src");
        if (!(imageSrcAfterDelete.contains("cover-image"))) {
            Assert.fail("image don`t delete");
        }
    }

    public void checkDraftMessage() {
//        String numberDrafts = $(draftButtonInMenu).getText()
//                .replaceAll("[a-zA-Zа-яА-Я]*", "")
//                .replaceAll("[\\(\\)]", "")
//                .replaceAll(" ", "");

        $(writeInBlogButton).click();
        enterBlogText("title", "subtitle", "announcement", " text", "школа");
        sleep(500);
        $(saveAndContinueButton).scrollTo().click();
        Assert.assertEquals($(draftTitle).getText(), "Черновик");
        $(userName).click();
        // давно выпиленно
//        String numberDraftsAfterSave = $(draftButtonInMenu).getText()
//                .replaceAll("[a-zA-Zа-яА-Я]*", "")
//                .replaceAll("[\\(\\)]", "")
//                .replaceAll(" ", "");
//
//        if (numberDrafts.trim().length() == 0) {
//            int number = 1;
//            if (!(numberDraftsAfterSave.trim().length() == 0)) {
//                if (number > Integer.parseInt(numberDraftsAfterSave)) {
//                    Assert.fail("number of drafts not increased");
//                }
//            } else {
//                Assert.fail("incorrect draft number");
//            }
//        } else {
//            if (!(numberDraftsAfterSave.trim().length() == 0)) {
//                if (Integer.parseInt(numberDrafts) > Integer.parseInt(numberDraftsAfterSave)) {
//                    Assert.fail("number of drafts not increased");
//                }
//            } else {
//                Assert.fail("incorrect draft number");
//            }
//        }

        clearBlogFields();
        enterBlogText("newTitle", "newSubtitle", "newAnnouncement", " New", "школа");
        sleep(1000);
        pressInPublicButton();
        $(titleTextInBlogPage).shouldHave(Condition.text("newTitle"));
        $(subtitleTextInBlogPage).shouldHave(Condition.text("newSubtitle"));

//        String numberDraftsAfterPublishing = $(draftButtonInMenu).getText()
//                .replaceAll("[a-zA-Zа-яА-Я]*", "")
//                .replaceAll("[\\(\\)]", "")
//                .replaceAll(" ", "");

//        if (!(numberDraftsAfterPublishing.trim().length() == 0)) {
//            if (Integer.parseInt(numberDraftsAfterPublishing) == Integer.parseInt(numberDraftsAfterSave)) {
//                Assert.fail("number of drafts not increased");
//            }
//        }

        $(editButton).click();
        $(deleteBlogButton).scrollTo().click();
        sleep(1000);
        $(confirmModalButton).click();

        $(writeInBlogButton).click();
        $(saveDraftButton).shouldBe(Condition.visible).scrollTo().click();
        Assert.assertEquals($(draftTitleInBlogerPage).getText(), "Без названия");

//        String numberDraftsAfterSecondPost = $(draftButtonInMenu).text()
//                .replaceAll("[a-zA-Zа-яА-Я]*", "")
//                .replaceAll("[\\(\\)]", "")
//                .replaceAll(" ", "");

//        if (!(numberDraftsAfterPublishing.trim().length() == 0)) {
//            if (Integer.parseInt(numberDraftsAfterPublishing) > Integer.parseInt(numberDraftsAfterSecondPost)) {
//                Assert.fail("number of drafts not increased");
//            }
//        }
//
        methods.scroll("700");
        $(draftTitleInBlogerPage).click();
        sleep(1000);
        $(saveDraftButton).click();
        $(confirmModalButton).click();
        if (checkDisplayedDraft()) {
            if ($(firstData).getText().contains("Сегодня")
                    && $(draftTitleInBlogerPage).getText().contains("Без названия")) {
                Assert.fail("draft isn`t deleted");
            }
        }

        logout.exitFromAccount();

    }

    private boolean checkDisplayedDraft() {
        try {
            return ($(firstData).isDisplayed()
                    && $(draftTitleInBlogerPage).isDisplayed());
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }



    public void ckeckMessage(){
        $(editMassage).shouldBe(Condition.text("Мы уже отредактировали и опубликовали ваш текст на сайте.\n" +
                "\n" +
                "Если вы не согласны с правками или хотите что-то изменить — напишите нашему редактору блогов на blogs@mel.fm.\n" +
                "\n" +
                "Обязательно вам ответим!"));
        $(OkButton).click();

    }
    public void checkEditingPrivileges() {
        AdminLogin adminLogin = new AdminLogin();
        getUrl = new GetUrl();

        getUrl.driverGetAdminUrl();
        adminLogin.adminAuthorisation("test@example.com", "123qwe11");
        sleep(1500);
        getUrl.driverGet();
        getUrl.driverGetCurrentUrl("blog/yelena-kalinicheva/65207-eto-strashno-kogda-rebenok-vozvrashchayetsya-domoy-kak-na-liniyu-fronta");

        try {
            $(editButton).shouldBe(Condition.visible).click();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            Assert.fail("edit button not displayed");
        }

        Assert.assertTrue(url().contains("editor"));
        if (checkDisplayedDeleteButton()) {
            Assert.fail("delete button is displayed");
        }
        if (checkOfComents()) {
            Assert.fail("в созданном посте коментарии не скрылись");
        }

    }

    public boolean checkOfComents() {
        try {
            return methods.getDisplayedElement(nameplateHideComents);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;

        }
    }

    public boolean checkOfComentsAdmin() {
        try {
            return methods.getDisplayedElement(checkAdminHideComents);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;

        }
    }

    public void pressInOfComments() {
        $(hideСomments).shouldBe(Condition.visible).click();// кнопка отключение коментов
    }

    private boolean checkDisplayedDeleteButton() {
        try {
            return $(deleteBlogButton).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public boolean getDisplayedAddedTag() {
        AdditionalMethods methods = new AdditionalMethods();
        return methods.getDisplayedElement(tagName);
    }

    public List<String> getPostTitles() {
        List<WebElement> elementList = getWebDriver().findElements(postTitlesInAdmin);
        ArrayList<String> titles = new ArrayList<>();
        for (int i = 0; i < elementList.size(); i++) {
            titles.add(elementList.get(i).getText());
        }
        return titles;
    }
}