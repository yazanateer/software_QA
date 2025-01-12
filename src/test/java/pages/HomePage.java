package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	WebDriver driver;
	JavascriptExecutor js;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	    this.js = (JavascriptExecutor) driver;
	}
	
	//locate the navigation buttons in the home page
	By ContactBtn = By.xpath("//button[text()='Contact']");
	By AboutUsBtn = By.xpath("//button[text()='About us']");
	By CartBtn = By.xpath("//a[text()='Cart']");
	By LoginBtn = By.id("login2");
	By LogoutBtn = By.id("logout2");
	By SignupBtn = By.xpath("//button[text()='Sign up']");
	
	
    By PrevBtn = By.xpath("//button[text()='Previous']");
    By NextBtn = By.xpath("//button[text()='Next']");
     
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
	public void clickLogout() {
		driver.findElement(LogoutBtn).click();
	}
	public void clickSignup() {
		driver.findElement(SignupBtn).click();
	}

    public void scrollToBottom() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }
    
    public void clickNext() {
        driver.findElement(NextBtn).click();
    }

    public void clickPrevious() {
        driver.findElement(PrevBtn).click();
    }
    
    public boolean isNextButtonEnabled() {
        try {
            WebElement next = driver.findElement(NextBtn);
            return next.isDisplayed() && next.isEnabled();
        } catch (Exception e) {
            return false; 
        }
    }

    public boolean isPrevButtonEnabled() {
        try {
            WebElement prev = driver.findElement(PrevBtn);
            return prev.isDisplayed() && prev.isEnabled();
        } catch (Exception e) {
            return false; 
        }
    }
	
}

