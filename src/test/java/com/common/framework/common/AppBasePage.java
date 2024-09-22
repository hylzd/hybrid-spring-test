package com.common.framework.common;

import com.common.framework.actions.AppBaseActions;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import jakarta.annotation.PostConstruct;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class AppBasePage extends AppBaseActions {

    @PostConstruct
    public void initDriver() {
        if (appProperties.getAppType().equalsIgnoreCase("edge"))
            PageFactory.initElements(new AjaxElementLocatorFactory(webDriver, appProperties.getImplicitTime()), this);
        else PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
    }

}
