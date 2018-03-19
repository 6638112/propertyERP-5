package com.cnfantasia.server.api.access.entity;

import com.cnfantasia.server.domainbase.carNumList.entity.CarNumList;

public class CarDetailEntity extends CarNumList {
	private Integer isBuyFinance;
	private Double buyMoney;
	private Double returnMoney;
	private String deduBeginTm;
	private String deduEndTm;
	/** 是否解除绑定 */
	private Integer isRelieve;
	private String gbName;
	private Integer carMonthIsOpen;
	private Integer needBillIsOpen;

	public Integer getIsBuyFinance() {
		return isBuyFinance;
	}

	public void setIsBuyFinance(Integer isBuyFinance) {
		this.isBuyFinance = isBuyFinance;
	}

	public Double getBuyMoney() {
		return buyMoney;
	}

	public void setBuyMoney(Double buyMoney) {
		this.buyMoney = buyMoney;
	}

	public Double getReturnMoney() {
		return returnMoney;
	}

	public void setReturnMoney(Double returnMoney) {
		this.returnMoney = returnMoney;
	}

	public String getDeduBeginTm() {
		return deduBeginTm;
	}

	public void setDeduBeginTm(String deduBeginTm) {
		this.deduBeginTm = deduBeginTm;
	}

	public String getDeduEndTm() {
		return deduEndTm;
	}

	public void setDeduEndTm(String deduEndTm) {
		this.deduEndTm = deduEndTm;
	}

	public Integer getIsRelieve() {
		return isRelieve;
	}

	public void setIsRelieve(Integer isRelieve) {
		this.isRelieve = isRelieve;
	}

	public String getGbName() {
		return gbName;
	}

	public void setGbName(String gbName) {
		this.gbName = gbName;
	}

	public Integer getCarMonthIsOpen() {
		return carMonthIsOpen;
	}

	public void setCarMonthIsOpen(Integer carMonthIsOpen) {
		this.carMonthIsOpen = carMonthIsOpen;
	}

	public Integer getNeedBillIsOpen() {
		return needBillIsOpen;
	}

	public void setNeedBillIsOpen(Integer needBillIsOpen) {
		this.needBillIsOpen = needBillIsOpen;
	}

}
