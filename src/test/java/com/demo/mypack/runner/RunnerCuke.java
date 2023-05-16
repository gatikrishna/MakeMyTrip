package com.demo.mypack.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.demo.mypack.stepDefinition"},
        tags = "@gati",
//        dryRun = false,
        monochrome = true,
        plugin = {"pretty",
                "json:target/cucumber-json-report.json",
                "html:target/cucumber-html-report.html",
                "junit:target/cucumber.xml",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
public class RunnerCuke {


}
