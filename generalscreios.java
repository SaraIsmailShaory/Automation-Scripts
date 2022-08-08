package AccountTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestData.TestData;

public class generalscreios {
	TestData TD=new TestData();

	@BeforeMethod
	public void lunchBrowser() {
		System.setProperty("webdriver.chrome.driver", TD.DriverPath);
		TD.driver = new ChromeDriver();
		TD.driver.navigate().to(TD.baseURL);
	}
	
	@Test
	public void LogintoAccount() {

		TD.driver.findElement(By.linkText("ACCOUNT")).click();
		TD.driver.findElement(By.linkText("Log In")).click();
		TD.driver.findElement(By.id("email")).sendKeys(TD.Email);
		TD.driver.findElement(By.id("pass")).sendKeys(TD.Password);
		TD.driver.findElement(By.id("send2")).click();
		TD.driver.manage().window().maximize();

	
	       // hover on women menu and skirts dresses 
			WebElement Women = TD.driver.findElement(By.xpath("//*[@id=\"nav\"]/ol/li[1]"));
			Actions actions = new Actions(TD.driver);
			actions.moveToElement(Women);
			WebElement subMenu = TD.driver.findElement(By.xpath("//*[@id=\"nav\"]/ol/li[1]/ul/li[5]"));
			actions.moveToElement(subMenu);
			actions.build().perform();

	        //to Perform wait before navigating to the Women  
			TD.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); 
			TD.driver.findElement(By.xpath("//*[@id=\"nav\"]/ol/li[1]")).click();
					
			
	        //to perform Scroll down application using Selenium
			JavascriptExecutor js = (JavascriptExecutor) TD.driver;
			js.executeScript("window.scrollBy(0,300)", "");
			TD.driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/ul/li[3]/a")).click();
			TD.driver.findElement(By.xpath("//*[@id=\"product-collection-image-428\"]")).click();
			Select color = new Select(TD.driver.findElement(By.id("attribute92")));
			color.selectByIndex(1);
			Select Size = new Select(TD.driver.findElement(By.id("attribute180")));
			Size.selectByIndex(1);
			TD.driver.findElement(By.xpath("//*[@id=\"product_addtocart_form\"]/div[3]/div[6]/div[2]/div[2]/button")).click();
			
			//Checking Grand Total  < my budget && getting item Text
			String GrandTotal = TD.driver.findElement(By.xpath("//*[@id=\"shopping-cart-totals-table\"]/tfoot/tr/td[2]/strong/span")).getText();
			System.out.println(GrandTotal);
			String TrimmedGT = GrandTotal.replace("$", "");
			System.out.println(TrimmedGT);
			Float GTVal = Float.parseFloat(TrimmedGT);
			Float myBudget = (float) 350;
			if (myBudget > GTVal) {
				Assert.assertTrue(myBudget > GTVal, "your Budget Total is greater than the Grand Total");
			} else {
				Assert.fail("Failed");
			} TD.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
}

			@AfterMethod
			public void AfterTest() {
				TD.driver.close();
			}

	
}
