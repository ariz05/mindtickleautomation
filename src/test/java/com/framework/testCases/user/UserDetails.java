package com.framework.testCases.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.dtos.requests.user.UserDto;

import java.util.HashMap;
import java.util.List;

public class UserDetails {

    public String setUserDetails(List<HashMap<String, String>> requestFields) {
        ObjectMapper objectMapper = new ObjectMapper();
        UserDto userDto;
        String listOfUsers = "[";
        try {

            for (int i = 0; i < requestFields.size(); i++) {
                HashMap<String, String> userDetails = requestFields.get(i);
                userDto = new UserDto();
                userDto.setId(userDetails.get("id"));
                userDto.setUsername(userDetails.get("userName"));
                userDto.setFirstName(userDetails.get("firstName"));
                userDto.setLastName(userDetails.get("lastName"));
                userDto.setEmail(userDetails.get("email"));
                userDto.setPassword(userDetails.get("password"));
                userDto.setPhone(userDetails.get("phone"));
                userDto.setUserStatus(userDetails.get("userStatus"));
                listOfUsers = listOfUsers + objectMapper.writeValueAsString(userDto) + ",";

            }
            listOfUsers = listOfUsers.substring(0, listOfUsers.length() - 1);
            listOfUsers = listOfUsers + "]";

        } catch (Exception ex) {
        }
        return listOfUsers;
    }


    public String setUserDetails(HashMap<String, String> requestFields) {
        ObjectMapper objectMapper = new ObjectMapper();
        UserDto userDto;
        String listOfUsers = "";
        try {
            userDto = new UserDto();
            userDto.setId(requestFields.get("id"));
            userDto.setUsername(requestFields.get("userName"));
            userDto.setFirstName(requestFields.get("firstName"));
            userDto.setLastName(requestFields.get("lastName"));
            userDto.setEmail(requestFields.get("email"));
            userDto.setPassword(requestFields.get("password"));
            userDto.setPhone(requestFields.get("phone"));
            userDto.setUserStatus(requestFields.get("userStatus"));
            listOfUsers = objectMapper.writeValueAsString(userDto);

        } catch (Exception ex) {
        }
        return listOfUsers;
    }
}
