package com.demo.mypack;

import java.util.HashMap;
import java.util.Map;

public class GlobalProperty {

    public static Map<String, String> map = new HashMap<String, String>();

    public static String getProperty(String key) {
        return map.get(key);
    }

    public static void setProperty(String key, String value) {
        map.put(key, value);
    }

}
