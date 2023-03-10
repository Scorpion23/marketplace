package com.rahulshettyacademy.www.Selenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import testComponents.BaseTest;
import website_Pageobjects.Dashboard;
import website_Pageobjects.LoginPage;
import website_Pageobjects.MyCart;

//@Test(groups = { "ErrorHandling" })//?
public class Error_Validations extends BaseTest {
	ExtentReports extent;
	String url = "https://rahulshettyacademy.com/client";
	String email = "dhoni@yopmail.com";
	String password = "fgfggfg";
	By element = By.cssSelector(".toast-error");

	By cartitems = By.xpath("//div[@class='cart']//ul");
	WebDriver driver;

	public Error_Validations() {

	}

	@Test(groups = { "ErrorHandling" }) // running two tests parallely then give two different emails
	public void zincorrect_login() {

		LoginPage a = returnloginpage();
		a.login(email, password); // returns Dashboard object
		a.waituntillvisibility(element);
		String errormsg = a.getErrormessage();
		System.out.println(errormsg);
		Assert.assertEquals("Incorrect email or password.", errormsg);

	}

	// disable before tests
	@Test(groups = { "ErrorHandling" })
	public void ProductErrorValidation() throws InterruptedException {
		String email1 = "dhoni@yopmail.com";
		String password1 = "Sachin@123";
		String product = "adidas";

		LoginPage a1 = returnloginpage();
		Dashboard b1 = a1.login(email1, password1);
		b1.waitforloginmsgsuccess();
		b1.addtocart(product);
		b1.waituntilldisappear21();
		b1.waituntillappear2();
		b1.waituntillcartclickable();
		MyCart c1 = b1.clickoncart();
		c1.getStringfromlist(cartitems, product);
		c1.boolean_assertion(cartitems, product);

	}

	public void config() // Extent Reports File Name Document Tile same as page title Tester Name
	{
		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");// report name
		reporter.config().setDocumentTitle("Test Results");// document title
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Sachin");

	}

}
