package com.cnfantasia.server.domainbase.flashDealRemind.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(一元Go提醒) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class FlashDealRemind implements Serializable{
*/


public class FlashDealRemind extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/**  */	private BigInteger tFlashDealActivityFId;	/** 活动开始时间（冗余） */	private String activityStartTime;	/**  */	private BigInteger tUserFId;	/** 提醒状态 */	private Integer remindStatus;	/** 提醒时间 */	private String remindTime;
	public FlashDealRemind(){
	}
	public FlashDealRemind(FlashDealRemind arg){
		this.id = arg.getId();		this.tFlashDealActivityFId = arg.gettFlashDealActivityFId();		this.activityStartTime = arg.getActivityStartTime();		this.tUserFId = arg.gettUserFId();		this.remindStatus = arg.getRemindStatus();		this.remindTime = arg.getRemindTime();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tFlashDealActivityFId 	 * @param activityStartTime 活动开始时间（冗余）	 * @param tUserFId 	 * @param remindStatus 提醒状态	 * @param remindTime 提醒时间	 * @param sys0AddTime 	 * @param sys0UpdTime 	 * @param sys0DelTime 	 * @param sys0AddUser 	 * @param sys0UpdUser 	 * @param sys0DelUser 	 * @param sys0DelState 	 */
	public FlashDealRemind(BigInteger id,BigInteger tFlashDealActivityFId,String activityStartTime,BigInteger tUserFId,Integer remindStatus,String remindTime,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tFlashDealActivityFId = tFlashDealActivityFId;		this.activityStartTime = activityStartTime;		this.tUserFId = tUserFId;		this.remindStatus = remindStatus;		this.remindTime = remindTime;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tFlashDealActivityFId=").append(tFlashDealActivityFId).append(";");		sbf.append("activityStartTime=").append(activityStartTime).append(";");		sbf.append("tUserFId=").append(tUserFId).append(";");		sbf.append("remindStatus=").append(remindStatus).append(";");		sbf.append("remindTime=").append(remindTime).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettFlashDealActivityFId() {		return tFlashDealActivityFId;	}	public void settFlashDealActivityFId(BigInteger tFlashDealActivityFId) {		this.tFlashDealActivityFId = tFlashDealActivityFId;	}	public String getActivityStartTime() {		return activityStartTime;	}	public void setActivityStartTime(String activityStartTime) {		this.activityStartTime = activityStartTime;	}	public BigInteger gettUserFId() {		return tUserFId;	}	public void settUserFId(BigInteger tUserFId) {		this.tUserFId = tUserFId;	}	public Integer getRemindStatus() {		return remindStatus;	}	public void setRemindStatus(Integer remindStatus) {		this.remindStatus = remindStatus;	}	public String getRemindTime() {		return remindTime;	}	public void setRemindTime(String remindTime) {		this.remindTime = remindTime;	}
	
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
		FlashDealRemind other = (FlashDealRemind) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
