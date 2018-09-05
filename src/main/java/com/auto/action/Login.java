package com.auto.action;

import com.auto.object.BasePage;
import org.openqa.selenium.WebDriver;


public class Login {
    private WebDriver driver;
    private BasePage loginPage;

    public Login(WebDriver driver) {
        this.driver = driver;
    }

    // 登录操作
    public void login(String username, String pwd) throws Exception {
        loginPage = new BasePage(driver, "login");//这里的pageName要对应page.xml文件中的page标签值
        loginPage.sendKey("登录输入账号框", username);//登录输入账号框对应的是page.xml中设置的元素名称
        loginPage.sendKey("登录输入密码框", pwd);
        loginPage.click("登录");
    }
}

