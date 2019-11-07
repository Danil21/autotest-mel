package mel.Tests.Site;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Config;
import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.MailAuthorisation;
import mel.Helper.SetDriver;
import mel.TestClasses.Login;
import mel.TestClasses.Logout;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AuthorisationTest extends SetDriver {

    private Login autoLogin;
    private static AdditionalMethods methods;
    private Logout logout;
    private GetUrl getUrl;
    private MailAuthorisation mailAuthorisation;

//    @AfterClass
//    public void browserLogs() throws IOException {
//        methods = new AdditionalMethods();
//        getUrl = new GetUrl();
//
//        ArrayList errors = new ArrayList();
//        errors.add("mel.fm/auth - Failed to load resource: the server responded with a status of 404");
//        errors.add("mel.fm/auth - Failed to load resource: the server responded with a status of 422");
//        errors.add("mel.fm/auth/recovery-email - Failed to load resource: the server responded with a status of 404");
//        errors.add("mail.ru");
//        errors.add("adriver");
//        methods.getBrowserLogs(errors, "AuthorisationTest");
//    }

    @Test
    public void authorisation() throws IOException {
        methods = new AdditionalMethods();
        getUrl = new GetUrl();
        autoLogin = new Login();
        logout = new Logout();
        mailAuthorisation = new MailAuthorisation();

        getUrl.driverGet();
        // incorrect email
        autoLogin.authorisation("test153153153@mail.ru", "12345678");
        Assert.assertEquals(methods.getCSSFromSelector(autoLogin.headerLoginButton, new String[]{"display", "font-family"}), "inline | Proxima, sans-serif");
        logout.exitFromAccount();
        // incorrect password
        autoLogin.authorisation("test153153153@mail.ru", "12345679");
        $(autoLogin.incorrectEmailAndPasswordText).shouldHave(Condition.text("Неправильный email или пароль"));
        // correct authorisation
        autoLogin.authorisationAfterIncorrectLogin("test153153153@mail.ru", "12345678");
        Assert.assertEquals(methods.getTextInsideElement(autoLogin.headerUserName), "mail test");
        // exit
        logout.exitFromAccount();
        Assert.assertEquals(methods.getTextInsideElement(logout.enterButton), "вход");
    }

    @Test
    public void recoveryPassword() {
        methods = new AdditionalMethods();
        getUrl = new GetUrl();
        autoLogin = new Login();
        logout = new Logout();
        mailAuthorisation = new MailAuthorisation();

        getUrl.driverGet();
        // recovery password with incorrect email
        autoLogin.passwordRecovery("estendr1@gmail.com");
//        $(autoLogin.incorrectEmailText).shouldBe(Condition.text("Такого пользователя не существует"));
        // recovery password with correct email
        autoLogin.passwordRecoveryAfterIncorrectSend("test153153153@mail.ru");
        $(autoLogin.correctRecoveryPasswordText).shouldHave(Condition.text("Письмо успешно отправлено на указанный вами адрес."));
        autoLogin.clickInCloseButtonInPasswordRecoveryWindow();

        open("https://mail.google.com");
        mailAuthorisation.emailAuthorisation("test153153153@gmail.com", "knock705b");

        final Set<String> oldWindowsSet = getWebDriver().getWindowHandles();

        mailAuthorisation.passwordRecoveryButton();
        methods.moveFocusToTheNewWindow(oldWindowsSet);
        // set new short password
        autoLogin.setNewPassword("123456", "123456");
        $(autoLogin.shortPasswordText).shouldHave(Condition.text("Пароль должен содержать минимум 8 символо"));
        // set new different passwords
        autoLogin.setNewPasswordAfterIncorrectEnter("12345678", "12345679");
        $(autoLogin.differentPasswordText).shouldHave(Condition.text("Пароли не совпадают"));
        // set new correct password
        autoLogin.setNewPasswordAfterIncorrectEnter("12345678", "12345678");
        $(autoLogin.successRecoveryPasswordText).shouldHave(Condition.text("Пароль был успешно сохранен."));
        // authorisation after recovery password
        autoLogin.clickInEnterButton();
        autoLogin.authorisationAfterRecoveryPassword("test153153153@mail.ru", "12345678");
        sleep(2000);
        autoLogin.checkPageRefresh();
        Assert.assertEquals(methods.getTextInsideElement(autoLogin.headerUserName), "mail test");
    }
}
