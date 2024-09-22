package com.common.framework.reporter.extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.common.framework.reporter.ReporterConfig;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class ExtentReportManager {

    private static final String EXTENT_REPORT_LOCAL_RESOURCES = ReporterConfig.getPropertyValue("extentReport.resources_path");
    private static final String HREF_REGEX_PATTERN = ReporterConfig.getPropertyValue("extentReport.href_regex_pattern");
    private static final String SRC_REGEX_PATTERN = ReporterConfig.getPropertyValue("extentReport.src_regex_pattern");
    private static final String EXTENT_REPORT_FONT_CSS = ReporterConfig.getPropertyValue("extentReport.font_css");
    private static final String EXTENT_REPORT_SPARK_CSS = ReporterConfig.getPropertyValue("extentReport.spark_css");
    private static final String EXTENT_REPORT_JSONTREE_JS = ReporterConfig.getPropertyValue("extentReport.jsontree_js");
    private static final String EXTENT_REPORT_SPARK_JS = ReporterConfig.getPropertyValue("extentReport.spark_js");

    private static final String reportPath = getExtentReportPath();
    private static final ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(reportPath);
    private static final ThreadLocal<ExtentTest> threadLocalExtentTest = new ThreadLocal<>();
    private static ExtentReports extentReports;


    public static void initExtentReport() {

        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        String hostname = ip.getHostName();
        extentReports.setSystemInfo("Host name", hostname);
        extentReports.setSystemInfo("User name", System.getProperty("user.name"));
        extentSparkReporter.config().setDocumentTitle("Extent Report");
        extentSparkReporter.config().setReportName("Automation Test Report");

    }

    static void setExtentTest(ExtentTest testName) {
        threadLocalExtentTest.set(testName);
    }

    public static ExtentTest getExtentTest() {
        return threadLocalExtentTest.get();
    }

    public static void createTest(String testCaseName) {
        setExtentTest(extentReports.createTest(testCaseName));
    }

    public static void flushExtentReport() {
        extentReports.flush();
        changeReport();
    }

    private static String getExtentReportPath() {
        if (ReporterConfig.getPropertyValue("extentReport.override").equalsIgnoreCase("yes")) {
            return ReporterConfig.getPropertyValue("extentReport.path") + File.separator + "index.html";
        } else
            return ReporterConfig.getPropertyValue("extentReport.path") + File.separator + getCurrentDateTime() + File.separator + "index.html";
    }

    private static String getCurrentDateTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd-HH_mm_ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        return dateTimeFormatter.format(localDateTime);
    }

    private static void changeReport() {
        File fileToBeModified = new File(reportPath);
        BufferedReader bufferedReader = null;
        FileWriter fileWriter = null;
        StringBuilder originalContent = new StringBuilder();
        try {
            bufferedReader = new BufferedReader(new FileReader(fileToBeModified));
            String line = bufferedReader.readLine();
            while (line != null) {
                originalContent.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
                if (line != null && line.contains(EXTENT_REPORT_SPARK_CSS))
                    line = StringRegexReplacement(HREF_REGEX_PATTERN, line,
                            "href=\"" + EXTENT_REPORT_LOCAL_RESOURCES + "/" + EXTENT_REPORT_SPARK_CSS + "\"");
                if (line != null && line.contains(EXTENT_REPORT_FONT_CSS))
                    line = StringRegexReplacement(HREF_REGEX_PATTERN, line,
                            "href=\"" + EXTENT_REPORT_LOCAL_RESOURCES + "/" + EXTENT_REPORT_FONT_CSS + "\"");
                if (line != null && line.contains(EXTENT_REPORT_JSONTREE_JS))
                    line = StringRegexReplacement(SRC_REGEX_PATTERN, line,
                            "src=\"" + EXTENT_REPORT_LOCAL_RESOURCES + "/" + EXTENT_REPORT_JSONTREE_JS + "\"");
                if (line != null && line.contains(EXTENT_REPORT_SPARK_JS))
                    line = StringRegexReplacement(SRC_REGEX_PATTERN, line,
                            "src=\"" + EXTENT_REPORT_LOCAL_RESOURCES + "/" + EXTENT_REPORT_SPARK_JS + "\"");
            }
            fileWriter = new FileWriter(fileToBeModified);
            fileWriter.write(originalContent.toString());
        } catch (Exception e) {
            log.error("Read report file failed...");
        } finally {
            try {
                assert bufferedReader != null;
                bufferedReader.close();
                assert fileWriter != null;
                fileWriter.close();
            } catch (Exception e) {
                log.error("Write report file failed...");
            }
        }
    }

    static String StringRegexReplacement(String regexPattern, String inputString, String replacementString) {
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(inputString);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            matcher.appendReplacement(sb, replacementString);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

}