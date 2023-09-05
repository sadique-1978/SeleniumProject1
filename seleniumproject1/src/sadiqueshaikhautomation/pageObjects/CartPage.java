package sadiqueshaikhautomation.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.AbstarctComponent;

public class CartPage extends AbstarctComponent{
	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	  @FindBy(css=".totalRow button")
	  WebElement checkoutEle;
	 

	
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartProducts;
	
	//By productsBy= By.cssSelector(".mb-3");
	
	
	
	public Boolean VerifyProductDisplay(String productName)
	{
		Boolean match = cartProducts.stream().anyMatch(product-> product.getText().equalsIgnoreCase(productName));
	    return match;
	}
	
	public CheckoutPage goToCheckout()
	{
		checkoutEle.click();
		return new CheckoutPage(driver);

	}
	
	

}
