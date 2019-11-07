package mel.AdminTestClasses;

import mel.Helper.SetDriver;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class AdminLogout extends SetDriver{

    private By exitButton = By.cssSelector(".b-header__logout-button");

    public void adminLogout() {
        $(exitButton).click();
    }
}
