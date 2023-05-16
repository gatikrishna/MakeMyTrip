package com.demo.mypack.util;

import com.demo.mypack.GlobalProperty;

import java.io.FileInputStream;
import java.util.Properties;

public class CommonUtil {

    public static void readPropertiesFile(String filePath) {
        try {
            FileInputStream file = new FileInputStream(filePath);
            Properties prop = new Properties();
            prop.load(file);
            prop.forEach((k, v) -> GlobalProperty.setProperty(k.toString(), v.toString()));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void readFiles() {
        String filePath = ".\\..\\Demo\\src\\main\\resources\\application.properties";
        readPropertiesFile(filePath);
        String env = GlobalProperty.getProperty("env") + ".properties";
        filePath = ".\\..\\Demo\\src\\main\\resources\\" + env;
        readPropertiesFile(filePath);
    }
}
