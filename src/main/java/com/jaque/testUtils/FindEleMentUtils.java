package com.jaque.testUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.List;

import static com.jaque.testUtils.ElementUtils.isElementPresent;

public class FindEleMentUtils {
    private static WebElement element = null;
    private static List<WebElement> elements = null;

    private static void waitForElementPresent(WebDriver driver,By by) {
        int time = 1;
        while(!isElementPresent(driver,by)){
            if(time >4){
                throw new TimeoutException("查找超时，无法定位到元素！");
            }
            Reporter.log("未找到元素，等待"+time+"s后重试...",true);
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
        element = findElement(driver,By.xpath(xpath));
        return element;
    }

    public static List<WebElement> findAllByXpath(WebDriver driver, String xpath){
        elements = findElements(driver,By.xpath(xpath));
        return elements;
    }

    public static WebElement findByXpath(WebDriver driver,String xpath, int i){
        element = findElement(driver,By.xpath(xpath),i);
        return element;
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
        element = findElement(driver,By.className(className));
        return element;
    }

    public static List<WebElement> findAllByClassName(WebDriver driver, String className){
        elements = findElements(driver,By.className(className));
        return elements;
    }

    public static WebElement findByClassName(WebDriver driver,String className, int i){
        element = findElement(driver,By.className(className),i);
        return element;
    }

    public static WebElement findById(WebDriver driver,String id){
        element = findElement(driver,By.id(id));
        return element;
    }

    public static WebElement findByName(WebDriver driver,String name){
        element = findElement(driver,By.name(name));
        return element;
    }

    public static List<WebElement> findAllByName(WebDriver driver, String name){
        elements = findElements(driver,By.name(name));
        return elements;
    }

    public static WebElement findByName(WebDriver driver,String name, int i){
        element = findElement(driver,By.name(name),i);
        return element;
    }

    public static WebElement findByCss(WebDriver driver,String cssSeletor){
        element = findElement(driver,By.cssSelector(cssSeletor));
        return element;
    }

    public static List<WebElement> findAllByCss(WebDriver driver, String cssSelector){
        elements = findElements(driver,By.cssSelector(cssSelector));
        return elements;
    }

    public static WebElement findByCss(WebDriver driver,String cssSelector, int i){
        element = findElement(driver,By.cssSelector(cssSelector),i);
        return element;
    }


}
