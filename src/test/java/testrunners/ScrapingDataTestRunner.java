package testrunners;

import config.Setup;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import pages.AdminWebTablePage;
import utils.Utils;
import java.io.IOException;

public class ScrapingDataTestRunner extends Setup {

    @Test(description = "Scrapping table data from admin dashboard")
    public void scrapData() throws IOException, ParseException, InterruptedException {
        Utils.setAuth(driver);
        driver.navigate().to("https://dailyfinance.roadtocareer.net/admin");
        AdminWebTablePage adminWebTablePage = new AdminWebTablePage(driver);
        adminWebTablePage.scrapDataFromWebTable();
    }

}
