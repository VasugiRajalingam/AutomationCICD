package testcomponents;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import pageobjects.LoginPage;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public LoginPage loginPage;


    public WebDriver initializeDriver() throws IOException {

        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")
                + "//src//main//java//resources//GlobalData.properties");
        properties.load(fileInputStream);

        String browserName = System.getProperty("browser") != null ?
                System.getProperty("browser") : properties.getProperty("browser");
//        String browserName = properties.getProperty("browser");

        if (browserName.contains("Chrome")) {
            ChromeOptions options = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            if (browserName.contains("headless")) {
                options.addArguments("--headless=new");
                options.addArguments("--start-maximized");
//                options.addArguments("--window-size=1920,1080");
            }
            driver = new ChromeDriver(options);
//            driver.manage().window().setSize(new Dimension(1440, 900));
        } else if (browserName.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browserName.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public LoginPage launchApp() throws IOException {
        driver = initializeDriver();
        loginPage = new LoginPage(driver);
        loginPage.goTo();
        return loginPage;
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
    }

    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        String jsonDataContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, String>> data = objectMapper.readValue(jsonDataContent,
                new TypeReference<List<HashMap<String, String>>>() {
                });
        return data;
    }

    public File getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
        FileUtils.copyFile(src, dest);
        return dest;
    }
}
