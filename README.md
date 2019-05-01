# java自动化测试工具包介绍
---
&nbsp;&nbsp;&nbsp;&nbsp;最开始做自动化的时候自己封装了一些工具类，把一些元素、driver的操作做了封装，所以就把这个项目叫做testUtils（测试工具）
。  
&nbsp;&nbsp;&nbsp;&nbsp;但最近看到一篇虫师的文章，他实现了一个python的poium测试库，核心是基于Page Objects实现元素定位的封装。看完之后，发现用这个测试库的话定位元素，元素操作都变得非常简洁，并且清晰明了。  
&nbsp;&nbsp;&nbsp;&nbsp;由于我一直用的是java做自动化开发，并早就烦恼于java各种类库的引用与代码的繁琐，所以我就想java能不能也封装出相似的测试库呢。几经思索，终于想通了一些细节，并着手实现了框架。  
<br>
实现此框架的原则：
1. 使用此框架之后代码必须简洁
2. 必须与IDE有较高的契合度，各项操作能自动补全代码
3. 对同一个对象的多次操作最好能一句代码实现。

基于以上原则，封装了`Element` `Assertion` `PageTemp`类，其中
- `Element` 是对`WebElement`的封装，使用它对元素的操作及定位都会简单一些。
- `PageTemp` 是Page Objects模式的页面元素集合。
- `Assertion` 是一些断言方法的封装，暂时实现了一部分，后续应该会实现Selenium IDE的所有断言。并且此处断言可以链式调用。

### 实现的特性
1. 相对于selenium的8种定位方式，增加了“文本”定位 和“包含文本”定位。如：<br>`new Element().text("文本内容")`<br>`new Element().containText("包含的文本")`
2. 针对iframe内的元素做特殊处理，只要给元素设置iframe属性后，之后对此元素的操作可以直接调用。如：<br>`Element iframe = new Element().name("iframe");`<br>`Element element = new Element().iframe(iframe).text("example");`<br>元素操作：<br>`element.click();`<br>省去了繁琐的driver与frame上下文切换操作
3. 未元素增加了一个父元素属性（pElement），设置pElement之后，查找元素会从pElement内查找。如：<br>`Element pEle = new Element().name("pElement");`<br>`Element cEle = new Element().pElement(pEle).name("cElement");`
4. 对多选元素封装了多个方法，可直接调用，免除了新建Select类的操作。
5. 对Element简单实现了几个断言方法，可直接使用。如：<br>`ele.assert_equalsText("expectText");`<br>不过还是建议建议使用Assertion进行断言。
6. 封装了一个断言类（Assertion），同一用例中可连续调用，即使断言失败也会继续执行后面的代码，只有在调用`Assertion.end()`才会判断用例是否成功。如：<br>
```java
//dosomething
 Assertion.start().assertTitle(driver,"expectTitle")
//dosomething
 Assertion.assertLocation(driver,"expectUrl").end();//最后一次断言
```

### 框架使用示例
##### page类
```java
import com.jaque.Element;
import com.jaque.factory.PageTemp;
import org.openqa.selenium.WebDriver;

public class PageTempTest extends PageTemp {

    public PageTempTest(WebDriver driver) {
        super(driver);
        setDriver();
    }

    public Element search_input = new Element().css("#kw").describe("搜索输入框");
    public Element search_btn = new Element().css("#su").describe("百度一下按钮");

    public Element classFrame = new Element().name("classFrame").describe("类详细描述的iframe");
    public Element jaqueLink = new Element().iframe(classFrame).text("com.jaque").describe("com.jaque的链接");
}
```
##### 测试类
```java
import com.jaque.Assertion;
import com.jaque.factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ElementTest {

    WebDriver driver = null;
    PageTempTest ptt = null;

    @BeforeTest
    public void setUp(){
        DriverFactory DriverFactory = new DriverFactory("chrome", 1440, 900);
        //DriverFactory.setUrl("http://www.baidu.com");//可以在此处设置初始url
        driver = DriverFactory.getDriver();
        ptt = new PageTempTest(driver);
    }
    @AfterTest
    public void tearDown(){
        driver.close();
    }

    @Test(priority = 1, description = "基础测试用例示例")
    public void testSimple(){
        driver.get("http://www.baidu.com");
        Assertion.start().assertTitle(driver,"百度一下，你就知道");
        ptt.search_input.type("selenium").sleep();
        ptt.search_btn.click();
        ptt.search_input.assert_hasText("selenium");
        Assertion.assertTitle(driver,"selenium_百度搜索").assertUrlContainStr(driver,"https://www.baidu.com/s").end();
    }

    @Test(priority = 2, description = "元素在iframe中操作示例")
    public void TestFrame(){
        driver.get("http://docs.jaque.top/");
        ptt.jaqueLink.click();
        Assertion.assertTitle(driver,"com.jaque").assertUrlContainStr(driver,"docs.jaque.top").end();
    }
}
```
  
javadoc地址：[http://docs.jaque.top]  
git地址：[https://github.com/jaqueliao/testUtils]  