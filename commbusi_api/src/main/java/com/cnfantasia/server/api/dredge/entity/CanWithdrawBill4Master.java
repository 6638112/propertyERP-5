package com.cnfantasia.server.api.dredge.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 可提现维修单
 * 
 * @author wenfq
 *
 */
public class CanWithdrawBill4Master {
	// canWithdrawInfo.put("dredgeType", "下水道疏通");
	// canWithdrawInfo.put("totalAmt", 65); 
	// canWithdrawInfo.put("billAmt", 60); 
	// canWithdrawInfo.put("billAddress", "青青世界A-103室"); 
	// canWithdrawInfo.put("submitDate", "2015-11-20"); 
	// canWithdrawInfo.put("subsidyAmt", 5); 
	// canWithdrawInfo.put("costAmt", 0); 
	
	long dredgeBillId; //维修单id
	String dredgeType;
	BigDecimal totalAmt;//总金额
	BigDecimal billAmt;//订单金额
	BigDecimal subsidyAmt;//补贴金额
	BigDecimal costAmt;//平台费用
	
	//收益明细
	List<RSADetail> amountList = new ArrayList<RSADetail>();
	
	String billAddress;//订单地址
	
	String payTime;//交易时间
	
	String submitDate;//预约时间
	String userMobile;//预约手机号
	
	String referrerMobile;//推荐人手机号
	
	public BigDecimal getSubsidyAmt() {
		subsidyAmt = BigDecimal.ZERO;
		for(int i = 0; i < amountList.size(); i++){
			subsidyAmt = subsidyAmt.add(amountList.get(i).getSubsidyAmt());
		}			
		return subsidyAmt;
	}

	public void setSubsidyAmt(BigDecimal subsidyAmt) {
		this.subsidyAmt = subsidyAmt;
	}

	public BigDecimal getCostAmt() {
		costAmt = BigDecimal.ZERO;
		for(int i = 0; i < amountList.size(); i++){
			costAmt = costAmt.add(amountList.get(i).getCostAmt());
		}			
		return costAmt;
	}

	public void setCostAmt(BigDecimal costAmt) {
		this.costAmt = costAmt;
	}

	/**
	 * 当前可提金额，不含平台费，未提的，可提日期<=0的
	 * @return
	 */
	public BigDecimal getCanWithdrawAmt() {
		BigDecimal canWithdrawAmt = BigDecimal.ZERO;
		for(int i = 0; i < amountList.size(); i++){
			if(amountList.get(i).getIsWithdrawed()==0 && amountList.get(i).getCanWithdrawDays() <= 0){ //CanWithdrawDays<=0时才能算是可提现金额
				canWithdrawAmt = canWithdrawAmt.add(amountList.get(i).getFeeAmount().subtract(amountList.get(i).getCostAmt()));
			}
		}			
		return canWithdrawAmt;
	}
	
	/**
	 * 总资产（不含平台费）
	 * @return
	 */
	public BigDecimal getTotalAmt() {
		totalAmt = BigDecimal.ZERO;
		for(int i = 0; i < amountList.size(); i++){
			totalAmt = totalAmt.add(amountList.get(i).getFeeAmount().subtract(amountList.get(i).getCostAmt()));
		}			
		return totalAmt;
	}

	public BigDecimal getBillAmt() {
		return billAmt;
	}

	public void setBillAmt(BigDecimal billAmt) {
		this.billAmt = billAmt;
	}

	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}

	public String getReferrerMobile() {
		return referrerMobile;
	}

	public void setReferrerMobile(String referrerMobile) {
		this.referrerMobile = referrerMobile;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public long getDredgeBillId() {
		return dredgeBillId;
	}

	public void setDredgeBillId(long dredgeBillId) {
		this.dredgeBillId = dredgeBillId;
	}

	public String getDredgeType() {
		return dredgeType;
	}

	public void setDredgeType(String dredgeType) {
		this.dredgeType = dredgeType;
	}

	public List<RSADetail> getAmountList() {
		return amountList;
	}

	public void setAmountList(List<RSADetail> amountList) {
		this.amountList = amountList;
	}

	public String getBillAddress() {
		return billAddress;
	}

	public void setBillAddress(String billAddress) {
		this.billAddress = billAddress;
	}

	public String getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}

}
