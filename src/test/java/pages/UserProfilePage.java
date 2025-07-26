package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;

import java.util.List;

public class UserProfilePage {

    @FindBy(tagName = "button")
    public List<WebElement> btnProfile;

    @FindBy(css = "[role=menuitem]")
    public List<WebElement> btnMenu;

    @FindBy(name = "email")
    WebElement txtEmail;

    public UserProfilePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void gotoProfile() {
        btnProfile.get(0).click();  // user menu to go into profile
        btnMenu.get(0).click();     // clicking on profile
    }

    public void updateGmail(String newGmail) {
        btnProfile.get(1).click();  // edit button
        txtEmail.sendKeys(Keys.CONTROL+"a", Keys.BACK_SPACE);
        txtEmail.sendKeys(newGmail);    // Email
        btnProfile.get(2).click();  // update button
    }

}

