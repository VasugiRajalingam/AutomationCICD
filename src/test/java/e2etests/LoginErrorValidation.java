package e2etests;

import org.testng.Assert;
import org.testng.annotations.Test;
import testcomponents.BaseTest;
import testcomponents.RetryListener;

public class LoginErrorValidation extends BaseTest {
    @Test(groups = {"Smoke"})
	
    public void verifyIncorrectPwdError() {
        loginPage.loginInToApp("vasugiraj@gmail.com", "VamiSathish@2025");
        String actualError = loginPage.getErrorMsg();
        Assert.assertEquals(actualError, "Incorrect email or password.", "Error message is incorrect");
    }

    @Test(retryAnalyzer = RetryListener.class)
    public void verifyIncorrectEmailError() {
        loginPage.loginInToApp("vasugiraj1233@gmail.com", "VamiSash@2025");
        String actualError = loginPage.getErrorMsg();
        Assert.assertEquals(actualError, "Incorrect email or password", "Error message is incorrect");
    }
}
