package com.framework.testCases.user;

import com.framework.testCases.BaseClass;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TC_UpdateUserDetails extends BaseClass {

    @BeforeMethod
    public void setUp() {
        System.out.println("Update User test has started ....");
    }

    //Positive scenario - All fields are provided with valid values
    @Test(priority = 1, dataProvider = "UpdateUser")
    @Description("Test to validate Update user API.")
    @Epic("EP001 - API Testing")
    @Feature("Feature1 : User CRUD API calls")
    @Story("Story : Update User API")
    @Step("Verify update user details API input params : {0}")
    @Severity(SeverityLevel.NORMAL)
    public void updateUser(HashMap<String, String> userDetails) throws IOException {
        List<HashMap<String, String>> userDetailsList = new ArrayList<>();
        userDetailsList.add(userDetails);
        serviceHelper.createRequest("UpdateUser", userDetailsList);
        serviceHelper.submitRequest(baseURL, "/v2/user/", "PUT", apiHelper);
        serviceHelper.verifyResponseData("UpdateUser", "PUT", apiHelper, userDetails.get("statusCode"), userDetailsList);

    }


    @DataProvider(name = "UpdateUser")
    Object[][] getData() throws IOException {
        return xUtils.getTestData("Users", "TC_UpdateUser");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("Update User test has ended ....");
    }

}
