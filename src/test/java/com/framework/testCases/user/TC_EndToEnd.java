package com.framework.testCases.user;

import com.framework.testCases.BaseClass;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class TC_EndToEnd extends BaseClass {

    @BeforeMethod
    public void setUp() {
        System.out.println("End to end user test has started ....");
    }

    //E2E scenario - All fields are provided with valid values
    @Test(priority = 4)
    @Description("Test to validate end to end scenario for users operations.")
    @Epic("EP001 - API Testing")
    @Feature("Feature1 : User CRUD API calls")
    @Story("Story : User CRUD API end to end test")
    @Step("Verify API calls for user add/update/get APIs")
    @Severity(SeverityLevel.BLOCKER)
    public void EndToEndUserTest() throws IOException {
        List<HashMap<String, String>> userDetailsListCreate = xUtils.readExcelData("Users", "TC_E2ECreate_001");
        serviceHelper.createRequest("CreateUsers", userDetailsListCreate);
        serviceHelper.submitRequest(baseURL, "/v2/user/createWithArray", "POST", apiHelper);
        serviceHelper.verifyResponseData("CreateUsers", "POST", apiHelper, userDetailsListCreate.get(0).get("statusCode"), userDetailsListCreate);
        List<HashMap<String, String>> userDetailsListUpdate = xUtils.readExcelData("Users", "TC_E2EUpdate_001");
        serviceHelper.createRequest("UpdateUser", userDetailsListUpdate);
        serviceHelper.submitRequest(baseURL, "/v2/user/", "PUT", apiHelper);
        serviceHelper.verifyResponseData("UpdateUser", "PUT", apiHelper, userDetailsListUpdate.get(0).get("statusCode"), userDetailsListUpdate);
        List<HashMap<String, String>> userDetailsListGet = xUtils.readExcelData("Users", "TC_E2EGet_001");
        serviceHelper.createRequest("GetUser", userDetailsListGet);
        serviceHelper.submitRequest(baseURL, "/v2/user/", "GET", apiHelper);
        serviceHelper.verifyResponseData("GetUser", "GET", apiHelper,userDetailsListGet.get(0).get("statusCode"), userDetailsListGet);

    }

    @AfterMethod
    public void tearDown() {
        System.out.println("End to end user test has ended ....");
    }

}

