package com.orangehrm.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.orangehrm.base.BaseClass;
import com.orangehrm.utilities.ExtentManager;

public class TestListener implements ITestListener {

	
	//Triggered when a Test Starts
	@Override
	public void onTestStart(ITestResult result) {
		
		String testName = result.getMethod().getMethodName();
		//Start logging in Extent Report
		ExtentManager.startTest(testName);
		ExtentManager.logStep(" ▶️ Test Started: "+testName);
	}

	//Triggered when a Test Succeeds
	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		ExtentManager.logStepWithScreenShot(BaseClass.getDriver(), "Test Passed Successfully!", "Test End: "+testName+" - ✔️ Test Passed");
		
	}

	//Triggered when Test Fails
	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		String failureMessage =result.getThrowable().getMessage();
		ExtentManager.logStep(failureMessage);
		ExtentManager.logFailure(BaseClass.getDriver(), "Test Failed", "Test End: "+testName+" - ❌ Test Failed");
		
	}

	
	//Triggered when a test Skipped
	@Override
	public void onTestSkipped(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		ExtentManager.logSkip("⏭️ Test Skipped: "+testName);
		
		
	}
	
	//Triggered when a suite starts
	@Override
	public void onStart(ITestContext context) {
		//initialize Extent Report
		ExtentManager.getReporter();
		
	}

	//Triggered when the suite ends
	@Override
	public void onFinish(ITestContext context) {
		//Flush the extent REport
		ExtentManager.endTest();
	}
	
	

}
