package com.qa.orangehrm.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.orangehrm.pages.AdminPage;
import com.qa.orangehrm.pages.HomePage;
import com.qa.orangehrm.pages.LoginPage;
import com.qa.orangehrm.util.AppConistents;
import com.qa.orangehrm.util.Credentials;
import com.qa.orangehrm.util.ExcelUtil;
import com.qa.oranghrm.base.BasePage;

public class AdminPageTest {
	WebDriver driver;
	BasePage basepage;
	Properties prop;
	LoginPage loginpage;
	HomePage homepage;
	Credentials userCred;
	AdminPage adminpage;
	
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
		adminpage = homepage.doAdmin();
		Thread.sleep(5000);

	}
	@Test(priority=1)
	public void verifyTitleTest() {
		String title = homepage.getPageTitle();
		System.out.println(" title of the page is :"+title);
		Assert.assertEquals(title, AppConistents.ADMIN_PAGE_TITLE);
	}
	@DataProvider
	public Object[][] getUsersTestData(){
		Object[][] data = ExcelUtil.getTestData(AppConistents.USERS_SHEET_NAME);
		
		return data;
		
	}
	
	@Test(priority=2, dataProvider = "getUsersTestData")
	public void createUsersTest(String Employeename,String username,String pswd,String cnfpswd) throws InterruptedException {
		adminpage.createNewUser(Employeename,username,pswd,cnfpswd);
		
		
	}
		
	
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	

}
