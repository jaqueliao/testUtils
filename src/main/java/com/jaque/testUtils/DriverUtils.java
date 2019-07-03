package com.jaque.testUtils;

import com.jaque.tools.StopLoadPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DriverUtils {


	public static void main(String[] args){
		String initurl = "https://ykttest.zytest.net/groupPurchasing?groupPurchasingId=840457600439943168&orderId=8404576060938649600&charset=UTF-8&out_trade_no=o1der7g6h4TwiPSrm45-_9eqb6AH1EDX&method=alipay.trade.wap.pay.return&total_amount=0.01&sign=Ia7cZJen1UA52zxNYunjJelddspEG1NF5cshkGHgHupCYG05RTNMnVMcRtUKQ%2BZkz3WOGe59%2BTMJMhBz3bdsjBL8%2FTOIQgD7I8eJdtj6BHbRZDK3I1OjShHPRoqA9TVlx%2FxGdNYiy%2BOu7y1NiqQIAXAxRKsQpsmUH8wd9ej5uyA%3D&trade_no=2019070322001468601055709440&auth_app_id=2017041206660636&version=1.0&app_id=2017041206660636&sign_type=RSA&seller_id=2088511697432208&timestamp=2019-07-03%2014%3A14%3A42";
		System.out.println(getPara(initurl,"groupPurchasingId"));
	}
	/**
	 * 返回解码后的url文本
	 * @param driver
	 * @return URL
	 * @throws UnsupportedEncodingException 不支持的解码异常
	 */
	public static String getUrl(WebDriver driver){
		String url = driver.getCurrentUrl();
		try {
			url =  URLDecoder.decode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			DriverUtils.log("url解码失败，返回原始url："+url);
		}
		return url;
	}

	/**
	 * 从 driver 的 url中 获取某个参数值
	 * @param driver driver
	 * @param paraKey 参数名
	 * @return 返回参数值
	 */
	public static String getPara(WebDriver driver, String paraKey){
		return getPara(getUrl(driver),paraKey);
	}

	/**
	 * 从 url 获取某个参数值
	 * @param url url
	 * @param paraKey 参数名
	 * @return 返回参数值
	 */
	public static String getPara(String url, String paraKey){
		String paraValue = "";
		String newUrl = url;
		try {
			newUrl = URLDecoder.decode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			DriverUtils.log("url解码失败，使用原始url："+url+"进行参数查询。");
		}
		String pattern = "(^|&)"+ paraKey +"=([^&]*)(&|$)";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(newUrl.substring(newUrl.indexOf("?")+1));
		if (m.find( )) {
			paraValue = m.group(2);
		} else {
			DriverUtils.log("未找到对应参数："+paraKey);
		}
		return paraValue;
	}
    /**
     * 截取整个网页
     * @param driver
     * @return
     */
    public static File captureBrowser(WebDriver driver){
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
     */
    public static void log(WebDriver driver){
    	
    	String dateString = TestUtils.getTimeStamp();
    	if(TestUtils.isTestServer()) {
    		String JenkinHome = System.getenv("CATALINA_HOME");
    		try {
				FileUtils.copyFile(captureBrowser(driver), new File(JenkinHome+"\\webapps\\testOutput\\img\\"+dateString+".png"));
			}catch (IOException e){
				Reporter.log("保存截图出错："+e.toString());
			}
        	Reporter.log("<img class='pimg' src='/testOutput/img/"+dateString+".png' width='100'/>");
    	}
    	else {
			try {
				FileUtils.copyFile(captureBrowser(driver), new File("test-output/img/"+dateString+".png"));
			}catch (IOException e){
				Reporter.log("保存截图出错："+e.toString());
			}
        	Reporter.log("<img class='pimg' src='../img/"+dateString+".png' width='100'/>");
    	}
    }
    
    /**
     * 往报告写入log的同时将浏览器截图传入报告
     * @param s	日志内容
     * @param driver 浏览器驱动
     */
    public static void log(String s, WebDriver driver){
    	log(s);
    	log(driver);
    }

    /**
     * log带时间
     * @param s
     */
    public static void log(String s){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStr = sdf.format(date);
        Reporter.log(timeStr + "  " + s,true);
    }

	/**
	 * 等待millisecond毫秒之后截图保存日志
	 * @param s 日志文本
	 * @param millisecond 时长
	 */
	public static void log(String s,WebDriver driver,int millisecond){
    	TestUtils.sleep(s,millisecond);
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
