package de.ios.screens;

import com.relevantcodes.extentreports.LogStatus;
import de.ios.util.extentreports.ExtentTestManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.HowToUseLocators;
import io.appium.java_client.pagefactory.LocatorGroupStrategy;
import io.appium.java_client.pagefactory.iOSFindBy;

public class InfoScreen extends AbstractScreen {
    public InfoScreen(AppiumDriver driver) {
        super(driver);
    }

    @HowToUseLocators(iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(accessibility = "Nach oben")
    @iOSFindBy(accessibility = "Back")
    @iOSFindBy(accessibility = "Zurück")
    private MobileElement back;

    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/activityHomeMore")
    @iOSFindBy(accessibility = "Info")
    private MobileElement info;

    @HowToUseLocators(iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/viewTabMoreAboutBeyerdynamic")
    @iOSFindBy(accessibility = "About beyerdynamic")
    @iOSFindBy(accessibility = "Über beyerdynamic")
    private MobileElement about;

    @AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().resourceId(\"com.beyerdynamic.android.dev:id/activityAboutTermsOfUse\"));")
    private MobileElement aboutTermsOfUse;

    @AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().resourceId(\"com.beyerdynamic.android.dev:id/activityAboutPrivacyPolicy\"));")
    private MobileElement aboutPrivacyPolicy;

    @HowToUseLocators(iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/viewTabMoreAboutAventhoWireless")
    @iOSFindBy(accessibility = "beyerdynamic Website")
    @iOSFindBy(accessibility = "beyerdynamic Webseite")
    private MobileElement aventhoWireless;

    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/viewTabMoreBeyerdynamicShop")
    @iOSFindBy(accessibility = "Shop")
    private MobileElement shop;

    @HowToUseLocators(iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/viewTabMoreFaqs")
    @iOSFindBy(accessibility = "FAQ")
    @iOSFindBy(accessibility = "FAQs")
    private MobileElement faqs;

    @HowToUseLocators(iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/viewTabMoreContact")
    @iOSFindBy(accessibility = "Contact")
    @iOSFindBy(accessibility = "Kontakt")
    private MobileElement contact;

    @HowToUseLocators(iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().resourceId(\"com.beyerdynamic.android.dev:id/viewTabMoreBeyerdynamicOnFacebook\"));")
    @iOSFindBy(accessibility = "beyerdynamic on Facebook")
    @iOSFindBy(accessibility = "beyerdynamic auf Facebook")
    private MobileElement facebook;

    @HowToUseLocators(iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().resourceId(\"com.beyerdynamic.android.dev:id/viewTabMoreDeviceInformation\"));")
    @iOSFindBy(accessibility = "Device information")
    @iOSFindBy(accessibility = "Geräteinformationen")
    private MobileElement deviceInformation;

    @HowToUseLocators(iOSAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().resourceId(\"com.beyerdynamic.android.dev:id/viewTabMorePrivacyPolicy\"));")
    @iOSFindBy(accessibility = "Privacy policy")
    @iOSFindBy(accessibility = "Datenschutzerklärung")
    private MobileElement privacyPolicy;

    @AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().resourceId(\"com.beyerdynamic.android.dev:id/viewTabMoreVersion\"));")
    @iOSFindBy(accessibility = "App-Info")
    private MobileElement appInfo;


    public void goToInfoScreen() {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Go to info screen");
        info.click();
    }

    public void showAbout() {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on about beyerdynamic");
        about.click();
        if(driver.getPlatformName().equals("Android")) {
            ExtentTestManager.getTest().log(LogStatus.INFO, "Click on terms of use");
            aboutTermsOfUse.click();
            ExtentTestManager.getTest().log(LogStatus.INFO, "Go back");
            back.click();
            ExtentTestManager.getTest().log(LogStatus.INFO, "Click on privacy policy");
            aboutPrivacyPolicy.click();
            ExtentTestManager.getTest().log(LogStatus.INFO, "Go back");
            back.click();
            // if same element on different screens is clicked too fast: StaleElementReferenceException in uiautomator2
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ExtentTestManager.getTest().log(LogStatus.INFO, "Go back");
        back.click();
    }

    public boolean aventhoWirelessLinkIsDisplayed() {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Check that link to Aventho wireless is present");
        return elementIsDisplayed(aventhoWireless);
    }

    public void showDeviceInformation() {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on device information");
        deviceInformation.click();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Go back");
        back.click();
    }

    public boolean faqsLinkIsDisplayed() {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Check that link to FAQ is present");
        return elementIsDisplayed(faqs);
    }

    public boolean contactLinkIsDisplayed() {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Check that link to contact is present");
        return elementIsDisplayed(contact);
    }

    public boolean facebookLinkIsDisplayed() {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Check that link to facebook is present");
        return elementIsDisplayed(facebook);
    }

    public boolean shopLinkIsDisplayed() {
        if (driver.getPlatformName().toLowerCase().equals("ios")) {
            scrollDown();
        }
        ExtentTestManager.getTest().log(LogStatus.INFO, "Check that link to shop is present");
        return elementIsDisplayed(shop);
    }

    public void showPrivacyPolicy() {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on privacy policy");
        privacyPolicy.click();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Go back");
        back.click();
    }

    public void showAppInfo() {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on app info");
        appInfo.click();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Go back");
        back.click();
    }
}
