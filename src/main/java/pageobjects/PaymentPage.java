package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import reusablecomponents.Utilities;

public class PaymentPage extends Utilities {
    WebDriver driver;

    public PaymentPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[placeholder='Select Country']")
    WebElement countryDropdown;
    @FindBy(xpath = "(//*[contains(@class,'ta-item')]/span)[2]")
    WebElement valueDropdown;
    @FindBy(css = ".action__submit")
    WebElement placeOrderBtn;
    By dropdownResults = By.cssSelector(".ta-results");

    public void selectCountry(String letters) {
        countryDropdown.sendKeys(letters);
        waitForElementVisible(dropdownResults);
        valueDropdown.click();
    }

    public OrderConfirmPage clickPlaceOrder() {
        placeOrderBtn.click();
        OrderConfirmPage orderConfirmPage = new OrderConfirmPage(driver);
        return orderConfirmPage;
    }

}
