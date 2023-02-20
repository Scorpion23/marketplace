package website_Pageobjects;




import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import website_AbstractComponents.AbstractComponents;


public class LoginPage extends AbstractComponents {
	 WebDriver driver; // instance variable > an object of a class contains instance variables, instance methods
	
	
	
	public LoginPage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		
		
	}
	

	public void landingpage(String url, String email, String password) {

		driver.get(url);

		driver.manage().window().maximize();

		driver.findElement(By.id("userEmail")).sendKeys(email);

		driver.findElement(By.id("userPassword")).sendKeys(password);

		driver.findElement(By.id("login")).click();
	

}


}
