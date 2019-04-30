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
    }
}