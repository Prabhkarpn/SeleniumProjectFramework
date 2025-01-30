package com.orangehrm.actiondriver;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orangehrm.base.BaseClass;

/**
 * @author Prabhakar added on 24th Jan 2025
 *
 */
public class ActionDriver {
	
	private WebDriver driver;
	private WebDriverWait wait;
	public static final Logger logger = BaseClass.logger;
	
	//constructor
	public ActionDriver(WebDriver driver) {
		
		this.driver = driver;
		int explicitWait = Integer.parseInt(BaseClass.getProp().getProperty("explicitWait"));
		wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));
		logger.info("WebDriver instance is associated to driver variable in ActionDriver class");
		
	}
	
	//Methos to click an Element
	public void click(By by) {
		String elementDescription = getElementDescription(by);
		
		try {
			waitForElementToBeClickable(by);
			driver.findElement(by).click();
			logger.info(" Clicked an element-->"+elementDescription);
		} catch (Exception e) {
			logger.error("Unable to click Element : "+e.getMessage());
		}
		
	}
	
	//Method to enter text into an input field
	public void enterText(By by, String value) {
		try {
			waitForElementToBeVisible(by);
			//driver.findElement(by).clear();
			//driver.findElement(by).sendKeys(value);
			WebElement element = driver.findElement(by);
			element.clear();
			element.sendKeys(value);
			logger.info("entered text on "+getElementDescription(by)+"--> "+value);
		} catch (Exception e) {
			logger.error("Unable to enter value in Input box : "+e.getMessage());
		}
		
	}
	
	//Method to get text from an Input Field
	public String getText(By by) {
		try {
			waitForElementToBeVisible(by);
			return driver.findElement(by).getText();
		} catch (Exception e) {
			logger.error("Unable to get the Text : "+e.getMessage());
			return "";
		}
		
	}
	
	//Methos to Compare two text --- changed return type
	public boolean compareText(By by, String expectedText) {
		try {
			waitForElementToBeVisible(by);
			String actualText = driver.findElement(by).getText();
			if(expectedText.equals(actualText))
			{
				logger.info("Text are Matching : "+actualText+" equals "+expectedText);
				return true;
			}
			else {
				logger.error("Text are not Matching : "+actualText+" not equals "+expectedText);
				return false;
			}
		} catch (Exception e) {
			logger.error("Unable to compare Texts : "+e.getMessage());
			
		}
		return false;
	}
	
	//Method to check if an element is displayed
	/*public boolean isDisplayed(By by) {
		try {
			waitForElementToBeClickable(by);
			boolean isDisplayed = driver.findElement(by).isDisplayed();
			if(isDisplayed) {
				System.out.println("Element is visible");
				return isDisplayed;
			}else {
				return isDisplayed;
			}
		} catch (Exception e) {
			System.out.println("Element is not displayed : "+e.getMessage());
			return false;
		}
		
	} */
	
	public boolean isDisplayed(By by)
	{
		try {
			waitForElementToBeVisible(by);
			logger.info("Element is displayed: "+getElementDescription(by));
			return driver.findElement(by).isDisplayed();
			
		} catch (Exception e) {
			logger.error("Element is not Displayed : "+e.getMessage());
			return false;
		}
	}
	
	//Scroll to an Element
	public void scrollToElement(By by) {
		try {
			JavascriptExecutor js =(JavascriptExecutor)driver;
			WebElement element = driver.findElement(by);
			js.executeScript("arguments[0].scrollIntoView(true)", element);
		} catch (Exception e) {
			logger.error("Unable to Locate Element : "+e.getMessage());
		}
	}
	
	
	//wait for element to be clickable
	private void waitForElementToBeClickable(By by) {
		
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			logger.error("Element is not clickable : "+e.getMessage());
		}
		
	}
	
	//Wait for Page Load
	public void waitForPageLoad(int timeOutInSec) {
		try {
			wait.withTimeout(Duration.ofSeconds(timeOutInSec)).until(WebDriver -> ((JavascriptExecutor)WebDriver)
			.executeScript("return document.readyState").equals("complete"));
			logger.info("Page Loaded Successfully");
		} catch (Exception e) {
			logger.error("Page did not Load within "+timeOutInSec+" seconds. Exception "+e.getMessage());
		}
	}
	
	//Wait for element to be Visible
	private void waitForElementToBeVisible(By by) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			logger.error("Element is not Visible : "+e.getMessage());
		}
	}

	//method to getthe description of an element using BY Locator
	public String getElementDescription(By locator) {
		//check for null driver or locator to avoid nullPointer expression
		if(driver == null) {
			return "driver is null";
		}
		if(locator == null) {
			return "Locator is null";
		}
		try {
			WebElement element = driver.findElement(locator);
			
			//Get element Description
			String name = element.getDomAttribute("name");
			String id = element.getDomAttribute("id");
			String text = element.getText();
			String className = element.getDomAttribute("class");
			String placeHolder = element.getDomAttribute("placeholder");
			
			//return the description based on the element attributes
			if(isNotEmpty(name)) {
				return "element with name : "+name;
			}
			else if(isNotEmpty(id)) {
				return "element with id : "+id;
			}
			else if(isNotEmpty(text)) {
				return "element with text : "+truncate(text,50);
			}
			else if(isNotEmpty(className)) {
				return "Element with placeholder : "+className;
			}
			else if(isNotEmpty(placeHolder)) {
				return "Element with placeholder : "+placeHolder;
			}
		} catch (Exception e) {
			logger.error("Unable to describe the element : "+e.getMessage());
		}
		return "Unable to describe the element";
	}
	
	//Utility method to check a String is not NULL or Empty
	private boolean isNotEmpty(String value) {
		return value!=null && !value.isEmpty();
	}
	
	//utility method to truncate long String
	private String truncate(String value, int maxLength) {
		if(value==null || value.length()<=maxLength) {
			return value;
		}
		return value.substring(0, maxLength)+"...";
	}
}


