package com.common.framework.actions;

import com.common.framework.interfaces.IWebUIElements;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class WebUIElements extends AppBaseActions implements IWebUIElements {

    @Override
    public void openBrowser(String url) {
        webDriver.get(url);
    }

    @Override
    public void navigateTo(String text) {
        String xpath = "'" + text + "'";
        WebElement webElement = webDriver.findElement(By.xpath("//a[text() = "+xpath+"]"));
        webElement.click();
    }

    @Override
    public void highlightElement(WebElement webElement) {
        JavascriptExecutor js = (JavascriptExecutor) applicationContext.getBean(WebDriver.class);
        js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');", webElement);
    }

    @Override
    public void click(WebElement webElement) {
        webElement.click();
    }

    @Override
    public boolean checkWebElementDisplay(WebElement webElement) {
        if (webElement.isDisplayed()) {
            return true;
        } else {
            log.error("the web element is not displayed");
            return false;
        }
    }

    @Override
    public void loadWebElement(WebElement webElement) {
        boolean isWait = false;
        int j = 1;

        for (int i = 0; i < appProperties.getTimeout(); i++) {
            try {
                if (webElement.isDisplayed()) {
                    log.info("loading web element complete...");
                    return;
                }
            } catch (NoSuchElementException ex) {
                isWait = true;
                log.info("no such web element has been handled...{}", j++);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    log.error("process is interrupted: {}", e.getMessage());
                }
            }
        }
        if (isWait) {
            log.warn("loading web element timeout...");
        }
    }

    @Override
    public void moveToElement(WebElement webElement) {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(webElement).perform();
    }
}
