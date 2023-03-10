package website_Pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import website_AbstractComponents.AbstractComponents;

public class Payment extends AbstractComponents {
	

	WebDriver driver;
	
	public  Payment(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		
	}
	
	public void placeorder(String country) {
	
	driver.findElement(By.xpath("(//input[@placeholder='Select Country'])[1]")).sendKeys(country);
	

	//WebElement countrydrop = driver.findElement(By.cssSelector(".ta-results"));
	//countrydrop.findElement(By.cssSelector(".fa-search")).click();
	
	driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
	
	}

}
