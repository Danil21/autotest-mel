package mel.AdminTestClasses;

import mel.Helper.AdditionalMethods;
import mel.Helper.SetDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class AdminAddingAuthor extends SetDriver {

    private AdditionalMethods methods = new AdditionalMethods();

    private By authorsTabButton = By.xpath("//*[@class='g-list__item ' and text()='Авторы']");
    private By newAuthorButton = By.cssSelector(".i-layout__new-author-button"); //*[text()='+ Новый автор']
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
    private By deleteButtonInDropdown = By.cssSelector("#\\33 84 > div.b-table-row__controls > div > div > div.g-dropdown__content > div > div:nth-child(2)");
    private By confirmDeleteAuthorButton = By.cssSelector(".i-layout__remove-button");
    public By authorNameAndSurname = By.cssSelector(".b-table-row__name");
    public By authorNameAndSurnameInSite = By.cssSelector(".b-pb-author__name");
    public By aboutAuthorInSite = By.cssSelector(".b-pb-author__quote");
    private By sortingPublicationsButton = By.cssSelector(".b-table__sort-by-publications-button");
    private By sortingSubscribersButton = By.cssSelector(".b-table__sort-by-subscriptions-button");
    public By deleteUserWithPublicationMessage = By.cssSelector(".i-layout__cant-remove-message");
    private By closeButtonInDeleteUserWindow = By.cssSelector(".i-layout__close-button > div");

    public By firstPublicationCount = By.cssSelector("#\\32 8 > div.b-table-row__publications-count");
    public By secondPublicationcount = By.cssSelector("#\\32 37 > div.b-table-row__publications-count");

    public By firstSubscribersCount = By.cssSelector("#\\31 15 > div.b-table-row__subscriptions-count");
    public By secondSubscribersCount = By.cssSelector("#\\36 7 > div.b-table-row__subscriptions-count");

    private By deleteCoverButton = By.cssSelector(".i-layout__remove-photo-button.i-layout__photo-remove");

    public void checkAddingAuthorWithIncorrectFields() {
        $(authorsTabButton).click();
        sleep(2000);
        $(newAuthorButton).click();
        sleep(500);
        $(downloadPhotoButton).click();
        sleep(500);
        methods.imageDownload("C:\\1.jpg");
        sleep(1000);
    }

    public void addingNewAuthor(String name, String surname, String email, String aboutAuthor) throws InterruptedException {
        $(downloadCoverButton).click();
        sleep(200);
        methods.imageDownload("C:\\1.jpg");
        sleep(200);
        $(nameField).sendKeys(name);
        $(surnameField).sendKeys(surname);
        $(emailField).sendKeys(email);
        $(aboutAuthorField).sendKeys(aboutAuthor);

        if (getDisplayedCover() == false) {
            $(downloadPhotoButton).click();
            sleep(200);
            methods.imageDownload("C:\\1.jpg");
            sleep(200);
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

    public void editAuthor(String name, String surname) {
        clickIndropdownButton();
        $(editButtonInDropdown).click();
        sleep(200);
        $(nameField).clear();
        sleep(200);
        $(nameField).sendKeys(name);
        sleep(200);
        $(surnameField).clear();
        sleep(200);
        $(surnameField).sendKeys(surname);
        $(saveAuthorButton).click();
        sleep(200);
    }

    public void clickInsortingPublicationButton() {
        $(sortingPublicationsButton).click();
    }

    public void clickInsortingSubscribersButton() {
        $(sortingSubscribersButton).click();
    }

    public int convertSelectorToNumber(By selector) {
        String str = $(selector).getText();
        return Integer.parseInt(str.replace(" ", ""), 10);
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
        $(deleteButtonInDropdown).click();
        sleep(500);
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