package com.auto.action;

import com.auto.object.BasePage;
import com.auto.utils.LogUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class Banner{

    private WebDriver driver = null;
    private BasePage bannerPage = null;
    private Actions action = null;


    public Banner(WebDriver driver) throws Exception {
        this.driver = driver;
        action = new Actions(driver);
        bannerPage = new BasePage(driver,getClass().getSimpleName());
    }

    public void click(String key,Boolean isToFristLable) throws Exception {
        if (null == bannerPage){
            bannerPage = new BasePage(driver,getClass().getSimpleName());
        }
        bannerPage.click(key);
        LogUtil.getLog4jUtil().info(bannerPage.getTitle());
        if (isToFristLable){
            bannerPage.toFirstLabel();
        }
        Thread.sleep(500);
    }

    public void actionPerform(String key) throws InterruptedException {
        action.clickAndHold(bannerPage.getElement(key)).build().perform();
        Thread.sleep(1000);
    }

}
