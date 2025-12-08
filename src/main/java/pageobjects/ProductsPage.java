package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import reusablecomponents.Utilities;

import java.util.List;

public class ProductsPage extends Utilities {
    WebDriver driver;

    public ProductsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".mb-3")
    List<WebElement> prdtsList;
    @FindBy(css=".ng-animating")
    WebElement prdtLoading;


    By prdtResults = By.cssSelector(".mb-3");
    By addCartBtn = By.cssSelector(".card-body button:last-of-type");
    By prdtaddedMsg = By.id("toast-container");

    public List<WebElement> getProductsList() {
        waitForElementVisible(prdtResults);
        return prdtsList;
    }

    public WebElement getProduct(String prdtName) {
        WebElement product = prdtsList.stream()
                .filter(prod -> prod.findElement(By.cssSelector("b")).getText().equals(prdtName))
                .findFirst()
                .orElse(null);
        return product;
    }

    public void addPrdtToCart(String prdtName) {
        WebElement prod = getProduct(prdtName);
        prod.findElement(addCartBtn).click();
        waitForElementInvisible(prdtLoading);
//        waitForElementVisible(prdtaddedMsg);
    }



}
