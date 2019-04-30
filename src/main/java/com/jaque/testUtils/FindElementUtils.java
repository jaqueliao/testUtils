package com.jaque.testUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.List;

import static com.jaque.testUtils.ElementUtils.isElementPresent;

public class FindElementUtils {

    private static void waitForElementPresent(WebDriver driver,By by) {
        int time = 1;
        while(!isElementPresent(driver,by)){
            if(time >3){
                throw new TimeoutException("查找超时，无法定位到元素！");
            }
            DriverUtils.log("未找到元素，等待"+time+"s后重试...");
            try {Thread.sleep(1000*time); } catch (InterruptedException e) {e.printStackTrace();}
            time++;
        }
    }

    private static WebElement findElement(WebDriver driver, By by){
        waitForElementPresent(driver,by);
        return driver.findElement(by);
    }

    private static List<WebElement> findElements(WebDriver driver, By by){
        waitForElementPresent(driver,by);
        return driver.findElements(by);
    }
    private static WebElement findElement(WebDriver driver, By by, int i){
        waitForElementPresent(driver,by);
        int time = 1;
        while(i > driver.findElements(by).size()-1){
            if(time >5){
                throw new TimeoutException("查找超时，无法定位到元素！");
            }
            Reporter.log("未找到元素，等待"+time+"s后重试...",true);
            try {Thread.sleep(1000*time); } catch (InterruptedException e) {e.printStackTrace();}
            time++;
        }
        return driver.findElements(by).get(i);
    }

    public static WebElement findByXpath(WebDriver driver,String xpath){
        return findElement(driver,By.xpath(xpath));
    }

    public static List<WebElement> findAllByXpath(WebDriver driver, String xpath){
        return findElements(driver,By.xpath(xpath));
    }

    public static WebElement findByXpath(WebDriver driver,String xpath, int i){
        return findElement(driver,By.xpath(xpath),i);
    }

    public static WebElement findByText(WebDriver driver,String text){
        return findByXpath(driver,"//*[text()='"+ text +"']");
    }

    public static List<WebElement> findAllByText(WebDriver driver, String text){
        return findAllByXpath(driver,"//*[text()='"+ text +"']");
    }

    public static WebElement findByText(WebDriver driver,String text, int i){
        return findByXpath(driver,"//*[text()='"+ text +"']",i);
    }
    public static WebElement findByContainText(WebDriver driver,String text){
        return findByXpath(driver,"//*[contains(text(),'"+text+"')]");
    }

    public static List<WebElement> findAllByContainText(WebDriver driver, String text){
        return findAllByXpath(driver,"//*[contains(text(),'"+text+"')]");
    }

    public static WebElement findByContainText(WebDriver driver,String text, int i){
        return findByXpath(driver,"//*[contains(text(),'"+text+"')]",i);
    }

    public static WebElement findByText(WebDriver driver, String text, String tagName){
        return findByXpath(driver,"//"+tagName+"[text()='"+ text +"']");
    }

    public static List<WebElement> findAllByText(WebDriver driver, String text, String tagName){
        return findAllByXpath(driver,"//"+tagName+"[text()='"+ text +"']");
    }

    public static WebElement findByText(WebDriver driver,String text, String tagName, int i){
        return findByXpath(driver,"//"+tagName+"[text()='"+ text +"']",i);
    }
    public static WebElement findByContainText(WebDriver driver,String text, String tagName){
        return findByXpath(driver,"//"+tagName+"[contains(text(),'"+text+"')]");
    }

    public static List<WebElement> findAllByContainText(WebDriver driver, String text, String tagName){
        return findAllByXpath(driver,"//"+tagName+"[contains(text(),'"+text+"')]");
    }

    public static WebElement findByContainText(WebDriver driver,String text, String tagName, int i){
        return findByXpath(driver,"//"+tagName+"[contains(text(),'"+text+"')]",i);
    }

    public static WebElement findByClassName(WebDriver driver,String className){
        return findElement(driver,By.className(className));
    }

    public static List<WebElement> findAllByClassName(WebDriver driver, String className){
        return findElements(driver,By.className(className));
    }

    public static WebElement findByClassName(WebDriver driver,String className, int i){
        return findElement(driver,By.className(className),i);
    }

    public static WebElement findById(WebDriver driver,String id){
        return findElement(driver,By.id(id));
    }

    public static WebElement findByName(WebDriver driver,String name){
        return findElement(driver,By.name(name));
    }

    public static List<WebElement> findAllByName(WebDriver driver, String name){
        return findElements(driver,By.name(name));
    }

    public static WebElement findByName(WebDriver driver,String name, int i){
        return findElement(driver,By.name(name),i);
    }

    public static WebElement findByCss(WebDriver driver,String cssSeletor){
        return findElement(driver,By.cssSelector(cssSeletor));
    }

    public static List<WebElement> findAllByCss(WebDriver driver, String cssSelector){
        return findElements(driver,By.cssSelector(cssSelector));
    }

    public static WebElement findByCss(WebDriver driver,String cssSelector, int i){
        return findElement(driver,By.cssSelector(cssSelector),i);
    }


    private static void waitForElementPresent(WebElement pelement,By by) {
        int time = 1;
        while(!isElementPresent(pelement,by)){
            if(time >3){
                throw new TimeoutException("查找超时，无法定位到元素！");
            }
            DriverUtils.log("未找到元素，等待"+time+"s后重试...");
            try {Thread.sleep(1000*time); } catch (InterruptedException e) {e.printStackTrace();}
            time++;
        }
    }

    private static WebElement findElement(WebElement pelement, By by){
        waitForElementPresent(pelement,by);
        return pelement.findElement(by);
    }

    private static List<WebElement> findElements(WebElement pelement, By by){
        waitForElementPresent(pelement,by);
        return pelement.findElements(by);
    }

    private static WebElement findElement(WebElement pelement, By by, int i){
        waitForElementPresent(pelement,by);
        int time = 1;
        while(i > pelement.findElements(by).size()-1){
            if(time >5){
                throw new TimeoutException("查找超时，无法定位到元素！");
            }
            Reporter.log("未找到元素，等待"+time+"s后重试...",true);
            try {Thread.sleep(1000*time); } catch (InterruptedException e) {e.printStackTrace();}
            time++;
        }
        return pelement.findElements(by).get(i);
    }

    public static WebElement findByXpath(WebElement pelement,String xpath){
        return findElement(pelement,By.xpath(xpath));
    }

    public static List<WebElement> findAllByXpath(WebElement pelement, String xpath){
        return findElements(pelement,By.xpath(xpath));
    }

    public static WebElement findByXpath(WebElement pelement,String xpath, int i){
        return findElement(pelement,By.xpath(xpath),i);
    }

    public static WebElement findByText(WebElement pelement,String text){
        return findByXpath(pelement,"//*[text()='"+ text +"']");
    }

    public static List<WebElement> findAllByText(WebElement pelement, String text){
        return findAllByXpath(pelement,"//*[text()='"+ text +"']");
    }

    public static WebElement findByText(WebElement pelement,String text, int i){
        return findByXpath(pelement,"//*[text()='"+ text +"']",i);
    }
    public static WebElement findByContainText(WebElement pelement,String text){
        return findByXpath(pelement,"//*[contains(text(),'"+text+"')]");
    }

    public static List<WebElement> findAllByContainText(WebElement pelement, String text){
        return findAllByXpath(pelement,"//*[contains(text(),'"+text+"')]");
    }

    public static WebElement findByContainText(WebElement pelement,String text, int i){
        return findByXpath(pelement,"//*[contains(text(),'"+text+"')]",i);
    }

    public static WebElement findByText(WebElement pelement, String text, String tagName){
        return findByXpath(pelement,"//"+tagName+"[text()='"+ text +"']");
    }

    public static List<WebElement> findAllByText(WebElement pelement, String text, String tagName){
        return findAllByXpath(pelement,"//"+tagName+"[text()='"+ text +"']");
    }

    public static WebElement findByText(WebElement pelement,String text, String tagName, int i){
        return findByXpath(pelement,"//"+tagName+"[text()='"+ text +"']",i);
    }
    public static WebElement findByContainText(WebElement pelement,String text, String tagName){
        return findByXpath(pelement,"//"+tagName+"[contains(text(),'"+text+"')]");
    }

    public static List<WebElement> findAllByContainText(WebElement pelement, String text, String tagName){
        return findAllByXpath(pelement,"//"+tagName+"[contains(text(),'"+text+"')]");
    }

    public static WebElement findByContainText(WebElement pelement,String text, String tagName, int i){
        return findByXpath(pelement,"//"+tagName+"[contains(text(),'"+text+"')]",i);
    }

    public static WebElement findByClassName(WebElement pelement,String className){
        return findElement(pelement,By.className(className));
    }

    public static List<WebElement> findAllByClassName(WebElement pelement, String className){
        return findElements(pelement,By.className(className));
    }

    public static WebElement findByClassName(WebElement pelement,String className, int i){
        return findElement(pelement,By.className(className),i);
    }

    public static WebElement findById(WebElement pelement,String id){
        return findElement(pelement,By.id(id));
    }

    public static WebElement findByName(WebElement pelement,String name){
        return findElement(pelement,By.name(name));
    }

    public static List<WebElement> findAllByName(WebElement pelement, String name){
        return findElements(pelement,By.name(name));
    }

    public static WebElement findByName(WebElement pelement,String name, int i){
        return findElement(pelement,By.name(name),i);
    }

    public static WebElement findByCss(WebElement pelement,String cssSeletor){
        return findElement(pelement,By.cssSelector(cssSeletor));
    }

    public static List<WebElement> findAllByCss(WebElement pelement, String cssSelector){
        return findElements(pelement,By.cssSelector(cssSelector));
    }

    public static WebElement findByCss(WebElement pelement,String cssSelector, int i){
        return findElement(pelement,By.cssSelector(cssSelector),i);
    }
}
