package com.common.framework.reporter.allure;

import com.common.framework.driver.DriverManager;
import com.common.framework.reporter.ReporterConfig;
import io.qameta.allure.Allure;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.TestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

public class AllureTestLifecycleListener implements TestLifecycleListener {
    @Override
    public void beforeTestStop(TestResult testResult) {
        if (testResult.getStatus() == Status.FAILED || testResult.getStatus() == Status.BROKEN) {
            if (ReporterConfig.getPropertyValue("reporter.platform").equalsIgnoreCase("web")) {
                byte[] screenshotAs = ((TakesScreenshot) DriverManager.getWebDriver()).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Fail Screenshot", new ByteArrayInputStream(screenshotAs));
            } else {
                byte[] screenshotAs = ((TakesScreenshot) DriverManager.getAppiumDriver()).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Fail Screenshot", new ByteArrayInputStream(screenshotAs));
            }
        }
    }
}
