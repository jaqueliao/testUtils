package com.jaque.testUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.jaque.tools.StopLoadPage;

public class DriverUtils {
	
	/**
	 * 返回解码后的url文本
	 * @param driver
	 * @return URL
	 * @throws UnsupportedEncodingException 不支持的解码异常
	 */
	public static String getUrl(WebDriver driver) throws UnsupportedEncodingException {
		return URLDecoder.decode(driver.getCurrentUrl(), "UTF-8");	
	}
	
    /**
     * 截取整个网页
     * @param driver
     * @return
     * @throws IOException
     */
    public static File captureBrowser(WebDriver driver) throws IOException{  
        // 截图整个网页  
        File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);  
        //FileUtils.copyFile(screen, new File("d:\\b.png")); 
        return screen;  
    } 
    /**
     * 将浏览器截图至本地
     * @param driver 浏览器驱动
     * @param file 本地文件路径
     * @throws IOException
     */
    public static void captureBrowserToFile(WebDriver driver,String file) throws IOException {
    	File screen = captureBrowser(driver);
		FileUtils.deleteQuietly(new File(file));
    	FileUtils.copyFile(screen, new File(file));
    }
    /**
     * 将页面与本地图片对比，相似度大于设置值表示元素显示正确，返回true
     * @param driver 
     * @param pic 本地图片路径
     * @param similar 相似度
     * @return
     * @throws Exception
     */
    public static boolean isPageRight(WebDriver driver,String pic,double similar) throws Exception {
		return PhotoDigest.getSimRate(captureBrowser(driver), new File(pic)) > similar?true:false; 	
    }    
    /**
     * 将页面与本地图片对比，相似度大于预设值（90）表示元素显示正确，返回true
     * @param driver 
     * @param pic 本地图片路径
     * @return
     * @throws Exception
     */
    public static boolean isPageRight(WebDriver driver,String pic) throws Exception {
		return isPageRight(driver, pic, (double) 90);
    }
    /**
     * 将页面与本地图片对比，相似度大于设置值表示元素显示正确，返回true
     * @param driver 
     * @param pic 本地图片路径
     * @param similar 相似度
     * @return
     * @throws Exception
     */
    public static boolean isPageRight(WebDriver driver,String pic,int similar) throws Exception {
		return isPageRight(driver, pic, (double) similar);
    }
    
    /**
     * 将浏览器截图传入报告
     * @param driver
     * @throws Exception
     */
    public static void log(WebDriver driver) throws Exception {
    	
    	String dateString = TestUtils.getTimeStamp();
    	if(TestUtils.isTestServer()) {
    		String JenkinHome = System.getenv("CATALINA_HOME");
        	FileUtils.copyFile(captureBrowser(driver), new File(JenkinHome+"\\webapps\\testOutput\\img\\"+dateString+".png")); 
        	Reporter.log("<img class='pimg' src='/testOutput/img/"+dateString+".png' width='100'/>");
    	}
    	else {   		
    		FileUtils.copyFile(captureBrowser(driver), new File("test-output/img/"+dateString+".png")); 
        	Reporter.log("<img class='pimg' src='../img/"+dateString+".png' width='100'/>");
    	}

    }
    
    /**
     * 往报告写入log的同时将浏览器截图传入报告
     * @param s	日志内容
     * @param driver 浏览器驱动
     * @throws Exception
     */
    public static void log(String s, WebDriver driver) throws Exception {
    	Reporter.log(s,true);
    	log(driver);
    }

    /**
     * 设置下一个页面自动停止加载
     * @param driver 浏览器驱动
     * @param sec 多少秒后停止加载
     */
    public static void setNextLoadTimeOut(WebDriver driver,int sec) {
		new StopLoadPage(driver,sec).start();
    }
}
