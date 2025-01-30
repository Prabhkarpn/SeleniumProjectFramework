package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangehrm.actiondriver.ActionDriver;
import com.orangehrm.base.BaseClass;

public class HomePage {

	public ActionDriver actionDriver;

	// Define locators using BY class
	private By adminTab = By.xpath("//span[text()='Admin']");
	private By userIDButton = By.xpath("//p[@class='oxd-userdropdown-name']");
	private By logoutButton = By.xpath("//a[text()='Logout']");
	private By orangeHRMLogo = By.xpath("//div[@class='oxd-brand-banner']/img");

	// initialize ActionDriver object by passing WebDriver Instance
	/*
	public HomePage(WebDriver driver) {
		this.actionDriver = new ActionDriver(driver);
	}  */
	
	public HomePage(WebDriver driver) {
		this.actionDriver = BaseClass.getActionDriver();
	}
	
	//Method to verify if Admin tab is visible
	public boolean isAdminTabVisible() {
		return actionDriver.isDisplayed(adminTab);
	}
	
	public boolean verifyOrangeHRMLogo() {
		return actionDriver.isDisplayed(orangeHRMLogo);
	}
	
	//Method to perform Logout operation
	public void logout() {
		actionDriver.click(userIDButton);
		actionDriver.click(logoutButton);
		
	}

}
