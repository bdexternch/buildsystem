package de.concepthero.tests;

import de.concepthero.screens.*;
import de.concepthero.util.TestListener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Listeners({TestListener.class})
public class TestCases extends BaseTest {

    private PairingScreen pairingScreen;
    private ProfileScreen profileScreen;
    private TrackingScreen trackingScreen;
    private TouchpadScreen touchpadScreen;
    private InfoScreen infoScreen;
    private DeviceInfoScreen deviceInfoScreen;

    @BeforeClass
    public void setUpScreens() {
        pairingScreen = new PairingScreen(driver);
        profileScreen = new ProfileScreen(driver);
        trackingScreen = new TrackingScreen(driver);
        touchpadScreen = new TouchpadScreen(driver);
        infoScreen = new InfoScreen(driver);
        deviceInfoScreen = new DeviceInfoScreen(driver);
    }


    @Parameters({"headphone"})
    @Test(description = "Resets the app if necessary and then pairs with the specified headphone.")
    public void pairingTest(String headphone) {
        // check if app is paired with headphone
        if(pairingScreen.deviceInfoIsDisplayed()) {
            pairingScreen.unpair();
        }
        pairingScreen.pair(headphone);
        // check if profile is reseted
        if(profileScreen.updateProfileButtonIsDisplayed()) {
            profileScreen.resetProfile();
        }
    }


    @Test(alwaysRun = true, dependsOnMethods = {"pairingTest"},description = "Checks that the battery info is loaded and displayed")
    public void batteryInfoTest() {
        deviceInfoScreen.batteryIsDisplayed();
    }



    @Parameters({"headphone"})
    @Test(alwaysRun = true, dependsOnMethods = {"batteryInfoTest"},description = "Checks that the device info is loaded and displayed")
    public void deviceInfoTest(String headphone) {
        deviceInfoScreen.deviceInfoIsDisplayed(headphone);
    }


    @Test(alwaysRun = true, dependsOnMethods = {"deviceInfoTest"},
        description = "Checks that all elements of the info screen are displayed.")
    public void infoTest() {
        infoScreen.goToInfoScreen();
        infoScreen.showAbout();
        Assert.assertTrue(infoScreen.aventhoWirelessLinkIsDisplayed());
        infoScreen.showDeviceInformation();
        Assert.assertTrue(infoScreen.faqsLinkIsDisplayed());
        Assert.assertTrue(infoScreen.contactLinkIsDisplayed());
        Assert.assertTrue(infoScreen.shopLinkIsDisplayed());
        Assert.assertTrue(infoScreen.facebookLinkIsDisplayed());
        infoScreen.showPrivacyPolicy();
        infoScreen.showAppInfo();
    }


    @Test(alwaysRun = true, dependsOnMethods = {"infoTest"},
            description = "Checks that the slider is displayed.")
    public void touchpadTest() {
        touchpadScreen.showSlider();
        Assert.assertTrue(touchpadScreen.sliderIsDisplayed());
    }


    @Test(alwaysRun = true, dependsOnMethods = {"touchpadTest"},
        description = "Shows the pop-up dialog of the tracking info and accepts it.")
    public void trackingTest() {
        trackingScreen.showTrackingInfo();
    }


    @Parameters({"mic", "soundCheckAccuracy", "saveSoundCheck"})
    @Test(alwaysRun = true, dependsOnMethods = {"trackingTest"},
        description = "Tests the process of personalizing (creating a profile).\n" +
            "If a microphone is specified, it checks that there is sound coming from the headphone.")
    public void profileTest(String mic, double soundCheckAccuracy, boolean saveSoundCheck) throws Exception {
        profileScreen.goToProfileScreen();
        driver.manage().timeouts().implicitlyWait(WEBVIEW_TIMEOUT, TimeUnit.MILLISECONDS);
        profileScreen.selectAge();
        profileScreen.hearingTest(mic, soundCheckAccuracy, saveSoundCheck);
        driver.manage().timeouts().implicitlyWait(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
    }


    @Test(dependsOnMethods = {"profileTest"},
            description = "Deactivates the profile, checks that intensity is not selectable and then " +
                    "reactivates the profile and checks that the intensity is selectable.")
    public void deactivateProfileTest() {
        profileScreen.deactivateProfile();
    }


    @Test(dependsOnMethods = {"deactivateProfileTest"},
        description = "Changes the intensity randomly and asserts that the appropriate element is highlighted.")
    public void changeIntensityTest() {
        profileScreen.changeIntensity();
    }


    @Test(alwaysRun = true, dependsOnMethods = {"changeIntensityTest"},
        description = "The profile is resetted.")
    public void resetProfileTest() {
        profileScreen.resetProfile();
    }


    @Test(alwaysRun = true, dependsOnMethods = {"resetProfileTest"},
        description = "The headphone is unpaired and presence of the pairing button is checked.")
    public void unpairTest() {
        pairingScreen.unpair();
        Assert.assertTrue(pairingScreen.pairingButtonIsDisplayed());
    }
}
