package com.framework.utilities;

import com.aventstack.extentreports.Status;
import com.framework.testCases.BaseClass;

import java.io.File;

public class Helper {

    //Logging the steps in report with info
    public static void stepLog(String logDetails) {
        BaseClass.logger.info(logDetails);
    }

    //Logging the steps in report with result status
    public static void stepLog(String status, String logDetails) {
        switch (status) {
            case "pass":
                BaseClass.logger.log(Status.PASS, logDetails);
                break;

            case "fail":
                BaseClass.logger.log(Status.FAIL, logDetails);
                break;

            case "error":
                BaseClass.logger.log(Status.ERROR, logDetails);
                break;

            case "debug":
                BaseClass.logger.log(Status.DEBUG, logDetails);
                break;

            case "fatal":
                BaseClass.logger.log(Status.FATAL, logDetails);
                break;

            default:
                BaseClass.logger.log(Status.FAIL, "invalid case passed for step logging");
                //System.out.println("Invalid case passed for step logging");
                break;

        }

    }

    //Checking file directory whether it exists or not.
    public static void checkFileDirectoryPath(String directoryPath) {
        File obj = new File(directoryPath);
        if (!obj.exists()) {
            obj.mkdir();
        }

    }
}
