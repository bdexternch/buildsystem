package de.concepthero.screens;

import de.concepthero.util.extentreports.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.HowToUseLocators;
import io.appium.java_client.pagefactory.LocatorGroupStrategy;
import io.appium.java_client.pagefactory.iOSFindBy;

public class PairingScreen extends AbstractScreen{

    public PairingScreen (AppiumDriver driver) {
        super(driver);
    }

    @HowToUseLocators(iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/activityPairingPairImageView")
    @iOSFindBy(accessibility = "PAIR YOUR DEVICE")
    @iOSFindBy(accessibility = "KOPFHÖRER KOPPELN")
    private MobileElement pairingButton;

    @HowToUseLocators(iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @iOSFindBy(xpath = "//XCUIElementTypeButton[@name=\"Pair\"]")
    @iOSFindBy(xpath = "//XCUIElementTypeButton[@name=\"Verbinden\"]")
    private MobileElement pairingConfirmation;

    @HowToUseLocators(iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/viewStatusIconSubtitle")
    @iOSFindBy(xpath = "//XCUIElementTypeButton[contains(@name,\"Connected\")]")
    @iOSFindBy(xpath = "//XCUIElementTypeButton[contains(@name,\"Verbunden\")]")
    private MobileElement deviceInfo;

    @HowToUseLocators(iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/activityDeviceInformationUnPair")
    @iOSFindBy(accessibility = "Unpair Headphones")
    @iOSFindBy(accessibility = "Kopfhörer trennen")
    private MobileElement unpairButton;

    @HowToUseLocators(iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @iOSFindBy(accessibility = "Unpair device")
    @iOSFindBy(accessibility = "Gerät trennen")
    private MobileElement unpairConfirmation;


    public void pair(String headphone) {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on pairing button");
        pairingButton.click();

        MobileElement pairableDevice;
        ExtentTestManager.getTest().log(LogStatus.INFO, "Select headphone: " + headphone);
        if(driver.getPlatformName().toLowerCase().equals("ios")) {
            pairableDevice = driver.findElement(MobileBy.AccessibilityId(headphone));
        } else {
            pairableDevice = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"" + headphone + "\")"));
        }
        pairableDevice.click();

        if(driver.getPlatformName().toLowerCase().equals("ios")) {
            ExtentTestManager.getTest().log(LogStatus.INFO, "Confirm pairing");
            pairingConfirmation.click();
        }
    }

    public void unpair() {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on device info");
        deviceInfo.click();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on unpair button");
        unpairButton.click();

        if(driver.getPlatformName().toLowerCase().equals("ios")) {
            ExtentTestManager.getTest().log(LogStatus.INFO, "Confirm unpairing");
            unpairConfirmation.click();
        }
    }

    public boolean pairingButtonIsDisplayed() {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Check that pairing button is displayed");
        return elementIsDisplayed(pairingButton);
    }

    public boolean deviceInfoIsDisplayed() {
        return elementIsDisplayed(deviceInfo);
    }
}
