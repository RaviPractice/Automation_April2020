package com.qa.orangehrm.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
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

@Epic("EPIC - 501 : create Login page feature")
@Feature("US-101 : create test for Loginpage on Orange HRM login")
public class LoginPageTest {
	WebDriver driver;
	BasePage basepage;
	Properties prop;
	LoginPage loginpage;
	Credentials userCred;

	@BeforeTest
	@Parameters(value = {"browser"})
	public void setUp(String browser) {
		String browserName = null;
		
		basepage = new BasePage();
		prop = basepage.initlize_propperties();
		
		if(browser.equals(null)) {
			 browserName = prop.getProperty("browser");
			
		}else {
			browserName = browser;
		}
		driver = basepage.initlize_driver(browserName);
		driver.get(prop.getProperty("url"));
		loginpage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		
	}

	@Test(priority=1)
	@Description("verify Login page title test...")
	@Severity(SeverityLevel.NORMAL)
	public void verifyLoginPageTitleTest() throws InterruptedException {
		Thread.sleep(5000);
		String title = loginpage.getPageTitle();
		System.out.println("loginpage title is :" + title);
		Assert.assertEquals(title, AppConistents.LOGIN_PAGE_TITLE);

	}

	@Test(priority=2,enabled=false)
	@Description("verify forgot password link..")
	@Severity(SeverityLevel.NORMAL)
	public void verifyForgotpaswdLink() {
		Assert.assertTrue(loginpage.checkForgotpswdlink());
	}
	@Test(priority=3,enabled=false)
	@Description("verify login button..")
	@Severity(SeverityLevel.NORMAL)
	public void verifyLoginButton() {
		Assert.assertTrue(loginpage.checkLoginButton());
	}
	@Test(priority=4,enabled=false)
	@Description("verify social icons..")
	@Severity(SeverityLevel.MINOR)
	public void verifySocialIcons() {
		Assert.assertTrue(loginpage.checkSocialIcons());
	}

	@Test(priority=5,enabled=false)
	@Description("verify forgot password link..")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest() {
		HomePage homepage =loginpage.doLogin(userCred);
		String title = homepage.getPageTitle();
		Assert.assertEquals(title, AppConistents.HOME_PAGE_TITLE);
	}
	@DataProvider
	public Object[][] getLoginInvaliddata() {
		Object data[][] = {{"test1@gmail.com","test123"},
							{"test2@gmail.com","test143"},
							{"test3@gmail.com",""},
							{"","test173"},
							{"",""}
							};
		return data;
		
	}
	@Test(priority=6,dataProvider="getLoginInvaliddata",enabled=false)
	public void login_invalidTestcases(String username,String pwd) {
		userCred.setAppUsername(username);
		userCred.setAppPassword(pwd);
		loginpage.doLogin(userCred);
		Assert.assertTrue(loginpage.checkLoginErrormessage());
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
