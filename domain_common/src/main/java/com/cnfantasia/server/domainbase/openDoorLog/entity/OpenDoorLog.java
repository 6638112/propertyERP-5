package com.cnfantasia.server.domainbase.openDoorLog.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(门禁开门记录表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class OpenDoorLog implements Serializable{
*/


public class OpenDoorLog extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 操作系统信息 */	private String os;	/** 开门状态 */	private Integer status;	/** 手机 */	private Integer phoneDevice;	/** 锁定状态 */	private String failReason;	/** 楼栋id */	private BigInteger tBuildingFId;	/** 开锁人id */	private BigInteger tUserId;	/** 开门日期 */	private String openDoorTime;
	public OpenDoorLog(){
	}
	public OpenDoorLog(OpenDoorLog arg){
		this.id = arg.getId();		this.os = arg.getOs();		this.status = arg.getStatus();		this.phoneDevice = arg.getPhoneDevice();		this.failReason = arg.getFailReason();		this.tBuildingFId = arg.gettBuildingFId();		this.tUserId = arg.gettUserId();		this.openDoorTime = arg.getOpenDoorTime();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param os 操作系统信息	 * @param status 开门状态	 * @param phoneDevice 手机	 * @param failReason 锁定状态	 * @param tBuildingFId 楼栋id	 * @param tUserId 开锁人id	 * @param openDoorTime 开门日期	 * @param sys0AddTime 申请时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public OpenDoorLog(BigInteger id,String os,Integer status,Integer phoneDevice,String failReason,BigInteger tBuildingFId,BigInteger tUserId,String openDoorTime,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.os = os;		this.status = status;		this.phoneDevice = phoneDevice;		this.failReason = failReason;		this.tBuildingFId = tBuildingFId;		this.tUserId = tUserId;		this.openDoorTime = openDoorTime;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("os=").append(os).append(";");		sbf.append("status=").append(status).append(";");		sbf.append("phoneDevice=").append(phoneDevice).append(";");		sbf.append("failReason=").append(failReason).append(";");		sbf.append("tBuildingFId=").append(tBuildingFId).append(";");		sbf.append("tUserId=").append(tUserId).append(";");		sbf.append("openDoorTime=").append(openDoorTime).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getOs() {		return os;	}	public void setOs(String os) {		this.os = os;	}	public Integer getStatus() {		return status;	}	public void setStatus(Integer status) {		this.status = status;	}	public Integer getPhoneDevice() {		return phoneDevice;	}	public void setPhoneDevice(Integer phoneDevice) {		this.phoneDevice = phoneDevice;	}	public String getFailReason() {		return failReason;	}	public void setFailReason(String failReason) {		this.failReason = failReason;	}	public BigInteger gettBuildingFId() {		return tBuildingFId;	}	public void settBuildingFId(BigInteger tBuildingFId) {		this.tBuildingFId = tBuildingFId;	}	public BigInteger gettUserId() {		return tUserId;	}	public void settUserId(BigInteger tUserId) {		this.tUserId = tUserId;	}	public String getOpenDoorTime() {		return openDoorTime;	}	public void setOpenDoorTime(String openDoorTime) {		this.openDoorTime = openDoorTime;	}
	
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
		OpenDoorLog other = (OpenDoorLog) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
