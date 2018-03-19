package com.cnfantasia.server.ms.payBill.entity;

import java.util.ArrayList;
import java.util.List;

import com.cnfantasia.server.domainbase.propertyFeeDetail.entity.PropertyFeeDetail;


/**
 * 导入的物业缴费单POJO对象
 * 
 * @author wenfq
 * 
 */
public class PayBillWithFeeDetailEntity extends PayBillEntity {
	/**
	 * 缴费明细单
	 */
	private List<PropertyFeeDetail> propertyFeeDetails = new ArrayList<PropertyFeeDetail>();

	public List<PropertyFeeDetail> getPropertyFeeDetails() {
		return propertyFeeDetails;
	}
	/**
	 * 是否收取欠费2收取，1不收取
	 */
	private int collectArrears;
	/**
	 * 是否不存在欠费
	 */
	private boolean isUnpaidFee;

	public void addPropertyFeeDetail(PropertyFeeDetail propertyFeeDetail) {
		propertyFeeDetails.add(propertyFeeDetail);
	}

	public void removePropertyFeeDetail(PropertyFeeDetail propertyFeeDetail) {
		propertyFeeDetails.remove(propertyFeeDetail);
	}


	public int getCollectArrears() {
		return collectArrears;
	}

	public boolean isUnpaidFee() {
		return isUnpaidFee;
	}

	public void setUnpaidFee(boolean unpaidFee) {
		isUnpaidFee = unpaidFee;
	}

	public void setCollectArrears(int collectArrears) {
		this.collectArrears = collectArrears;
	}

	public Long getAmountAfterFinance() {
		Long amount = getAmount();
		for(PropertyFeeDetail propertyFeeDetail : propertyFeeDetails) {
			amount -= propertyFeeDetail.getAllowancePrice();
		}
		return amount;
	}
	
	public Long getAmountFinance() {
		Long amount = 0L;
		for(PropertyFeeDetail propertyFeeDetail : propertyFeeDetails) {
			amount += propertyFeeDetail.getAllowancePrice();
		}
		return amount;
	}
}
