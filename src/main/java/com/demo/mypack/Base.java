package com.demo.mypack;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.concurrent.TimeUnit;

public abstract class Base {
    public static WebDriver driver;

    public static void launchBrowser() {
        driver = DriverManager.getInstance().getDriver(GlobalProperty.getProperty("browser"));
        DriverManager.getInstance().setDriver(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(GlobalProperty.getProperty("url"));
        driver.manage().window().maximize();
    }



    public abstract ExpectedCondition getPageLoadCondition();

}
