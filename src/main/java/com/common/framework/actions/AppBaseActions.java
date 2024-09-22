package com.common.framework.actions;

import com.common.framework.config.AppProperties;
import com.common.framework.utils.ScreenshotUtil;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class AppBaseActions {

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

    @Autowired
    public ScreenshotUtil screenshotUtil;

}
