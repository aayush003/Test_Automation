package com.demoblaze.Pages;

import java.util.List;

import org.apache.poi.hssf.record.CountryRecord;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.AssertJUnit;

import com.demoblaze.Base.TestBase;

public class CartPage extends TestBase{
	
	private static WebDriver driver;
	private static String amount;
	private static String id;
	
	@FindBy(id = "cartur")
	WebElement cart;
	
	@FindBy(xpath = "//*[@id=\"tbodyid\"]/tr")
	static
	List<WebElement> selected_categories;
	
	@FindBy(xpath = "//*[@class=\"btn btn-success\"]")
	WebElement placeOrder;
	
	@FindBy(id = "name")
	WebElement name;
	
	@FindBy(id = "country")
	WebElement country;
	
	@FindBy(id = "city")
	WebElement city;
	
	@FindBy(id = "card")
	WebElement card;
	
	@FindBy(id = "month")
	WebElement month;
	
	@FindBy(id = "year")
	WebElement year;
	
	public CartPage() {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public void clickCart() {
		cart.click();
		//driver.findElement((By) cart).click();
	}
	
	public static void deleteProductByName(String name) throws InterruptedException {
		
		//driver.findElement(By.id("cartur")).click();
		//List<WebElement> categories =  driver.findElementsByXPath("//*[@id=\"tbodyid\"]/tr");
		for (WebElement webElement : selected_categories) {
			WebElement element = webElement.findElement(By.xpath("./td[2]"));
			if(element.getText().equals(name)) {
				webElement.findElement(By.xpath("./td[4]/a")).click();
				return;
			}
		}
	}
	
	public void clickPlaceOrder() {
		placeOrder.click();
	}
	
	public void enterDetails() throws InterruptedException {
		
		name.sendKeys(prop.getProperty("name"));
		country.sendKeys(prop.getProperty("country"));
		city.sendKeys(prop.getProperty("city"));
		card.sendKeys(prop.getProperty("card"));
		month.sendKeys(prop.getProperty("month"));
		year.sendKeys(prop.getProperty("year"));
		
		Thread.sleep(3000);
		
	}
	
	public static void scrollAndClick(By by)
	{
	   WebElement element = driver.findElement(by);
	   int elementPosition = element.getLocation().getY();
	   String js = String.format("window.scroll(0, %s)", elementPosition);
	   ((JavascriptExecutor)driver).executeScript(js);
	   element.click();
	}
	
	public static void placeOrder() throws InterruptedException {
		/*
		 * driver.findElementByXPath("//*[@class=\"btn btn-success\"]").click();
		 * Thread.sleep(5000);
		 * 
		 * driver.findElementById("name").sendKeys("Aayush Jain");
		 * 
		 * 
		 * driver.findElementById("country").sendKeys("India");
		 * driver.findElementById("city").sendKeys("Delhi");
		 * driver.findElementById("card").sendKeys("xxxx-xxxx-xxxx");
		 * driver.findElementById("month").sendKeys("August");
		 * driver.findElementById("year").sendKeys("2020");
		 * 
		 * Thread.sleep(5000);
		 */
		
		
		scrollAndClick(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]"));
		
		Thread.sleep(5000);
		String data = driver.findElement(By.xpath("/html/body/div[10]/p")).getText();
		String[] info = data.split("\\r?\\n");
		
		for (String string : info) {
			String[] attributes = string.split(":");
			if(attributes[0].equals("Id"))
				id = attributes[1];
			else if(attributes[0].equals("Amount"))
				amount = attributes[1].trim();
		}
		Assert.assertEquals(amount, "790 USD");
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div[10]/div[7]/div/button")).click();
		
	}

}
