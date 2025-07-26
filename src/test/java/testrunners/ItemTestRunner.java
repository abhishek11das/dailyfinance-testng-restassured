package testrunners;

import config.Setup;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ItemPage;
import pages.LoginLogoutPage;
import pages.UserDashboardPage;
import pages.UserProfilePage;
import utils.Utils;

import java.io.IOException;
import java.time.Duration;

public class ItemTestRunner extends Setup {

    private LoginLogoutPage loginLogoutPage;
    private UserProfilePage userProfilePage;
    private ItemPage itemPage;

    public ItemTestRunner() throws IOException, ParseException, InterruptedException {
    }

    @BeforeClass
    public void init(){
        loginLogoutPage = new LoginLogoutPage(driver);
        userProfilePage = new UserProfilePage(driver);
        itemPage = new ItemPage(driver);
    }


    @Test(priority = 1, description = "user login after password reset")
    public void userLogin() throws IOException, ParseException, InterruptedException {
        JSONObject userObj = Utils.gettinglastUserObj();
        String email = userObj.get("email").toString();
        String pass = userObj.get("pass").toString();
        loginLogoutPage.doLogin(email, pass);
    }

    @Test(priority = 2, description = "Adding in all the fields")
    public void addAllItem() throws InterruptedException, IOException, ParseException {
        itemPage.addingItem("Books", "500", "Awesome Books");
        Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        Thread.sleep(1000);
    }

    @Test(priority = 3, description = "Adding only required fields")
    public void addMandatoryItem() throws InterruptedException {
        itemPage.addingItem("Shoes", "1000");
        Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        Thread.sleep(1000);
    }

    @Test(priority = 4, description = "Checks if all items and required item list exist in the User Daily Costs table")
    public void itemExists() {
        UserDashboardPage dashboard = new UserDashboardPage(driver);
        Assert.assertTrue(dashboard.itemExists("Books"));
        Assert.assertTrue(dashboard.itemExists("Shoes"));
    }

    String prevEmail;
    @Test(priority = 6, description = "Updating user gmail")
    public void updateGmail() throws IOException, ParseException, InterruptedException {
        prevEmail = Utils.gettinglastUserObj().get("email").toString();
        System.out.println(prevEmail);
        userProfilePage.gotoProfile();
        Thread.sleep(2000);
        String gmail = "abhidiptadas11+" + Utils.generateRandomNumber(1000, 9999) + "@gmail.com";
        userProfilePage.updateGmail(gmail);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        Thread.sleep(2000);
        Utils.updateMail(gmail);
        loginLogoutPage.doLogout();
        Thread.sleep(1000);
    }

    @Test(priority = 7, description = "Logging in with previous email", enabled = true)
    public void prevEmailLogin() throws InterruptedException, IOException, ParseException {
        JSONObject userObj = Utils.gettinglastUserObj();
        String pass = userObj.get("pass").toString();
        loginLogoutPage.doLogin(prevEmail, pass);
        String actualMsg = driver.findElement(By.xpath("//p[text()='Invalid email or password']")).getText();
        String expectedMeg = "Invalid email or password";
        Assert.assertEquals(actualMsg, expectedMeg);
    }

    public void clearEmail() {
        loginLogoutPage.txtMail.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
    }

    public void clearPass() {
        loginLogoutPage.txtPass.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
    }

    @Test(priority = 8, description = "Log in with the updated email", enabled = true)
    public void newEmailLogin() throws IOException, ParseException, InterruptedException {
        JSONObject userObj = Utils.gettinglastUserObj();
        String newEmail = userObj.get("email").toString();
        String pass = userObj.get("pass").toString();
        clearEmail();
        clearPass();
        loginLogoutPage.doLogin(newEmail, pass);
        Thread.sleep(2000);
        loginLogoutPage.doLogout();
    }

}
