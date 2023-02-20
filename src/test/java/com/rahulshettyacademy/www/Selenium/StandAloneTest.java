package com.rahulshettyacademy.www.Selenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import website_Pageobjects.Dashboard;
import website_Pageobjects.LoginPage;
import website_Pageobjects.MyCart;
import website_Pageobjects.Payment;

public class StandAloneTest {

	public static void main(String[] args) {
		String product = "adidas";
		String url ="https://rahulshettyacademy.com/client";
		String email ="pass23@yopmail.com";
		String password ="Sachin@123";
		String country = "India";

		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		LoginPage loginpage = new LoginPage(driver);
		loginpage.landingpage(url,email,password);
		  

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div"))));
		String LoginSuccess = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div")).getText();
		Assert.assertEquals("Login Successfully", LoginSuccess);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div")).getText());

		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector(".mb-3"))));
		
		
		Dashboard dashboard = new Dashboard(driver);
		dashboard.addproducttocart(product);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[text()=' Product Added To Cart ']")));
		dashboard.clickoncart();


		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartWrap"));
		Boolean match = cartProducts.stream()
				.anyMatch(cartproduct -> cartproduct.getText().toLowerCase().contains(product));

		Assert.assertTrue(match);
		System.out.println(match);
		
		
		MyCart mycart = new MyCart(driver);
		mycart.clickoncheckout();
		
		Payment payment = new Payment(driver);
		payment.placeorder(country);
		


		String thankyou = driver.findElement(By.cssSelector(".hero-primary")).getText();
		System.out.println(driver.findElement(By.cssSelector(".hero-primary")).getText());
		Assert.assertTrue(thankyou.toLowerCase().contains("thank"));
		
		
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/myorders']")).click();
		
		List<WebElement> orderedproducts = driver.findElements(By.xpath("//tr/td[2]"));
		
		Boolean orderhistorymatch = orderedproducts.stream().anyMatch(orderedproduct -> orderedproduct.getText().toLowerCase().contains(product));
		
		Assert.assertTrue(orderhistorymatch);
		
		System.out.println(orderhistorymatch);
		
		driver.findElement(By.cssSelector(".fa-sign-out")).click();
		

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()=' Logout Successfully ']")));
		
		  Boolean logout = driver.findElement(By.xpath("//div[text()=' Logout Successfully ']")).getText()
				 .toLowerCase().contains("logout");
		  
		  System.out.println(driver.findElement(By.xpath("//div[text()=' Logout Successfully ']")).getText());
		 
		
		Assert.assertTrue(logout);
	}

}
