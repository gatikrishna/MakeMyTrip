package com.demo.mypack.util;

import com.demo.mypack.Base;
import com.demo.mypack.DriverManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.MethodNotSupportedException;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class LocatorUtil {

    WebDriver driver = DriverManager.getInstance().getDriver();
    PluginUtil pluginUtil;
    private static final Logger LOGGER = Logger.getLogger(LocatorUtil.class);
    private static LocatorUtil locatorUtil = null;
    private long LOAD_TIMEOUT = 30;
    private long REFRESH_RATE = 2;

    public static synchronized LocatorUtil getInstance() {
        if (locatorUtil == null)
            locatorUtil = new LocatorUtil();
        return locatorUtil;
    }

    public void feedData(Map<String, String> data, String page) throws MethodNotSupportedException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        pluginUtil = PluginUtil.getInstance();
        Class c = pluginUtil.getClassInstance(page);
        Object o = "";
        try {
            o = openPage(c);
        } catch (Exception ex) {
            LOGGER.info("Page not found: " + page);
        }
        fillDetailsToPage(o, data);
    }

    public void fillDetailsToPage(Object o, Map<String, String> details) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, MethodNotSupportedException {
        LOGGER.info("fill details");
        String actionType;
//        if (details.containsKey("iframe")) {
//            String frameName = details.get("iframe");
//            if (frameName.equalsIgnoreCase("default")) {
//                switchDefault();
//            } else {
//                switchToFrame(frameName);
//            }
//        }


        StringBuilder builder = new StringBuilder();
        builder.append(String.format("|%s|%s", "Key", "Value"));
        builder.append("\n");
        for (String fieldName : details.keySet()) {
            Method methodObj = PluginUtil.getInstance().getRequiredMethodFromObject(o, fieldName);
            if (methodObj != null) {
                actionType = methodObj.getName().split("_")[1];
                WebElement element = (WebElement) methodObj.invoke(o);
                String value = String.valueOf(details.get(fieldName));
//                value = Utility.getValue(value);
//
//                PropertyHolder.setProperty(fieldName, value);
//                PropertyHolder.getProperty(fieldName);

                value = StringUtils.equals(value, "null") ? "" : value;
                builder.append(String.format("|%s|%s", fieldName, value));
                builder.append("\n");
                Map<String, String> resultset = new HashMap<String, String>();
                doAction(element, actionType, fieldName, value, resultset);
//                performViewAction(o, resultset);
            } else {
                throw new MethodNotSupportedException(String.format("Method Not Found For Field %s", fieldName));
            }
        }
    }

    private int AJAX_ELEMENT_TIMEOUT = 30;

    public <T> T openPage(Class<T> clazz) {
        T page = null;
        try {

            AjaxElementLocatorFactory ajaxElemFactory = new AjaxElementLocatorFactory(driver, AJAX_ELEMENT_TIMEOUT);
            page = PageFactory.initElements(driver, clazz);
            PageFactory.initElements(ajaxElemFactory, page);


//            String def = clazz.getSimpleName();
//            def = def.replace(String.valueOf(def.charAt(0)), String.valueOf(def.charAt(0)).toLowerCase());


            ExpectedCondition pageLoadCondition = ((Base) page).getPageLoadCondition();
            waitForPageToLoad(pageLoadCondition);
            LOGGER.info(String.format("Opening page %s ", clazz.getSimpleName()));
        } catch (NoSuchElementException e) {
//            SystemUtils.addScreenshotToReport();
            throw new IllegalStateException(String.format("This is not the %s page", clazz.getSimpleName()));
        }
        return page;
    }

    private void waitForPageToLoad(ExpectedCondition pageLoadCondition) {
        Wait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(LOAD_TIMEOUT))
                .pollingEvery(Duration.ofSeconds(REFRESH_RATE));
        wait.until(pageLoadCondition);
    }


    public static void doAction(WebElement webElement, String actionType, String key, String value, Map resulset) {
        switch (actionType) {
            case "btn":
                webElement.click();
                break;
            case "type":
                webElement.sendKeys(value);
                break;
            case "lbl":
                webElement.getAttribute(value);
                break;
            default:
                System.out.println("Method not available");

        }
    }
}
