package mel.AdminTestClasses;

import com.codeborne.selenide.WebDriverRunner;
import mel.Helper.AdditionalMethods;
import mel.Helper.SetDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AdminBranding extends SetDriver {

    private AdditionalMethods methods = new AdditionalMethods();

    // admin advertising main page
    private By advertisingTabButton = By.xpath("//*[@class='g-list__item ' and text()='Реклама']");
    private By advertisingBrandingTabButton = By.xpath("//*[@class='g-tab__tabs']//*[@href='#branding']");
    // admin branding page
    private By addItemButton = By.xpath("//*[contains(@class, 'branding-manager__branding-buttons')]//*[contains(@class,'branding-manager__add-button')]");
    private By saveButton = By.xpath("//*[contains(@class,'branding-manager__save-button')]");
    private By deleteButton = By.xpath("//button[contains(@class, 'branding-manager__remove-button')]");
    private By dialogDeleteButton = By.xpath("//*[contains(@class, 'dialog__delete-button')]");
    public By notificationModal = By.xpath("//*[@class='g-modal__content']");
    public By nameInput = By.xpath("//*[contains(@placeholder, 'Введите название')]");
    public By nameInputDiv = By.xpath("//*[contains(@class, 'branding-manager__name-input')]");

    private By linkInput = By.xpath("//*[contains(@class, 'branding-manager__link-input')]//*[contains(@class,'g-input__input')]");
    private By tagInput = By.xpath("//*[contains(@class, 'branding-manager__tag-input')]//*[contains(@class,'g-input__input')]");
    private By cover = By.xpath("//*[@class='branding-manager__image-uploader']");
    private By mainPageCheckbox = By.xpath("//*[contains(@class, 'branding-manager__mainpage-checkbox')]");
    private By notificationModalOkButton = By.xpath("//*[contains(@class, 'notification-dialog__ok-button')]");
    private By showPreviewButton = By.xpath("//*[@class='branding-manager__preview-link']");
    private By enableCheckbox = By.xpath("//*[contains(@class, 'branding-manager__enable-checkbox')]");
    public By  brandingButtonIcon = By.xpath("//*[contains(@class, 'branding-button__icon')]");
    private By rubricButton = By.xpath("//*[@class='branding-manager__rubrics']/div/button[2]");

    private By articleInput = By.xpath("//*[contains(@class, 'branding-manager__publication-list')]/div[1]//*[contains(@class, 'g-input__input')]");
    private By articleInputInserted = By.xpath("//*[contains(@value,'https://mel.fm')]");

    public By  tagButton = By.xpath("//*[contains(@class, 'branding-manager__tag-list')]//button[1]");
    private By brandingButtonLabel = By.xpath("//*[@class='branding-button__label']");
    private By deleteButtonArticle = By.xpath("//*[contains(@class,'branding-manager__delete-publication-button')]");

    // site layout branding
    public By layoutBranding = By.xpath("//*[@class='i-layout i-layout_branding']");

    public void deleteActiveCovers() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        List<WebElement> icons = brandingButtonIcon.findElements(driver);
        for (WebElement element : icons) {
            element.click();
            $(deleteButton);
            $(deleteButton).click();
            methods.Wait(500);
            $(dialogDeleteButton).click();
            methods.Wait(2000);
        }
    }

    public String getValueFromNameInput() {
        return $(nameInput).getAttribute("value");
    }

    public String getValueFromLinkInput() {
        return $(linkInput).getAttribute("value");
    }

    public String getValueFromTagInput() {
        return $(tagButton).getText();
    }

    public String getValueFromButtonLabel() {
        return $(brandingButtonLabel).getText();
    }

    public void openBrandingTab() {
        $(advertisingTabButton).click();
        $(advertisingBrandingTabButton).click();
    }

    public void checkValidation() {
        $(addItemButton).click();
        methods.scroll("2000");
        $(saveButton).click();
    }

    public void closeNotificationModal() {
        $(notificationModalOkButton).click();
    }

    public void checkCoverPreview() {
        String parentWindowId = getWebDriver().getWindowHandle();
        final Set<String> oldWindowsSet = getWebDriver().getWindowHandles();
        $(advertisingBrandingTabButton);
        $(cover).click();
        sleep(1000);
        methods.imageDownload("C:\\1.jpg");
        sleep(3000);
        $(showPreviewButton).click();
        methods.moveFocusToTheNewWindow(oldWindowsSet);
        sleep(1500);
        if (!methods.getDisplayedElement(layoutBranding)) {
            Assert.fail("Не отображается подложка в превью");
        }

        getWebDriver().switchTo().window(parentWindowId);
    }

    public void createCoverDraft(String name, String link, String article, String tag) {
        $(advertisingBrandingTabButton).scrollIntoView(true);
        $(nameInput).click();
        inputClear();
        $(nameInput).sendKeys(name);
        $(linkInput).click();
        inputClear();
        $(linkInput).sendKeys(link);
        $(mainPageCheckbox).click();
        $(rubricButton).click();
//        boolean inserted = $(articleInputInserted).isDisplayed();
//        if(inserted){
//            $(deleteButtonArticle).click();
//        }
        $(articleInput).click();
        $(articleInput).sendKeys(article);
        $(articleInput).sendKeys(Keys.ENTER);
        $(tagInput).click();
        inputClear();
        $(tagInput).sendKeys(tag);
        $(tagInput).sendKeys(Keys.ENTER);
        $(saveButton).scrollIntoView(true).click();
        getWebDriver().navigate().refresh();
    }

    public void enableCover() {
        $(enableCheckbox).click();
        $(saveButton).click();
    }

    private void inputClear() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_A);
        robot.keyPress(KeyEvent.VK_DELETE);

        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_DELETE);

    }


    public void editCover(String editedLink) {
        $(brandingButtonIcon).click();
        $(linkInput).scrollIntoView(false).click();
        inputClear();
        $(linkInput).clear();
        $(linkInput).sendKeys(editedLink);
        $(saveButton).click();
    }

    public void deleteCover() {
        //int coversBeforeDelete = countAllCovers();
        $(brandingButtonIcon).click();
        $(deleteButton).click();
        $(dialogDeleteButton).click();
        sleep(1000);
        /*int coversAfterDelete = countAllCovers();
        if (coversAfterDelete >= coversBeforeDelete){
            Assert.fail("Подложка не удалилась");
        }*/
    }

    public int countAllCovers() {
        List<WebElement> elementsNumber = getWebDriver().findElements(brandingButtonLabel);
        return elementsNumber.size();
    }
}