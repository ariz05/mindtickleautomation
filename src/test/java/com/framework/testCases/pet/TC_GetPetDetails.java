package com.framework.testCases.pet;

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

public class TC_GetPetDetails extends BaseClass {

    @BeforeMethod
    public void setUp() {
        System.out.println("Get Pet details test has started ....");
    }

    //Positive scenario - All fields are provided with valid values
    @Test(priority = 2, dataProvider = "GetPetDetails")
    @Description("Test to validate Get pet API.")
    @Epic("EP001 - API Testing")
    @Feature("Feature2 : Pet CRUD API calls")
    @Story("Story : Get Pet API")
    @Step("Verify get pet details API call input params : {0}")
    @Severity(SeverityLevel.NORMAL)
    public void validatePetDetails(HashMap<String, String> petDetails) throws IOException {
        List<HashMap<String, String>> petDetailsList = new ArrayList<>();
        petDetailsList.add(petDetails);
        serviceHelper.createRequest("GetPetDetails", petDetailsList);
        serviceHelper.submitRequest(baseURL, "/v2/pet/findByStatus?status=", "GET", apiHelper);
        serviceHelper.verifyResponseData("GetPetDetails", "GET", apiHelper, petDetails.get("statusCode"), petDetailsList);

    }


    @DataProvider(name = "GetPetDetails")
    Object[][] getData() throws IOException {
        return xUtils.getTestData("Pets", "TC_GetPetDetails_001");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("Get Pet details test has ended ....");
    }
}
