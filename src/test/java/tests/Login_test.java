package tests;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.HomePage;
import pages.LoginPage;

public class Login_test {

	public static void main(String[] args) {
		//sanity test

		WebDriver driver = null;
		try {
			JSONParser parser = new JSONParser();
			//define the reader for the specific file "login.json"
			FileReader reader = new FileReader("login.json");
			JSONArray users = (JSONArray) parser.parse(reader);
			
			//define the driver for the "demobalze" website 
			driver = new ChromeDriver();
			driver.get("https://demoblaze.com");
			
			//create the objects for the pages
		    HomePage home_page = new HomePage(driver);
            LoginPage login_page = new LoginPage(driver);
			
			//iterate over the users that exist in the json file 
            for (Object userObject : users) {
                JSONObject user = (JSONObject) userObject;

                //fetch the username + password + expectedResult
                String username = (String) user.get("username");
                String password = (String) user.get("password");
                String expectedResult = (String) user.get("expectedResult");

                home_page.clickLogin();
                Thread.sleep(3000);

                login_page.fillUsername(username);
                login_page.fillPassword(password);
                login_page.clickLoginButton();
                Thread.sleep(3000);

                
                try {
                	//handle the alert message after the login
                    Alert alert = driver.switchTo().alert();
                    String alertText = alert.getText();
                    System.out.println("Alert Text: " + alertText);


                    alert.accept(); // Dismiss the alert
                } catch (Exception e) {
                    System.out.println("No alert present for user: " + username);
                }
                
                Thread.sleep(3000);
                
                if (expectedResult.equals("Correct")) {
                    try {
                        String loggedInUser = driver.findElement(By.id("nameofuser")).getText();
                        if (loggedInUser.contains(username)) {
                            System.out.println("Test Passed: Login successful for user " + username);
                            //logout the user
                            home_page.clickLogout();
                        } else {
                            System.out.println("Test Failed: Unexpected user after login for " + username);
                        }
                    } catch (Exception e) {
                        System.out.println("Test Failed: Login failed for user " + username);
                    }
                } else {
                    System.out.println("Test Passed: Login failed as expected for user " + username);
                }
                
                driver.navigate().refresh();
                Thread.sleep(2000);
			
		}
            } catch(Exception e) {
            e.printStackTrace();
        } finally {
            
        	
            if (driver != null) {
                driver.quit();
            }
        }
		
	}

}
