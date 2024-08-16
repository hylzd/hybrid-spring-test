package com.common.framework.utils;

import com.common.framework.config.AppProperties;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class AppBaseUtil {

    @Autowired
    @Lazy
    public WebDriver webDriver;

    @Autowired
    @Lazy
    public AppiumDriver appiumDriver;

    @Autowired
    @Lazy
    public WebDriverWait webDriverWait;

    @Autowired
    public AppProperties appProperties;

    @Autowired
    public ApplicationContext applicationContext;

    // to be continue...
    public void loadElement(By by) {
        boolean isWait = false;
        int j = 1;

        if (!StringUtils.equalsIgnoreCase(appProperties.getAppType(), "android")) {
            for (int i = 0; i < appProperties.getTimeout(); i++) {
                try {
                    if (webDriver.findElement(by).isDisplayed()) {
                        System.out.println("loading web element complete...");
                        return;
                    }
                } catch (NoSuchElementException ex) {
                    isWait = true;
                    System.out.println("no such web element has been handled...#" + j++);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (isWait) {
                System.out.println("Timeout...");
            }
        } else {
            for (int i = 0; i < appProperties.getTimeout(); i++) {
                try {
                    if (appiumDriver.findElement(by).isDisplayed()) {
                        System.out.println("loading mobile element complete...");
                        return;
                    }
                } catch (NoSuchElementException ex) {
                    isWait = true;
                    System.out.println("no such mobile element has been handled...#" + j++);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (isWait) {
                System.out.println("Timeout...");
            }
        }
    }
}
