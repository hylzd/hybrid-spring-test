package com.common.framework.tests;

import com.common.framework.pages.WelcomePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@SpringBootTest
public class AboutSummaryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WelcomePage welcomePage;

    @Test(priority = 1)
    public void goToWelcomeHome() {
        welcomePage.goToHomepage();
    }

    @Test(priority = 2)
    public void goToAboutUs() throws Exception {
        welcomePage.clickOnAccept();
        welcomePage.checkHomeLogo();
        welcomePage.iWebUIElements.navigateTo("About us");
    }
}
