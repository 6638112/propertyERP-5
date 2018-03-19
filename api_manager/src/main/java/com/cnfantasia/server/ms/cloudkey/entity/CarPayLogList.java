package com.cnfantasia.server.ms.cloudkey.entity;

import java.math.BigInteger;

public class CarPayLogList {
	private BigInteger pcId;
	private String pcName;
	/**停车场*/
	private String parking;
	/**小区*/
	private String gbName;
	private String buildingname;
	private String unitName;
	private String roomNum;
	private String carNum;
	private String userName;
	private String phoneNum;
	private String payTime;
	private Integer carType;
	private Long payNum;
	private Long payMoney;
	private Long couponAmount;
	private Long wyCouponAmount;
	private Integer payType;
	private String payFlown;
	private Integer needBill;
	private String payMethod;

	public BigInteger getPcId() {
		return pcId;
	}

	public void setPcId(BigInteger pcId) {
		this.pcId = pcId;
	}

	public String getPcName() {
		return pcName;
	}

	public void setPcName(String pcName) {
		this.pcName = pcName;
	}

	public String getParking() {
		return parking;
	}

	public void setParking(String parking) {
		this.parking = parking;
	}

	public String getBuildingname() {
		return buildingname;
	}

	public void setBuildingname(String buildingname) {
		this.buildingname = buildingname;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public Integer getCarType() {
		return carType;
	}

	public void setCarType(Integer carType) {
		this.carType = carType;
	}

	public Long getPayNum() {
		return payNum;
	}

	public void setPayNum(Long payNum) {
		this.payNum = payNum;
	}

	public Long getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Long payMoney) {
		this.payMoney = payMoney;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getPayFlown() {
		return payFlown;
	}

	public void setPayFlown(String payFlown) {
		this.payFlown = payFlown;
	}

	public Integer getNeedBill() {
		return needBill;
	}

	public void setNeedBill(Integer needBill) {
		this.needBill = needBill;
	}

	public Long getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(Long couponAmount) {
		this.couponAmount = couponAmount;
	}

	public String getPayMethod() {
		return getPayWay(payMethod);
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	/**
	 * "1":"微信支付","2":"支付宝","3":"银联支付","4":"纯粮票支付","5":"纯积分支付","6":"微信轻应用支付","7":"纯优惠券支付","9":"双乾支付"
	 * 
	 * @param payMethod
	 * @return
	 */
	private final String getPayWay(String payMethod){
		if("1".equals(payMethod)){
			return "微信支付";
		} else if("2".equals(payMethod)){
			return "支付宝";
		} else if("3".equals(payMethod)){
			return "银联支付";
		} else if("4".equals(payMethod)){
			return "纯粮票支付";
		} else if("5".equals(payMethod)){
			return "纯积分支付";
		} else if("6".equals(payMethod)){
			return "微信轻应用支付";
		} else if("7".equals(payMethod)){
			return "纯优惠券支付";
		} else if("9".equals(payMethod)){
			return "双乾支付";
		} 
		return "";
	}

	public String getGbName() {
		return gbName;
	}

	public void setGbName(String gbName) {
		this.gbName = gbName;
	}

	public Long getWyCouponAmount() {
		return wyCouponAmount;
	}

	public void setWyCouponAmount(Long wyCouponAmount) {
		this.wyCouponAmount = wyCouponAmount;
	}
	
}
