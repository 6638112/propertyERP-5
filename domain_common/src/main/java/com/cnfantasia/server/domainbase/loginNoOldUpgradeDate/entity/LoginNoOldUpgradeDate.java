package com.cnfantasia.server.domainbase.loginNoOldUpgradeDate.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(微信升级老数据) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class LoginNoOldUpgradeDate implements Serializable{
*/


public class LoginNoOldUpgradeDate extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 账号 */	private String userAccNo;	/** 登录账号类型 */	private Long userAccType;	/** 设备Id */	private String deviceId;	/** 渠道Id */	private String channel;	/** 处理状态 */	private Integer dealStatus;	/** 用户Id */	private BigInteger userId;
	public LoginNoOldUpgradeDate(){
	}
	public LoginNoOldUpgradeDate(LoginNoOldUpgradeDate arg){
		this.id = arg.getId();		this.userAccNo = arg.getUserAccNo();		this.userAccType = arg.getUserAccType();		this.deviceId = arg.getDeviceId();		this.channel = arg.getChannel();		this.dealStatus = arg.getDealStatus();		this.userId = arg.getUserId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param userAccNo 账号	 * @param userAccType 登录账号类型	 * @param deviceId 设备Id	 * @param channel 渠道Id	 * @param dealStatus 处理状态	 * @param userId 用户Id	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public LoginNoOldUpgradeDate(BigInteger id,String userAccNo,Long userAccType,String deviceId,String channel,Integer dealStatus,BigInteger userId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.userAccNo = userAccNo;		this.userAccType = userAccType;		this.deviceId = deviceId;		this.channel = channel;		this.dealStatus = dealStatus;		this.userId = userId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("userAccNo=").append(userAccNo).append(";");		sbf.append("userAccType=").append(userAccType).append(";");		sbf.append("deviceId=").append(deviceId).append(";");		sbf.append("channel=").append(channel).append(";");		sbf.append("dealStatus=").append(dealStatus).append(";");		sbf.append("userId=").append(userId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getUserAccNo() {		return userAccNo;	}	public void setUserAccNo(String userAccNo) {		this.userAccNo = userAccNo;	}	public Long getUserAccType() {		return userAccType;	}	public void setUserAccType(Long userAccType) {		this.userAccType = userAccType;	}	public String getDeviceId() {		return deviceId;	}	public void setDeviceId(String deviceId) {		this.deviceId = deviceId;	}	public String getChannel() {		return channel;	}	public void setChannel(String channel) {		this.channel = channel;	}	public Integer getDealStatus() {		return dealStatus;	}	public void setDealStatus(Integer dealStatus) {		this.dealStatus = dealStatus;	}	public BigInteger getUserId() {		return userId;	}	public void setUserId(BigInteger userId) {		this.userId = userId;	}
	
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
		LoginNoOldUpgradeDate other = (LoginNoOldUpgradeDate) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
