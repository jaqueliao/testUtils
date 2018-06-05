package com.jaque.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.jaque.testUtils.PropertyPraser;
import com.jaque.testUtils.TestUtils;

public class MyTestngListener implements ITestListener {

	public void onTestFailure(ITestResult result) {

		String fileName = "failTest" + TestUtils.getTimeStamp();
		try {
			if (TestUtils.isTestServer()) {
				TestUtils.takeScreenSnap(TestUtils.getJenkinsHome()+"\\webapps\\testOutput\\img\\" + fileName + ".png");
				Reporter.log("<img class='pimg' src='/testOutput/img/" + fileName + ".png' width='100'/>");
			} else {
				TestUtils.takeScreenSnap("test-output/img/" + fileName + ".png");
				Reporter.log("<img class='pimg' src='../img/" + fileName + ".png' width='100'/>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}
}
