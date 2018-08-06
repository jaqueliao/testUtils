package com.jaque.tools;

import com.jaque.testUtils.TestUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.Date;
import java.util.concurrent.*;

public class StopLoadPage extends Thread{
	WebDriver driver = null;
	int sec = 0; 
	public StopLoadPage(WebDriver driver,int sec){
		this.driver = driver;
		this.sec = sec;
	}
	@Override
	public void run() {
		while(true) {
			
			  final ExecutorService exec = Executors.newFixedThreadPool(1);  
	          
		        Callable<String> call = new Callable<String>() {  
		            public String call() throws Exception {  
		                //开始执行耗时操作  
		            	String url = driver.getCurrentUrl();
		            	return "线程执行完成："+url+".";  
		            }  

		        };
			
		        try {  
		            Future<String> future = exec.submit(call);  
		            String obj = future.get(1000 * sec, TimeUnit.MILLISECONDS); //任务处理超时时间设为 sec 秒
					if(!TestUtils.isTestServer()){
						System.out.println("任务成功返回:" + obj);
					}
		        } catch (Exception e) {
					if(!TestUtils.isTestServer()) {
						System.out.println(new Date().toString() + "  页面加载超时！");
					}
		        	((JavascriptExecutor) driver).executeScript("window.stop();");
					if(!TestUtils.isTestServer()) {
						System.out.println(new Date().toString() + "  执行停止完成！");
					}
		            //e.printStackTrace();  
		        }  
		        // 关闭线程池  
		        exec.shutdown();  
		        try {
					Thread.sleep(sec*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			
			
			
		/*	String url = driver.getCurrentUrl();
			System.out.println(new Date().toString() + "当前："+url+"。");
			try {
				Thread.sleep(sec*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(url.equals(driver.getCurrentUrl())){
				System.out.println(new Date().toString() + "超时:"+url+"！");
				((JavascriptExecutor) driver).executeScript("window.stop();");
			}*/
		}
	}
} 