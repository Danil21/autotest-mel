package mel.TestClasses;

import mel.Helper.SetDriver;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.title;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Footer extends SetDriver {

    public void checkLinks(String[] expectedTitles, int buttonsNumber, String type) {
        String actualTitle;
        By element = null;

        for (int i = 1; i < buttonsNumber; i++) {
            $(".i-layout__footer").scrollIntoView(false);
            if (type == "links") {
                element = By.xpath("//*[@class='b-footer__links']//a[" + i + "]");
            } else if (type == "social network") {
                element = By.xpath("//*[@class='b-footer__socials']/div[" + i + "]/a");
            } else if (type == "rubric") {
                element = By.xpath("//*[@class='b-footer__rubrics']/div/a[" + i + "]");
            }
            $(element).click();

            actualTitle = title();
//            if(i==6){
//                Assert.assertTrue(actualTitle.contains(expectedTitles[i-1]));
//            }
            getWebDriver().navigate().back();
        }
    }
}