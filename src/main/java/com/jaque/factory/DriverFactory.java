package com.jaque.factory;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
/**
 * 
 * @author JaqueLiao
 *
 */
public class DriverFactory {
	
	public static void main(String[] args) throws URISyntaxException {
		
		DriverFactory DriverFactory = new DriverFactory("ie",600,800);
		DriverFactory.setUrl("http://www.baidu.com");
		DriverFactory.setDisableImage();
		//DriverFactory.setDeviceEmulateOptions(360, 640, 3.0, "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");
		DriverFactory.getDriver();
	}
	
	private WebDriver driver = null;
	private boolean deviceEmulateFlag = false;
	private int deviceWidth;
	private int deviceHeight;
	private double devicePixelRatio;
	private String userAgent = null ;
	private int globalTimeOut = 0;
	private int pageLoadTimeOut = 0;
	private Dimension browserDimension;
	private int browserWidth;
	private int browserHeight;
	private boolean showImageFlag = true;
	private String browser = "";
	private String driverPath = null;
	private String url = null;
	/**
	 * 构造方法，默认使用chrome浏览器，可以使用setBrowser()设置
	 */
	public DriverFactory() {
	}
	/**
	 * 构造方法
	 * @param browser 浏览器名称 谷歌浏览器--chrome/gc/googleChrome；火狐浏览器-firefox/ff/fire；IE浏览器-IE/Iexplore/IEexplore/InternetExplorer,ie暂未实现不不显示图片功能
	 * 
	 */
	public DriverFactory(String browser) {
		this.browser = browser;
	}
	/**
	 * 构造方法
	 * @param browser 浏览器名称 谷歌浏览器--chrome/gc/googleChrome；火狐浏览器-firefox/ff/fire；IE浏览器-IE/Iexplore/IEexplore/InternetExplorer,ie暂未实现不不显示图片功能
	 * @param browserWidth 浏览器宽度
	 * @param browserHeight	浏览器高度
	 */
	public DriverFactory(String browser,int browserWidth,int browserHeight) {
		this.browser = browser;
		this.setBrowserDimension(browserWidth, browserHeight);
	}
	/**
	 * 通过DriverFactory取得driver，可设置使用哪个浏览器，默认为chrome
	 * @return 返回浏览器驱动
	 */
	public WebDriver getDriver() {
		switch(browser.toLowerCase()) {
		//谷歌浏览器
		case "chrome" : 
				this.initChrome();break;
		case "googlechrome" : 
			this.initChrome();break;
		case "gc" : 
			this.initChrome();break;
		//火狐浏览器
		case "firefox" : 
			this.initFireFox();break;
		case "ff" : 
			this.initFireFox();break;
		case "fire" :
			this.initFireFox();break;
		//opera浏览器
		case "opera" :
			this.initOpera();break;
		//IE浏览器
		case "ie" :
			this.initIexplore();break;
		case "iexplore" :
			this.initIexplore();break;
		case "ieexplore" :
			this.initIexplore();break;
		case "internetexplorer" :
			this.initIexplore();break;

		//默认使用谷歌浏览器
		default : 
			this.initChrome();break;
		}
		return driver;
	}
	/**
	 * 初始化opera
	 */
	private void initOpera() {
		if(!showImageFlag) {
		}
		if(deviceEmulateFlag) {
		}
		if(!(null == driverPath)) {
			System.setProperty("webdriver.opera.driver", this.driverPath);
		}
		try {
			driver = new OperaDriver();
		}catch (IllegalStateException e) {
			System.out.println("未找到浏览器驱动,其路径为："+this.driverPath+"。"
					+ "\n请将浏览器驱动放在path环境变量的某个路径下，或者使用”setDriverPath()“方法设置正确的浏览器驱动路径！");
		}
		this.initBrowser();
        
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
	/**
	 * 初始化ie
	 */
	private void initIexplore() {
		
		if(!showImageFlag) {
			//TODO
		}
		
		if(!(null == driverPath)) {
			System.setProperty("webdriver.internetExplorer.bin", this.driverPath);
		}
		try {
			driver = new InternetExplorerDriver();
		}catch (IllegalStateException e) {
			System.out.println("未找到浏览器驱动,其路径为："+this.driverPath+"。"
					+ "\n请将浏览器驱动放在path环境变量的某个路径下，或者使用”setDriverPath()“方法设置正确的浏览器驱动路径！\n");
			e.printStackTrace();
		}
		
		
		this.initBrowser();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 初始化chrome
	 */
	private void initChrome() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-infobars");
		if(!showImageFlag) {
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.managed_default_content_settings.images", 2);
			options.setExperimentalOption("prefs", prefs);
		}
		if(deviceEmulateFlag) {
			Map<String, Object> deviceMetrics = new HashMap<String, Object>();  
		    deviceMetrics.put("width", this.deviceWidth);  
		    deviceMetrics.put("height", this.deviceHeight);  
		    deviceMetrics.put("pixelRatio", this.devicePixelRatio);  
		    Map<String, Object> mobileEmulation = new HashMap<String, Object>(); 
		    mobileEmulation.put("deviceMetrics", deviceMetrics);  
		   
		    mobileEmulation.put("userAgent", this.userAgent);  
		    options.setExperimentalOption("mobileEmulation", mobileEmulation);
		}
		if(!(null == driverPath)) {
			System.setProperty("webdriver.chrome.driver", this.driverPath);
		}
		try {
			driver = new ChromeDriver(options);
		}catch (IllegalStateException e) {
			System.out.println("未找到浏览器驱动,其路径为："+this.driverPath+"。"
					+ "\n请将浏览器驱动放在path环境变量的某个路径下，或者使用”setDriverPath()“方法设置正确的浏览器驱动路径！");
		}
		this.initBrowser();
        
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 初始化火狐
	 */
	private void initFireFox() {
		
		FirefoxOptions fo = new FirefoxOptions();
		
		if(!showImageFlag) {
			fo.addPreference("permissions.default.image",2);
		}
		
		if(!(null == driverPath)) {
			System.setProperty("webdriver.firefox.bin", this.driverPath);
		}
		try {
			driver = new FirefoxDriver(fo);
		}catch (IllegalStateException e) {
			System.out.println("未找到浏览器驱动,其路径为："+this.driverPath+"。"
					+ "\n请将浏览器驱动放在path环境变量的某个路径下，或者使用”setDriverPath()“方法设置正确的浏览器驱动路径！");
		}
		
		this.initBrowser();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 浏览器公用初始化动作
	 */
	private void initBrowser() {
		if(!(0== globalTimeOut)){
			driver.manage().timeouts().implicitlyWait(this.globalTimeOut, TimeUnit.SECONDS);
		}
		if(!(0 == pageLoadTimeOut)){
	        driver.manage().timeouts().pageLoadTimeout(this.pageLoadTimeOut, TimeUnit.SECONDS);
		}
		if(!(null == browserDimension)) {
			driver.manage().window().setSize(browserDimension);
		}
		if(!(null == url)) {
			driver.get(url);
		}
	}
	/**
	 * 设置浏览器不显示图片	
	 */
	public void setDisableImage() {
		this.showImageFlag = false;
	}
	/**
	 * 设置浏览器显示图片	
	 */
	public void setShowImage() {
		this.showImageFlag = true;
	}
	/**
	 * 设置浏览器模拟移动设备的参数
	 * @param deviceWidth 设备宽度
	 * @param deviceHeight 设备高度
	 * @param devicePixelRatio 设备PixelRatio
	 * @param userAgent 设备userAgent
	 */
	public void setDeviceEmulateOptions(int deviceWidth,int deviceHeight,double devicePixelRatio, String userAgent) {
		this.deviceEmulateFlag = true;
		this.deviceWidth = deviceWidth;
		this.deviceHeight = deviceHeight;
		this.devicePixelRatio = devicePixelRatio;
		this.userAgent = userAgent;
	}
	/**
	 * 设置浏览器模拟移动设备的参数
	 * @param deviceWidth 设备宽度
	 * @param deviceHeight 设备高度
	 * @param devicePixelRatio 设备PixelRatio
	 */
	public void setDeviceMetrics(int deviceWidth,int deviceHeight,double devicePixelRatio) {
		String initUserAgent = "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19";
		this.setDeviceEmulateOptions(deviceWidth, deviceHeight,devicePixelRatio , null == this.userAgent ? initUserAgent : this.userAgent);
	}
	/**
	 * 设置浏览器模拟移动设备的参数
	 * @param deviceWidth 设备宽度
	 * @param deviceHeight 设备高度
	 */
	public void setDeviceMetrics(int deviceWidth,int deviceHeight) {
		this.setDeviceMetrics(deviceWidth, deviceHeight, 1.0);
	}
	/**
	 * 设置浏览器超时时间
	 * @param globalTimeOut	全局超时时间
	 * @param pageLoadTimeOut 页面加载超时时间
	 */
	public void setTimeOut(int globalTimeOut,int pageLoadTimeOut) {
		this.globalTimeOut = globalTimeOut;
		this.pageLoadTimeOut = pageLoadTimeOut;
	}
	/**
	 * 设置浏览器启动时大小
	 * @param browserDimension 大小
	 */
	public void setBrowserDimension(Dimension browserDimension) {
		this.browserWidth = browserDimension.width;
		this.browserHeight = browserDimension.height;
		this.browserDimension = browserDimension;
	}
	/**
	 * 设置浏览器启动时大小
	 * @param browserWidth 宽度
	 * @param browserHeight 高度
	 */
	public void setBrowserDimension(int browserWidth,int browserHeight) {
		this.browserWidth = browserWidth;
		this.browserHeight = browserHeight;
		Dimension dimension = new Dimension(browserWidth, browserHeight);
		this.browserDimension = dimension;
	}

	public int getDeviceWidth() {
		return deviceWidth;
	}

	public int getDeviceHeight() {
		return deviceHeight;
	}

	public double getDevicePixelRatio() {
		return devicePixelRatio;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public int getGlobalTimeOut() {
		return globalTimeOut;
	}

	public void setGlobalTimeOut(int globalTimeOut) {
		this.globalTimeOut = globalTimeOut;
	}

	public int getPageLoadTimeOut() {
		return pageLoadTimeOut;
	}
	
	public void setPageLoadTimeOut(int pageLoadTimeOut) {
		this.pageLoadTimeOut = pageLoadTimeOut;
	}

	public Dimension getBrowserDimension() {
		return browserDimension;
	}

	public int getBrowserWidth() {
		return browserWidth;
	}

	public int getBrowserHeight() {
		return browserHeight;
	}

	public boolean getShowImageFlag() {
		return showImageFlag;
	}

	public String getBrowser() {
		return browser;
	}
	
	/**
	 * 设置浏览器 
	 * @param browser 浏览器名称 谷歌浏览器--chrome/gc/googleChrome；火狐浏览器-firefox/ff/fire；IE浏览器-IE/Iexplore/IEexplore/InternetExplorer,ie暂未实现不不显示图片功能
	 */
	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getUrl() {
		return driver.getCurrentUrl();
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDriverPath() {
		return driverPath;
	}

	/**
	 * 设置浏览器驱动路径
	 * @param driverPath 路径
	 */
	public void setDriverPath(String driverPath) {
		this.driverPath = driverPath;
	}
}
