package com.cnfantasia.server.api.commSysPara.entity;

/**
 * 轻松到家 参数配置
 * @author wenfq 2016-12-20
 */
public class QSDJPushParam {
	String key;
	String secret;
	String baseURL; //请求的URL根目录
	
	public QSDJPushParam(String key, String secret, String baseURL) {
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
