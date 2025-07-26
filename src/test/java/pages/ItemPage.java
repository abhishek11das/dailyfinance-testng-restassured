package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.List;

public class ItemPage {

    @FindBy(className = "add-cost-button")
    WebElement btnAddCost;

    @FindBy(id = "itemName")
    WebElement txtItemName;

    @FindBy(id = "amount")
    WebElement txtamount;

    @FindBy(id = "remarks")
    WebElement txtRemarks;

    @FindBy(className = "submit-button")
    WebElement btnSubmit;

    public ItemPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void addingItem (String itemName, String amount, String remarks) {
        btnAddCost.click();
        txtItemName.sendKeys(itemName);
        txtamount.sendKeys(amount);
        txtRemarks.sendKeys(remarks);
        btnSubmit.click();
    }

    public void addingItem (String itemName, String amount) {
        btnAddCost.click();
        txtItemName.sendKeys(itemName);
        txtamount.sendKeys(amount);
        btnSubmit.click();
    }
}
