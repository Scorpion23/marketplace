package com.rahulshettyacademy.www.Selenium;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.sun.net.httpserver.Authenticator.Retry;

import testComponents.BaseTest;
import website_AbstractComponents.AbstractComponents;
import website_Pageobjects.Dashboard;
import website_Pageobjects.LoginPage;
import website_Pageobjects.MyCart;
import website_Pageobjects.Orders_History;
import website_Pageobjects.Payment;

public class StandAloneTest extends BaseTest {
	
	ExtentReports extent;
	public By myorders = By.xpath("//tr/td[2]");

	By bylogoutsuccess = By.xpath("//div[text()=' Logout Successfully ']");

	/*
	 * String email = "pass23@yopmail.com"; String password = "Sachin@123"; String
	 * product = "zara"; String country = "India";
	 */
	@Test(dataProvider = "getData", groups = { "Purchase" },retryAnalyzer =testComponents.Retry.class)//retryAnalyzer=Retry.class
	public void main1(HashMap<String, String> input) throws InterruptedException, IOException {
		ExtentTest test = extent.createTest("Stand Alone Test");
		WebDriver driver;

		//By bysuccess = By.xpath("//*[@id='toast-container']/div/div");
		By bysuccess = By.xpath("//div[text()=' Login Successfully']");
		By byloadproducts = By.cssSelector(".mb-3");
		By byproductadd2cart = By.xpath("/html/body/div/div/div/div");// By.xpath("//div[text()=' Product Added To Cart
																		// ']");

		By toast = By.xpath("//*[@id=\"toast-container\"]/div/div");
		String toastmsg = "Successfully";
		By cart = By.cssSelector(".cartWrap");
		By ordersuccess = By.xpath("//div/div[text()='Order Placed Successfully']");
		By ordersuccess2 = By.xpath("//div/div/div/div[text()=' Order Placed Successfully ']");
		By cart2= By.xpath("//button[@routerlink='/dashboard/cart']");
		driver = getDriver();

		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		LoginPage loginpage = new LoginPage(driver);
		loginpage.geturl();
		Dashboard dashboard = loginpage.login(input.get("email"), input.get("password"));

		AbstractComponents await = new AbstractComponents(driver);
		
		Thread.sleep(5000);
	
		/*
		 * await.waituntillvisibility(bysuccess);
		 * 
		 * await.waituntillvisibility(byloadproducts);
		 */
		
		dashboard.addtocart(input.get("product"));
	
		//dashboard.waituntillclickable(cart2);

		//await.waituntillInvisibility(byproductadd2cart);
		Thread.sleep(5000);
		MyCart mycart = dashboard.clickoncart();

		//await.boolean_assertion(cart, input.get("product"));

		Payment x = mycart.checkout();

		
		x.placeorder(input.get("country"));
		
		await.waituntillvisibility(ordersuccess2);
		await.boolean_assertion(ordersuccess2, "order");

		System.out.println(driver.findElement(By.xpath("//div/div[text()=' Order Placed Successfully ']")).getText());

		String thankyou = driver.findElement(By.cssSelector("h1.hero-primary")).getText();
		System.out.println(driver.findElement(By.cssSelector("h1.hero-primary")).getText());
		Assert.assertEquals(true, thankyou.toLowerCase().contains("thank"));

	}

	@Test(dependsOnMethods = { "main1" })
	public void orderHistoryTest() {
		String email = "pass23@yopmail.com";
		String password = "Sachin@123";
		String product = "zara";
		String country = "India";
		
		

		LoginPage loginpage = new LoginPage(driver);
		loginpage.geturl();
		loginpage.login(email, password);

		Orders_History orderhistory = new Orders_History(driver);
		orderhistory.myorders();
		AbstractComponents await = new AbstractComponents(driver);
		await.boolean_assertion(myorders, product);

		// System.out.println(orderhistorymatch);

		AbstractComponents logout = new AbstractComponents(driver);
		logout.signout();

		await.waituntillvisibility(bylogoutsuccess);

		String vlogout = driver.findElement(By.xpath("//div[text()=' Logout Successfully ']")).getText();

		Assert.assertEquals("Logout Successfully", vlogout);

		System.out.println(vlogout);

		// driver.quit();
		
		extent.flush();
	}



	
	//ExtentReports
	@BeforeMethod
	public void config()
	{
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");//report name
		reporter.config().setDocumentTitle("Test Results");//document title
		 extent = new ExtentReports();
		extent.attachReporter(reporter);
	extent.setSystemInfo("Tester", "Sachin");	
		
	}
	
	
	@DataProvider

	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}
	/*
	 * public List<HashMap<String, String>> getData() {
	 * 
	 * HashMap<String,String> map =new HashMap<String,String>(); map.put("email",
	 * "pass23@yopmail.com"); map.put("password", "Sachin@123"); map.put("product",
	 * "zara"); map.put("country", "India");
	 * 
	 * HashMap<String,String> map1 =new HashMap<String,String>(); map1.put("email",
	 * "dhoni1@yopmail.com"); map1.put("password", "Dhoni!12"); map1.put("product",
	 * "adidas"); map1.put("country", "aus");
	 * 
	 * 
	 * 
	 * ObjectMapper mapper = new ObjectMapper(); List <HashMap<String,String>> data
	 * = mapper.readValue(mapper.readValue(getJsonDataToMap, new
	 * TypeReference<List<HashMap<String,String>>>(){})); return data; //return new
	 * Object [] [] {{map},{map1}}; }
	 */

	/*
	 * public Object[][] getData() {
	 * 
	 * return new Object [] []
	 * {{"pass23@yopmail.com","Sachin@123","zara","India"},{"dhoni1@yopmail.com",
	 * "Dhoni!12","adidas","aus"}}; }
	 */

}
