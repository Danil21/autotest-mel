package mel.AdminTestClasses;

import mel.Helper.AdditionalMethods;
import mel.Helper.SetDriver;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class AdminAddingUser extends SetDriver{

    private By users = By.xpath("//a[contains(@class,'g-list__item') and text()='Пользователи']");
    private By newUserButton = By.cssSelector(".b-user-list__new-user-button > div");
    private By newUserNameInput = By.cssSelector(".b-user-list__first-name-input > div > input");
    private By newUserSurnameInput = By.cssSelector(".b-user-list__last-name-input > div > input");
    private By newUserEmailInput = By.cssSelector(".b-user-list__email-input > div > input");
    private By newUserSaveButton = By.cssSelector(".b-user-list__save-button > div");
    public By incorrectPassword = By.cssSelector(".i-layout__password-input > div > div");
    public By incorrectEmailMessage = By.cssSelector(".b-user-list__email-input > div > div");
    private By disabledUserSaveButton = By.xpath("//*[contains(@class, 'g-button_disabled')]");

    private By adminRegistrationName = By.cssSelector(".i-layout__first-name-input > div > input");
    private By adminRegistrationSurname = By.cssSelector(".i-layout__last-name-input > div > input");
    private By adminRegistrationEmail = By.cssSelector(".i-layout__email-input > div > input");
    private By adminRegistrationPassword = By.cssSelector(".i-layout__password-input > div > input");
    private By adminRegistrationPasswordConfirm = By.cssSelector(".i-layout__same-password-input > div > input");
    private By adminRegistrationButton = By.cssSelector(".i-layout__create-button > div");
    public By adminIncorrectRegistrationEmail = By.cssSelector(".i-layout__email-input > div > div");

    private By adminRecoveryPasswordButton = By.cssSelector(".i-layout__content > a");
    private By adminRecoveryPasswordField = By.cssSelector(".i-layout__email-input > div > input");
    private By adminRecoveryPasswordConfirmButton = By.cssSelector(".i-layout__send-button");
    public By adminRecoveryEmail = By.cssSelector(".i-layout__email");
    private By adminNewPassword = By.cssSelector(".i-layout__password-input > div > input");
    private By adminConfirmNewPassword = By.cssSelector(".i-layout__same-password-input > div > input");
    private By adminSaveButton = By.cssSelector(".i-layout__save-button > div");
    public By adminPasswordsDontMatch = By.cssSelector(".i-layout__same-password-input > div > div");

    private By dropdown = By.cssSelector(".g-dropdown__opener > div");
    private By adminEditUser = By.cssSelector(".g-dropdown__content > div > div:nth-child(1)");
    public By editUserFirstName = By.cssSelector(".b-user-list__first-name-input > div > input");
    public By editUserLastName = By.cssSelector(".b-user-list__last-name-input > div > input");
    public By editUserEmail = By.cssSelector(".b-user-list__email-input > div > input");
    private By editUserSaveButton = By.cssSelector(".b-user-list__save-button > div");
    private By editUserCloseButton = By.xpath("//*[@class='g-modal__close-icon']");

    public By addedUser = By.xpath("//div[text()='SurName Name' and @class='b-user-list__user-name']");
    private By addedUserDropdown = By.xpath("//*[@data-first-name=\"Name\"]/div/div[1]/div");
    private By deleteAddedUserButton = By.xpath("//*[@class=\"g-dropdown__content\"]/div/div[2]");
    private By deleteButton = By.cssSelector(".b-user-list__remove-button > div");

    public void clickInUsersButton(){
        $(users).click();
    }

    public void addingNewUser(){
        $(users).click();
        $(newUserButton).click();
    }
    public void fillingNewUserFields(String userName, String userSurname, String userEmail){
        $(newUserNameInput).sendKeys(userName);
        $(newUserSurnameInput).sendKeys(userSurname);
        $(newUserEmailInput).sendKeys(userEmail);
        $(newUserSaveButton).click();
    }

    public void clearNewUserFields(){
        $(newUserNameInput).clear();
        $(newUserSurnameInput).clear();
        $(newUserEmailInput).clear();
    }

    public String getRegistrationName() {
        String str = $(adminRegistrationName).getAttribute("value");
        return str;
    }

    public String getRegistrationSurname() {
        String str = $(adminRegistrationSurname).getAttribute("value");
        return str;
    }

    public String getRegistrationEmail() {
        String str = $(adminRegistrationEmail).getAttribute("value");
        return str;
    }

    public void enterPasswordAndConfirm(String firstPassword, String lastPassword){
        $(adminRegistrationPassword).click();
        $(adminRegistrationPassword).sendKeys(firstPassword);
        $(adminRegistrationPasswordConfirm).sendKeys(lastPassword);
        $(adminRegistrationButton).click();
    }

    public void checkRecoveryPassword(String email){
        $(adminRecoveryPasswordButton).click();
        $(adminRecoveryPasswordField).sendKeys(email);
        $(adminRecoveryPasswordConfirmButton).click();
    }

    public void enterNewPasswordAndConfirm(String firstPassword, String lastPassword){
        $(adminNewPassword).sendKeys(firstPassword);
        $(adminConfirmNewPassword).sendKeys(lastPassword);
        $(adminSaveButton).click();
    }

    public void clearPasswordFields(){
        $(adminRegistrationPassword).clear();
        $(adminRegistrationPasswordConfirm).clear();
    }

    public void sendNewRegistrationEmail(String email){
        $(adminRegistrationEmail).sendKeys(email);
    }

    public void clearFieldNewRegistrationEmail(){
        $(adminRegistrationEmail).clear();
    }

    public void editNewUserFields(){
        $(dropdown).click();
        $(adminEditUser).click();
    }

    public void clearEditUserFields(){
        $(editUserFirstName).clear();
        $(editUserLastName).clear();
        $(editUserEmail).clear();
    }

    public void sendNewValuesInEditUserWindow(String firstName, String lastName, String email){
        $(editUserFirstName).sendKeys(firstName);
        $(editUserLastName).sendKeys(lastName);
        $(editUserEmail).sendKeys(email);
        $(editUserSaveButton).click();
    }

    public String getEditUserFirstName(){
        String str = $(editUserFirstName).getAttribute("value");
        return str;
    }

    public String getEditUserLastName(){
        String str = $(editUserLastName).getAttribute("value");
        return str;
    }

    public String getEditUserEmail(){
        String str = $(editUserEmail).getAttribute("value");
        return str;
    }

    public void deleteAddedUser(){
        $(addedUserDropdown).click();
        $(deleteAddedUserButton).click();
        $(deleteButton).click();
    }

    public void closeEditUserPopup(){
        $(editUserCloseButton).click();
    }

    public boolean isUserSaveButtonDisabled(){
        AdditionalMethods methods = new AdditionalMethods();
        return methods.getDisplayedElement(disabledUserSaveButton);
    }
}