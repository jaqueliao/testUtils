package com.jaque;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装此类是由于在一个Test中，可能会有多次assert的动作，但是只要第
 * 一个assert失败，后续的操作及assert都不会执行。
 * 使用此Assertion的用例中间的所有assert都不会导致用例停止执行，只会
 * 抛出异常，只有在调用了assertEnd()方法时，若失败才会停止执行。
 *
 * 所以任何用例使用Assertion进行断言时，在用例最后一个断言处一定要增
 * 加一次assertEnd()掉用。
 * 如：
 * ...dosomething
 * Assertion.start().assertTitle(driver,"expectTitle")
 * ...dosomething
 * Assertion.assertLocation(driver,"expectUrl").end();//最后一次断言
 */
public class Assertion {

    //适配多用例同时调用，为每个用例生成Assertion实例
    private static Map<String, Assertion> assertMap = new HashMap<String, Assertion>();

    private boolean flag=true;

    /**
     * 获取测试用例方法名
     * @return 测试用例方法名
     */
    private static String getTestName(){
        //必须保证此方法是第二层调用，否则获取的方法名会有错误
        StackTraceElement[] stacks = (new Throwable()).getStackTrace();
        String testName = stacks[2].getClassName() + "-" + stacks[2].getMethodName();
        //System.out.println("getTestName:"+testName);
        return testName;
    }

    /**
     * 获取对应用例的断言实例
     * @return 对应用例的断言实例
     */
    private static Assertion getTestAssertion(){
        //必须保证此方法是第二层调用，否则获取的方法名会有错误
        StackTraceElement[] stacks = (new Throwable()).getStackTrace();
        String testName = stacks[2].getClassName() + "-" + stacks[2].getMethodName();
        //System.out.println("getTestAssertion:"+testName);
        Assertion assertion = assertMap.get(testName);
        if(null == assertion){
            assertion = new Assertion();
            assertMap.put(testName,assertion);
            assertion.flag = true;
        }
        return assertion;
    }

    /**
     * 用例开始，初始化此用例的断言实例
     * @return 返回空，链式调用
     */
    public static Assertion start(){
        Assertion assertion = new Assertion();
        assertMap.put(getTestName(),assertion);
        assertion.flag = true;
        return null;
    }

    //仅供测试用
    public static Assertion forTest(){
        try{
            Assert.assertTrue(true);
        }catch (Error e) {
            e.printStackTrace();
            getTestAssertion().flag = false;
        }
        return null;
    }

    /**
     * 用例结束断言
     */
    public static void end(){
        try{
            Assert.assertTrue(getTestAssertion().flag);
        }finally {
            assertMap.remove(getTestName());
        }
    }

    /**
     * 断言字符串包含
     * @param actual 实际值
     * @param expected 期望值
     * @param message 附带信息
     * @return 返回空，链式调用
     */
    public static Assertion assertContainStr(String actual,String expected, String message){
        try{
            Assert.assertTrue(actual.contains(expected),message);
        }catch (Error e) {
            e.printStackTrace();
            getTestAssertion().flag = false;
        }
        return null;
    }

    /**
     * 断言字符串相等
     * @param actual 实际值
     * @param expected 期望值
     * @param message 附带信息
     * @return 返回空，链式调用
     */
    public static Assertion assertEquals(String actual,String expected, String message){
        try{
            Assert.assertEquals(actual, expected, message);
        }catch (Error e) {
            e.printStackTrace();
            getTestAssertion().flag = false;
        }
        return null;
    }

    /**
     * 断言浏览器url
     * @param driver 浏览器
     * @param url 期望url
     * @return 返回空，链式调用
     */
    public static Assertion assertUrl(WebDriver driver, String url){
        String actualUrl = driver.getCurrentUrl();
        return assertEquals(actualUrl,url, "断言浏览器url："+actualUrl+"等于"+url);
    }

    /**
     * 断言浏览器url，并可带上描述信息
     * @param driver 浏览器
     * @param url 期望url
     * @param message 描述信息
     * @return 返回空，链式调用
     */
    public static Assertion assertUrl(WebDriver driver, String url,String message){
        String actualUrl = driver.getCurrentUrl();
        return assertEquals(actualUrl,url, message);
    }

    /**
     * 断言浏览器url包含某个字符串
     * @param driver 浏览器
     * @param str 期望url包含的字符串
     * @return 返回空，链式调用
     */
    public static Assertion assertUrlContainStr(WebDriver driver, String str){
        String url = driver.getCurrentUrl();
        return assertContainStr(url, str, "断言url：" + url + "包含文本：" + str);
    }

    /**
     * 断言页面页面标题
     * @param driver 浏览器
     * @param title 页面标题
     * @return 返回空，链式调用
     */
    public static Assertion assertTitle(WebDriver driver, String title){
        String actualTitle = driver.getTitle();
        return assertEquals(actualTitle,title, "断言浏览器url："+actualTitle+"等于"+title);
    }

    /**
     * 断言页面页面标题，并可带上描述信息
     * @param driver 浏览器
     * @param title 页面标题
     * @param message 描述信息
     * @return 返回空，链式调用
     */
    public static Assertion assertTitle(WebDriver driver, String title,String message){
        String actualTitle = driver.getTitle();
        return assertEquals(actualTitle,title, message);
    }

    /**
     * 断言页面页面标题包含字符串str
     * @param driver 浏览器
     * @param str 包含的字符串
     * @return 返回空，链式调用
     */
    public static Assertion assertTitleContainStr(WebDriver driver, String str){
        String title = driver.getTitle();
        return assertContainStr(title, str, "断言页面标题：" + title + "包含文本：" + str);
    }

    /**
     * 断言元素的value值
     * @param element 元素
     * @param value value值
     * @return 返回空，链式调用
     */
    public static Assertion assertValue(WebElement element,String value){
        String actualValue = element.getAttribute("value");
        return assertEquals(actualValue,value, "断言元素："+element+"的value值等于："+value);
    }

    /**
     * 断言元素的value值包含str
     * @param element 元素
     * @param str 包含的字符串
     * @return 返回空，链式调用
     */
    public static Assertion assertValueContainStr(WebElement element,String str){
        assertContainStr(element.getAttribute("value"), str,"断言元素："+element+"的value值包含："+str);
        return null;
    }

    /**
     * 断言元素的value值
     * @param element 元素
     * @param value value值
     * @return 返回空，链式调用
     */
    public static Assertion assertValue(Element element,String value){
        return assertValue(element.getElement(),value);
    }

    /**
     * 断言元素的value值包含str
     * @param element 元素
     * @param str 包含的字符串
     * @return 返回空，链式调用
     */
    public static Assertion assertValueContainStr(Element element,String str){
        return assertValueContainStr(element.getElement(),str);
    }



    /**
     * assertSelected（检查select的下拉菜单中选中是否正确）、
     * assertSelectedOptions（检查下拉菜单中的选项的是否正确）、
     * assertText（检查指定元素的文本）、
     * assertTextPresent（检查在当前给用户显示的页面上是否有出现指定的文本）、
     * assertTextNotPresent（检查在当前给用户显示的页面上是否没有出现指定的文本）、
     * assertAttribute（检查当前指定元素的属性的值）、
     * assertTable（检查table里的某个cell中的值）、
     * assertEditable（检查指定的input是否可以编辑）、
     * assertNotEditable（检查指定的input是否不可以编辑）、
     * assertAlert（检查是否有产生带指定message的alert对话框）、
     * waitForElementPresent （等待检验某元素的存在。为真时，则执行。)
     */


}
