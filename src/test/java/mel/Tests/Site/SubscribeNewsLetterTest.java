package mel.Tests.Site;

import com.codeborne.selenide.Condition;
import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.SetDriver;
import mel.TestClasses.SubscribeNewsLetter;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.title;

public class SubscribeNewsLetterTest extends SetDriver {

    private AdditionalMethods methods;
    private SubscribeNewsLetter subscribeNewsLetter;
    private GetUrl getUrl;

    @AfterClass
    public void browserLogs() throws IOException {
        methods = new AdditionalMethods();
        ArrayList errors = new ArrayList();
        errors.add("yandex");
        methods.getBrowserLogs(errors, "SubscribeNewsLetterTest");
    }

    @Test
    public void subscribeNewsLetter() {
        methods = new AdditionalMethods();
        subscribeNewsLetter = new SubscribeNewsLetter();
        getUrl = new GetUrl();

        getUrl.driverGet();
        methods.closeNotificationCookie();

        String str1 = "testpablo";
        String str2 = "@p33.org";
        Random r = new Random(System.currentTimeMillis());
        int q = r.nextInt(1111) - 100;
        Random r1 = new Random(System.currentTimeMillis());
        int w = r1.nextInt(111) - 10;
        String email = w + str1 + q + str2;
        String email2 = w + str1 + q;

        subscribeNewsLetter.subscribe("");
        Assert.assertTrue(subscribeNewsLetter.getDisplayedErrorInSubscribeInput());

        getUrl.driverGet();
        subscribeNewsLetter.subscribe(email);
        subscribeNewsLetter.subscribe(email2);
        Assert.assertEquals(title(), "«Мел». Полезное, понятное и удобное онлайн-медиа про образование и детей");
        $x("//*[contains(@class,'i-layout__subscribe')]").shouldHave(Condition.text("Ура, вы только что подписались!\nСледите за обновлениями в почтовом ящике"));
        //проверка подписки под статьей
        getUrl.driverGetCurrentUrl("pravila-vospitaniya/192658-sheila_metzner");
        subscribeNewsLetter.checkSubscribeUnderPublication(email);

        getUrl.driverGetCurrentUrl("blog/tikhiye-yabloni/61975-igry-na-kryshe-shkolnyye-ploshchadki-po-aziatski");
        subscribeNewsLetter.checkSubscribeUnderBlog(email);
        subscribeNewsLetter.checkSubcribeInPageBook(email);

    }
    @Test
    public void pagesSubscribe(){
        methods = new AdditionalMethods();
        subscribeNewsLetter = new SubscribeNewsLetter();
        getUrl = new GetUrl();

        getUrl.driverGet();




    }

}
