package TestScript;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ReadingElements {
	FileInputStream fis;
	Properties p;

	public String getpropertiesDetails(String key) throws IOException
	{
		 fis = new FileInputStream("src\\Repository\\Locator.properties");
		
		 p = new Properties();
		p.load(fis);
		String ElemnetDetails = p.getProperty(key);  //  id:email
		return ElemnetDetails;
	}
	
	
	public WebElement getWebElementReference(String key1 , WebDriver driver) throws IOException
	{
		WebElement ele= null;
		ReadingElements r= new ReadingElements();
		String element = r.getpropertiesDetails(key1);  //  id:email
		String[] info = element.split(":");  //  info[0]= id , info[1]= email
		
		switch(info[0])
		{
		case "id":
			 ele = driver.findElement(By.id(info[1]));
			 break;
			
		case "name":
			 ele = driver.findElement(By.name(info[1]));
			 break;
		case "xpath":
			 ele = driver.findElement(By.xpath(info[1]));
			 break;
		case "css":
			ele = driver.findElement(By.cssSelector(info[1]));
			 break;
			 
			 default:
				 System.out.println("wrong locator found...");
		}
		return ele;
		
	}
	
}
