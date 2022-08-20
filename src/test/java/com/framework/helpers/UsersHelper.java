package com.framework.helpers;

import com.framework.dtos.requests.user.UserDto;
import com.framework.dtos.responses.user.CreateUserResponseDto;

import java.util.HashMap;

import static com.framework.utilities.CommonUtils.assertFields;

public class UsersHelper {

    public void validateCreateUserResponse(HashMap<String, String> responseFields, CreateUserResponseDto createUserResponseDto) {
        assertFields("equals", "code", responseFields.get("code"), createUserResponseDto.getCode());
        assertFields("equals", "type", responseFields.get("type"), createUserResponseDto.getType());
        assertFields("equals", "message", responseFields.get("message"), createUserResponseDto.getMessage());
    }

    public void validateUserDetails(HashMap<String, String> responseFields, UserDto userDto) {
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
