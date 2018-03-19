package com.cnfantasia.server.api.access.entity;

import java.math.BigInteger;


public class UserCarNummsg{
	private String cardname;
	private double fee;
	private BigInteger id;
	private Boolean monthcard;
	private String platenumber;
	private String groupbuildingName;
	private Integer carStatus;
	public String getCardname() {
		return cardname;
	}
	public void setCardname(String cardname) {
		this.cardname = cardname;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public Boolean getMonthcard() {
		return monthcard;
	}
	public void setMonthcard(Boolean monthcard) {
		this.monthcard = monthcard;
	}
	public String getPlatenumber() {
		return platenumber;
	}
	public void setPlatenumber(String platenumber) {
		this.platenumber = platenumber;
	}
	public String getGroupbuildingName() {
		return groupbuildingName;
	}
	public void setGroupbuildingName(String groupbuildingName) {
		this.groupbuildingName = groupbuildingName;
	}
	public Integer getCarStatus() {
		return carStatus;
	}
	public void setCarStatus(Integer carStatus) {
		this.carStatus = carStatus;
	}

}
