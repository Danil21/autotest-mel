package mel.Tests.Admin;

import MelAppium.resources.config;
import com.codeborne.selenide.Condition;
import mel.AdminTestClasses.AdminLogin;
import mel.AdminTestClasses.AdminRubricator;
import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.SetDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class RubricatorTest extends SetDriver {


    @Test
    public void rubricator() {
        AdditionalMethods methods = new AdditionalMethods();
        AdminRubricator rubricator = new AdminRubricator();
        GetUrl getUrl = new GetUrl();
        AdminLogin adminLogin = new AdminLogin();

        getUrl.driverGetAdminUrl();
        adminLogin.adminAuthorisation(config.getTestProperty("adminLogin"),config.getTestProperty("adminPass"));
        $(rubricator.rubricatorTab).click();

        //add new rubric
        rubricator.addNewRubric("Testing");
        sleep(3000);

        // check new rubric
        $(rubricator.editNewRubricButton).shouldHave(Condition.text("Testing"));
        $(rubricator.visibilitySwitch).click();
        $(rubricator.confirmationVisibility).click();
        sleep(3000);
        getUrl.driverGet();
        $(rubricator.newRubricOnWebsite).shouldBe(Condition.text("TESTING"));
        $(rubricator.newRubricOnWebsite).click();

        Assert.assertEquals(getWebDriver().getCurrentUrl(), getUrl.driverGetStr() + "rubric/testing");
        Assert.assertEquals(getWebDriver().getTitle(), "Testing | «Мел». Полезное, понятное и удобное онлайн-медиа про образование и детей");

        getUrl.driverGetAdminUrl();
        rubricator.checkNewRubricOnAddPublicationPage();
        Assert.assertEquals(methods.getTextFromSelector(rubricator.rubricInput), "Testing");
        sleep(3000);

        //edit rubric
        $(rubricator.rubricatorTab).click();
        rubricator.editRubric("2", "TestTitleSeo", "TestDescriptionSeo");
        sleep(3000);

        // check edit rubric
        Assert.assertEquals(methods.getTextFromSelector(rubricator.editRubricButton), "Testing2");
        getUrl.driverGet();
        Assert.assertEquals(methods.getTextFromSelector(rubricator.newRubricOnWebsite), "TESTING2");
        $(rubricator.newRubricOnWebsite).click();

        Assert.assertEquals(getWebDriver().getCurrentUrl(), getUrl.driverGetStr() + "rubric/testing");
        Assert.assertEquals(rubricator.metaNameSeoTitleRubric(),"TestTitleSeo");
        Assert.assertEquals(rubricator.metaNameSeoDescriptionRubric(),"TestDescriptionSeo");

        //check close popup
        getUrl.driverGetAdminUrl();
        $(rubricator.rubricatorTab).click();
        rubricator.checkCloseEditRubricPopup();
        sleep(3000);

        //check delete rubric
        rubricator.deleteRubric();
        Assert.assertTrue($(rubricator.rubricHidden).isDisplayed());
        sleep(3000);
        getUrl.driverGetCurrentUrl("rubric/testing");
        Assert.assertEquals(getWebDriver().getTitle(), "Страница не найдена | Мел");
    }
}
