package tests;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
import pages.CartPage;
import pages.HomePage;
import pages.PlaceOrderPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class PlaceOrder_test extends base_test {
    
    private static final Logger logger = LogManager.getLogger(PlaceOrder_test.class);

	private WebDriver driver;

    @After
    public void tearDown() {
        quitDriver(driver);
    }
    
    @Before
    public void setUp() {
        driver = initializeDriver();
        driver.get("https://demoblaze.com");
        logger.info("Navigated to https://demoblaze.com.");

    }

    @Test
    public void simple() throws InterruptedException, IOException, ParseException {
       
        logger.info("Starting Place Order test...");

    	
    	// Parse order data from JSON file
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader("orders.json");
        JSONObject orderData = (JSONObject) parser.parse(reader);

        String name = (String) orderData.get("name");
        String country = (String) orderData.get("country");
        String city = (String) orderData.get("city");
        String creditCard = (String) orderData.get("creditCard");
        String month = (String) orderData.get("month");
        String year = (String) orderData.get("year");
        logger.debug("Order data loaded from JSON file: " + orderData);

        
        // Initialize Page Objects
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);
        PlaceOrderPage placeOrderPage = new PlaceOrderPage(driver);

        // Navigate to the Cart
        homePage.clickCart();
        Thread.sleep(3000);
        logger.info("Navigated to Cart.");


        // Click "Place Order"
        cartPage.clickPlaceOrder();
        Thread.sleep(3000);
        logger.info("Clicked 'Place Order'.");


        // Fill out the order form
        placeOrderPage.fillName(name);
        placeOrderPage.fillCountry(country);
        placeOrderPage.fillCity(city);
        placeOrderPage.fillCreditCard(creditCard);
        placeOrderPage.fillMonth(month);
        placeOrderPage.fillYear(year);
        Thread.sleep(3000);
        logger.debug("Filled the order data...");


        // Click "Purchase"
        placeOrderPage.clickPurchase();
        Thread.sleep(3000);
        logger.info("Clicked 'Purchase'.");


        
        // Handle Alert or Confirmation Message
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            logger.info("Alert Text: " + alertText);

            if (alertText.contains("Please fill out Name and Credit Card")) {
                logger.error("Test Failed: Missing required fields.");
            } else {
                logger.error("Test Failed: Unexpected alert message.");
            }

            // Close the alert
            alert.accept();

        } catch (org.openqa.selenium.NoAlertPresentException e) {
            // No alert indicates the purchase was successful
            logger.info("Test Passed: Purchase completed successfully.");

            String confirmationMessage = driver.findElement(By.xpath("//h2[text()='Thank you for your purchase!']")).getText();
            if (confirmationMessage.contains("Thank you")) {
                logger.info("Confirmation Message: " + confirmationMessage);
            } else {
                logger.error("Test Failed: Confirmation message not found.");
            }
        }
    }
    
    public static void main(String args[]) {
    	  JUnitCore junit = new JUnitCore();
    	  junit.addListener(new TextListener(System.out));
    	  org.junit.runner.Result result = junit.run(PlaceOrder_test.class); 
    	  if (result.getFailureCount() > 0) {
    	    logger.error("Test failed.");
    	    System.exit(1);
    	  } else {
		    logger.info("Test finished successfully.");
    	    System.exit(0);
    	    
    	  }
      }


}
