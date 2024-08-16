package com.common.framework.tests;

import com.common.framework.pages.ToutiaoFirstPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@SpringBootTest
public class MobileTestRunner extends AbstractTestNGSpringContextTests {

    @Autowired
    protected ToutiaoFirstPage toutiaoFirstPage;

    @Test
    public void oneSearch() {
        toutiaoFirstPage.inputSearchText("test");
        toutiaoFirstPage.doSearch();
    }
}
