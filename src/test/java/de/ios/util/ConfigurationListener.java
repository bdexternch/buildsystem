package de.ios.util;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import de.ios.util.extentreports.ExtentManager;
import de.ios.util.extentreports.ExtentTestManager;
import org.testng.IConfigurationListener;
import org.testng.ITestResult;
import org.testng.annotations.Parameters;

public class ConfigurationListener implements IConfigurationListener {
    @Override
    public void onConfigurationSuccess(ITestResult testResult) {
    }

    @Parameters({})
    @Override
    public void onConfigurationFailure(ITestResult testResult) {
        ExtentTest test = ExtentTestManager.startTest(testResult.getTestContext().getName() + ": " + "Setup");
        test.assignCategory(testResult.getTestContext().getName());
        ExtentTestManager.getTest().log(LogStatus.ERROR, "Setup failed");
        ExtentTestManager.getTest().log(LogStatus.ERROR, testResult.getThrowable());
        ExtentTestManager.endTest();
        ExtentManager.getReporter().flush();
    }

    @Override
    public void onConfigurationSkip(ITestResult testResult) {
    }
}
