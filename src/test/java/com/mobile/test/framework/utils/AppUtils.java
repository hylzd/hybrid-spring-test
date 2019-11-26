package com.mobile.test.framework.utils;

import cucumber.api.java.en_scouse.An;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Assert;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

@Configuration
public class AppUtils {

    @Value("${explicit.wait}")
    public int explicitWaitTime;

    @Value("${implicit.wait}")
    public int implicitWaitTime;

    @Value("${platform.name}")
    private String platformName;

    @Value("${application.path}")
    private String applicationPath;

    @Value("${device.noReset}")
    private boolean isNoReset;

    @Value("${appium.server.host}:${appium.server.port}${appium.server.url}")
    private String appiumUrl;

    @Value("${new.command.timeout}")
    private String newCommandTimeout;

    @Value("${automation.android.instrumentation}")
    private String androidInstrumentation;

    @Value("${platform.android.version}")
    private String androidVersion;

    @Value("${device.android.name}")
    private String androidDeviceName;

    @Value("${android.app.package}")
    private String appPackage;

    @Value("${android.app.activity}")
    private String appActivity;

    private AppiumDriver<? extends MobileElement> driver;

    public AppUtils() {}

    @Bean(destroyMethod = "quit")
    @Scope("cucumber-glue")
    public AppiumDriver<? extends MobileElement> getDriver() {
        try {
            if (platformName.equalsIgnoreCase("ANDROID")) {
                driver = getAndroidDriver();
            } else {
                // tbd
            }
        } catch (WebDriverException var2) {
            var2.printStackTrace();
        }

        return driver;
    }

    private AppiumDriver<? extends MobileElement> getAndroidDriver() {
        return new AndroidDriver(getUrl(), getDesiredCapabilities());
    }

    private DesiredCapabilities getDesiredCapabilities() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        if (!applicationPath.isEmpty()) {
            desiredCapabilities.setCapability(MobileCapabilityType.APP, new File(applicationPath).getAbsolutePath());
        }
        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, isNoReset);
        //desiredCapabilities.setCapability(MobileCapabilityType.CLEAR_SYSTEM_FILES, true);
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, newCommandTimeout);

        switch (platformName.toUpperCase()) {
            case "ANDROID":
                desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, androidInstrumentation);
                desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, androidDeviceName);
                desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, androidVersion);
                //desiredCapabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
                if (applicationPath.isEmpty()) {
                    desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appPackage);
                    desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appActivity);
                }
                break;
            case "IOS":
                // tbd
                break;
            default:
                Assert.fail("Current test platform is NOT supported: " + platformName);
        }
        return desiredCapabilities;
    }

    private URL getUrl() {
        try {
            System.out.println(appiumUrl);
            return new URL(appiumUrl);
        } catch (MalformedURLException e) {
            Assert.fail("Can NOT initiate REST http interface listener URL...");
            return null;
        }
    }
}
