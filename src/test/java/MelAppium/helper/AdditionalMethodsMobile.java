package MelAppium.helper;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.IExecutionListener;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.sleep;

public class AdditionalMethodsMobile implements IExecutionListener {
    private String firstAppiumCommand = "cmd /c start cmd.exe /K \"cd c:/ && appium -a 127.0.0.1 -p 1234 -cp 1234 -bp 2345";
    //private String secondAppiumCommand = "cmd /c start cmd.exe /K \"cd c:/ && appium -a 127.0.0.2 -p 3456 -cp 3456 -bp 4567";

    public void onExecutionStart() {
        try {
            Runtime.getRuntime().exec(firstAppiumCommand);
            //Runtime.getRuntime().exec(secondAppiumCommand);
            sleep(15000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onExecutionFinish() {
        try {
            Runtime.getRuntime().exec("taskkill /F /IM cmd.exe /T");
           // Runtime.getRuntime().exec("taskkill /F /IM cmd.exe /T");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getRandomUserName() {
        String randomAlphabetic = RandomStringUtils.randomAlphabetic(7);
        return randomAlphabetic;
    }

}
