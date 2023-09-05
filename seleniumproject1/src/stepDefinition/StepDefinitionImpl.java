package stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sadiqueshaikhautomation.pageObjects.CartPage;
import sadiqueshaikhautomation.pageObjects.CheckoutPage;
import sadiqueshaikhautomation.pageObjects.ConfirmationPage;
import sadiqueshaikhautomation.pageObjects.LandingPage;
import sadiqueshaikhautomation.pageObjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest {

	public LandingPage landingpage;
	public ProductCatalogue prodcatalogue;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingpage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_password(String username, String password)
	{
		prodcatalogue=landingpage.loginApplication(username, password);
	}
	
	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_the_cart(String productName)
	{
		List<WebElement> products=prodcatalogue.getProductList();
		prodcatalogue.addProductToCart(productName);		
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void Checkout_submit_the_order(String productName) throws InterruptedException
	{
		CartPage cartPage=prodcatalogue.goToCartPage();
		
		Boolean match =cartPage.VerifyProductDisplay(productName);		
        Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		confirmationPage=checkoutPage.submitOrder();
	}
	
	@Then("^\"([^\"]*)\" message is displayed$")
	public void something_message_displayed(String string1)
	{
		Assert.assertEquals(string1, landingpage.getErrorMessage()); 
		driver.close();
		
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string)
	{
		String confirmMessage =confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
}
