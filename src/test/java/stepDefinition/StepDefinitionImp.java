package stepDefinition;
import java.io.IOException;

import org.testng.Assert;

import PageObject.CartPage;
import PageObject.CheckoutPage;
import PageObject.ConfirmationPage;
import PageObject.ProductCatalog;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testComponents.BaseTest;

public class StepDefinitionImp extends BaseTest {

	public ProductCatalog productCatalog;
	public CartPage cartPage;
	public CheckoutPage checkoutPage;
	public ConfirmationPage confirmPage;
	
	@Given("I landed on Ecommerce Page")
	public void iLandedOnEcommercePage() throws IOException {
		launchApplication();
	}

	@Given("Logged in with username {string} and password {string}")
	public void loggedInWithUsernameAndPassword(String username, String password) {
		productCatalog = landingPage.loginApplication(username, password);
	}

	@When("I add product {string} to the cart")
	public void iAddProductToCart(String productName) throws InterruptedException {
		productCatalog.addProductToCart(productName);
	}

	@When("checkout {string} and submit the order")
	public void checkoutAndSubmitOrder(String productName) {
		cartPage = productCatalog.goToCart();
		Boolean match = cartPage.verifyProductInCart(productName);
		Assert.assertTrue(match, "Product not found in cart!");
		checkoutPage = cartPage.checkout();
		// SelectCountry
		checkoutPage.selectCountry("India");
		confirmPage = checkoutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on the confirmation page")
	public void messageIsDisplayedOnConfirmationPage(String expectedMessage) {
		String confirmMessage = confirmPage.MessageConfirmation();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(expectedMessage));
	}
	@Then("{string} message is displayed")
	public void messageIsDisplayed(String expectedMessage) {
		Assert.assertEquals(landingPage.getErrorMessage(), expectedMessage);
	}
	@After
	public void tearDown() {
	    if(driver != null) {
	        driver.quit();
	}
	}
}