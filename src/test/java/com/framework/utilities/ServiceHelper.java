package com.framework.utilities;

import com.framework.helpers.PetsHelper;
import com.framework.helpers.UsersHelper;
import com.framework.testCases.pet.PetDetails;
import com.framework.testCases.user.UserDetails;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static com.framework.utilities.CommonUtils.assertFields;

public class ServiceHelper {
    String userName;
    String endPointUrl = null;
    public String responseString = null;
    public String requestString = null;
    String status;
    Long id;

    // to create request payload.
    public void createRequest(String endPointName, List<HashMap<String, String>> requestFields) {
        UserDetails userDetails;
        PetDetails petDetails;
        switch (endPointName) {

            //Request payload creation for Create user(s) API
            case "CreateUsers":
                userDetails = new UserDetails();
                requestString = userDetails.setUserDetails(requestFields);
                //Helper.stepLog("pass", "Create User API Request payload : " + requestString);
                //System.out.println(requestString);
                break;

            //Request payload creation for user Update API
            case "UpdateUser":
                userName = requestFields.get(0).get("userName");
                userDetails = new UserDetails();
                requestString = userDetails.setUserDetails(requestFields.get(0));
                //System.out.println(requestString);
                break;

            //Get user details API
            case "GetUser":
                userName = requestFields.get(0).get("userName");
                break;

            //Request payload creation for pet Add API
            case "AddPet":
                petDetails = new PetDetails();
                requestString = petDetails.setPetDetails(requestFields.get(0));
                break;

            //Request payload creation for pet Update API
            case "UpdatePetDetails":
                id = Long.valueOf(requestFields.get(0).get("id"));
                petDetails = new PetDetails();
                requestString = petDetails.setPetDetails(requestFields.get(0));
                break;

            //Get pet details API
            case "GetPetDetails":
                status = requestFields.get(0).get("status");
                break;

            default:
                System.out.println("Invalid Payload creation request case provided.");


        }
    }


    //To invoke API(s) request
    public void submitRequest(String baseUrl, String endPoint, String method, ApiHelper apiHelper) {
        try {
            endPointUrl = baseUrl + endPoint;
            switch (endPoint + ":" + method) {

                // Submitting POST request to create users
                case "/v2/user/createWithArray:POST":
                    responseString = apiHelper.submitPostRequest(endPointUrl, requestString);
                    break;

                // Submitting PUT request to update user.
                case "/v2/user/:PUT":
                    endPointUrl = endPointUrl + userName;
                    responseString = apiHelper.submitPutRequest(endPointUrl, requestString);
                    break;

                // Submitting GET request to fetch user details.
                case "/v2/user/:GET":
                    endPointUrl = endPointUrl + userName;
                    responseString = apiHelper.submitGetRequest(endPointUrl);
                    break;

                // Submitting POST request to Add pet details.
                case "/v2/pet:POST":
                    System.out.println("Inside submit request : Add pet details method ");
                    responseString = apiHelper.submitPostRequest(endPointUrl, requestString);
                    break;

                // Submitting PUT request to Update pet details.
                case "/v2/pet:PUT":
                    System.out.println("Inside submit request : Update pet details method ");
                    responseString = apiHelper.submitPutRequest(endPointUrl, requestString);
                    break;

                // Submitting GET request to fetch pet details.
                case "/v2/pet/findByStatus?status=:GET":
                    endPointUrl = endPointUrl + status;
                    responseString = apiHelper.submitGetRequest(endPointUrl);
                    break;

                default:
                    System.out.println("Invalid submit request case provided.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //To verify API HTTPS status code.
    public void verifyResponseCode(String code, ApiHelper apiHelper) throws IOException {

        if (apiHelper.httpURLConnection == null) {
            assertFields("equals", "API ResponseCode", code, String.valueOf(apiHelper.statusCode));
        } else {
            assertFields("equals", "API ResponseCode", code, String.valueOf(apiHelper.httpURLConnection.getResponseCode()));
        }

    }

    //To verify API response details
    public void verifyResponseData(String endPointName, String method, ApiHelper apiHelper, String code, List<HashMap<String, String>> requestFields) throws IOException {
        UsersHelper usersHelper;
        PetsHelper petsHelper;
        //calling method to validate API HTTPS status code.
        verifyResponseCode(code, apiHelper);
        switch (endPointName + ":" + method) {

            //validate create users API response.
            case "CreateUsers:POST":
                System.out.println("Inside Create user details API response validation");
                usersHelper = new UsersHelper();
                usersHelper.validateResponse(requestFields.get(0), responseString);
                break;

            //validate update user API response.
            case "UpdateUser:PUT":
                System.out.println("Inside Update user details API response validation");
                usersHelper = new UsersHelper();
                usersHelper.validateResponse(requestFields.get(0), responseString);
                break;

            //validate get user API response.
            case "GetUser:GET":
                System.out.println("Inside Get user details API response validation");
                usersHelper = new UsersHelper();
                if (requestFields.get(0).get("statusCode").equalsIgnoreCase("200")) {
                    usersHelper.validateUserDetails(requestFields.get(0), responseString);
                } else {
                    usersHelper.validateResponse(requestFields.get(0), responseString);
                }

                break;

            //validate Add pet API response.
            case "AddPet:POST":
                petsHelper = new PetsHelper();
                petsHelper.validatePetDetails(requestString, responseString);
                System.out.println("Inside Add pet details API response validation");
                //assertFields("equals", "Add pet Details", requestString, responseString);
                break;

            //validate Update pet API response.
            case "UpdatePetDetails:PUT":
                petsHelper = new PetsHelper();
                petsHelper.validatePetDetails(requestString, responseString);
                System.out.println("Inside Update pet details API response validation");
                //assertFields("equals", "Update pet Details", requestString, responseString);
                break;

            //validate get pet API response.
            case "GetPetDetails:GET":
                System.out.println("Inside Get pet details API response validation");
                petsHelper = new PetsHelper();
                petsHelper.validatePetDetailsResponse(requestFields.get(0), responseString);
                break;
        }
    }
}
