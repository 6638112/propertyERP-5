package com.cnfantasia.server.domainbase.parkingRecord.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Long;
import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(停车记录表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class ParkingRecord implements Serializable{
*/


public class ParkingRecord extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 停车时间 */	private String parkingStartTime;	/** 离开时间 */	private String parkingEndTime;	/** 车牌id */	private BigInteger tCarNumId;	/** 停车费 */	private Long parkingFee;
	/** 停车小区Id */
	private BigInteger tGroupBuildingId;
	public ParkingRecord(){
	}
	public ParkingRecord(ParkingRecord arg){
		this.id = arg.getId();		this.parkingStartTime = arg.getParkingStartTime();		this.parkingEndTime = arg.getParkingEndTime();		this.tCarNumId = arg.gettCarNumId();
		this.parkingFee = arg.getParkingFee();
		this.tGroupBuildingId = arg.gettGroupBuildingId();
		this.sys0AddTime = arg.getSys0AddTime();
		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();	}
	/**	 * 	 * @param id 	 * @param parkingStartTime 停车时间	 * @param parkingEndTime 离开时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelTime 删除时间	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public ParkingRecord(BigInteger id,String parkingStartTime,String parkingEndTime,BigInteger tCarNumId,Long parkingFee,BigInteger tGroupBuildingId,String sys0AddTime,String sys0UpdTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,String sys0DelTime,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.parkingStartTime = parkingStartTime;		this.parkingEndTime = parkingEndTime;		this.tCarNumId = tCarNumId;
		this.parkingFee = parkingFee;
		this.tGroupBuildingId = tGroupBuildingId;
		this.sys0AddTime = sys0AddTime;
		this.sys0UpdTime = sys0UpdTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelTime = sys0DelTime;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("parkingStartTime=").append(parkingStartTime).append(";");		sbf.append("parkingEndTime=").append(parkingEndTime).append(";");		sbf.append("tCarNumId=").append(tCarNumId).append(";");
		sbf.append("parkingFee=").append(parkingFee).append(";");
		sbf.append("tGroupBuildingId=").append(tGroupBuildingId).append(";");
		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");
		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getParkingStartTime() {		return parkingStartTime;	}	public void setParkingStartTime(String parkingStartTime) {		this.parkingStartTime = parkingStartTime;	}	public String getParkingEndTime() {		return parkingEndTime;	}	public void setParkingEndTime(String parkingEndTime) {		this.parkingEndTime = parkingEndTime;	}	public BigInteger gettCarNumId() {		return tCarNumId;	}	public void settCarNumId(BigInteger tCarNumId) {		this.tCarNumId = tCarNumId;	}	public Long getParkingFee() {		return parkingFee;	}	public void setParkingFee(Long parkingFee) {		this.parkingFee = parkingFee;	}
	public BigInteger gettGroupBuildingId() {
		return tGroupBuildingId;
	}
	public void settGroupBuildingId(BigInteger tGroupBuildingId) {
		this.tGroupBuildingId = tGroupBuildingId;
	}
	
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
		ParkingRecord other = (ParkingRecord) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
