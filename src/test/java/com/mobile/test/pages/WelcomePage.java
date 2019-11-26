package com.mobile.test.pages;

import com.mobile.test.framework.base.AbstractPage;
import com.mobile.test.framework.driver.DriverHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WelcomePage extends AbstractPage {

    public WelcomePage(AppiumDriver<? extends MobileElement> driver) {
        super(driver);
    }

    @Autowired
    private DriverHelper driverHelper;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id, 'id/bnl')]")
    private MobileElement toutiaoSearch_Elm;

    @AndroidFindBy(xpath = "//android.widget.EditText[contains(@resource-id, 'id/v0')]")
    private MobileElement toutiaoSearch_Inp;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id, 'id/ux')]")
    private MobileElement toutiaoSearch_Btn;

    public void inputSearchText(String searchText) {
        driverHelper.clickButton(toutiaoSearch_Elm);
        driverHelper.typeValue(toutiaoSearch_Inp, searchText);
    }

    public void clickSearchButton() {
        driverHelper.clickButton(toutiaoSearch_Btn);
    }
}
