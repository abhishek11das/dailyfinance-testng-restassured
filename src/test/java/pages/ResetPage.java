package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResetPage {
    @FindBy(css = "[type=email]")
    public WebElement txtEmail;

    @FindBy(css = "[type=submit]")
    WebElement btnResetLink;

    public ResetPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void ResetPass(String email) {
        txtEmail.sendKeys(email);
        btnResetLink.click();
    }

}

