package com.cnfantasia.server.domainbase.carXmfMsg.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
import java.lang.String;
import java.lang.Long;
import java.lang.Integer;

 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(小蜜蜂临停车缴费通知记录) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class CarXmfMsg implements Serializable{
*/


public class CarXmfMsg extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */
	private BigInteger id;
	/**  */
	private BigInteger tCarNumPayLogFId;
	/** 小蜜蜂的订单id */
	private String tXmfOrderId;
	/** 优惠金额（单位 */
	private Long discountAmount;
	/** 用户付款金额（单位 */
	private Long fee;
	/** 缴费状态 */
	private Integer payStatus;
	/** 下次发送时间 */
	private String sendTime;
	/** 推送次数 */
	private Integer pushTimes;
	/** 推送状态 */
	private Integer pushStatus;
	/** 车辆类型 */
	private Integer carType;
	/** 小蜜蜂的carid */
	private String xmfCarId;

	public CarXmfMsg(){
	}
	public CarXmfMsg(CarXmfMsg arg){
		this.id = arg.getId();
		this.tCarNumPayLogFId = arg.gettCarNumPayLogFId();
		this.tXmfOrderId = arg.gettXmfOrderId();
		this.discountAmount = arg.getDiscountAmount();
		this.fee = arg.getFee();
		this.payStatus = arg.getPayStatus();
		this.sendTime = arg.getSendTime();
		this.pushTimes = arg.getPushTimes();
		this.pushStatus = arg.getPushStatus();
		this.sys0AddTime = arg.getSys0AddTime();
		this.sys0UpdTime = arg.getSys0UpdTime();
		this.sys0DelTime = arg.getSys0DelTime();
		this.sys0AddUser = arg.getSys0AddUser();
		this.sys0UpdUser = arg.getSys0UpdUser();
		this.sys0DelUser = arg.getSys0DelUser();
		this.sys0DelState = arg.getSys0DelState();
		this.carType = arg.getCarType();
		this.xmfCarId = arg.getXmfCarId();

	}
	/**
	 * 
	 * @param id 
	 * @param tCarNumPayLogFId 
	 * @param tXmfOrderId 小蜜蜂的订单id
	 * @param discountAmount 优惠金额（单位
	 * @param fee 用户付款金额（单位
	 * @param payStatus 缴费状态
	 * @param sendTime 下次发送时间
	 * @param pushTimes 推送次数
	 * @param pushStatus 推送状态
	 * @param sys0AddTime 
	 * @param sys0UpdTime 
	 * @param sys0DelTime 
	 * @param sys0AddUser 
	 * @param sys0UpdUser 
	 * @param sys0DelUser 
	 * @param sys0DelState 记录状态
	 * @param carType 车辆类型
	 * @param xmfCarId 小蜜蜂的carid
	 */

	public CarXmfMsg(BigInteger id,BigInteger tCarNumPayLogFId,String tXmfOrderId,Long discountAmount,Long fee,Integer payStatus,String sendTime,Integer pushTimes,Integer pushStatus,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,Integer carType,String xmfCarId){
		this.id = id;
		this.tCarNumPayLogFId = tCarNumPayLogFId;
		this.tXmfOrderId = tXmfOrderId;
		this.discountAmount = discountAmount;
		this.fee = fee;
		this.payStatus = payStatus;
		this.sendTime = sendTime;
		this.pushTimes = pushTimes;
		this.pushStatus = pushStatus;
		this.sys0AddTime = sys0AddTime;
		this.sys0UpdTime = sys0UpdTime;
		this.sys0DelTime = sys0DelTime;
		this.sys0AddUser = sys0AddUser;
		this.sys0UpdUser = sys0UpdUser;
		this.sys0DelUser = sys0DelUser;
		this.sys0DelState = sys0DelState;
		this.carType = carType;
		this.xmfCarId = xmfCarId;

	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("id=").append(id).append(";");
		sbf.append("tCarNumPayLogFId=").append(tCarNumPayLogFId).append(";");
		sbf.append("tXmfOrderId=").append(tXmfOrderId).append(";");
		sbf.append("discountAmount=").append(discountAmount).append(";");
		sbf.append("fee=").append(fee).append(";");
		sbf.append("payStatus=").append(payStatus).append(";");
		sbf.append("sendTime=").append(sendTime).append(";");
		sbf.append("pushTimes=").append(pushTimes).append(";");
		sbf.append("pushStatus=").append(pushStatus).append(";");
		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");
		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");
		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");
		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");
		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");
		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");
		sbf.append("sys0DelState=").append(sys0DelState).append(";");
		sbf.append("carType=").append(carType).append(";");
		sbf.append("xmfCarId=").append(xmfCarId).append(";");
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
	public String gettXmfOrderId() {
		return tXmfOrderId;
	}
	public void settXmfOrderId(String tXmfOrderId) {
		this.tXmfOrderId = tXmfOrderId;
	}
	public Long getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(Long discountAmount) {
		this.discountAmount = discountAmount;
	}
	public Long getFee() {
		return fee;
	}
	public void setFee(Long fee) {
		this.fee = fee;
	}
	public Integer getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public Integer getPushTimes() {
		return pushTimes;
	}
	public void setPushTimes(Integer pushTimes) {
		this.pushTimes = pushTimes;
	}
	public Integer getPushStatus() {
		return pushStatus;
	}
	public void setPushStatus(Integer pushStatus) {
		this.pushStatus = pushStatus;
	}
	public Integer getCarType() {
		return carType;
	}
	public void setCarType(Integer carType) {
		this.carType = carType;
	}
	public String getXmfCarId() {
		return xmfCarId;
	}
	public void setXmfCarId(String xmfCarId) {
		this.xmfCarId = xmfCarId;
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
		CarXmfMsg other = (CarXmfMsg) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}
	
}
