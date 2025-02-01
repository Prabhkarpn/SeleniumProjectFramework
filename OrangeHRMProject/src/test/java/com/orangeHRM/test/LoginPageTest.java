package com.orangeHRM.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utilities.ExtentManager;

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
		//ExtentManager.startTest("Valid Login Test"); -- This has been implemented in TestListener
		System.out.println("Running testMethod1 on Thread: "+Thread.currentThread().getId());
		ExtentManager.logStep("Navigating to Login Page enetring username and Password");
		loginPage.login("Admin", "admin123");
		ExtentManager.logStep("Verrifying admin tab is visible or not");
		Assert.assertTrue(homePage.isAdminTabVisible(),"Admin tab should be visible After successful Login");
		ExtentManager.logStep("Validation successfully!");
		homePage.logout();
		ExtentManager.logStep("Looged out successfully!");
		staticWait(5);
	}
	
	@Test
	public void inValidLoginTest() {
		//ExtentManager.startTest("Invalid Login Test"); -- This has been implemented in TestListener
		System.out.println("Running testMethod2 on Thread: "+Thread.currentThread().getId());
		ExtentManager.logStep("Navigating to Login Page enetring username and Password");
		loginPage.login("Admin", "admin123");
		staticWait(5);
		String expectedErrorMessage = "Invalid credentials";
		Assert.assertTrue(loginPage.verifyErrorMessage(expectedErrorMessage),"Test Failed : Invalid Error Message");
		ExtentManager.logStep("Validation Successful for Invalid credentials");
		ExtentManager.logStep("Looged out successfully!");
		
		
	}

}
