package website_Pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import website_AbstractComponents.AbstractComponents;

public class MyCart extends AbstractComponents {
	

	WebDriver driver;
	
	public  MyCart(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		
	}
	
	
	
	
	public Payment checkout() {
		
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		
		Payment payment = new Payment(driver);
		return payment;
	}
	
	
	
	
}
