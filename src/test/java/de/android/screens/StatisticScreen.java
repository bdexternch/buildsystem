package de.android.screens;

import com.relevantcodes.extentreports.LogStatus;
import de.android.util.extentreports.ExtentTestManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;

public class StatisticScreen extends de.android.screens.AbstractScreen {
    public StatisticScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/statistic_item")
    @iOSFindBy(accessibility = "Tracking")
    private MobileElement statistic;

    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/viewTabEarparonInfo")
    @iOSFindBy(accessibility = "More Info")
    private MobileElement earpatronInfo;

    @AndroidFindBy(id = "android:id/button1")
    private MobileElement okButton;

    public void showTrackingInfo() {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Go to statistic screen");
        statistic.click();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on info button");
        earpatronInfo.click();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on OK button of tracking info");
        if(driver.getPlatformName().toLowerCase().equals("android")) {
            okButton.click();
        } else {
            driver.switchTo().alert().accept();
        }
    }
}
