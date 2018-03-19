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

	public void addPropertyFeeDetail(PropertyFeeDetail propertyFeeDetail) {
		propertyFeeDetails.add(propertyFeeDetail);
	}

	public void removePropertyFeeDetail(PropertyFeeDetail propertyFeeDetail) {
		propertyFeeDetails.remove(propertyFeeDetail);
	}
}
