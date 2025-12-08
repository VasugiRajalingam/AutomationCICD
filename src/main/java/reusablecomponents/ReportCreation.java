package reusablecomponents;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.ExtentReports;

public class ReportCreation {
    public static  ExtentReports getExtentReport() {
        String path = System.getProperty("user.dir") + "//reports//index.html";
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(path);
        extentSparkReporter.config().setReportName("Test Automation Results");
        extentSparkReporter.config().setDocumentTitle("Test Results");

        ExtentReports extentReport = new ExtentReports();
        extentReport.attachReporter(extentSparkReporter);
        extentReport.setSystemInfo("Tester", "Vasugi");
        return extentReport;
    }
}
