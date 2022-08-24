package com.framework.utilities;


import io.qameta.allure.Attachment;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ReportListener implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    // Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("Test suite execution started : " + iTestContext.getName());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("Test suite execution ended : " + iTestContext.getName());
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("Test started " + getTestMethodName(iTestResult));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("Test " + getTestMethodName(iTestResult) + " passed. ");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Test " + getTestMethodName(iTestResult) + " failed!!");
        saveTextLog(getTestMethodName(iTestResult) + " failed. ");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("Test " + getTestMethodName(iTestResult) + " skipped!!");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }

}

