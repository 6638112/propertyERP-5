package com.cnfantasia.pub.util;

import java.io.InputStream;
import java.util.Properties;

public class WeChatConfig {
	//解放区APPID
	public static String APPID = null; 
	//解放区APPSECRET
	public static String APPSECRET = null;
	
	//解放区访问路径
	public static String AUTH2URL = null;
	
	//解放区微信转发路径
	public static String REDIRECT_URI = null;
	
	//访问访问
	public static String SCOPE = null;
	
	/**
	 * 参数自动获取
	 * */
	static{
		Properties props = new Properties();
	    try {
	      InputStream ips = WeChatConfig.class.getResourceAsStream("cnfantasia-application-comm.properties");
	      props.load(ips);
	      APPID = props.getProperty("JFQ-WX-APPID");
	      APPSECRET = props.getProperty("JFQ-WX-APPSECRET");
	      AUTH2URL = props.getProperty("JFQ-WX-AUTH2URL");
	      REDIRECT_URI = props.getProperty("JFQ-WX-REDIRECT_URI");
	      SCOPE = props.getProperty("JFQ-WX-SCOPE");
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	public static void main(String[] args) {
		System.out.println("APPID:"+WeChatConfig.APPID);
		System.out.println("APPSECRET:"+WeChatConfig.APPSECRET);
		
		System.out.println("AUTH2URL:"+WeChatConfig.AUTH2URL);
	}
}
