package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.openqa.selenium.WebDriver;
import pages.HomePage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Pagination_test extends base_test {
	
    private static final Logger logger = LogManager.getLogger(Pagination_test.class);

    private WebDriver driver;
    private HomePage homePage;

    @After
    public void tearDown() {
        quitDriver(driver);
    }
    
    @Before
    public void setUp() {
        driver = initializeDriver();
        driver.get("https://demoblaze.com");
        homePage = new HomePage(driver);
        logger.info("Navigated to demoblaze.com");

    }

    @Test
    public void simple() throws InterruptedException {
        logger.info("Starting pagination test...");

        //check if there are "next" button in the page 
        while (homePage.isNextButtonEnabled()) {
            homePage.scrollToBottom();  //scroll down in the page
            Thread.sleep(2000); 
            logger.debug("Clicking on 'Next' button.");
            homePage.clickNext(); 
            Thread.sleep(2000); 
        }
        logger.info("Reached the last page.");

        
            homePage.scrollToBottom(); 
            Thread.sleep(2000); 
            logger.debug("Clicking on 'Previous' button.");
            homePage.clickPrevious(); 
            Thread.sleep(2000); 
      
    }
    
    public static void main(String args[]) {
    	  JUnitCore junit = new JUnitCore();
    	  junit.addListener(new TextListener(System.out));
    	  org.junit.runner.Result result = junit.run(Pagination_test.class); 
    	  if (result.getFailureCount() > 0) {
              logger.error("Test failed.");
    	    System.exit(1);
    	  } else {
              logger.info("Test finished successfully.");
    	    System.exit(0);
    	  }
      }


}
