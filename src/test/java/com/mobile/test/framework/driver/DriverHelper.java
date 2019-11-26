package com.mobile.test.framework.driver;

import com.mobile.test.framework.utils.AppUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Component
public class DriverHelper {

    @Autowired
    protected AppUtils appUtils;

    private AppiumDriver<? extends MobileElement> driver;

    public DriverHelper() {}

    public AppiumDriver<? extends MobileElement> getDriver() {
        return driver;
    }

    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void waitForPresent(By by, int timeout) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeout);
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickButton(MobileElement mobileElement) {
        for (int i = 0; i < 30; ++i) {
            boolean clicked = clickButtonWhenItIsPresented(mobileElement);
            if (clicked) { break; }
        }
    }

    private boolean clickButtonWhenItIsPresented(MobileElement mobileElement) {
        boolean isPresented = false;
        try {
            mobileElement.click();
            isPresented = true;
        } catch (NoSuchElementException e) {
            sleep(1000L);
        }

        return isPresented;
    }

    public void typeValue(MobileElement mobileElement, String inputValue) {
        for (int i = 0; i < 30; ++i) {
            boolean typed = typeValueWhenItIsPresented(mobileElement, inputValue);
            if (typed) { break; }
        }
    }

    private boolean typeValueWhenItIsPresented(MobileElement mobileElement, String inputValue) {
        boolean isPresented = false;
        try {
            mobileElement.sendKeys(inputValue);
            isPresented = true;
        } catch (NoSuchElementException e) {
            sleep(1000L);
        }

        return isPresented;
    }
}
