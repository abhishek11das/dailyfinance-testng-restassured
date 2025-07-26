package controller;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import javax.naming.ConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class GmailController  {

    Properties prop;

    public GmailController(Properties prop) {
        this.prop = prop;
    }

    public Response gmailInboxList() throws ConfigurationException, org.apache.commons.configuration.ConfigurationException {
        RestAssured.baseURI = "https://gmail.googleapis.com";
        return given().contentType("application/json")
                .header("Authorization", "Bearer " + prop.getProperty("g_token"))
                .when().get("/gmail/v1/users/me/messages");
    }

    public Response readMessage() throws IOException {
        FileInputStream fs = new FileInputStream("./src/test/resources/config.properties");
        prop.load(fs);
        RestAssured.baseURI = "https://gmail.googleapis.com";
        return given().contentType("application/json")
                .header("Authorization", "Bearer " + prop.getProperty("g_token"))
                .when().get("/gmail/v1/users/me/messages/" + prop.getProperty("email_id"));
    }
}
