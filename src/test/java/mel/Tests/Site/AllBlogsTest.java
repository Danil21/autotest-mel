package mel.Tests.Site;

import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.SetDriver;
import mel.TestClasses.AllBlogsPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

public class AllBlogsTest extends SetDriver {

    @AfterClass
    public void browserLogs() throws IOException {
        AdditionalMethods methods = new AdditionalMethods();
        ArrayList errors = new ArrayList();
        errors.add("adriver");
        errors.add("adx");
        errors.add("aidata");
        methods.getBrowserLogs(errors, "AllBlogsTest");
    }

    @Test
    public void checkAllBlogersPage() {
        AllBlogsPage blogsPage = new AllBlogsPage();
        GetUrl getUrl = new GetUrl();
        AdditionalMethods methods = new AdditionalMethods();

        getUrl.driverGet();
        methods.deleteCookie();
        blogsPage.checkCreatePost();
        blogsPage.checkPagination();
        blogsPage.checkFirstBlog();
    }
}
