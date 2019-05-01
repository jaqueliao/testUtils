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