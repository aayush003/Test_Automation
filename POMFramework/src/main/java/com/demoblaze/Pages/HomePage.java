package com.demoblaze.Pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demoblaze.Base.TestBase;

public class HomePage extends TestBase{

	@FindBy(xpath = "//*[@id=\"itemc\"]")
	static
	List<WebElement> categories;

	@FindBy(xpath = "//*[@id=\"tbodyid\"]/div")
	static
	List<WebElement> laptop_categories;


	public HomePage() {

		PageFactory.initElements(driver, this);

	}


	public void getCategory(String category) throws InterruptedException {
		//List<WebElement> categories =  driver.findElementsByXPath("//*[@id=\"itemc\"]");
		
		for (WebElement webElement : categories) {
			if(webElement.getText().equals(category)) {
				//System.out.println(webElement.getText());
				//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				Thread.sleep(5000);
				webElement.click();
				Thread.sleep(5000);
				return;
			}
		}
		//System.out.println(driver.getTitle());
		//System.out.println(driver.getCurrentUrl());
	}
	
	

	public void getLaptopByName(String name) throws InterruptedException {

		//System.out.println(driver.getTitle());
		//System.out.println(driver.getCurrentUrl());
		//List<WebElement> categories =  driver.findElementsByXPath("//*[@id=\"tbodyid\"]/div");
		for (WebElement webElement : laptop_categories) {
			//System.out.println(webElement.getText());
			WebElement element = webElement.findElement(By.xpath(".//*[@class=\"card-title\"]"));
			//System.out.println("after webelement : "+element.getText());
			if(element.getText().equals(name)) {
				element.click();
				
				Thread.sleep(5000);
				driver.findElement(By.xpath("//*[@class=\"btn btn-success btn-lg\"]")).click();
				
				Thread.sleep(5000);
				driver.switchTo().alert().accept();
				Thread.sleep(3000);
				return;
			}
		}
	}
	
	public void addLaptop(String name) throws InterruptedException {
		
		getCategory("Laptops");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		getLaptopByName(name);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//*[@id=\"navbarExample\"]/ul/li[1]/a")).click();
		Thread.sleep(3000);
		
		//return new HomePage();
	}
}
