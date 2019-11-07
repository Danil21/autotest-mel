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
    private AllAuthorsPage allAuthors = new AllAuthorsPage();
    private GetUrl getUrl = new GetUrl();
    private AdditionalMethods methods = new AdditionalMethods();

//    @AfterClass
//    public void browserLogs() throws IOException {
//        AdditionalMethods methods = new AdditionalMethods();
//        ArrayList errors = new ArrayList();
//        errors.add("adriver");
//        errors.add("adx");
//        methods.getBrowserLogs(errors, "AllAuthorsTest");
//    }

    @Test
    public void checkAllAuthorsPage() {
        getUrl.driverGet();

        methods.deleteCookie();
        allAuthors.checkSearchWithAuthor();
        allAuthors.checkTagsInAuthorCard();
        allAuthors.checkCreatePost();
        allAuthors.checkPagination();
    }
}
