package mel.AdminTestClasses;

import mel.Helper.AdditionalMethods;
import mel.Helper.SetDriver;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class AdminRubricator extends SetDriver {

    private AdditionalMethods methods;

    public By rubricatorTab = By.xpath("//*[@class='g-list__item ' and text()='Рубрикатор']");
    //add rubric
    private By addRubricButton = By.cssSelector(".b-tree-list__additional-creator-name");
    private By titleRubricInput = By.cssSelector(".b-item-editor__name-editor > div > input");
    private By titleSeoInput = By.cssSelector(".b-item-editor__title-seo-editor > div > input");
    private By descriptionSeoInput = By.cssSelector(".b-item-editor__description-seo-editor > div > input");
    private By saveButtonInAddRubricPopup = By.cssSelector(".b-item-editor__save-button");
    //check new rubric on website
    public By newRubricOnWebsite = By.xpath("//a[@href='/rubric/testing']");
    private By metaNameSeoTitle = By.xpath("//meta[@name='title']");
    private By metaNameDescriptionTitle = By.xpath("//meta[@name='description']");
    //check edit rubric and close popup
    public By  editNewRubricButton = By.xpath("//div[@class='b-tree-list__name' and text()='Testing']"); //поменять при смене имени рубрики
    public By  editRubricButton = By.xpath("//div[@class='b-tree-list__name' and text()='Testing2']"); //поменять при смене имени рубрики
    private By closeButtonInAddRubricPopup = By.cssSelector(".g-modal__close-icon > div");
    //check
    private By newPublicationButton = By.cssSelector(".i-layout__new-publication-button");
    private By rubricDropdownOpenerButton = By.cssSelector(".g-dropdown__opener");
    private By newRubricInList = By.xpath("//div[contains(@class,'g-dropdown__content_scrollable')]/descendant::span[text()='Testing']");
    public By rubricInput = By.xpath(" //div[contains(@class,'rubric-select')]/descendant::div[contains(@class,'dropdown__opener-text')]");
    //check delete rubric
    private By deleteRubricButton = By.cssSelector(".b-item-editor__remover");
    public By rubricHidden = By.cssSelector("body > div:nth-child(4)");

    public By visibilitySwitch = By.xpath("//*[text()='Testing']/following-sibling::div[@class='b-tree-list__visibility-toggle-layout']/div");
    public By confirmationVisibility = By.xpath("//button[text()='ОК']");
    public By cancelDisplay = By.xpath("//button[text()='Отмена']");

    public String metaNameSeoTitleRubric() {
        String str = $(metaNameSeoTitle).getAttribute("content");
        return str;
    }

    public String metaNameSeoDescriptionRubric() {
        String str = $(metaNameDescriptionTitle).getAttribute("content");
        return str;
    }

    public void addNewRubric(String TitleRubric) {
        $(addRubricButton).click();
        sleep(2000);
        $(titleRubricInput).sendKeys(TitleRubric);
        sleep(2000);
        $(saveButtonInAddRubricPopup).click();
    }

    public void editRubric(String TitleRubric, String TitleSeo, String DescriptionSeo) {
        $(editNewRubricButton).click();
        $(titleRubricInput).sendKeys(TitleRubric);
        $(titleSeoInput).sendKeys(TitleSeo);
        $(descriptionSeoInput).sendKeys(DescriptionSeo);
        $(saveButtonInAddRubricPopup).click();
    }

    public void checkNewRubricOnAddPublicationPage() {
        $(newPublicationButton).click();
        $(rubricDropdownOpenerButton).click();
        sleep(500);
        $(newRubricInList).click();
    }

    public void checkCloseEditRubricPopup() {
        $(editRubricButton).click();
        sleep(3000);
        $(closeButtonInAddRubricPopup).click();
    }

    public void deleteRubric() {
        $(editRubricButton).click();
        sleep(4000);
        $(deleteRubricButton).click();
    }
}
