package mel.AdminTestClasses;

import mel.Helper.SetDriver;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class AdminLogin extends SetDriver{

    private By emailInput = By.xpath("//div[@class='i-layout__email-input']//input");
    private By passwordInput = By.cssSelector(".i-layout__password-input > div > input");
    private By enterButton = By.cssSelector(".i-layout__login-button");
    public By incorrectEmailAndPasswordText = By.cssSelector(".i-layout__email-input > div > div");
    public By incorrectEmailText = By.cssSelector(".i-layout__email-input > div > div");

    public void adminAuthorisation(String email, String password) {
        $(emailInput).sendKeys(email);
        $(passwordInput).sendKeys(password);
        $(enterButton).click();
    }

    public void clearFields(){
        $(emailInput).clear();
        $(passwordInput).clear();
    }
}
