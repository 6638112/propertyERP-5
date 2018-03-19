package com.cnfantasia.server.domainbase.msPrizeActivity.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(抽奖活动表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class MsPrizeActivity implements Serializable{
*/


public class MsPrizeActivity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 活动名称 */	private String name;	/** 活动开始时间 */	private String startTime;	/** 活动结束时间 */	private String endTime;	/** 分享的文案信息 */	private String shareText;	/** 分享的图标地址 */	private String shareIcon;	/** 活动状态 */	private Integer activityStatus;	/** 最近更新时间 */	private String lastUpdTime;
	public MsPrizeActivity(){
	}
	public MsPrizeActivity(MsPrizeActivity arg){
		this.id = arg.getId();		this.name = arg.getName();		this.startTime = arg.getStartTime();		this.endTime = arg.getEndTime();		this.shareText = arg.getShareText();		this.shareIcon = arg.getShareIcon();		this.activityStatus = arg.getActivityStatus();		this.lastUpdTime = arg.getLastUpdTime();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param name 活动名称	 * @param startTime 活动开始时间	 * @param endTime 活动结束时间	 * @param shareText 分享的文案信息	 * @param shareIcon 分享的图标地址	 * @param activityStatus 活动状态	 * @param lastUpdTime 最近更新时间	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public MsPrizeActivity(BigInteger id,String name,String startTime,String endTime,String shareText,String shareIcon,Integer activityStatus,String lastUpdTime,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.name = name;		this.startTime = startTime;		this.endTime = endTime;		this.shareText = shareText;		this.shareIcon = shareIcon;		this.activityStatus = activityStatus;		this.lastUpdTime = lastUpdTime;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("startTime=").append(startTime).append(";");		sbf.append("endTime=").append(endTime).append(";");		sbf.append("shareText=").append(shareText).append(";");		sbf.append("shareIcon=").append(shareIcon).append(";");		sbf.append("activityStatus=").append(activityStatus).append(";");		sbf.append("lastUpdTime=").append(lastUpdTime).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public String getStartTime() {		return startTime;	}	public void setStartTime(String startTime) {		this.startTime = startTime;	}	public String getEndTime() {		return endTime;	}	public void setEndTime(String endTime) {		this.endTime = endTime;	}	public String getShareText() {		return shareText;	}	public void setShareText(String shareText) {		this.shareText = shareText;	}	public String getShareIcon() {		return shareIcon;	}	public void setShareIcon(String shareIcon) {		this.shareIcon = shareIcon;	}	public Integer getActivityStatus() {		return activityStatus;	}	public void setActivityStatus(Integer activityStatus) {		this.activityStatus = activityStatus;	}	public String getLastUpdTime() {		return lastUpdTime;	}	public void setLastUpdTime(String lastUpdTime) {		this.lastUpdTime = lastUpdTime;	}
	
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
		MsPrizeActivity other = (MsPrizeActivity) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
