package com.common.framework.actions;

import com.common.framework.interfaces.IMobileUIElements;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class MobileUIElements extends AppBaseActions implements IMobileUIElements {

    @Override
    public void inputText(WebElement webElement, String string) {
        loadMobileElement(webElement);
        webElement.sendKeys(string);
    }

    @Override
    public void click(WebElement webElement) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement)).click();
    }

    @Override
    public void loadMobileElement(WebElement webElement) {
        boolean isWait = false;
        int j = 1;

        for (int i = 0; i < appProperties.getTimeout(); i++) {
            try {
                if (webElement.isDisplayed()) {
                    log.info("loading mobile element complete...");
                    return;
                }
            } catch (NoSuchElementException ex) {
                isWait = true;
                log.info("no such mobile element has been handled...{}", j++);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    log.error("process is interrupted: {}", e.getMessage());
                }
            }
        }
        if (isWait) {
            log.warn("loading mobile element timeout...");
        }
    }
}
