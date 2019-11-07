package mel.AdminTestClasses;

import mel.Helper.SetDriver;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;

public class AdminAutosave extends SetDriver {

    private By publicationSaveButton = By.cssSelector(".b-control-panel-draft__save-button > div");
    public By  publicationSaveTime = By.cssSelector(".b-control-panel-draft__saving-info-display");

    public void clickInPublicationSaveButton(){
        $(publicationSaveButton).click();
    }

    public void comparisonPublicationTime(String firstTime, String lastTime) {
        if (firstTime.equals(lastTime)) {
            Assert.fail("Autosave is not working");
        }
    }
}
