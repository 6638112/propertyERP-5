package com.cnfantasia.server.domainbase.appCrashLog.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(app崩溃日志) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class AppCrashLog implements Serializable{
*/


public class AppCrashLog extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/**  */	private BigInteger userId;	/** 手机机型 */	private String phoneModel;	/** 当前版本号,a.b.c */	private String version;	/** 手机系统 */	private String operatingSystem;	/** 网络信号（2G,3G,4G,WIFI） */	private String networkSignal;	/** 崩溃描述 */	private String crashContent;
	public AppCrashLog(){
	}
	public AppCrashLog(AppCrashLog arg){
		this.id = arg.getId();		this.userId = arg.getUserId();		this.phoneModel = arg.getPhoneModel();		this.version = arg.getVersion();		this.operatingSystem = arg.getOperatingSystem();		this.networkSignal = arg.getNetworkSignal();		this.crashContent = arg.getCrashContent();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param userId 	 * @param phoneModel 手机机型	 * @param version 当前版本号,a.b.c	 * @param operatingSystem 手机系统	 * @param networkSignal 网络信号（2G,3G,4G,WIFI）	 * @param crashContent 崩溃描述	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public AppCrashLog(BigInteger id,BigInteger userId,String phoneModel,String version,String operatingSystem,String networkSignal,String crashContent,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.userId = userId;		this.phoneModel = phoneModel;		this.version = version;		this.operatingSystem = operatingSystem;		this.networkSignal = networkSignal;		this.crashContent = crashContent;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("userId=").append(userId).append(";");		sbf.append("phoneModel=").append(phoneModel).append(";");		sbf.append("version=").append(version).append(";");		sbf.append("operatingSystem=").append(operatingSystem).append(";");		sbf.append("networkSignal=").append(networkSignal).append(";");		sbf.append("crashContent=").append(crashContent).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getUserId() {		return userId;	}	public void setUserId(BigInteger userId) {		this.userId = userId;	}	public String getPhoneModel() {		return phoneModel;	}	public void setPhoneModel(String phoneModel) {		this.phoneModel = phoneModel;	}	public String getVersion() {		return version;	}	public void setVersion(String version) {		this.version = version;	}	public String getOperatingSystem() {		return operatingSystem;	}	public void setOperatingSystem(String operatingSystem) {		this.operatingSystem = operatingSystem;	}	public String getNetworkSignal() {		return networkSignal;	}	public void setNetworkSignal(String networkSignal) {		this.networkSignal = networkSignal;	}	public String getCrashContent() {		return crashContent;	}	public void setCrashContent(String crashContent) {		this.crashContent = crashContent;	}
	
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
		AppCrashLog other = (AppCrashLog) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
