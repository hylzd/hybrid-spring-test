package com.common.framework.pages;

import com.common.framework.common.BasePage;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class WelcomePage extends BasePage {

    private static By acceptBtn = By.id("onetrust-accept-btn-handler");
    private static By homeLogo = By.className("site-branding__logo--full");

    public void goToHomepage() {
        iWebUIElements.openBrowser(appProperties.getUrl());
        iWebUIElements.maximizeBrowser();
    }

    public void clickOnAccept() {
        loadElement(acceptBtn);
        iWebUIElements.click(acceptBtn);
    }

    public void checkHomeLogo() {
        if (iWebUIElements.checkWebElementDisplay(homeLogo)) {
            iWebUIElements.highlightElement(homeLogo);
            System.out.println("the homepage is loaded.");
        }
        else System.out.println("the homepage load failed.");
    }

}


