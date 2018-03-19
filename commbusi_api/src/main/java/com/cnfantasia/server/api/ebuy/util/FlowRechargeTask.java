package com.cnfantasia.server.api.ebuy.util;

import java.util.List;

import com.cnfantasia.server.api.ebuy.service.IEbuyFlowRechargeService;

public class FlowRechargeTask {

	private IEbuyFlowRechargeService ebuyFlowRechargeService;

	public void financeDeduction() {
		List<String> orderIdList = ebuyFlowRechargeService.getOrderIdForRecharge();
		for(String orderId : orderIdList) {
			FlowRechargePool.addOrderIdForRecharge(orderId);
		}
	}

	public void setEbuyFlowRechargeService(
			IEbuyFlowRechargeService ebuyFlowRechargeService) {
		this.ebuyFlowRechargeService = ebuyFlowRechargeService;
	}
	
}
