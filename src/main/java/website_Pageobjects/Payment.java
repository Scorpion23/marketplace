package website_Pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Payment {
	
	WebDriver driver;
	
	public Payment(WebDriver driver) {
		this.driver=driver;
		
	}
	
	
	public void placeorder(String country) {
		
		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys(country);
		WebElement countrydrop = driver.findElement(By.cssSelector(".ta-results"));
		countrydrop.findElement(By.xpath("//div/div[1]/div/section/button[2]")).click();
		driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
		
	}

}
