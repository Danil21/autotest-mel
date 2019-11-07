package mel.AdminTestClasses;

import mel.Helper.AdditionalMethods;
import mel.Helper.SetDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;

public class AdminAddingAuthor extends SetDriver {

    private AdditionalMethods methods;

    private By authorsTabButton = By.xpath("//*[@class='g-list__item ' and text()='Авторы']");
    private By newAuthorButton = By.cssSelector(".i-layout__subheader > div");
    private By downloadPhotoButton = By.cssSelector(".i-layout__photo-upload > div");
    private By downloadCoverButton = By.cssSelector(".i-layout__cover-upload > div");
    private By nameField = By.cssSelector(".i-layout__first-name-input > div > input");
    private By surnameField = By.cssSelector(".i-layout__last-name-input > div > input");
    private By emailField = By.cssSelector(".i-layout__email-input > div > input");
    private By aboutAuthorField = By.cssSelector(".i-layout__description-textarea > div > textarea");
    private By saveAuthorButton = By.cssSelector(".i-layout__save-button > div");
    private By disabledSaveAuthorButton = By.xpath("//*[contains(@class, 'g-button_solid-theme g-button_disabled')]");
    public By secondAuthor = By.cssSelector("#\\31 84 > div.b-table-row__name");
    public By thirdAuthor = By.cssSelector("#\\32 11 > div.b-table-row__name");
    private By sortArrowButton = By.cssSelector(".b-table__sort-by-name-button > div");
    private By openInNewPageButton = By.cssSelector(".b-table-row__controls");
    private By dropdownButton = By.cssSelector(".g-dropdown__opener");
    private By editButtonInDropdown = By.cssSelector(".g-dropdown__content > div > div:nth-child(1)");
    private By deleteButtonInDropdown = By.xpath("//div[contains(@class,'g-dropdown_focused')]/descendant::div[text()='Удалить']");
    private By confirmDeleteButton = By.xpath("//div[contains(@class,'g-button_solid-theme') and text()='Удалить']");
    private By confirmDeleteAuthorButton = By.cssSelector(".i-layout__remove-button");
    public By authorNameAndSurname = By.cssSelector(".b-table-row__name");
    public By authorNameAndSurnameInSite = By.cssSelector(".b-pb-author__name");
    public By aboutAuthorInSite = By.cssSelector(".b-pb-author__quote");
    private By sortingPublicationsButton = By.cssSelector(".b-table__sort-by-publications-button");
    private By sortingSubscribersButton = By.cssSelector(".b-table__sort-by-subscriptions-button");
    public By deleteUserWithPublicationMessage = By.cssSelector(".i-layout__cant-remove-message");
    private By closeButtonInDeleteUserWindow = By.cssSelector(".i-layout__close-button > div");
    private By passwordsDontMatchMessage = By.cssSelector(".i-layout__same-password-input > div > div");

    public By firstPublicationCount = By.cssSelector("#\\32 8 > div.b-table-row__publications-count");
    public By secondPublicationcount = By.cssSelector("#\\32 37 > div.b-table-row__publications-count");

    public By firstSubscribersCount = By.cssSelector("#\\31 15 > div.b-table-row__subscriptions-count");
    public By secondSubscribersCount = By.cssSelector("#\\36 7 > div.b-table-row__subscriptions-count");

    private By deleteCoverButton = By.cssSelector(".i-layout__remove-photo-button.i-layout__photo-remove");
    private By createdAuthorEditButton = By.xpath("//a[contains(@href,'firstname')]/following-sibling::div");

    public void checkAddingAuthorWithIncorrectFields() {
        methods = new AdditionalMethods();
        $(authorsTabButton).click();
        $(newAuthorButton).click();
        $(downloadPhotoButton).click();
        methods.Wait(500);
        methods.imageDownload("C:\\1.jpg");
        methods.Wait(2000);
    }

    public void addingNewAuthor(String name, String surname, String email, String aboutAuthor) throws InterruptedException {
        methods = new AdditionalMethods();

        $(downloadCoverButton).click();
        methods.Wait(500);
        methods.imageDownload("C:\\1.jpg");
        methods.Wait(500);
        $(nameField).sendKeys(name);
        $(surnameField).sendKeys(surname);
        $(emailField).sendKeys(email);
        $(aboutAuthorField).sendKeys(aboutAuthor);

        if (getDisplayedCover() == false) {
            $(downloadPhotoButton).click();
            methods.Wait(500);
            methods.imageDownload("C:\\1.jpg");
            methods.Wait(500);
        }

        $(saveAuthorButton).click();
    }

    public void clickInSortArrowButton() {
        $(sortArrowButton).click();
    }

    public void clickInOpenInNewPageButton() {
        $(openInNewPageButton).click();
    }

    public void clickIndropdownButton() {
        $(dropdownButton).click();
    }

    public void clickOnAuthorTab() {
        $(authorsTabButton).click();
    }

    public void editAuthor(String name, String surname) {
        methods = new AdditionalMethods();

        clickIndropdownButton();
        $(editButtonInDropdown).click();
        methods.Wait(100);
        $(nameField).clear();
        methods.Wait(100);
        $(nameField).sendKeys(name);
        methods.Wait(100);
        $(surnameField).clear();
        methods.Wait(100);
        $(surnameField).sendKeys(surname);
        $(saveAuthorButton).click();
        methods.Wait(100);
    }

    public void clickInsortingPublicationButton() {
        $(sortingPublicationsButton).click();
    }

    public void clickInsortingSubscribersButton() {
        $(sortingSubscribersButton).click();
    }

    public int convertSelectorToNumber(By selector) {
        String str = $(selector).getText();
        int number = Integer.parseInt(str.replace(" ", ""), 10);
        return number;
    }

    // сравнение чисел для сортировки
    public void compareTheNumbers(int firstNumber, int lastNumber) {
        if (firstNumber < lastNumber) {
            Assert.fail("Sorting not working");
        }
    }

    public void clickOnEditCreatedAuthor() {
        $(dropdownButton).click();
    }

    public void clickInDeleteButtons() {
        methods = new AdditionalMethods();

        $(deleteButtonInDropdown).click();
        methods.Wait(1000);
        $(confirmDeleteAuthorButton).click();
    }

    // сравнение двух строк
    public void compareAuthorsAfterSort(String[] arr) {
        for (int j = 0; j < arr.length; j++) {
            for (int i = j + 1; i < arr.length; i++) {
                // если целое значение, возвращенное методом, отрицательно, то строка,
                // с которой был вызван метод, меньше строки-параметра, если положительно — больше.
                if (arr[i].compareTo(arr[j]) > 0) {
                    Assert.fail("Sorting authors not works");
                }
            }
        }
    }

    public void clickInDeleteUserButton() {
        $(deleteButtonInDropdown).click();
    }

    public void clickOnConfirmDeleteButton() {
        $(confirmDeleteButton).click();
    }

    public void clickInCloseButtonInDeleteUserWindow() {
        $(closeButtonInDeleteUserWindow).click();
    }

    private boolean getDisplayedCover() {
        try {
            return $(deleteCoverButton).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isSaveButtonDisabled() {
        return methods.getDisplayedElement(disabledSaveAuthorButton);
    }
}