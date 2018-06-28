package de.android.screens;

import com.relevantcodes.extentreports.LogStatus;
import de.android.util.extentreports.ExtentTestManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.testng.Assert;

public class TouchpadScreen extends de.android.screens.AbstractScreen {

    public TouchpadScreen(AppiumDriver driver) {
        super(driver);
    }

    private final String firstText = "Voriger Titel";
    private final String secondText = "Nächste Titel";
    private final String thirdText = "Anrufe annehmen";
    private final String fourthText = "Nächste Titel";

    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/touch_item")
    @iOSFindBy(accessibility = "Touch")
    private MobileElement touch;

    @AndroidFindBy(id = "swipe_right_image_view")
    private MobileElement rightArrow;

    @AndroidFindBy(id = "swipe_left_image_view")
    private MobileElement leftArrow;

    @AndroidFindBy(id = "button_for_touch1_animation")
    private MobileElement touchAnimation;

    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/touch_explained_text_view")
    private MobileElement touchExplainedText;

    @AndroidFindBy(id = "com.beyerdynamic.android.dev:id/sensitivity_seek_bar")
    @iOSFindBy(className = "XCUIElementTypeSlider")
    private MobileElement slider;

    public void showSlider() {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Go to touch screen");
        touch.click();
    }

    public void swipeExplanation() {
        TouchAction action = new TouchAction(driver);
        isText(firstText);
        action.press(rightArrow).moveTo(touchAnimation).release().perform();
        isText(secondText);
        action.press(rightArrow).moveTo(leftArrow).release().perform();
        isText(thirdText);
        action.press(rightArrow).moveTo(leftArrow).release().perform();
        isText(fourthText);
    }

    private void isText(String text){
        ExtentTestManager.getTest().log(LogStatus.INFO, "Check that text is \""+ text + "\"");
        Assert.assertEquals(touchExplainedText.getText(), text);
    }

    public boolean sliderIsDisplayed() {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Check that slider is displayed");
        return elementIsDisplayed(slider);
    }
}
