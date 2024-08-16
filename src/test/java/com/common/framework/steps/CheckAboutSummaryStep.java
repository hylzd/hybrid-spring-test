package com.common.framework.steps;

import com.common.framework.common.BasePage;
import com.common.framework.pages.WelcomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@CucumberContextConfiguration
public class CheckAboutSummaryStep extends BasePage {

    @Autowired
    protected WelcomePage welcomePage;

    @Given("go to welcome homepage")
    public void go_to_welcome_homepage() {
        welcomePage.goToHomepage();
    }
    @When("navigate to about summary")
    public void navigate_to_about_summary() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("when...");
    }
    @Then("the summary details should be shown")
    public void the_summary_details_should_be_shown() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("then...");
    }

}
