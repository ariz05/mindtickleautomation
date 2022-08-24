package com.framework.test.services;

import com.framework.test.utils.APIBeanHelper;
import com.framework.utilities.ApiHelper;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.framework.utilities.CommonUtils.assertFields;

public class ApiHelperTest {
    ApiHelper apiHelper;

    @BeforeMethod
    void setUp() {
        System.out.println("API helper unit test started ...");
        apiHelper = new ApiHelper();
    }

    @Test(priority = 0)
    @Epic("EP002 - Unit Testing")
    @Feature("Feature1 : APIHelper methods")
    @Step("Verify submitPostRequestTest")
    @Severity(SeverityLevel.BLOCKER)
    public void submitPostRequestTest() {
        String actualResult = apiHelper.submitPostRequest(APIBeanHelper.postUserEndpoint, APIBeanHelper.postUserRequestString);
        assertFields("equals", "submitPostRequestMethod", APIBeanHelper.postUserResponseString, actualResult);

    }


    @Test(priority = 1)
    @Epic("EP002 - Unit Testing")
    @Feature("Feature1 : APIHelper methods")
    @Step("Verify submitPostRequestError")
    @Severity(SeverityLevel.BLOCKER)
    public void submitPostRequestErrorTestInvalidRequest() {
        String actualResult = apiHelper.submitPostRequest(APIBeanHelper.postUserEndpoint, APIBeanHelper.userRequestErrorString);
        assertFields("equals", "submitPostRequestMethod", APIBeanHelper.userResponseErrorString, actualResult);

    }

    @Test(priority = 2)
    @Epic("EP002 - Unit Testing")
    @Feature("Feature1 : APIHelper methods")
    @Step("Verify submitUpdateRequestTest")
    @Severity(SeverityLevel.BLOCKER)
    public void submitUpdateRequestTest() {
        String actualResult = apiHelper.submitPutRequest(APIBeanHelper.putUserEndpoint, APIBeanHelper.putUserRequestString);
        assertFields("equals", "submitPutRequestMethod", APIBeanHelper.putUserResponseString, actualResult);

    }

    @Test(priority = 3)
    @Epic("EP002 - Unit Testing")
    @Feature("Feature1 : APIHelper methods")
    @Step("Verify submitUpdateRequestError")
    @Severity(SeverityLevel.BLOCKER)
    public void submitUpdateRequestErrorTestInvalidRequest() {
        String actualResult = apiHelper.submitPutRequest(APIBeanHelper.putUserEndpoint, APIBeanHelper.userRequestErrorString);
        assertFields("equals", "submitPutRequestMethod", APIBeanHelper.userResponseErrorString, actualResult);

    }

    @Test(priority = 4)
    @Epic("EP002 - Unit Testing")
    @Feature("Feature1 : APIHelper methods")
    @Step("Verify submitGetRequestTest")
    @Severity(SeverityLevel.BLOCKER)
    public void submitGetRequestTest() {
        String actualResult = apiHelper.submitGetRequest(APIBeanHelper.getUserEndpoint);
        assertFields("equals", "submitGetRequestMethod", APIBeanHelper.getUserResponseString, actualResult);

    }

    @Test(priority = 5)
    @Epic("EP002 - Unit Testing")
    @Feature("Feature1 : APIHelper methods")
    @Step("Verify submitGetRequestErrorTest")
    @Severity(SeverityLevel.BLOCKER)
    public void submitGetRequestErrorTest() {

        String actualResult = apiHelper.submitGetRequest(APIBeanHelper.getUserInvalidEndpoint);
        assertFields("equals", "submitGetRequestMethod", APIBeanHelper.getUserResponseErrorString, actualResult);
    }


    @AfterMethod
    void tearDown() {
        System.out.println("API helper unit test ended ...");
        apiHelper = null;
    }

}

