package mel.TestClasses;

import mel.Helper.SetDriver;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Login extends SetDriver {

    public By headerLoginButton = By.xpath("//*[contains(text(),'вход')]");
    public By emailLoginButton = By.xpath("//span[contains(text(),'почта')]");

    private By loginEmainInput = By.xpath("//div[contains(@class,'email__login-email-input')]//div//input");
    private By loginEmainInputReact = By.xpath("//input[contains(@name,'email')]");

    private By loginPasswordInput = By.xpath("//div[contains(@class,'email__login-password-input')]//div//input");
    private By loginPasswordInputReact = By.xpath("//input[contains(@name,'password')]");

    private By loginEnterButton = By.xpath("//span[contains(text(),'Войти')]");
    private By loginEnterButtonReact = By.xpath("//span[text()='войти' and @class='button__label button__label_big']");

    public  By headerUserName = By.cssSelector(".user-info__name");
    private By passwordRecoveryButton = By.xpath("//span[contains(text(),'Восстановление')]");
    private By passwordRecoveryEmailInput = By.cssSelector(".b-password-recovery-request__email-input > div > input");
    private By passwordRecoverySendButton = By.cssSelector(".b-password-recovery-request__send-button > div");
    private By passwordRecoveryCloseButton = By.xpath("//div[contains(@class,'b-password-recovery-request__page-done')]//span[contains(text(),'Закрыть')]");
    private By newPassword = By.cssSelector(".b-password-recovery__password-input > div > input");
    private By secondNewPassword = By.cssSelector(".b-password-recovery__same-password-input > div > input");
    private By saveButton = By.cssSelector(".b-password-recovery__save-button > div");
    public  By successRecoveryPasswordText = By.xpath("//*[.='Пароль был успешно сохранен.']");
    private By enterButton = By.xpath("//*[.='Вход на сайт']");
    // private   By incorrectPasswordText = By.xpath("//*[text()='Неверный пароль' and @class='g-input__hint']"); //пока не используется
    public  By incorrectEmailAndPasswordText = By.xpath("//*[@class='b-auth-email__error-message']");
    public By  incorrectEmailText = By.xpath("//*[text()='Такого пользователя не существует' and @class='g-input__hint']");
    public  By correctRecoveryPasswordText = By.xpath("//*[text()='Письмо успешно отправлено на указанный вами адрес.' and @class='b-password-recovery-request__message']");
    public  By shortPasswordText = By.xpath("//*[text()='Пароль должен содержать минимум 8 символов' and @class='g-input__hint']");
    public  By differentPasswordText = By.xpath("//*[text()='Пароли не совпадают' and @class='g-input__hint']");
    private By freshOnMelBlock = By.cssSelector(".b-pb-article-fresh");

    public void authorisation(String email, String password) {
        $(headerLoginButton).click();
        $(emailLoginButton).click();
        $(loginEmainInput).sendKeys(email);
        $(loginPasswordInput).sendKeys(password);
        $(loginEnterButton).click();
    }

    public void authorisationInPageReact(String email, String password) {
        $(headerLoginButton).click();
        $(emailLoginButton).click();
        $(loginEmainInputReact).sendKeys(email);
        $(loginPasswordInputReact).sendKeys(password);
        $(loginEnterButtonReact).click();
    }

    public void automationIsNotMain(String email, String password) {
        $(emailLoginButton).click();
        $(loginEmainInput).sendKeys(email);
        $(loginPasswordInput).sendKeys(password);
        $(loginEnterButton).click();
    }
    public void passwordRecovery(String email) {
        $(headerLoginButton).click();
        $(emailLoginButton).click();
        $(passwordRecoveryButton).click();
        $(passwordRecoveryEmailInput).sendKeys(email);
        $(passwordRecoverySendButton).click();
    }

    public void passwordRecoveryAfterIncorrectSend(String email) {
        $(passwordRecoveryEmailInput).clear();
        $(passwordRecoveryEmailInput).sendKeys(email);
        $(passwordRecoverySendButton).click();
    }

    public void clickInCloseButtonInPasswordRecoveryWindow() {
        $(passwordRecoveryCloseButton).click();
    }

    public void clickInEnterButton() {
        $(enterButton).click();
    }

    public void setNewPassword(String password, String samePassword) {
        $(newPassword).sendKeys(password);
        $(secondNewPassword).sendKeys(samePassword);
        $(saveButton).click();
    }

    public void authorisationAfterRecoveryPassword(String email, String password) {
        $(loginEmainInput).sendKeys(email);
        $(loginPasswordInput).sendKeys(password);
        $(loginEnterButton).click();

    }

    public void authorisationAfterIncorrectLogin(String email, String password) {
        $(loginEmainInput).clear();
        $(loginPasswordInput).clear();
        $(loginEmainInput).sendKeys(email);
        $(loginPasswordInput).sendKeys(password);
        $(loginEnterButton).click();
    }

    public void setNewPasswordAfterIncorrectEnter(String password, String samePassword) {
        $(newPassword).clear();
        $(secondNewPassword).clear();
        $(newPassword).sendKeys(password);
        $(secondNewPassword).sendKeys(samePassword);
        $(saveButton).click();
    }

    public String getFontFamilyValue(){
        String str = $(freshOnMelBlock).getCssValue("font-family");
        return str;
    }

    public void checkPageRefresh() {
        boolean refresh = $(headerLoginButton).isDisplayed();
        if (refresh) {
            getWebDriver().navigate().refresh();
        }

    }
}