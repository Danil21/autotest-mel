package mel.Tests.Site;

import com.codeborne.selenide.Condition;
import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.SetDriver;
import mel.TestClasses.Login;
import mel.TestClasses.Logout;
import mel.TestClasses.Profile;
import mel.TestClasses.Registration;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ProfileTest extends SetDriver {

    private Registration registration;
    private AdditionalMethods methods;
    private Profile profile;
    private GetUrl getUrl;
    private Logout openDropdown;

    @Test(priority = 1)
    public void profile() {
        methods = new AdditionalMethods();
        getUrl = new GetUrl();
        profile = new Profile();
        registration = new Registration();
        openDropdown = new Logout();
        Login autoLogin = new Login();

        getUrl.driverGet();
        registration.userRegistrationWithLoginButton("testname", "testlastname", methods.generateStr(), "12345678");
        //methods.closeNotificationCookie();

        openDropdown.openProfile();
        Assert.assertEquals(profile.getFirstname(), "testname");
        Assert.assertEquals(profile.getLastname(), "testlastname");
        profile.editFirstnameLastname("newtestname", "newtestlastname");
        Assert.assertEquals(profile.getFirstname(), "newtestname");
        Assert.assertEquals(profile.getLastname(), "newtestlastname");
        //Assert.assertEquals(methods.getTextFromSelector(profile.headerUserName),"newtestname newtestlastname");
        methods.Wait(500);
        openDropdown.openBlog();
        openDropdown.openProfile();
        profile.editUserNameAndAbout("username", "Люблю читать мел");
        //Assert.assertEquals(methods.getTextFromSelector(profile.headerUserName),"username");
        methods.Wait(500);
        openDropdown.openBlog();
        $(profile.authorNameInBlog).shouldHave(Condition.text("username"));
        $(profile.authorQuoteInBlog).shouldHave(Condition.text("Люблю читать мел"));
        methods.Wait(500);
        openDropdown.openProfile();

        String str5 = profile.getEmail();
        profile.changeEmail(methods.generateStr());
        String str6 = profile.getEmail();
        methods.Wait(500);
        profile.logout();

        autoLogin.authorisation(str5, "12345678");
        profile.clickOnCloseButton();
        autoLogin.authorisation(str6, "12345678");
        openDropdown.openProfile();

        profile.editPhoneAndBirthdate("89165554433", "01.01.1990");
        Assert.assertEquals(profile.getPhone(), "89165554433");
        Assert.assertEquals(profile.getBirthdate(), "01.01.1990");
        methods.Wait(200);
        profile.selectRoleAndGender();
        Assert.assertEquals(methods.getTextFromSelector(profile.roleInput), "Учитель");
        Assert.assertEquals(methods.getTextFromSelector(profile.genderInput), "Женский");

        methods.scroll("scroll(0,0)");
        sleep(1000);
        profile.uploadAvatar();
        profile.clickOnSaveButton();
        sleep(2000);
        methods.scroll("scroll(0,0)");
        Assert.assertEquals(profile.getImageClass("header"), "img");
        Assert.assertEquals(profile.getImageClass("profile"), "img");
        profile.deleteAvatar();
        methods.Wait(1500);
        Assert.assertTrue(profile.checkDisplayedUserIcon());
        Assert.assertEquals(methods.getTextFromSelector(profile.downloadAvatarButton), "Загрузить фото");
        profile.addLinkInProfile("mel.fm", "facebook.com", "vk.com", "twitter.com");
        methods.Wait(1500);
        openDropdown.openBlog();

        Assert.assertEquals(methods.getHrefFromSelector(profile.siteUrlInBlog), "http://mel.fm/");
        Assert.assertEquals(methods.getHrefFromSelector(profile.fbUrlInBlog), "http://facebook.com/");
        Assert.assertEquals(methods.getHrefFromSelector(profile.vkUrlInBlog), "http://vk.com/");
        Assert.assertEquals(methods.getHrefFromSelector(profile.twitterUrlInBlog), "http://twitter.com/");
    }

    @Test(priority = 2)
    public void checkInvalidValues() {
        methods = new AdditionalMethods();
        getUrl = new GetUrl();
        profile = new Profile();
        openDropdown = new Logout();
        Login login = new Login();
        registration = new Registration();

        String email = methods.generateStr();
        String newEmail = methods.generateStr();
        String password = "12345678";

        getUrl.driverGet();
        getWebDriver().manage().deleteAllCookies();
        getWebDriver().navigate().refresh();
        registration.userRegistrationWithLoginButton("testname", "testlastname", email, password);

        openDropdown.openProfile();
        profile.sendValueInInput("firstName", "12312312123");
        Assert.assertEquals(profile.getHint(1), "Пожалуйста, используйте только буквы");

        profile.sendValueInInput("firstName", "firstName");
        profile.sendValueInInput("lastName", "1231");
        Assert.assertEquals(profile.getHint(2), "Пожалуйста, используйте только буквы");

        profile.sendValueInInput("firstName", "firstNameFirstNameFirstName");
        Assert.assertEquals(profile.getHint(1), "Не более 20 символов");

        profile.sendValueInInput("firstName", "firstName");
        profile.sendValueInInput("lastName", "lastNameLastNameLastName");
        Assert.assertEquals(profile.getHint(2), "Не более 20 символов");

        profile.sendValueInInput("firstName", "firstName");
        profile.sendValueInInput("lastName", "lastName");
        profile.sendValueInInput("userName", "userNameUserNameUserNameUserNameuserNameUserNameUserNameUserNameuserNameUserNameUserNameUserName");
        Assert.assertEquals(profile.getHint(3), "Не более 50 символов");

        profile.sendValueInInput("userName", "userName");
        profile.sendValueInInput("email", "incorrectEmail");
        Assert.assertEquals(profile.getHint(5), "Введён некорректный адрес электронной почты");

        profile.sendValueInInput("email", newEmail);
        profile.sendValueInInput("phone", "test");
        Assert.assertEquals(profile.getHint(6), "Неверный формат телефона");

        profile.sendValueInInput("phone", "123123123");
        profile.sendValueInInput("birthdate", "test");
        Assert.assertEquals(profile.getHint(8), "Некорректная дата");

        profile.sendValueInInput("birthdate", "24.12.1995");
        profile.sendValueInInput("firstName", " ");

        Assert.assertEquals(profile.getHint(1), "Не заполнено обязательное поле");

        profile.sendValueInInput("firstName", "firstName");

        profile.sendValueInInput("email", " ");

        Assert.assertEquals(profile.getHint(5), "Не заполнено обязательное поле");

        profile.sendValueInInput("email", newEmail);

        openDropdown.exitFromAccount();
        login.authorisation(email, password);
        Assert.assertEquals(methods.getTextFromSelector(login.incorrectEmailAndPasswordText), "Неправильный email или пароль");
    }
}
