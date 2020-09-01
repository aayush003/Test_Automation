package com.demoblaze.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	
	
	public TestBase () {
		
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("D:\\Eclipse workspace-20200724T163020Z-001\\POMFramework\\src\\main\\resources\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "static-access", "deprecation" })
	public static void initialization(){
		
		String browserName  = prop.getProperty("browser");
		//System.out.println(browserName);
		//System.out.println("Entering Initialization");
		
		if(browserName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",prop.getProperty("path_chromedriver") );
			//DesiredCapabilities capabilities = new DesiredCapabilities().chrome();
			//System.out.println("cap added"+ capabilities);
			try {
	        //driver = new ChromeDriver(capabilities);
	        driver = new ChromeDriver();
	        System.out.println("chrome capabilities");
			}catch(Exception e) {
				System.out.println("Exception while instantiating driver.");
			}
		}
		else if (browserName.equals("firefox"))
		{
			System.setProperty("webdriver.geckodriver.driver",prop.getProperty("path_geckodriver") );
			DesiredCapabilities capabilities = new DesiredCapabilities().firefox();
			
			try {
	        driver = new FirefoxDriver(capabilities);
	        System.out.println("firefox capabilities");

			}catch(Exception e) {
				System.out.println("Exception while instantiating driver.");
			}
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		
		
	}
}
