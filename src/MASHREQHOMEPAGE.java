

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
public class MASHREQHOMEPAGE {
  public WebDriver driver;
  
  
  @Test
  public void MASHREQHOMEPAGE() throws InterruptedException {
	System.setProperty("webdriver.chrome.driver","C:\\Users\\user\\Desktop\\chromedriver.exe");
	String URL ="https://www.mashreqbank.com/uae/en/personal/home";
	 driver=new ChromeDriver();
    driver.get(URL);
    Thread.sleep(6000);
    driver.manage().window().maximize();
    Thread.sleep(6000);
    driver.findElement(By.xpath("/html/body/div[2]/div[4]/div[1]/nav/div/div[2]/div/ul/li[3]/a/span")).click();
    Thread.sleep(6000);
    new Select(driver.findElement(By.xpath("//select[@id='compInqServ']"))).selectByVisibleText("Benefits & Promotions");
    driver.findElement(By.id("compInqServ")).click();
    new Select(driver.findElement(By.xpath("//select[@id='reachoutforproduct']"))).selectByVisibleText("Enquire about a product");
    driver.findElement(By.id("reachoutforproduct")).click();
    driver.findElement(By.id("need")).click();
    new Select(driver.findElement(By.id("need"))).selectByVisibleText("Investments and Financial Planning");
    driver.findElement(By.id("need")).click();
    driver.findElement(By.id("product")).click();
    new Select(driver.findElement(By.id("product"))).selectByVisibleText("UAE Market");
    driver.findElement(By.id("product")).click();
    driver.findElement(By.id("emirate")).click();
    new Select(driver.findElement(By.id("emirate"))).selectByVisibleText("Dubai");
    driver.findElement(By.id("emirate")).click();
    driver.findElement(By.id("branch")).click();
    new Select(driver.findElement(By.id("branch"))).selectByVisibleText("Jumeirah");
    driver.findElement(By.id("branch")).click();
    driver.findElement(By.id("firstName")).click();
    
    driver.findElement(By.id("firstName")).sendKeys("ROHAN");
    
    driver.findElement(By.id("lastName")).sendKeys("BAKSI");
    
    driver.findElement(By.id("email")).sendKeys("burittik@gmail.com");
   
    driver.findElement(By.xpath("//input[@id='mobile']")).sendKeys("900768");
   
    driver.findElement(By.xpath("//button[@id='btnSubmit']")).click();
    Thread.sleep(6000);
   String textshowerror= driver.findElement(By.xpath("//input[@id='mobile']//following-sibling::span")).getText();
   System.out.println(textshowerror);
   Assert.assertEquals("Mobile number should be 7 digit", textshowerror);
   driver.quit();
   
  }


}