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
        return new Object[][]{{"li","1111","用户名或密码错误"},{"li","","请输入登录密码！"},{"","123456","请输入登录账户！"}};
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
        login.login(username, pwd);
        String tip = new BasePage(driver, "loginPage").getText("错误提示");
        Assert.assertEquals(tip, expectedTip);
    }

    @Test(dependsOnMethods = "login02",description = "正常用户登录")
    @Parameters({ "username", "pwd" })
    public void login(String username,String pwd) throws Exception {
        login.login(username, pwd);
        String tip = new BasePage(driver, "mainPage").getText("客服热线");
        Assert.assertEquals(tip, "客服热线：0755-2309674");

    }


    @AfterClass
    public void afterClass() {

    }
    @AfterTest
    public void afterTest(){
        driver.quit();
    }
}

