package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.openqa.selenium.WebDriver;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddDeleteItems_test extends base_test {
    
	private static final Logger logger = LogManager.getLogger(AddDeleteItems_test.class);

    
    
	private WebDriver driver;
    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;

    @After
    public void tearDown() {
        quitDriver(driver);
    }
    
    @Before
    public void setUp() {
        driver = initializeDriver();
        driver.get("https://demoblaze.com");
        logger.info("Navigated to demoblaze.com");

        // Initialize page objects
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    @Test
    public void simple() throws InterruptedException {
    	logger.info("Starting Add and Delete Items test...");
    	Thread.sleep(3000);
    	
        // navigate to the product page 
        homePage.clickProduct();
        Thread.sleep(3000);
        logger.debug("Clicked on the product.");


        //click on the 'add to cart' button
        productPage.clickAddToCart();
        Thread.sleep(3000); 
        logger.debug("Clicked 'Add to Cart' button.");
        
        // handle alert
        driver.switchTo().alert().accept();
        logger.info("Item added to the cart.");

        // navigate to the cart page
        homePage.clickCart();
        Thread.sleep(2000);

        // delete items from the cart
        if (!cartPage.isEmpty()) {
            cartPage.clickDelete();
            Thread.sleep(2000);
            logger.info("Item has been deleted from the cart.");
        } else {
            logger.warn("Cart is already empty.");
        }
    }
    
    
  public static void main(String args[]) {
	  JUnitCore junit = new JUnitCore();
	  junit.addListener(new TextListener(System.out));
	  org.junit.runner.Result result = junit.run(AddDeleteItems_test.class); 
	  if (result.getFailureCount() > 0) {
          logger.error("Test failed.");
	    System.exit(1);
	  } else {
          logger.info("Test finished successfully.");
	    System.exit(0);
	  }
  }
    
    


}
