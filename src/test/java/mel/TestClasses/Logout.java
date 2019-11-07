package mel.TestClasses;

import mel.Helper.SetDriver;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;


public class Logout extends SetDriver{

    public By headerDropdown = By.cssSelector(".right-buttons__user-info-button");
    private By exitButtonInDropdown = By.xpath("//*[contains(text(),'Выход')]");
    public By enterButton = By.xpath("//*[contains(text(),'вход')]");
    private By profileButtonDropdown = By.cssSelector(".user-dropdown-menu__item:nth-child(3) > a");
    private By blogButtonDropdown =By.xpath("//a[@href='/blog']");

    public void openBlog(){
        $(headerDropdown).scrollTo().click();
        $(blogButtonDropdown).click();
    }

    public void openProfile(){
        $(headerDropdown).click();
        $(profileButtonDropdown).click();
    }

    public void exitFromAccount() {
        $(headerDropdown).click();
        $(exitButtonInDropdown).click();
    }

    public void exitToAuthorisationSocial() {
        $(headerDropdown).click();
        $(exitButtonInDropdown).click();
    }
}

