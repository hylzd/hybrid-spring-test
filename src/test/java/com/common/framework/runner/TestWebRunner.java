package com.common.framework.runner;

import com.common.framework.driver.DriverManager;
import org.testng.annotations.AfterTest;

public class TestWebRunner extends TestBaseRunner {
    @AfterTest
    public void quit() {
        DriverManager.getWebDriver().quit();
    }
}
