package com.demo.mypack.util;

import org.apache.log4j.Logger;

import java.lang.reflect.Method;

public class PluginUtil {

    private static PluginUtil pluginUtil = null;
    private static final Logger LOGGER = Logger.getLogger(PluginUtil.class);

    private PluginUtil() {

    }

    public static synchronized PluginUtil getInstance() {
        if (pluginUtil == null)
            pluginUtil = new PluginUtil();
        return pluginUtil;
    }

    public Class getClassInstance(String className) {
        final Package[] packages = Package.getPackages();
        for (final Package p : packages) {
            final String pack = p.getName();
            if (pack.contains("com.demo.mypack")) {
                final String tentative = pack + "." + className;
                LOGGER.info("Tentative class: "+tentative);
                try {
                    return Class.forName(tentative);
                } catch (final ClassNotFoundException ex) {
                    continue;
                }
            }
        }
        return null;
    }

    public Method getRequiredMethodFromObject(Object obj, String fieldName) throws ClassNotFoundException {
        Class c = Class.forName(String.valueOf(obj.getClass().getName()));
        Method methods[] = c.getMethods();//c.getDeclaredMethods();
        for (Method method : methods) {
            String methodName = method.getName();
//            LOGGER.info("method name: " + methodName);
            if (method != null && !"class".equals(methodName)) {
                if (methodName.toLowerCase().contains(fieldName.toLowerCase())) {
                    methodName = methodName.substring(3, methodName.length());
                    methodName = methodName.split("_")[0];
                    if (methodName.equalsIgnoreCase(fieldName)) {
                        return method;
                    }
                }
            }
        }
        return null;
    }
}
