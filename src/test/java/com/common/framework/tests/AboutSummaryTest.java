package com.common.framework.tests;

import com.common.framework.listeners.ExtentReportListener;
import com.common.framework.pages.WelcomePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@SpringBootTest
@Listeners(ExtentReportListener.class)
public class AboutSummaryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WelcomePage welcomePage;

    @Test
    public void goToAboutUs() {
        welcomePage.openURL();
        welcomePage.clickOnAccept();
        welcomePage.checkHomeLogo();
        welcomePage.webUIElements.navigateTo("About us");
        welcomePage.moveToAboutUs();
    }

    @AfterTest
    public void quit() {
        welcomePage.tearDown();
    }


}
