package com.cnfantasia.server.domainbase.newUserLocation.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.String;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(新用户第一次打开app定位结果信息) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class NewUserLocation implements Serializable{
*/


public class NewUserLocation extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 是否签约 */	private Integer signStatus;	/** 是否根据定位信息精确找到 */	private Integer hasfound;	/** 定位到的小区ID */	private BigInteger gbid;	/** 用户设备id */	private String deviceid;
	public NewUserLocation(){
	}
	public NewUserLocation(NewUserLocation arg){
		this.id = arg.getId();		this.signStatus = arg.getSignStatus();		this.hasfound = arg.getHasfound();		this.gbid = arg.getGbid();		this.deviceid = arg.getDeviceid();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param signStatus 是否签约	 * @param hasfound 是否根据定位信息精确找到	 * @param gbid 定位到的小区ID	 * @param deviceid 用户设备id	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public NewUserLocation(BigInteger id,Integer signStatus,Integer hasfound,BigInteger gbid,String deviceid,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.signStatus = signStatus;		this.hasfound = hasfound;		this.gbid = gbid;		this.deviceid = deviceid;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("signStatus=").append(signStatus).append(";");		sbf.append("hasfound=").append(hasfound).append(";");		sbf.append("gbid=").append(gbid).append(";");		sbf.append("deviceid=").append(deviceid).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public Integer getSignStatus() {		return signStatus;	}	public void setSignStatus(Integer signStatus) {		this.signStatus = signStatus;	}	public Integer getHasfound() {		return hasfound;	}	public void setHasfound(Integer hasfound) {		this.hasfound = hasfound;	}	public BigInteger getGbid() {		return gbid;	}	public void setGbid(BigInteger gbid) {		this.gbid = gbid;	}	public String getDeviceid() {		return deviceid;	}	public void setDeviceid(String deviceid) {		this.deviceid = deviceid;	}
	
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
		NewUserLocation other = (NewUserLocation) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
