package tests;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Login_test extends base_test {
	
	private static final Logger logger = LogManager.getLogger(Login_test.class);
	
    private WebDriver driver;

    @After
    public void tearDown() {
        quitDriver(driver);
    }
    
    @Before
    public void setUp() {
        driver = initializeDriver();
        driver.get("https://demoblaze.com");
        logger.info("Navigated to demoblaze.com");
    }

    @Test
    public void simple() throws InterruptedException, IOException, ParseException {
    	
    	logger.info("Starting Login Test...");
    	
    	
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader("login.json");
        JSONArray users = (JSONArray) parser.parse(reader);

        logger.debug("fetching users data from JSON file.");
        
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        for (Object userObject : users) {
            JSONObject user = (JSONObject) userObject;
            String username = (String) user.get("username");
            String password = (String) user.get("password");
            String expectedResult = (String) user.get("expectedResult");
            logger.info("Testing login for user: " + username);
            
            
            
            homePage.clickLogin();
            Thread.sleep(2000);
            logger.debug("Navigate to the Login page .");

            loginPage.fillUsername(username);
            loginPage.fillPassword(password);
            
            loginPage.clickLoginButton();
            logger.debug("Clicked Login button.");
            
            Thread.sleep(2000);

            try {
                Alert alert = driver.switchTo().alert();
                logger.warn("Alert present for user: " + username + ". Text: " + alert.getText());
                alert.accept();
            } catch (Exception e) {
                logger.info("No alert for user: " + username);
            }

            Thread.sleep(2000);
            if (expectedResult.equals("Correct")) {
                try {
                    String loggedInUser = driver.findElement(By.id("nameofuser")).getText();
                    if (loggedInUser.contains(username)) {
                    	logger.info("Login successful for user: " + username);
                    	homePage.clickLogout();
                        logger.debug(username + " logged out successfully.");
                    } else {
                        logger.error("Unexpected user after login for: " + username);
                    }
                } catch (Exception e) {
                    logger.error("Login failed for user: " + username, e);
                }
            } else {
                logger.info("Login failed as expected for user: " + username);
            }

            driver.navigate().refresh();
            Thread.sleep(2000);
        }
        logger.info("Login Test completed.");
    }
    
    public static void main(String args[]) {
  	  JUnitCore junit = new JUnitCore();
  	  junit.addListener(new TextListener(System.out));
  	  org.junit.runner.Result result = junit.run(Login_test.class); 
  	  if (result.getFailureCount() > 0) {
	    logger.error("Test failed."); 
  	    System.exit(1);
  	  } else {
        logger.info("Test finished successfully.");
  	    System.exit(0);
  	  }
    }


}
