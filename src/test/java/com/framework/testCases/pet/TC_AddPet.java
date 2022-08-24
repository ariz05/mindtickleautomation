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

public class TC_AddPet extends BaseClass {

    @BeforeMethod
    public void setUp() {
        System.out.println("Add Pet details test has started ....");
    }

    //Positive scenario - All fields are provided with valid values
    @Test(priority = 0, dataProvider = "AddPet")
    @Description("Test to validate pet Add API.")
    @Epic("EP001 - API Testing")
    @Feature("Feature2 : Pet CRUD API calls")
    @Story("Story : Add Pet API")
    @Step("Verify Add pet API with input params : {0}")
    @Severity(SeverityLevel.NORMAL)
    public void AddPet(HashMap<String, String> petDetails) throws IOException {
        List<HashMap<String, String>> petDetailsList = new ArrayList<>();
        petDetailsList.add(petDetails);
        serviceHelper.createRequest("AddPet", petDetailsList);
        serviceHelper.submitRequest(baseURL, "/v2/pet", "POST", apiHelper);
        serviceHelper.verifyResponseData("AddPet", "POST", apiHelper, petDetails.get("statusCode"), petDetailsList);

    }


    @DataProvider(name = "AddPet")
    Object[][] getData() throws IOException {
        return xUtils.getTestData("Pets", "TC_AddPet_001");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("Add Pet details test has ended ....");
    }
}
