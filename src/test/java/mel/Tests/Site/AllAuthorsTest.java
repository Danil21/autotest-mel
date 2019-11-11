package mel.Tests.Site;

import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.SetDriver;
import mel.TestClasses.AllAuthorsPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

public class AllAuthorsTest extends SetDriver {

    @AfterClass
    public void browserLogs() throws IOException {
        AdditionalMethods methods = new AdditionalMethods();
        ArrayList errors = new ArrayList();
        errors.add("adriver");
        errors.add("adx");
        methods.getBrowserLogs(errors, "AllAuthorsTest");
    }

    @Test
    public void checkAllAuthorsPage() {
        AdditionalMethods methods = new AdditionalMethods();
        AllAuthorsPage allAuthors = new AllAuthorsPage();
        GetUrl getUrl = new GetUrl();


        getUrl.driverGet();
        methods.closeNotificationCookie();

        allAuthors.checkSearchWithAuthor();
        allAuthors.checkTagsInAuthorCard();
        allAuthors.checkCreatePost();
        allAuthors.checkPagination();
    }
}
