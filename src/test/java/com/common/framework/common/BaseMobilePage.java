package com.common.framework.common;

import com.common.framework.interfaces.IMobileUIElements;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseMobilePage extends AppBasePage {

    @Autowired
    public IMobileUIElements mobileUIElements;

    public void tearDown() {
        appiumDriver.quit();
    }

}
