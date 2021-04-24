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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestScript.ReadingElements;
import Utility.ExcelReadDemo;

public class LoginTestCaseExcel {
	 WebDriver driver=null;
	
	@BeforeMethod
	public void Launch()
	{
		System.setProperty("webdriver.chrome.driver", "src\\Drivers\\chromedriver.exe");
		
		 driver = new ChromeDriver();

		driver.get("https://www.facebook.com/");
	}

	@Test(dataProvider="credencials")
	public void LoginTc(String user , String pass) throws IOException
	{
		
		Assert.assertEquals(driver.getTitle(), "Facebook – log in or sign up");
		
		ReadingElements r= new ReadingElements();
		
	WebElement Unm	= r.getWebElementReference("UserName", driver);
	Unm.sendKeys(user);

	r.getWebElementReference("Password", driver).sendKeys(pass);
	
	r.getWebElementReference("Log", driver).click();
	}

	
	
	@DataProvider
	  public Object[][] credencials() throws IOException 
	  {
		ExcelReadDemo e= new ExcelReadDemo();
		
		int r =e.getLastRowDetails(0);
		int c= e.getLastColDetails(0);
		
		Object[][] obj = new Object[r+1][c];
		for(int row =1 ; row <= r; row++)
		{
			for(int col =0; col < c; col++)
			{
				obj[row][col]= e.getExcelData(0, row, col);
			}
		}
		return obj;
		
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
