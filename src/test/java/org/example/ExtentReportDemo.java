package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ExtentReportDemo {

    ExtentReports extentReport;

//    @BeforeTest
    public void reportConfiguration() {
        String path = System.getProperty("user.dir") + "//reports//index.html";
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(path);
        extentSparkReporter.config().setReportName("Test Automation Results");
        extentSparkReporter.config().setDocumentTitle("Test Results");

        extentReport = new ExtentReports();
        extentReport.attachReporter(extentSparkReporter);
        extentReport.setSystemInfo("Tester", "Vasugi");
    }

//    @Test
    public void reportSample() {
        extentReport.createTest("Sample Report Demo");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");
        System.out.println(driver.getTitle());
        extentReport.flush();
    }
}
