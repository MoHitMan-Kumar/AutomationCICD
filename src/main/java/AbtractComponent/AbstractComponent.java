package AbtractComponent;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObject.CartPage;
import PageObject.OrderPage;

public class AbstractComponent {

	WebDriver driver;
	WebDriverWait wait;
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(6));
		PageFactory.initElements(driver,this);
	}

	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;

	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	
	@FindBy(css=".infoWrap")
	WebElement cartItem;
	
	
	//Reusable wait method
	public void waitForByElementToAppear(By findBy) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForElementToAppear(WebElement ele) {
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void waitForElementToDisappear(WebElement ele) {
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	//click on header cart
	public CartPage goToCart() {
		cartHeader.click();
		waitForElementToAppear(cartItem);
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	

	public OrderPage goToOrdersPage() {
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
	
	
	
}
