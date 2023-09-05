package sadiqueshaikhautomation;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static org.testng.Assert.assertTrue;

import io.github.bonigarcia.wdm.WebDriverManager;
import sadiqueshaikhautomation.pageObjects.LandingPage;



public class StandAloneTest {
	
	
	public static void main(String[] args) {
		String productName ="zara coat 3";
		WebDriver driver = WebDriverManager.chromedriver().create();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		driver.get("https://rahulshettyacademy.com/client");
		
		LandingPage lp= new LandingPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("sadique.ss@test.com");
		driver.findElement(By.id("userPassword")).sendKeys("Tester786");
		driver.findElement(By.id("login")).click();
		
		
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector("[routerlink*=cart]")).click();
		
		
		List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartsection h3"));	
		
		boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
        assertTrue(match);

		driver.findElement(By.cssSelector(".totalRow button")).click();
		

		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirmMsg =driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
	}

}
