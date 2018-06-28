package de.ios.screens;

import com.relevantcodes.extentreports.LogStatus;
import de.ios.exceptions.NoSoundException;
import de.ios.tests.BaseTest;
import de.ios.util.Config;
import de.ios.util.FileOperations;
import de.ios.util.Record;
import de.ios.util.extentreports.ExtentTestManager;
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

public class ProfileScreen extends AbstractScreen {

    private List<MobileElement> intensityButtons = new ArrayList<>();

    public ProfileScreen (AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(accessibility = "Nach oben navigieren")
    @iOSFindBy(accessibility = "X")
    private MobileElement back;

    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/activityHomeSound")
    @iOSFindBy(accessibility = "Profile")
    private MobileElement profile;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/viewTabSoundOnboardingButton")
    @iOSFindBy(accessibility = "START NOW")
    @iOSFindBy(accessibility = "JETZT STARTEN")
    private MobileElement soundOnboardingButton;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.view.View[@resource-id ='root']/android.view.View[6]/android.view.View/android.widget.Button")
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Los geht's\"]")
    @iOSFindBy(accessibility = "Let's start")
    @iOSFindBy(accessibility = "Los geht's")
    private MobileElement selectAgeButton;

    @AndroidFindBy(accessibility = "›")
    @iOSFindBy(accessibility = "›")
    private MobileElement moreYearsRight;

    @AndroidFindBy(xpath = "1992")
    @iOSFindBy(xpath = "1992")
    private MobileElement year1992;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.view.View[@resource-id ='root']/android.view.View[5]/android.widget.Button")
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Personalisieren\"]")
    @AndroidFindBy(accessibility = "Personalisieren")
    @iOSFindBy(accessibility = "personalize")
    @iOSFindBy(accessibility = "Personalisieren")
    private MobileElement personalizeButton;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View[4]/android.view.View/android.widget.Button")
    @AndroidFindBy(id = "completeTest")
    @iOSFindBy(accessibility = "complete test")
    @iOSFindBy(accessibility = "Test vervollständigen")
    private MobileElement completeTestButton;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.view.View[@resource-id ='root']/android.view.View[6]/android.view.View/android.widget.Button")
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Ich bin bereit\"]")
    @iOSFindBy(accessibility = "I'm ready")
    @iOSFindBy(accessibility = "Ich bin bereit")
    private MobileElement readyButton;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.view.View[@resource-id ='adjust-volume-button']/android.widget.Button")
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Weiter\"]")
    @iOSFindBy(accessibility = "it's adjusted")
    @iOSFindBy(accessibility = "Weiter")
    private MobileElement adjustVolumeButton;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.view.View[@resource-id = 'root']/android.view.View[5]/android.view.View/android.widget.Button")
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Ich bin bereit\"]")
    @iOSFindBy(accessibility = "I'm ready")
    @iOSFindBy(accessibility = "Ich bin bereit")
    private MobileElement readyButton2;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.widget.Button")
    @AndroidFindBy(id = "hearing-test-button")
    @iOSFindBy(accessibility = "Start")
    @iOSFindBy(accessibility = "Los geht's")
    private MobileElement startHearingTestButton;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.widget.Button")
    //@AndroidFindBy(id = "hearing-test-button")
    @iOSFindBy(accessibility = "I can hear the beep")
    //@iOSFindBy(accessibility = "Ich höre den Ton!")
    private MobileElement hearingTestButton;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.view.View[@resource-id = 'root']/android.view.View[5]/android.view.View/android.widget.Button")
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Weiter geht's\"]")
    @iOSFindBy(accessibility = "let's continue")
    @iOSFindBy(accessibility = "Weiter geht's")
    private MobileElement continueHearingTestButton;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.view.View[@resource-id = 'root']/android.view.View[6]/android.view.View/android.widget.Button")
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Personalisieren\"]")
    @AndroidFindBy(accessibility = "Personalisieren")
    @iOSFindBy(accessibility = "personalize")
    @iOSFindBy(accessibility = "Personalisieren")
    private MobileElement personalizeButton2;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View[3]/android.widget.Button")
    @AndroidFindBy(id = "done")
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Fertig\"]")
    @iOSFindBy(accessibility = "done")
    @iOSFindBy(accessibility = "Fertig")
    private MobileElement doneButton;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(id = "reset")
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Profil zurücksetzen\"]")
    @iOSFindBy(accessibility = "reset Sound Profile")
    @iOSFindBy(accessibility = "Profil zurücksetzen")
    private MobileElement reset;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/viewTabSoundOnboardedProfileButton")
    @iOSFindBy(accessibility = "Activated")
    //@iOSFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Activated\"]")
    @iOSFindBy(accessibility = "Aktiviert")
    private MobileElement activatedButton;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/viewTabSoundOnboardedProfileButton")
    @iOSFindBy(accessibility = "Deactivated")
    //@iOSFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Deactivated\"]")
    @iOSFindBy(accessibility = "Deaktiviert")
    private MobileElement deactivatedButton;

    @AndroidFindBy(id = "button100")
    @iOSFindBy(accessibility = "100")
    private MobileElement intensity100;

    @AndroidFindBy(id = "button80")
    @iOSFindBy(accessibility = "80")
    private MobileElement intensity80;

    @AndroidFindBy(id = "button60")
    @iOSFindBy(accessibility = "60")
    private MobileElement intensity60;

    @AndroidFindBy(id = "button40")
    @iOSFindBy(accessibility = "40")
    private MobileElement intensity40;

    @AndroidFindBy(id = "button20")
    @iOSFindBy(accessibility = "20")
    private MobileElement intensity20;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/viewTabSoundOnboardedButton")
    @iOSFindBy(accessibility = "UPDATE YOUR PROFILE")
    @iOSFindBy(accessibility = "AKTUALISIERE DEIN PROFIL")
    private MobileElement updateProfileButton;

    public void goToProfileScreen() {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Go to profile screen");
        profile.click();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Start profile creation");
        soundOnboardingButton.click();
    }

    public void selectAge() {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Go to age selection");
        tap(selectAgeButton);
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on personalize button");
        tap(personalizeButton);
    }

    public void hearingTest(String mic, double soundCheckAccuracy, boolean saveSoundCheck) throws Exception {
        // wait because button seems to be present even though the screen is loading
        try {
            Thread.sleep(15000);
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
        ExtentTestManager.getTest().log(LogStatus.INFO, "Start hearing test");
        tap(startHearingTestButton);

        if(mic.length() > 0) {
            Config config = FileOperations.loadConfig(mic, soundCheckAccuracy, saveSoundCheck);
            boolean sound = new Record().record(config);
            ExtentTestManager.getTest().log(LogStatus.INFO, "Check that sound is coming from headphone");
            if (!sound) {
                throw new NoSoundException("No sound heard by " + mic);
            }
            press(hearingTestButton.getCenter(), 10);
        }

        try {
            ExtentTestManager.getTest().log(LogStatus.INFO, "Press and release hearing test button continously");
            while (hearingTestButton.isEnabled()) {
                press(hearingTestButton.getCenter(), 3);
            }
        } catch (NoSuchElementException ex) {
            // hearing test finished -> continue
        }

        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on continue button");
        tap(continueHearingTestButton);

        ExtentTestManager.getTest().log(LogStatus.INFO, "Start second hearing test");
        tap(startHearingTestButton);

        if(mic.length() > 0) {
            Config config = FileOperations.loadConfig(mic, soundCheckAccuracy, saveSoundCheck);
            boolean sound = new Record().record(config);
            ExtentTestManager.getTest().log(LogStatus.INFO, "Check that sound is coming from headphone");
            if (!sound) {
                throw new NoSoundException("No sound heard by " + mic);
            }
            press(hearingTestButton.getCenter(), 10);
        }

        try {
            ExtentTestManager.getTest().log(LogStatus.INFO, "Press and release hearing test button continously");
            while (hearingTestButton.isEnabled()) {
                press(hearingTestButton.getCenter(), 3);
            }
        } catch (NoSuchElementException ex) {
            // hearing test finished -> continue
        }

        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on personalize button");
        tap(personalizeButton2);
        // wait because button seems to be present even though the screen is loading
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on done button");
        tap(doneButton);

        ExtentTestManager.getTest().log(LogStatus.INFO, "Go back");
        back.click();
    }

    public void deactivateProfile() {
        activatedButton.isDisplayed();
        intensity100.click();
        ExtentTestManager.getTest().log(LogStatus.INFO, "'Intensity " + getIntensityName(intensity100) + "' selected");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on activated button");
        activatedButton.click();
        intensity20.click();
        ExtentTestManager.getTest().log(LogStatus.INFO, "'Intensity " + getIntensityName(intensity20) + "' selected");
        assertCorrectIntensity(intensity100);
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on deactivated button");
        deactivatedButton.click();
        intensity20.click();
        ExtentTestManager.getTest().log(LogStatus.INFO, "'Intensity " + getIntensityName(intensity20) + "' selected");
        assertCorrectIntensity(intensity20);
    }

    public void changeIntensity() {
        intensityButtons.add(intensity20);
        intensityButtons.add(intensity40);
        intensityButtons.add(intensity60);
        intensityButtons.add(intensity80);
        intensityButtons.add(intensity100);

        clickIntensityButtonsRandomly(10);
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

    private void assertCorrectIntensity(MobileElement selectedIntensity) {
        if (driver.getPlatformName().toLowerCase().equals("android")) {
            for (MobileElement intensity : intensityButtons) {
                if (intensity == selectedIntensity) {
                    Assert.assertEquals(intensity.getAttribute("checked"), "true");
                } else {
                    Assert.assertEquals(intensity.getAttribute("checked"), "false");
                }
            }
        } else {
        for (MobileElement intensity : intensityButtons) {
            if (intensity == selectedIntensity) {
                Assert.assertEquals(intensity.getAttribute("value"), "1");
            } else {
                Assert.assertNull(intensity.getAttribute("value"));
            }
        }
    }
}

    private void clickIntensityButtonsRandomly(int times) {
        Random rand = new Random();
        for (int i = 0; i < times; i++) {
            int index = rand.nextInt(intensityButtons.size());
            MobileElement selectedIntensity = intensityButtons.get(index);
            selectedIntensity.click();
            ExtentTestManager.getTest().log(LogStatus.INFO, "'Intensity " + getIntensityName(selectedIntensity) + "' selected");

            assertCorrectIntensity(selectedIntensity);
        }
    }

    private String getIntensityName(MobileElement intensity) {
        return intensity.toString().substring(intensity.toString().length()-4, intensity.toString().length()-2);
    }

}
