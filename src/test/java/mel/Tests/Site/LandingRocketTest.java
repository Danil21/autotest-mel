package mel.Tests.Site;

import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.SetDriver;
import mel.TestClasses.LandingRocket;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.title;

public class LandingRocketTest extends SetDriver{

    private AdditionalMethods methods;
    private GetUrl getUrl;
    private LandingRocket rocket;

    @Test
    public void landingRocket() throws IOException {
        rocket = new LandingRocket();
        methods = new AdditionalMethods();
        getUrl = new GetUrl();

        getUrl.driverGetCurrentUrl("rockettest/");

        ArrayList<String> expectedResult = new ArrayList();
        expectedResult.add("easy2rider2@gmail.com");
        expectedResult.add("knock705b");
        expectedResult.add("«Мел» и «Рокетбанк» предлагают вернуться во времена, когда вам платили за оценки");
        expectedResult.add("89164948378");
        expectedResult.add("123qwe");
        expectedResult.add("89161604481");
        expectedResult.add("Контрольная работа");

        rocket.sharingFb(expectedResult);
        rocket.sharingOk(expectedResult);

        rocket.checkRocketBackgroundImage("/images/n-site/n-site/l-rocket-test/image_background.png");

        Assert.assertEquals(title(),"Контрольная работа");
        Assert.assertEquals(methods.getTextFromSelector(rocket.introText),"«Мел» и «Рокетбанк» предлагают вам вспомнить прекрасные " +
                "школьные годы и проверить свои знания в контрольных работах по истории, литературе и биологии. " +
                "К сожалению, вы не успели на розыгрыш призов, однако получить удовольствие от наших тестов всё ещё " +
                "можно — пусть оно и не будет выражено в твёрдой валюте.");

        rocket.clickOnHeaderLogo();
        Assert.assertEquals(title(),"«Мел». Полезное, понятное и удобное онлайн-медиа про" +
                " образование и детей");
        //driver.navigate().back();

        rocket.checkTwitterSharingButton("«Мел» и «Рокетбанк» предлагают вернуться во времена, когда вам платили за оценки");

        ArrayList<String> expectedResultArticle = new ArrayList();
        expectedResultArticle.add("Денщик или ямщик: контрольная работа по истории | Мел");
        expectedResultArticle.add("Майор Прыщ и Капитан Баклан: контрольная работа по литературе | Мел");
        expectedResultArticle.add("Фильтруй и выделяй: контрольная работа по биологии | Мел");

        rocket.checkArticleCards(expectedResultArticle);
        JavascriptExecutor jse1 = (JavascriptExecutor) driver;
        jse1.executeScript("scroll(0,600)");
        rocket.clickOnReedArticleButton();
        methods.Wait(400);
        Assert.assertEquals(title(),"6 способов сберечь семейный бюджет | Мел");
        //driver.navigate().back();
        jse1.executeScript("scroll(0,1000)");
        methods.Wait(400);
        rocket.clickOnAboutRocketCard();
        methods.Wait(400);
        Assert.assertEquals(title(),"Уютный Космос");
        //driver.navigate().back();

        ArrayList<String> expectedResultFooter = new ArrayList();
        expectedResultFooter.add("Школа");
        expectedResultFooter.add("ВУЗ");
        expectedResultFooter.add("Перемена");
        expectedResultFooter.add("Семья");
        expectedResultFooter.add("Блоги | «Мел». Полезное, понятное и удобное онлайн-медиа про образование и детей");
        expectedResultFooter.add("Афиша «Мела» | Мел");
        expectedResultFooter.add("Профессии будущего | «Мел». Полезное, понятное и удобное онлайн-медиа про образование и детей");

        rocket.checkFooterButtons(expectedResultFooter);
    }
}
