package website_AbstractComponents;



import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class AbstractComponents {
	
	
	WebDriver driver;
	
	
	public AbstractComponents(WebDriver driver) {
		
		this.driver=driver;
	}

	public void myorders() {
		
		driver.findElement(By.cssSelector("button[routerlink='/dashboard/myorders']")).click();
		
	} 
	
	public void signout() {
		
		driver.findElement(By.cssSelector(".fa-sign-out")).click();
	}
	
	
	
	/// *** all these methods should contain driver the methods which call these methods do not provide driver info
	
	public void waituntillvisibility(By locator) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}
	
	public void waituntillclickable(By locator) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		wait.until(ExpectedConditions.elementToBeClickable(locator));
		
	}
	
	
	/*
	 * public void waituntillInvisibility(By locator) {
	 *  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	 * 
	 * wait.until(ExpectedConditions.invisibilityOf(driver.findElement(locator)));
	 * 
	 * }
	 */
	
	public void waituntillInvisibility(By locator) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

	    wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	/*
	 * public void fluentwait(By locator) {
	 * 
	 * Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	 * .withTimeout(Duration.ofSeconds(30)) .pollingEvery(Duration.ofSeconds(5))
	 * .ignoring(NoSuchElementException.class);
	 * wait.until(ExpectedConditions.invisibilityOf(driver.findElement(locator)));
	 * 
	 * }
	 */
	
	//compare two strings for equality
	public void String_assertion(By toast,String actual) {
		
		String text = driver.findElement(toast).getText();
		System.out.println(driver.findElement(toast).getText());
		Assert.assertEquals(actual, text);
		
	}
	
	//compare boolean if text contains partial text - webelement
	
	public void String_assertion2(By locator1,String a) {
		
		Boolean textcontains = driver.findElement(locator1).getText().toLowerCase().contains(a);
		System.out.println(driver.findElement(locator1).getText());
		Assert.assertTrue(textcontains);
		
	}
	
	
	//	//compare boolean if text contains partial text - webelements

	
	public void boolean_assertion(By locator2, String b) {
		
		List<WebElement> cartProducts = driver.findElements(locator2);
		Boolean match = cartProducts.stream().anyMatch(cartproduct -> cartproduct.getText().toLowerCase().contains(b));
		Assert.assertTrue(match);
	}
	
	public void movetoElementandclick(By element) {
		
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(element)).click().build().perform();
	}
	
	
	public void getStringfromlist(By locator3, String c) {
		
		List<WebElement> cartProducts = driver.findElements(locator3);
		Boolean match = cartProducts.stream().anyMatch(cartproduct -> cartproduct.getText().toLowerCase().contains(c));
		Assert.assertTrue(match); 
		
		if (match) {
			System.out.println("String found: " + c);
		}
	}
	
	

}
