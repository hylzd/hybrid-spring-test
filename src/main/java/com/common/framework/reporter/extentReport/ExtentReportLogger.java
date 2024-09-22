package com.common.framework.reporter.extentReport;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.common.framework.reporter.ReporterConfig;
import com.common.framework.utils.ScreenshotUtil;

import java.io.IOException;

public class ExtentReportLogger {
    public static <E> void logPass(E e, String message) throws IOException {
        if (ReporterConfig.getPropertyValue("extentReport.passed_screenshots").equalsIgnoreCase("yes")) {
            ExtentReportManager.getExtentTest().pass(message,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtil.captureScreenshotAsBase64(e)).build());
        } else {
            ExtentReportManager.getExtentTest().pass(MarkupHelper.createLabel(message, ExtentColor.GREEN));
        }
    }

    public static <E> void logFail(E e, String message, Throwable throwable) {
        if (ReporterConfig.getPropertyValue("extentReport.failed_screenshots").equalsIgnoreCase("yes")) {
            ExtentReportManager.getExtentTest()
                    .fail(MarkupHelper.createLabel(message, ExtentColor.RED))
                    .fail(message, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtil.captureScreenshotAsBase64(e)).build())
                    .fail(throwable);
        } else {
            ExtentReportManager.getExtentTest().fail(message).fail(throwable);
        }
    }
}
