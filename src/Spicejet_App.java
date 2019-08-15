

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
public class Spicejet_App {
  public WebDriver driver;
  
  
  @Test
  public void test_SpicejetWebsite() throws Exception {
	System.setProperty("webdriver.chrome.driver","C:\\Users\\user\\Desktop\\chromedriver.exe");
	WebDriver driver=new ChromeDriver();
    driver.get("https://www.spicejet.com/");
    Thread.sleep(6000);
    driver.manage().window().maximize();
    Thread.sleep(6000);
    driver.findElement(By.xpath("//input[@id='ctl00_mainContent_rbtnl_Trip_1']")).click();
    Thread.sleep(6000);
    driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXTaction")).click();
    driver.findElement(By.linkText("Kolkata (CCU)")).click();
    driver.findElement(By.id("ctl00_mainContent_ddl_destinationStation1_CTXT")).click();
    driver.findElement(By.linkText("Goa (GOI)")).click();
    driver.findElement(By.linkText("15")).click();
    Thread.sleep(6000);
    driver.findElement(By.xpath("//span[@id='view_fulldate_id_2']//preceding-sibling::button")).click();
    driver.findElement(By.linkText("22")).click();
    Thread.sleep(6000);
    driver.findElement(By.id("divpaxinfo")).click();
    Thread.sleep(6000);
    driver.findElement(By.xpath("//span[@id='spanAudlt']//following-sibling::span")).click();
    driver.findElement(By.id("hrefIncAdt")).click();
    driver.findElement(By.id("hrefIncAdt")).click();
    driver.findElement(By.id("hrefIncChd")).click();
    driver.findElement(By.id("hrefIncChd")).click();
    driver.findElement(By.id("hrefIncInf")).click();
    driver.findElement(By.id("btnclosepaxoption")).click();
    Thread.sleep(6000);
    new Select(driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"))).selectByVisibleText("AED");
    driver.findElement(By.id("ctl00_mainContent_btn_FindFlights")).click();
    driver.close();
  }


}