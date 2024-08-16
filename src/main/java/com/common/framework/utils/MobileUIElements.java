package com.common.framework.utils;

import com.common.framework.interfaces.IMobileUIElements;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.stereotype.Component;

@Component
public class MobileUIElements extends AppBaseUtil implements IMobileUIElements {

    @Override
    public void inputText(By by, String string) {
        loadElement(by);
        appiumDriver.findElement(by).sendKeys(string);
    }

    @Override
    public void click(By by) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(appiumDriver.findElement(by)))
                .click();
    }

}
