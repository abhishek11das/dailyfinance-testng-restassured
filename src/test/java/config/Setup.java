package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Setup {

    public WebDriver driver;
    public Properties prop;

    @BeforeTest
    public void setup() throws IOException {
        prop = new Properties();
        FileInputStream fs = new FileInputStream("./src/test/resources/config.properties");
        prop.load(fs);
        if (driver == null) {
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://dailyfinance.roadtocareer.net/");
    }

    @AfterMethod
    public void reloadConfig() throws IOException {
        prop = new Properties();
        FileInputStream fs = new FileInputStream("./src/test/resources/config.properties");
        prop.load(fs);
    }

    @AfterTest(enabled = true)
    public void tearDown() {
        driver.quit();
    }
}

