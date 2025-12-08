package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class FirstTest {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client");

        String prdtName = "ADIDAS ORIGINAL";

        driver.findElement(By.id("userEmail")).sendKeys("vasugiraj@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("VamiSash@2025");
        driver.findElement(By.name("login")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        List<WebElement> prdtsList = driver.findElements(By.cssSelector(".mb-3"));

        WebElement product = prdtsList.stream()
                .filter(prod -> prod.findElement(By.cssSelector("b")).getText().equals(prdtName))
                .findFirst()
                .orElse(null);

        product.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

        driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();

        List<WebElement> cartPrdts = driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean matchItem = cartPrdts.stream().anyMatch(prdt -> prdt.getText().equals(prdtName));
        Assert.assertTrue(matchItem,"Product in cart is not matched");

        driver.findElement(By.xpath("//button[text()='Checkout']")).click();

        driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("ind");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("(//*[contains(@class,'ta-item')]/span)[2]")).click();

        driver.findElement(By.cssSelector(".action__submit")).click();

        String thankYouMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(thankYouMsg.equalsIgnoreCase("Thankyou for the order."),"Order placed message is wrong");


        driver.close();
    }
}
