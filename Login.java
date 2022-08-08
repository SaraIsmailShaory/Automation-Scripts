package AccountTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestData.TestData;

public class Login {
	TestData TD=new TestData();

	@BeforeMethod
	public void lunchBrowser() {
		System.setProperty("webdriver.chrome.driver", TD.DriverPath);
		TD.driver = new ChromeDriver();
		TD.driver.navigate().to(TD.baseURL);
	}
	
	
	@Test(priority = 1)
	public void LogintoAccount() {

		TD.driver.findElement(By.linkText("ACCOUNT")).click();
		TD.driver.findElement(By.linkText("Log In")).click();
		TD.driver.findElement(By.id("email")).sendKeys(TD.Email);
		TD.driver.findElement(By.id("pass")).sendKeys(TD.Password);
		TD.driver.findElement(By.id("send2")).click();
		TD.driver.manage().window().maximize();
		}

	
	

	@AfterMethod
	public void AfterTest() {
		TD.driver.close();
	}

}
