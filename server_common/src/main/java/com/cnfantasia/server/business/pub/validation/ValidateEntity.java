package com.cnfantasia.server.business.pub.validation;

import java.util.LinkedHashMap;
import java.util.Map;

public class ValidateEntity {
	private String url;
	private Map<String, Object> regulations;
	public ValidateEntity(){}
	public ValidateEntity(String url){
		this.url = url;
		this.regulations = new LinkedHashMap<String, Object>();
	}
	public ValidateEntity(String url,Map<String, Object> regulations){
		this.url = url;
		this.regulations = regulations;
	}
	
	public void putRegulations(String key,Object value){
		regulations.put(key, value);
	}
	public void removeRegulations(String key){
		regulations.remove(key);
	}
	public Object getRegulations(String key){
		return regulations.get(key);
	}
	
	//get set 方法
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Map<String, Object> getRegulations() {
		return regulations;
	}
	public void setRegulations(Map<String, Object> regulations) {
		this.regulations = regulations;
	}
	
	
}
