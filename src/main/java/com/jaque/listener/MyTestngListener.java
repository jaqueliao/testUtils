package com.jaque.listener;

import com.jaque.testUtils.DriverUtils;
import com.jaque.testUtils.TestUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.*;

public class MyTestngListener implements ITestListener {

	/**
	 * 用例失败截图
	 * @param result 测试结果
	 */
	@Override
	public void onTestFailure(ITestResult result) {
		String fileName = "failTest" + TestUtils.getTimeStamp();
		try {
			if (TestUtils.isTestServer()) {
				TestUtils.takeScreenSnap(TestUtils.getJenkinsHome()+"\\webapps\\testOutput\\img\\" + fileName + ".png");
				DriverUtils.log("<img class='pimg' src='/testOutput/img/" + fileName + ".png' width='100'/>");
			} else {
				TestUtils.takeScreenSnap("test-output/img/" + fileName + ".png");
				DriverUtils.log("<img class='pimg' src='../img/" + fileName + ".png' width='100'/>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 测试结束去除重复用例
	 * @param testContext
	 */
	@Override
	public void onFinish(ITestContext testContext) {
//由于testng升级，重试前的用例都归入了skip，此方法并不能删除重试多余的用例，而且并不是很需要这个功能，故注释掉
/*
		// List of test results which we will delete later
		ArrayList<ITestResult> testsToBeRemoved = new ArrayList<ITestResult>();
		// collect all id's from passed test
		Set<Integer> passedTestIds = new HashSet<Integer>();
		for (ITestResult passedTest : testContext.getPassedTests().getAllResults()) {
			DriverUtils.log("PassedTests = " + passedTest.getName());
			passedTestIds.add(getId(passedTest));
		}

		Set<Integer> failedTestIds = new HashSet<Integer>();
		for (ITestResult failedTest : testContext.getFailedTests().getAllResults()) {
			DriverUtils.log("failedTest = " + failedTest.getName());
			// id = class + method + dataprovider
			int failedTestId = getId(failedTest);

			// if we saw this test as a failed test before we mark as to be
			// deleted
			// or delete this failed test if there is at least one passed
			// version
			if (failedTestIds.contains(failedTestId) || passedTestIds.contains(failedTestId)) {
				testsToBeRemoved.add(failedTest);
			} else {
				failedTestIds.add(failedTestId);
			}
		}

		// finally delete all tests that are marked
		for (Iterator<ITestResult> iterator = testContext.getFailedTests().getAllResults().iterator(); iterator
				.hasNext();) {
			ITestResult testResult = iterator.next();
			if (testsToBeRemoved.contains(testResult)) {
				DriverUtils.log("Remove repeat Fail Test: " + testResult.getName());
				iterator.remove();
			}
		}
*/
	}

	private int getId(ITestResult result) {
		int id = result.getTestClass().getName().hashCode();
		id = id + result.getMethod().getMethodName().hashCode();
		id = id + (result.getParameters() != null ? Arrays.hashCode(result.getParameters()) : 0);
		return id;
	}

	public void onTestStart(ITestResult result) {

	}

	public void onTestSuccess(ITestResult result) {

	}

	public void onTestSkipped(ITestResult result) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onStart(ITestContext context) {

	}
}
