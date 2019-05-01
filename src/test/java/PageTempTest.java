import com.jaque.Element;
import com.jaque.factory.PageTemp;
import org.openqa.selenium.WebDriver;

public class PageTempTest extends PageTemp {

    public PageTempTest(WebDriver driver) {
        super(driver);
        setDriver();
    }

    public Element input = new Element(driver).css("#kw").describe("搜索输入框");
    public Element btn = new Element(driver).css("#su").describe("百度一下按钮");


    public Element classFrame = new Element().name("classFrame").describe("类详细描述的iframe");
    public Element jaque = new Element().iframe(classFrame).text("com.jaque").describe("com.jaque的链接");
}
