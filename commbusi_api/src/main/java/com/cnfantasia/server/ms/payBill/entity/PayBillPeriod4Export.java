package com.cnfantasia.server.ms.payBill.entity;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * 周期账单导出实体
 * 
 * @author wenfq 2015-12-23
 * 
 */
public class PayBillPeriod4Export {
	BigInteger id;//账单id
	BigInteger gbId;//小区id
	String billMonthStart;// 账单开始月份
	String billMonthEnd;// 账单截止月份
	String billTypeName;// 账单类型名称
	String payDayStart;// 缴费开始日期
	String payDayEnd;// 缴费截止日期
	String gbName;// 小区名称
	String bName;// 楼栋名称
	String unitName;// 单元名称
	String rrNum;// 房号
	String ppName;// 业主名称
	BigDecimal succPayAmount;// 用户支付金额
	BigDecimal pbAmount;// 账单金额
	BigDecimal pbSubSidyAmount;// 补贴金额
	BigDecimal deduPrice; //物业宝抵扣金额
	String pbPayTime;// 缴费时间
	String orderNo;// 订单号
	int payMethod;// 支付方式
	int paymentWay;// 支付渠道
	Integer cycleType;// 周期缴费方式=={"1":"固定周期","2":"选择性周期"}
	List<PropertyFeeDetail4Export> feeDetailList;
	Long lastUnpaid;//往月欠费
	int isPay;//缴费状态
	int financeStatus;//物业宝抵扣状态

	public int getPaymentWay() {
		return paymentWay;
	}

	public void setPaymentWay(int paymentWay) {
		this.paymentWay = paymentWay;
	}

	public String getBillMonthStart() {
		return billMonthStart;
	}

	public void setBillMonthStart(String billMonthStart) {
		this.billMonthStart = billMonthStart;
	}

	public String getBillMonthEnd() {
		return billMonthEnd;
	}

	public void setBillMonthEnd(String billMonthEnd) {
		this.billMonthEnd = billMonthEnd;
	}

	public String getBillTypeName() {
		return billTypeName;
	}

	public void setBillTypeName(String billTypeName) {
		this.billTypeName = billTypeName;
	}

	public String getPayDayStart() {
		return payDayStart;
	}

	public void setPayDayStart(String payDayStart) {
		this.payDayStart = payDayStart;
	}

	public String getPayDayEnd() {
		return payDayEnd;
	}

	public void setPayDayEnd(String payDayEnd) {
		this.payDayEnd = payDayEnd;
	}

	public String getGbName() {
		return gbName;
	}

	public void setGbName(String gbName) {
		this.gbName = gbName;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getRrNum() {
		return rrNum;
	}

	public void setRrNum(String rrNum) {
		this.rrNum = rrNum;
	}

	public String getPpName() {
		return ppName;
	}

	public void setPpName(String ppName) {
		this.ppName = ppName;
	}

	public BigDecimal getSuccPayAmount() {
		return succPayAmount;
	}

	public void setSuccPayAmount(BigDecimal succPayAmount) {
		this.succPayAmount = succPayAmount;
	}

	public BigDecimal getPbAmount() {
		return pbAmount;
	}

	public void setPbAmount(BigDecimal pbAmount) {
		this.pbAmount = pbAmount;
	}

	public BigDecimal getPbSubSidyAmount() {
		return pbSubSidyAmount;
	}

	public void setPbSubSidyAmount(BigDecimal pbSubSidyAmount) {
		this.pbSubSidyAmount = pbSubSidyAmount;
	}

	public String getPbPayTime() {
		return pbPayTime;
	}

	public void setPbPayTime(String pbPayTime) {
		this.pbPayTime = pbPayTime;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public int getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(int payMethod) {
		this.payMethod = payMethod;
	}

	public Integer getCycleType() {
		return cycleType;
	}

	public void setCycleType(Integer cycleType) {
		this.cycleType = cycleType;
	}

	public List<PropertyFeeDetail4Export> getFeeDetailList() {
		return feeDetailList;
	}

	public void setFeeDetailList(List<PropertyFeeDetail4Export> feeDetailList) {
		this.feeDetailList = feeDetailList;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public BigInteger getGbId() {
		return gbId;
	}

	public void setGbId(BigInteger gbId) {
		this.gbId = gbId;
	}

	public Long getLastUnpaid() {
		return lastUnpaid;
	}

	public void setLastUnpaid(Long lastUnpaid) {
		this.lastUnpaid = lastUnpaid;
	}

	public BigDecimal getDeduPrice() {
		return deduPrice;
	}

	public void setDeduPrice(BigDecimal deduPrice) {
		this.deduPrice = deduPrice;
	}

	public int getIsPay() {
		return isPay;
	}

	public void setIsPay(int isPay) {
		this.isPay = isPay;
	}

	public int getFinanceStatus() {
		return financeStatus;
	}

	public void setFinanceStatus(int financeStatus) {
		this.financeStatus = financeStatus;
	}
}
