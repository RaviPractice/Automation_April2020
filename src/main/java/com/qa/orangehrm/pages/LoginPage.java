package com.qa.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.orangehrm.util.AppConistents;
import com.qa.orangehrm.util.Credentials;
import com.qa.orangehrm.util.ElementUtil;
import com.qa.orangehrm.util.JavaScriptUtil;
import com.qa.oranghrm.base.BasePage;

public class LoginPage extends BasePage{
	WebDriver driver;
	ElementUtil elementutil;
	JavaScriptUtil jsutil;
	//1.Locators - By
	By username = By.id("txtUsername");
	By password = By.id("txtPassword");
	By loginBtn = By.id("btnLogin");
	By forgotpswd = By.linkText("Forgot your password?");
	By loginbtn2 = By.id("openIdLogin");
	By socialIcons = By.xpath("//div[@id='social-icons']");
	By logo = By.xpath("//div[@id='divLogo']");
	By loginerrormessage = By.xpath("//span[@id='spanMessage']");
	
	//page constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementutil = new ElementUtil(driver);
		jsutil = new JavaScriptUtil(driver);
		
	}
	
	//page actions
	public String getPageTitle() {
		elementutil.waitForTitlePresent(AppConistents.LOGIN_PAGE_TITLE);
		return elementutil.doGetPageTitle();
	}
	//java script -page title
	public String getPageTitleByJavaScript() {
		return jsutil.getTitleByJS();
		
		//elementutil.waitForTitlePresent(AppConistents.LOGIN_PAGE_TITLE);
		//return jsutil.doGetPageTitle();
	}
	public boolean checkForgotpswdlink() {
		return elementutil.doIsDisplayed(forgotpswd);
	}
	
	public boolean checkLoginButton() {
		return elementutil.doIsDisplayed(loginbtn2);
	}
	public boolean checkSocialIcons() {
		return elementutil.doIsDisplayed(socialIcons);
	}
	public boolean checkLoginErrormessage() {
		return elementutil.doIsDisplayed(loginerrormessage);
	}
	
	
	public HomePage doLogin(Credentials userCred) {
		elementutil.doSendKeys(username, userCred.getAppUsername());
		elementutil.doSendKeys(password, userCred.getAppPassword());
		elementutil.doClick(loginBtn);
		
		return new HomePage(driver);
		
		
		
	}
	

}
