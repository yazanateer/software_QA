package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;

public class AddDeleteItems_test {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoblaze.com");

        // Create page objects
        HomePage homePage = new HomePage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        Thread.sleep(2000);
        // Add an item to the cart
        homePage.clickProduct();
        Thread.sleep(2000);

        productPage.clickAddToCart();
        Thread.sleep(3000); // Wait for the "Added to cart" alert

        // Handle alert
        driver.switchTo().alert().accept();

        // Navigate to the cart page
        homePage.clickCart();
        Thread.sleep(4000);

        // Delete items from the cart
        if(!cartPage.isEmpty()) {
            cartPage.clickDelete();
            Thread.sleep(2000);
            System.out.println("the item has been deleted from the cart.");
        }



        driver.quit();
    }
}
