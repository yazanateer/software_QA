package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PlaceOrderPage {

	WebDriver driver;
	
	public PlaceOrderPage(WebDriver driver) {
		this.driver = driver;
	}
	
	//to locate the input fields
	By Name_input = By.id("name");
	By Country_input = By.id("country");
	By City_input = By.id("city");
	By CreditCard_input = By.id("card");
	By Month_input = By.id("month");
	By Year_input = By.id("year");
	
	//to locate the buttons
	
	By Purchase_button = By.xpath("//button[text()='Purchase']");
	By Close_button = By.xpath("//button[text()='Close']");

	//to fill the input fields
	public void fillName(String name) {
		driver.findElement(Name_input).sendKeys(name);
	}
	
	public void fillCountry(String country) {
		driver.findElement(Country_input).sendKeys(country);
	}
	
	public void fillCity(String city) {
		driver.findElement(City_input).sendKeys(city);
	}
	
	public void fillCreditCard(String card) {
		driver.findElement(CreditCard_input).sendKeys(card);
	}
	
	public void fillMonth(String month) {
		driver.findElement(Month_input).sendKeys(month);
	}
	
	public void fillYear(String year) {
		driver.findElement(Year_input).sendKeys(year);
	}
	
	//to click on the buttons
	public void clickPurchase() {
		driver.findElement(Purchase_button).click();
	}
	
	public void clickClose() {
		driver.findElement(Close_button).click();
	}
	
	
}
