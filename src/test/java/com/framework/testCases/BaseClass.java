package com.framework.testCases;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.framework.utilities.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class BaseClass {

    ReadConfig readconfig = new ReadConfig();
    public String baseURL = readconfig.getApplicationURL();
    public static ExtentReports report;
    public static ExtentTest logger;
    public ApiHelper apiHelper = new ApiHelper();
    public ServiceHelper serviceHelper = new ServiceHelper();
    public XLUtils xUtils = new XLUtils();

    @BeforeSuite
    public void start() {
        //for extent reports
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
        String repName = "Test-Report-" + timeStamp + ".html";
        Helper.checkFileDirectoryPath(System.getProperty("user.dir") + "/test-output");
        ExtentHtmlReporter extent = new ExtentHtmlReporter(new File(System.getProperty("user.dir") + "/test-output/" + repName));
        extent.config().setDocumentTitle("MindTickle Automation Report");
        extent.config().setReportName("MindTickle API Validations Report");
        extent.config().setTheme(Theme.DARK);
        report = new ExtentReports();
        report.attachReporter(extent);
        report.setSystemInfo("Hostname", "LocalHost");
        report.setSystemInfo("OS", "Windows-10");
        report.setSystemInfo("QA", "Ariz");

    }

    @AfterSuite
    public void endTest() {
        System.out.println("Ending Suite Execution");
        report.flush();
    }


}

