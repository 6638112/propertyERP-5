package com.cnfantasia.server.api.access.entity;

import java.math.BigInteger;

public class UserCarpushMsg{
	private BigInteger userId;
	private BigInteger defaultRoomId;
	private String carMsgtitle;
	private String msgDesc;
	private String expiredate;
	private UserCarNummsg userCar;
	public UserCarNummsg getUserCar() {
		return userCar;
	}
	public void setUserCar(UserCarNummsg userCar) {
		this.userCar = userCar;
	}
	public BigInteger getDefaultRoomId() {
		return defaultRoomId;
	}
	public void setDefaultRoomId(BigInteger defaultRoomId) {
		this.defaultRoomId = defaultRoomId;
	}
	public BigInteger getUserId() {
		return userId;
	}
	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}
	public String getCarMsgtitle() {
		return "您的"+this.userCar.getPlatenumber()+"的车费即将到期";
	}
	public void setCarMsgtitle(String carMsgtitle) {
		this.carMsgtitle = carMsgtitle;
	}
	public String getMsgDesc() {
		return "您的"+this.userCar.getPlatenumber()+"将在  "+this.expiredate+" 到期,请尽快缴费";
	}
	public void setMsgDesc(String msgDesc) {
		this.msgDesc = msgDesc;
	}
	public String getExprideTime() {
		return expiredate;
	}
	public void setExprideTime(String expiredate) {
		this.expiredate = expiredate;
	}
	
}
