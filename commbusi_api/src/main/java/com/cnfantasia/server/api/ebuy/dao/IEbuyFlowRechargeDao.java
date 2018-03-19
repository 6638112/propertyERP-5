package com.cnfantasia.server.api.ebuy.dao;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.ebuy.entity.EbuyFlowRecharge;

public interface IEbuyFlowRechargeDao {
	
	public int saveFlowRecharge(EbuyFlowRecharge flowRecharge);
	
	public EbuyFlowRecharge getFlowRecharge(Map<String, Object> paramMap);
	
	public List<String> getOrderIdForRecharge();

	public int updateFlowRecharge(EbuyFlowRecharge flowRecharge);
	
	public int updateFlowRechargeOrder(EbuyFlowRecharge flowRecharge);
	
	public int updateForReloadRecharge();
	
	public int updateReloadRechargeStatus();
	
}
