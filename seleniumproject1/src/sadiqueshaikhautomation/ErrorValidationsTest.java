package sadiqueshaikhautomation;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import TestComponents.Retry;

import static org.testng.Assert.assertTrue;

import io.github.bonigarcia.wdm.WebDriverManager;
import sadiqueshaikhautomation.pageObjects.CartPage;
import sadiqueshaikhautomation.pageObjects.CheckoutPage;
import sadiqueshaikhautomation.pageObjects.ConfirmationPage;
import sadiqueshaikhautomation.pageObjects.LandingPage;
import sadiqueshaikhautomation.pageObjects.ProductCatalogue;



public class ErrorValidationsTest extends BaseTest{
	
	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)	
	public void LoginErrorValidations() throws IOException, InterruptedException
	 {
		
		landingpage.loginApplication("sadique.ss@test.com", "Tester7786");
	    Assert.assertEquals("Incorrect email password.", landingpage.getErrorMessage()); 

	}
	
	@Test
	public void ProductErrorValidations() throws IOException, InterruptedException
	 {
		String productName ="ZARA COAT 3";
		
		ProductCatalogue prodcatalogue=landingpage.loginApplication("sadique@test.com", "Tester@1978");		
		
		List<WebElement> products=prodcatalogue.getProductList();
		prodcatalogue.addProductToCart(productName);
		CartPage cartPage=prodcatalogue.goToCartPage();
		
		Boolean match =cartPage.VerifyProductDisplay("ZARA COAT");		
        Assert.assertFalse(match);
		

	}

}
