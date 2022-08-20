package com.framework.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.dtos.requests.pet.PetDto;
import com.framework.dtos.requests.user.UserDto;
import com.framework.dtos.responses.user.CreateUserResponseDto;
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
    String responseString;
    ObjectMapper objectMapper = new ObjectMapper();
    String requestString;
    CreateUserResponseDto createUserResponseDto;
    List<PetDto> petDetailsResponseDto;
    UserDto userDto;
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
                //System.out.println(requestString);
                Helper.stepLog("pass", "Create User API Request payload : " + requestString);

                break;

            //Request payload creation for user Update API
            case "UpdateUser":
                userName = requestFields.get(0).get("userName");
                userDetails = new UserDetails();
                requestString = userDetails.setUserDetails(requestFields.get(0));
                Helper.stepLog("pass", "Create User API Request payload : " + requestString);
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


        }
    }


    //To invoke API(s) request
    public void submitRequest(String baseUrl, String endPoint, String method, ApiHelper apiHelper) throws JsonProcessingException {
        endPointUrl = baseUrl + endPoint;
        switch (endPoint + ":" + method) {

            // Submitting POST request to create users
            case "/v2/user/createWithArray:POST":
                responseString = apiHelper.submitPostRequest(endPointUrl, requestString);
                createUserResponseDto = new CreateUserResponseDto();
                createUserResponseDto = objectMapper.readValue(responseString, CreateUserResponseDto.class);
                break;

            // Submitting PUT request to update user.
            case "/v2/user/:PUT":
                endPointUrl = endPointUrl + userName;
                responseString = apiHelper.submitPutRequest(endPointUrl, requestString);
                createUserResponseDto = new CreateUserResponseDto();
                createUserResponseDto = objectMapper.readValue(responseString, CreateUserResponseDto.class);
                break;

            // Submitting GET request to fetch user details.
            case "/v2/user/:GET":
                endPointUrl = endPointUrl + userName;
                responseString = apiHelper.submitGetRequest(endPointUrl);
                userDto = new UserDto();
                userDto = objectMapper.readValue(responseString, UserDto.class);
                break;

            // Submitting POST request to Add pet details.
            case "/v2/pet:POST":
                responseString = apiHelper.submitPostRequest(endPointUrl, requestString);
                break;

            // Submitting PUT request to Update pet details.
            case "/v2/pet:PUT":
                responseString = apiHelper.submitPutRequest(endPointUrl, requestString);
                break;

            // Submitting GET request to fetch pet details.
            case "/v2/pet/findByStatus?status=:GET":
                endPointUrl = endPointUrl + status;
                responseString = apiHelper.submitGetRequest(endPointUrl);
                petDetailsResponseDto = objectMapper.readValue(responseString, new TypeReference<List<PetDto>>() {
                });
                break;
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
                usersHelper.validateCreateUserResponse(requestFields.get(0), createUserResponseDto);
                break;

            //validate update user API response.
            case "UpdateUser:PUT":
                System.out.println("Inside Update user details API response validation");
                usersHelper = new UsersHelper();
                usersHelper.validateCreateUserResponse(requestFields.get(0), createUserResponseDto);
                break;

            //validate get user API response.
            case "GetUser:GET":
                System.out.println("Inside Get user details API response validation");
                usersHelper = new UsersHelper();
                usersHelper.validateUserDetails(requestFields.get(0), userDto);
                break;

            //validate Add pet API response.
            case "AddPet:POST":
                System.out.println("Inside Add pet details API response validation");
                assertFields("equals", "Add pet Details", requestString, responseString);
                break;

            //validate Update pet API response.
            case "UpdatePetDetails:PUT":
                System.out.println("Inside Update pet details API response validation");
                assertFields("equals", "Update pet Details", requestString, responseString);
                break;

            //validate get pet API response.
            case "GetPetDetails:GET":
                System.out.println("Inside Get pet details API response validation");
                petsHelper = new PetsHelper();
                petsHelper.validatePetDetailsResponse(requestFields.get(0), petDetailsResponseDto);
                break;
        }
    }
}
