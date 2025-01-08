package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	//locate the navigation buttons in the home page
	By ContactBtn = By.xpath("//button[text()='Contact']");
	By AboutUsBtn = By.xpath("//button[text()='About us']");
	By CartBtn = By.xpath("//button[text()='Cart']");
	By LoginBtn = By.xpath("//button[text()='Log in']");
	By SignupBtn = By.xpath("//button[text()='Sign up']");
	
	//methods to click on each one 
	public void clickContact() {
		driver.findElement(ContactBtn).click();
	}
	public void clickAboutUs() {
		driver.findElement(AboutUsBtn).click();		
	}
	public void clickCart() {
		driver.findElement(CartBtn).click();
	}
	public void clickLogin() {
		driver.findElement(LoginBtn).click();
	}
	public void clickSignup() {
		driver.findElement(SignupBtn).click();
	}

	
}

