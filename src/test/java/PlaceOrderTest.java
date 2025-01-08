import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PlaceOrderTest {

	
	@Test
	public void placeOrder() {
		WebDriver driver = new ChromeDriver();
		try {
			driver.manage().window().maximize();
			driver.get("https://www.demoblaze.com");
			System.out.println("the webiste opened successfully");
			
			//navigate to the cart page 
			WebElement cartPageBtn = driver.findElement(By.linkText("Cart"));
			cartPageBtn.click();
			
			//click on the palace order button 
			WebElement placeOrderBtn = driver.findElement(By.xpath("//button[text()='Place Order']"));
			placeOrderBtn.click();
		    
					 
			//fill the input fields details in the place order page 
			WebElement nameInput = driver.findElement(By.id("name"));
			nameInput.sendKeys("alex");
			
			WebElement countryInput = driver.findElement(By.id("country"));
			countryInput.sendKeys("usa");
			
			WebElement cityInput = driver.findElement(By.id("city"));
			cityInput.sendKeys("las v");
			
			WebElement cardInput = driver.findElement(By.id("card"));
			cardInput.sendKeys("123123");
			
			WebElement monthInput = driver.findElement(By.id("month"));
			monthInput.sendKeys("12");
			
			WebElement yearInput = driver.findElement(By.id("year"));
			yearInput.sendKeys("1");
			
			
			//click on the Purchase button
			WebElement purchaseBtn = driver.findElement(By.xpath("//button[text()='Purchase']"));
			purchaseBtn.click();
			
			Thread.sleep(2000);
			
			WebElement okBtn = driver.findElement(By.xpath("//button[text()='OK']"));
			okBtn.click();
			
			
		} catch(Exception e) {
			System.out.println("error during place the order " + e.getMessage());
		}
	}
}
