package website_Pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyCart {
	
	WebDriver driver;
	
	public MyCart(WebDriver driver) {
		this.driver=driver;
		
	}
	
	
	
	public void clickoncheckout() {
		
	driver.findElement(By.xpath("//button[text()='Checkout']")).click();
	
	}
}
