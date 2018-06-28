package de.ios.screens;

import com.relevantcodes.extentreports.LogStatus;
import de.ios.util.extentreports.ExtentTestManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.HowToUseLocators;
import io.appium.java_client.pagefactory.LocatorGroupStrategy;
import io.appium.java_client.pagefactory.iOSFindBy;

public class TrackingScreen extends AbstractScreen {
    public TrackingScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/activityHomeEarpatron")
    @iOSFindBy(accessibility = "Tracking")
    private MobileElement tracking;

    @HowToUseLocators(iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/viewTabEarparonInfo")
    @iOSFindBy(accessibility = "More Info")
    @iOSFindBy(accessibility = "Weitere Infos")
    private MobileElement trackingInfo;

    @AndroidFindBy(id = "android:id/button1")
    private MobileElement okButton;

    public void showTrackingInfo() {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Go to tracking screen");
        tracking.click();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on info button");
        trackingInfo.click();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on OK button of tracking info");
        if(driver.getPlatformName().toLowerCase().equals("android")) {
            okButton.click();
        } else {
            driver.switchTo().alert().accept();
        }
    }
}
