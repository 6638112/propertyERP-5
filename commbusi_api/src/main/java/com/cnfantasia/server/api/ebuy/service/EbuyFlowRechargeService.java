package com.cnfantasia.server.api.ebuy.service;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.common.constant.Lock;
import com.cnfantasia.server.api.common.dao.ICommonLockDao;
import com.cnfantasia.server.api.ebuy.dao.IEbuyFlowRechargeDao;
import com.cnfantasia.server.api.ebuy.entity.EbuyFlowRecharge;

public class EbuyFlowRechargeService implements IEbuyFlowRechargeService {
	
	private IEbuyFlowRechargeDao ebuyFlowRechargeDao;
	
	private ICommonLockDao commonLockDao;
	
	public int saveFlowRecharge(EbuyFlowRecharge flowRecharge) {
		return ebuyFlowRechargeDao.saveFlowRecharge(flowRecharge);
	}
	
	public int updateFlowRecharge(EbuyFlowRecharge flowRecharge) {
		commonLockDao.getLock(Lock.EBUY_ORDER_FLOW_RECHARGE);
		return ebuyFlowRechargeDao.updateFlowRecharge(flowRecharge);
	}
	
	public int updateFlowRechargeOrder(EbuyFlowRecharge flowRecharge) {
		return ebuyFlowRechargeDao.updateFlowRechargeOrder(flowRecharge);
	}
	
	@Override
	public EbuyFlowRecharge getFlowRecharge(Map<String, Object> paramMap) {
		return ebuyFlowRechargeDao.getFlowRecharge(paramMap);
	}
	
	public List<String> getOrderIdForRecharge() {
		commonLockDao.getLock(Lock.EBUY_ORDER_FLOW_RECHARGE);
		//把所有各种原因充流量失败的状态置成6
		ebuyFlowRechargeDao.updateForReloadRecharge();
		List<String> orderIdList = ebuyFlowRechargeDao.getOrderIdForRecharge();
		
		//加载完后置状态为7，防止重复加载
		ebuyFlowRechargeDao.updateReloadRechargeStatus();
		return orderIdList;
	}
	
//	public int updateForReloadRecharge() {
//		return ebuyFlowRechargeDao.updateForReloadRecharge();
//	}

	public void setEbuyFlowRechargeDao(IEbuyFlowRechargeDao ebuyFlowRechargeDao) {
		this.ebuyFlowRechargeDao = ebuyFlowRechargeDao;
	}

	public void setCommonLockDao(ICommonLockDao commonLockDao) {
		this.commonLockDao = commonLockDao;
	}

}
