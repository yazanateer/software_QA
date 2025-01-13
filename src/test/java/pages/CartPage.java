package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
	WebDriver driver;
 
	public CartPage(WebDriver driver) {
		this.driver = driver;
	}
	
	//to locate the "Place Order" button
	By PlaceOrderButton = By.xpath("//button[text()='Place Order']");
	
	//to locate the option for delete items from the cart page 
	By DeleteButton = By.xpath("//a[text()='Delete']");
	
	
	
	//methods to click on the buttons
	public void clickPlaceOrder() {
		driver.findElement(PlaceOrderButton).click();
	}
	
	public void clickDelete() {
		driver.findElement(DeleteButton).click();
	}
	
	public boolean isEmpty() {
            return driver.findElements(DeleteButton).isEmpty();
      
	}
	
}
