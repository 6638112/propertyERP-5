package com.cnfantasia.server.domainbase.loginLog.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(登录历史) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class LoginLog implements Serializable{
*/


public class LoginLog extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 登录时间 */	private String loginTime;	/** 登录日期 */	private String loginDay;	/**  */	private BigInteger tUserFId;	/**  */	private String ip;	/**  */	private String deviceId;	/**  */	private String userName;	/**  */	private String os;	/**  */	private String osVer;	/**  */	private Long accType;	/**  */	private String channel;	/** 版本号 */	private String version;
	public LoginLog(){
	}
	public LoginLog(LoginLog arg){
		this.id = arg.getId();		this.loginTime = arg.getLoginTime();		this.loginDay = arg.getLoginDay();		this.tUserFId = arg.gettUserFId();		this.ip = arg.getIp();		this.deviceId = arg.getDeviceId();		this.userName = arg.getUserName();		this.os = arg.getOs();		this.osVer = arg.getOsVer();		this.accType = arg.getAccType();		this.channel = arg.getChannel();		this.version = arg.getVersion();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param loginTime 登录时间	 * @param loginDay 登录日期	 * @param tUserFId 	 * @param ip 	 * @param deviceId 	 * @param userName 	 * @param os 	 * @param osVer 	 * @param accType 	 * @param channel 	 * @param version 版本号	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public LoginLog(BigInteger id,String loginTime,String loginDay,BigInteger tUserFId,String ip,String deviceId,String userName,String os,String osVer,Long accType,String channel,String version,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.loginTime = loginTime;		this.loginDay = loginDay;		this.tUserFId = tUserFId;		this.ip = ip;		this.deviceId = deviceId;		this.userName = userName;		this.os = os;		this.osVer = osVer;		this.accType = accType;		this.channel = channel;		this.version = version;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("loginTime=").append(loginTime).append(";");		sbf.append("loginDay=").append(loginDay).append(";");		sbf.append("tUserFId=").append(tUserFId).append(";");		sbf.append("ip=").append(ip).append(";");		sbf.append("deviceId=").append(deviceId).append(";");		sbf.append("userName=").append(userName).append(";");		sbf.append("os=").append(os).append(";");		sbf.append("osVer=").append(osVer).append(";");		sbf.append("accType=").append(accType).append(";");		sbf.append("channel=").append(channel).append(";");		sbf.append("version=").append(version).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getLoginTime() {		return loginTime;	}	public void setLoginTime(String loginTime) {		this.loginTime = loginTime;	}	public String getLoginDay() {		return loginDay;	}	public void setLoginDay(String loginDay) {		this.loginDay = loginDay;	}	public BigInteger gettUserFId() {		return tUserFId;	}	public void settUserFId(BigInteger tUserFId) {		this.tUserFId = tUserFId;	}	public String getIp() {		return ip;	}	public void setIp(String ip) {		this.ip = ip;	}	public String getDeviceId() {		return deviceId;	}	public void setDeviceId(String deviceId) {		this.deviceId = deviceId;	}	public String getUserName() {		return userName;	}	public void setUserName(String userName) {		this.userName = userName;	}	public String getOs() {		return os;	}	public void setOs(String os) {		this.os = os;	}	public String getOsVer() {		return osVer;	}	public void setOsVer(String osVer) {		this.osVer = osVer;	}	public Long getAccType() {		return accType;	}	public void setAccType(Long accType) {		this.accType = accType;	}	public String getChannel() {		return channel;	}	public void setChannel(String channel) {		this.channel = channel;	}	public String getVersion() {		return version;	}	public void setVersion(String version) {		this.version = version;	}
	
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
		LoginLog other = (LoginLog) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
