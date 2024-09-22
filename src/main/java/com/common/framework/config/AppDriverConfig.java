package com.common.framework.config;

import com.common.framework.driver.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@Slf4j
@Configuration
public class AppDriverConfig {

    @Autowired
    private AppProperties appProperties;

    @Bean
    @ConditionalOnExpression("'${app.properties.appType}'.equals('edge')")
    @Scope("driverScope")
    public WebDriver getEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        log.info("initiate web driver...");
        WebDriver driver = new EdgeDriver(getEdgeOptions());
        DriverManager.setWebDriver(driver);
        return driver;
    }

    public EdgeOptions getEdgeOptions() {
        var edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--start-maximized");
        return edgeOptions;
    }

    @Bean
    @ConditionalOnExpression("'${app.properties.appType}'.equals('android')")
    @Scope("driverScope")
    public AppiumDriver getAppiumDriver() throws MalformedURLException {
        UiAutomator2Options uiAutomator2Options = new UiAutomator2Options()
                .setDeviceName(appProperties.getDeviceName())
                .setNoReset(true)
                .setApp(appProperties.getAppPackage());
        log.info("initiate android driver...{}", appProperties.getAppiumURL());
        //return new AppiumDriver( new URL(appProperties.getAppiumURL()), uiAutomator2Options);
        AppiumDriver appiumDriver = new AppiumDriver(new URL(appProperties.getAppiumURL()), uiAutomator2Options);
        DriverManager.setAppiumDriver(appiumDriver);
        return appiumDriver;
    }

    @Bean
    @ConditionalOnExpression("'${app.properties.appType}'.equals('android')")
    @Scope("driverScope")
    public WebDriverWait androidElementWait() throws MalformedURLException {
        getAppiumDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(appProperties.getImplicitTime()));
        return new WebDriverWait(getAppiumDriver(), Duration.ofSeconds(appProperties.getExplicitTime()));
    }
}
