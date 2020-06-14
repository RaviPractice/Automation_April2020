package com.qa.oranghrm.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	//public WebDriver driver;
	public Properties prop;
	public static boolean highlightElement;
	public OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	
	public static synchronized WebDriver getDriver() {
		return tldriver.get();
	}
	
	
	public WebDriver initlize_driver(String browserName) {
		highlightElement = prop.getProperty("highlight").equals("yes") ? true : false;
		
		System.out.println(" browsername is :"+browserName);
		optionsManager = new OptionsManager(prop);
		
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			
			tldriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			
		}
		else if (browserName.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			tldriver.set(new InternetExplorerDriver());
			//driver = new InternetExplorerDriver();
	}
		else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tldriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			
		}
		else {
			System.out.println("browsername :"+browserName +"is not found please pass the correct browser");
		}
		getDriver().manage().deleteAllCookies();
		//getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		getDriver().manage().window().maximize();
		//driver.get(url);
		return getDriver();
	}

	public Properties initlize_propperties() {
		prop = new Properties();
		String path = null;
		String env = null;
		try {
			env = System.getProperty("env");
			if(env.equals("qa")) {
				path = ".\\src\\main\\java\\com\\qa\\orangehrm\\config\\config.qa.properties";
			} else if (env.equals("stg")) {
				path = ".\\src\\main\\java\\com\\qa\\orangehrm\\config\\config.stg.properties";
			}
		}catch(Exception e) {
			path = ".\\src\\main\\java\\com\\qa\\orangehrm\\config\\config.properties";
		}
		
		try {
			FileInputStream fip = new FileInputStream(path);
			prop.load(fip);
		} catch (FileNotFoundException e) {
			System.out.println("some issue with config properties..please correct your config");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
		
	}
	
	/**
	 * create method for taking screenshot of webpage
	 */
	public String getScreenshot() {
		File source =((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path =System.getProperty("user.dir") + "\\Screenshots\\" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(source, destination);
		} catch (IOException e) {
			System.out.println("  some exception occur in taking screenshot...");
		}
		return path;
		
	}
}
