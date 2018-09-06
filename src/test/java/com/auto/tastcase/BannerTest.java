package com.auto.tastcase;

import com.auto.action.Banner;
import com.auto.utils.BrowserUtil;
import com.auto.utils.TestNGListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

@Listeners({TestNGListener.class})
public class BannerTest {

    private WebDriver driver;
    private Banner banner;

    @BeforeClass
    @Parameters({"browserDriverUrl","url"})
    public void beforeClass(String browserDriverUrl,String url) {
        driver = BrowserUtil.chrome(browserDriverUrl,30);
        //在内存中运行，不用启用浏览器，使用该方法无法使用截图功能
        //driver = new HtmlUnitDriver();
        driver.get(url);
    }

    @BeforeMethod
    public void BeforeMethod() throws Exception {
        banner = new Banner(driver);
        TestNGListener.setDriver(driver);
    }

    @Test
    public void click() throws Exception {

        banner.click("室内装修效果图在线设计-酷家乐",false);

        banner.click("免费室内设计在线学习视频教程_酷家乐大学0",false);


//        banner.click("提示框",false);


        banner.actionPerform("免费室内设计在线学习视频教程_酷家乐大学0");
        banner.click("免费室内设计在线学习视频教程_酷家乐大学1",true);


        banner.actionPerform("免费室内设计在线学习视频教程_酷家乐大学0");
        banner.click("酷家乐 - 云设计帮助中心",true);


        banner.actionPerform("免费室内设计在线学习视频教程_酷家乐大学0");
        banner.click("户型工具-酷家乐",true);


        banner.click("2018全国装修设计方案大全_装修效果图大全_酷家乐0",false);

        banner.actionPerform("2018全国装修设计方案大全_装修效果图大全_酷家乐0");
        banner.click("2018全国装修设计方案大全_装修效果图大全_酷家乐1",false);


        banner.actionPerform("2018全国装修设计方案大全_装修效果图大全_酷家乐0");
        banner.click("设计大赛_碰撞思想，专注设计",true);

        banner.click("模型库_酷家乐云设计",false);

        banner.click("杭州户型图大全_房型图大全 _酷家乐",false);

        banner.click("到店购 品牌商和设计师的互动平台 酷家乐 新零售 到店购",true);

        banner.click("酷家乐装修设计软件下载_官方软件下载_酷家乐0",false);

        banner.actionPerform("酷家乐装修设计软件下载_官方软件下载_酷家乐0");
        banner.click("酷家乐装修设计软件下载_官方软件下载_酷家乐1",true);

        banner.actionPerform("酷家乐装修设计软件下载_官方软件下载_酷家乐0");
        banner.click("App下载0",true);

        banner.actionPerform("酷家乐装修设计软件下载_官方软件下载_酷家乐0");
        banner.click("KoolVR客户端下载_酷家乐",true);

        banner.actionPerform("酷家乐装修设计软件下载_官方软件下载_酷家乐0");
        banner.click("酷咖名片，设计师的销售利器_酷家乐",true);

        banner.click("室内装修效果图在线设计-酷家乐0",false);

        banner.actionPerform("室内装修效果图在线设计-酷家乐0");
        banner.click("App下载1",true);

        banner.actionPerform("室内装修效果图在线设计-酷家乐0");
        banner.click("室内装修效果图在线设计-酷家乐1",false);

        banner.actionPerform("室内装修效果图在线设计-酷家乐0");
        banner.click("全国户型改造_酷家乐",false);

        banner.actionPerform("室内装修效果图在线设计-酷家乐0");
        banner.click("全国装修答疑_酷家乐",false);

        banner.actionPerform("室内装修效果图在线设计-酷家乐0");
        banner.click("全国装修日记_酷家乐",false);

        banner.actionPerform("室内装修效果图在线设计-酷家乐0");
        banner.click("全国装修攻略_酷家乐",false);

        banner.click("企业版首页",false);

    }

    @AfterClass
    public void afterClass() {

    }

    @AfterTest
    public void afterTest(){
        driver.quit();
    }

}
