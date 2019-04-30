package com.jaque.factory;

import com.jaque.Element;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Field;

public class PageTemp {
    protected WebDriver driver;

    public PageTemp(WebDriver driver) {
        this.driver = driver;
    }

    protected void setDriver() {
        Field[] field = this.getClass().getDeclaredFields();
        for(int j=0 ; j<field.length ; j++){ //遍历所有属性
            //String name = field[j].getName(); //获取属性的名字
            //System.out.println("attribute name:"+name);
            //name = name.substring(0,1).toUpperCase()+name.substring(1); //将属性的首字符大写，方便构造get，set方法
            String type = field[j].getGenericType().toString(); //获取属性的类型
            //System.out.println("attribute type:"+type);
            if(type.equals("class com.jaque.Element")){ //如果type是类类型，则前面包含"class "，后面跟类名
                try {
                    //System.out.println(this);
                    //System.out.println(field[j].get(this));
                    Element e = (Element) field[j].get(this);
                    e.driver(driver);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
