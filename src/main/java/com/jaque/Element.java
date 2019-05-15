package com.jaque;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * 元素包装类，为元素添加了定位、操作及断言等封装
 * @CreateDate 2019-04-29 09:00:00
 * @author jaqueliao
 */
public class Element {

    private WebDriver driver;//浏览器驱动
    private Element pElement;//父元素，pElement与driver必须设置一个
    private Element iframe;//设置之后本元素表示在此iframe内，会通过此iframe找寻元素
    private String description;//元素描述文本
    private By by; //选择器
    private String findType; //查找元素的方式
    private String selector; //元素定位字符串
    private int index = 0;//若选择器可找到多个元素，此处表示第几个
    private boolean signForOperate = false;//操作元素时是否标志，给元素加红色边框

    public Element() {}
    public Element(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * 设置浏览器driver，使用PageTemp设置后也可使用此方法更新，不推荐使用
     * @param driver 浏览器驱动
     * @return 返回Element对象本身，可以链式设置属性
     */
    public Element driver(WebDriver driver){
        this.driver = driver;
        return this;
    }

    /**
     * 设置父元素，若设置之后，会使用pElement查询元素
     * @param pElement 父元素
     * @return 返回Element对象本身，可以链式设置属性
     */
    public Element pElement(Element pElement){
        this.pElement = pElement;
        return this;
    }

    /**
     * 设置iframe，若设置之后，切换到此iframe后再查询元素
     * @param iframe iframe
     * @return 返回Element对象本身，可以链式设置属性
     */
    public Element iframe(Element iframe){
        this.iframe = iframe;
        return this;
    }
    /**
     * 设置元素描述信息
     * @param description 描述文本
     * @return 返回Element对象本身，可以链式设置属性
     */
    public Element describe(String description){
        this.description = description;
        return this;
    }

    /**
     * 设置选取第几个元素
     * @param index 元素序号
     * @return 返回Element对象本身，可以链式设置属性
     */
    public Element index(int index){
        this.index = index;
        return this;
    }

    /**
     * 操作元素时是否标志，给元素加红色边框
     * @param signForOperate 是否添加
     * @return 返回Element对象本身，可以链式设置属性
     */
    public Element sign(boolean signForOperate){
        // TODO 待增加添加边框操作
        this.signForOperate = signForOperate;
        return this;
    }

    /**
     * 根据id定位
     * @param id id
     * @return 返回Element对象本身，可以链式设置属性
     */
    public Element id(String id){
        this.findType = "id";
        this.selector = id;
        this.by = By.id(id);
        return this;
    }

    /**
     * 根据name定位
     * @param name name
     * @return 返回Element对象本身，可以链式设置属性
     */
    public Element name(String name){
        this.findType = "name";
        this.selector = name;
        this.by = By.name(name);
        return this;
    }

    /**
     * 根据className定位
     * @param className className
     * @return 返回Element对象本身，可以链式设置属性
     */
    public Element className(String className){
        this.findType = "className";
        this.selector = className;
        this.by = By.className(className);
        return this;
    }

    /**
     * 根据xpath定位
     * @param xpath xpath
     * @return 返回Element对象本身，可以链式设置属性
     */
    public Element xpath(String xpath){
        this.findType = "xpath";
        this.selector = xpath;
        this.by = By.xpath(xpath);
        return this;
    }

    /**
     * 根据cssSelector定位
     * @param css css选择器
     * @return 返回Element对象本身，可以链式设置属性
     */
    public Element css(String css){
        this.findType = "css";
        this.selector = css;
        this.by = By.cssSelector(css);
        return this;
    }

    /**
     * 根据标签定位
     * @param tagName 标签名
     * @return 返回Element对象本身，可以链式设置属性
     */
    public Element tagName(String tagName){
        this.findType = "tagName";
        this.selector = tagName;
        this.by = By.tagName(tagName);
        return this;
    }

    /**
     * 根据链接文本定位
     * @param linkText 链接文本
     * @return 返回Element对象本身，可以链式设置属性
     */
    public Element linkText(String linkText){
        this.findType = "linkText";
        this.selector = linkText;
        this.by = By.linkText(linkText);
        return this;
    }

    /**
     * 根据链接的部分文本定位
     * @param linkText 链接文本
     * @return 返回Element对象本身，可以链式设置属性
     */
    public Element partialLinkText(String linkText){
        this.findType = "partialLinkText";
        this.selector = linkText;
        this.by = By.partialLinkText(linkText);
        return this;
    }

    /**
     * 根据元素文本定位
     * @param text 元素文本
     * @return 返回Element对象本身，可以链式设置属性
     */
    public Element text(String text){
        this.findType = "text";
        this.selector = text;
        this.by = By.xpath("//*[text()='"+ text +"']");
        return this;
    }

    /**
     * 根据元素包含的部分文本定位
     * @param text 部分文本内容
     * @return 返回Element对象本身，可以链式设置属性
     */
    public Element containText(String text){
        this.findType = "containText";
        this.selector = text;
        this.by = By.xpath("//*[contains(text(),'"+text+"')]");
        return this;
    }

    /**
     * 获取Selenium自带的WebElement类型的元素
     * @return 返回WebElement
     */
    public WebElement getElement(){
        if(!isPresent()){
            throw new IllegalArgumentException( "使用设置的定位方式无法定位到元素"+this);
        }
        if(length()<=this.index){
            throw new IllegalArgumentException( "index大于可定为到元素个数："+length()+"，index为："+index);
        }
        if(null != this.pElement){
            return this.pElement.getElement().findElements(by).get(index);
        }
        if(null != this.driver){
            return this.driver.findElements(by).get(index);
        }
        return null;
    }

    /**
     * 切换到iframe内，若未设置iframe则不切换
     * @return 返回Element对象本身，可以链式操作
     */
    public Element switchToFrame(){
        if(null != this.iframe) {
            this.driver.switchTo().frame(this.iframe.switchToFrame().getElement());
        }else{
            //System.out.println("未设置iframe，不作切换");
        }
        return this;
    }

    /**
     * 切换回默认上下文
     * @return 返回Element对象本身，可以链式操作
     */
    public Element switchToDefault(){
        this.driver.switchTo().defaultContent();
        return this;
    }

    /**
     * 获取当前设置的选择器可找打的所有Selenium自带的WebElement类型的元素
     * @return 返回WebElement的List
     */
    public List<WebElement> getAllElements(){
        if (null != this.pElement){
            return this.pElement.getElement().findElements(by);
        }
        if(null != this.driver){
            return this.driver.findElements(by);
        }
        throw new IllegalArgumentException( "driver 与 pElement 不能同时为空");
    }

    /**
     * 用当前设置的选择器可定位的元素个数
     * @return 选择器可定位的元素个数
     */
    public int length(){
        return getAllElements().size();
    }
    /**
     * 点击操作
     * @return 返回Element对象本身，可以链式操作
     */
    public Element click(){
        this.switchToFrame().getElement().click();
        return this.switchToDefault();
    }

    /**
     * 移动到元素中间然后点击，当元素是不可点击时，但要点击此元素所在的位置时可使用
     * @return 返回Element对象本身，可以链式操作
     */
    public Element moveToElementThenclick(){
        Actions actions = new Actions(this.driver);
        this.switchToFrame();
        actions.moveToElement(getElement()).click().perform();
        return this.switchToDefault();
    }

    /**
     * 输入内容
     * @param keysToSend 字符串或者Keys
     * @return 返回Element对象本身，可以链式操作
     */
    public Element type(CharSequence... keysToSend){
        this.switchToFrame().getElement().sendKeys(keysToSend);
        return this.switchToDefault();
    }

    /**
     * 清除内容，一般只对input生效
     * @return 返回Element对象本身，可以链式操作
     */
    public Element clear(){
        this.switchToFrame().getElement().clear();
        return this.switchToDefault();
    }

    /**
     * 多选元素 通过index选择
     * @param index index
     * @return 返回Element对象本身，可以链式操作
     */
    public Element selectByIndex(int index){
        this.switchToFrame();
        Select select = new Select(getElement());
        select.selectByIndex(index);
        return this.switchToDefault();
    }

    /**
     * 多选元素 通过文本选择
     * @param text 选择项
     * @return 返回Element对象本身，可以链式操作
     */
    public Element selectByVisibleText(String text){
        this.switchToFrame();
        Select select = new Select(getElement());
        select.selectByVisibleText(text);
        return this.switchToDefault();
    }

    /**
     * 多选元素 通过value值选择
     * @param value value
     * @return 返回Element对象本身，可以链式操作
     */
    public Element selectByValue(String value){
        this.switchToFrame();
        Select select = new Select(getElement());
        select.selectByValue(value);
        return this.switchToDefault();
    }

    /**
     * 多选元素 通过index取消选择
     * @param index index
     * @return 返回Element对象本身，可以链式操作
     */
    public Element deselectByIndex(int index) {
        this.switchToFrame();
        Select select = new Select(getElement());
        select.deselectByIndex(index);
        return this.switchToDefault();
    }

    /**
     * 多选元素 通过文本取消选择
     * @param text 选择项
     * @return 返回Element对象本身，可以链式操作
     */
    public Element deselectByVisibleText(String text){
        this.switchToFrame();
        Select select = new Select(getElement());
        select.deselectByVisibleText(text);
        return this.switchToDefault();
    }

    /**
     * 多选元素 通过value值取消选择
     * @param value value
     * @return 返回Element对象本身，可以链式操作
     */
    public Element deselectByValue(String value){
        this.switchToFrame();
        Select select = new Select(getElement());
        select.deselectByValue(value);
        return this.switchToDefault();
    }

    /**
     * 取消所有选择
     * @return 返回Element对象本身，可以链式操作
     */
    public Element deselectAll(){
        this.switchToFrame();
        Select select = new Select(getElement());
        select.deselectAll();
        return this.switchToDefault();
    }

    /**
     * 是否可多选
     * @return isMultiple
     */
    public boolean isMultiple(){
        boolean isMultiple;
        this.switchToFrame();
        Select select = new Select(getElement());
        isMultiple =  select.isMultiple();
        this.switchToDefault();
        return isMultiple;
    }

    /**
     * 选项是否是已选中状态，一般用于单选
     * @return boolean
     */
    public boolean isSelected(){
        boolean isSelected;
        this.switchToFrame();
        isSelected = getElement().isSelected();
        this.switchToDefault();
        return isSelected;
    }

    /**
     * 元素是否显示
     * @return boolean
     */
    public boolean isDisplayed(){
        boolean isDisplayed;
        this.switchToFrame();
        isDisplayed = getElement().isDisplayed();
        this.switchToDefault();
        return isDisplayed;
    }

    /**
     * 元素是否可用，只有当元素是input，且不可用时才会返回false
     * @return boolean
     */
    public boolean isEnabled(){
        boolean isEnabled;
        this.switchToFrame();
        isEnabled = getElement().isEnabled();
        this.switchToDefault();
        return isEnabled;
    }

    /**
     * 返回所有选项的内容
     * @return  String List
     */
    public List<String> getAllOptionsText(){
        List<String> list = new ArrayList<>();
        this.switchToFrame();
        Select select = new Select(getElement());
        for(WebElement e:select.getOptions()){
            list.add(e.getText());
        }
        this.switchToDefault();
        return list;
    }

    /**
     * 返回所有选项的value值
     * @return String List
     */
    public List<String> getAllOptionsValue(){
        List<String> list = new ArrayList<>();
        this.switchToFrame();
        Select select = new Select(getElement());
        for(WebElement e:select.getOptions()){
            list.add(e.getAttribute("value"));
        }
        this.switchToDefault();
        return list;
    }

    /**
     * 获取多选的选项个数
     * @return 个数
     */
    public int getSelectSize(){
        int size = 0;
        this.switchToFrame();
        Select select = new Select(getElement());
        size = select.getOptions().size();
        this.switchToDefault();
        return size;
    }

    /**
     * 返回所有已选选项的内容
     * @return String List
     */
    public List<String> getSelectedOptionsText(){
        List<String> list = new ArrayList<>();
        this.switchToFrame();
        Select select = new Select(getElement());
        for(WebElement e:select.getAllSelectedOptions()){
            list.add(e.getText());
        }
        this.switchToDefault();
        return list;
    }

    /**
     * 返回所有选项的value值
     * @return String List
     */
    public List<String> getSelectedOptionsValue(){
        List<String> list = new ArrayList<>();
        this.switchToFrame();
        Select select = new Select(getElement());
        for(WebElement e:select.getAllSelectedOptions()){
            list.add(e.getAttribute("value"));
        }
        this.switchToDefault();
        return list;
    }

    /**
     * 获取多选的已选选项个数
     * @return 个数
     */
    public int getSelectedSize(){
        int size = 0;
        this.switchToFrame();
        Select select = new Select(getElement());
        size = select.getAllSelectedOptions().size();
        this.switchToDefault();
        return size;
    }

    /**
     * 返回第一个已选选项的内容
     * @return String
     */
    public String getFirstSelectedText(){
        String firstSelectedText;
        this.switchToFrame();
        Select select = new Select(getElement());
        firstSelectedText = select.getFirstSelectedOption().getText();
        this.switchToDefault();
        return firstSelectedText;
    }

    /**
     * 返回所有选项的value值
     * @return String List
     */
    public String getFirstSelectedValue(){
        String firstSelectedValue;
        this.switchToFrame();
        Select select = new Select(getElement());
        firstSelectedValue = select.getFirstSelectedOption().getAttribute("value");
        this.switchToDefault();
        return firstSelectedValue;
    }

    /**
     * 获取显示的文本内容，当元素隐藏时此方法获取的值可能为空
     * @return 显示的文本内容
     */
    public String getText(){
        String text;
        this.switchToFrame();
        text = getElement().getText();
        this.switchToDefault();
        return text;
    }

    /**
     * 获取文本内容，即使元素隐藏时也可获取内容
     * @return 文本内容
     */
    public String getContent(){
        return getAttribute("textContent");
    }

    /**
     * 一般元素为input时获取内容使用此方法，getText或getContent获取的内容可能为空
     * @return 返回元素文本
     */
    public String getValue(){
        return getAttribute("value");
    }

    /**
     * 获取某个属性的值
     * @param name 属性名
     * @return 属性值
     */
    public String getAttribute(String name){
        String text;
        this.switchToFrame();
        text = getElement().getAttribute(name);
        this.switchToDefault();
        return text;
    }

    /**
     * 获取css值
     * @param propertyName css属性名
     * @return css值
     */
    public String getCssValue(String propertyName){
        String text;
        this.switchToFrame();
        text = getElement().getCssValue(propertyName);
        this.switchToDefault();
        return text;
    }

    /**
     * 获取标签名
     * @return 标签名
     */
    public String getTagName(){
        String text;
        this.switchToFrame();
        text = getElement().getTagName();
        this.switchToDefault();
        return text;
    }

    /**
     * 获取元素所在坐标
     * @return 元素坐标
     */
    public Point getLocation(){
        Point point;
        this.switchToFrame();
        point = getElement().getLocation();
        this.switchToDefault();
        return point;
    }

    /**
     * 获取元素的位置及大小
     * @return Rectangle
     */
    public Rectangle getRect(){
        Rectangle rectangle;
        this.switchToFrame();
        rectangle = getElement().getRect();
        this.switchToDefault();
        return rectangle;
    }

    /**
     * 获取元素的大小
     * @return 元素大小
     */
    public Dimension getSize(){
        Dimension size;
        this.switchToFrame();
        size = getElement().getSize();
        this.switchToDefault();
        return size;
    }

    /**
     * 等待
     * @param millisecond 毫秒数
     * @return 返回Element对象本身，可以链式操作
     */
    public Element sleep(int millisecond){
        try {Thread.sleep( millisecond); } catch (InterruptedException e) {e.printStackTrace();}
        return this;
    }

    /**
     * 等待1s
     * @return 返回Element对象本身，可以链式操作
     */
    public Element sleep(){
        return this.sleep(1000);
    }

    /**
     * 当前元素是否存在
     * @return 存在true，不存在false
     */
    public boolean isPresent() {
        if (null != this.pElement){
            try {
                this.pElement.getElement().findElement(by);
            }catch(Exception e) {
                return false;
            }
            return true;
        }
        if(null != this.driver){
            try {
                this.driver.findElement(by);
            }catch(Exception e) {
                return false;
            }
            return true;
        }
        throw new IllegalArgumentException( "driver 与 pElement 不能同时为空"+this);
    }

    /**
     * 断言 元素包含文本
     * @param s 文本
     */
    public void assert_hasText(String s) {
        assertTrue(getText().contains(s) ||getContent().contains(s) || getValue().contains(s) );

    }

    /**
     * 断言 元素内文本与期望值相等
     * @param s 文本
     */
    public void assert_equalsText(String s) {
        assertEquals(getText(),s);
    }

    @Override
    public String toString() {
        return "Element{" +
                "driver=" + driver +
                ", pElement=" + pElement +
                ", iframe=" + iframe +
                ", description='" + description + '\'' +
                ", findType='" + findType + '\'' +
                ", selector='" + selector + '\'' +
                ", index=" + index +
                ", signForOperate=" + signForOperate +
                '}';
    }
}
