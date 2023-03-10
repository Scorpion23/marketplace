package website_Pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import website_AbstractComponents.AbstractComponents;

public class Dashboard extends AbstractComponents {
	

	WebDriver driver;
	//By byloginwait = By.cssSelector("#toast-container");
	
	//By byloginwait = By.cssSelector("div[aria-label='Login Successfully']");
	By byloginwait = By.xpath("//*[@id=\'toast-container\']/div/div");
	By byproductadd2cart = By.xpath("//div[text()=' Product Added To Cart ']");
	By element = By.xpath("//button[@routerlink='/dashboard/cart']");
	
	By spinner = By.cssSelector(".ng-animating");
	
	By cartclickable = By.xpath("//button[@routerlink='/dashboard/cart']");
	
	//By spinner = By.cssSelector(".ngx-spinner-overlay");
	
	public  Dashboard(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		
	}
	
	public void waitforloginmsgsuccess() {
		
		waituntillInvisibility(byloginwait);
	}
	
	public void addtocart(String product) {
		List<WebElement> products = driver.findElements(By.cssSelector(".card-body"));
		//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3.ng-star-inserted"));
		for (int i = 0; i < products.size(); i++) {

			WebElement parent = products.get(i);

			if (products.get(i).getText().toLowerCase().contains(product)) {
				parent.findElement(By.cssSelector(".w-10")).click();
				
				break;
			}

		}
		
	}
	
	public MyCart clickoncart()  {
		
		   //wait not working
		
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		MyCart mycart = new MyCart(driver);
		return mycart;
		
	}
	
	public void waituntillappear2() {
		
		waituntillvisibility(byproductadd2cart);
	}
	
	public MyCart moveandclick() {
		
		movetoElementandclick(element);
		MyCart mycart = new MyCart(driver);
		return mycart;
	}
	
	
	public void waituntilldisappear21() {
		
		waituntillInvisibility(spinner);
	}
	
	
	public void waituntillcartclickable() {
		
		waituntillclickable(cartclickable);
	}
	
	
	
	
	
	
	
	
	
	
}
