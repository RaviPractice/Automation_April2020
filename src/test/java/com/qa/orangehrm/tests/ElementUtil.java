package com.qa.orangehrm.tests;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;

public class ElementUtil {
	public WebDriver driver;
	
	public WebDriver startBrowser(WebDriver driver,String browserName,String appurl) {
		System.out.println("****LOG:INFO browser launched successfully****");
		
		if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "D:\\MukeshAutomation\\drivers\\chromedriver.exe");
			 driver = new ChromeDriver();
			}
		else if (browserName.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", "D:\\MukeshAutomation\\drivers\\IEDriverServer-32.exe");
			 driver = new InternetExplorerDriver();
			}
		else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "D:\\MukeshAutomation\\drivers\\geckodriver.exe");
			 driver = new FirefoxDriver();
			}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
		driver.get(appurl);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("****LOG:INFO browser UP and Running successfully****");
		return driver;			
		
	}
	/**
	 * this method will take a screenshot for webpage
	 * @param driver
	 * @param fileName
	 */
	
	public  void takePageScreenshot(WebDriver driver,String fileName) {
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			//FileHandler.copy(from, to);
			FileUtils.copyFile(srcFile, new File("./target/screenshots/"+fileName+".png"));
		} catch (IOException e) {
			System.out.println("error while copying file from source to destination");
		}
		
	}
	/**
	 * this method to use the  title of the page
	 * @return
	 */
	
	public String doGetPageTitle(WebDriver driver) {
		try {
		return driver.getTitle();
		}catch(Exception exp) {
			System.out.println(" some exception occur in while getting the page title...");
		}
		return null;
		
	}
	/**
	 * this method is used to create webelement in a page
	 * @param locator
	 * @return
	 */
	
	public WebElement getElement(By locator) {
		WebElement element=null;
		
		try {
		 element = driver.findElement(locator);
		}catch(Exception exp) {
			System.out.println("some exception occur in while creating webelement...");
		}
		return element;
		
	}
	/**
	 * this method is used to create click on webelement
	 * @param locator
	 */
	public void doClick(By locator,String stepInfo) {
		try {
		getElement(locator).click();
		}catch(Exception exp) {
			System.out.println("LOG:INFO " +stepInfo);
			System.out.println("some exception occur while click on webelement...");
		}
	}
	/**
	 * this method is used to sending values to the webelement
	 * @param locator
	 * @param text
	 */
	
	public void doSendKeys(By locator,String value,String stepInfo) {
		try {
			WebElement element =getElement(locator);
			element.clear();
			element.sendKeys(value);
		}catch(Exception exp) {
			System.out.println("LOG:INFO " +stepInfo);
			System.out.println("some exception occur in sendkeys method...");
		}
	}
	/**
	 * this method get the text from webelement 
	 * @param locator
	 * @return
	 */
	
	public String doGetText(By locator) {
		try {
		Thread.sleep(3000);
		return getElement(locator).getText();
		}catch(Exception exp) {
			System.out.println("some exception occur in getText method...");
		}
		return null;
		
	}
	/**
	 * this method is used for Actions class click
	 * @param locator
	 */
	public void doActionClick(By locator) {
		try {
		WebElement element = getElement(locator);
		Actions actions = new Actions(driver);
		actions.click(element).build().perform();
		}catch(Exception exp) {
			System.out.println("some exception occur in action class click...");
		}	
	}
	
	/**
	 * this method is used for action class sendkeys..
	 * @param locator
	 * @param value
	 */
	public void doActionSendKeys(By locator,String value) {
		try {
		WebElement element = getElement(locator);
		Actions actions = new Actions(driver);
		actions.sendKeys(value).build().perform();
		}catch(Exception exp) {
			System.out.println(" some exception occur in action class sendkeys...");
			}
		}

	public void closeBrowser(String stepInfo) {
		//driver.quit();
		System.out.println("LOG:INFO " +stepInfo);
	}
}
