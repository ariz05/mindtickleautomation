package com.framework.utilities;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class CommonUtils {


    public static String getRandomEmail() {
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(10000);
        return RandomStringUtils.randomAlphabetic(6).toLowerCase() + randomInt + "@gmail.com";
    }

    public String randomString() {
        String generatedstring = RandomStringUtils.randomAlphabetic(8);
        return (generatedstring);
    }

    public static String randomNum() {
        String generatedString2 = RandomStringUtils.randomNumeric(4);
        return (generatedString2);
    }

    public static String setFieldValue(Map<String, String> requestFields, String key, String value) {
        String field = null, fieldVal;
        if (requestFields.get(key) != null) {
            fieldVal = requestFields.get(key);
            switch (fieldVal.toUpperCase()) {
                case "GENERATE":
                    field = value;
                    break;
                case "EMPTY":
                    field = "";
                    break;
                case "NULL":
                    field = null;
                    break;
                default:
                    if (fieldVal.contains("stamp"))
                        field = returnStampValue(fieldVal);
                    else
                        field = requestFields.get(key);
            }
        } else
            field = requestFields.get(key);
        return field;
    }


    private static String returnStampValue(String fieldVal) {
        String value;
        if (fieldVal.contains("Timestamp"))
            value = fieldVal.replace("Timestamp", (new SimpleDateFormat("yyyyMMddHHmmssSSS")).format(Calendar.getInstance().getTime()));
        else if (fieldVal.contains("Minutestamp"))
            value = fieldVal.replace("Minutestamp", (new SimpleDateFormat("yyyyMMddHHmm")).format(Calendar.getInstance().getTime()));
        else if (fieldVal.contains("Hourstamp"))
            value = fieldVal.replace("Hourstamp", (new SimpleDateFormat("yyyyMMddHH")).format(Calendar.getInstance().getTime()));
        else if (fieldVal.contains("Secondstamp"))
            value = fieldVal.replace("Secondstamp", (new SimpleDateFormat("yyyyMMddHHmmss")).format(Calendar.getInstance().getTime()));
        else if (fieldVal.contains("Ssnstamp"))
            value = fieldVal.replace("Ssnstamp", (new SimpleDateFormat("MMddHH")).format(Calendar.getInstance().getTime()));
        else if (fieldVal.contains("Phonestamp"))
            value = fieldVal.replace("Phonestamp", (new SimpleDateFormat("yyMMddHH")).format(Calendar.getInstance().getTime()));
        else
            throw new IllegalStateException("Unexpected value: " + fieldVal);
        return value;
    }

    public static void assertFields(String assertionType, String field, String expectedValue, Object actualValue) {

        System.out.println("Expected " + Optional.ofNullable(expectedValue).toString() + ":" + "Actual " + Optional.ofNullable(actualValue).toString());
        switch (assertionType) {
            case "non-null":
                System.out.println("Inside non-null for field : " + field);
                Assert.assertFalse("Field : " + field + " is empty", actualValue.toString().isEmpty());
                break;
            case "equals":
                System.out.println("Inside equals for field : " + field);
                Assert.assertEquals("Field : " + field + " value is not as expected.", expectedValue, actualValue);
                break;
            case "notequals":
                System.out.println("Inside notequals  for field : " + field);
                Assert.assertNotEquals("Field : " + field + " value is not as expected.", expectedValue, actualValue);
                break;
            case "contains":
                System.out.println("Inside contains  for field : " + field);
                Assert.assertTrue("Field value does not contain expected value", actualValue.toString().contains(expectedValue));
                break;
            case "null":
                System.out.println("Inside null for field : " + field);
                Assert.assertNull("Field : " + field + " is not null", actualValue);
                break;
            case "long":
                System.out.println("Inside long  for field : " + field);
                Assert.assertEquals("Field : " + field + " value is not as expected.", Long.parseLong(expectedValue), Long.parseLong(actualValue.toString()));
                break;
            case "boolean":
                System.out.println("Inside boolean for field : " + field);
                Assert.assertEquals("Field : " + field + " value is not as expected.", Boolean.parseBoolean(expectedValue), Boolean.parseBoolean(actualValue.toString()));
                break;
            default:
                Assert.fail("Invalid Value Type for assertion");
                break;
        }
    }
}
