package Testcase;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestScript.ReadingElements;

public class LoginTestCase {
	 WebDriver driver;
	
	@BeforeMethod
	public void Launch()
	{
		System.setProperty("webdriver.chrome.driver", "src\\Drivers\\chromedriver.exe");
		
		 driver = new ChromeDriver();

		driver.get("https://www.facebook.com/");
	}

	@Test
	public void LoginTc() throws IOException
	{
		
		Assert.assertEquals(driver.getTitle(), "Facebook – log in or sign up");
		
		ReadingElements r= new ReadingElements();
		
	WebElement Unm	= r.getWebElementReference("UserName", driver);
	Unm.sendKeys("aaaaa");

	r.getWebElementReference("Password", driver).sendKeys("test");
	
	r.getWebElementReference("Log", driver).click();
	}

	@AfterMethod
	//public void TearDown()
	public void TearDown(ITestResult r) throws IOException
	{
		int statusTC =r.getStatus();
		int expedtedStatus =ITestResult.FAILURE;
		
		//if(r.getStatus() == ITestResult.FAILURE)
		if(statusTC == expedtedStatus)
		
		{
			Date d = new Date();
			
			DateFormat df= new SimpleDateFormat("dd-yyyy hh-mm-ss");
			String day = df.format(d);
			System.out.println(day);
			
			File	x	= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			   File y = new File("src\\scrren\\img"+day + ".png");
			   FileUtils.copyFile(x, y);
		}
		else
		{
			driver.close();
		}
		
	}
}
