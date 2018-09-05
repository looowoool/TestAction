package com.auto.utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 监听测试过程
 *
 */
public class TestNGListener extends TestListenerAdapter {

    private static WebDriver driver;

    public static void setDriver(WebDriver driver) {
        TestNGListener.driver = driver;
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        LogUtil.getLog4jUtil().info(tr.getName() + ":Test Success");
        super.onTestSuccess(tr);
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        LogUtil.getLog4jUtil().error(tr.getName() + ":Test Failure");
        super.onTestFailure(tr);
        ScreenShot screenShot = new ScreenShot(driver);
        //获取当前project目录
        String path = System.getProperty("user.dir").replace("\\", "/");
        //加上时间戳以区分截图
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddHHmmss");
        String curTime = ft.format(dNow);
        screenShot.saveScreenShot(path + "/img/", "testFail" + curTime + ".png");
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        LogUtil.getLog4jUtil().error(tr.getName() + ":Test Skipped");
        super.onTestSkipped(tr);
    }

    @Override
    public void onStart(ITestContext testContext) {
        LogUtil.getLog4jUtil().info(testContext.getName() + ":Test Start");
        super.onStart(testContext);
    }

    @Override
    public void onFinish(ITestContext testContext) {
        LogUtil.getLog4jUtil().info(testContext.getName() + ":Test Finish");
        super.onFinish(testContext);
    }

}

