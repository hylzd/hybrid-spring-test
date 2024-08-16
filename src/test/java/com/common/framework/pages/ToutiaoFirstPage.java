package com.common.framework.pages;


import com.common.framework.common.BasePage;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ToutiaoFirstPage extends BasePage {

    private static final By searchBox = By.id("com.ss.android.article.news:id/h6m");
    private static final By searchBlank = By.id("com.ss.android.article.news:id/d0");
    private static final By searchBtn = By.id("com.ss.android.article.news:id/e2");

    @Step("input search text")
    public void inputSearchText(String str) {
        iMobileUIElements.click(searchBox);
        iMobileUIElements.inputText(searchBlank, str);
    }

    @Step("click on search button")
    public void doSearch() {
        iMobileUIElements.click(searchBtn);
    }

}
