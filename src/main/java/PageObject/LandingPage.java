package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbtractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;

	//constructor for driver with PageFactory
	public LandingPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement userName;
	
	@FindBy(id="userPassword")
	WebElement Password;

	@FindBy(id="login")
	WebElement submit;	
	
	@FindBy(css=".ng-trigger-flyInOut")
	WebElement errorMessage;
	
	public void NavigateToURL(String url) {
		driver.get(url);
	}
	
	public ProductCatalog loginApplication(String email, String Pass) {
		
		userName.sendKeys(email);
		Password.sendKeys(Pass);
		submit.click();
		return new ProductCatalog(driver);		
	}
	
	public String getErrorMessage() {
		waitForElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
}

