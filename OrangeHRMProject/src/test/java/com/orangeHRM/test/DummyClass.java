package com.orangeHRM.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;

public class DummyClass extends BaseClass{
	
	@Test
	public void dummyTest() throws InterruptedException {
		
		System.out.println("The Browser lauched");
		//Thread.sleep(20000);
		String title = getDriver().getTitle();
		Assert.assertEquals(title, "OrangeHRM","TestFailed : Title is not matched");
		
	}

}
