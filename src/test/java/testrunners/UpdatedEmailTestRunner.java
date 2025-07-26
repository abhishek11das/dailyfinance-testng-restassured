package testrunners;

import config.Setup;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginLogoutPage;
import utils.Utils;
import java.io.IOException;

public class UpdatedEmailTestRunner extends Setup {

    private LoginLogoutPage loginLogoutPage;
    @BeforeClass
    public void init(){
        loginLogoutPage = new LoginLogoutPage(driver);
    }

    @Test(priority = 1, description = "Admin login")
    public void adminLogin() throws InterruptedException, IOException {
//        LoginLogoutPage loginLogoutPage = new LoginLogoutPage(driver);
        if (System.getProperty("email") != null && System.getProperty("pass") != null) {
            loginLogoutPage.doLogin(System.getProperty("email"), System.getProperty("pass"));
        } else {
            loginLogoutPage.doLogin("admin@test.com", "admin123");
        }
        Utils.getToken(driver);
    }

    @Test(priority = 2, description = "Searching the updated email in the search box within the admin login dashboard")
    public void searchUpdatedGmail() throws IOException, ParseException, InterruptedException {
        boolean search = loginLogoutPage.searchGmail();
        Assert.assertTrue(search, "Updated email not found");
        Thread.sleep(2000);
        loginLogoutPage.doLogout();
    }


}
