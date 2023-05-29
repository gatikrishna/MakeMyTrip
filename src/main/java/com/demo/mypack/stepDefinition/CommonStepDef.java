package com.demo.mypack.stepDefinition;

import com.demo.mypack.Base;
import com.demo.mypack.DriverManager;
import com.demo.mypack.util.Flights;
import com.demo.mypack.util.CommonUtil;
import com.demo.mypack.util.LocatorUtil;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;

import org.apache.log4j.Logger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


import java.util.*;


public class CommonStepDef {

    WebDriver driver;
    Flights flights;
    private static final Logger LOGGER = Logger.getLogger(CommonStepDef.class);

    @Given("^User Provides Below details for page \"([^\"]*)\"$")
    public void user_provides_below_details_for_page_something(String page, DataTable dt) throws Throwable {
        Map<String, String> data = dt.asMap(String.class, String.class);
        feedDataToPage(data, page);
//        LOGGER.info("column count is : " + dt.asLists(String.class).get(0).stream().count());
//        LOGGER.info("row count is : " + dt.asLists(String.class).stream().count());
//        readExcel();

       /* List<List<String>> rows= dt.asLists(String.class);
        for(List<String> columns: rows){
            columns.size();
            LOGGER.info("inside method..."+columns.get(0)+" "+columns.get(1)+" "+columns.get(2));
        }*/
//        LOGGER.info("inside method...");
//        data.forEach((k)-> LOGGER.info("k is: "+k.toString()));

    }





    public void feedDataToPage(Map<String, String> map, String page) {
        try {
            LOGGER.info("Feeding data to page");
            LocatorUtil.getInstance().feedData(map, page);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

//    @When("^user performs below action$")
//    public void user_performs_below_action() throws Throwable {
//        System.out.println("user_performs_below_action");
//    }


    @AfterStep
    public void tearDownStep(Scenario scenario) {
        final byte[] screenshot;
        screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "image");
    }


        @Before
    public void init() {
        System.out.println("reading 1st");
        CommonUtil.readFiles();
        Base.launchBrowser();
        driver = DriverManager.getInstance().getDriver();


    }

    @After
    public void destoyFramework() {
        driver.close();
        if (driver != null) {
//            driver.close();
            driver.quit();
        }

    }


}
