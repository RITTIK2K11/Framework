package Selenium_testcases;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
public class ScreenshootGoogle {
  public WebDriver driver;
  
  
  @Test
  public void TestJavaS1()
 {
 // Open Chrome
	  System.setProperty("webdriver.chrome.driver","C:\\Users\\user\\Desktop\\chromedriver.exe");
 driver=new ChromeDriver();
  
 // Maximize the window
 driver.manage().window().maximize();
  
 // Pass the url
 driver.get("http://www.google.com");
  
 // Take screenshot and store as a file format
 File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
 try {
  // now copy the  screenshot to desired location using copyFile //method
 FileUtils.copyFile(src, new File("C:/Users/user/Desktop/error.png"));
 }
  
 catch (IOException e)
  {
   System.out.println(e.getMessage());
  
  }
 driver.close(); }
}