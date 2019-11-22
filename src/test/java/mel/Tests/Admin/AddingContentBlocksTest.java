package mel.Tests.Admin;

import MelAppium.resources.config;
import mel.AdminTestClasses.AdminAddingPublication;
import mel.AdminTestClasses.AdminAddingTestContent;
import mel.AdminTestClasses.AdminLogin;
import mel.Helper.GetUrl;
import mel.Helper.SetDriver;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.sleep;

public class AddingContentBlocksTest extends SetDriver {

    private AdminAddingTestContent addingTest = new AdminAddingTestContent();
    private GetUrl getUrl = new GetUrl();
    private AdminLogin adminLogin = new AdminLogin();
    private AdminAddingPublication addingPublication = new AdminAddingPublication();

    @Test
    public void addingContentBlocks() {

        int a = 0;
        int b = 10000;
        int randomNumber = a + (int) (Math.random() * b);
        String title = "TestCheck" + randomNumber;

        getUrl.driverGetAdminUrl();
        adminLogin.adminAuthorisation(config.getTestProperty("adminLogin"), config.getTestProperty("adminPass"));
        sleep(2000);
        addingPublication.clickInNewPublication();
        sleep(1000);
        addingPublication.fillingFields(title, "Subtitle", "The Question", "Annoucement",
                "Covertag", "Addingtag", "Text in block");
        //Добавление теста к статье
        addingTest.addNewTest("QuestionOne?", "AnswerOne", "DescriptionOne",
                "1", "AnswerTwo", "0", "QuestionTwo?",
                "secondAnswer", "1", "0", "1", "2",
                "2", "Description");

        addingPublication.addingCovers();
        sleep(1000);
        addingPublication.clickInPublicButton();
        sleep(1000);
        //Проверка созданного теста на сайте
        addingTest.checkNewTest();
        //Проверка удаления блока с тестом в админке
        addingTest.deleteTestAndOpenInAdminka();
    }
}
