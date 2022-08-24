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

public class TC_Update_PetDetails extends BaseClass {

    @BeforeMethod
    public void setUp() {
        System.out.println("Update Pet details test has started ....");
    }

    //Positive scenario - All fields are provided with valid values
    @Test(priority = 1, dataProvider = "UpdatePetDetails")
    @Description("Test to validate Update pet API.")
    @Epic("EP001 - API Testing")
    @Feature("Feature2 : Pet CRUD API calls")
    @Story("Story : Update Pet API")
    @Step("Verify Update pet details API input params : {0}")
    @Severity(SeverityLevel.NORMAL)
    public void updatePetDetails(HashMap<String, String> petDetails) throws IOException {
        List<HashMap<String, String>> petDetailsList = new ArrayList<>();
        petDetailsList.add(petDetails);
        serviceHelper.createRequest("UpdatePetDetails", petDetailsList);
        serviceHelper.submitRequest(baseURL, "/v2/pet", "PUT", apiHelper);
        serviceHelper.verifyResponseData("UpdatePetDetails", "PUT", apiHelper, petDetails.get("statusCode"), petDetailsList);

    }


    @DataProvider(name = "UpdatePetDetails")
    Object[][] getData() throws IOException {
        return xUtils.getTestData("Pets", "TC_UpdatePetDetails_001");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("Update Pet details test has ended ....");
    }
}
