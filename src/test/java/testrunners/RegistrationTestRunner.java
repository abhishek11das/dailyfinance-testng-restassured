package testrunners;

import config.Setup;
import controller.GmailController;
import org.apache.commons.lang3.StringEscapeUtils;
import org.testng.annotations.BeforeClass;
import utils.Utils;
import com.github.javafaker.Faker;
import config.UserModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.UserRegistrationPage;
import javax.naming.ConfigurationException;
import java.io.IOException;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationTestRunner extends Setup {

    private GmailController gmailController;

    @BeforeClass
    public void initGmailController() {
        gmailController = new GmailController(prop);
    }

    @Test(priority = 1, description = "User Registration", enabled = true)
    public void userRegistration() throws InterruptedException, IOException, ParseException, ConfigurationException, org.apache.commons.configuration.ConfigurationException {
        driver.findElement(By.partialLinkText("Register")).click();

        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String mail_name = "abhidiptadas11+";
        String email = (mail_name + Utils.generateRandomNumber(1000, 9999) + "@gmail.com");
        String pass = "1234";
        String phn = "0166" + Utils.generateRandomNumber(1000000, 9999999);
        String addrs = faker.address().fullAddress();
        UserModel userModel = new UserModel();
        userModel.setFirstName(firstName);
        userModel.setLastName(lastName);
        userModel.setEmail(email);
        userModel.setPass(pass);
        userModel.setPhn(phn);
        userModel.setAddress(addrs);

        UserRegistrationPage user = new UserRegistrationPage(driver);
        user.userRegistration(userModel);
        Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));     // explicit wait
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Toastify__toast")));
        String actualSuccessfulMsg = driver.findElement(By.className("Toastify__toast")).getText();
        String expectedSuccessfulMsg = "registered successfully!";
        Assert.assertTrue(actualSuccessfulMsg.contains(expectedSuccessfulMsg));

        JSONObject userObj = new JSONObject();
        userObj.put("firstName", firstName);
        userObj.put("lastName", lastName);
        userObj.put("email", email);
        userObj.put("pass", pass);
        userObj.put("phn", phn);
        userObj.put("addrs", addrs);

        Utils.saveUserData("./src/test/resources/users.json", userObj);
    }

    @Test(priority = 2, description = "Validate email contains greeting and message")
    public void mail() throws IOException, ConfigurationException, org.apache.commons.configuration.ConfigurationException {
        Gmail gmail = new Gmail();
        gmail.mailId();
        String actualMsg = gmail.mailSnippet();
        actualMsg = StringEscapeUtils.unescapeHtml4(actualMsg);//decoding html encoded characters
        System.out.println("Actual Message: " + actualMsg);
        Pattern pattern = Pattern.compile("^Dear\\s+(\\w+),");
        Matcher matcher = pattern.matcher(actualMsg);
        Assert.assertTrue(matcher.find(), "Greeting format 'Dear [Name],' not found in email.");
        String expectedPart = "Welcome to our platform! We're excited to have you onboard. Best regards, Road to Career";
        Assert.assertTrue(actualMsg.contains(expectedPart), "Expected message not found in actual email snippet.");
    }

}
