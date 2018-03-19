package com.cnfantasia.server.api.plotproperty.util;

import com.cnfantasia.server.api.plotproperty.service.FinanceService;

public class FinanceDeductionTask {

	private FinanceService financeService;

	public void financeDeduction() {
		financeService.deductionPropertyBill();
	}
	
	public void setFinanceService(FinanceService financeService) {
		this.financeService = financeService;
	}
}
