package mel.TestClasses;

import mel.Helper.AdditionalMethods;
import mel.Helper.SetDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Search extends SetDriver {

    private AdditionalMethods methods;

    private  By searchButton = By.cssSelector(".right-buttons__search-button");
    private  By searchInput = By.cssSelector(".b-search-bar__input > div > input");

    private  By firstArticleInListSearchResult = By.cssSelector(".l-search__search-results-container > div:nth-child(1)");
    private  By showMoreArticles = By.xpath("//span[@class='g-button__label' and text()='Показать еще материалы']");
    private  By elevenArticleInListSearchResult = By.cssSelector(".l-search__search-results-container > div:nth-child(11)");

    private  By emptySearchTitle = By.cssSelector(".l-search__empty-search-title");
    private  By closeSearchButton = By.cssSelector(".b-search-bar__close-button");
    private  By ListPublication = By.cssSelector(".l-search__search-results-container > div");
    public  By ListImagePublication = By.cssSelector(".l-search__search-results-container > div:nth-child(1) > a");

    private  By HeaderLogoMel = By.cssSelector(".header > a");
    public  By errorRobot = By.cssSelector(".b-error_extended");

    public String getTextInSearchResultInput() {
        String str = $(searchInput).getAttribute("value");
        return str;
    }

    public String getSearchInputPlaceholderText() {
        String placeholder = $(searchInput).getAttribute("placeholder");
        return placeholder;
    }

    public void checkTextInvalidRequest() {
        $(emptySearchTitle).shouldHave(text("Ой, мы вряд ли сможем что-нибудь найти по вашему запросу.\n" +
                "Попробуйте сформулировать запрос иначе, а пока можете почитать наши новые тексты:"));
    }

    public  void clickOnSearchButton() {
        $(searchButton).click();
    }

    public  void sendSearchQuery(String searchQueryText) {
        $(searchInput).sendKeys(searchQueryText);
        $(searchInput).sendKeys(Keys.ENTER);
    }

    public  void checkArticlesInListSearchResult() {
        $(firstArticleInListSearchResult).click();
        getWebDriver().navigate().back();
        $(showMoreArticles).scrollIntoView(true).click();
        $$(ListPublication).shouldHaveSize(20);
        $(elevenArticleInListSearchResult).click();
        //TODO: вернуть коллекцию картинок для проверки на наличие
    }

    public  void checkDeleteInSearchResultInput(String resultTextInSearch) {
        $(closeSearchButton).click();
        $(searchInput).sendKeys(resultTextInSearch);
        $(searchInput).sendKeys(Keys.ENTER);
    }

    public  void checkCloseSearch() {
        $(HeaderLogoMel).click();
        $(searchButton).click();
        $(closeSearchButton).click();
    }

    public boolean checkSearchInputIsHidden() {
        boolean flag = true;
        try {
            $(searchInput).click();
        } catch (ElementNotVisibleException e) {
            flag = false;
        }
        return flag;
    }

    public void refreshSearsh() {
        boolean error = $(errorRobot).isDisplayed();
        if (error) {
            getWebDriver().navigate().refresh();
        }
    }
}
