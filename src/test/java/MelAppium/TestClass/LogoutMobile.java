package MelAppium.TestClass;

import mel.Helper.SetDriver;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LogoutMobile extends SetDriver {

    public By  headerMenu = By.xpath("//button[contains(@class,'hamburger-button')]");
    private By exitButtonInMenu = By.xpath("//*[contains(text(),'Выход')]");
    private By profileButtonInMenu = By.xpath("//a[@href='/profile']");
    private By blogButtonInMenu = By.xpath("//a[@href='/blog']");
    private By profileInHeader = By.xpath("//button[contains(@class,'top-mobile-menu__profile-button')]");
    public By comeInSiteButton = By.xpath("//button[contains(@class,'top-mobile-menu__login-button')]");

    public void mobileProfile() {
        $(headerMenu).click();
        $(profileInHeader).click();
    }

    public void mobileOpenBlog() {
        $(headerMenu).click();
        $(profileInHeader).click();
        $(blogButtonInMenu).click();
    }

    public void mobileOpenProfile() {
        $(headerMenu).click();
        $(profileInHeader).click();
        $(profileButtonInMenu).click();
    }

    public void mobileExitFromAccount() {
        $(headerMenu).click();
        $(profileInHeader).click();
        $(exitButtonInMenu).click();
    }

    public void mobileExit() {
        $(exitButtonInMenu).click();
    }
}
