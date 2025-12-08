package stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageobjects.*;
import testcomponents.BaseTest;

import java.io.IOException;

public class StepDefImplementation extends BaseTest {
    public LoginPage loginPage;
    public ProductsPage productsPage;
    public CartPage cartPage;
    public PaymentPage paymentPage;
    public OrderConfirmPage orderConfirmPage;

    @Given("Launch the application")
    public void Launch_the_application() throws IOException {
        loginPage = launchApp();
    }

    @Given("^Logged in to app using (.+) and (.+)$")
    public void Login_to_app(String username, String password) {
        productsPage = loginPage.loginInToApp(username, password);
    }

    @When("^Add a (.+) to cart$")
    public void add_Prdt_To_cart(String productName) {
        productsPage.addPrdtToCart(productName);
        cartPage = productsPage.clickCartIcon();
    }

    @And("^checkout the (.+) and submit an order$")
    public void checkout_and_submit(String prdtName) {
        CartPage cartPage = productsPage.clickCartIcon();

        Boolean matchItem = cartPage.productsInCart(prdtName);
        Assert.assertTrue(matchItem, "Product in cart is not matched");
        paymentPage = cartPage.clickCheckout();

        paymentPage.selectCountry("ind");
        orderConfirmPage = paymentPage.clickPlaceOrder();
    }

    @Then("verify {string} message is displaying")
    public void verify_confirm_msg(String msg) {
        String thankYouMsg = orderConfirmPage.getConfirmMsg();
        Assert.assertTrue(thankYouMsg.equalsIgnoreCase(msg));
    }

    @Then("{string} message is displaying")
    public void verify_error_msg(String expectedError) {
        String actualError = loginPage.getErrorMsg();
        Assert.assertEquals(actualError, expectedError, "Error message is incorrect");
    }

    @Then("close browser")
    public void close_browser(){
        driver.close();
    }


}
