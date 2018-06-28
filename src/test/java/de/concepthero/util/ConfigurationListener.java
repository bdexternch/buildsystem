package de.concepthero.util;

import de.concepthero.util.extentreports.ExtentManager;
import de.concepthero.util.extentreports.ExtentTestManager;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
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
