package com.cnfantasia.server.api.ebuy.service;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.ebuy.entity.EbuyFlowRecharge;

public interface IEbuyFlowRechargeService {
	
	public int saveFlowRecharge(EbuyFlowRecharge flowRecharge);
	
	public int updateFlowRecharge(EbuyFlowRecharge flowRecharge);
	
	public int updateFlowRechargeOrder(EbuyFlowRecharge flowRecharge);

	public EbuyFlowRecharge getFlowRecharge(Map<String, Object> paramMap);
	
	public List<String> getOrderIdForRecharge();

}
