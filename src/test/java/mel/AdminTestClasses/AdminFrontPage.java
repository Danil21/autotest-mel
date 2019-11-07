package mel.AdminTestClasses;

import mel.Helper.SetDriver;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;

public class AdminFrontPage extends SetDriver{

    private By frontPageButton = By.cssSelector(".b-header__menu > div > div:nth-child(2)");
    private By pageSwitcherIcon = By.cssSelector(".b-switcher__icon > div");
    public By titleMainPublication = By.xpath("//*[@class='b-ml-cover__content']//div[@class='b-ml-cover__title']");
    public By publicationToAdd = By.cssSelector(".b-switcher__title");
    private By frontPageSaveButton = By.cssSelector(".i-layout__save-button > div");

    public void clickInFrontPageButton(){
        $(frontPageButton).click();
    }

    public void clickInPublicationSwitcher(){
        $(pageSwitcherIcon).click();
    }

    public void comprasionPublicationsInFrontPage(String firstPublication, String secondPublication){
        if(firstPublication.equals(secondPublication)){
            Assert.fail("The publication isn`t added on the front page by clicking in switcher");
        }
    }

    public void clickInFrontPageSaveButton(){
        $(frontPageSaveButton).click();
    }
}
