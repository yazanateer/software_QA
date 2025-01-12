package tests;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.CartPage;
import pages.HomePage;
import pages.PlaceOrderPage;

public class PlaceOrder_test {

	public static void main(String[] args) throws InterruptedException {
		//sanity test
//		
//		WebDriver driver = new ChromeDriver();
//		driver.get("https://demoblaze.com");
//		
//		//create the obejcts for the pages
//		HomePage home_page = new HomePage(driver);
//		CartPage cart_page = new CartPage(driver);
//		PlaceOrderPage placeOrder_page = new PlaceOrderPage(driver);
//		
//		Thread.sleep(3000);
//		
//		home_page.clickCart();
//		Thread.sleep(3000);
//
//		
//		cart_page.clickPlaceOrder();
//		Thread.sleep(3000);
//
//		placeOrder_page.fillName("name");
//		placeOrder_page.fillCountry("country");
//		placeOrder_page.fillCity("city");
//		placeOrder_page.fillCreditCard("123");
//		placeOrder_page.fillMonth("1");
//		placeOrder_page.fillYear("1999");
//		Thread.sleep(3000);
//
//		placeOrder_page.clickPurchase();
		
		WebDriver driver = null;
		try {
			JSONParser parser = new JSONParser();
			FileReader reader = new FileReader("orders.json");
			JSONObject orderData = (JSONObject) parser.parse(reader);
			
			String name = (String) orderData.get("name");
            String country = (String) orderData.get("country");
            String city = (String) orderData.get("city");
            String creditCard = (String) orderData.get("creditCard");
            String month = (String) orderData.get("month");
            String year = (String) orderData.get("year");
            
            driver = new ChromeDriver();
            driver.get("https://demoblaze.com");
	            
            
            HomePage home_page = new HomePage(driver);
            CartPage cart_page = new CartPage(driver);
            PlaceOrderPage placeOrder_page = new PlaceOrderPage(driver);

            home_page.clickCart();
            Thread.sleep(3000);

            cart_page.clickPlaceOrder();
            Thread.sleep(3000);

            placeOrder_page.fillName(name);
            placeOrder_page.fillCountry(country);
            placeOrder_page.fillCity(city);
            placeOrder_page.fillCreditCard(creditCard);
            placeOrder_page.fillMonth(month);
            placeOrder_page.fillYear(year);
            
            Thread.sleep(3000);
            
            placeOrder_page.clickPurchase();
            try {
                // Check if an alert is present
                String alertText = driver.switchTo().alert().getText();
                System.out.println("Alert Text: " + alertText);

                if (alertText.contains("Please fill out Name and Credit Card")) {
                    System.out.println("Test Failed: Missing required fields.");
                } else {
                    System.out.println("Test Failed: Unexpected alert message.");
                }

                // Accept and close the alert
                driver.switchTo().alert().accept();

            } catch (org.openqa.selenium.NoAlertPresentException e) {
                // No alert indicates the purchase was successful
                System.out.println("Test Passed: Purchase completed successfully.");

                
                String confirmationMessage = driver.findElement(By.xpath("//h2[text()='Thank you for your purchase!']")).getText();
                if (confirmationMessage.contains("Thank you")) {
                    System.out.println("Confirmation Message: " + confirmationMessage);
                } else {
                    System.out.println("Test Failed: Confirmation message not found.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}