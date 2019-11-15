package mel.Helper;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class MailAuthorisation extends SetDriver {

    private AdditionalMethods methods;

    private By mailEmailInput = By.xpath("//input[contains(@id,'identifierId') or text()='Телефон или адрес эл. почты']");
    private By mailPasswordInput = By.xpath("//input[contains(@name,'password')]");
    private By mailBoxLetter = By.cssSelector(".AO >div>div tr:nth-child(1)"); //нестабильный локатор//.AO >div>div tr:nth-child(1)
    private By openLetter = By.xpath("//div[contains(@aria-label,'Показать скрытую часть')]");
    private By mailBoxLetterRegistrationButton = By.xpath("//a[text()=' Зарегистрироваться ']");
    private By mailBoxLetterPasswordRecovery = By.xpath("//*[.=' Задать новый пароль ']");
    private By recoveryPasswordForAdminUserButton = By.partialLinkText("Задать пароль");
    public By textInComplaintReviewLetter = By.xpath("//p[contains(text(),'Пользователь')]");
    public By textMessageReviewLetter = By.xpath("//p[contains(text(),'Текст комментария:')]");
    private By recoveryPasswordButtonInLoginPopup = By.xpath("//span[contains(@class,'recovery-link')]");
    private By recoveryEmailInput = By.xpath("//div[contains(@class,'recovery-request__email')]/descendant::input");
    private By sendRecoveryRequestButton = By.xpath("//div[contains(@class,'recovery-request__send-button')]/descendant::span");

    public void emailAuthorisation(String email, String password) {
        methods = new AdditionalMethods();
        sleep(500);
        $(mailEmailInput).sendKeys(email);
        $(mailEmailInput).sendKeys(Keys.ENTER);
        $(mailPasswordInput).sendKeys(password);
        $(mailPasswordInput).sendKeys(Keys.ENTER);
        sleep(500);
        $(mailBoxLetter).click();
        getWebDriver().navigate().back();
        getWebDriver().navigate().forward();
        sleep(500);
        if (methods.getDisplayedElement(openLetter)) {
            $(openLetter).click();
        }
    }

    public void registrateUser() {
        $(mailBoxLetterRegistrationButton).click();
    }

    public void passwordRecoveryButton() {
        $(mailBoxLetterPasswordRecovery).click();
    }

    public void recoveryPasswordForAdminUser() {
        getWebDriver().navigate().refresh();
        $(mailBoxLetter).click();

        if (methods.getDisplayedElement(openLetter)) {
            $(openLetter).click();
        }
        $(recoveryPasswordForAdminUserButton).click();
    }

    public void recoveryPasswordForUser(String recoveryEmail) {
        $(recoveryPasswordButtonInLoginPopup).click();
        $(recoveryEmailInput).sendKeys(recoveryEmail);
        $(sendRecoveryRequestButton).click();
    }
}