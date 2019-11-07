package mel.Tests.Site;

import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.SetDriver;
import mel.TestClasses.Logout;
import mel.TestClasses.Registration;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static com.codeborne.selenide.Selenide.title;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class RegistrationTest extends SetDriver {

    private Registration registration;
    private AdditionalMethods methods;
    private Logout logout;
    private GetUrl getUrl;


    @Test
    public void registration(String[] args) throws IOException {
        methods = new AdditionalMethods();
        getUrl = new GetUrl();
        registration = new Registration();
        logout = new Logout();

        getUrl.driverGet();
        String parentWindowId = getWebDriver().getWindowHandle();
        final Set<String> oldWindowsSet = getWebDriver().getWindowHandles();
        registration.checkTermOfUse();
        methods.moveFocusToTheNewWindow(oldWindowsSet);
        Assert.assertEquals(title(), "Пользовательское соглашение | Мел");
        getWebDriver().switchTo().window(parentWindowId);
        getWebDriver().navigate().refresh();

        // регистрация с нажатием кнопки "Вход" и с невалидными данными в имени
        registration.userRegistrationWithLoginButton("!#$%&'*+-/=?^_`{|}~", "LastName", methods.generateStr(), "12345678");
        //Assert.assertEquals(methods.getTextFromSelector(registration.incorrectFirstNameText), "Пожалуйста, используйте только буквы");
        registration.pressInRegistrationButton();

        // регистрация с невалидными данными в фамилии
        registration.userRegistrationWithoutLoginButton("FirstName", "!#$%&'*+-/=?^_`{|}~", methods.generateStr(), "12345678");
        //Assert.assertEquals(methods.getTextFromSelector(registration.incorrectLastNameText), "Пожалуйста, используйте только буквы");
        registration.pressInRegistrationButton();

        // регистрация пользователя с существующим email
        registration.userRegistrationWithoutLoginButton("FirstName", "LastName", "test153153153@mail.ru", "12345678");
        //Assert.assertEquals(methods.getTextFromSelector(registration.doubleEmailText), "Пользователь с таким email уже существует");
        registration.pressInRegistrationButton();

        // регистрация с невалидными данными в email
        registration.userRegistrationWithoutLoginButton("FirstName", "LastName", "ab(c)d,e:f;g<h>i[jk]l@example.com", "12345678");
        //Assert.assertEquals(methods.getTextFromSelector(registration.incorrectEmailText), "Неверный email");
        registration.pressInRegistrationButton();

        // регистрация с валидными данными
        registration.userRegistrationWithoutLoginButton("FirstName", "LastName", methods.generateStr(), "12345678");
        //Assert.assertEquals(methods.getTextFromSelector(registration.headerUserName), "FirstName LastName");

        logout.exitFromAccount();
        Assert.assertEquals(methods.getTextFromSelector(logout.enterButton), "ВХОД");
    }
}
