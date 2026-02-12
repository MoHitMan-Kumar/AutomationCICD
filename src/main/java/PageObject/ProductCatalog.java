package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbtractComponent.AbstractComponent;

public class ProductCatalog extends AbstractComponent {

	WebDriver driver;


	//constructor for driver with PageFactory
	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List <WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By Toast = By.cssSelector("#toast-container");
	//Method to get the product list

	
	public List<WebElement> getProductList() {
		waitForByElementToAppear(productsBy);
		return products;
	}
	
	public void addProductToCart(String productName) throws InterruptedException {
		
		WebElement prod = getProductList().stream().filter
				(product -> product.findElement(By.cssSelector("b")).getText().equals(productName))
		        .findFirst().orElse(null);		    
		    // Safety Check: Agar product nahi mila to error throw karo (Debugging aasan hoti hai)
		    if (prod == null) {
		        throw new RuntimeException("Product with name " + productName + " not found!");
		    }

		    // 2. Button element nikalo
		    WebElement addToCartBtn = prod.findElement(addToCart);

		    // 4. THE PRO FIX: Javascript Click (No Scroll needed, No Sleep needed)
		    // Ye direct DOM pe hit karta hai, bhale hi element thoda chupa ho
		    JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("arguments[0].click();", addToCartBtn);

		    // 5. Toast aur Spinner ka wait karo (Confirmation ke liye)
		    waitForByElementToAppear(Toast);
		    Thread.sleep(1000); // Spinner ke jagah use kiya hu as spinner took around 4-5 sec
		
//		WebElement prod = getProductList().stream().filter(product -> 
//		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("window.scrollBy(0,200)");
//		Thread.sleep(1000);
//	    prod.findElement(addToCart).click();
//	    js.executeScript("window.scrollBy(0,-200)");
//	    Thread.sleep(1000);
//	    waitForByElementToAppear(Toast);
//		Thread.sleep(1000);
	}
	
}

