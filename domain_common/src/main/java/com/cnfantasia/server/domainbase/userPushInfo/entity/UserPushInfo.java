package com.cnfantasia.server.domainbase.userPushInfo.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Long;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(用户推送配置消息) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class UserPushInfo implements Serializable{
*/


public class UserPushInfo extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 所属渠道 */	private Long tChannelSubFId;	/** 百度推送用户Id */	private String baiduUserId;	/** 百度推送渠道id */	private String baiduChannelId;	/** gt推送渠道id */	private String channelGt;	/** xm推送渠道id */	private String channelXm;	/** hw推送渠道id */	private String channelHw;	/** bd推送渠道id */	private String channelBd;	/** 用户Id */	private BigInteger userId;	/** 用户类别 */	private Integer userType;	/** 用户手机品牌（华为 */	private String userAgent;	/** app版本号 */	private String appVersion;
	public UserPushInfo(){
	}
	public UserPushInfo(UserPushInfo arg){
		this.id = arg.getId();		this.tChannelSubFId = arg.gettChannelSubFId();		this.baiduUserId = arg.getBaiduUserId();		this.baiduChannelId = arg.getBaiduChannelId();		this.channelGt = arg.getChannelGt();		this.channelXm = arg.getChannelXm();		this.channelHw = arg.getChannelHw();		this.channelBd = arg.getChannelBd();		this.userId = arg.getUserId();		this.userType = arg.getUserType();		this.userAgent = arg.getUserAgent();		this.appVersion = arg.getAppVersion();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tChannelSubFId 所属渠道	 * @param baiduUserId 百度推送用户Id	 * @param baiduChannelId 百度推送渠道id	 * @param channelGt gt推送渠道id	 * @param channelXm xm推送渠道id	 * @param channelHw hw推送渠道id	 * @param channelBd bd推送渠道id	 * @param userId 用户Id	 * @param userType 用户类别	 * @param userAgent 用户手机品牌（华为	 * @param appVersion app版本号	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public UserPushInfo(BigInteger id,Long tChannelSubFId,String baiduUserId,String baiduChannelId,String channelGt,String channelXm,String channelHw,String channelBd,BigInteger userId,Integer userType,String userAgent,String appVersion,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tChannelSubFId = tChannelSubFId;		this.baiduUserId = baiduUserId;		this.baiduChannelId = baiduChannelId;		this.channelGt = channelGt;		this.channelXm = channelXm;		this.channelHw = channelHw;		this.channelBd = channelBd;		this.userId = userId;		this.userType = userType;		this.userAgent = userAgent;		this.appVersion = appVersion;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tChannelSubFId=").append(tChannelSubFId).append(";");		sbf.append("baiduUserId=").append(baiduUserId).append(";");		sbf.append("baiduChannelId=").append(baiduChannelId).append(";");		sbf.append("channelGt=").append(channelGt).append(";");		sbf.append("channelXm=").append(channelXm).append(";");		sbf.append("channelHw=").append(channelHw).append(";");		sbf.append("channelBd=").append(channelBd).append(";");		sbf.append("userId=").append(userId).append(";");		sbf.append("userType=").append(userType).append(";");		sbf.append("userAgent=").append(userAgent).append(";");		sbf.append("appVersion=").append(appVersion).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public Long gettChannelSubFId() {		return tChannelSubFId;	}	public void settChannelSubFId(Long tChannelSubFId) {		this.tChannelSubFId = tChannelSubFId;	}	public String getBaiduUserId() {		return baiduUserId;	}	public void setBaiduUserId(String baiduUserId) {		this.baiduUserId = baiduUserId;	}	public String getBaiduChannelId() {		return baiduChannelId;	}	public void setBaiduChannelId(String baiduChannelId) {		this.baiduChannelId = baiduChannelId;	}	public String getChannelGt() {		return channelGt;	}	public void setChannelGt(String channelGt) {		this.channelGt = channelGt;	}	public String getChannelXm() {		return channelXm;	}	public void setChannelXm(String channelXm) {		this.channelXm = channelXm;	}	public String getChannelHw() {		return channelHw;	}	public void setChannelHw(String channelHw) {		this.channelHw = channelHw;	}	public String getChannelBd() {		return channelBd;	}	public void setChannelBd(String channelBd) {		this.channelBd = channelBd;	}	public BigInteger getUserId() {		return userId;	}	public void setUserId(BigInteger userId) {		this.userId = userId;	}	public Integer getUserType() {		return userType;	}	public void setUserType(Integer userType) {		this.userType = userType;	}	public String getUserAgent() {		return userAgent;	}	public void setUserAgent(String userAgent) {		this.userAgent = userAgent;	}	public String getAppVersion() {		return appVersion;	}	public void setAppVersion(String appVersion) {		this.appVersion = appVersion;	}
	
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
		UserPushInfo other = (UserPushInfo) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
