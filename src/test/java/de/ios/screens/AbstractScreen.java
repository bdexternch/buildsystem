package de.ios.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

abstract class AbstractScreen {
    private static final int TIMEOUT = 60;

    AppiumDriver<MobileElement> driver;

    AbstractScreen(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    MobileElement findElementWithTimeout(By by) {
        return (MobileElement)(new WebDriverWait(driver, TIMEOUT)).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    MobileElement findElementByAccessibility(String id) {
        By by = MobileBy.AccessibilityId(id);
        return (MobileElement)(new WebDriverWait(driver, TIMEOUT)).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    boolean elementIsDisplayed(MobileElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    void waitForVisibility(MobileElement element) {
        new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.visibilityOf(element));
    }


    void waitForVisibilityThenClick(MobileElement element) {
        waitForVisibility(element);
        element.click();
    }

    // needed for iOS because elements in webview are present but not visible and therefore not clickable
    void tap(MobileElement element) {
        Point center = element.getCenter();
        (new TouchAction(driver))
                .tap( center.getX(),  center.getY())
                .perform();
    }

    void press(Point point, long seconds) {
//        Point point = element.getCenter();
        (new TouchAction(driver))
                .press( point.getX(),  point.getY())
                .waitAction(Duration.ofSeconds(seconds))
                .release()
                .perform();
    }

    void pressThenWait(Point point, long seconds) {
        press(point, seconds);
//            try {
//                Thread.sleep(seconds * 500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
    }

    void scrollDown() {
        Dimension size = this.driver.manage ()
                .window ()
                .getSize ();
        int startX = size.getWidth () / 2;
        int startY = size.getHeight () / 2;
        int endX = 0;
        int endY = (int) (startY * -1 * 0.5);
        TouchAction action = new TouchAction(driver);
        action.press (startX, startY)
                .moveTo (endX, endY)
                .release ()
                .perform ();
    }
}
