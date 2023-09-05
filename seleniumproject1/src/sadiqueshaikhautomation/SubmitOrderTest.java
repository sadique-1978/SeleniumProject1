package sadiqueshaikhautomation;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestComponents.BaseTest;

import static org.testng.Assert.assertTrue;

import io.github.bonigarcia.wdm.WebDriverManager;
import sadiqueshaikhautomation.pageObjects.CartPage;
import sadiqueshaikhautomation.pageObjects.CheckoutPage;
import sadiqueshaikhautomation.pageObjects.ConfirmationPage;
import sadiqueshaikhautomation.pageObjects.LandingPage;
import sadiqueshaikhautomation.pageObjects.OrderPage;
import sadiqueshaikhautomation.pageObjects.ProductCatalogue;



public class SubmitOrderTest extends BaseTest{

	String productName ="ZARA COAT 3";
	
	@Test(dataProvider="getData", groups={"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	 {
		
		
		ProductCatalogue prodcatalogue=landingpage.loginApplication(input.get("email"), input.get("password"));		
		
		List<WebElement> products=prodcatalogue.getProductList();
		prodcatalogue.addProductToCart(input.get("product"));
		CartPage cartPage=prodcatalogue.goToCartPage();
		
		Boolean match =cartPage.VerifyProductDisplay(input.get("product"));		
        Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage=checkoutPage.submitOrder();

		String confirmMessage =confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistory()
	{
		ProductCatalogue prodcatalogue=landingpage.loginApplication("sadique.ss@test.com", "Tester786");
		OrderPage ordersPage=prodcatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}
	
	
	  
	@DataProvider
	public Object[][] getData()throws IOException
	  { 	  
		  
		  List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//sadique//shaikhautomation//data//PurchaseOrder.json");
		  return new Object[][] {{data.get(0)},{data.get(1)}};

	  }
	
	
	/*
	 * @DataProvider public Object[][] getData() { return new Object[][]
	 * {{"sadique.ss@test.com", "Tester786","ZARA COAT 3"},{"sadique@test.com",
	 * "Tester@1978","ADIDAS ORIGINAL"}}; }
	 */
	 
		/*
		 * HashMap<String,String> map=new HashMap<String,String>(); map.put("email",
		 * "sadique.ss@test.com"); map.put("password", "Tester786"); map.put("product",
		 * "ZARA COAT 3");
		 * 
		 * HashMap<String,String> map1=new HashMap<String,String>(); map1.put("email",
		 * "sadique@test.com"); map1.put("password", "Tester@1978"); map1.put("product",
		 * "ADIDAS ORIGINAL");
		 */
}
