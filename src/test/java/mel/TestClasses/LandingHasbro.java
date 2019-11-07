package mel.TestClasses;


import mel.Helper.AdditionalMethods;
import mel.Helper.SetDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Set;

import static com.codeborne.selenide.Selenide.$;

public class LandingHasbro extends SetDriver {
    private AdditionalMethods methods;

    private By hasbroLogo = By.cssSelector(".b-header__hasbro-logo");
    private By hasbroMelButton = By.cssSelector(".b-header__mel-logo");
    private By firstChoiceGameButton = By.cssSelector(".b-header__chose-game-button-wrapper > div");
    private By firstGame = By.cssSelector(".b-game-types__game-previews-first-row > a:nth-child(2)");
    private By lastGame = By.cssSelector(".b-game-types__game-previews-second-row > a:nth-child(3)");
    private By lastChoiceGameButton = By.cssSelector(".b-image-slider__image-slider-button");
    private By firstWhereBuyButton = By.cssSelector(".l-shops-cards__cards-wrapper > div:nth-child(1) > div > a > div > div.b-single-card__button-wrapper > div");
    private By vkButton = By.cssSelector(".b-footer__learn-more-buttons-wrapper > div:nth-child(1)");
    private By facebookButton = By.cssSelector(".b-footer__learn-more-buttons-wrapper > div:nth-child(2)");
    private By hasbroFooterButton = By.cssSelector(".b-footer__learn-more-buttons-wrapper > div:nth-child(3)");

    public void checkButtons(ArrayList<String> expectedResult) {
        methods = new AdditionalMethods();

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        ArrayList buttons = new ArrayList();
        buttons.add(hasbroLogo);
        buttons.add(hasbroMelButton);
        buttons.add(firstChoiceGameButton);
        buttons.add(firstGame);
        buttons.add(lastGame);
        buttons.add(lastChoiceGameButton);
        buttons.add(firstWhereBuyButton);
        buttons.add(vkButton);
        buttons.add(facebookButton);
        buttons.add(hasbroFooterButton);

        for (int i = 0; i < buttons.size(); i++) {
            if (i == 0 || i == 1 || i == 2){
                String parentWindowId = driver.getWindowHandle();
                final Set<String> oldWindowsSet = driver.getWindowHandles();
                methods.Wait(200);
                $((By) buttons.get(i)).click();
                methods.Wait(200);
                methods.moveFocusToTheNewWindow(oldWindowsSet);
                methods.Wait(2000);
                Assert.assertTrue(driver.getTitle().contains(expectedResult.get(i)),"hasbroLogo or " +
                        "melLogo or first \"Выбрать игру\" button is not working");
                driver.close();
                driver.switchTo().window(parentWindowId);
            }

            else if(i == 3 || i == 4 || i == 5){
                String parentWindowId2 = driver.getWindowHandle();
                final Set<String> oldWindowsSet2 = driver.getWindowHandles();
                jse.executeScript("scroll(0,1200)");
                methods.Wait(500);
                $((By) buttons.get(i)).click();
                methods.Wait(200);
                methods.moveFocusToTheNewWindow(oldWindowsSet2);
                methods.Wait(2000);
                Assert.assertTrue(driver.getTitle().contains(expectedResult.get(i)),"\"Монополия Россия\" game button or " +
                        "\"Пирог в лицо\" game button or second \"Выбрать игру\" button is not working");
                driver.close();
                driver.switchTo().window(parentWindowId2);
            }

            else {
                String parentWindowId2 = driver.getWindowHandle();
                final Set<String> oldWindowsSet2 = driver.getWindowHandles();
                jse.executeScript("scroll(0,3250)");
                methods.Wait(500);
                $((By) buttons.get(i)).click();
                methods.Wait(200);
                methods.moveFocusToTheNewWindow(oldWindowsSet2);
                methods.Wait(2000);
                 Assert.assertTrue(driver.getTitle().contains(expectedResult.get(i)),"vkFooterButton or " +
                        "facebookFooterButton or hasbroFooterButton is not working");
                driver.close();
                driver.switchTo().window(parentWindowId2);
            }
        }
    }
}