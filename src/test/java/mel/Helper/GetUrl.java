package mel.Helper;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class GetUrl extends SetDriver {

    Settings settings;

    public String choiceStand() {

        settings = new Settings();
        int stand = settings.setStandNumber();

        if (stand == 2) {
            String str = String.valueOf(stand);
            return str;
        }
        return "";
    }

    public void driverGet() {
        open("https://qa" + choiceStand() + ".mel.fm/");

    }

    public void driverGetCurrentUrl(String str) {
        getWebDriver().get("https://qa" + choiceStand() + ".mel.fm/" + str);
    }

    public String driverGetStr() {
        String str = "https://qa" + choiceStand() + ".mel.fm/";
        return str;
    }

    public void driverGetAdminUrl() {
        open("https://admin-qa" + choiceStand() + ".mel.fm/");
    }

    public String driverGetAdminUrlStr() {
        String str = "https://admin-qa" + choiceStand() + ".mel.fm/";
        return str;
    }

    public void driverGetCurrentAdminUrl(String str) {
        getWebDriver().get("https://admin-qa" + choiceStand() + ".mel.fm/" + str);
    }


}
