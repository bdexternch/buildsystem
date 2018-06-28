package de.ios.screens;

import com.relevantcodes.extentreports.LogStatus;
import de.ios.util.extentreports.ExtentTestManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.HowToUseLocators;
import io.appium.java_client.pagefactory.LocatorGroupStrategy;
import io.appium.java_client.pagefactory.iOSFindBy;

public class DeviceInfoScreen extends AbstractScreen {

    public DeviceInfoScreen (AppiumDriver driver) {
        super(driver);
    }

    @HowToUseLocators(iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(accessibility = "Nach oben")
    @iOSFindBy(accessibility = "Back")
    @iOSFindBy(accessibility = "Zurück")
    private MobileElement back;

    @HowToUseLocators(iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"% Batterie\")")
    @iOSFindBy(xpath = "//XCUIElementTypeButton[contains(@name,\"% Battery\")]")
    @iOSFindBy(xpath = "//XCUIElementTypeButton[contains(@name,\"% Batterie\")]")
    private MobileElement batteryText;

    @HowToUseLocators(iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/viewStatusIconSubtitle")
    @iOSFindBy(xpath = "//XCUIElementTypeButton[contains(@name,\"Connected\")]")
    @iOSFindBy(xpath = "//XCUIElementTypeButton[contains(@name,\"Verbunden\")]")
    private MobileElement deviceInfo;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Beyerdynamic\")")
    @iOSFindBy(accessibility = "Beyerdynamic")
    private MobileElement manufacturerName;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Aventho wireless\")")
    @iOSFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,\"Aventho\")]")
    private MobileElement headphoneType;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\".\")")
    @iOSFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,\".\")]")
    private MobileElement softwareRevision;

    @iOSFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,\":\")]")
    private MobileElement bluetoothAddress;


    public void batteryIsDisplayed() {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Check that battery info is displayed");
        batteryText.isDisplayed();
    }

    public void deviceInfoIsDisplayed(String headphone) {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on device info");
        deviceInfo.click();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Check that manufacturer name is displayed");
        manufacturerName.isDisplayed();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Check that headphone type is displayed");
        headphoneType.isDisplayed();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Check that sotware revision is displayed");
        softwareRevision.isDisplayed();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Check that bluetooth address is displayed");
        if(driver.getPlatformName().toLowerCase().equals("ios")) {
            bluetoothAddress.isDisplayed();
        } else {
            bluetoothAddress = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"" + headphone + "\")"));
        }
        back.click();
    }
}
