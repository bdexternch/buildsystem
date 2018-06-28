package de.android.screens;

import com.relevantcodes.extentreports.LogStatus;
import de.android.util.extentreports.ExtentTestManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.HowToUseLocators;
import io.appium.java_client.pagefactory.LocatorGroupStrategy;
import io.appium.java_client.pagefactory.iOSFindBy;

public class DeviceInfoScreen extends AbstractScreen {

    public DeviceInfoScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Aventho\")")
    @iOSFindBy(xpath = "//XCUIElementTypeButton[(@name,\"Aventho\")]")
    private MobileElement batteryText;

//    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"0%)\")")
//    @iOSFindBy(xpath = "//XCUIElementTypeButton[contains(@name,\"%)\")]")
//    private MobileElement batteryPercentage;

    @HowToUseLocators(iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/bar_headphone_state_include")
    @iOSFindBy(xpath = "//XCUIElementTypeButton[contains(@name,\"Verbunden\")]")
    @iOSFindBy(xpath = "//XCUIElementTypeButton[contains(@name,\"Connected\")]")
    private MobileElement deviceInfo;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Beyerdynamic\")")
    @iOSFindBy(accessibility = "Beyerdynamic")
    private MobileElement manufacturerName;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Aventho wireless\")")
    @iOSFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,\"Aventho wireless\")]")
    private MobileElement headphoneType;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\".\")")
    @iOSFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,\".\")]")
    private MobileElement softwareRevision;

    @iOSFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,\":\")]")
    private MobileElement bluetoothAddress;


    public void batteryIsDisplayed() {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Check that battery info is displayed");
        batteryText.isDisplayed();
//        batteryPercentage.isDisplayed();
    }

    public void deviceInfoIsDisplayed(String headphone) {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on device info");
        deviceInfo.click();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Check that manufacturer name is displayed");
        manufacturerName.isDisplayed();
        scrollDown();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Check that headphone type is displayed");
        headphoneType.isDisplayed();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Check that sotware revision is displayed");
        softwareRevision.isDisplayed();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Check that bluetooth address is displayed");
        if(driver.getPlatformName().toLowerCase().equals("ios")) {
            bluetoothAddress.isDisplayed();
        } else {
            bluetoothAddress = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"" + headphone.toLowerCase() + "\")"));
        }
    }
}
