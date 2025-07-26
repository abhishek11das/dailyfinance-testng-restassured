package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class AdminWebTablePage {

    @FindBy(tagName = "tbody")
    WebElement table;

    public AdminWebTablePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void scrapDataFromWebTable() throws IOException {
        List<WebElement> allRows = table.findElements(By.tagName("tr"));
        int i = 0;
        BufferedWriter writer = new BufferedWriter(new FileWriter("./src/test/resources/writeData.txt"));
        for(WebElement row : allRows) {
            List<WebElement> allCells = row.findElements(By.tagName("td"));
            for(WebElement cell:allCells) {
                i++;
                System.out.println("num[" + i + "] " + cell.getText());
                writer.write(cell.getText() + "\t");
            }
            writer.newLine();
        }
        writer.close();
    }

}
