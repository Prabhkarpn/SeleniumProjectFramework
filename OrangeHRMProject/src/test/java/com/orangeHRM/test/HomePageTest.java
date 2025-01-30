package com.orangeHRM.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;

import junit.framework.Assert;

public class HomePageTest extends BaseClass{
	
	private LoginPage loginPage;
	private HomePage homePage;
	
	@BeforeMethod
	public void setupPages() {
		
		loginPage=new LoginPage(getDriver());  //here getDriver() method we are calling it inside BaseClass
		homePage = new HomePage(getDriver());
		
	}
	
	@Test
	public void verifyOrangeHRMLogo() {
		loginPage.login("Admin", "admin123");
		staticWait(10);
		Assert.assertTrue("Logo is not Visible",homePage.verifyOrangeHRMLogo());
		
		
	}

}
