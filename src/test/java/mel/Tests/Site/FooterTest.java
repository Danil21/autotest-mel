package mel.Tests.Site;

import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.SetDriver;
import mel.TestClasses.Footer;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;


public class FooterTest extends SetDriver {

    private Footer footer;
    private GetUrl getUrl;

//    @AfterClass
//    public void browserLogs() throws IOException {
//        AdditionalMethods methods = new AdditionalMethods();
//
//        ArrayList errors = new ArrayList();
//        errors.add("facebook");
//        errors.add("tellapart");
//        errors.add("adriver");
//        methods.getBrowserLogs(errors, "FooterTest");
//    }

    @Test
    public void footer() {
        footer = new Footer();
        getUrl = new GetUrl();

        getUrl.driverGet();

        String[] footerRubrics = {
                "До школы | «Мел». Полезное, понятное и удобное онлайн-медиа про образование и детей",
                "Школа",
                "ВУЗ",
                "Перемена",
                "Семья",
                "Профессии будущего | «Мел». Полезное, понятное и удобное онлайн-медиа про образование и детей",
                "Подростки",
                "ЕГЭ",
                "Блоги | «Мел». Полезное, понятное и удобное онлайн-медиа про образование и детей"
        };
        footer.checkLinks(footerRubrics, 10, "rubric");

        String[] linksTitles = {
                "Контакты | Мел",
                "Реклама | Мел",
                "Мероприятия | Мел",
                "Корпоративные блоги | Мел",
                "Пользовательское соглашение | Мел",
                "Согласие на обработку персональных данных | Мел",
                "Политика в отношении обработки персональных данных | Мел",
                "Архив материалов",
                "Наши авторы | Мел",
                "Свежее в блогах | Мел",
                "Популярные темы"
        };
        footer.checkLinks(linksTitles, 9, "links");

        String[] socialNetworkTitles = {
                "Мел. Про образование и воспитание детей - Главная | Facebook",
                "Мел | ВКонтакте",
                "МЕЛ (@melfmru) | Твиттер",
                "Мел. Про образование и детей – человеческим языком | OK.RU",
                //"tg://resolve?domain=melfm", // телега
                "МЕЛ (@melfmru) • Фото и видео в Instagram",
                "МЕЛ на Flipboard"
        };
        footer.checkLinks(socialNetworkTitles, 6, "social network");
    }
}
