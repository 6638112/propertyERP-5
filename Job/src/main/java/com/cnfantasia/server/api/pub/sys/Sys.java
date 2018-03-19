package com.cnfantasia.server.api.pub.sys;

import java.io.Serializable;
import java.util.Date;

public class Sys implements Serializable {

	private static final long serialVersionUID = -7365154159142719691L;
	
	//1
	private Date reqStartTime;
	
	private String ip;
	
	private String deviceId;
	
	private String os;
	
	private String channel;
	//2

	public Date getReqStartTime() {
		return reqStartTime;
	}

	public void setReqStartTime(Date reqStartTime) {
		this.reqStartTime = reqStartTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
}
