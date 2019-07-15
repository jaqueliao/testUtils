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

    public Element ele = new Element().describe("公用元素");

    public Element getSearch_input() {
        return ele.id("kw").describe("搜索输入框");
    }

    public Element getSearch_btn() {
        return ele.id("su").describe("百度一下按钮");
    }

    public Element a1 = new Element().id("a1").describe("a1内容");
    public Element mainIframe = new Element().id("main").describe("第一个iframe");
    public Element b1 = new Element().iframe(mainIframe).id("b1").describe("b1内容");
    public Element testIframe = new Element().iframe(mainIframe).id("testIframe").describe("第二个iframe");
    public Element textdiv = new Element().iframe(testIframe).css("body > div:nth-child(2)").describe("文本");


}
