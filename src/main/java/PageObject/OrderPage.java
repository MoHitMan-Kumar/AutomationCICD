package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbtractComponent.AbstractComponent;

public class OrderPage extends AbstractComponent{

	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//td[2]")
	List <WebElement> products;
	
	public boolean verifyProductInOrders(String ProductName) {
		waitForByElementToAppear(By.xpath ("//td[2]"));
		Boolean match = products.stream().anyMatch(orderProduct -> orderProduct.getText().trim().contains(ProductName));
		return match;
	}
}
