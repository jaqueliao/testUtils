package com.jaque;

import com.jaque.testUtils.DriverUtils;
import com.jaque.testUtils.TestUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 封装此类是由于在一个Test中，可能会有多次assert的动作，但是只要第
 * 一个assert失败，后续的操作及assert都不会执行。<br/>
 * 使用此Assertion的用例中间的所有assert都不会导致用例停止执行，只会
 * 抛出异常，只有在调用了assertEnd()方法时，若失败才会停止执行。<br/>
 *
 * 所以任何用例使用Assertion进行断言时，在用例最后一个断言处一定要增
 * 加一次end()调用。<br/>
 * 如：<br/>
 * <pre>
 * ...dosomething
 * Assertion.start().assertTitle(driver,"expectTitle")
 * ...dosomething
 * Assertion.assertLocation(driver,"expectUrl").end();//最后一次断言
 * </pre>
 */
public class Assertion {

    //适配多用例同时调用，为每个用例生成一个标识用例状态的flag
    private static Map<String, Boolean> flagMap = new HashMap<>();
    /**
     * 获取测试用例方法名,通过反射验证方法是否有 TestNg 的 @Test 注解，有的化则断定为测试用例，否则继续检测
     * @return 测试用例方法名
     */
    private static String getTestName(){
        String testName = "";
        StackTraceElement[] stacks = (new Throwable()).getStackTrace();
/*        for(StackTraceElement stack :stacks) {
            System.out.println("方法："+stack.getClassName()+"-"+stack.getMethodName());
        }*/
        for(StackTraceElement stack :stacks){
            try{
                Class c = Class.forName(stack.getClassName());
                Method method = c.getDeclaredMethod(stack.getMethodName());
                Annotation[] annotations = method.getAnnotations();
                if (method.isAnnotationPresent(Test.class)) {
                    /*for (Annotation annotation : annotations) {
                        System.out.println("注解："+annotation);
                    }*/
                    testName = stack.getClassName() + "-" + stack.getMethodName();
                    break;
                }
            }catch (Exception e){
                //e.printStackTrace();
            }
        }
        //System.out.println("getTestName:"+testName);
        return testName;
    }

    /**
     * 用例开始，初始化此用例的断言实例
     * @return 返回空，链式调用
     */
    public static Assertion start(){
        flagMap.put(getTestName(),true);
        return null;
    }

    //仅供测试用
    public static Assertion forTest(){
        try{
            Assert.assertTrue(true);
        }catch (Error e) {
            e.printStackTrace();
            flagMap.put(getTestName(),false);
        }
        return null;
    }

    /**
     * 用例结束断言
     */
    public static void end(){
        //System.out.print(getTestAssertion().flag);
        Boolean flag = flagMap.get(getTestName());
        if (null != flag) {
            if (!flag){
                flagMap.remove(getTestName());
                Assert.fail("用例失败！");
            }
        }
    }

    /**
     * 断言字符串包含
     * @param actual 实际值
     * @param expected 期望值
     * @param message 附带信息
     * @return 返回空，链式调用
     */
    public static Assertion assertContainStr(String actual, String expected, String message){
        return assertTrue(actual.contains(expected),message);
    }

    /**
     * 获取异常或错误的详细stack
     * @param t t
     * @return
     */
    private static String getTrace(Throwable t) {
        StringWriter stringWriter= new StringWriter();
        PrintWriter writer= new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer= stringWriter.getBuffer();
        return buffer.toString();
    }

    /**
     * 用例失败状态时截图保存
     */
    private static void saveFaileState() {
        String fileName = "failTest" + TestUtils.getTimeStamp();
        try {
            if (TestUtils.isTestServer()) {
                TestUtils.takeScreenSnap(TestUtils.getJenkinsHome()+"\\webapps\\testOutput\\img\\" + fileName + ".png");
                DriverUtils.log("<img class='pimg' src='/testOutput/img/" + fileName + ".png' width='100'/>");
            } else {
                TestUtils.takeScreenSnap("test-output/img/" + fileName + ".png");
                DriverUtils.log("<img class='pimg' src='../img/" + fileName + ".png' width='100'/>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            //e.printStackTrace();
            DriverUtils.log(getTrace(e));
            flagMap.put(getTestName(),false);
            saveFaileState();
        }
        return null;
    }

    /**
     * 断言值为真true
     * @param actual 实际值
     * @param message 附带信息
     * @return 返回空，链式调用
     */
    public static Assertion assertTrue(boolean actual, String message){
        try{
            Assert.assertTrue(actual, message);
        }catch (Error e) {
            DriverUtils.log(getTrace(e));
            flagMap.put(getTestName(),false);
            saveFaileState();
        }
        return null;
    }
    /**
     * 断言值为假false
     * @param actual 实际值
     * @param message 附带信息
     * @return 返回空，链式调用
     */
    public static Assertion assertFalse(boolean actual, String message){
        try{
            Assert.assertFalse(actual, message);
        }catch (Error e) {
            DriverUtils.log(getTrace(e));
            flagMap.put(getTestName(),false);
            saveFaileState();
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
        return assertEquals(actualUrl,url, "断言浏览器url："+actualUrl+" 等于"+url);
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
        return assertContainStr(url, str, "断言url：" + url + " 包含文本：" + str);
    }

    /**
     * 断言页面页面标题
     * @param driver 浏览器
     * @param title 页面标题
     * @return 返回空，链式调用
     */
    public static Assertion assertTitle(WebDriver driver, String title){
        String actualTitle = driver.getTitle();
        return assertEquals(actualTitle,title, "断言浏览器title："+actualTitle+" 等于"+title);
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
        return assertContainStr(title, str, "断言页面标题：" + title + " 包含文本：" + str);
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
        return assertContainStr(element.getAttribute("value"), str,"断言元素："+element+"的value值包含："+str);
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
     * 断言元素的文本内容
     * @param element 元素
     * @param text 文本
     * @return 返回空，链式调用
     */
    public static Assertion assertText(WebElement element,String text){
        String actualText = element.getText();
        return assertEquals(actualText,text, "断言元素："+element+"的text值等于："+text);
    }

    /**
     * 断言元素的text值包含str
     * @param element 元素
     * @param str 包含的字符串
     * @return 返回空，链式调用
     */
    public static Assertion assertTextContainStr(WebElement element,String str){
        return assertContainStr(element.getText(), str,"断言元素："+element+"的text值:"+element.getText()+"包含："+str);
    }

    /**
     * 断言元素的text值
     * @param element 元素
     * @param text text值
     * @return 返回空，链式调用
     */
    public static Assertion assertText(Element element,String text){
        return assertText(element.getElement(),text);
    }

    /**
     * 断言元素的text值包含str
     * @param element 元素
     * @param str 包含的字符串
     * @return 返回空，链式调用
     */
    public static Assertion assertTextContainStr(Element element,String str){
        return assertTextContainStr(element.getElement(),str);
    }

    /**
     * 断言元素包含class
     * @param element 元素
     * @param className className
     * @return 返回空，链式调用
     */
    public static Assertion assertHasClass(WebElement element, String className) {
        return assertAttributeContainStr(element, "class", className);
    }

    /**
     * 断言元素包含class
     * @param element 元素
     * @param className className
     * @return 返回空，链式调用
     */
    public static Assertion assertHasClass(Element element, String className) {
        return assertAttributeContainStr(element.getElement(), "class", className);
    }
    /**
     * 断言元素的属性值
     * @param element 元素
     * @param name 属性名
     * @param attribute 属性值
     * @return 返回空，链式调用
     */
    public static Assertion assertAttribute(WebElement element, String name, String attribute) {
        return assertEquals(element.getAttribute(name), attribute, "断言元素："+element+"的"+ name +"属性值为:"+ attribute);
    }

    /**
     * 断言元素的属性值包含某个字符串
     * @param element 元素
     * @param name 属性名
     * @param attribute 包含的属性值
     * @return 返回空，链式调用
     */
    public static Assertion assertAttributeContainStr(WebElement element, String name, String attribute) {
        return assertTrue(element.getAttribute(name).contains(attribute) , "断言元素："+element+"的"+ name +"属性值包含:"+ attribute);
    }
    /**
     * TODO
     * 待实现的assert
     * assertSelected（检查select的下拉菜单中选中是否正确）、
     * assertSelectedOptions（检查下拉菜单中的选项的是否正确）、
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
