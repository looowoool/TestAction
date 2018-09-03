package com.auto.object;


/**
 * 封装页面元素，每个元素都有相应的定位地址（xpath路径或css或id）,等待时间，定位方式，默认为By.xpath
 *
 */
public class Locator {
    private String address; // 定位地址
    private int waitSec; // 等待时间
    private ByType byType; // 定位方式

    /**
     * 定位类型枚举
     *
     *
     */
    public enum ByType {
        by, xpath, linkText, id, name, className
    }

    public Locator() {
    }

    /**
     * Locator构造器，默认定位类型By.xpath,等待时长3s
     *
     * @param element
     */
    public Locator(String address) {
        this.address = address;
        this.waitSec = 3;
        this.byType = ByType.xpath;
    }

    public Locator(String address, int waitSec) {
        this.waitSec = waitSec;
        this.address = address;
        this.byType = ByType.xpath;
    }

    public Locator(String address, int waitSec, ByType byType) {
        this.waitSec = waitSec;
        this.address = address;
        this.byType = byType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getWaitSec() {
        return waitSec;
    }

    public void setWaitSec(int waitSec) {
        this.waitSec = waitSec;
    }

    public ByType getByType() {
        return byType;
    }

    public void setByType(ByType byType) {
        this.byType = byType;
    }

}

