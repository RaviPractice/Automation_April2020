package com.qa.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.orangehrm.util.AppConistents;
import com.qa.orangehrm.util.ElementUtil;
import com.qa.orangehrm.util.JavaScriptUtil;
import com.qa.oranghrm.base.BasePage;

public class HomePage extends BasePage {
	WebDriver driver;
	ElementUtil elementutil;
	JavaScriptUtil jsutil;
	//Locators - By
	
	By dashboard = By.xpath("//h1[contains(text(),'Dashboard')]");
	By user = By.xpath("//a[@class='panelTrigger']");
	By adminTab = By.xpath("//b[text()='Admin']");
	
	//constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementutil = new ElementUtil(driver);
		jsutil = new JavaScriptUtil(driver);
		
	}
	
	//page actions
	
	public String getPageTitle() {
		elementutil.waitForTitlePresent(AppConistents.HOME_PAGE_TITLE);
		return elementutil.doGetPageTitle();
	}
	// jsutil - get page title
	public String getPageTitleByJavaScript() {
		return jsutil.getTitleByJS();
		
	}
	
	
	public String getPageHeader() {
		return elementutil.doGetText(dashboard);
	}
	
	public String getUserName() {
		return elementutil.doGetText(user);
		
		
	}
	public AdminPage doAdmin() {
		elementutil.waitForElementPresent(adminTab);
		elementutil.doClick(adminTab);
		return new AdminPage(driver);
	}
	

}
