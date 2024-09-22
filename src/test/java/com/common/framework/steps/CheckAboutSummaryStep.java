package com.common.framework.steps;

import com.common.framework.common.BaseWebPage;
import com.common.framework.pages.WelcomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@CucumberContextConfiguration
public class CheckAboutSummaryStep extends BaseWebPage {

    @Autowired
    protected WelcomePage welcomePage;

    @Given("go to welcome homepage")
    public void go_to_welcome_homepage() {
        welcomePage.openURL();
        welcomePage.clickOnAccept();
        welcomePage.checkHomeLogo();
    }
    @When("navigate to about summary")
    public void navigate_to_about_summary() {
        webUIElements.navigateTo("About us");
        welcomePage.moveToAboutUs();
    }
    @Then("the summary details should be shown")
    public void the_summary_details_should_be_shown() {
        screenshotUtil.takeScreenshotByAllure(applicationContext.getBean(WebDriver.class), "summary list of About Us");
    }

}
