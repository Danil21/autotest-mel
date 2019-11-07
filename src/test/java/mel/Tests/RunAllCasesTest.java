package mel.Tests;

import com.beust.jcommander.internal.Lists;
import mel.Helper.Settings;
import org.testng.TestNG;

import java.util.List;

public class  RunAllCasesTest extends Settings {
    public static void main(String[] args) throws Exception {
        TestNG testng = new TestNG();
        List<String> suites = Lists.newArrayList();
        suites.add(testsSelection("all"));
        testng.setTestSuites(suites);
        testng.run();
    }
}

