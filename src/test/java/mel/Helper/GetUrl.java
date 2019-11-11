package mel.Helper;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class GetUrl extends SetDriver {

    Settings settings;

    public String choiceStand() {

        settings = new Settings();
        int stand = settings.setStandNumber();

        if (stand == 2) {
            return String.valueOf(stand);
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
        return "https://qa" + choiceStand() + ".mel.fm/";
    }

    public void driverGetAdminUrl() {
        open("https://admin-qa" + choiceStand() + ".mel.fm/");
    }

    public String driverGetAdminUrlStr() {
        return "https://admin-qa" + choiceStand() + ".mel.fm/";
    }

    public void driverGetCurrentAdminUrl(String str) {
        getWebDriver().get("https://admin-qa" + choiceStand() + ".mel.fm/" + str);
    }


}
