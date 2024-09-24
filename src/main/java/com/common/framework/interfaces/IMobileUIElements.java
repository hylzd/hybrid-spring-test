package com.common.framework.interfaces;

import org.openqa.selenium.WebElement;

public interface IMobileUIElements {

    void inputText(WebElement webElement, String string);

    void click(WebElement webElement);

    void loadMobileElement(WebElement webElement);
}
