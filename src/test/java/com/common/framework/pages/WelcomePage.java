package com.common.framework.pages;

import com.common.framework.common.BaseWebPage;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WelcomePage extends BaseWebPage {

    @FindBy(id = "onetrust-accept-btn-handler")
    private WebElement acceptButton;

    @FindBy(className = "site-branding__logo--full")
    private WebElement homeLogo;

    @FindBy(xpath = "//a[text()='Who we are']")
    private WebElement aboutUs;

    public void clickOnAccept() {
        webUIElements.loadWebElement(acceptButton);
        screenshotUtil.takeScreenshotByAllure(applicationContext.getBean(WebDriver.class), "pop-up dialog");
        webUIElements.click(acceptButton);
    }

    public void moveToAboutUs() {
        webUIElements.moveToElement(aboutUs);
    }

    @Step("check home page")
    public void checkHomeLogo() {
        if (webUIElements.checkWebElementDisplay(homeLogo)) {
            webUIElements.highlightElement(homeLogo);
            log.info("the homepage is loaded...");
        }
        else log.error("the homepage load failed...");
    }
}


