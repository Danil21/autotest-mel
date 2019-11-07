package mel.AdminTestClasses;

import com.codeborne.selenide.WebDriverRunner;
import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import org.openqa.selenium.By;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;

public class AdminPublicationDraft extends AdminAddingPublication {

    private By draftListButton = By.xpath("//*[@class='g-list__item ' and text()='Черновики']");
    private By firstDraft = By.xpath("//*[contains(@class, 'b-article-list_admin')]/div[1]//div[@class='b-article-list-item__title']");
    private By mainDraftTab = By.xpath("//*[@class='g-tab__tabs']/div[1]");
    private By draftText = By.xpath("//*[contains(@class,'cke_show_borders')]/p");

    private By titleError = By.xpath("//*[contains(@class, 'b-main-tab__labeled-control_title')]//*[contains(@class,'g-hint_admin')]");
    private By subtitleError = By.xpath("//*[contains(@class, 'b-main-tab__labeled-control_author')]//*[contains(@class,'g-hint_admin')]");
    private By tagError = By.xpath("//*[contains(@class, 'b-main-tab__labeled-control_main-tag')]//*[contains(@class,'g-hint_admin')]");
    private By tagName = By.cssSelector(".b-tag__name");

    private By firstCoverError = By.xpath("//*[contains(@class, 'b-inner-cover_admin')]//*[contains(@class, 'g-hint_default-theme')]");
    private By secondCoverError = By.xpath("//*[contains(@class, 'b-radiobutton__item_selected')]//*[contains(@class, 'g-hint_height_auto')]");
    private By thirdCoverError = By.xpath("//*[@class='b-cover-manager__mobile-format']//*[contains(@class, 'g-hint_padding_M')]");
    private By fourthCoverError = By.xpath("//*[@class='b-cover-manager__mobile-main-page-format']//*[contains(@class, 'g-hint_padding_M')]");

    private By saveButton = By.xpath("//*[contains(text(),'Сохранить')]");
    private By savingTime = By.cssSelector(".b-control-panel-draft__saving-info-display");

    private By deleteDraftButton = By.cssSelector(".b-control-panel-draft__delete-button");
    private By confirmDeleteDraftButton = By.xpath("//*[contains(@class, 'g-button_size_L') and text()='Удалить']");
    private By cancelDeleteDraftButton = By.xpath("//*[contains(@class, 'g-modal_confirm-change-theme') and not(contains(@class, 'i-utils__hidden'))]//*[text()='Отмена']");

    private By textInErrorPage = By.cssSelector(".b-error__message");
    private By notificationOldSave = By.xpath("//*[contains(text(),'Вы пытаетесь сохранить старую версию черновика.')]");
    private By ОkOldSave = By.xpath("//*[text()='OK']");
    private By closePreview = By.xpath("//div[24]/div[2]/div[1]/div");

//    public void checkCoverSingle(int linkNumber, String typeCover) {
//        By element = null;
//
//        for (int numbr = 1; numbr < linkNumber; numbr++) {
//
//            if (typeCover == "Half") {
//                element = By.xpath("//div[2]/div/div[" + numbr + "]/div[2]/div/div[5]/div");
//                $x("//div[24]/div[2]/div[1]/div").click();
//            } else if (typeCover == "WithIcon") {
//                element = By.xpath("//div[2]/div/div[" + numbr + "]/div[2]/div/div[5]/div");
//                $x("//div[18]/div[2]/div[1]/div").click();
//            } else if (typeCover == "DoubleCard") {
//                element = By.xpath("//div[2]/div/div[" + numbr + "]/div[2]/div/div[5]/div");
//            } else if (typeCover == "WithQuote") {
//                element = By.xpath("//div[2]/div/div[" + numbr + "]/div[2]/div/div[5]/div");
//            } else if (typeCover == "Single") {
//                element = By.xpath("//div[2]/div/div[" + numbr + "]/div[2]/div/div[5]/div");
//            } else if (typeCover == "Number") {
//                element = By.xpath("//div[2]/div/div[" + numbr + "]/div[2]/div/div[5]/div");
//            } else if (typeCover == "Quote") {
//                element = By.xpath("//div[2]/div/div[" + numbr + "]/div[2]/div/div[5]/div");
//            }
//        }
//
//    }
//
//    public void checkCoverHalf() {
//        $(cardPreview).click();
//
//    }
//
//    public void checkCoverWithIcon() {
//        $(cardPreview).click();
//
//    }
//
//    public void checkCoverWithQuote() {
//
//
//    }
//
//    public void checkCoverDoubleCard() {
//        $(cardPreview).click();
//
//    }
//
//    public void checkCoverQuote() {
//        $(cardPreview).click();
//
//    }


    public void clickInDraftButton() {
        $(draftListButton).click();
    }

    public void clickInFirstDraft() {
        $(firstDraft).click();
    }

    public void clickInSaveDraftButton() {
        $(saveButton).click();
    }

    public void checkNotificationOldSave() {
        boolean notification = $(notificationOldSave).isDisplayed();
        if (notification) {
            $(ОkOldSave).click();
        }
    }

    public void clickInDeleteButton() {
        $(deleteDraftButton).click();
    }

    public void clickInConfirmDeleteButton() {
        $(confirmDeleteDraftButton).click();
    }

    public void clickInCancelDeleteButton() {
        $(cancelDeleteDraftButton).click();
    }

    public void clickInMainTab() {
        $(mainDraftTab).click();
    }

    public String getDraftUrl() {
        GetUrl getUrl = new GetUrl();
        return WebDriverRunner.currentFrameUrl().replaceAll(getUrl.driverGetAdminUrlStr(), "");
    }

    public boolean getDisplayedSavingTime() {
        AdditionalMethods methods = new AdditionalMethods();
        return methods.getDisplayedElement(savingTime);
    }

    public String getFirstTitleInDraftPage() {
        AdditionalMethods methods = new AdditionalMethods();
        return methods.getTextFromSelector(firstDraft);
    }

    public String getTextInErrorPage() {
        AdditionalMethods methods = new AdditionalMethods();
        return methods.getTextFromSelector(textInErrorPage);
    }

    public boolean[] displayedErrors() {
        AdditionalMethods methods = new AdditionalMethods();

        By[] selectors = {titleError, subtitleError, tagError, firstCoverError,
                secondCoverError, thirdCoverError, fourthCoverError};
        boolean[] statusDisplayed = new boolean[selectors.length];

        for (int i = 0; i < statusDisplayed.length; i++) {
            if (i == 3) {
                methods.Wait(2000);
                $(publicationCovers).click();
                methods.Wait(500);
            }
            statusDisplayed[i] = methods.getDisplayedElement(selectors[i]);
        }
        return statusDisplayed;
    }

    public String[] filledFieldsPublished() throws IOException, UnsupportedFlavorException {
        AdditionalMethods methods = new AdditionalMethods();

        By[] selectors = {publicationTitle, publicationSubtitle, publicationAuthorInput,
                publicationAnnouncement, publicationTagOnTheCover, tagName, draftText};
        String[] fieldsValues = new String[selectors.length];

        for (int i = 0; i < selectors.length; i++) {
            if (selectors[i] == draftText) {
                methods.scroll("500");
                methods.Wait(1000);
                $(publicationTextBlock).click();
                Robot robot = null;
                try {
                    robot = new Robot();
                } catch (AWTException e) {
                    e.printStackTrace();
                }
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_A);
                robot.keyRelease(KeyEvent.VK_A);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_C);
                robot.keyRelease(KeyEvent.VK_C);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                methods.Wait(500);
                fieldsValues[i] = (String) Toolkit.getDefaultToolkit()
                        .getSystemClipboard().getData(DataFlavor.stringFlavor);
            } else if (selectors[i] == publicationAuthorInput || selectors[i] == publicationTagOnTheCover) {
                fieldsValues[i] = $(selectors[i]).getAttribute("value");
            } else {
                fieldsValues[i] = methods.getTextFromSelector(selectors[i]);
            }
        }
        return fieldsValues;
    }
}