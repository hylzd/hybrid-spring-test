package com.common.framework.interfaces;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface IWebUIElements {

    void openBrowser(String url);

    void navigateTo(String text);

    void highlightElement(WebElement webElement);

    void inputText(WebElement webElement, String string);

    void click (WebElement webElement);

    boolean checkWebElementDisplay(WebElement webElement);

    void loadWebElement(WebElement webElement);

    void moveToElement(WebElement webElement);

}
