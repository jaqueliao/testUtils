import com.jaque.Assertion;
import com.jaque.Element;
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
        ptt.search_btn.assert_hasText("百度一下");
        ptt.search_input.assert_hasText("selenium");
        Assertion.assertTitle(driver,"selenium_百度搜索").assertUrlContainStr(driver,"https://www.baidu.com/s").end();
    }

    @Test(priority = 2, description = "元素在iframe中操作示例")
    public void TestFrame(){
        driver.get("http://docs.jaque.top/");
        ptt.jaqueLink.click();
        Assertion.assertTitle(driver,"com.jaque").assertUrlContainStr(driver,"docs.jaque.top").end();
    }

    @Test(priority = 3, description = "页面方法返回可自定义元素用例示例")
    public void testPage(){
        driver.get("http://www.baidu.com");
        ptt.getSearch_input().type("selenium").sleep();
        ptt.getSearch_btn().click();
        ptt.getSearch_input().assert_hasText("selenium");
        Assertion.assertTitle(driver,"selenium_百度搜索").assertUrlContainStr(driver,"https://www.baidu.com/s").end();
    }

    @Test(priority = 3, description = "页面方法返回可自定义元素用例示例，这里不能再次设置变量，否则会引用错乱")
    public void testPage2(){
        driver.get("http://www.baidu.com");
        Element search_input = ptt.getSearch_input();
        System.out.println(search_input);
        search_input.type("selenium").sleep();
        Element search_btn = ptt.getSearch_btn();
        search_btn.click();
        System.out.println(search_btn);
        System.out.println(search_input);
        System.out.println(ptt.ele);
        System.out.println(ptt.ele == search_btn);
        System.out.println(ptt.ele == search_input);
        //search_input.assert_hasText("selenium");
        //Assertion.assertTitle(driver,"selenium_百度搜索").assertUrlContainStr(driver,"https://www.baidu.com/s").end();
    }

    @Test(priority = 3, description = "极限简单代码形式")
    public void testUlimit(){
        driver.get("http://www.baidu.com");
        new Element(driver).id("kw").type("selenium").sleep().id("su").click().sleep(3000).id("kw").clear().type("自动化").id("su").click().sleep(10000);
        Assertion.assertTitle(driver,"自动化_百度搜索").assertUrlContainStr(driver,"https://www.baidu.com/s").end();
    }

    @Test(priority = 4, description = "多层嵌套iframe测试")
    public void iframeTest(){
        driver.get("file:///C:/Users/Administrator/Desktop/iframetest/A%E9%A1%B5%E9%9D%A2.html");
        System.out.println(ptt.textdiv.getText());
        System.out.println(ptt.a1.getText());
        System.out.println(ptt.b1.getText());
    }

}