package com.common.framework.listeners;

import com.common.framework.driver.DriverManager;
import com.common.framework.reporter.ReporterConfig;
import com.common.framework.reporter.extentReport.ExtentReportLogger;
import com.common.framework.reporter.extentReport.ExtentReportManager;
import lombok.extern.slf4j.Slf4j;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Slf4j
public class ExtentReportListener implements ITestListener, ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        ExtentReportManager.initExtentReport();
    }

    @Override
    public void onTestStart(ITestResult testResult) {
        ExtentReportManager.createTest(testResult.getMethod().getMethodName());
        log.info("Extent Report Listener: test start from {}", testResult.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult testResult) {
        try {
            if (ReporterConfig.getPropertyValue("reporter.platform").equalsIgnoreCase("web")) {
                ExtentReportLogger.logPass(DriverManager.getWebDriver(), "Test Passed...");
            } else {
                ExtentReportLogger.logPass(DriverManager.getAppiumDriver(), "Test Passed...");
            }
        } catch (Exception e) {
            log.error("Test fail on...");
        }
    }

    @Override
    public void onTestFailure(ITestResult testResult) {
        if (ReporterConfig.getPropertyValue("reporter.platform").equalsIgnoreCase("web")) {
            ExtentReportLogger.logFail(DriverManager.getWebDriver(), "Test fail...", testResult.getThrowable());
        } else {
            ExtentReportLogger.logFail(DriverManager.getAppiumDriver(), "Test fail...", testResult.getThrowable());
        }
    }

    @Override
    public void onFinish(ISuite suite) {
        ExtentReportManager.flushExtentReport();
    }

}
