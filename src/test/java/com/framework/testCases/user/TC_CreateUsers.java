package com.framework.testCases.user;

import com.framework.testCases.BaseClass;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class TC_CreateUsers extends BaseClass {

    //Positive scenario - All fields are provided with valid values
    @Test(priority = 0)
    @Description("Test to validate creation of multiple users.")
    @Epic("EP001 - API Testing")
    @Feature("Feature1 : User CRUD API calls")
    @Story("Story : Create User API")
    @Step("Verify create user API")
    @Severity(SeverityLevel.NORMAL)
    public void createMultipleUsers() throws IOException {
        // for reports
        logger = report.createTest("Test to validate creation of multiple users.");
        List<HashMap<String, String>> userDetails1 = xUtils.readExcelData("Users", "TC_CreateUsers_Multiple_001");
        serviceHelper.createRequest("CreateUsers", userDetails1);
        serviceHelper.submitRequest(baseURL, "/v2/user/createWithArray", "POST", apiHelper);
        serviceHelper.verifyResponseData("CreateUsers", "POST", apiHelper, "200", userDetails1);

    }

    //Positive scenario - All fields are provided with valid values
    @Test(priority = 1)
    @Description("Test to validate creation of single user.")
    @Epic("EP001 - API Testing")
    @Feature("Feature1 : User CRUD API calls")
    @Story("Story : Create User API")
    @Step("Verify create user API")
    @Severity(SeverityLevel.NORMAL)
    public void createSingleUser() throws IOException {
        logger = report.createTest("Test to validate creation of single user.");
        List<HashMap<String, String>> userDetails2 = xUtils.readExcelData("Users", "TC_CreateUsers_002");
        serviceHelper.createRequest("CreateUsers", userDetails2);
        serviceHelper.submitRequest(baseURL, "/v2/user/createWithArray", "POST", apiHelper);
        serviceHelper.verifyResponseData("CreateUsers", "POST", apiHelper, "200", userDetails2);

    }


}
