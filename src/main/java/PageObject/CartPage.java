package PageObject;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbtractComponent.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;
	
	public CartPage( WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".infoWrap")
	List <WebElement> cartProducts;
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	//Verify if the product in the cart is right or not by using assert and stream API
	
	public boolean verifyProductInCart(String ProductName) {
		Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().trim().contains(ProductName));
		return match;		
	}
	public CheckoutPage checkout() {
		// TODO Auto-generated method stub
		checkoutEle.click();
		return new CheckoutPage(driver);
	}
	
	
}