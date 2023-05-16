package com.demo.mypack;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverManager {
    public static WebDriver driver;
    Logger LOGGER = Logger.getLogger(DriverManager.class);
    private static DriverManager driverManager = null;


    private DriverManager() {
        LOGGER.info("Instantiating Driver Manager");
    }

    public static synchronized DriverManager getInstance() {
        if (driverManager == null)
            driverManager = new DriverManager();
        return driverManager;
    }


    public WebDriver selectBrowser(String browser) {
        switch (browser) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(chromeOptions);
                return driver;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(firefoxOptions);
                return driver;

            default:
                LOGGER.info("Browser name not specified");
                return null;

        }
    }


    public WebDriver getDriver(String browser) {
        return selectBrowser(browser);
    }

    public void setDriver(WebDriver driver){
        DriverManager.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
