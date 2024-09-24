package com.common.framework.steps;

import com.common.framework.pages.SauceLoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

public class UserLoginStep {

    @Autowired
    SauceLoginPage sauceLoginPage;

    @Given("input username {string} and password {string}")
    public void input_username_password(String username, String password) {
        sauceLoginPage.openURL("https://www.saucedemo.com/");
        sauceLoginPage.inputUserInfo(username, password);
    }

    @When("submit user login")
    public void submit_user_login() {
        sauceLoginPage.submitLogin();
    }

    @Then("navigate to welcome page")
    public void navigate_to_welcome_page() {
        sauceLoginPage.screenshotUtil.takeScreenshotByAllure(
                sauceLoginPage.applicationContext.getBean(WebDriver.class), "welcome home");
    }


}
