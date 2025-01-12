package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.CartPage;
import pages.HomePage;
import pages.PlaceOrderPage;

public class PlaceOrder_test {

	public static void main(String[] args) throws InterruptedException {
		//sanity test
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://demoblaze.com");
		
		//create the obejcts for the pages
		HomePage home_page = new HomePage(driver);
		CartPage cart_page = new CartPage(driver);
		PlaceOrderPage placeOrder_page = new PlaceOrderPage(driver);
		
		Thread.sleep(3000);
		
		home_page.clickCart();
		Thread.sleep(3000);

		
		cart_page.clickPlaceOrder();
		Thread.sleep(3000);

		placeOrder_page.fillName("name");
		placeOrder_page.fillCountry("country");
		placeOrder_page.fillCity("city");
		placeOrder_page.fillCreditCard("123");
		placeOrder_page.fillMonth("1");
		placeOrder_page.fillYear("1999");
		Thread.sleep(3000);

		placeOrder_page.clickPurchase();
		
		
		
		
		

	}

}
