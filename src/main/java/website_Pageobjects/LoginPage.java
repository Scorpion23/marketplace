package website_Pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import website_AbstractComponents.AbstractComponents;

public class LoginPage extends AbstractComponents {
	

	WebDriver driver;
	

	//By LoginError = By.xpath("//*[@id='toast-container']");
	
	//By LoginError = By.cssSelector(".toast-error");
	By LoginError = By.cssSelector(".toast-bottom-right");
	
	public  LoginPage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;   //goes to line 11
		
		
	}
	
	
	
	public void geturl(){
		
	driver.get("https://rahulshettyacademy.com/client");
	//driver.manage().window().maximize();


}
	public Dashboard login(String email, String password) {  // login() = new Dashboard(driver)
		
		driver.findElement(By.id("userEmail")).sendKeys(email);

		driver.findElement(By.id("userPassword")).sendKeys(password);

		driver.findElement(By.id("login")).click();
		
		Dashboard dashboard = new Dashboard(driver);
		
		return dashboard;
		
	}
	
	
	public String getErrormessage() {
		
		//waituntillvisibility(LoginError);
		
		return  driver.findElement(LoginError).getText();
		
	}
	

}