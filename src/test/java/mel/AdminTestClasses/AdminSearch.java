package mel.AdminTestClasses;

import com.codeborne.selenide.Condition;
import mel.Helper.AdditionalMethods;
import mel.Helper.SetDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AdminSearch extends SetDriver {

    private By searchString = By.xpath("//div[contains(@class,'search-bar')]//input");
    private By readButtonInSearch = By.xpath("//*[contains(@class,'cancel-button')]/div");
    private By firstTextPublicationInList = By.xpath("//*[contains(@class,'item__title')]");
    private By textInSearch = By.xpath("//div[1]//div[contains(@class,'title')]");
    private By resultText = By.cssSelector(".i-layout__search-result-title");
    private By resultTextInBlogPage = By.xpath("//*[@class='i-layout__search-post-list']/div");
    private By firstBlogInSearchInList = By.xpath("//div[2]//div[contains(@class,'title')]");
    private By textInBlogSearch = By.xpath("//div[2]//div[contains(@class,'title')]");
    private By titleTodayInblogList = By.xpath("//div[contains(text(), 'Сегодня')]");

    public void insertText(String searchLine) {
        $(searchString).sendKeys(searchLine);
        Robot r = null;
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        r.keyPress(KeyEvent.VK_ENTER);
        r.keyRelease(KeyEvent.VK_ENTER);
    }

    public void clickAndCheckClearInputSearsh() {
        $(readButtonInSearch).click();
        $(titleTodayInblogList).shouldHave(Condition.visible);
    }

    public void checkSearchPublication(String type) {
        AdditionalMethods methods = new AdditionalMethods();
        String firstPublicationTitle = null;
        if (type == "publication") {
            firstPublicationTitle = methods.getTextFromSelector(firstTextPublicationInList);
        } else if (type == "blog") {
            firstPublicationTitle = methods.getTextFromSelector(firstBlogInSearchInList);
        }

        insertText(firstPublicationTitle);

        List<WebElement> selectors = null;
        if (type == "publication") {
            selectors = getWebDriver().findElements(textInSearch);
        } else if (type == "blog") {
            selectors = getWebDriver().findElements(textInBlogSearch);
        }

        ArrayList<String> selectorsText = new ArrayList<>();
        for (WebElement selector : selectors) {
            selectorsText.add(selector.getText());
        }
        methods.Wait(5000);
        if (!selectorsText.contains(firstPublicationTitle)) {
            Assert.fail("incorrect search");
        }
    }

    public String getTextInBlogResultText() {
        return $(resultTextInBlogPage).getText();
    }

    public String getTextInResultText() {
        return $(resultText).getText();
    }
}
