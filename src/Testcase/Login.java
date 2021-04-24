package Testcase;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import TestScript.ReadingElements;

public class Login {

	public static void main(String[] args) throws IOException {
		System.setProperty("webdriver.chrome.driver", "src\\Drivers\\chromedriver.exe");
		
		 WebDriver driver = new ChromeDriver();

		driver.get("https://www.facebook.com/");
		
		ReadingElements r= new ReadingElements();
		
	WebElement Unm	= r.getWebElementReference("UserName", driver);
	Unm.sendKeys("aaaaa");

	r.getWebElementReference("Password", driver).sendKeys("test");
	
	r.getWebElementReference("Log", driver).click();
	}

}
