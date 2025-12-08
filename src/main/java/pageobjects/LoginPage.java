package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import reusablecomponents.Utilities;

public class LoginPage extends Utilities {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "userEmail")
    WebElement loginEmail;

    @FindBy(id = "userPassword")
    WebElement loginPassword;

    @FindBy(name = "login")
    WebElement loginBtn;

    @FindBy(css = "[class*='flyInOut']")
    WebElement loginError;

    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client/");
    }

    public ProductsPage loginInToApp(String username, String password) {
        loginEmail.sendKeys(username);
        loginPassword.sendKeys(password);
        loginBtn.click();
        ProductsPage productsPage = new ProductsPage(driver);
        return productsPage;
    }

    public String getErrorMsg(){
        waitForElementVisible(loginError);
        return loginError.getText();
    }


}
