package com.cnfantasia.server.api.plotproperty.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.plotproperty.entity.FinanceAgent;
import com.cnfantasia.server.api.plotproperty.entity.FinanceBuyEntity;
import com.cnfantasia.server.api.plotproperty.entity.FinanceDeductionDetail;
import com.cnfantasia.server.api.plotproperty.entity.FinanceDeductionEntity;
import com.cnfantasia.server.api.plotproperty.entity.FinanceLogEntity;
import com.cnfantasia.server.api.plotproperty.entity.FinanceProfitEntity;
import com.cnfantasia.server.api.plotproperty.entity.FinanceReqEntity;
import com.cnfantasia.server.api.plotproperty.entity.FinanceSum;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;

public class FinanceDao extends AbstractBaseDao {
	
	public Long getPropertyFee(Long realRoomId) {
		return sqlSession.selectOne("finance.getPropertyFee", realRoomId);
	}
	
	public FinanceReqEntity getFinanceReqForResp(Map<String, Object> paramMap) {
		return sqlSession.selectOne("finance.getFinanceReqForResp", paramMap);
	}
	
	public FinanceReqEntity getFinanceReqForCar(Map<String, Object> paramMap) {
		return sqlSession.selectOne("finance.getFinanceReqForCar", paramMap);
	}
	
	public int insertFinanceReq(FinanceReqEntity financeReqEntity) {
		return sqlSession.insert("finance.insertFinanceReq", financeReqEntity);
	}
	
	public int insertFinanceProfit(FinanceProfitEntity financeProfitEntity) {
		return sqlSession.insert("finance.insertFinanceProfit", financeProfitEntity);
	}

	/**
	 * 取已导入账单但是未生成抵扣记录的数据
	 */
	public List<FinanceDeductionEntity> getFinanceForDeductionList(Map<String, Object> paramMap) {
		return sqlSession.selectList("finance.getFinanceForDeductionList", paramMap);
	}

	public int updatePropertyDeductionPrice(FinanceDeductionEntity financeDeductionEntity) {
		return sqlSession.update("finance.updatePropertyDeductionPrice", financeDeductionEntity);
	}
	
	public int insertFinanceDeduction(FinanceDeductionEntity financeDeductionEntity) {
		return sqlSession.insert("finance.insertFinanceDeduction", financeDeductionEntity);
	}
	
	public int updateDeductionCount(String orderNo) {
		return sqlSession.update("finance.updateDeductionCount", orderNo);
	}
	
	public int insertFinanceLog(FinanceLogEntity financeLogEntity) {
		return sqlSession.insert("finance.insertFinanceLog", financeLogEntity);
	}
	
	public boolean isExistFinanceBuyCount(FinanceBuyEntity financeBuyEntity) {
		int count = sqlSession.selectOne("finance.getFinanceBuyCount", financeBuyEntity);
		return (count > 0 ? true : false);
	}

	public int insertFinanceBuy(FinanceBuyEntity financeBuyEntity) {
		return sqlSession.insert("finance.insertFinanceBuy", financeBuyEntity);
	}
	
	public boolean isExitsPaybillFeeOther(Long payBillId, BigDecimal deductionPrice) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("payBillId", payBillId);
		paramMap.put("deductionPrice", deductionPrice);
		int count = sqlSession.selectOne("finance.getPaybillFeeCount", paramMap);
		return (count > 0 ? true : false);
	}
	
	public int updatePayBillStatus(Long payBillId, Integer isPay, Long deduPrice) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("payBillId", payBillId);
		paramMap.put("isPay", isPay);
		paramMap.put("deduPrice", deduPrice);
		return sqlSession.update("finance.updatePayBillStatus", paramMap);
	}
	
	/**
	 * 更新有效期
	 * @param orderNo
	 * @return
	 */
	public int updateParkingExpireDate(String orderNo){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orderNo", orderNo);
		return sqlSession.update("finance.update_parking_expire_date", paramMap);
	}
	
	/**
	 * 物业或者代理商取收益列表
	 */
	public List<FinanceProfitEntity> getProfitListByWyOrAgent(Map<String, Object> paramMap) {
		return sqlSession.selectList("finance.getProfitListByWyOrAgent", paramMap);
	}
	
	public List<FinanceProfitEntity> getProfitAllList(Map<String, Object> paramMap) {
		return sqlSession.selectList("finance.getProfitAllList", paramMap);
	}

	public int getProfitListByWyOrAgentCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("finance.getProfitListByWyOrAgentCount", paramMap);
	}
	
	public int getProfitAllListCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("finance.getProfitAllListCount", paramMap);
	}
	
	public List<FinanceBuyEntity> getFinanceBuyList(Map<String, Object> paramMap) {
		return sqlSession.selectList("finance.getFinanceBuyList", paramMap);
	}

	public int getFinanceBuyListCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("finance.getFinanceBuyListCount", paramMap);
	}
	
	public FinanceSum getFinanceBuyListSum(Map<String, Object> paramMap) {
		return sqlSession.selectOne("finance.getFinanceBuyListSum", paramMap);
	}
	
	public FinanceSum getProfitListByWyOrAgentSum(Map<String, Object> paramMap) {
		return sqlSession.selectOne("finance.getProfitListByWyOrAgentSum", paramMap);
	}
	
	public FinanceSum getProfitAllListSum(Map<String, Object> paramMap) {
		return sqlSession.selectOne("finance.getProfitAllListSum", paramMap);
	}
	
	public FinanceAgent getFinanceAgent(Map<String, Object> paramMap) {
		return sqlSession.selectOne("finance.getFinanceAgent", paramMap);
	}
	
	public FinanceAgent getFinanceAgent2(Map<String, Object> paramMap) {
		return sqlSession.selectOne("finance.getFinanceAgent2", paramMap);
	}
	
	public List<FinanceDeductionDetail> getFinanceDeductionDetailList(Map<String, Object> paramMap) {
		return sqlSession.selectList("finance.getFinanceDeductionDetailList", paramMap);
	}
	
	public int getFinanceDeductionDetailCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("finance.getFinanceDeductionDetailCount", paramMap);
	}
	
	public FinanceSum getFinanceDeductionDetailSum(Map<String, Object> paramMap) {
		return sqlSession.selectOne("finance.getFinanceDeductionDetailSum", paramMap);
	}
	
	/**
	 * 查询物业宝未抵扣月数
	 * 
	 * @param realRoomId
	 * @return
	 */
	public Date getWYBDeduMonth(BigInteger realRoomId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("realRoomId", realRoomId);
		return sqlSession.selectOne("finance.getWYBDeduMonth", paramMap);
	}
}
