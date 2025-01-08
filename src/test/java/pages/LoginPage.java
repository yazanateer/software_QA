package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	//to locate the input field for the username
	By userName_input = By.id("loginusername");
	
	//to locate the input field for the password
	By password_input = By.id("loginpassword");
	
	//to locate the login button
	By login_button = By.xpath("//button[text()='Log in']");
	
	//to auto fill the username input
	public void fillUsername(String username) {
		driver.findElement(userName_input).sendKeys(username);
	}
	
	//to auto fill the password input
	public void fillPassword(String password) {
		driver.findElement(password_input).sendKeys(password);
	}
	
	//to click on the login button
	public void clickLoginButton() {
		driver.findElement(login_button).click();
	}
	
	
}
