package com.qa.orangehrm.tests;

import java.util.Properties;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.orangehrm.pages.HomePage;
import com.qa.orangehrm.pages.HomePage;
import com.qa.orangehrm.pages.LoginPage;
import com.qa.orangehrm.util.AppConistents;
import com.qa.orangehrm.util.Credentials;
import com.qa.oranghrm.base.BasePage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Epic("Epic-502: Create Home page feature")
@Feature("US -102: create test for home page test on Orange HRM")
public class HomePageTest {
	WebDriver driver;
	BasePage basepage;
	Properties prop;
	LoginPage loginpage;
	HomePage homepage;
	Credentials userCred;

	@BeforeTest
	public void setUp() throws InterruptedException {
		basepage = new BasePage();
		prop = basepage.initlize_propperties();
		String browserName = prop.getProperty("browser");
		driver = basepage.initlize_driver(browserName);
		driver.get(prop.getProperty("url"));
		loginpage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		homepage = loginpage.doLogin(userCred);
		Thread.sleep(5000);

	}
	
	@Test(priority=1)
	@Description("verify title test...")
	@Severity(SeverityLevel.MINOR)
	public void verifyTitleTest() throws InterruptedException {
		//Thread.sleep(3000);
		String title = homepage.getPageTitle();
		System.out.println(" title of the page is :"+title);
		Assert.assertEquals(title, AppConistents.HOME_PAGE_TITLE);
	}
	@Test(priority=2)
	@Description("verify title test...")
	@Severity(SeverityLevel.NORMAL)
	public void verifyHomepagebanner() {
		String header = homepage.getPageHeader();
		System.out.println("header text is:"+header);
		Assert.assertEquals(header, AppConistents.HOME_PAGE_HEADER);
	}
	@Test(priority=3)
	@Description("verify title test...")
	@Severity(SeverityLevel.BLOCKER)
	public void verifyUserName() {
		String username = homepage.getUserName();
		System.out.println("username is :"+username);
		Assert.assertEquals(username, AppConistents.ADMIN_USER);
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
