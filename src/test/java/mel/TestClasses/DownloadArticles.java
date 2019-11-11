package mel.TestClasses;

import com.codeborne.selenide.WebDriverRunner;
import mel.Helper.SetDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

public class DownloadArticles extends SetDriver {

    private By moreArticleButton = By.cssSelector(".b-pb-frontpage__more-publications-button_scope_mobile > div");
    public By articles = By.cssSelector(".i-layout__content");
    private By articleInPublicationPage = getRandomArticleInPublication();
    private By articleInRubricPage = getRandomArticleInRubric();

    public void pressInArticle(By selector) {
        WebDriver driver = WebDriverRunner.getWebDriver();
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("scroll(0," + $(selector).getLocation().y + ")");
        $(selector).click();
    }

    public By getRandomArticle() {
        final int min = 75;
        final int max = 85;
        final int randomArticleNumber = rnd(min, max);

        return By.cssSelector("div.b-pb-frontpage__grid > div:nth-child(" + randomArticleNumber + ")");
    }

    private By getRandomArticleInRubric() {
        final int min = 60;
        final int max = 95;
        final int randomArticleNumber = rnd(min, max);

        return By.xpath("//div[" + randomArticleNumber + "][contains(@class,'b-pb-frontpage__card')]");
    }

    private By getRandomArticleInPublication() {
        final int min = 8;
        final int max = 18;
        final int randomArticleNumber = rnd(min, max);

        return By.xpath("//div[" + randomArticleNumber + "][contains(@class,'b-pb-frontpage__card_scope_desktop')]");
    }

    public void pressInArticleMore() {
        $(moreArticleButton).click();
    }

    public void checkButtonInPagePublication() {
        $(moreArticleButton).click();
        $(articleInPublicationPage).click();
    }

    public void checkButtonInPageRubric() {
        $(moreArticleButton).click();
        $(articleInRubricPage).click();
    }

    private static int rnd(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
}
