package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ResetPasswordPage {

    @FindBy(css = "[type=password]")
    List<WebElement> txtPass;

    @FindBy(css = "[type=submit]")
    WebElement btnReset;

    public ResetPasswordPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void enterPassword(String password) {
        txtPass.get(0).sendKeys(password);
        txtPass.get(1).sendKeys(password);
        btnReset.click();
    }

}