package testrunners;

import config.Setup;
import controller.GmailController;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.Utils;

import javax.naming.ConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Gmail extends Setup {

    private GmailController gmailController;

    public Gmail() throws IOException {
        prop = new Properties();
        FileInputStream fs = new FileInputStream("./src/test/resources/config.properties");
        prop.load(fs);
        this.gmailController = new GmailController(prop);
    }

    public void mailId() throws ConfigurationException, org.apache.commons.configuration.ConfigurationException {
        Response res = gmailController.gmailInboxList();
        JsonPath jsonPathId = res.jsonPath();
        String id = jsonPathId.get("messages[0].id");
        System.out.println("Last Mail ID: " + id);
        Utils.setEnvVar("email_id", id);
    }

    public String mailSnippet() throws IOException {
        Response res = gmailController.readMessage();
        JsonPath jsonPathSnippet = res.jsonPath();
        String actualMsg = jsonPathSnippet.get("snippet");
        return actualMsg;
    }

}
