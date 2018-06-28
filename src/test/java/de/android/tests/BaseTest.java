package de.android.tests;

import de.android.util.ConfigurationListener;
import de.android.util.TestListener;
import de.android.util.extentreports.ExtentManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Listeners({TestListener.class, ConfigurationListener.class})
public abstract class BaseTest {
    public static long DEFAULT_TIMEOUT;
    public static long WEBVIEW_TIMEOUT;
    private static int LOCAL_PORT = 8100;
    private static int SYSTEM_PORT = 8200;

    private AppiumDriverLocalService appium;
    AppiumDriver<MobileElement> driver;

    @Parameters({"platformName", "deviceName", "udid", "appDir", "xcodeOrgId", "appPackage", "appActivity", "timeout", "webviewTimeout"})
    @BeforeClass
    public void setUp(String platformName, String deviceName, String udid, String appDir, @Optional("") String xcodeOrgId, @Optional("") String appPackage, @Optional("") String appActivity, int timeout, int webviewTimeout, ITestContext context) throws Exception {
        // seconds to milliseconds
        DEFAULT_TIMEOUT = timeout * 1000;
        WEBVIEW_TIMEOUT = webviewTimeout * 1000;
        ExtentManager.setReportFile(platformName);
        ExtentManager.getReporter().addSystemInfo("Default Timeout", Integer.toString(timeout) + " s");
        ExtentManager.getReporter().addSystemInfo("Webview Timeout", Integer.toString(webviewTimeout) + " s");
        startAppium();
        driverSetUp(platformName, deviceName, udid, appDir, xcodeOrgId, appPackage, appActivity);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (appium != null) {
            appium.stop();
        }
    }

    private void startAppium() {
        appium = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingAnyFreePort());
        appium.start();
    }

    private void driverSetUp(String platformName, String deviceName, String udid, String appDir, String xcodeOrgId, String appPackage, String appActivity) throws Exception {
        //Set the Desired Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", platformName);
        caps.setCapability("deviceName", deviceName);
        caps.setCapability("udid", udid);
        caps.setCapability("noReset", true);
        caps.setCapability("app", getApp(appDir));

        if(platformName.toLowerCase().equals("ios")) {
            caps.setCapability("automationName", "XCUITest");
            caps.setCapability("xcodeOrgId", xcodeOrgId);
            caps.setCapability("xcodeSigningId", "iPhone Developer");
            caps.setCapability("startIWDP", true);
            caps.setCapability("wdaLocalPort", LOCAL_PORT++);
            driver = new IOSDriver<>(appium.getUrl(), caps);
            // app isn't automatically reinstalled by appium
            Map<String, Object> terminateArgs = new HashMap<>();
            terminateArgs.put("bundleId", "com.beyerdynamic.ios");
            driver.executeScript("mobile: terminateApp", terminateArgs);
            Map<String, Object> installArgs = new HashMap<>();
            installArgs.put("app", getApp(appDir));
            driver.executeScript("mobile: installApp", installArgs);
            driver.launchApp();
        } else {
            caps.setCapability("appPackage", appPackage);
            caps.setCapability("appActivity", appActivity);
            caps.setCapability("autoGrantPermissions", true);
            caps.setCapability("automationName", "uiautomator2");
            caps.setCapability("skipUnlock", true);
            caps.setCapability("systemPort", SYSTEM_PORT++);
            driver = new AndroidDriver<>(appium.getUrl(), caps);
            // app isn't automatically reinstalled by appium
            driver.closeApp();
            executeShellCommand("adb", "-s", udid, "install", "-r", "-d", getApp(appDir));
            executeShellCommand("adb", "-s", udid, "shell", "am", "start", "-n", appPackage + "/" + appActivity);
        }
        driver.manage().timeouts().implicitlyWait(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    public static String getApp(String appDir) throws Exception {
        File dir = new File(appDir);
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.getName().endsWith(".app") || file.getName().endsWith(".apk")) {
                return file.getAbsolutePath();
            }
        }
        throw new Exception("App file not found in: " + appDir);
    }

    public static void executeShellCommand(String... command) {
        ProcessBuilder pb = new ProcessBuilder(command);
        Process pc;
        try {
            pc = pb.start();
            pc.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  AppiumDriver<MobileElement> getDriver() {
        return driver;
    }
}