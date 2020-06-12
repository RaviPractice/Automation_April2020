package com.qa.orangehrm.util;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.oranghrm.base.BasePage;

public class ElementUtil extends BasePage {
	WebDriver driver;
	WebDriverWait wait;
	JavaScriptUtil jsutil;
	Properties prop;
	
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver,AppConistents.DEFAULT_TIMEOUT);	
		jsutil = new JavaScriptUtil(driver);
		
		
	}
	
	public boolean waitForElementPresent(By locator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return true;
	}
	
	public boolean waitForElementVisabulity(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return true;
	}
	public boolean waitForElementToBeClickable(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		return true;
	}
	public boolean waitForTitlePresent(String title) {
		wait.until(ExpectedConditions.titleIs(title));
		return true;
		
	}
	
	
	public String doGetPageTitle() {
		try {
		return driver.getTitle();
		}catch(Exception e) {
			System.out.println("some exception occur while getting the page title..");
		}
		return null;
	}
	/**
	 * this method is used to create the webelement on the basis of By locator 
	 * @param locator
	 * @return
	 */
			
	public WebElement getElement(By locator) {
		WebElement element=null;
		try {
			//if(waitForElementPresent(locator));
		 element = driver.findElement(locator);
		 if(highlightElement) {
			 jsutil.flash(element);
		 }
		 
		}catch(Exception e) {
			System.out.println("some exception occur while creating webelement..");
		}
		return element;
		
	}
	
	public void doClick(By locator) {
		try {
		getElement(locator).click();
		}catch(Exception e) {
			System.out.println("some exception occur while clicking on  webelement..");
		}
		
	}
	
	public void doSendKeys(By locator,String value) {
		try {
		WebElement element = getElement(locator);
		element.clear();
		element.sendKeys(value);
		}catch(Exception e) {
			System.out.println("some exception occur while entering on the field..");
		}
		
	}
	public boolean doIsDisplayed(By locator) {
		try {
		return getElement(locator).isDisplayed();
		}catch(Exception e) {
			System.out.println(" some exception occur while displaying..");
		}
		return false;
		
		
	}
	public String doGetText(By locator) {
		try {
		return getElement(locator).getText();
		}catch(Exception e) {
			System.out.println("some exception occur while getting the text...");
		}
		return null;
		
	}

}
