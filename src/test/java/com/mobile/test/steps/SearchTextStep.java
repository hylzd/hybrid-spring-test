package com.mobile.test.steps;

import com.mobile.test.framework.base.AbstractPage;
import com.mobile.test.pages.WelcomePage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.springframework.beans.factory.annotation.Autowired;

public class SearchTextStep extends AbstractPage {

    public SearchTextStep(AppiumDriver<? extends MobileElement> driver) {
        super(driver);
    }

    @Autowired
    WelcomePage welcomePage;

    @Given("input {string} on welcome page")
    public void input_on_welcome_page(String inputText) {
        // Write code here that turns the phrase above into concrete actions
        welcomePage.inputSearchText(inputText);
    }

    @Then("click on the search button")
    public void click_on_the_search_button() {
        // Write code here that turns the phrase above into concrete actions
        welcomePage.clickSearchButton();
    }

    @Then("related context is listed")
    public void related_context_is_listed() {
        // Write code here that turns the phrase above into concrete actions
    }
}
