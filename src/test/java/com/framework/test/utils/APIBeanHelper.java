package com.framework.test.utils;

public class APIBeanHelper {
    public static String postUserRequestString = "[{\"id\":\"10010\",\"username\":\"test001\",\"firstName\":\"testfirstname\",\"lastName\":\"testlastname\",\"email\":\"test001@gmail.com\",\"password\":\"hello1234\",\"phone\":\"9999999999\",\"userStatus\":\"1\"}]";
    public static String postUserResponseString = "{\"code\":200,\"type\":\"unknown\",\"message\":\"ok\"}";
    public static String userRequestErrorString = "[{\"id\":\"abcd\",\"username\":\"test001\",\"firstName\":\"testfirstname\",\"lastName\":\"testlastname\",\"email\":\"test001@gmail.com\",\"password\":\"hello1234\",\"phone\":\"9999999999\",\"userStatus\":\"1\"}]";
    public static String userResponseErrorString = "{\"code\":500,\"type\":\"unknown\",\"message\":\"something bad happened\"}";
    public static String postUserEndpoint = "https://petstore.swagger.io/v2/user/createWithArray";

    public static String putUserRequestString = "{\"id\":\"10010\",\"username\":\"test001\",\"firstName\":\"testfirstname\",\"lastName\":\"testlastname\",\"email\":\"test001@gmail.com\",\"password\":\"hello1234\",\"phone\":\"9999999999\",\"userStatus\":\"1\"}";
    public static String putUserResponseString = "{\"code\":200,\"type\":\"unknown\",\"message\":\"10010\"}";
    public static String putUserEndpoint = "https://petstore.swagger.io/v2/user/test001";

    public static String getUserResponseString = "{\"id\":10010,\"username\":\"test001\",\"firstName\":\"testfirstname\",\"lastName\":\"testlastname\",\"email\":\"test001@gmail.com\",\"password\":\"hello1234\",\"phone\":\"9999999999\",\"userStatus\":1}";
    public static String getUserEndpoint = "https://petstore.swagger.io/v2/user/test001";
    public static String getUserInvalidEndpoint = "https://petstore.swagger.io/v2/user/testt";
    public static String getUserResponseErrorString = "{\"code\":1,\"type\":\"error\",\"message\":\"User not found\"}";



}
