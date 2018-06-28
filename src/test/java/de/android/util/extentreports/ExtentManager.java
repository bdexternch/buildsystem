package de.android.util.extentreports;

import com.relevantcodes.extentreports.ExtentReports;

import java.io.File;

public class ExtentManager {
    private static ExtentReports extent;
    private static String reportFile;
    private static String workingDir = System.getProperty("user.dir");

    public synchronized static ExtentReports getReporter() {
        if (extent == null) {

            extent = new ExtentReports(reportFile, true);
            extent.loadConfig(new File(workingDir + "/src/test/resources/extent-config.xml"));
        }
        return extent;
    }

    public static void setReportFile(String platformName) {
        if(platformName.toLowerCase().equals("android")) {
            reportFile = workingDir + "/ExtentReportsAndroid" + "/report-miy-android.html";
        } else {
            reportFile = workingDir + "/ExtentReportsIOS" + "/report-miy-ios.html";
        }
    }
}
