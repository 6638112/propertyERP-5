package com.cnfantasia.server.api.plotproperty.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.payBill.entity.PayBill;

/**
 * @version:    1.0.0
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 */
public class PaybillWyb implements Serializable {
	
	private static final long serialVersionUID = 7609303002521642938L;

	private GroupBuilding groupBuilding;
	
	private List<PayBill> payBillList;

	public GroupBuilding getGroupBuilding() {
		return groupBuilding;
	}

	public void setGroupBuilding(GroupBuilding groupBuilding) {
		this.groupBuilding = groupBuilding;
	}

	public List<PayBill> getPayBillList() {
		return payBillList;
	}

	public void setPayBillList(List<PayBill> payBillList) {
		this.payBillList = payBillList;
	}
	
	public PayBill getLastNotPay() {
		return null;
	}
	
	public Date getNextMonthForDedu() {
		return null;
	}
}
