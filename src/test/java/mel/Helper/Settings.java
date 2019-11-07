package mel.Helper;

public class Settings {

    public final int setStandNumber(){
        int standNumber = 2;
        return standNumber;
    }

    public static String testsSelection(String testSuite){
        String str = null;
        switch (testSuite){
            case "all":
                str = "src/test/java/mel/AllTestSuite.xml";
                break;
            case "site":
                str = "src/test/java/mel/SiteTestSuite.xml";
                break;
            case "admin":
                str = "src/test/java/mel/AdminTestSuite.xml";
                break;
        }
        return str;
    }

}
