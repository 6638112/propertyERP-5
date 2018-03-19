package com.cnfantasia.server.api.ebuy.dao;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.ebuy.entity.EbuyFlowRecharge;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;

public class EbuyFlowRechargeDao extends AbstractBaseDao implements IEbuyFlowRechargeDao{

	@Override
	public int saveFlowRecharge(EbuyFlowRecharge flowRecharge) {
		return sqlSession.insert("ebuyFlowRecharge.insert_flowRecharge", flowRecharge);
	}
	
	public EbuyFlowRecharge getFlowRecharge(Map<String, Object> paramMap) {
		return sqlSession.selectOne("ebuyFlowRecharge.getFlowRecharge", paramMap);
	}
	
	public List<String> getOrderIdForRecharge() {
		return sqlSession.selectList("ebuyFlowRecharge.getOrderIdForRecharge", 0);
	}

	@Override
	public int updateFlowRecharge(EbuyFlowRecharge flowRecharge) {
		return sqlSession.update("ebuyFlowRecharge.update_flowRecharge", flowRecharge);
	}
	
	@Override
	public int updateFlowRechargeOrder(EbuyFlowRecharge flowRecharge) {
		return sqlSession.update("ebuyFlowRecharge.update_flowRechargeOrder", flowRecharge);
	}
	
	public int updateForReloadRecharge() {
		return sqlSession.update("ebuyFlowRecharge.updateForReloadRecharge");
	}
	
	public int updateReloadRechargeStatus() {
		return sqlSession.update("ebuyFlowRecharge.updateReloadRechargeStatus");
	}

}
