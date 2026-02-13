package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import PageObject.LandingPage;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebElement;

public class StandAloneTest {

	public static void main(String[] args) {
		String Product = "ZARA COAT 3";
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.get("https://rahulshettyacademy.com/client");
		

		//adding the comment for github webhook continuous integration
		//write code to to login into the shopping site
		driver.findElement(By.id("userEmail")).sendKeys("mohitmb02@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Mohit@RahulAcademy1");
		driver.findElement(By.id("login")).click();
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		//stream API to filter the product
		WebElement prod = products.stream().filter(product -> 
		product.findElement(By.cssSelector("b")).getText().equals(Product)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//Explicit wait for the toast message to appear
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#toast-container"))));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//click on the cart
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		// Wait for cart items to be visible before asserting
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".infoWrap"))));
		
		//Verify if the product in the cart is right or not by using assert and stream API
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".infoWrap"));
		System.out.println("Number of products in the cart: " + cartProducts.size());
			
		// Use contains + trim to avoid mismatches due to extra text/whitespace and provide a helpful message on failure
		Assert.assertTrue(
			cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().trim().contains(Product)),
			"Expected product not found in cart: " + Product
		);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		//Entering the country name as 'ind' by actions class
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder = 'Select Country']")), "ind").build().perform();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-results"))));
		a.click(driver.findElement(By.xpath("//button[normalize-space(.)='India']"))).build().perform();
		a.click(driver.findElement(By.cssSelector(".action__submit"))).build().perform();
		
		//Verifying the order confirmation message by ignoring case sensitivity
		
		String ConfirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
		//sleep for 2 SEC and closing the browser
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
		
		
		
		
		// TODO Auto-generated method stub

	}

}