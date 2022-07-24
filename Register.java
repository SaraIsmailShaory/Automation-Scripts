package AccountTest;


import static org.testng.Assert.fail;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import TestData.TestData;

public class Register {
	
	TestData TD=new TestData();

	@BeforeMethod
	public void lunchBrowser() {
		System.setProperty("webdriver.chrome.driver", TD.DriverPath);
		TD.driver = new ChromeDriver();
		TD.driver.navigate().to(TD.baseURL);
	}

	@Test(priority = 1)
	public void RegisterAnAccount() {
		TD.driver.findElement(By.linkText("ACCOUNT")).click();

		TD.driver.findElement(By.linkText("Register")).click();

		TD.driver.findElement(By.id("firstname")).sendKeys(TD.FirstName);

		TD.driver.findElement(By.id("lastname")).sendKeys(TD.LastName);

		TD.driver.findElement(By.id("email_address")).sendKeys(TD.Email);

		TD.driver.findElement(By.id("password")).sendKeys(TD.Password);

		TD.driver.findElement(By.id("confirmation")).sendKeys(TD.ConfirmPassword);

		TD.driver.findElement(By.xpath("//*[@id=\"form-validate\"]/div[2]/button")).click();

//Waiting untill the element is visiable in the screen 
		TD.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Assert.assertTrue(TD.driver.getPageSource().contains("Thank you for registering with Madison Island."));
		TD.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	

	@AfterMethod
	public void AfterTest() {
		TD.driver.close();
	}



}
