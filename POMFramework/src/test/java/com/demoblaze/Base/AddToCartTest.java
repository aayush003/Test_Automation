package com.demoblaze.Base;
//package com.demoblaze.TestCases;
//package com.demoblaze.Base;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import com.demoblaze.Pages.CartPage;
import com.demoblaze.Pages.HomePage;

public class AddToCartTest extends TestBase{
	HomePage homepage;
	CartPage cart;
	//AddToCartPage addToCartPage;
	
	public AddToCartTest() {
		super ();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		homepage = new HomePage();
		cart = new CartPage();
	}
	
	@Test(priority =1)
	public void homepage_select_item_category() throws InterruptedException {
		
		homepage.addLaptop("Sony vaio i5");
		homepage.addLaptop("Dell i7 8gb");
		
	}
	
	@Test
	public void delete_item_and_placeOrder() throws InterruptedException {
		cart.clickCart();
		cart.deleteProductByName("Dell i7 8gb");
		
	}
	
	@AfterMethod
	public void teardown() {
		driver.close();
		driver.quit();
		
	}
}
