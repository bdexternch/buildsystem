package de.concepthero.screens;

import de.concepthero.util.extentreports.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;

public class TouchpadScreen extends AbstractScreen {

    public TouchpadScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/activityHomeTouchpad")
    @iOSFindBy(accessibility = "Touch")
    private MobileElement touch;

    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/viewStepSliderSeekBar")
    @iOSFindBy(className = "XCUIElementTypeSlider")
    private MobileElement slider;

    public void showSlider() {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Go to touch screen");
        touch.click();
    }

    public boolean  sliderIsDisplayed() {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Check that slider is displayed");
        return elementIsDisplayed(slider);
    }
}
