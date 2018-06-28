package de.android.screens;

import com.relevantcodes.extentreports.LogStatus;
import de.android.exceptions.NoSoundException;
import de.android.exceptions.NoSuchRecordingDeviceException;
import de.android.tests.BaseTest;
import de.android.util.extentreports.ExtentTestManager;
import de.android.util.micUtil.Config;
import de.android.util.micUtil.FileOperations;
import de.android.util.micUtil.Record;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.HowToUseLocators;
import io.appium.java_client.pagefactory.LocatorGroupStrategy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ProfileScreen extends AbstractScreen {

    private static final int PERSONALIZATION_TIMEOUT = 15000;
    private static final int HEARING_TEST_BUTTON_PRESS = 3;
    private static final int HEARING_TEST_LOADING_TIME = 5000;
    private static final int NUM_RANDOM_CLICKS_INTENSITY = 10;

    private List<MobileElement> intensityButtons = new ArrayList<>();

    public ProfileScreen (AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(accessibility = "Nach oben navigieren")
    @iOSFindBy(accessibility = "X")
    private MobileElement back;

    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/profile_item")
    @iOSFindBy(accessibility = "Profile")
    private MobileElement profile;

    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/start_now_button")
    @iOSFindBy(accessibility = "START NOW")
    private MobileElement soundOnboardingButton;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.view.View[@resource-id ='root']/android.view.View[6]/android.view.View/android.widget.Button")
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Los geht's\"]")
    @iOSFindBy(accessibility = "Los geht's")
    @iOSFindBy(accessibility = "Let's start")
    private MobileElement startPersonalization;

//    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[4]/android.widget.GridView/android.view.View[1]/android.view.View[3]")
//    private MobileElement moreYearsRight;
//
//    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[4]/android.view.View[4]")
//    private MobileElement year1992;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.view.View[@resource-id ='root']/android.view.View[5]/android.widget.Button")
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Personalisieren\"]")
    @AndroidFindBy(accessibility = "Personalisieren")
    @iOSFindBy(accessibility = "Personalisieren")
    private MobileElement personalizeButton;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.view.View[@resource-id ='root']/android.view.View[1]/android.view.View/android.view.View[4]/android.view.View")
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Test vervollständigen\"]")
    @AndroidFindBy(id = "completeTest")
    @iOSFindBy(accessibility = "complete test")
    private MobileElement completeTestButton;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.view.View[@resource-id ='root']/android.view.View[6]/android.view.View/android.widget.Button")
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Ich bin bereit\"]")
    @iOSFindBy(accessibility = "Ich bin bereit")
    @iOSFindBy(accessibility = "I'm ready")
    private MobileElement readyButton;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Weiter\"]")
    @AndroidFindBy(xpath = "//android.view.View[@resource-id ='root']/android.view.View[6]/android.view.View/android.widget.Button")
    @AndroidFindBy(id = "adjust-volume-button")
    @iOSFindBy(accessibility = "Weiter")
    @iOSFindBy(accessibility = "it's adjusted")
    private MobileElement adjustVolumeButton;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.view.View[@resource-id = 'root']/android.view.View[5]/android.view.View/android.widget.Button")
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Ich bin bereit\"]")
    @iOSFindBy(accessibility = "Ich bin bereit")
    @iOSFindBy(accessibility = "I'm ready")
    private MobileElement readyButton2;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.view.View[@resource-id ='root']/android.widget.Button")
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Los geht's Los geht's\"]")
//    @AndroidFindBy(id = "hearing-test-button")
    @iOSFindBy(accessibility = "Los geht's")
    @iOSFindBy(accessibility = "start")
    private MobileElement startHearingTestButton;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.view.View[@resource-id ='root']/android.widget.Button")
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Ich höre den Ton! Los geht's\"]")
//    @AndroidFindBy(id = "hearing-test-button")
    @iOSFindBy(accessibility = "Ich höre den Ton!")
    @iOSFindBy(accessibility = "I hear the beep!")
    private MobileElement hearingTestButton;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.view.View[@resource-id = 'root']/android.view.View[5]/android.view.View/android.widget.Button")
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Weiter geht's\"]")
    @iOSFindBy(accessibility = "Weiter geht's")
    @iOSFindBy(accessibility = "let's continue")
    private MobileElement continueHearingTestButton;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.view.View[@resource-id = 'root']/android.view.View[6]/android.view.View/android.widget.Button")
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Personalisieren\"]")
    @AndroidFindBy(accessibility = "Personalisieren")
    @iOSFindBy(accessibility = "Personalisieren")
    @iOSFindBy(accessibility = "personalize")
    private MobileElement personalizeButton2;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.view.View[@resource-id = 'root']/android.view.View[1]/android.view.View/android.view.View[3]/android.widget.Button")
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Fertig\"]")
    @AndroidFindBy(id = "done")
    @AndroidFindBy(accessibility = "Fertig")
    @iOSFindBy(accessibility = "Fertig")
    @iOSFindBy(accessibility = "done")
    private MobileElement doneButton;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Profil zurücksetzen\")")
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Profil zurücksetzen\"]")
//    @AndroidFindBy(id = "reset")
//    @AndroidFindBy(accessibility = "Profil zurücksetzen")
//    @AndroidFindBy(xpath = "//android.view.View[@resource-id = 'root']/android.view.View[6]/android.view.View")
    @iOSFindBy(accessibility = "Profil zurücksetzen")
    @iOSFindBy(accessibility = "reset Sound Profile")
    private MobileElement reset;

    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/switch_sound_profile_dsp_button")
    private MobileElement dspButton;

//    @iOSFindBy(accessibility = "Activated")
//    private MobileElement activatedButton;
//
//    @iOSFindBy(accessibility = "Deactivated")
//    private MobileElement deactivatedButton;

    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/button100")
    @iOSFindBy(accessibility = "100")
    private MobileElement intensity100;

    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/button80")
    @iOSFindBy(accessibility = "80")
    private MobileElement intensity80;

    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/button60")
    @iOSFindBy(accessibility = "60")
    private MobileElement intensity60;

    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/button40")
    @iOSFindBy(accessibility = "40")
    private MobileElement intensity40;

    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/button20")
    @iOSFindBy(accessibility = "20")
    private MobileElement intensity20;

    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/update_button")
    @iOSFindBy(accessibility = "UPDATE YOUR PROFILE")
    private MobileElement updateProfileButton;


    public void goToProfileScreen() {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Go to profile screen");
        profile.click();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Start profile creation");
        if (elementIsDisplayed(updateProfileButton)) {
            updateProfileButton.click();
        } else {
            soundOnboardingButton.click();
        }
        try {
            Thread.sleep(BaseTest.WEBVIEW_TIMEOUT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scrollDown();
        if (elementIsDisplayed(reset)) {
            reset.click();
        }
    }

    public void selectAge() {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Go to age selection");
        tap(startPersonalization);
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on personalize button");
        tap(personalizeButton);
    }

    public void hearingTest(String mic, double soundCheckAccuracy, boolean saveSoundCheck) throws Exception {
        // wait because button seems to be present even though the screen is loading
        try {
            Thread.sleep(PERSONALIZATION_TIMEOUT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on complete test button");
        tap(completeTestButton);
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on ready button");
        tap(readyButton);
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on next button");
        tap(adjustVolumeButton);
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on ready button");
        tap(readyButton2);
        // wait for the button to be loaded
        try {
            Thread.sleep(HEARING_TEST_LOADING_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ExtentTestManager.getTest().log(LogStatus.INFO, "Start hearing test");
        tap(startHearingTestButton);

        // decrease timeout because element identifiers depend on device (android)
        // if timeout is too long hearing test continues without button being pushed
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        checkSound(mic, soundCheckAccuracy, saveSoundCheck);

        try {
            ExtentTestManager.getTest().log(LogStatus.INFO, "Press and release hearing test button continously");
            while (hearingTestButton.isEnabled()) {
                press(hearingTestButton.getCenter(), HEARING_TEST_BUTTON_PRESS);
            }
        } catch (NoSuchElementException ex) {
            // hearing test finished -> continue
        }

        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on continue button");
        tap(continueHearingTestButton);

        // wait for the button to be loaded
        try {
            Thread.sleep(HEARING_TEST_LOADING_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ExtentTestManager.getTest().log(LogStatus.INFO, "Start second hearing test");
        tap(startHearingTestButton);

        checkSound(mic, soundCheckAccuracy, saveSoundCheck);

        try {
            ExtentTestManager.getTest().log(LogStatus.INFO, "Press and release hearing test button continously");
            while (hearingTestButton.isEnabled()) {
                press(hearingTestButton.getCenter(), HEARING_TEST_BUTTON_PRESS);
            }
        } catch (NoSuchElementException ex) {
            // hearing test finished -> continue
        }
        driver.manage().timeouts().implicitlyWait(BaseTest.WEBVIEW_TIMEOUT, TimeUnit.MILLISECONDS);

        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on personalize button");
        tap(personalizeButton2);
        // wait because button seems to be present even though the screen is loading
        try {
            Thread.sleep(PERSONALIZATION_TIMEOUT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on done button");
        tap(doneButton);
    }

    public void changeIntensity() {
        intensityButtons.add(intensity20);
        intensityButtons.add(intensity40);
        intensityButtons.add(intensity60);
        intensityButtons.add(intensity80);
        intensityButtons.add(intensity100);

        clickIntensityButtonsRandomly(NUM_RANDOM_CLICKS_INTENSITY );
    }

    public void deactivateProfile() throws Exception {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Deactivate profile");
        dspButton.click();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Check that profile is deactivated");
        if (elementIsDisplayed(intensity20)) {
            throw new Exception("Deactivated profile but intensity buttons visible");
        }
        ExtentTestManager.getTest().log(LogStatus.INFO, "Activate profile");
        dspButton.click();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Check that profile is deactivated");
        intensity20.isDisplayed();
    }

    public void resetProfile() {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on update profile button");
        updateProfileButton.click();
        try {
            Thread.sleep(BaseTest.WEBVIEW_TIMEOUT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scrollDown();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Reset profile");
        tap(reset);
        ExtentTestManager.getTest().log(LogStatus.INFO, "Go back");
        back.click();
    }

    public boolean updateProfileButtonIsDisplayed() {
        return elementIsDisplayed(updateProfileButton);
    }

    private void checkSound(String mic, double soundCheckAccuracy, boolean saveSoundCheck) throws NoSuchRecordingDeviceException, NoSoundException {
        if(mic.length() > 0) {
            Config config = FileOperations.loadConfig(mic, soundCheckAccuracy, saveSoundCheck);
            boolean sound = new Record().record(config);
            ExtentTestManager.getTest().log(LogStatus.INFO, "Check that sound is coming from headphone");
            if (!sound) {
                throw new NoSoundException("No sound heard by " + mic);
            }
            press(hearingTestButton.getCenter(), Record.RECORDING_TIME / 1000);
        }
    }

    private void clickIntensityButtonsRandomly(int times) {
        Random rand = new Random();
        for (int i = 0; i < times; i++) {
            int index = rand.nextInt(intensityButtons.size());
            MobileElement selectedIntensity = intensityButtons.get(index);
            ExtentTestManager.getTest().log(LogStatus.INFO, "Select 'Intensity " + getIntensityName(selectedIntensity) + "'");
            selectedIntensity.click();

            assertCorrectIntensity(selectedIntensity);
        }
    }

    private void assertCorrectIntensity(MobileElement selectedIntensity) {
        for (MobileElement intensity : intensityButtons) {
            if (driver.getPlatformName().toLowerCase().equals("android")) {
                if (intensity == selectedIntensity) {
                    Assert.assertEquals(intensity.getAttribute("checked"), "true");
                } else {
                    Assert.assertEquals(intensity.getAttribute("checked"), "false");
                }
            } else {
                if (intensity == selectedIntensity) {
                    Assert.assertEquals(intensity.getAttribute("value"), "1");
                } else {
                    Assert.assertNull(intensity.getAttribute("value"));
                }
            }
        }
    }

    private String getIntensityName(MobileElement intensity) {
        return intensity.toString().substring(intensity.toString().length()-4, intensity.toString().length()-2);
    }

}
