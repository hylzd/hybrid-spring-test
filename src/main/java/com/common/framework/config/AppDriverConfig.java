package com.common.framework.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@Configuration
public class AppDriverConfig {

    @Autowired
    private AppProperties appProperties;

    @Bean
    @ConditionalOnExpression("'${app.properties.appType}'.equals('edge')")
    @Scope("driverScope")
    public WebDriver getEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        System.out.println("Step >> web driver management...");
        return new EdgeDriver();
    }

    @Bean
    @ConditionalOnExpression("'${app.properties.appType}'.equals('edge')")
    @Scope("driverScope")
    public WebDriverWait webElementWait() {
        getEdgeDriver().manage().timeouts().implicitlyWait(appProperties.getImplicitTime());
        return new WebDriverWait(getEdgeDriver(), appProperties.getExplicitTime());
    }

    @Bean
    @ConditionalOnExpression("'${app.properties.appType}'.equals('android')")
    @Scope("driverScope")
    public AppiumDriver getAppiumDriver() throws MalformedURLException {
        UiAutomator2Options uiAutomator2Options = new UiAutomator2Options()
                .setDeviceName(appProperties.getDeviceName())
                .setNoReset(true)
                .setApp(appProperties.getAppPackage());
        System.out.println("Step >> android driver management..." + appProperties.getAppiumURL());
        return new AppiumDriver( new URL(appProperties.getAppiumURL()), uiAutomator2Options);
    }

    @Bean
    @ConditionalOnExpression("'${app.properties.appType}'.equals('android')")
    @Scope("driverScope")
    public WebDriverWait androidElementWait() throws MalformedURLException {
        getAppiumDriver().manage().timeouts().implicitlyWait(appProperties.getImplicitTime());
        return new WebDriverWait(getAppiumDriver(), appProperties.getExplicitTime());
    }

}
