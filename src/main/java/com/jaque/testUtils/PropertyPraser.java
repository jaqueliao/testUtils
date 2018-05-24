package com.jaque.testUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertyPraser {
    public static void main(String[] args) throws Exception{ 
    	
    	File root = new File("src/resources");
    	
    	
    	System.out.println(root.getAbsolutePath());
    	
    	System.out.println(getProperty("DBPASSWORD"));
    	
    	System.out.println(TestUtils.getWorkPath());
    } 
    public static String getProperty(String key){
     	String value = "";
    	Properties prop = new Properties(); 
    	 try{
             //读取属性文件a.properties
    		 InputStream in = new BufferedInputStream (new FileInputStream(TestUtils.getWorkPath()+"/conf/conf.properties"));
             
    		 //InputStream ips = PropertiesUtils.class.getResourceAsStream("/conf/conf.properties");  
    		 
    		 
    		 prop.load(in);     ///加载属性列表
             value = prop.getProperty(key);
             in.close();
         }
         catch(Exception e){
            e.printStackTrace();
         }
		return value; 
    }
}