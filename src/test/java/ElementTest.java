import com.jaque.Assertion;
import com.jaque.factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class ElementTest {

    @Test
    public void test1(){

        DriverFactory DriverFactory = new DriverFactory("chrome", 1440, 900);
        DriverFactory.setUrl("http://www.baidu.com");
        WebDriver driver = DriverFactory.getDriver();

        PageTempTest pft = new PageTempTest(driver);
        pft.input.type("selenium").sleep();
        pft.btn.click();
        pft.input.assert_hasText("selenium");
        Assertion.assertTitle(driver,"selenium_百度搜索").assertUrlContainStr(driver,"https://www.baidu.com/s").end();
        driver.close();
    }

    @Test
    public void TestFrame(){
        DriverFactory DriverFactory = new DriverFactory("chrome", 1440, 900);
        DriverFactory.setUrl("http://docs.jaque.top/");
        WebDriver driver = DriverFactory.getDriver();

        PageTempTest pft = new PageTempTest(driver);
        pft.jaque.click();
        Assertion.assertTitle(driver,"com.jaque1").assertUrlContainStr(driver,"docs.1jaque.top").end();
        driver.close();
    }
}