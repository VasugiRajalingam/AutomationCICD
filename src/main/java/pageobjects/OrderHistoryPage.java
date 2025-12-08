package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import reusablecomponents.Utilities;

import java.util.List;

public class OrderHistoryPage extends Utilities {
    WebDriver driver;

    public OrderHistoryPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "tr td:nth-child(3)")
    List<WebElement> prdtsInTable;

    public Boolean verifyPrdtName(String prdtName){
        Boolean match = prdtsInTable.stream().anyMatch(prdt->prdt.getText().equalsIgnoreCase(prdtName));
        return match;
    }

}
