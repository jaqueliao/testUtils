package com.jaque.tools;

import java.net.SocketException;

import com.jaque.testUtils.TestUtils;

/**
 * 
 * @author Administrator
 *
 */
public abstract class ActionTools{
		int sec;
		public ActionTools(){
			this.sec = 3;
		}
		public ActionTools(int sec){
			this.sec = sec;
		}
		public void dosomething() {
			System.out.println("dosomething");
		}
		public void retry() throws Exception {
			try {
				dosomething();
			} catch (Exception e) {
				if(!TestUtils.isTestServer()) {
					e.printStackTrace();
				}
				try {Thread.sleep(sec*1000);} catch (InterruptedException e1) {}
				dosomething();
			}
		}
		public void doOnce() throws Exception {
			try {
				dosomething();
			} catch (Exception e) {
				if(!TestUtils.isTestServer()) {
					e.printStackTrace();
				}
				try {Thread.sleep(sec*1000);} catch (InterruptedException e1) {}
			}
		}
		
		public static void main(String[] args) throws Exception {
			final int i = 100;
			new ActionTools(3) {
				@Override
				public void dosomething() {
					System.out.println("dosomething"+i);
				}
			}.retry();

		}
	
}
