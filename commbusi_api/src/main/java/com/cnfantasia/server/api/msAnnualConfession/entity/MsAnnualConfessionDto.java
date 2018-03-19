package com.cnfantasia.server.api.msAnnualConfession.entity;

/**
 * 表白活动
 * 
 * @author liyulin
 * @version 1.0 2016年8月4日 下午1:09:16
 */
public class MsAnnualConfessionDto {
	
	/** 注册时间 */
	private String dateRegister;
	/** 第一次缴费时间 */
	private String dateFirstFee;
	/** 第一次手误时间 */
	private String dateFirstMarket;
	/** 第一次服务时间 */
	private String dateFirstService;
	/** 消费总额 */
	private String amountPayment;

	public MsAnnualConfessionDto() {
		super();
	}

	public MsAnnualConfessionDto(String dateRegister, String dateFirstFee,
			String dateFirstMarket, String dateFirstService,
			String amountPayment) {
		super();
		this.dateRegister = dateRegister;
		this.dateFirstFee = dateFirstFee;
		this.dateFirstMarket = dateFirstMarket;
		this.dateFirstService = dateFirstService;
		this.amountPayment = amountPayment;
	}

	public String getDateRegister() {
		return dateRegister;
	}

	public void setDateRegister(String dateRegister) {
		this.dateRegister = dateRegister;
	}

	public String getDateFirstFee() {
		return dateFirstFee;
	}

	public void setDateFirstFee(String dateFirstFee) {
		this.dateFirstFee = dateFirstFee;
	}

	public String getDateFirstMarket() {
		return dateFirstMarket;
	}

	public void setDateFirstMarket(String dateFirstMarket) {
		this.dateFirstMarket = dateFirstMarket;
	}

	public String getDateFirstService() {
		return dateFirstService;
	}

	public void setDateFirstService(String dateFirstService) {
		this.dateFirstService = dateFirstService;
	}

	public String getAmountPayment() {
		return amountPayment;
	}

	public void setAmountPayment(String amountPayment) {
		this.amountPayment = amountPayment;
	}

	@Override
	public String toString() {
		return "MsAnnualConfessionDto [dateRegister=" + dateRegister
				+ ", dateFirstFee=" + dateFirstFee + ", dateFirstMarket="
				+ dateFirstMarket + ", dateFirstService=" + dateFirstService
				+ ", amountPayment=" + amountPayment + "]";
	}

}
