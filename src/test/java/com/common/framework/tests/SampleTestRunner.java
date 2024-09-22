package com.common.framework.tests;

import com.common.framework.listeners.ExtentReportListener;
import com.common.framework.pages.WelcomePage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Slf4j
@SpringBootTest
@Listeners(ExtentReportListener.class)
public class SampleTestRunner extends AbstractTestNGSpringContextTests {

    public static final Logger LOGGER = LoggerFactory.getLogger(SampleTestRunner.class);

    @Autowired
    protected WelcomePage welcomePage;

    @Test
    public void sampleTest() {
        LOGGER.info("logging test...");
        By forTest = By.id("nothing");
        //welcomePage.goToHomepage();
        welcomePage.webDriver.findElement(forTest);
    }

}
