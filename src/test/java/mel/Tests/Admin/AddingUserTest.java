package mel.Tests.Admin;

import MelAppium.resources.config;
import mel.AdminTestClasses.AdminAddingUser;
import mel.AdminTestClasses.AdminLogin;
import mel.AdminTestClasses.AdminLogout;
import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.MailAuthorisation;
import mel.Helper.SetDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;

public class AddingUserTest extends SetDriver{

    @Test
    public void addingAdminUser() {
        AdditionalMethods methods = new AdditionalMethods();
        GetUrl getUrl = new GetUrl();
        AdminLogin adminLogin = new AdminLogin();
        MailAuthorisation mailAuthorisation = new MailAuthorisation();
        AdminLogout logout = new AdminLogout();
        AdminAddingUser addingUser = new AdminAddingUser();

        // получение рандомной почты для регистрируемого пользователя
        String UserEmail = methods.generateStr();
        getUrl.driverGetAdminUrl();
        adminLogin.adminAuthorisation(config.getTestProperty("adminLogin"),config.getTestProperty("adminPass"));
        sleep(2000);
        // заполнение полей регистрируемого пользователя
        addingUser.addingNewUser();
        Assert.assertTrue(addingUser.isUserSaveButtonDisabled());
        addingUser.fillingNewUserFields("Name", "SurName","test@");
        Assert.assertEquals(methods.getTextFromSelector(addingUser.incorrectEmailMessage), "Неверный email");
        addingUser.clearNewUserFields();
        addingUser.fillingNewUserFields("Name", "SurName",UserEmail);

        // авторизация на gmail.com
        open("https://mail.google.com/");
        mailAuthorisation.emailAuthorisation("test153153153@gmail.com", "knock705b");
        sleep(3000);

        // сохранение значений текущего окна
        final Set<String> oldWindowsSet = getWebDriver().getWindowHandles();
        sleep(3000);
        // переход по ссылке для окончания регистрации
        mailAuthorisation.registrateUser();
        // перенос фокуса на первое окно
        methods.moveFocusToTheNewWindow(oldWindowsSet);
        sleep(500);

        // сравнение полей, вводимых при первичной регистрации
        Assert.assertEquals(addingUser.getRegistrationName(), "Name");
        Assert.assertEquals(addingUser.getRegistrationSurname(), "SurName");
        Assert.assertEquals(addingUser.getRegistrationEmail(), UserEmail);
        Assert.assertEquals(getWebDriver().getTitle(), "Регистрация пользователя");

        // ввод данных на странице регистрации
        addingUser.enterPasswordAndConfirm("1234567","1234567");
        Assert.assertEquals(methods.getTextFromSelector(addingUser.incorrectPassword), "Пароль должен содержать минимум 8 символов");

        addingUser.clearPasswordFields();
        addingUser.enterPasswordAndConfirm("12345678","12345679");
        Assert.assertEquals(methods.getTextFromSelector(addingUser.adminPasswordsDontMatch), "Пароли не совпадают");

        addingUser.clearFieldNewRegistrationEmail();
        addingUser.clearPasswordFields();
        addingUser.sendNewRegistrationEmail("test@");
        addingUser.enterPasswordAndConfirm("12345678","12345678");
        Assert.assertEquals(methods.getTextFromSelector(addingUser.adminIncorrectRegistrationEmail), "Неверный email");

        addingUser.clearFieldNewRegistrationEmail();
        addingUser.clearPasswordFields();
        addingUser.sendNewRegistrationEmail("test@example.com");
        addingUser.enterPasswordAndConfirm("12345678","12345678");
        sleep(1500);
        Assert.assertEquals(methods.getTextFromSelector(addingUser.adminIncorrectRegistrationEmail), "Пользователь с таким email уже существует");

        addingUser.clearFieldNewRegistrationEmail();
        addingUser.sendNewRegistrationEmail(UserEmail);
        addingUser.enterPasswordAndConfirm("12345678","12345678");
        sleep(500);

        // проверка перехода на страницу публикаций после окончания регистрации
        Assert.assertEquals(getWebDriver().getTitle(), "Публикации");

        // проверка восстановления пароля у пользователя
        logout.adminLogout();
        addingUser.checkRecoveryPassword("str@str.com");
        sleep(1000);
        Assert.assertEquals(methods.getTextFromSelector(adminLogin.incorrectEmailText), "Неверный email");
        getWebDriver().navigate().back();
        addingUser.checkRecoveryPassword(UserEmail);
        open("https://mail.google.com/");
        sleep(1000);
        final Set<String> oldWindowsSet2 = getWebDriver().getWindowHandles();
        sleep(2000);
        mailAuthorisation.recoveryPasswordForAdminUser();
        methods.moveFocusToTheNewWindow(oldWindowsSet2);
        sleep(2000);
        Assert.assertEquals(getWebDriver().getTitle(), "Восстановление пароля");
        Assert.assertEquals(methods.getTextFromSelector(addingUser.adminRecoveryEmail), UserEmail);
        String newPassword = "123456789";
        addingUser.enterNewPasswordAndConfirm(newPassword, "12345678");
        Assert.assertEquals(methods.getTextFromSelector(addingUser.adminPasswordsDontMatch), "Пароли должны совпадать");
        addingUser.clearPasswordFields();
        addingUser.enterNewPasswordAndConfirm(newPassword, newPassword);
        adminLogin.adminAuthorisation(UserEmail,newPassword);
        sleep(1000);
        Assert.assertEquals(getWebDriver().getTitle(), "Публикации");

        //редактирование пользователя
        sleep(1000);
        addingUser.clickInUsersButton();
        addingUser.editNewUserFields();
        addingUser.clearEditUserFields();
        addingUser.sendNewValuesInEditUserWindow("Admin2", "Admin2", "test2@example.com");
        sleep(1000);
        addingUser.editNewUserFields();
        sleep(2000);
        Assert.assertEquals(addingUser.getEditUserFirstName(), "Admin2");
        Assert.assertEquals(addingUser.getEditUserLastName(), "Admin2");
        Assert.assertEquals(addingUser.getEditUserEmail(), "test2@example.com");

        addingUser.clearEditUserFields();
        addingUser.sendNewValuesInEditUserWindow("Admin", "Admin", "test@example.com");
        sleep(2000);
        Assert.assertEquals(methods.getTextFromSelector(addingUser.incorrectEmailMessage), "Пользователь с таким email уже существует");
        addingUser.closeEditUserPopup();
        sleep(500);

        Assert.assertEquals(methods.getTextFromSelector(addingUser.addedUser), "SurName Name");
        sleep(1000);
        addingUser.deleteAddedUser();
        sleep(2000);
        Assert.assertEquals(url(), getUrl.driverGetAdminUrlStr()+"users");
    }
}