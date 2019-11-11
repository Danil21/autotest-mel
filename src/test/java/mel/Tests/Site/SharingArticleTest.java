package mel.Tests.Site;

import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.SetDriver;
import mel.TestClasses.SharingArticle;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.open;

public class SharingArticleTest extends SetDriver {
    private AdditionalMethods methods = new AdditionalMethods();
    private SharingArticle sharingArticle = new SharingArticle();
    private GetUrl getUrl = new GetUrl();


    @Test
    public void sharingArticle() {
        ArrayList<String> expectedResult = new ArrayList();
        //fb
        expectedResult.add("easy2rider2@gmail.com");
        //vk
        expectedResult.add("89774128696");
        //ok
        expectedResult.add("89774128451");
        //пароль отовсех аккаунтов
        expectedResult.add("knock705b");
        //заголовок статьи шера
        expectedResult.add("7 причин, чтобы сменить школу");

        open(getUrl.driverGetStr() + "vazhny_razgovor/2539804-change_school");
        methods.closeNotificationCookie();
        //Sharing Fb
        sharingArticle.sharingFb(expectedResult);
        // Sharing Ok
        sharingArticle.sharingOk(expectedResult);
        // Sharing Vk
        sharingArticle.sharingVk(expectedResult);
        // Sharing twitter
        sharingArticle.twitterSharing(expectedResult);
        // Sharing pinterest
     //   sharingArticle.sharingFlip();

    }
}
