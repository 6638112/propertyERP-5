package com.cnfantasia.server.domainbase.carYhsMsg.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
import java.lang.String;
import java.lang.Long;
import java.lang.Integer;

 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(临停车缴费消息发送表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class CarYhsMsg implements Serializable{
*/


public class CarYhsMsg extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */
	private BigInteger id;
	/**  */
	private BigInteger tCarNumPayLogFId;
	/** 停车小区 */
	private BigInteger tGroupBuildingFId;
	/** 车牌 */
	private String tCarNum;
	/** 停车费（单位元） */
	private Long fee;
	/** 缴费时间 */
	private String payTime;
	/** 发送状态 */
	private Integer sendStatus;

	public CarYhsMsg(){
	}
	public CarYhsMsg(CarYhsMsg arg){
		this.id = arg.getId();
		this.tCarNumPayLogFId = arg.gettCarNumPayLogFId();
		this.tGroupBuildingFId = arg.gettGroupBuildingFId();
		this.tCarNum = arg.gettCarNum();
		this.fee = arg.getFee();
		this.payTime = arg.getPayTime();
		this.sendStatus = arg.getSendStatus();
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
	 * @param tGroupBuildingFId 停车小区
	 * @param tCarNum 车牌
	 * @param fee 停车费（单位元）
	 * @param payTime 缴费时间
	 * @param sendStatus 发送状态
	 * @param sys0AddTime 
	 * @param sys0UpdTime 
	 * @param sys0DelTime 
	 * @param sys0AddUser 
	 * @param sys0UpdUser 
	 * @param sys0DelUser 
	 * @param sys0DelState 记录状态
	 */

	public CarYhsMsg(BigInteger id,BigInteger tCarNumPayLogFId,BigInteger tGroupBuildingFId,String tCarNum,Long fee,String payTime,Integer sendStatus,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;
		this.tCarNumPayLogFId = tCarNumPayLogFId;
		this.tGroupBuildingFId = tGroupBuildingFId;
		this.tCarNum = tCarNum;
		this.fee = fee;
		this.payTime = payTime;
		this.sendStatus = sendStatus;
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
		sbf.append("tGroupBuildingFId=").append(tGroupBuildingFId).append(";");
		sbf.append("tCarNum=").append(tCarNum).append(";");
		sbf.append("fee=").append(fee).append(";");
		sbf.append("payTime=").append(payTime).append(";");
		sbf.append("sendStatus=").append(sendStatus).append(";");
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
	public BigInteger gettGroupBuildingFId() {
		return tGroupBuildingFId;
	}
	public void settGroupBuildingFId(BigInteger tGroupBuildingFId) {
		this.tGroupBuildingFId = tGroupBuildingFId;
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
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public Integer getSendStatus() {
		return sendStatus;
	}
	public void setSendStatus(Integer sendStatus) {
		this.sendStatus = sendStatus;
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
		CarYhsMsg other = (CarYhsMsg) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}
	
}
