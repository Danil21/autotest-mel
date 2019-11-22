package mel.AdminTestClasses;

import com.codeborne.selenide.Condition;
import mel.Helper.AdditionalMethods;
import mel.Helper.SetDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AdminAddingPublication extends SetDriver {

    private AdditionalMethods methods;

    private By newPublicationButton = By.cssSelector(".i-layout__new-publication-button > div");
    public By publicationTitle = By.cssSelector(".b-main-tab__title-textarea > div > textarea");
    public By publicationSubtitle = By.cssSelector(".b-main-tab__subtitle-textarea > div > textarea");
    protected By publicationAuthorInput = By.cssSelector(".b-pb-suggest__emitter > div > input");
    protected By publicationAuthor = By.cssSelector(".b-pb-suggest__output-agent > div > div:nth-child(1)");
    protected By publicationAnnouncement = By.cssSelector(".b-main-tab__announcement-textarea > div > textarea");
    protected By publicationTagOnTheCover = By.cssSelector(".b-main-tab__main-tag-select > div > div.b-pb-suggest__emitter > div > input");
    protected By publicationAddingTag = By.cssSelector(".b-main-tab__tag-select > div > div.b-pb-suggest__emitter > div > input");
    public By publicationTextBlock = By.cssSelector("#cke_1_contents > iframe");
    private By publicationsInList = By.cssSelector(".b-article-list-item__title");

    public By publicationCovers = By.xpath("//div[contains(@class,'g-tab__tab') and text()='Обложки']");
    private By publicationCoverInArticle = By.cssSelector(".b-cover-uploader__content");
    private By publicationCoverOnTheMainPage = By.cssSelector(".b-tile__click-receiver");
    public By publicationCoverAdditionalFormats = By.cssSelector(".b-cover-manager__format-preview > div > div.b-cover-uploader__content");
    private By publicationShowPreviewButton = By.cssSelector(".b-control-panel-draft__middle-column");
    private By publicationDoubleCover = By.xpath("//div[contains(@class,'b-radiobutton__item_selected')]//div[@class='b-tile__click-receiver']");

    private By settingsTabButton = By.xpath("//div[contains(@class,'g-tab__tab') and text()='Настройки']");
    private By generateDraftLinkButton = By.xpath("//*[text()='Сгенерировать ссылку']");
    public By generatedLink = By.xpath("//div[@class='b-publication-settings__link-for-access' and contains(text(),'mel.fm/draft/')]");
    private By copyGeneratedLinkButton = By.xpath("//*[@data-params='{\"type\":\"copy-link\"}']");
    private By pixelCodeInput = By.xpath("//*[@placeholder='Вставьте код']");
    private By generatedLinkInInput = By.xpath("//div[@class='g-textarea__text-container' and contains(text(),'mel.fm/draft/')]");
    private By enableLinkCheck = By.xpath("//*[text()='Включить доступ по ссылке']");

    public By publicationPreviewTitle = By.className("b-ml-cover__title");
    public By publicationPreviewSubtitle = By.cssSelector(".b-ml-cover__subtitle");
    public By publicationPreviewText = By.xpath("//p");

    private By publicationButton = By.cssSelector(".b-control-panel-draft__publish-button > div");
    private By publicationConfirmButton = By.cssSelector("div.b-confirm-modal__confirm-actions > div.b-confirm-modal__confirm-button ");

    private By publicationSettings = By.xpath("//div[text()='Настройки']");
    private By publicationUrl = By.cssSelector(".b-publication-settings__address-site-input > div > span");

    protected By saveButton = By.cssSelector(".b-control-panel-published__save-button");
    private By cancelButton = By.xpath("//*[contains(@class, 'g-modal_confirm-change-theme') " +
            "and not(contains(@class, 'i-utils__hidden'))]//div[text()='Отмена']");
    protected By confirmSaveButton = By.xpath("//*[contains(@class, 'g-modal_confirm-change-theme') " +
            "and not(contains(@class, 'i-utils__hidden'))]//div[text()='Сохранить']");
    private By unpublishingButton = By.cssSelector(".b-control-panel-published__unpublish-button");
    private By confirmUnpublishingButton = By.xpath("//*[contains(@class, 'g-modal_confirm-change-theme') " +
            "and not(contains(@class, 'i-utils__hidden'))]//div[text()='Снять с публикации']");
    private By deletePublicationButton = By.cssSelector(".b-control-panel-draft__delete-button");
    private By confirmDeletePublicaitonButton = By.xpath("//*[contains(@class, 'g-modal_confirm-change-theme') " +
            "and not(contains(@class, 'i-utils__hidden'))]//div[text()='Удалить']");
    private By modal = By.xpath("//*[@class='i-control g-modal g-modal_admin g-modal_confirm-change-theme']/div[2]");
    public By okButton = By.xpath("//*[@class='b-popup-message__ok-button']/div");
    private By tagInDraftPreview = By.cssSelector(".b-ml-cover__main-tag-link");

    public By switchTypePublication = By.cssSelector(".b-publication-settings__type-dropdown");
    public By buttonTypePublication = By.xpath("//*[contains(text(),'Внешняя')]");
    public By confirmationPublicationChange = By.xpath("//div[@tabindex='0' and text()='Продолжить']");
    public By textInputUrlExist = By.xpath("//*[text()='Адрес']");
    public By InputUrlExist = By.xpath("//*[@data-wrap='input']//input");

    private By editingFirstPublicationToday = By.xpath("//div[text() ='Сегодня']/following-sibling::a[1]");


    public void fillingFields(String title, String subtitle, String author,
                              String announcement, String covertag, String addingtag, String textblock) {
        methods = new AdditionalMethods();

        $(publicationTitle).sendKeys(title);
        $(publicationSubtitle).sendKeys(subtitle);
        $(publicationAuthorInput).sendKeys(author);
        sleep(1000);
        $(publicationAuthor).click();
        $(publicationAnnouncement).sendKeys(announcement);
        $(publicationTagOnTheCover).sendKeys(covertag);
        $(publicationAddingTag).sendKeys(addingtag);
        Robot r = null;
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        r.keyPress(KeyEvent.VK_ENTER);
        r.keyRelease(KeyEvent.VK_ENTER);
        methods.scroll("550");
        $(publicationTextBlock).click();
        $(publicationTextBlock).sendKeys(textblock);
    }

    public void editPublicationTitle(String title) {
        $(publicationTitle).clear();
        $(publicationTitle).sendKeys(title);
    }

    public void clearFields() {
        $(publicationTitle).clear();
        $(publicationSubtitle).clear();
        $(publicationAuthorInput).clear();
        $(publicationAnnouncement).clear();
        $(publicationTagOnTheCover).clear();
    }

    public void clickInNewPublication() {
        $(newPublicationButton).click();
    }

    public void addingCovers() {
        methods = new AdditionalMethods();

        methods.scroll("-550");
        $(publicationCovers).click();
        $(publicationCoverInArticle).click();

        methods.imageDownload("C:\\1.jpg");
        methods.Wait(1000);
        $(publicationCoverOnTheMainPage).scrollTo().click();
        methods.Wait(1000);
        methods.imageDownload("C:\\1.jpg");
        methods.Wait(1000);
        methods.scroll("650");
        $(publicationDoubleCover).click();
        methods.Wait(1000);
        methods.imageDownload("C:\\1.jpg");
        methods.Wait(1000);
        $(publicationCoverAdditionalFormats).scrollTo().click();
        methods.imageDownload("C:\\1.jpg");
        methods.Wait(1000);
    }

    public void showPreviewPublication() {
        $(publicationShowPreviewButton).click();
        sleep(3000);
    }

    public void clickInPublicButton() {
        $(publicationButton).click();
        sleep(20000);
        $(publicationConfirmButton).click();
        $(okButton).click();
    }

    public void clickInPublicationSettings() {
        methods = new AdditionalMethods();

        methods.scroll("-550");
        $(publicationSettings).click();
    }

    public String getPublicationUrl() {
        StringBuffer str = new StringBuffer($(publicationUrl).getText());
        str.delete(0, 6);
        return String.valueOf(str);
    }

    public void clickInSettingsTab() {
        $(settingsTabButton).click();
    }

    public void clickInGenerateDraftLink() {
        $(generateDraftLinkButton).click();
    }

    public void clickInCopyGeneratedLink() {
        $(copyGeneratedLinkButton).click();
    }

    public void sendGeneratedLinkToInput() {
        $(pixelCodeInput).click();
        Robot r = null;
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_V);
        r.keyRelease(KeyEvent.VK_V);
        r.keyRelease(KeyEvent.VK_CONTROL);
    }

    public String getClipboardData() {
        return $(generatedLinkInInput).getAttribute("textContent");
    }

    public void clickOnEnableLinkCheck() {
        $(enableLinkCheck).click();
    }

    private void clickInCancelButton() {
        $(cancelButton).click();
        sleep(2000);
        if (methods.getDisplayedElement(modal)) {
            Assert.fail("modal displayed");
        }
    }

    public void clickInSaveButtons() {
        $(saveButton).click();
        sleep(500);
        clickInCancelButton();
        $(saveButton).click();
        $(confirmSaveButton).click();
    }

    public void clickInUnpublishingButtons() {
        $(unpublishingButton).click();
        clickInCancelButton();
        $(unpublishingButton).click();
        $(confirmUnpublishingButton).click();
    }

    public void clickInDeleteButtons() {
        $(deletePublicationButton).click();
        clickInCancelButton();
        $(deletePublicationButton).click();
        $(confirmDeletePublicaitonButton).click();
    }

    public void clickInPublicateButtons() {
        $(publicationButton).click();
        sleep(500);
        clickInCancelButton();
        $(publicationButton).click();
        $(publicationConfirmButton).click();
        $(okButton).click();
    }

    public void clickInFirstPostToday() {
        $(editingFirstPublicationToday).scrollTo().click();
        boolean hidden = $(editingFirstPublicationToday).isDisplayed();
        if (hidden) {
            getWebDriver().navigate().refresh();
            sleep(2000);
            $(editingFirstPublicationToday).scrollTo().click();
        }
    }

    public void checkPublicationPreviewTag() {
        $(tagInDraftPreview).shouldHave(Condition.text("Covertag"));
    }

    public String[] publicationsTitles() {
        List<WebElement> selectors = getWebDriver().findElements(publicationsInList);
        String[] titles = new String[selectors.size()];
        for (int i = 0; i < selectors.size(); i++) {
            titles[i] = selectors.get(i).getText();
        }
        return titles;
    }
}