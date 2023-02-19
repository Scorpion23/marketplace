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

public class StandAloneTest {

	public static void main(String[] args) {
		String product = "zara";

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();

		driver.findElement(By.id("userEmail")).sendKeys("pass23@yopmail.com");

		driver.findElement(By.id("userPassword")).sendKeys("Sachin@123");

		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(
				ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div"))));
		String LoginSuccess = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div")).getText();
		Assert.assertEquals("Login Successfully", LoginSuccess);

		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector(".mb-3"))));

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		for (int i = 0; i < products.size(); i++) {

			WebElement parent = products.get(i);

			if (products.get(i).getText().toLowerCase().contains(product)) {
				parent.findElement(By.cssSelector(".card-body button:last-of-type")).click();
				break;
			}

		}

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()=' Product Added To Cart ']")));

		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartWrap"));

		Boolean match = cartProducts.stream()
				.anyMatch(cartproduct -> cartproduct.getText().toLowerCase().contains(product));

		Assert.assertEquals(true, match);
		System.out.println(match);
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();

		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("India");

		WebElement countrydrop = driver.findElement(By.cssSelector(".ta-results"));
		countrydrop.findElement(By.xpath("//div/div[1]/div/section/button[2]")).click();

		driver.findElement(By.xpath("//a[text()='Place Order ']")).click();

		String thankyou = driver.findElement(By.cssSelector(".hero-primary")).getText();
		System.out.println(driver.findElement(By.cssSelector(".hero-primary")).getText());

		Assert.assertEquals(true, thankyou.toLowerCase().contains("thank"));
		
		
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/myorders']")).click();
		
		List<WebElement> orderedproducts = driver.findElements(By.xpath("//tr/td[2]"));
		
		Boolean orderhistorymatch = orderedproducts.stream().anyMatch(orderedproduct -> orderedproduct.getText().toLowerCase().contains(product));
		
		Assert.assertEquals(true, orderhistorymatch);
		
		System.out.println(orderhistorymatch);
		
		driver.findElement(By.cssSelector(".fa-sign-out")).click();
		
		Boolean logout = driver.findElement(By.xpath("//div[text()=' Logout Successfully ']")).getText().toLowerCase().contains("Logout");
		
		System.out.println(driver.findElement(By.xpath("//div[text()=' Logout Successfully ']")).getText());
		
		Assert.assertEquals(true, logout);
	}

}
