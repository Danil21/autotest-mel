package mel.AdminTestClasses;

import com.codeborne.selenide.Condition;
import mel.Helper.AdditionalMethods;
import org.openqa.selenium.By;

import java.awt.*;
import java.awt.event.KeyEvent;

import static com.codeborne.selenide.Selenide.$;

public class LockEditingPublication extends AdminAddingPublication {
    AdditionalMethods methods = new AdditionalMethods();

    private By firstPublication = By.xpath("//*[contains(@class, 'b-article-list_admin')]/div[1]//div[@class='b-article-list-item__title']");
    private By secondPublication = By.xpath("//*[contains(@class, 'b-article-list_admin')]/div[2]//div[@class='b-article-list-item__title']");
    private By redLockWhenEditingImg = By.xpath("//*[contains(@class, 'g-icon_img_lock')]");
    private By editingUserInModal = By.cssSelector(".b-blocking-control-modal__editor-name");
    private By editingPublicationInModal = By.cssSelector(".b-blocking-control-modal__article-title");
    private By stopEditingButton = By.xpath("//*[contains(text(), 'Прервать редактирование')]");
    private By textInErrorSavePopap = By.cssSelector(".b-popup-message__message");
    private By confirmButtonInErrorSavePopap = By.cssSelector(".b-popup-message__ok-button > div");
    private By messageAboutEditingSelectedArticle = By.cssSelector(".b-blocking-control-modal__message");

    public void clickInStopEditingButton() {
        $(stopEditingButton).click();
    }

    public void clickInInterruptEditing() {
        $(messageAboutEditingSelectedArticle).shouldHave(Condition.visible);
    }

    public void clickInFirstPublication() {
        $(firstPublication).click();
    }

    public void clickInConfirmButtonInErrorSavePopap() {
        $(confirmButtonInErrorSavePopap).click();
    }

    public boolean getDisplayedLockImg() {
        return methods.getDisplayedElement(redLockWhenEditingImg);
    }

    public String getTextFromFirstPublication() {
        return methods.getTextFromSelector(firstPublication);
    }

    public String getTextFromPublicationInModal() {
        return methods.getTextFromSelector(editingPublicationInModal);
    }

    public String getTextFromUserInModal() {
        return methods.getTextFromSelector(editingUserInModal)
                .replaceAll("«", "").replaceAll("»", "");
    }

    public String getErrorMessageInSavePopap() {
        return methods.getTextFromSelector(textInErrorSavePopap);
    }

    public void clickInSavePublicationButtons() {
        $(saveButton).click();
        methods.Wait(500);
        $(confirmSaveButton).click();
    }

    public void editPublicationFirsAdmin(String title, String covertag, String textblock) {
        methods = new AdditionalMethods();

        $(publicationTitle).clear();
        $(publicationTitle).sendKeys(title);
        methods.Wait(1000);
        $(publicationTagOnTheCover).clear();
        $(publicationTagOnTheCover).sendKeys(covertag);
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
}
