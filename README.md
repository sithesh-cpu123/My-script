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

public class Amazontask2 {
	
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
		// driver.get("https://www.amazon.in");

		// Find the search box and enter "mobile"
		WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
		searchBox.sendKeys("mobile");
		searchBox.submit();
		
		// Wait for the results to load
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".s-latency-cf-section .puis-card-border")));
				
				// Get product elements
				List<WebElement> products = driver.findElements(By.cssSelector(".s-latency-cf-section .puis-card-border"));

				// Iterate through products and print names and prices
				for (WebElement product : products) {
					try {

						String productName = product.findElement(By.cssSelector("span.a-text-normal")).getText();
						String trimmedProduct = trimProductName(productName);
						double price = Double.parseDouble(product.findElement(By.cssSelector("span.a-price-whole")).getText()
								.replace("₹", "").replace(",", "").trim());

						// Print product name and price category
						if (price < 7000) {
							System.out.println(productName + " - Price: ₹" + price + " (Less than 7000)");
							System.out.println(trimmedProduct + " - Price: ₹" + price + " (Less than 7000)");
							System.out.println();
						} else {
							System.out.println(productName + " - Price: " + price + " (Greater than 7000)");
							System.out.println(trimmedProduct + " - Price: " + price + " (Greater than 7000)");
							System.out.println();
						}
						// Print product name and price category
						// System.out.println(productName + " - Price: ₹" + price);

					} catch (Exception e) {
						// Handle cases where price is not found
						System.out.println(product + " - Price not available");
						System.out.println();

					}
				}
			}

			// For print only product name without its features
			private String trimProductName(String product) {
				// TODO Auto-generated method stub
				return product.split(" \\(")[0];

			}

			@AfterClass
			public void tearDown() {
				// Quit the driver
				if (driver != null) {
					driver.quit();
				}

			}

}
