package mel.Tests.Site;

import com.codeborne.selenide.Condition;
import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.SetDriver;
import mel.TestClasses.Search;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.$;

public class PublicationVariations extends SetDriver {

    private AdditionalMethods methods = new AdditionalMethods();
    private GetUrl getUrl = new GetUrl();

//    @AfterClass
//    public void browserLogs() throws IOException {
//        methods = new AdditionalMethods();
//
//        ArrayList errors = new ArrayList();
//        errors.add("https://image.mel.fm/i/G/GgpAWCDbxh/640.jpg");
//        errors.add("https://x01.aidata.io/");
//        errors.add("adriver");
//        errors.add("revsci");
//        methods.getBrowserLogs(errors, "PublicationVariations");
//    }

    @Test
    public void openPublicationVariations() {
        Search search = new Search();

        getUrl.driverGet();

        getUrl.driverGetCurrentUrl("komiksy/8092476-comics");
        $(search.errorRobot).shouldBe(Condition.not(Condition.visible));
        getUrl.driverGetCurrentUrl("chto-pochitat/1698253-nine_books");
        $(search.errorRobot).shouldBe(Condition.not(Condition.visible));
        getUrl.driverGetCurrentUrl("novosti/6814790-itforum");
        $(search.errorRobot).shouldBe(Condition.not(Condition.visible));
        getUrl.driverGetCurrentUrl("afisha-mela/3698125-portfolio_revie");
        $(search.errorRobot).shouldBe(Condition.not(Condition.visible));
        getUrl.driverGetCurrentUrl("afisha-mela/9720354-edcrunchspb");
        $(search.errorRobot).shouldBe(Condition.not(Condition.visible));
        getUrl.driverGetCurrentUrl("novosti/7346592-miting");
        $(search.errorRobot).shouldBe(Condition.not(Condition.visible));
        getUrl.driverGetCurrentUrl("otryvok/4071865-an_epidemic_of_absence");
        $(search.errorRobot).shouldBe(Condition.not(Condition.visible));
        getUrl.driverGetCurrentUrl("test2/6538240-miyazaki_test");
        $(search.errorRobot).shouldBe(Condition.not(Condition.visible));
        getUrl.driverGetCurrentUrl("1-sentyabrya/846573-mel_team");
        $(search.errorRobot).shouldBe(Condition.not(Condition.visible));
        getUrl.driverGetCurrentUrl("novosti/2465971-seryozha ");
    }
}
