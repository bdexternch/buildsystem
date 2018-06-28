package de.android.screens;

import com.relevantcodes.extentreports.LogStatus;
import de.android.util.extentreports.ExtentTestManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;

public class PairingScreen extends AbstractScreen {

    public PairingScreen (AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/open_settings_button")
    private MobileElement openSettings;

    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/skip_button")
    private MobileElement pairLater;

    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/choose_button")
    private MobileElement chooseHeadphoneButton;

//    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/activityPairingPairImageView")
//    @iOSFindBy(accessibility = "PAIR YOUR DEVICE")
//    private MobileElement pairingButton;

//    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"PairableDevice(macAddress=00:20:BB:70:2F:CB, name=Aventho)\")")
//    @iOSFindBy(accessibility = "AventhoTestIOs")
//    private MobileElement pairableDevice;

    @iOSFindBy(xpath = "//XCUIElementTypeButton[@name=\"Pair\"]")
    private MobileElement pairingConfirmation;

    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/bar_headphone_state_include")
    @iOSFindBy(xpath = "//XCUIElementTypeButton[contains(@name,\"Connected\")]")
    private MobileElement deviceInfo;

    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/acknowledge_button")
    private MobileElement firmwareUpdateButton;

    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/disconnect_headphone_button")
    @iOSFindBy(accessibility = "Unpair Headphones")
    private MobileElement unpairButton;

    @iOSFindBy(accessibility = "Unpair device")
    private MobileElement unpairConfirmation;



    public void pair(String headphone) {
        this.firmwareUpdateDisplayed();

        MobileElement pairableDevice;
        ExtentTestManager.getTest().log(LogStatus.INFO, "Select headphone: " + headphone);
        if(driver.getPlatformName().toLowerCase().equals("ios")) {
            pairableDevice = driver.findElement(MobileBy.AccessibilityId(headphone));
        } else {
            pairableDevice = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"" + headphone + "\")"));
        }
        pairableDevice.click();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Choose headphone");
        chooseHeadphoneButton.click();

        if(driver.getPlatformName().toLowerCase().equals("ios")) {
            ExtentTestManager.getTest().log(LogStatus.INFO, "Confirm pairing");
            pairingConfirmation.click();
        }
    }


    public void defaultHeadset(){
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on skip button");
        pairLater.click();
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

    public boolean chooseHeadphoneButtonIsDisplayed() {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Check that choose headphone button is displayed");
        return elementIsDisplayed(chooseHeadphoneButton);
    }

    public boolean deviceInfoIsDisplayed() {
        return elementIsDisplayed(deviceInfo);
    }

    public void firmwareUpdateDisplayed() {
        if(elementIsDisplayed(firmwareUpdateButton)) {
            firmwareUpdateButton.click();
        }
    }
}
