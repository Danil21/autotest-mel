package mel.TestClasses;

import mel.Helper.AdditionalMethods;
import mel.Helper.SetDriver;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.NoSuchElementException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class Profile extends SetDriver {
    private AdditionalMethods methods = new AdditionalMethods();
    //HeaderDropdown
    private By headerDropdownIcon = By.cssSelector(".right-buttons__user-info-icon");
    private By profileButtonInList = By.cssSelector(".user-dropdown-menu__item:nth-child(3) > a");
    private By logoutButtonInList = By.cssSelector(".user-dropdown-menu > button > span");
    private By blogButtonInList = By.cssSelector(".user-dropdown-menu__item:nth-child(1) > a");

    //ProfileTab
    private By firstnameInput = By.cssSelector(".b-profile__input-firstname .g-input__input");
    private By lastnameInput = By.cssSelector(".b-profile__input-lastname > input");
    private By saveButton = By.cssSelector(".b-profile__save");
    private By userNameInput = By.cssSelector(".b-profile__input-displayname > input");
    private By aboutTextarea = By.cssSelector(".b-profile__about > div > textarea");
    private By emailInput = By.cssSelector(".b-profile__input-email > input");
    private By phoneInput = By.cssSelector(".b-profile__input-phone > input");
    private By birthdateInput = By.cssSelector(".b-profile__input-birthdate > input");
    private By selectRoleDropdownIcon = By.cssSelector(".b-profile__select-role> .g-dropdown__opener");
    private By selectGenderDropdownIcon = By.cssSelector(".b-profile__select-gender> .g-dropdown__opener");
    private By roleInList = By.cssSelector(".b-profile__select-role.g-dropdown_select.g-dropdown_icon-position_right > div.g-dropdown__content > div > div:nth-child(2)");
    private By genderInList = By.cssSelector(".b-profile__select-gender.g-dropdown_select.g-dropdown_icon-position_right > div.g-dropdown__content > div > div:nth-child(2)");
    public By roleInput = By.cssSelector(".b-profile__select-role >.g-dropdown__opener");
    public By genderInput = By.cssSelector(".b-profile__select-gender >.g-dropdown__opener");
    //Blog
    public By authorNameInBlog = By.cssSelector(".b-pb-author__name");
    public By authorQuoteInBlog = By.cssSelector(".b-pb-author__quote");
    //Avatar
    //кнопка загрузки аватарки
    public By downloadAvatarButton = By.xpath("//div[text()='Загрузить фото']");
    //загруженная аватарка профиле
    private By imageClass = By.cssSelector(".b-profile__avatar-image-wrapper > img");
    //загруженная аватарка в шапке
    private By headerImageClass = By.xpath("//*[contains(@class, 'user-info')]//img");
    //удаление аватарки
    private By deleteAvatarButton = By.cssSelector(".b-profile__avatar-control-btn");
    //
    private By closeButtonInLoginPopup = By.cssSelector(".g-modal__close-icon > div");
    // дефолтная заклушка аватарки в профиле
    private By avatarIcon = By.xpath("//*[contains(@class, 'g-icon_img_no-user-big')]");
    //дефолтная заглушка на месте аватарки
    private By headerAvatarIconDownload = By.xpath("//*[contains(@class, 'svg-icon user-info__photo user-info__photo_no-user')]");

    //SocialNetworksTab
    private By socialNetworksTab = By.cssSelector(".g-tab__tabs > div:nth-child(2)");
    private By siteUrlInput = By.cssSelector(".b-profile__input-siteurl > input");
    private By fbInput = By.cssSelector(".b-profile__input-fb > input");
    private By vkInput = By.cssSelector(".b-profile__input-vk > input");
    private By twitterInput = By.cssSelector(".b-profile__input-tw > input");
    private By saveButtonInSocialNetworksTab = By.cssSelector(".b-profile__save");
    public By siteUrlInBlog = By.xpath("//a[@class='b-pb-author__url']");
    public By fbUrlInBlog = By.xpath("//a[@class='b-pb-author__url b-pb-author__url__url-facebook']");
    public By vkUrlInBlog = By.xpath("//a[@class='b-pb-author__url b-pb-author__url__url-vk']");
    public By twitterUrlInBlog = By.xpath("//a[@class='b-pb-author__url b-pb-author__url__url-twitter']");

    private void clickOnHeaderDropdownIcon() {
        $(headerDropdownIcon).scrollTo().click();
    }


    public String getFirstname() {
        String str = $(firstnameInput).getAttribute("value");
        return str;
    }

    public String getLastname() {
        String str = $(lastnameInput).getAttribute("value");
        return str;
    }

    public void clickOnSaveButton() {
        $(saveButton).scrollIntoView(true).click();
    }

    public String getEmail() {
        String str = $(emailInput).getAttribute("value");
        return str;
    }

    public String getPhone() {
        String str = $(phoneInput).getAttribute("value");
        return str;
    }

    public String getBirthdate() {
        String str = $(birthdateInput).getAttribute("value");
        return str;
    }

    public void clickOnCloseButton() {
        $(closeButtonInLoginPopup).click();
    }

    private void clearInputs() {
        $(firstnameInput).clear();
        $(lastnameInput).clear();
    }

    public void editFirstnameLastname(String firstname, String lastname) {
        clearInputs();
        $(firstnameInput).click();
        $(firstnameInput).sendKeys(firstname);
        $(lastnameInput).click();
        $(lastnameInput).sendKeys(lastname);
        clickOnSaveButton();
    }

    public void editUserNameAndAbout(String userName, String Textarea) {
        $(userNameInput).click();
        $(userNameInput).sendKeys(userName);
        $(aboutTextarea).click();
        $(aboutTextarea).sendKeys(Textarea);
        clickOnSaveButton();
    }

    public void changeEmail(String email) {
        $(emailInput).click();
        $(emailInput).clear();
        $(emailInput).sendKeys(email);
        clickOnSaveButton();
    }

    public void logout() {
        clickOnHeaderDropdownIcon();
        $(logoutButtonInList).click();
    }

    public void editPhoneAndBirthdate(String phone, String birthdate) {
        $(phoneInput).click();
        $(phoneInput).sendKeys(phone);
        $(birthdateInput).sendKeys(birthdate);
        clickOnSaveButton();
    }

    public void selectRoleAndGender() {
        $(selectRoleDropdownIcon).click();
        $(roleInList).click();
        $(selectGenderDropdownIcon).click();
        $(genderInList).click();
        clickOnSaveButton();
    }

    public void uploadAvatar() {
        //AdditionalMethods methods = new AdditionalMethods();

        $(downloadAvatarButton).click();
        methods.Wait(2000);
        methods.imageDownload("C:\\1.jpg");
        methods.Wait(3000);
    }

    //check download avatar
    public String getImageClass(String type) {
        String tagName = null;

        if (type == "header") {
            tagName = $(headerImageClass).getTagName();
        } else if (type == "profile") {
            tagName = $(imageClass).getTagName();
        }
        return tagName;
    }

    public boolean checkDisplayedUserIcon() {
        try {
            return $(avatarIcon).isDisplayed() && $(headerAvatarIconDownload).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void deleteAvatar() {
        $(deleteAvatarButton).click();
        clickOnSaveButton();
    }

    public void addLinkInProfile(String SiteUrl, String fbText, String vkText, String twitterText) {
        $(socialNetworksTab).scrollIntoView(false);
        $(socialNetworksTab).click();
        $(siteUrlInput).click();
        $(siteUrlInput).sendKeys(SiteUrl);
        $(fbInput).click();
        $(fbInput).sendKeys(fbText);
        $(vkInput).click();
        $(vkInput).sendKeys(vkText);
        $(twitterInput).click();
        $(twitterInput).sendKeys(twitterText);
        $(saveButtonInSocialNetworksTab).click();
    }

    public void sendValueInInput(String type, String value) {

        switch (type) {
            case "firstName":
                clearAndSendValueInInput(firstnameInput, value);
                break;
            case "lastName":
                clearAndSendValueInInput(lastnameInput, value);
                break;
            case "userName":
                clearAndSendValueInInput(userNameInput, value);
                break;
            case "email":
                clearAndSendValueInInput(emailInput, value);
                break;
            case "phone":
                clearAndSendValueInInput(phoneInput, value);
                break;
            case "birthdate":
                clearAndSendValueInInput(birthdateInput, value);
                break;
            default:
                Assert.fail("Incorrect type");
        }
        clickOnSaveButton();
    }

    private void clearAndSendValueInInput(By selector, String value) {
        $(selector).clear();
        sleep(500);
        $(selector).sendKeys(value);
    }

    public String getHint(int value) {
        return $(By.xpath("//*[@class='b-profile__content']/div[" + value + "]//div[@class='g-input__hint']")).innerText();
    }
}

