package com.common.framework.tests;

import com.common.framework.listeners.ExtentReportListener;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;

@CucumberOptions(
        tags = "",
        features = "src/test/resources/features",
        glue = "com.common.framework.steps",
        plugin = { "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "json:target/cucumber-reports/json-files/cucumber.json",
                "html:target/cucumber-reports/cucumber.html" }
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests{

}
