package com.framework.utilities;


import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

    Properties pro;

    public ReadConfig() {
        File src = new File("./Configuration/config.properties");
        try {
            FileInputStream fis = new FileInputStream(src);
            pro = new Properties();
            pro.load(fis);
        } catch (Exception e) {
            System.out.println("Error in loading configuration file " + e.getMessage());
        }
    }

    public String getApplicationURL() {
        return pro.getProperty("baseURL");
    }


}





