package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbtractComponent.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);	
	}

	@FindBy(css="[placeholder = 'Select Country']")
	WebElement countryInput;
	
	@FindBy(css = ".ta-results")
	WebElement countryResults;
	
	@FindBy(xpath = "//button[normalize-space(.)='India']")
	WebElement CountrySelect;
	
	@FindBy(css = ".action__submit")
	WebElement submitButton;

	
	public void selectCountry(String countryName) {	
		Actions a = new Actions(driver);
		a.sendKeys(countryInput, countryName).build().perform();
		waitForElementToAppear(countryResults); 
		a.click(CountrySelect).build().perform();
	}
	
	public ConfirmationPage submitOrder() {
		submitButton.click();
		return new ConfirmationPage(driver);
	}
		
	
}
