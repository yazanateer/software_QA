package tests;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class base_test {
	private static final Logger logger = LogManager.getLogger(base_test.class);
	
	//initialize the driver 
	 public static WebDriver initializeDriver() {
		logger.info("Initializing the driver...");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize(); //maximze the chrome browser
        logger.info("Driver initialized successfully.");
        return driver;
    }
	 
	//close the driver
	 public static void quitDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit(); 
            logger.info("Driver close .");
        }
    }
}
