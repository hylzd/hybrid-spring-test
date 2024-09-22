package com.common.framework.tests;

import com.common.framework.listeners.ExtentReportListener;
import com.common.framework.pages.ToutiaoFirstPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@SpringBootTest
@Listeners(ExtentReportListener.class)
public class MobileTestRunner extends AbstractTestNGSpringContextTests {

    @Autowired
    protected ToutiaoFirstPage toutiaoFirstPage;

    @Test
    public void oneSearch() {
        toutiaoFirstPage.inputSearchText("test");
        toutiaoFirstPage.doSearch();
    }

    @AfterTest
    public void tearDown() {
        toutiaoFirstPage.tearDown();
    }
}
