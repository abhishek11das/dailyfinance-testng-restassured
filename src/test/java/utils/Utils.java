package utils;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.naming.ConfigurationException;
import java.io.*;
import java.time.Duration;

public class Utils {

    public static int generateRandomNumber(int min, int max) {
        double randomNumber = Math.random()*(max-min)+min;
        return (int) randomNumber;
    }

    public static void saveUserData(String filePath, JSONObject jsonObject) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filePath));

        jsonArray.add(jsonObject);
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(jsonArray.toJSONString());
        fileWriter.flush();
        fileWriter.close();
    }

    public static void setEnvVar(String key, String value) throws ConfigurationException, org.apache.commons.configuration.ConfigurationException {
        PropertiesConfiguration config = new PropertiesConfiguration("./src/test/resources/config.properties");
        config.setProperty(key, value);
        config.save();
    }

    public static String extractResetLink(String mailBody) {
        String prefix = "Click on the following link to reset your password:";
        if (mailBody.contains(prefix)) {
            String link = mailBody.replace(prefix, "").trim();
            return link;
        }
        return mailBody;
    }

    public static JSONObject gettinglastUserObj() throws IOException, ParseException, InterruptedException {
        JSONParser parser = new JSONParser();
        JSONArray userArray = (JSONArray) parser.parse(new FileReader("./src/test/resources/users.json"));
        Thread.sleep(2000);
        JSONObject userObj = (JSONObject) userArray.get(userArray.size()-1);
        return userObj;
    }

    public static void updateMail(String gmail) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray userArray = (JSONArray) parser.parse(new FileReader("./src/test/resources/users.json"));
        JSONObject userObj = (JSONObject) userArray.get(userArray.size() - 1);
        userObj.put("email", gmail); // puts email in the last json object
        FileWriter fileWriter = new FileWriter("./src/test/resources/users.json");
        fileWriter.write(userArray.toJSONString());
        fileWriter.flush();
        fileWriter.close();
    }

    public static void getToken(WebDriver driver) throws IOException {
        // wait until the auth token is available
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until((ExpectedCondition<Boolean>) wd -> js.executeScript("return window.localStorage.getItem('authToken')") != null);

        // get the authToken from the local storage
        String authToken = (String) js.executeScript("return window.localStorage.getItem('authToken')");
        String authTokenData = (String) js.executeScript("return window.localStorage.getItem('authTokenData')");
        System.out.println("Auth Token Retrieved: " + authToken);
        System.out.println("Auth Token Retrieved: " + authTokenData);

        // save the auth token to a localStorage.json file
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("authToken", authToken);
        jsonObject.put("authTokenData", authTokenData);
        FileWriter writer = new FileWriter("./src/test/resources/localStorage.json");
        writer.write(jsonObject.toJSONString());
        writer.flush();
        writer.close();
    }


    public static void setAuth(WebDriver driver) throws IOException, ParseException, InterruptedException {
        JSONParser jsonParser = new JSONParser();
        JSONObject authObj = (JSONObject) jsonParser.parse(new FileReader("./src/test/resources/localStorage.json"));
        String authToken = authObj.get("authToken").toString();
        String authTokenData = authObj.get("authTokenData").toString();
        System.out.println(authToken);

        // inject object at local storage on browser
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.localStorage.setItem('authToken', arguments[0]);", authToken);
        js.executeScript("window.localStorage.setItem('authTokenData', arguments[0]);", authTokenData);
        Thread.sleep(2000);
    }

}

