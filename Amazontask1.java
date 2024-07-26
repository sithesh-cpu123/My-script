package testing;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Amazontask1 {

	  private WebDriver driver;

	 // @Test

	    @BeforeClass
	    public void setUp() {
	       
	    	 driver = new ChromeDriver();
	    	 driver.get("https://www.amazon.in");
	        driver.manage().window().maximize();
	    }
	    @Test
	    public void searchMobileAndPrintPrices() {
	        // Navigate to Amazon
	      //  driver.get("https://www.amazon.in");
	        
	        // Find the search box and enter "mobile"
	        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
	        searchBox.sendKeys("mobile");
	        searchBox.submit();

	        // Wait for the results to load
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".s-main-slot .s-result-item")));

	        
	        // Get product elements
	        List<WebElement> products = driver.findElements(By.cssSelector(".s-main-slot .s-result-item"));

	        // Iterate through products and print names and prices
	        for (WebElement product : products) {
	            try {
	                String productName = product.findElement(By.cssSelector("h2 .a-link-normal")).getText();
	                String priceString = product.findElement(By.cssSelector(".a-price .a-offscreen")).getText();
	                priceString = priceString.replace("₹", "").replace(",", "").trim(); // Clean price string
	                double price = Double.parseDouble(priceString);

	                // Print product name and price category
	                if (price < 7000) {
	                    System.out.println(productName + " - Price: " + price + " (Less than 7000)");
	                } else {
	                    System.out.println(productName + " - Price: " + price + " (Greater than 7000)");
	                }
	            } catch (Exception e) {
	                // Handle cases where price is not found
	                System.out.println(product + " - Price not available");
	            }
	        }
	    }

	    @AfterClass
	    public void tearDown() {
	    	 // Quit the driver
	        if (driver != null) {
	        driver.quit();
	    }
}}

