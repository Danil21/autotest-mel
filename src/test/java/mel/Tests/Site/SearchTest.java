package mel.Tests.Site;

import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.RunTestAgain;
import mel.Helper.SetDriver;
import mel.TestClasses.Search;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


public class SearchTest extends SetDriver {

    private AdditionalMethods methods;
    private Search search;
    private GetUrl getUrl;

//    @AfterClass
//    public void browserLogs() throws IOException {
//        methods = new AdditionalMethods();
//
//        ArrayList errors = new ArrayList();
//        errors.add("https://image.mel.fm/i/G/GgpAWCDbxh/640.jpg");
//        errors.add("https://x01.aidata.io/");
//        errors.add("adriver");
//        errors.add("revsci");
//        errors.add("otm-r");
//        errors.add("rubiconproject");
//        methods.getBrowserLogs(errors, "SearchTest");
//    }

    @Test
    public void search() {
        methods = new AdditionalMethods();
        search = new Search();
        getUrl = new GetUrl();

        getUrl.driverGet();
        getWebDriver().manage().deleteAllCookies();
        //Send valid search query and check placeholder presence
        search.clickOnSearchButton();
        Assert.assertEquals(search.getSearchInputPlaceholderText(), "Что вы ищете?");
        search.sendSearchQuery("новости");
        search.refreshSearsh();

        Assert.assertNotNull(methods.getHrefFromSelector(search.ListImagePublication), "нет картинки");
        //Check if search result is correct
        search.checkArticlesInListSearchResult();
        getUrl.driverGet();

        //Send  invalid search query
        search.clickOnSearchButton();
        search.sendSearchQuery("!#$%&'*+-/=?^_`{|}~");
        search.checkTextInvalidRequest();// костыль,переделать
        search.checkDeleteInSearchResultInput("мяу");
        Assert.assertEquals(search.getTextInSearchResultInput(), "мяу");

        search.checkCloseSearch();
        Assert.assertTrue(search.checkSearchInputIsHidden(), "Close button in search input is not working");
    }
}
