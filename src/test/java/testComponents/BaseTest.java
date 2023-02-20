package testComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import website_Pageobjects.LoginPage;

public class BaseTest {
	
	LoginPage loginpage;// object of LoginPage
	
	
	
	public LoginPage  login() {
		
		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();

		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();

		driver.findElement(By.id("userEmail")).sendKeys("pass23@yopmail.com");

		driver.findElement(By.id("userPassword")).sendKeys("Sachin@123");

		driver.findElement(By.id("login")).click();
		return loginpage;
		
	}



}

