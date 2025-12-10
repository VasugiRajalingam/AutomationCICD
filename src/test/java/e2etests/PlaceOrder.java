package e2etests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.*;
import testcomponents.BaseTest;

import java.io.IOException;

public class PlaceOrder extends BaseTest {
    String prdtName = "ZARA COAT 3";
	// test comment for CICD webhook chnages
    @Test(dataProvider = "getData", groups = "Order")
    public void submitOrder(String email, String pwd, String prdtName) throws IOException {

        ProductsPage productsPage = loginPage.loginInToApp(email, pwd);
        productsPage.addPrdtToCart(prdtName);
        CartPage cartPage = productsPage.clickCartIcon();

        Boolean matchItem = cartPage.productsInCart(prdtName);
        Assert.assertTrue(matchItem, "Product in cart is not matched");
        PaymentPage paymentPage = cartPage.clickCheckout();

        paymentPage.selectCountry("ind");
        OrderConfirmPage orderConfirmPage = paymentPage.clickPlaceOrder();

        String thankYouMsg = orderConfirmPage.getConfirmMsg();
        Assert.assertTrue(thankYouMsg.equalsIgnoreCase("Thankyou for the order."), "Order placed message is wrong");

    }

    @Test
    public void verifyPrdtInCart() throws IOException {

        String prdtName = "IPHONE 13 PRO";

        ProductsPage productsPage = loginPage.loginInToApp("vasugiraj@gmail.com", "VamiSash@2025");
        productsPage.addPrdtToCart(prdtName);
        CartPage cartPage = productsPage.clickCartIcon();

        Boolean matchItem = cartPage.productsInCart(prdtName);
        Assert.assertTrue(matchItem, "Product in cart is not matched");
    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void verifyOrdersHistory() {
        ProductsPage productsPage = loginPage.loginInToApp("vasugiraj@gmail.com", "VamiSash@2025");
        OrderHistoryPage orderHistoryPage = productsPage.clickOrdersIcon();
        orderHistoryPage.verifyPrdtName(prdtName);
    }

    @DataProvider
    public Object[][] getData() {
        return new Object[][]{{"vasugiraj@gmail.com", "VamiSash@2025", "ADIDAS ORIGINAL"},
                {"vasugisathish@gmail.com", "VamiSash@2025", "IPHONE 13 PRO"}};
    }
}
