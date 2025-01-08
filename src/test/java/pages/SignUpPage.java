package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage {

	WebDriver driver;
	
	public SignUpPage(WebDriver driver) {
		this.driver = driver;
	}
	
	//to locate the input field for the username
	By userName_input = By.id("sign-username");
	
	//to locate the input field for the password
	By password_input = By.id("sign-password");
	
	//to locate the sign up button
	By signup_button = By.xpath("//button[text()='Sign up']");
	
	//to auto fill the username input
	public void fillUsername(String username) {
		driver.findElement(userName_input).sendKeys(username);
	}
	
	//to auto fill the password input
	public void fillPassword(String password) {
		driver.findElement(password_input).sendKeys(password);
	}
	
	//to click on the Sign up button
	public void clickSignupButton() {
		driver.findElement(signup_button).click();
	}
}
