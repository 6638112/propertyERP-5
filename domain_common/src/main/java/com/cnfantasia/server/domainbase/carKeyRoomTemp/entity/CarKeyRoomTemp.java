package com.cnfantasia.server.domainbase.carKeyRoomTemp.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(门牌车牌信息临时表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class CarKeyRoomTemp implements Serializable{
*/


public class CarKeyRoomTemp extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 楼栋号 */	private String buildingNo;	/** 房间号 */	private String roomNo;	/** 车牌号 */	private String carNum;	/** 小区Id */	private BigInteger tGroupBuildingFId;	/** 是否月卡 */	private Integer isnotStatus;	/** 状态 */	private Integer status;
	public CarKeyRoomTemp(){
	}
	public CarKeyRoomTemp(CarKeyRoomTemp arg){
		this.id = arg.getId();		this.buildingNo = arg.getBuildingNo();		this.roomNo = arg.getRoomNo();		this.carNum = arg.getCarNum();		this.tGroupBuildingFId = arg.gettGroupBuildingFId();		this.isnotStatus = arg.getIsnotStatus();		this.status = arg.getStatus();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param buildingNo 楼栋号	 * @param roomNo 房间号	 * @param carNum 车牌号	 * @param tGroupBuildingFId 小区Id	 * @param isnotStatus 是否月卡	 * @param status 状态	 * @param sys0AddTime 申请时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public CarKeyRoomTemp(BigInteger id,String buildingNo,String roomNo,String carNum,BigInteger tGroupBuildingFId,Integer isnotStatus,Integer status,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.buildingNo = buildingNo;		this.roomNo = roomNo;		this.carNum = carNum;		this.tGroupBuildingFId = tGroupBuildingFId;		this.isnotStatus = isnotStatus;		this.status = status;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("buildingNo=").append(buildingNo).append(";");		sbf.append("roomNo=").append(roomNo).append(";");		sbf.append("carNum=").append(carNum).append(";");		sbf.append("tGroupBuildingFId=").append(tGroupBuildingFId).append(";");		sbf.append("isnotStatus=").append(isnotStatus).append(";");		sbf.append("status=").append(status).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getBuildingNo() {		return buildingNo;	}	public void setBuildingNo(String buildingNo) {		this.buildingNo = buildingNo;	}	public String getRoomNo() {		return roomNo;	}	public void setRoomNo(String roomNo) {		this.roomNo = roomNo;	}	public String getCarNum() {		return carNum;	}	public void setCarNum(String carNum) {		this.carNum = carNum;	}	public BigInteger gettGroupBuildingFId() {		return tGroupBuildingFId;	}	public void settGroupBuildingFId(BigInteger tGroupBuildingFId) {		this.tGroupBuildingFId = tGroupBuildingFId;	}	public Integer getIsnotStatus() {		return isnotStatus;	}	public void setIsnotStatus(Integer isnotStatus) {		this.isnotStatus = isnotStatus;	}	public Integer getStatus() {		return status;	}	public void setStatus(Integer status) {		this.status = status;	}
	
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
		CarKeyRoomTemp other = (CarKeyRoomTemp) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
