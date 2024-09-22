package com.common.framework.pages;

import com.common.framework.common.BaseMobilePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

@Component
public class ToutiaoFirstPage extends BaseMobilePage {

    @AndroidFindBy(id = "com.ss.android.article.news:id/h6m")
    private WebElement searchBox;

    @AndroidFindBy(id = "com.ss.android.article.news:id/d0")
    private WebElement searchBlank;

    @AndroidFindBy(id = "com.ss.android.article.news:id/e2")
    private WebElement searchBtn;

    @Step("input search text")
    public void inputSearchText(String str) {
        mobileUIElements.click(searchBox);
        mobileUIElements.inputText(searchBlank, str);
    }

    @Step("click on search button")
    public void doSearch() {
        mobileUIElements.click(searchBtn);
    }

}
