package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.CartPage;
import PageObject.ProductCatalog;
import testComponents.BaseTest;

import java.io.IOException;

public class ErrorValidationTest extends BaseTest {

	@Test(groups = "Error Validation" , retryAnalyzer = testComponents.Retry.class)
	public void LoginErrorvalidation() throws IOException, InterruptedException {
		
		String ExpectedErrorMessage = "Incorrect email or password.";
		// Using Page Object Model to login
		landingPage.loginApplication("mohitmb02@gmail.com", "MohitMan@RahulAcademy1");
		Assert.assertEquals(landingPage.getErrorMessage(), ExpectedErrorMessage);
	}

	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		String Product = "ZARA COAT 3";

		ProductCatalog productCatalog = landingPage.loginApplication("mohitmb02@gmail.com", "Mohit@RahulAcademy1");

		productCatalog.addProductToCart(Product);

		CartPage cartPage = productCatalog.goToCart();

		Boolean match = cartPage.verifyProductInCart("ZARA COAT 321");
		Assert.assertFalse(match, "Product found in cart!");
		
	}
}