package de.android.screens;

import com.relevantcodes.extentreports.LogStatus;
import de.android.util.extentreports.ExtentTestManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;

public class InfoScreen extends AbstractScreen {
    public InfoScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(accessibility = "Nach oben")
    @iOSFindBy(accessibility = "Back")
    private MobileElement back;

    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/information_item")
    private MobileElement info;

    @AndroidFindBy(xpath = "//android.widget.TableLayout[@resource-id = 'com.beyerdynamic.android.dev:id/information_table_layout']/android.view.ViewGroup[1]")
    @iOSFindBy(accessibility = "Device information")
    private MobileElement deviceInformation;

    @AndroidFindBy(xpath = "//android.widget.TableLayout[@resource-id = 'com.beyerdynamic.android.dev:id/information_table_layout']/android.view.ViewGroup[2]")
    @iOSFindBy(accessibility = "Shop")
    private MobileElement shop;

    @AndroidFindBy(xpath = "//android.widget.TableLayout[@resource-id = 'com.beyerdynamic.android.dev:id/information_table_layout']/android.view.ViewGroup[3]")
    @iOSFindBy(accessibility = "beyerdynamic on Facebook")
    private MobileElement facebook;

    @AndroidFindBy(xpath = "//android.widget.TableLayout[@resource-id = 'com.beyerdynamic.android.dev:id/information_table_layout']/android.view.ViewGroup[4]")
    @iOSFindBy(accessibility = "Contact")
    private MobileElement contact;

    @AndroidFindBy(xpath = "//android.widget.TableLayout[@resource-id = 'com.beyerdynamic.android.dev:id/information_table_layout']/android.view.ViewGroup[5]")
    @iOSFindBy(accessibility = "FAQ")
    private MobileElement faq;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Datenschutzerklärung\")")
    @iOSFindBy(accessibility = "Privacy policy")
    private MobileElement privacyPolicy;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Impressum\")")
    private MobileElement legalInfo;


    public void goToInfoScreen() {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Go to info screen");
        info.click();
    }

    public void showDeviceInformation() {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on device information");
        deviceInformation.click();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Go back");
        ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
    }

    public void showShop() {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on shop");
        shop.click();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Go back");
        ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
    }

    public void showFacebook() {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on facebook");
        facebook.click();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Go back");
        ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
    }

    public void showContact() {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on contact");
        contact.click();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Go back");
        ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
    }

    public void showFaq() {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on faq");
        faq.click();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Go back");
        ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
    }

    public void showPrivacyPolicy() {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on privacy policy");
        scrollDown();
        privacyPolicy.click();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Go back");
        ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
    }

    public void showLegalInfo() {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on legal info");
        legalInfo.click();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Go back");
        ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
    }
}
