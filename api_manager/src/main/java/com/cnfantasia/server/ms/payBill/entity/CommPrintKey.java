package com.cnfantasia.server.ms.payBill.entity;

import java.math.BigDecimal;
import java.util.List;

/**
 * 普通打印模板key
 * 
 * @author liyulin
 * @version 1.0 2016年12月8日 下午7:28:31
 */
public class CommPrintKey {
	/**是否缴费*/
	private Integer isPay;
	/** 客户名称 */
	private String customerName;
	/** 账单名称 */
	private String billTypeName;
	/** 账单月份 */
	private String month;
	/** 缴费时间 */
	private String payTime;
	/** 缴费明细 */
	private List<PrintFeeDetail> printFeeDetails;
	/** 物业公司名称 */
	private String pcName;
	/** yyyy-MM-dd */
	private String now;
	/** 广告图片地址 */
	private String adIconUrl;
	/**往月欠费（单位：元）*/
	private BigDecimal lastUunpaid;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getBillTypeName() {
		return billTypeName;
	}

	public void setBillTypeName(String billTypeName) {
		this.billTypeName = billTypeName;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public List<PrintFeeDetail> getPrintFeeDetails() {
		return printFeeDetails;
	}

	public void setPrintFeeDetails(List<PrintFeeDetail> printFeeDetails) {
		this.printFeeDetails = printFeeDetails;
	}

	public String getPcName() {
		return pcName;
	}

	public void setPcName(String pcName) {
		this.pcName = pcName;
	}

	public String getNow() {
		return now;
	}

	public void setNow(String now) {
		this.now = now;
	}

	public String getAdIconUrl() {
		return adIconUrl;
	}

	public void setAdIconUrl(String adIconUrl) {
		this.adIconUrl = adIconUrl;
	}

	public Integer getIsPay() {
		return isPay;
	}

	public void setIsPay(Integer isPay) {
		this.isPay = isPay;
	}

	public BigDecimal getLastUunpaid() {
		return lastUunpaid;
	}

	public void setLastUunpaid(BigDecimal lastUunpaid) {
		this.lastUunpaid = lastUunpaid;
	}

}
