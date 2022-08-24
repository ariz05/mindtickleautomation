package com.framework.test.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServiceBeanHelper {
    public static String expectedRequestString1 = "[{\"id\":\"10010\",\"username\":\"test001\",\"firstName\":\"testfirstname\",\"lastName\":\"testlastname\",\"email\":\"test001@gmail.com\",\"password\":\"hello1234\",\"phone\":\"9999999999\",\"userStatus\":\"1\"}]";
    public static String createUsersEndPoint = "/v2/user/createWithArray";
    public static String createUserCase = "CreateUsers";
    public static String createUserMethodType = "POST";
    public static String baseURL = "https://petstore.swagger.io";
    public static String expectedRequestString2 = "[{\"id\":\"abcd\",\"username\":\"test001\",\"firstName\":\"testfirstname\",\"lastName\":\"testlastname\",\"email\":\"test001@gmail.com\",\"password\":\"hello1234\",\"phone\":\"9999999999\",\"userStatus\":\"1\"}]";
    public static List<HashMap<String, String>> requestFields = setCreateUserRequest();


    public static List<HashMap<String, String>> setCreateUserRequest()
    {
        List<HashMap<String, String>> listOfParams = new ArrayList<>();
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("id","10010");
        hashMap.put("userName","test001");
        hashMap.put("firstName","testfirstname");
        hashMap.put("lastName","testlastname");
        hashMap.put("email","test001@gmail.com");
        hashMap.put("password","hello1234");
        hashMap.put("phone","9999999999");
        hashMap.put("userStatus","1");
        listOfParams.add(hashMap);
        return listOfParams;

    }
}
