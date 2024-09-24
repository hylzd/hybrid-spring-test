package com.common.framework.common;

import com.common.framework.interfaces.IWebUIElements;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseWebPage extends AppBasePage {

    @Autowired
    public IWebUIElements webUIElements;

    public void openURL() {
        webUIElements.openBrowser(appProperties.getUrl());
    }

    public void openURL(String url) {
        webUIElements.openBrowser(url);
    }

    public void tearDown() {
        webDriver.quit();
    }

}
