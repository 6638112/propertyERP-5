package com.cnfantasia.server.domainbase.carHuaanMsg.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
import java.lang.String;
import java.lang.Long;
import java.lang.Integer;

 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(华安临停车缴费通知记录) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class CarHuaanMsg implements Serializable{
*/


public class CarHuaanMsg extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */
	private BigInteger id;
	/**  */
	private BigInteger tCarNumPayLogFId;
	/** 车牌 */
	private String tCarNum;
	/** 用户付款金额（单位 */
	private Long fee;
	/** 车辆类型 */
	private Integer carType;
	/** 缴费状态 */
	private Integer payStatus;
	/** 月卡开始时间 */
	private String startTime;
	/** 月卡截止时间 */
	private String endTime;
	/** 推送次数 */
	private Integer pushTimes;
	/** 发送时间 */
	private String sendTime;
	/** 推送状态 */
	private Integer pushStatus;

	public CarHuaanMsg(){
	}
	public CarHuaanMsg(CarHuaanMsg arg){
		this.id = arg.getId();
		this.tCarNumPayLogFId = arg.gettCarNumPayLogFId();
		this.tCarNum = arg.gettCarNum();
		this.fee = arg.getFee();
		this.carType = arg.getCarType();
		this.payStatus = arg.getPayStatus();
		this.startTime = arg.getStartTime();
		this.endTime = arg.getEndTime();
		this.pushTimes = arg.getPushTimes();
		this.sendTime = arg.getSendTime();
		this.pushStatus = arg.getPushStatus();
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
	 * @param tCarNumPayLogFId 
	 * @param tCarNum 车牌
	 * @param fee 用户付款金额（单位
	 * @param carType 车辆类型
	 * @param payStatus 缴费状态
	 * @param startTime 月卡开始时间
	 * @param endTime 月卡截止时间
	 * @param pushTimes 推送次数
	 * @param sendTime 发送时间
	 * @param pushStatus 推送状态
	 * @param sys0AddTime 
	 * @param sys0UpdTime 
	 * @param sys0DelTime 
	 * @param sys0AddUser 
	 * @param sys0UpdUser 
	 * @param sys0DelUser 
	 * @param sys0DelState 记录状态
	 */

	public CarHuaanMsg(BigInteger id,BigInteger tCarNumPayLogFId,String tCarNum,Long fee,Integer carType,Integer payStatus,String startTime,String endTime,Integer pushTimes,String sendTime,Integer pushStatus,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;
		this.tCarNumPayLogFId = tCarNumPayLogFId;
		this.tCarNum = tCarNum;
		this.fee = fee;
		this.carType = carType;
		this.payStatus = payStatus;
		this.startTime = startTime;
		this.endTime = endTime;
		this.pushTimes = pushTimes;
		this.sendTime = sendTime;
		this.pushStatus = pushStatus;
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
		sbf.append("tCarNumPayLogFId=").append(tCarNumPayLogFId).append(";");
		sbf.append("tCarNum=").append(tCarNum).append(";");
		sbf.append("fee=").append(fee).append(";");
		sbf.append("carType=").append(carType).append(";");
		sbf.append("payStatus=").append(payStatus).append(";");
		sbf.append("startTime=").append(startTime).append(";");
		sbf.append("endTime=").append(endTime).append(";");
		sbf.append("pushTimes=").append(pushTimes).append(";");
		sbf.append("sendTime=").append(sendTime).append(";");
		sbf.append("pushStatus=").append(pushStatus).append(";");
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
	public BigInteger gettCarNumPayLogFId() {
		return tCarNumPayLogFId;
	}
	public void settCarNumPayLogFId(BigInteger tCarNumPayLogFId) {
		this.tCarNumPayLogFId = tCarNumPayLogFId;
	}
	public String gettCarNum() {
		return tCarNum;
	}
	public void settCarNum(String tCarNum) {
		this.tCarNum = tCarNum;
	}
	public Long getFee() {
		return fee;
	}
	public void setFee(Long fee) {
		this.fee = fee;
	}
	public Integer getCarType() {
		return carType;
	}
	public void setCarType(Integer carType) {
		this.carType = carType;
	}
	public Integer getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getPushTimes() {
		return pushTimes;
	}
	public void setPushTimes(Integer pushTimes) {
		this.pushTimes = pushTimes;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public Integer getPushStatus() {
		return pushStatus;
	}
	public void setPushStatus(Integer pushStatus) {
		this.pushStatus = pushStatus;
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
		CarHuaanMsg other = (CarHuaanMsg) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}
	
}
