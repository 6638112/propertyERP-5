package com.cnfantasia.server.api.commSysPara.entity;

/**
 * 十分到家 参数配置
 * @author wenfq  2016年11月10日
 *
 */
public class SFDJPushParam {
	String key;
	String secret;
	String baseURL; //请求的URL根目录
	
	public SFDJPushParam(String key, String secret, String baseURL) {
		super();
		this.key = key;
		this.secret = secret;
		this.baseURL = baseURL;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}
}
