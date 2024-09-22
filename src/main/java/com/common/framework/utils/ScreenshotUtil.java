package com.common.framework.utils;

import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.File;

@Slf4j
@Component
public class ScreenshotUtil {

    // common screenshot capture
    public <E> void captureScreenshotAsFile(E e, String filePath) {
        File file = ((TakesScreenshot) e).getScreenshotAs(OutputType.FILE);
        String screenshotPath = "target/screenshots/" + filePath + ".jpeg";
        try {
            FileUtils.copyFile(file, new File(screenshotPath));
        } catch (Exception ex) {
            log.error("capture screenshot fail {}", ex.getMessage());
        }
    }

    // allure screenshot capture
    public <E> void takeScreenshotByAllure(E e, String screenName) {
        byte[] screenshotAs = ((TakesScreenshot) e).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment(screenName, new ByteArrayInputStream(screenshotAs));
    }

    // capture screenshot as base64
    public static <E> String captureScreenshotAsBase64(E e) {
        return ((TakesScreenshot) e).getScreenshotAs(OutputType.BASE64);
    }

}
