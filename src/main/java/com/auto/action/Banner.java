package com.auto.action;

import com.auto.object.BasePage;
import org.openqa.selenium.WebDriver;

public class Banner extends BasePage{

    private WebDriver driver;
    private BasePage loginPage;


    public Banner(WebDriver driver, String pageName) throws Exception {
        super(driver, pageName);
    }


}
