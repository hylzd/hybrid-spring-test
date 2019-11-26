package com.mobile.test.framework.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractPage {

    public AbstractPage(AppiumDriver<? extends MobileElement> driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
}
