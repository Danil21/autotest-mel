package mel.TestClasses;

import com.codeborne.selenide.Condition;
import mel.Helper.SetDriver;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Registration extends SetDriver{

    // Registration window
    private By registrationWindow = By.cssSelector(".g-tab__tabs > div:nth-child(2)");
    private By registrationFirstNameInput = By.cssSelector(".b-auth-email__first-name-input > div > input");
    private By registrationLastNameInput = By.cssSelector(".b-auth-email__last-name-input > div > input");
    private By registrationEmailInput = By.cssSelector(".b-auth-email__reg-email-input > div > input");
    private By registrationPasswordInput = By.cssSelector(".b-auth-email__reg-password-input > div > input");
    private By registrationCheckBox = By.cssSelector(".b-auth-email__accept-checkbox > div > div.b-checkbox__icon");
    private By registrationEmailButton = By.xpath("//div[@class='b-auth-email__registration-button']/descendant::span[text()='Зарегистрироваться']");
    public  By headerUserName = By.xpath("//*[@class='user-info__name']");
    private By termsOfUseButton = By.cssSelector(".g-auth-social__extra-text > a:nth-child(1)");
    public  By incorrectEmailText = By.cssSelector(".b-auth-email__reg-email-input > div > div");
    public  By incorrectFirstNameText = By.cssSelector(".b-auth-email__first-name-input > div > div");
    public  By incorrectLastNameText = By.cssSelector(".b-auth-email__last-name-input > div > div");
    public  By doubleEmailText = By.cssSelector(".b-auth-email__reg-email-input > div > div");

    public void pressInRegistrationButton(){
        $(registrationWindow).click();
    }

    // Registration of the user with enter button
    public void userRegistrationWithLoginButton(String firstName, String lastName, String email, String password) {
        Login login = new Login();

        $(login.headerLoginButton).shouldBe(Condition.enabled).click();
        $(login.emailLoginButton).click();
        $(registrationWindow).click();
        $(registrationFirstNameInput).sendKeys(firstName);
        $(registrationLastNameInput).sendKeys(lastName);
        $(registrationEmailInput).sendKeys(email);
        $(registrationPasswordInput).sendKeys(password);
        $(registrationCheckBox).click();
        $(registrationEmailButton).click();
    }

    // Method for registration with valid data
    public void userRegistrationWithoutLoginButton(String firstName, String lastName, String email, String password) {
        $(registrationFirstNameInput).sendKeys(firstName);
        $(registrationLastNameInput).sendKeys(lastName);
        $(registrationEmailInput).sendKeys(email);
        $(registrationPasswordInput).sendKeys(password);
        $(registrationCheckBox).click();
        $(registrationEmailButton).click();
    }

    public void checkTermOfUse(){
        Login login = new Login();
        $(login.headerLoginButton).click();
        $(termsOfUseButton).click();
    }
}
