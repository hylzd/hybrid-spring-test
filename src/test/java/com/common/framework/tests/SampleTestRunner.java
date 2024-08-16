package com.common.framework.tests;

import com.common.framework.config.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@SpringBootTest
public class SampleTestRunner extends AbstractTestNGSpringContextTests {

    @Autowired
    protected ConfigProperties configProperties;

    @Test
    public void sampleTest() {
        System.out.println(configProperties.getUrl());
    }
}
