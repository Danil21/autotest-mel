package mel.TestClasses;

import mel.Helper.AdditionalMethods;
import mel.Helper.SetDriver;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.Set;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.title;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


public class AdvertisingPage extends SetDriver {
    private By mediakitButton = By.xpath("//*[@class='l-advertising-proposal__content']/a[1]");
    private By priceButton = By.xpath("//*[@class='l-advertising-proposal__content']/a[2]");

    public void checkAdvertising(String mediakitTitle, String priceTitle) {
        AdditionalMethods methods = new AdditionalMethods();

        String parentWindowId = getWebDriver().getWindowHandle();
        final Set<String> oldWindowsSet = getWebDriver().getWindowHandles();

        $(mediakitButton).click();
        methods.moveFocusToTheNewWindow(oldWindowsSet);
        Assert.assertEquals(title(), mediakitTitle);
        getWebDriver().close();
        getWebDriver().switchTo().window(parentWindowId);

        String secondParentWindowId = getWebDriver().getWindowHandle();
        final Set<String> secondOldWindowsSet = getWebDriver().getWindowHandles();

        $(priceButton).click();
        methods.moveFocusToTheNewWindow(secondOldWindowsSet);
        Assert.assertEquals(title(), priceTitle);
        getWebDriver().close();
        getWebDriver().switchTo().window(secondParentWindowId);

    }
}
