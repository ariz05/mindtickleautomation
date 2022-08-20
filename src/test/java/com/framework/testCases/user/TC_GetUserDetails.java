package com.framework.testCases.user;

import com.framework.testCases.BaseClass;
import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TC_GetUserDetails extends BaseClass {

    //Positive scenario - All fields are provided with valid values
    @Test(priority = 3, dataProvider = "GetUser")
    @Description("Test to validate GET user API.")
    @Epic("EP001 - API Testing")
    @Feature("Feature1 : User CRUD API calls")
    @Story("Story : Get User API")
    @Step("Verify get user details API")
    @Severity(SeverityLevel.NORMAL)
    public void validateUserDetails(HashMap<String, String> userDetails) throws IOException {
        logger = report.createTest("Test to validate GET user API.");
        List<HashMap<String, String>> userDetailsList = new ArrayList<>();
        userDetailsList.add(userDetails);
        serviceHelper.createRequest("GetUser", userDetailsList);
        serviceHelper.submitRequest(baseURL, "/v2/user/", "GET", apiHelper);
        serviceHelper.verifyResponseData("GetUser", "GET", apiHelper, "200", userDetailsList);

    }


    @DataProvider(name = "GetUser")
    Object[][] getData() throws IOException {
        return xUtils.getTestData("Users", "TC_GetUser");
    }
}
