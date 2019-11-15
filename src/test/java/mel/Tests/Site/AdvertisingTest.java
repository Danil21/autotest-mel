package mel.Tests.Site;

import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.SetDriver;
import mel.TestClasses.AdvertisingPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.title;

public class AdvertisingTest extends SetDriver {
    private GetUrl getUrl = new GetUrl();
    private AdvertisingPage advertisingPage = new AdvertisingPage();
    private AdditionalMethods methods = new AdditionalMethods();

    @AfterClass
    public void browserLogs() throws IOException {
        AdditionalMethods methods = new AdditionalMethods();

        ArrayList errors = new ArrayList();
        errors.add("adriver");
        errors.add("dropbox");
        methods.getBrowserLogs(errors, "AdvertisingTest");
    }

    @Test
    public void checkAdvertising() {
        String mediakitTitle = "Dropbox - Mel_Mediakit.pdf - Упростите себе жизнь";
        String priceTitle = "Dropbox - Mel_Pricelist.pdf - Упростите себе жизнь";

        getUrl.driverGet();
        methods.deleteCookie();
        getUrl.driverGetCurrentUrl("page/advertising-proposal");
        Assert.assertTrue(title().contains("Реклама"));
        sleep(1000);
        advertisingPage.checkAdvertising(mediakitTitle, priceTitle);
    }

}