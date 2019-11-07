package mel.Tests.Site;

import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.SetDriver;
import mel.TestClasses.Archive;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.WebDriverRunner.url;

public class ArchiveTest extends SetDriver {

    private Archive archive = new Archive();
    private GetUrl getUrl = new GetUrl();
    private AdditionalMethods methods = new AdditionalMethods();

//    @AfterClass
//    public void browserLogs() throws IOException {
//        methods = new AdditionalMethods();
//
//        ArrayList errors = new ArrayList();
//        errors.add("revsci.net");
//        errors.add("adriver");
//        methods.getBrowserLogs(errors, "ArchiveTest");
//    }

    @Test
    public void archiveCheck() {
        getUrl.driverGet();

        methods.deleteCookie();
        archive.clickOnFooterArchive();
        archive.checkArchiveTitle();
        archive.checkForMissingButtons();
        String firstPageTitle = url();
        archive.clickOnNumberButton();
        String secondPageTitle = url();
        //Assert that after clicking on pagination button the page is actually changed
        Assert.assertNotEquals(firstPageTitle, secondPageTitle);
        archive.clickOnPaginationNextPage();
        String nextPageTitle = url();
        //Assert that after clicking on pagination "Next page" button the page is actually changed
        Assert.assertNotEquals(secondPageTitle, nextPageTitle);
        archive.clickOnPaginationPrevPage();
        String prevPageTitle = url();
        //Assert that after clicking on pagination "Previous page" button the page is actually changed
        Assert.assertNotEquals(nextPageTitle, prevPageTitle);
    }
}

