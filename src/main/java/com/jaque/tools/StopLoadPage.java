package com.jaque.tools;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class StopLoadPage extends Thread{
			WebDriver driver = null;
			int sec = 0; 
			public StopLoadPage(WebDriver driver,int sec){
				this.driver = driver;
				this.sec = sec;
			}
			@Override
			public void run() {
				try {
					Thread.sleep(sec*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				((JavascriptExecutor) driver).executeScript("window.stop();");
			}
		} 