package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UserDashboardPage {

    @FindBy(tagName = "tr")
    List<WebElement> txtRows;

    public UserDashboardPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public boolean itemExists(String item_name) {
        boolean exists = false;
        for(WebElement row : txtRows) {
            String txtRow = row.getText();
            if (txtRow.contains(item_name)) {
                exists = true;
            }
        }
        return exists;
    }

}
