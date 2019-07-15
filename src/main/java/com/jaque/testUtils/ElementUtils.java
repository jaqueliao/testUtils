package com.jaque.testUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Point;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.internal.WrapsDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ElementUtils {
	
    /**
     * 页面元素截图，返回File型
     * @param element 元素
     * @return
     * @throws IOException 
     */
    public static File captureElement(WebElement element) throws IOException{  
        WrapsDriver wrapsDriver = (WrapsDriver) element;  
        // 截图整个页面  
        File screen = ((TakesScreenshot) wrapsDriver.getWrappedDriver()).getScreenshotAs(OutputType.FILE);  
       //FilePathUtils.copyFile(screen, new File("d:\\b.png"));

        BufferedImage img = ImageIO.read(screen);  
        // 获得元素的高度和宽度  
        int width = element.getSize().getWidth();  
        int height = element.getSize().getHeight();  
        // 创建一个矩形使用上面的高度，和宽度  
        Rectangle rect = new Rectangle(width, height);  
        // 得到元素的坐标  
        Point p = element.getLocation();  
        //System.out.println("元素的坐标为:"+p);
        try {
        	BufferedImage dest = img.getSubimage(p.getX(), p.getY(), rect.width,rect.height);  
            ImageIO.write(dest, "png", screen);  
        }catch(Exception e) {
        	System.out.println("因网页结构原因，无法截取元素，改为截取整个页面");
        }
        //存为png格式  
        return screen;  
    }  
    /**
     * 将元素截图至本地
     * @param element 元素
     * @param file 本地路径
     * @throws IOException
     */
    public static void captureElementToFile(WebElement element,String file) throws IOException {
    	File screen = captureElement(element);
    	FileUtils.copyFile(screen, new File(file));
    }
    /**
     * 将元素与本地图片对比，相似度大于设置值表示元素显示正确，返回true
     * @param element 元素
     * @param pic 本地图片路径
     * @param similar 相似度
     * @return
     * @throws Exception
     */
    public static boolean isElementRight(WebElement element,String pic,double similar) throws Exception {
		return PhotoDigest.getSimRate(captureElement(element), new File(pic)) > similar?true:false; 	
    }    
    /**
     * 将元素与本地图片对比，相似度大于预设值（90）表示元素显示正确，返回true
     * @param element 元素
     * @param pic 本地图片路径
     * @return
     * @throws Exception
     */
    public static boolean isElementRight(WebElement element,String pic) throws Exception {
		return isElementRight(element, pic, (double) 90);
    }
    /**
     * 将元素与本地图片对比，相似度大于设置值表示元素显示正确，返回true
     * @param element 元素
     * @param pic 本地图片路径
     * @param similar 相似度
     * @return
     * @throws Exception
     */
    public static boolean isElementRight(WebElement element,String pic,int similar) throws Exception {
		return isElementRight(element, pic, (double) similar);
    }
    /**
     * 使浏览器滚动到某个元素的位置
     * @param driver
     * @param element
     */
    public static void scrollToElement(WebDriver driver,WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);  
    }
    public static void scrollToElementAndClick(WebDriver driver,WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        TestUtils.sleep("等待页面滚动完成");
        element.click();
    }
    /**
     * 将元素的截图在测试报告内显示
     * @param element
     * @throws Exception
     */
    public static void log(WebElement element) throws Exception {
    	
    	String dateString = TestUtils.getTimeStamp();
    	if(TestUtils.isTestServer()) {   		
        	FileUtils.copyFile(captureElement(element), new File(PropertyPraser.getProperty("JenkinHome")+"\\webapps\\testOutput\\img\\"+dateString+".png")); 
        	Reporter.log("<img class='pimg' src='/testOutput/img/"+dateString+".png' width='100'/>");
    	}
    	else {   		
    		FileUtils.copyFile(captureElement(element), new File("test-output/img/"+dateString+".png")); 
        	Reporter.log("<img class='pimg' src='../img/"+dateString+".png' width='100'/>");
    	}

    }

    /**
     * 往报告写入log的同时将元素截图传入报告
     * @param s
     * @param element
     * @throws Exception
     */
    public static void log(String s, WebElement element) throws Exception {
    	Reporter.log(s,true);
    	log(element);
    }
    /**
     * 判断元素是否出现
     * @param driver
     * @param by
     * @return 
     */
    public static boolean isElementPresent(WebDriver driver,By by) {
    	try {
    		driver.findElement(by);
    	}catch(Exception e) {
    		return false;
    	}
		return true;
    }

    /**
     * 判断元素是否在父元素内出现
     * @param pelement 父元素
     * @param by
     * @return
     */
    public static boolean isElementPresent(WebElement pelement,By by) {
        try {
            pelement.findElement(by);
        }catch(Exception e) {
            return false;
        }
        return true;
    }

    public static void waitForElementClickalbeAndClick(WebElement element){
        int time = 1;
        while(true){
            try{
                element.click();
                break;
            }catch (Exception e){
                if(time >5){
                    throw new WebDriverException("无法点击元素");
                }
                TestUtils.sleep("元素暂时无法点击，1s之后重试");
            }
            time++;
        }
    }
    @Test  
    public void testCaptureElement() throws IOException{  
    	
    	//报告正确显示截图
		 System.setProperty("org.uncommons.reportng.escape-output", "false");  
		 
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-infobars");
		WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();  
        driver.get("http://www.w3school.com.cn/tiy/t.asp?f=html_iframe_align");  
                
        WebElement wb = driver.findElement(By.xpath("//*[@id=\"wrapper\"]"));  
        try {  
            FileUtils.copyFile(captureElement(wb), new File("d:\\a.png")); 
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        driver.quit();  
    }  
}  
