package com.cnfantasia.server.domainbase.appDownLog.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(应用下载日志) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class AppDownLog implements Serializable{
*/


public class AppDownLog extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/**  */	private String os;	/**  */	private String osVer;	/**  */	private String time;	/**  */	private String ip;	/**  */	private String device;	/**  */	private String appType;	/** 应用下载信息 */	private String appInfo;
	public AppDownLog(){
	}
	public AppDownLog(AppDownLog arg){
		this.id = arg.getId();		this.os = arg.getOs();		this.osVer = arg.getOsVer();		this.time = arg.getTime();		this.ip = arg.getIp();		this.device = arg.getDevice();		this.appType = arg.getAppType();		this.appInfo = arg.getAppInfo();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param os 	 * @param osVer 	 * @param time 	 * @param ip 	 * @param device 	 * @param appType 	 * @param appInfo 应用下载信息	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public AppDownLog(BigInteger id,String os,String osVer,String time,String ip,String device,String appType,String appInfo,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.os = os;		this.osVer = osVer;		this.time = time;		this.ip = ip;		this.device = device;		this.appType = appType;		this.appInfo = appInfo;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("os=").append(os).append(";");		sbf.append("osVer=").append(osVer).append(";");		sbf.append("time=").append(time).append(";");		sbf.append("ip=").append(ip).append(";");		sbf.append("device=").append(device).append(";");		sbf.append("appType=").append(appType).append(";");		sbf.append("appInfo=").append(appInfo).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getOs() {		return os;	}	public void setOs(String os) {		this.os = os;	}	public String getOsVer() {		return osVer;	}	public void setOsVer(String osVer) {		this.osVer = osVer;	}	public String getTime() {		return time;	}	public void setTime(String time) {		this.time = time;	}	public String getIp() {		return ip;	}	public void setIp(String ip) {		this.ip = ip;	}	public String getDevice() {		return device;	}	public void setDevice(String device) {		this.device = device;	}	public String getAppType() {		return appType;	}	public void setAppType(String appType) {		this.appType = appType;	}	public String getAppInfo() {		return appInfo;	}	public void setAppInfo(String appInfo) {		this.appInfo = appInfo;	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppDownLog other = (AppDownLog) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
