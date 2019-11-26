package com.mobile.test.framework.base;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "json:target/cucumber-report.json"},
        //plugin = {"json:target/cucumber-report.json"},
        features = {"src/test/resources/features"},
        glue = {"com.mobile.test.steps"})
public class CucumberCukes {
}
