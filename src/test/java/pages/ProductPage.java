package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {

    WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locate the "Add to cart" button
    By addToCartButton = By.xpath("//a[text()='Add to cart']");

    public void clickAddToCart() {
        driver.findElement(addToCartButton).click();
    }
}