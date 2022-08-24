package com.framework.test.services;

import com.framework.test.utils.APIBeanHelper;
import com.framework.test.utils.ServiceBeanHelper;
import com.framework.utilities.ApiHelper;
import com.framework.utilities.ServiceHelper;
import io.qameta.allure.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.framework.utilities.CommonUtils.assertFields;
import static org.mockito.Mockito.when;

public class ServiceHelperTest {

    ServiceHelper serviceHelper;
    @Mock
    ApiHelper mockAPiHelper = Mockito.mock(ApiHelper.class);

    @BeforeMethod
    void setUp() {
        System.out.println("Service helper unit test started ...");
        serviceHelper = new ServiceHelper();

    }

    @Test
    @Epic("EP002 - Unit Testing")
    @Feature("Feature1 : ServiceHelper methods")
    @Step("Verify CreateRequestTest")
    @Severity(SeverityLevel.BLOCKER)
    public void CreateRequestTest() {
        serviceHelper.createRequest(ServiceBeanHelper.createUserCase, ServiceBeanHelper.requestFields);
        assertFields("equals", "createRequestMethod", ServiceBeanHelper.expectedRequestString1, serviceHelper.requestString);

    }

    @Test(priority = 1)
    @Epic("EP002 - Unit Testing")
    @Feature("Feature1 : ServiceHelper methods")
    @Step("Verify CreateRequestTestError")
    @Severity(SeverityLevel.BLOCKER)
    public void CreateRequestTestError() {
        serviceHelper.createRequest("invalid endpoint", ServiceBeanHelper.requestFields);
        assertFields("null", "createRequestMethod", "null", serviceHelper.requestString);

    }

    @Test(priority = 2)
    @Epic("EP002 - Unit Testing")
    @Feature("Feature1 : ServiceHelper methods")
    @Step("Verify SubmitRequestTest")
    @Severity(SeverityLevel.BLOCKER)
    public void SubmitRequestTest() {
        serviceHelper.requestString = ServiceBeanHelper.expectedRequestString1;
        when(mockAPiHelper.submitPostRequest(APIBeanHelper.postUserEndpoint, ServiceBeanHelper.expectedRequestString1)).thenReturn(APIBeanHelper.postUserResponseString);
        serviceHelper.submitRequest(ServiceBeanHelper.baseURL, ServiceBeanHelper.createUsersEndPoint, ServiceBeanHelper.createUserMethodType, mockAPiHelper);
        assertFields("equals", "submitRequestMethod", APIBeanHelper.postUserResponseString, serviceHelper.responseString);
    }

    @Test(priority = 3)
    @Epic("EP002 - Unit Testing")
    @Feature("Feature1 : ServiceHelper methods")
    @Step("Verify SubmitRequestTest")
    @Severity(SeverityLevel.BLOCKER)
    public void SubmitRequestTestError() {
        serviceHelper.requestString = ServiceBeanHelper.expectedRequestString1;
        when(mockAPiHelper.submitPostRequest(APIBeanHelper.postUserEndpoint, ServiceBeanHelper.expectedRequestString1)).thenReturn(APIBeanHelper.postUserResponseString);
        serviceHelper.submitRequest(ServiceBeanHelper.baseURL, "invalid endpoint", ServiceBeanHelper.createUserMethodType, mockAPiHelper);
        assertFields("null", "submitRequestMethod", "null", serviceHelper.responseString);
    }

    @AfterMethod
    void tearDown() {
        System.out.println("Service helper unit test ended ...");
        serviceHelper = null;
    }
}
