package com.framework.testCases;

import com.framework.utilities.ApiHelper;
import com.framework.utilities.ReadConfig;
import com.framework.utilities.ServiceHelper;
import com.framework.utilities.XLUtils;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class BaseClass {

    ReadConfig readconfig = new ReadConfig();
    public String baseURL = readconfig.getApplicationURL();
    public ApiHelper apiHelper = new ApiHelper();
    public ServiceHelper serviceHelper = new ServiceHelper();
    public XLUtils xUtils = new XLUtils();

    @BeforeSuite
    public void start() {
        System.out.println("Starting Suite Execution .....");

    }

    @AfterSuite
    public void endTest() {
        System.out.println("Ending Suite Execution.....");
    }


}

