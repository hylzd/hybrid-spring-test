package com.common.framework.steps;

import com.common.framework.common.BaseWebPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

@Slf4j
public class CucumberHooks extends BaseWebPage {

    @Before
    public void initialization() {
        log.info("Cucumber Hooks -- init");
        //webUIElements.openBrowser("https://www.saucedemo.com/");
    }

    @After
    public void captureScreenScenario() {
        screenshotUtil.takeScreenshotByAllure(applicationContext.getBean(WebDriver.class), "Scenario End");
    }

}
