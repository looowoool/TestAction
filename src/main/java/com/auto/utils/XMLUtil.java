package com.auto.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.auto.object.Locator;
import com.auto.object.Locator.ByType;

public class XMLUtil {

    /**
     * 读取页面配置文件
     *
     * @author ljl
     * @param xmlUrl
     *            页面配置文件路径
     * @param pageName
     *            页面名称
     */
    public static HashMap<String, Locator> readXMLDocument(String xmlUrl, String pageName) throws Exception {
        HashMap<String, Locator> locatorMap = new HashMap<>();
        File file = new File(xmlUrl);
        if (!file.exists()) {
            LogUtil.error("can't find " + xmlUrl);
        } else {
            // 创建SAXReader对象
            SAXReader sr = new SAXReader();
            // 读取xml文件转换为Document
            Document document = sr.read(file);
            // 获取所有根节点元素对象
            Element root = document.getRootElement();
            Iterator<?> rootIte = root.elementIterator();
            Locator locator = null;
            // 遍历根节点
            while (rootIte.hasNext()) {
                Element page = (Element) rootIte.next();
                LogUtil.info("pageName is " + pageName);
                // 忽略大小写比较
                if (page.attribute(0).getValue().equalsIgnoreCase(pageName)) {
                    Iterator<?> pageIte = page.elementIterator();
                    // 找到pageName后遍历该page内各个节点
                    while (pageIte.hasNext()) {
                        String type = "";
                        String timeOut = "3";
                        String value = "";
                        String locatorName = "";
                        Element locatorEle = (Element) pageIte.next();
                        Iterator<?> locatorIte = locatorEle.attributeIterator();
                        // 遍历单个标签内的元素
                        while (locatorIte.hasNext()) {
                            Attribute attribute = (Attribute) locatorIte.next();
                            String attributeName = attribute.getName();
                            if (attributeName.equals("type")) {
                                type = attribute.getValue();
                            } else if (attributeName.equals("timeOut")) {
                                timeOut = attribute.getValue();
                            } else {
                                value = attribute.getValue();
                            }
                        }
                        locator = new Locator(value, Integer.parseInt(timeOut), getByType(type));
                        locatorName = locatorEle.getText();
                        locatorMap.put(locatorName, locator);

                    }
                    break;
                }
            }
        }
        return locatorMap;
    }

    /**
     * 转换元素定位类型
     *
     */
    public static ByType getByType(String type) {
        ByType byType = ByType.xpath;
        if (type == null || type.equalsIgnoreCase("xpath")) {
            byType = ByType.xpath;
        } else if (type.equalsIgnoreCase("id")) {
            byType = ByType.id;
        } else if (type.equalsIgnoreCase("name")) {
            byType = ByType.name;
        } else if (type.equalsIgnoreCase("className")) {
            byType = ByType.className;
        } else if (type.equalsIgnoreCase("linkText")){
            byType = ByType.linkText;
        } else if (type.equalsIgnoreCase("by")){
            byType = ByType.by;
        }
        return byType;
    }
}

