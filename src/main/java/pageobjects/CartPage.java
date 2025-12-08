package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import reusablecomponents.Utilities;

import java.util.List;

public class CartPage extends Utilities {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".cartSection h3")
    List<WebElement> cartList;

    @FindBy(xpath = "//button[text()='Checkout']")
    WebElement checkoutBtn;

    public boolean productsInCart(String prdtName) {
        Boolean matchItem = cartList.stream().anyMatch(prdt -> prdt.getText().equals(prdtName));
        return matchItem;
    }

    public PaymentPage clickCheckout(){
        checkoutBtn.click();
        PaymentPage paymentPage = new PaymentPage(driver);
        return paymentPage;
    }
}
