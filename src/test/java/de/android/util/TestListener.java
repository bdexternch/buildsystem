package de.android.util;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import de.android.tests.BaseTest;
import de.android.util.extentreports.ExtentManager;
import de.android.util.extentreports.ExtentTestManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {
    // This method will execute before starting of Test suite.
    public void onStart(ITestContext testContext) {

    }

    // This method will execute, Once the Test suite is finished.
    public void onFinish(ITestContext testContext) {
        ExtentManager.getReporter().flush();
    }

    // This method will execute before the main test start (@Test)
    public void onTestStart(ITestResult testResult) {
        ExtentTest test = ExtentTestManager.startTest(testResult.getTestContext().getName() + ": " +
                testResult.getMethod().getMethodName(), testResult.getMethod().getDescription());
        test.assignCategory(testResult.getTestContext().getName());
    }

    // This method will execute only when the test is pass.
    public void onTestSuccess(ITestResult testResult) {
        ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
        ExtentTestManager.endTest();
        ExtentManager.getReporter().flush();
    }

    // This method will execute only on the event of fail test.
    public void onTestFailure(ITestResult testResult) {
        Object currentClass = testResult.getInstance();
        AppiumDriver<MobileElement> driver = ((BaseTest) currentClass).getDriver();
        String screenshot = captureScreenshot(testResult, driver);

        ExtentTestManager.getTest().log(LogStatus.FAIL, testResult.getThrowable());
        ExtentTestManager.getTest().log(LogStatus.FAIL, ExtentTestManager.getTest().addScreenCapture(screenshot));
        ExtentTestManager.endTest();
        ExtentManager.getReporter().flush();

        // Ensures that the following test begins at the start screen
        driver.closeApp();
        driver.launchApp();
    }

    // This method will execute only if any of the main test(@Test) get skipped
    public void onTestSkipped(ITestResult testResult) {
        ExtentTest test = ExtentTestManager.startTest(testResult.getTestContext().getName() + ": " +
                testResult.getMethod().getMethodName(), testResult.getMethod().getDescription());
        test.assignCategory(testResult.getTestContext().getName());
        test.log(LogStatus.SKIP, "Test Skipped");
        ExtentTestManager.endTest();
        ExtentManager.getReporter().flush();
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult testResult) {
    }

    private String captureScreenshot(ITestResult testResult, AppiumDriver driver) {
        String testName = testResult.getTestContext().getName();
        String methodName = testResult.getMethod().getMethodName();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd_HH-mm-ss");
        String destFile = "/" + testName + "-" + methodName + "_" + dateFormat.format(new Date()) + ".png";

        String workingDir = System.getProperty("user.dir");
        if(driver.getPlatformName().toLowerCase().equals("android")) {
            workingDir += "/ExtentReportsAndroid";
        } else {
            workingDir += "/ExtentReportsIOS";
        }

        String screenshotDir = "/screenshots";

        String imgPath = workingDir + screenshotDir + destFile;
        File scrFile = driver.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(imgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "." + screenshotDir + destFile;
    }
}
