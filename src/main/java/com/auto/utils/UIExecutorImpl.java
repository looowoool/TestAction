package com.auto.utils;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.auto.object.Locator;

/**
 * UIExecutor接口实现类
 *
 */
public class UIExecutorImpl implements UIExecutor {
    private WebDriver driver;

    public UIExecutorImpl(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * 点击元素
     *
     * @param locator
     */
    public void click(Locator locator) {
        WebElement element = getElement(locator);
        element.click();
    }

    /**
     * 输入文本
     *
     */
    @Override
    public void sendKey(Locator locator, String value) {
        WebElement element = getElement(locator);
        element.clear();
        element.sendKeys(value);
    }

    @Override
    public String getText(Locator locator) {
        WebElement element = getElement(locator);
        return element.getText();
    }

    /**
     * 获取元素
     *
     *
     */
    @Override
    public WebElement getElement(Locator locator) {
        WebElement element = null;
        String address = locator.getAddress();
//		long tinkTime = locator.getWaitSec() * 1000;
//		try {
//			// 思考时间，等待元素加载
//			Thread.sleep(tinkTime);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
        switch (locator.getByType()) {
            case xpath:
                element = driver.findElement(By.xpath(address));
                break;
            case id:
                element = driver.findElement(By.id(address));
                break;
            case className:
                element = driver.findElement(By.className(address));
                break;
            case linkText:
                element = driver.findElement(By.linkText(address));
                break;
            case name:
                element = driver.findElement(By.name(address));
                break;
            default:
                break;
        }
        return element;
    }

    /**
     * 元素是否显示
     *
     */
    @Override
    public boolean isElementDisplayed(Locator locator) {
        boolean flag = false;
        WebElement element = getElement(locator);
        flag = element.isDisplayed();
        return flag;
    }

    /**
     * 切换窗口
     *
     */
    @Override
    public void switchWindow(String title) {
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            if (handle.equals(driver.getWindowHandle())) {
                continue;
            } else {
                driver.switchTo().window(handle);
                if (title.contains(driver.getTitle())) {
                    break;
                } else {
                    continue;
                }
            }
        }
    }

    /**
     * 切换frame
     *
     */
    @Override
    public void switchFrame(Locator locator) {
        driver.switchTo().frame(locator.getAddress());
    }

    /**
     * 智能等待，超过该时长抛出异常
     *
     */
    @Override
    public void waitElement(Locator locator) {
        // TODO Auto-generated method stub
        try {
            Thread.sleep(locator.getWaitSec()*500);
        } catch (InterruptedException e) {
            LogUtil.error(e.getMessage());
        }
    }

    /**
     * 回到第一个标签
     */
    @Override
    public void toFirstLabel() {
        Set<String> winHandels = driver.getWindowHandles(); // 得到当前窗口的set集合
        List<String> it = new ArrayList<String>(winHandels); // 将set集合存入list对象
        driver.switchTo().window(it.get(0));
    }

    /**
     * @return 得到标题
     */
    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    /**
     *
     * @return 得到网址
     */
    @Override
    public String getUrl() {
        return driver.getCurrentUrl();
    }

}

