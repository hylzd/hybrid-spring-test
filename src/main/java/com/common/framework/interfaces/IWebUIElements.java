package com.common.framework.interfaces;

import org.openqa.selenium.By;

public interface IWebUIElements {

    void openBrowser(String url);

    void maximizeBrowser();

    void navigateTo(String text);

    void highlightElement(By by);

    void click (By by);

    boolean checkWebElementDisplay(By by);

}
