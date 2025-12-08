package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import reusablecomponents.Utilities;

public class OrderConfirmPage extends Utilities {
    WebDriver driver;

    public OrderConfirmPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".hero-primary")
    WebElement thankYouMsg;

    public String getConfirmMsg() {
        String actualText = thankYouMsg.getText();
        return actualText;
    }

}
