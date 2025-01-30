package com.orangeHRM.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;

public class LoginPageTest extends BaseClass{
	
	private LoginPage loginPage;
	private HomePage homePage;
	
	@BeforeMethod
	public void setupPages() {
		
		loginPage=new LoginPage(getDriver());  //here getDriver() method we are calling it inside BaseClass
		homePage = new HomePage(getDriver());
		
	}
	
	@Test
	public void verifyValidLoginTest() {
		loginPage.login("Admin", "admin123");
		Assert.assertTrue(homePage.isAdminTabVisible(),"Admin tab should be visible After successful Login");
		homePage.logout();
		staticWait(5);
	}
	
	@Test
	public void inValidLoginTest() {
		loginPage.login("Admin", "admin1234");
		staticWait(5);
		String expectedErrorMessage = "Invalid credentials";
		Assert.assertTrue(loginPage.verifyErrorMessage(expectedErrorMessage),"Test Failed : Invalid Error Message");
		
		
	}

}
