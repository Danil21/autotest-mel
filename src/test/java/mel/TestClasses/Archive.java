package mel.TestClasses;

import com.codeborne.selenide.Condition;
import mel.Helper.SetDriver;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Archive extends SetDriver {

    private By footerArchive = By.xpath("//*[@class='b-footer__links']//*[@href='/archive/recent']");
    private By archiveTitle = By.xpath("//*[text()='Архив материалов']");
    private By numberButton = By.xpath("//*[@data-page-index='2']");
    private By paginationNextPage = By.xpath("//*[text()='Следующая страница']");
    private By paginationPrevPage = By.xpath("//*[text()='Предыдущая страница']");


    public void clickOnFooterArchive() {
        $(footerArchive).scrollIntoView(true).click();
    }

    public void clickOnNumberButton() {
        $(numberButton).scrollIntoView(true).click();
    }

    public void clickOnPaginationNextPage() {
        $(paginationNextPage).scrollTo().click();
    }

    public void clickOnPaginationPrevPage() {
        $(paginationPrevPage).scrollIntoView(true).click();
    }

    public void checkForMissingButtons() {
        $(paginationPrevPage).shouldHave(Condition.not(Condition.visible));
    }

    public void checkArchiveTitle() {
        $(archiveTitle).shouldHave(Condition.visible);
    }
}