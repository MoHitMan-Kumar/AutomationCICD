package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObject.CartPage;
import PageObject.CheckoutPage;
import PageObject.ConfirmationPage;
import PageObject.OrderPage;
import PageObject.ProductCatalog;
import testComponents.BaseTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrder extends BaseTest {
	// variables
	String ProductName = "ZARA COAT 3";
	String CountryName = "India";
	String ExpectedMessage = "THANKYOU FOR THE ORDER.";
	
	@Test(dataProvider = "getData", groups = "Purchase")
	public void submitOrder( HashMap <String, String> input) throws IOException, InterruptedException {
	
		// Using Page Object Model to login
		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));

		// adding product to cart
		productCatalog.addProductToCart(input.get("product"));

		// Go to Cart Page
		CartPage cartPage = productCatalog.goToCart();

		// Verify if the product in the cart is right or not by using assert and stream
		// API
		Boolean match = cartPage.verifyProductInCart(input.get("product"));
		Assert.assertTrue(match, "Product not found in cart!");

		// Proceed to checkout
		CheckoutPage checkoutPage = cartPage.checkout();

		// SelectCountry
		checkoutPage.selectCountry(CountryName);
		ConfirmationPage confirmPage = checkoutPage.submitOrder();
		// Verifying the order confirmation message by ignoring case sensitivity
		String ConfirmMessage = confirmPage.MessageConfirmation();
		Assert.assertTrue(ConfirmMessage.equalsIgnoreCase(ExpectedMessage));
		Thread.sleep(1000);
	}

	@Test(dependsOnMethods = "submitOrder")
	public void OrderHistoryTest() {
		
		ProductCatalog productCatalog = landingPage.loginApplication("mohitmb02@gmail.com", "Mohit@RahulAcademy1");
		OrderPage orderPage = productCatalog.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyProductInOrders(ProductName));
		
	}

	
	@DataProvider
	public Object[][] getData() throws IOException {
		// read json to String
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\data\\PurchaseOrder.json");

		return new Object[][] { {data.get(0)} , {data.get(1)}};
		}
		
//	HashMap<String, String> map = new HashMap <String, String>();
//	map.put("email", "mohitmb02@gmail.com");
//	map.put("password", "Mohit@RahulAcademy1");
//	map.put("product", "ZARA COAT 3");
//	
//	HashMap<String, String> map1 = new HashMap <String, String>();
//	map1.put("email", "iamsidhartha.kumar@gmail.com");
//	map1.put("password", "SidPassword@1");
//	map1.put("product", "ADIDAS ORIGINAL");
//	return new Object[][] { {map} , {map1}};
//}
	}

//@DataProvider
//public Object[][] getData() {
//	return new Object[][] { {"mohitmb02@gmail.com", "Mohit@RahulAcademy1", "ZARA COAT 3"} , 
//		{"iamsidhartha.kumar@gmail.com", "SidPassword@1", "ADIDAS ORIGINAL"}};
//}