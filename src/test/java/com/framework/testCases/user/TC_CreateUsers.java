package com.framework.testCases.user;

import com.framework.testCases.BaseClass;
import com.framework.utilities.ReportListener;
import io.qameta.allure.*;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Listeners({ReportListener.class})
public class TC_CreateUsers extends BaseClass {

    @BeforeMethod
    public void setUp()
    {
        System.out.println("Create User test has started ....");
    }

//    //Positive scenario - All fields are provided with valid values
    @Test
    @Description("Test to validate creation of multiple users.")
    @Epic("EP001 - API Testing")
    @Feature("Feature1 : User CRUD API calls")
    @Story("Story : Create User API")
    @Step("Verify create user API")
    @Severity(SeverityLevel.NORMAL)
    public void createMultipleUsers() throws IOException {
        List<HashMap<String, String>> userDetails1 = xUtils.readExcelData("Users", "TC_CreateUsers_Multiple_001");
        serviceHelper.createRequest("CreateUsers", userDetails1);
        serviceHelper.submitRequest(baseURL, "/v2/user/createWithArray", "POST", apiHelper);
        serviceHelper.verifyResponseData("CreateUsers", "POST", apiHelper, userDetails1.get(0).get("statusCode"), userDetails1);

    }

    //Positive scenario - All fields are provided with valid values
    @Test(priority = 1,dataProvider = "CreateUser")
    @Description("Test to validate creation of single user.")
    @Epic("EP001 - API Testing")
    @Feature("Feature1 : User CRUD API calls")
    @Story("Story : Create User API")
    @Step("Verify create user API")
    @Severity(SeverityLevel.NORMAL)
    public void createSingleUser(HashMap<String, String> userDetails) throws IOException {
        List<HashMap<String, String>> userDetails2 = new ArrayList<>();
        userDetails2.add(userDetails);
        serviceHelper.createRequest("CreateUsers", userDetails2);
        serviceHelper.submitRequest(baseURL, "/v2/user/createWithArray", "POST", apiHelper);
        serviceHelper.verifyResponseData("CreateUsers", "POST", apiHelper, userDetails.get("statusCode"), userDetails2);

    }

    @DataProvider(name = "CreateUser")
    Object[][] getData() throws IOException {
        return xUtils.getTestData("Users", "TC_CreateUser");
    }


    @AfterMethod
    public void tearDown()
    {
        System.out.println("Create User test has ended ....");
    }

}
