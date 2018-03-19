package com.cnfantasia.server.domainbase.carNumPayLog.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;

 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(月卡车缴费记录) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class CarNumPayLog implements Serializable{
*/


public class CarNumPayLog extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */
	private BigInteger id;
	/** 车牌Id */
	private BigInteger tCarNumId;
	/** 订单Id */
	private BigInteger tEbuyOrderId;
	/** 是否解放区缴费 */
	private Integer status;
	/** 推送次数 */
	private Integer pushTimes;
	/** 下次发送时间 */
	private String sendTime;
	/** 推送状态 */
	private Integer pushStatus;
	/** 月卡购买月数 */
	private Long payNum;
	/** 月卡车缴费开始有效期 */
	private String payStartDate;
	/** 月卡车缴费截至有效期 */
	private String payEndDate;
	/** 缴费金额 */
	private Long fee;
	/** 随机立减优惠金额（单位 */
	private Long couponAmount;
	/** 应收费用（第三方车禁需要，单位 */
	private Long receivableFee;
	/** 华鹏飞临停车cardsn */
	private String cardsn;
	/** 购买时间 */
	private String payTime;
	/** 临停车缴费小区id */
	private BigInteger tGroupBuildingFId;
	/** 现金流生成状态 */
	private Integer cashStatus;
	/** 随机立减 */
	private BigInteger tCarPreferFId;
	/** 是否需要发票 */
	private Integer needBill;

	public CarNumPayLog(){
	}
	public CarNumPayLog(CarNumPayLog arg){
		this.id = arg.getId();
		this.tCarNumId = arg.gettCarNumId();
		this.tEbuyOrderId = arg.gettEbuyOrderId();
		this.status = arg.getStatus();
		this.pushTimes = arg.getPushTimes();
		this.sendTime = arg.getSendTime();
		this.pushStatus = arg.getPushStatus();
		this.payNum = arg.getPayNum();
		this.payStartDate = arg.getPayStartDate();
		this.payEndDate = arg.getPayEndDate();
		this.fee = arg.getFee();
		this.couponAmount = arg.getCouponAmount();
		this.receivableFee = arg.getReceivableFee();
		this.cardsn = arg.getCardsn();
		this.payTime = arg.getPayTime();
		this.tGroupBuildingFId = arg.gettGroupBuildingFId();
		this.sys0AddTime = arg.getSys0AddTime();
		this.sys0UpdTime = arg.getSys0UpdTime();
		this.sys0DelTime = arg.getSys0DelTime();
		this.sys0AddUser = arg.getSys0AddUser();
		this.sys0UpdUser = arg.getSys0UpdUser();
		this.sys0DelUser = arg.getSys0DelUser();
		this.sys0DelState = arg.getSys0DelState();
		this.cashStatus = arg.getCashStatus();
		this.tCarPreferFId = arg.gettCarPreferFId();
		this.needBill = arg.getNeedBill();

	}
	/**
	 * 
	 * @param id 
	 * @param tCarNumId 车牌Id
	 * @param tEbuyOrderId 订单Id
	 * @param status 是否解放区缴费
	 * @param pushTimes 推送次数
	 * @param sendTime 下次发送时间
	 * @param pushStatus 推送状态
	 * @param payNum 月卡购买月数
	 * @param payStartDate 月卡车缴费开始有效期
	 * @param payEndDate 月卡车缴费截至有效期
	 * @param fee 缴费金额
	 * @param couponAmount 随机立减优惠金额（单位
	 * @param receivableFee 应收费用（第三方车禁需要，单位
	 * @param cardsn 华鹏飞临停车cardsn
	 * @param payTime 购买时间
	 * @param tGroupBuildingFId 临停车缴费小区id
	 * @param sys0AddTime 新增时间
	 * @param sys0UpdTime 更新时间
	 * @param sys0DelTime 删除时间
	 * @param sys0AddUser 新增者
	 * @param sys0UpdUser 修改者
	 * @param sys0DelUser 删除者
	 * @param sys0DelState 记录状态
	 * @param cashStatus 现金流生成状态
	 * @param tCarPreferFId 随机立减
	 * @param needBill 是否需要发票
	 */

	public CarNumPayLog(BigInteger id,BigInteger tCarNumId,BigInteger tEbuyOrderId,Integer status,Integer pushTimes,String sendTime,Integer pushStatus,Long payNum,String payStartDate,String payEndDate,Long fee,Long couponAmount,Long receivableFee,String cardsn,String payTime,BigInteger tGroupBuildingFId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,Integer cashStatus,BigInteger tCarPreferFId,Integer needBill){
		this.id = id;
		this.tCarNumId = tCarNumId;
		this.tEbuyOrderId = tEbuyOrderId;
		this.status = status;
		this.pushTimes = pushTimes;
		this.sendTime = sendTime;
		this.pushStatus = pushStatus;
		this.payNum = payNum;
		this.payStartDate = payStartDate;
		this.payEndDate = payEndDate;
		this.fee = fee;
		this.couponAmount = couponAmount;
		this.receivableFee = receivableFee;
		this.cardsn = cardsn;
		this.payTime = payTime;
		this.tGroupBuildingFId = tGroupBuildingFId;
		this.sys0AddTime = sys0AddTime;
		this.sys0UpdTime = sys0UpdTime;
		this.sys0DelTime = sys0DelTime;
		this.sys0AddUser = sys0AddUser;
		this.sys0UpdUser = sys0UpdUser;
		this.sys0DelUser = sys0DelUser;
		this.sys0DelState = sys0DelState;
		this.cashStatus = cashStatus;
		this.tCarPreferFId = tCarPreferFId;
		this.needBill = needBill;

	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("id=").append(id).append(";");
		sbf.append("tCarNumId=").append(tCarNumId).append(";");
		sbf.append("tEbuyOrderId=").append(tEbuyOrderId).append(";");
		sbf.append("status=").append(status).append(";");
		sbf.append("pushTimes=").append(pushTimes).append(";");
		sbf.append("sendTime=").append(sendTime).append(";");
		sbf.append("pushStatus=").append(pushStatus).append(";");
		sbf.append("payNum=").append(payNum).append(";");
		sbf.append("payStartDate=").append(payStartDate).append(";");
		sbf.append("payEndDate=").append(payEndDate).append(";");
		sbf.append("fee=").append(fee).append(";");
		sbf.append("couponAmount=").append(couponAmount).append(";");
		sbf.append("receivableFee=").append(receivableFee).append(";");
		sbf.append("cardsn=").append(cardsn).append(";");
		sbf.append("payTime=").append(payTime).append(";");
		sbf.append("tGroupBuildingFId=").append(tGroupBuildingFId).append(";");
		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");
		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");
		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");
		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");
		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");
		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");
		sbf.append("sys0DelState=").append(sys0DelState).append(";");
		sbf.append("cashStatus=").append(cashStatus).append(";");
		sbf.append("tCarPreferFId=").append(tCarPreferFId).append(";");
		sbf.append("needBill=").append(needBill).append(";");
		return sbf.toString();

	}
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public BigInteger gettCarNumId() {
		return tCarNumId;
	}
	public void settCarNumId(BigInteger tCarNumId) {
		this.tCarNumId = tCarNumId;
	}
	public BigInteger gettEbuyOrderId() {
		return tEbuyOrderId;
	}
	public void settEbuyOrderId(BigInteger tEbuyOrderId) {
		this.tEbuyOrderId = tEbuyOrderId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public Long getPayNum() {
		return payNum;
	}
	public void setPayNum(Long payNum) {
		this.payNum = payNum;
	}
	public String getPayStartDate() {
		return payStartDate;
	}
	public void setPayStartDate(String payStartDate) {
		this.payStartDate = payStartDate;
	}
	public String getPayEndDate() {
		return payEndDate;
	}
	public void setPayEndDate(String payEndDate) {
		this.payEndDate = payEndDate;
	}
	public Long getFee() {
		return fee;
	}
	public void setFee(Long fee) {
		this.fee = fee;
	}
	public Long getCouponAmount() {
		return couponAmount;
	}
	public void setCouponAmount(Long couponAmount) {
		this.couponAmount = couponAmount;
	}
	public Long getReceivableFee() {
		return receivableFee;
	}
	public void setReceivableFee(Long receivableFee) {
		this.receivableFee = receivableFee;
	}
	public String getCardsn() {
		return cardsn;
	}
	public void setCardsn(String cardsn) {
		this.cardsn = cardsn;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public BigInteger gettGroupBuildingFId() {
		return tGroupBuildingFId;
	}
	public void settGroupBuildingFId(BigInteger tGroupBuildingFId) {
		this.tGroupBuildingFId = tGroupBuildingFId;
	}
	public Integer getCashStatus() {
		return cashStatus;
	}
	public void setCashStatus(Integer cashStatus) {
		this.cashStatus = cashStatus;
	}
	public BigInteger gettCarPreferFId() {
		return tCarPreferFId;
	}
	public void settCarPreferFId(BigInteger tCarPreferFId) {
		this.tCarPreferFId = tCarPreferFId;
	}
	public Integer getNeedBill() {
		return needBill;
	}
	public void setNeedBill(Integer needBill) {
		this.needBill = needBill;
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
		CarNumPayLog other = (CarNumPayLog) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}
	
}
