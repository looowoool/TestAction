package com.auto.tastcase;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.auto.action.Login;
import com.auto.object.BasePage;
import com.auto.utils.BrowserUtil;
import com.auto.utils.TestNGListener;

@Listeners({TestNGListener.class})
public class LoginTest {

    private WebDriver driver;
    private Login login;

    @DataProvider(name = "loginParams")
    public Object[][] loginParams(){
        return new Object[][]{{"13003697518","","请输入密码"},{"","12345678a","请输入邮箱或者手机号"},{"13003697518","12345678aaaa","账号密码错误"}};
    }

    @BeforeClass
    @Parameters({"browserDriverUrl","url"})
    public void beforeClass(String browserDriverUrl,String url) {
        driver = BrowserUtil.chrome(browserDriverUrl,30);
        //在内存中运行，不用启用浏览器，使用该方法无法使用截图功能
        //driver = new HtmlUnitDriver();
        driver.get(url);
    }

    @BeforeMethod
    public void BeforeMethod(){
        login = new Login(driver);
        TestNGListener.setDriver(driver);
    }


    @Test(dataProvider = "loginParams",description = "异常用户信息登录")
    public void login02(String username,String pwd,String expectedTip) throws Exception{
        login.login(username, pwd,"Login");
        String tip;
        if (username.equals("") || username == null) {
            tip = new BasePage(driver,"Login" ,"loginError").getText("账号错误提示");
        }else {
            tip = new BasePage(driver, "Login","loginError").getText("密码错误提示");
        }
        login.close();
        Thread.sleep(1000);
        Assert.assertEquals(tip, expectedTip);
    }

    @Test(dependsOnMethods = "login02",description = "正常用户登录")
    @Parameters({ "username", "pwd" })
    public void login(String username,String pwd) throws Exception {
        login.login(username, pwd,"Login");
        String tip = new BasePage(driver, "Login").getText("开始设计");
        Assert.assertEquals(tip, "开始设计");
    }


    @AfterClass
    public void afterClass() {

    }
    @AfterTest
    public void afterTest(){
        driver.quit();
    }
}

