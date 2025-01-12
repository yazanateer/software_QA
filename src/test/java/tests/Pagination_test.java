package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.HomePage;

public class Pagination_test {
	  public static void main(String[] args) throws InterruptedException {
	        WebDriver driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.get("https://demoblaze.com");

	       
	        HomePage homePage = new HomePage(driver);

	 
	        while (homePage.isNextButtonEnabled()) {
	            homePage.scrollToBottom(); 
	            Thread.sleep(2000); 
	            System.out.println("Clicking on 'Next' button.");
	            homePage.clickNext(); 
	            Thread.sleep(2000); 
	        }
	        System.out.println("Reached the last page.");

	    
	            homePage.scrollToBottom(); 
	            Thread.sleep(2000); 
	            System.out.println("Clicking on 'Previous' button.");
	            homePage.clickPrevious(); 
	            Thread.sleep(2000); 

	        driver.quit();
	  }
}
