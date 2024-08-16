package com.common.framework.utils;

import com.common.framework.interfaces.IWebUIElements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.stereotype.Component;

@Component
public class WebUIElements extends AppBaseUtil implements IWebUIElements {

    @Override
    public void openBrowser(String url) {
        webDriver.get(url);
    }

    @Override
    public void maximizeBrowser() {
        webDriver.manage().window().maximize();
    }

    @Override
    public void navigateTo(String text) {
        String xpath = "'" + text + "'";
        WebElement webElement = webDriver.findElement(By.xpath("//a[text() = "+xpath+"]"));
        webElement.click();
    }

    @Override
    /*public void highlightElement(By by) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');", webDriver.findElement(by));
    }*/
    public void highlightElement(By by) {
        JavascriptExecutor js = (JavascriptExecutor) applicationContext.getBean(WebDriver.class);
        js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');", webDriver.findElement(by));
    }

    @Override
    public void click(By by) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(by)))
                .click();
    }

    @Override
    public boolean checkWebElementDisplay(By by) {
        if (webDriver.findElement(by).isDisplayed()) {
            return true;
        } else {
            System.out.println("the web element is not displayed");
            return false;
        }
    }

}
