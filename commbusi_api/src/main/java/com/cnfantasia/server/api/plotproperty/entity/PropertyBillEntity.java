package com.cnfantasia.server.api.plotproperty.entity;

import java.io.Serializable;
import java.math.BigInteger;

public class PropertyBillEntity implements Serializable {
	/**账单ID*/
	private BigInteger payBillId;
	/**账单标题*/
	private String billTitle;//账单已出的就为账单月份
	/**账单月份*/
	private String billMonth;
	/**账单类型*/
	private String billType;
	/**账单类型名称*/
	private String billTypeName;
	/**账单类型图标*/
	private String billTypeImg;
	/**账单金额（优惠前））*/
	private Long billAmt;
	/**账单实付金额（优惠后）*/
	private Long billRelAmt;//实付金额 = 总金额 - 优惠金额
	/**优惠名称（随机立减）*/
	private String preferentialType;
	/**优惠金额*/
	private Long preferentialAmt;
	/**缴费开始时间*/
	private String payStart;
	/**缴费结束时间*/
	private String payEnd;
	/**缴费期间描述(12月4日-1月31日 可在线缴费)*/
	private String payDateDesc;
	/**是否已经交费（1已支付，0未支付，2支付确认中）*/
	private int isPay;
	/**是否有优惠*/
	private int isPreferential;
	/**是否抽取优惠*/
	private int isGetPreferential;
	/**账单是否已出*/
	private int isSucBill;
	/**物业宝抵扣状态*/
	private int financeStatus;
	/**物业宝抵扣金额*/
	private Long deduPrice;
	
	private String forBillPayMonth;//为计算缴费日期做准备
	/**最后更新时间*/
	private String lastUpdTime;

	/**周期缴费类型 1 固定周期缴费 2选择周期缴费*/
	private int propertyPeriodType;
	/**判断是否欠费过多*/
	private int periodPayServiceStatus;
	//数据来源，0为系统账单，1为对接软件查询的数据
	private int dataFromType = 0;
	/**选择性周期的滞纳金*/
	private Long lateFeeAmt;
	
	private Integer bankCollectionStatus; //f_bank_collection_status;

	//是否收拖欠费，==2 时收，其它情况不收
	private Integer collectionArrearsType;
	
	public BigInteger getPayBillId() {
		return payBillId;
	}
	public void setPayBillId(BigInteger payBillId) {
		this.payBillId = payBillId;
	}
	public String getBillTitle() {
		if(payBillId != null) {
			billTitle = billMonth;
		}
		return billTitle;
	}
	public void setBillTitle(String billTitle) {
		this.billTitle = billTitle;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getBillTypeName() {
		return billTypeName;
	}
	public void setBillTypeName(String billTypeName) {
		this.billTypeName = billTypeName;
	}
	public String getBillTypeImg() {
		return billTypeImg;
	}
	public void setBillTypeImg(String billTypeImg) {
		this.billTypeImg = billTypeImg;
	}
	public Long getBillAmt() {
		return billAmt == null ? 0 : billAmt;
	}
	public void setBillAmt(Long billAmt) {
		this.billAmt = billAmt;
	}
	public Long getBillRelAmt() {
		return billRelAmt == null ? 0 : billRelAmt;
	}
	public void setBillRelAmt(Long billRelAmt) {
		this.billRelAmt = billRelAmt;
	}
	public String getPreferentialType() {
		return preferentialType;
	}
	public void setPreferentialType(String preferentialType) {
		this.preferentialType = preferentialType;
	}
	public Long getPreferentialAmt() {
		return preferentialAmt == null ? 0 : preferentialAmt;
	}
	public void setPreferentialAmt(Long preferentialAmt) {
		this.preferentialAmt = preferentialAmt;
	}
	public String getPayDateDesc() {
		return payDateDesc;
	}
	public void setPayDateDesc(String payDateDesc) {
		this.payDateDesc = payDateDesc;
	}
	public int getIsPay() {
		return isPay;
	}
	public void setIsPay(int isPay) {
		this.isPay = isPay;
	}
	public String getBillMonth() {
		return billMonth;
	}
	public void setBillMonth(String billMonth) {
		this.billMonth = billMonth;
	}
	public String getPayStart() {
		return payStart;
	}
	public void setPayStart(String payStart) {
		this.payStart = payStart;
	}
	public String getPayEnd() {
		return payEnd;
	}
	public void setPayEnd(String payEnd) {
		this.payEnd = payEnd;
	}
	
	public int getIsPreferential() {
		return isPreferential;
	}
	public void setIsPreferential(int isPreferential) {
		this.isPreferential = isPreferential;
	}
	
	public int getIsSucBill() {
		return isSucBill;
	}
	public void setIsSucBill(int isSucBill) {
		this.isSucBill = isSucBill;
	}
	public String getForBillPayMonth() {
		return forBillPayMonth;
	}
	public void setForBillPayMonth(String forBillPayMonth) {
		this.forBillPayMonth = forBillPayMonth;
	}
	public int getIsGetPreferential() {
		return isGetPreferential;
	}
	public void setIsGetPreferential(int isGetPreferential) {
		this.isGetPreferential = isGetPreferential;
	}
	public int getFinanceStatus() {
		return financeStatus;
	}
	public void setFinanceStatus(int financeStatus) {
		this.financeStatus = financeStatus;
	}
	public Long getDeduPrice() {
		return deduPrice == null ? 0 : deduPrice;
	}
	public void setDeduPrice(Long deduPrice) {
		this.deduPrice = deduPrice;
	}
	public String getLastUpdTime() {
		return lastUpdTime;
	}
	public void setLastUpdTime(String lastUpdTime) {
		this.lastUpdTime = lastUpdTime;
	}

	public int getPropertyPeriodType() {
		if(propertyPeriodType != 2) {
			propertyPeriodType = 1;
		}
		return propertyPeriodType;
	}

	public void setPropertyPeriodType(int propertyPeriodType) {
		this.propertyPeriodType = propertyPeriodType;
	}

	public int getPeriodPayServiceStatus() {
		return periodPayServiceStatus;
	}

	public void setPeriodPayServiceStatus(int periodPayServiceStatus) {
		this.periodPayServiceStatus = periodPayServiceStatus;
	}

	public int getDataFromType() {
		return dataFromType;
	}

	public void setDataFromType(int dataFromType) {
		this.dataFromType = dataFromType;
	}
	
	public Long getLateFeeAmt() {
		return lateFeeAmt;
	}
	
	public void setLateFeeAmt(Long lateFeeAmt) {
		this.lateFeeAmt = lateFeeAmt;
	}
	public Integer getBankCollectionStatus() {
		return bankCollectionStatus;
	}
	public void setBankCollectionStatus(Integer bankCollectionStatus) {
		this.bankCollectionStatus = bankCollectionStatus;
	}

	public Integer getCollectionArrearsType() {
		return collectionArrearsType;
	}

	public void setCollectionArrearsType(Integer collectionArrearsType) {
		this.collectionArrearsType = collectionArrearsType;
	}
}
