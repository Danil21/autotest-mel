package mel.Helper;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RunTestAgain implements IRetryAnalyzer {

    private int nowCount = 0;
    private static final int maxCount = 1;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (nowCount < maxCount) {
            nowCount++;
            return true;
        } else{
            System.out.println("Тест упал 2 раза");}
        return false;
    }

}
