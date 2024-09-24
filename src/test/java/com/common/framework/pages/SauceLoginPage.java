package com.common.framework.pages;

import com.common.framework.common.BaseWebPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class SauceLoginPage extends BaseWebPage {

    @FindBy(id = "user-name")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement login;

    @Step("user profile info")
    public void inputUserInfo(String username, String password) {
        webUIElements.inputText(this.username, username);
        webUIElements.inputText(this.password, password);
    }

    @Step("user login submit")
    public void submitLogin() {
        webUIElements.click(login);
    }

}
