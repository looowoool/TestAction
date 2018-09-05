package com.auto.object;

import java.util.HashMap;

import com.auto.utils.LogUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.auto.utils.UIExecutorImpl;
import com.auto.utils.XMLUtil;

/**
 * 基础页面类
 *
 */
public class BasePage extends UIExecutorImpl {
    protected WebDriver driver;
    protected String resourcesName;// 页面名称
    protected String xmlPath;// 页面元素配置文件路径
    protected HashMap<String, Locator> locatorMap;//存储页面元素信息

    public BasePage(WebDriver driver, String resourcesName) throws Exception {
        super(driver);
        this.driver = driver;
        this.resourcesName = resourcesName;
        // data.xml路径，data.xml在同级目录
        xmlPath = this.getClass().getClassLoader().getResource("").getPath() + resourcesName +".xml";
        LogUtil.info(xmlPath + ";resourcesName=" +resourcesName);
        locatorMap = XMLUtil.readXMLDocument(xmlPath, resourcesName);
    }

    public BasePage(WebDriver driver, String resourcesName,String locatorName) throws Exception {
        super(driver);
        this.driver = driver;
        this.resourcesName = resourcesName;
        // data.xml路径，data.xml在同级目录
        xmlPath = this.getClass().getClassLoader().getResource("").getPath() + resourcesName +".xml";
        LogUtil.info(xmlPath + ";resourcesName=" +resourcesName + ";locatorName=" + locatorName);
        locatorMap = XMLUtil.readXMLDocument(xmlPath, locatorName);
    }

    public void click(String locatorName) {
        super.click(getLocator(locatorName));
    }

    public void sendKey(String locatorName, String value) {
        super.sendKey(getLocator(locatorName), value);
    }

    public String getText(String locatorName) {
        return super.getText(getLocator(locatorName));
    }

    public WebElement getElement(String locatorName) {
        return super.getElement(getLocator(locatorName));
    }

    public boolean isElementDisplayed(String locatorName) {
        return super.isElementDisplayed(getLocator(locatorName));
    }

    public void switchWindow(String title) {
        super.switchWindow(title);
    }

    public void switchFrame(String locatorName) {
        super.switchFrame(getLocator(locatorName));
    }

    /**
     * 根据locatorName返回对应的locator
     *
     */
    public Locator getLocator(String locatorName) {
        Locator locator = null;
        if (locatorMap != null) {
            locator = locatorMap.get(locatorName);
        }
        return locator;
    }
}

