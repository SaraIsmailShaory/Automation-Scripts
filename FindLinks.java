


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class FindLinks {
	
	
	public WebDriver driver;
	public String baseURL = "http://magento-demo.lexiconn.com/";
	public String DriverPath = "C:\\Users\\sarat\\Downloads\\chromedriver.exe";
	
	@BeforeMethod
	public void lunchBrowser() {
		System.setProperty("webdriver.chrome.driver", DriverPath);
		driver = new ChromeDriver();
		driver.navigate().to(baseURL);
	}
	
	@Test
	public void TestLinks()
	{
		String url = "";
		HttpURLConnection huc = null;
		int PassCode = 200;
		driver.manage().window().maximize();
		List<WebElement> links = driver.findElements(By.tagName("a"));

		Iterator<WebElement> it = links.iterator();
		
		int size = links.size();

		System.out.println("Total Number of Links is "+size);

		while(it.hasNext()){

		url = it.next().getAttribute("href");

		System.out.println(url);

		if(url == null || url.isEmpty()){
		System.out.println("URL is either not configured for anchor tag or it is empty");
		continue;
		}

		if(!url.startsWith(baseURL)){
		System.out.println("URL belongs to another domain, skipping it.");
		continue;
		}

		try {
		huc = (HttpURLConnection)(new URL(url).openConnection());

		huc.setRequestMethod("HEAD");

		huc.connect();

		PassCode = huc.getResponseCode();


		if(PassCode >= 400){
		System.out.println(url+" is a broken link");
		}
		else{
		System.out.println(url+" is a valid link");
		}

		} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		}
		}

	@AfterMethod
	public void AfterTest() {
		driver.close();
	}

	
}
