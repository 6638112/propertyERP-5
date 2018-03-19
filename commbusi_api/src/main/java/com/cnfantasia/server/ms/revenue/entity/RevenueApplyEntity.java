package com.cnfantasia.server.ms.revenue.entity;

import java.util.List;

import com.cnfantasia.server.domainbase.revenueApply.entity.RevenueApply;
import com.cnfantasia.server.domainbase.revenueSignalAmount.entity.RevenueSignalAmount;

public class RevenueApplyEntity extends RevenueApply{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5636528548035778022L;
	
	private List<RevenueSignalAmount> revenueSignalAmountList;

	public List<RevenueSignalAmount> getRevenueSignalAmountList() {
		return revenueSignalAmountList;
	}

	public void setRevenueSignalAmountList(List<RevenueSignalAmount> revenueSignalAmountList) {
		this.revenueSignalAmountList = revenueSignalAmountList;
	}
}
