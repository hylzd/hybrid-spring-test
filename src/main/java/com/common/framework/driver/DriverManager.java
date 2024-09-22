package com.common.framework.driver;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;

public class DriverManager {
    private final static ThreadLocal<WebDriver> localWebdriver = new ThreadLocal<>();
    private final static ThreadLocal<AppiumDriver> localAppiumDriver = new ThreadLocal<>();

    public static WebDriver getWebDriver() {
        return localWebdriver.get();
    }

    public static AppiumDriver getAppiumDriver() {
        return localAppiumDriver.get();
    }

    public static void setWebDriver(WebDriver webDriver) {
        localWebdriver.set(webDriver);
    }

    public static void setAppiumDriver(AppiumDriver appiumDriver) {
        localAppiumDriver.set(appiumDriver);
    }

    public static void quitWebDriver() {
        localWebdriver.get().quit();
        localWebdriver.remove();
    }

    public static void quitAppiumDriver() {
        localAppiumDriver.get().quit();
        localAppiumDriver.remove();
    }
}
