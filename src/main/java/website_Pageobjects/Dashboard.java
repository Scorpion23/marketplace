package website_Pageobjects;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Dashboard {
	
	WebDriver driver;
	
	public Dashboard(WebDriver driver) {
		this.driver=driver;
		
		
	}

	
	public void addproducttocart(String productname) {
		
	
	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

	for (int i = 0; i < products.size(); i++) {

		WebElement parent = products.get(i);

		if (products.get(i).getText().toLowerCase().contains(productname)) {
			parent.findElement(By.cssSelector(".card-body button:last-of-type")).click();
			break;
		}

	}
	
	}
	
	public void clickoncart() {
		
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
	}
	
}
