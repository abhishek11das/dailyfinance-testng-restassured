package pages;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;

import java.io.IOException;
import java.util.List;

public class LoginLogoutPage {

    @FindBy(id = "email")
    public WebElement txtMail;

    @FindBy(id = "password")
    public WebElement txtPass;

    @FindBy(tagName = "button")
    public WebElement btnLogin;

    @FindBy(css = "[type=button]")
    List<WebElement> btnProfileIcon;

    @FindBy(css = "[role=menuitem]")
    List<WebElement> menuItem;

    @FindBy(tagName = "button")
    public List<WebElement> button;

    @FindBy(className = "search-box")  // admin dashboard search box
    public WebElement searchInput;

    @FindBy(tagName = "tr")
    List<WebElement> txtRows;

    public LoginLogoutPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void doLogin(String email, String pass) throws InterruptedException {
        txtMail.sendKeys(email);
        txtPass.sendKeys(pass);
        Thread.sleep(2000);
        btnLogin.click();
    }

    public void doLogout() {
        btnProfileIcon.get(0).click();
        menuItem.get(1).click();
    }

    public boolean searchGmail() throws IOException, ParseException, InterruptedException {  // searches in admin dashboard search
        String gmail = Utils.gettinglastUserObj().get("email").toString();
        boolean found = false;
        searchInput.sendKeys(gmail);
        for (WebElement row : txtRows) {
            String txtRow = row.getText();
            if (txtRow.contains(gmail)) {
                found = true;
            }
        }
        return found;
    }

}
