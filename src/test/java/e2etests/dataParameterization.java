package e2etests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.CartPage;
import pageobjects.OrderConfirmPage;
import pageobjects.PaymentPage;
import pageobjects.ProductsPage;
import testcomponents.BaseTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class dataParameterization extends BaseTest {

    @Test(dataProvider = "getData1", groups = "Order")
    public void submitOrder(HashMap<String, String> input) throws IOException {

        ProductsPage productsPage = loginPage.loginInToApp(input.get("email"), input.get("pwd"));
        productsPage.addPrdtToCart(input.get("prdtName"));
        CartPage cartPage = productsPage.clickCartIcon();

        Boolean matchItem = cartPage.productsInCart(input.get("prdtName"));
        Assert.assertTrue(matchItem, "Product in cart is not matched");
        PaymentPage paymentPage = cartPage.clickCheckout();

        paymentPage.selectCountry("ind");
        OrderConfirmPage orderConfirmPage = paymentPage.clickPlaceOrder();

        String thankYouMsg = orderConfirmPage.getConfirmMsg();
        Assert.assertTrue(thankYouMsg.equalsIgnoreCase("Thankyou for the order."), "Order placed message is wrong");

    }

    @DataProvider
    public Object[][] getData() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("email", "vasugiraj@gmail.com");
        map.put("pwd", "VamiSash@2025");
        map.put("prdtName", "ADIDAS ORIGINAL");

        HashMap<Object, Object> map1 = new HashMap<Object, Object>();
        map1.put("email", "vasugisathish@gmail.com");
        map1.put("pwd", "VamiSash@2025");
        map1.put("prdtName", "IPHONE 13 PRO");

        return new Object[][]{{map}, {map1}};
    }

    @DataProvider
    public Object[][] getData1() throws IOException {
        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")
                + "//src//main//java//resources//testdata.json");

        return new Object[][]{{data.get(0)}, {data.get(1)}};
    }


}
