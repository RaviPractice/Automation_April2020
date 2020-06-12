package com.qa.orangehrm.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrangeHRMDemo {

	public static void main(String[] args) {
		WebDriver driver=null;
		ElementUtil util = new ElementUtil();
		util.startBrowser(driver, "chrome", "https://opensource-demo.orangehrmlive.com/index.php/dashboard");
		util.doSendKeys(By.id("txtUsername"), "Admin", "LOG:INFO - enter username");
		util.doSendKeys(By.id("txtPassword"), "admin123", "LOG:INFO - enter password");
		util.doClick(By.id("btnLogin"), "LOG:INFO - enter login button");
		util.closeBrowser("LOG:INFO - browser terminated");	
		

	}

}
