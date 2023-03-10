package website_Pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import website_AbstractComponents.AbstractComponents;

public class Orders_History extends AbstractComponents {
	

	WebDriver driver;
	
	public  Orders_History(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
			

	}

	public void myorderspage(WebDriver driver) {
		
		myorders();
	}
	

}
