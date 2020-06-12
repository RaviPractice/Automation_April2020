package com.qa.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.orangehrm.util.AppConistents;
import com.qa.orangehrm.util.ElementUtil;
import com.qa.orangehrm.util.JavaScriptUtil;
import com.qa.oranghrm.base.BasePage;

import io.qameta.allure.Step;

public class AdminPage extends BasePage {
	WebDriver driver;
	ElementUtil elementutil;
	JavaScriptUtil jsutil;
	
	//Locators - By
	 By addButton = By.xpath("//input[@value='Add']");
	 By deleteButton = By.xpath("//input[@id='btnDelete']");
	 //Add user - By locators
	 By employeeName = By.xpath("//label[text()='Employee Name']//following::input[1]");
	 By username = By.xpath("//label[text()='Username']//following::input[1]");
	 By passWord = By.xpath("//label[text()='Password']//following::input[1]");
	 By cnfPassword = By.xpath("//label[text()='Confirm Password']//following::input[1]");
	 By saveButton = By.xpath("//input[@value='Save']");
	 
	 
	 
	 //constructor
	 public AdminPage(WebDriver driver) {
		 this.driver = driver;
		 elementutil = new ElementUtil(driver);
			jsutil = new JavaScriptUtil(driver);
	 }
	 //page actions
	 public String getPageTitle() {
		 elementutil.waitForTitlePresent(AppConistents.ADMIN_PAGE_TITLE);
			return elementutil.doGetPageTitle();
	 }
	 @Step("create new users with {0},{1},{2},{3}")
	 public void createNewUser(String empName,String userName,String pwd,String cnfpwd) throws InterruptedException {
		 Thread.sleep(3000);
		 elementutil.waitForElementPresent(addButton);
		 elementutil.doClick(addButton);
		 
		 elementutil.waitForElementPresent(employeeName);
		 elementutil.doSendKeys(employeeName, empName);
		 
		 elementutil.doSendKeys(username, userName);
		 elementutil.doSendKeys(passWord, pwd);
		 elementutil.doSendKeys(cnfPassword, cnfpwd);
		 
		 elementutil.doClick(saveButton);	
		 Thread.sleep(3000);
		 
	 }
	 

}
