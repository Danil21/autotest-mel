package mel.Tests.Site;

import com.codeborne.selenide.Condition;
import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.SetDriver;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.title;

public class CheckErrorPageTest extends SetDriver {

    private By errorMessage = By.cssSelector(".l-page-404__text-description");
    private By errorImage = By.cssSelector(".i-layout__content > div > img");
    private By checktTransitionMain = By.cssSelector(".l-frontpage__rubrics");// строка с рубриками


    @Test
    public void checkErrorPage() {

        AdditionalMethods methods = new AdditionalMethods();
        GetUrl getUrl = new GetUrl();

            getUrl.driverGet();
            getUrl.driverGetCurrentUrl("somewrongpagetest");
            Assert.assertEquals(title(), "Страница не найдена | Мел");
            $(errorMessage).shouldHave(Condition.text("Похоже, вы неправильно набрали адрес или страница, которую вы ищете, не существует.\n" +
                    "Но у нас есть много всего интересного на главной"));

            Assert.assertEquals($(errorImage).getTagName(), "img");
            $(checktTransitionMain).shouldHave(Condition.visible);
            Assert.assertEquals(title(), "«Мел». Полезное, понятное и удобное онлайн-медиа про образование и детей");
    }
}
