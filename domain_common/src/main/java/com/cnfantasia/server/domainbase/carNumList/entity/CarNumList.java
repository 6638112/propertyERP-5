package com.cnfantasia.server.domainbase.carNumList.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
import java.lang.String;
import java.lang.Integer;
import java.lang.Long;

 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(车牌表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class CarNumList implements Serializable{
*/


public class CarNumList extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */
	private BigInteger id;
	/** 车牌号 */
	private String tCarNum;
	/** 是否月卡 */
	private Integer status;
	/** 来源 */
	private Integer from;
	/** 次缴卡为剩余金额，月卡车为每月卡费 */
	private Long fee;
	/** 锁定状态 */
	private Integer lockStatus;
	/** 所属小区id */
	private BigInteger tGroupBuildingFId;
	/** 所属real room id(已不用) */
	private BigInteger realRoomId;
	/** 过期日 */
	private String expireDate;
	/**  */
	private String buildingNum;
	/**  */
	private String unitNum;
	/**  */
	private String roomNum;

	public CarNumList(){
	}
	public CarNumList(CarNumList arg){
		this.id = arg.getId();
		this.tCarNum = arg.gettCarNum();
		this.status = arg.getStatus();
		this.from = arg.getFrom();
		this.fee = arg.getFee();
		this.lockStatus = arg.getLockStatus();
		this.tGroupBuildingFId = arg.gettGroupBuildingFId();
		this.realRoomId = arg.getRealRoomId();
		this.expireDate = arg.getExpireDate();
		this.buildingNum = arg.getBuildingNum();
		this.unitNum = arg.getUnitNum();
		this.roomNum = arg.getRoomNum();
		this.sys0AddTime = arg.getSys0AddTime();
		this.sys0UpdTime = arg.getSys0UpdTime();
		this.sys0DelTime = arg.getSys0DelTime();
		this.sys0AddUser = arg.getSys0AddUser();
		this.sys0UpdUser = arg.getSys0UpdUser();
		this.sys0DelUser = arg.getSys0DelUser();
		this.sys0DelState = arg.getSys0DelState();

	}
	/**
	 * 
	 * @param id 
	 * @param tCarNum 车牌号
	 * @param status 是否月卡
	 * @param from 来源
	 * @param fee 次缴卡为剩余金额，月卡车为每月卡费
	 * @param lockStatus 锁定状态
	 * @param tGroupBuildingFId 所属小区id
	 * @param realRoomId 所属real room id(已不用)
	 * @param expireDate 过期日
	 * @param buildingNum 
	 * @param unitNum 
	 * @param roomNum 
	 * @param sys0AddTime 申请时间
	 * @param sys0UpdTime 更新时间
	 * @param sys0DelTime 删除时间
	 * @param sys0AddUser 新增者
	 * @param sys0UpdUser 修改者
	 * @param sys0DelUser 删除者
	 * @param sys0DelState 记录状态
	 */

	public CarNumList(BigInteger id,String tCarNum,Integer status,Integer from,Long fee,Integer lockStatus,BigInteger tGroupBuildingFId,BigInteger realRoomId,String expireDate,String buildingNum,String unitNum,String roomNum,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;
		this.tCarNum = tCarNum;
		this.status = status;
		this.from = from;
		this.fee = fee;
		this.lockStatus = lockStatus;
		this.tGroupBuildingFId = tGroupBuildingFId;
		this.realRoomId = realRoomId;
		this.expireDate = expireDate;
		this.buildingNum = buildingNum;
		this.unitNum = unitNum;
		this.roomNum = roomNum;
		this.sys0AddTime = sys0AddTime;
		this.sys0UpdTime = sys0UpdTime;
		this.sys0DelTime = sys0DelTime;
		this.sys0AddUser = sys0AddUser;
		this.sys0UpdUser = sys0UpdUser;
		this.sys0DelUser = sys0DelUser;
		this.sys0DelState = sys0DelState;

	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("id=").append(id).append(";");
		sbf.append("tCarNum=").append(tCarNum).append(";");
		sbf.append("status=").append(status).append(";");
		sbf.append("from=").append(from).append(";");
		sbf.append("fee=").append(fee).append(";");
		sbf.append("lockStatus=").append(lockStatus).append(";");
		sbf.append("tGroupBuildingFId=").append(tGroupBuildingFId).append(";");
		sbf.append("realRoomId=").append(realRoomId).append(";");
		sbf.append("expireDate=").append(expireDate).append(";");
		sbf.append("buildingNum=").append(buildingNum).append(";");
		sbf.append("unitNum=").append(unitNum).append(";");
		sbf.append("roomNum=").append(roomNum).append(";");
		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");
		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");
		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");
		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");
		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");
		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");
		sbf.append("sys0DelState=").append(sys0DelState).append(";");
		return sbf.toString();

	}
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String gettCarNum() {
		return tCarNum;
	}
	public void settCarNum(String tCarNum) {
		this.tCarNum = tCarNum;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getFrom() {
		return from;
	}
	public void setFrom(Integer from) {
		this.from = from;
	}
	public Long getFee() {
		return fee;
	}
	public void setFee(Long fee) {
		this.fee = fee;
	}
	public Integer getLockStatus() {
		return lockStatus;
	}
	public void setLockStatus(Integer lockStatus) {
		this.lockStatus = lockStatus;
	}
	public BigInteger gettGroupBuildingFId() {
		return tGroupBuildingFId;
	}
	public void settGroupBuildingFId(BigInteger tGroupBuildingFId) {
		this.tGroupBuildingFId = tGroupBuildingFId;
	}
	public BigInteger getRealRoomId() {
		return realRoomId;
	}
	public void setRealRoomId(BigInteger realRoomId) {
		this.realRoomId = realRoomId;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public String getBuildingNum() {
		return buildingNum;
	}
	public void setBuildingNum(String buildingNum) {
		this.buildingNum = buildingNum;
	}
	public String getUnitNum() {
		return unitNum;
	}
	public void setUnitNum(String unitNum) {
		this.unitNum = unitNum;
	}
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
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
		CarNumList other = (CarNumList) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}
	
}
