package com.qa.orangehrm.tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PHPTravelsDemoTest {

	public static void main(String[] args) throws InterruptedException {
		ElementUtil util = new ElementUtil();
		System.setProperty("webdriver.chrome.driver", "D:\\MukeshAutomation\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.get("https://www.phptravels.net/index.php");
		driver.findElement(By.xpath("(//a[@id='dropdownCurrency'])[2]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//a[contains(text(),'Login')])[1]")).click();
		Thread.sleep(3000);
		//login page
		WebElement email = driver.findElement(By.xpath("//*[@id=\'loginfrm\']/div[3]/div[1]/label/span"));
		WebElement password = driver.findElement(By.xpath("//*[@id=\'loginfrm\']/div[3]/div[2]/label/span"));
		WebElement login = driver.findElement(By.xpath("(//*[@type='submit'])[1]"));
		
		//actions class
		Actions action = new Actions(driver);
		action.click(email).sendKeys("user@phptravels.com").build().perform();
		action.click(password).sendKeys("demouser").build().perform();
		action.click(login).build().perform();
		//ckick on php travels home page header
		Thread.sleep(3000);
		driver.findElement(By.xpath("//header[@id='//header-waypoint-sticky']//div//a//img[@alt='My Account']")).click();
		//driver.findElement(By.linkText("Flights")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Flights')]")).click();
		Thread.sleep(3000);
		//oneway ,twoway radio buttons
		WebElement oneway = driver.findElement(By.xpath("(//label[@class='custom-control-label'])[1]"));
		WebElement twoway = driver.findElement(By.xpath("(//label[@class='custom-control-label'])[2]"));
		twoway.click();
		Thread.sleep(1000);
		oneway.click();
		driver.findElement(By.xpath("(//a[@class='select2-choice'])[2]")).click();
		//driver.findElement(By.xpath("(//a[@class='select2-choice'])[2]")).clear();xzdqzA
		driver.findElement(By.xpath("(//a[@class='select2-choice'])[2]")).sendKeys("Bang");
		
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class='select2-results']//li//div[1]")));
	
		
		List<WebElement> listtext = driver.findElements(By.xpath("//ul[@class='select2-results']//li//div[1]"));
		System.out.println(" total no of text :"+listtext.size());
		
		for(WebElement element : listtext) {
			String data = element.getText();
			System.out.println(data);
			
			if(data.contains("Bangalore (BLR)")) {
				element.click();
				break;
			}
			
			
			}

		
		driver.findElement(By.xpath("(//a[@class='select2-choice'])[3]")).click();
		driver.findElement(By.xpath("(//a[@class='select2-choice'])[3]")).sendKeys("Del");
		
		WebDriverWait wait1 = new WebDriverWait(driver,20);
		wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class='select2-results']//li//div[1]")));
		
		List<WebElement> listtext1 = driver.findElements(By.xpath("//ul[@class='select2-results']//li//div[1]"));
		System.out.println(" total no of text :"+listtext1.size());
		
		for(WebElement element : listtext1) {
			String data = element.getText();
			System.out.println(data);
			
			if(data.contains("Delhi (DEL)")) {
				element.click();
				break;
			}
			
			
			}
		
		WebElement dates = driver.findElement(By.xpath("//input[@id='FlightsDateStart']"));
		dates.click();
		dates.sendKeys("2020-07-10");
		
		/*driver.findElement(By.xpath("(//div[@class='datepicker--nav-action'])[1]")).click();
		System.out.println("next is clicked");
		Thread.sleep(1000);
		List<WebElement> caldates = driver.findElements(By.xpath("//div[@class='calDate']"));
		System.out.println("dates list is :"+caldates.size());
		for(WebElement ele:caldates) {
			String mydate = ele.getText();
			System.out.println(mydate);
			if(mydate.equals("10")) {
				ele.click();
				break;
			}
			
		}*/
	
		
		
		Thread.sleep(5000);
		driver.quit();
		
		//driver.findElement(by)
		
		
		
		
		
		
		
		

	}

}
