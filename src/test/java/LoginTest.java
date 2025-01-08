import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.junit.Test;

import java.io.FileReader;

public class LoginTest {

    @Test
    public void Login() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        try {
            // Step 1: Maximize the browser window and open the website
            driver.manage().window().maximize();
            driver.get("https://www.demoblaze.com/"); // Open the Demoblaze website
            System.out.println("the webiste opened successfully");

            // Step 2: Read username and password from login.json
            JSONArray userData = null;
            try {
                JSONParser parser = new JSONParser();
                FileReader reader = new FileReader("login.json"); // Path to the JSON file
                userData = (JSONArray) parser.parse(reader);
                System.out.println("Data fetched from JSON file successfully.");
            } catch (Exception e) {
                System.out.println("Error reading login.json: " + e.getMessage());
                e.printStackTrace();
                return; // Exit test if reading JSON fails
            }

            // Iterate through the JSON array and test each user
            for (Object obj : userData) {
                JSONObject user = (JSONObject) obj;
                String username = (String) user.get("username");
                String password = (String) user.get("password");
                String expectedResult = (String) user.get("expectedResult");

                System.out.println("Testing with username: " + username + " and password: " + password);

                // Step 3: Perform login
                WebElement loginBtn = driver.findElement(By.id("login2")); // Locate the "Log In" navigation button
                loginBtn.click(); // Click on it

                Thread.sleep(2000); // Wait for the modal to appear

                // Locate the input fields (username - password) for login
                WebElement loginUsernameField = driver.findElement(By.id("loginusername"));
                WebElement loginPasswordField = driver.findElement(By.id("loginpassword"));

                // Fill in the username and password in the login fields
                loginUsernameField.sendKeys(username);
                loginPasswordField.sendKeys(password);

                // Locate and click the "Log In" button
                WebElement confirmLoginButton = driver.findElement(By.xpath("//button[text()='Log in']"));
                confirmLoginButton.click();

                Thread.sleep(2000); // Wait for the page to load

                // Validate login
                try {
                    WebElement loggedInUsername = driver.findElement(By.id("nameofuser"));
                    if (loggedInUsername.getText().contains(username)) {
                        System.out.println("Test Passed: Login was successful for " + username);
                    } else {
                        System.out.println("Test Failed: Login failed for " + username);
                    }
                } catch (Exception e) {
                    System.out.println("Test Failed: Login failed for " + username);
                }

                // Logout to prepare for the next iteration
                try {
                    WebElement logoutBtn = driver.findElement(By.id("logout2"));
                    logoutBtn.click();
                    Thread.sleep(2000); // Wait for the logout to complete
                } catch (Exception e) {
                    System.out.println("Error during logout for " + username);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
            System.out.println("Driver closed.");
        }
    }
}
