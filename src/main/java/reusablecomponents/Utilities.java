package reusablecomponents;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.CartPage;
import pageobjects.OrderHistoryPage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Utilities {
    WebDriver driver;


    public Utilities(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "button[routerlink*='cart']")
    WebElement cartBtn;

    @FindBy(css = "button[routerlink*='myorders']")
    WebElement ordersBtn;

    public void waitForElementVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementInvisible(WebElement ele) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(ele));
    }

    public CartPage clickCartIcon() {
        cartBtn.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }

    public OrderHistoryPage clickOrdersIcon() {
        ordersBtn.click();
        OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
        return orderHistoryPage;
    }

}
