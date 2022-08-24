package com.framework.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.dtos.requests.user.UserDto;
import com.framework.dtos.responses.CreateUserResponseDto;

import java.util.HashMap;

import static com.framework.utilities.CommonUtils.assertFields;

public class UsersHelper {
    ObjectMapper objectMapper = new ObjectMapper();

    public void validateResponse(HashMap<String, String> expectedResponseFields, String responseString) throws JsonProcessingException {
        CreateUserResponseDto createUserResponseDto = objectMapper.readValue(responseString, CreateUserResponseDto.class);
        assertFields("equals", "code", expectedResponseFields.get("code"), createUserResponseDto.getCode());
        assertFields("equals", "type", expectedResponseFields.get("type"), createUserResponseDto.getType());
        assertFields("equals", "message", expectedResponseFields.get("message"), createUserResponseDto.getMessage());
    }

    public void validateUserDetails(HashMap<String, String> responseFields, String responseString) throws JsonProcessingException {
        UserDto userDto;
        userDto = objectMapper.readValue(responseString, UserDto.class);
        assertFields("equals", "id", responseFields.get("id"), userDto.getId());
        assertFields("equals", "userName", responseFields.get("userName"), userDto.getUsername());
        assertFields("equals", "firstName", responseFields.get("firstName"), userDto.getFirstName());
        assertFields("equals", "lastName", responseFields.get("lastName"), userDto.getLastName());
        assertFields("equals", "email", responseFields.get("email"), userDto.getEmail());
        assertFields("equals", "password", responseFields.get("password"), userDto.getPassword());
        assertFields("equals", "phone", responseFields.get("phone"), userDto.getPhone());
        assertFields("equals", "userStatus", responseFields.get("userStatus"), userDto.getUserStatus());
    }


}
