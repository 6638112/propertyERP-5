package com.cnfantasia.server.api.cashFlowSummary.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.cashFlowSummary.dao.CashFlowSummaryBaseDao;
import com.cnfantasia.server.domainbase.cashFlowSummary.entity.CashFlowSummary;

/**
 * 现金流量汇总表
 * 
 * @author liyulin
 * @version 1.0 2016年7月18日 下午5:39:58
 */
public class CashFlowSummaryDao extends CashFlowSummaryBaseDao implements ICashFlowSummaryDao {

	/**
	 * 查询“物业费”数据  查询的数据条数
	 * 
	 * @param queryLimitCount 查询的数据条数
	 * @return
	 */
	@Override 
	public List<CashFlowSummary> selectForWY(int queryLimitCount){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("feeType", 1);
		paramMap.put("limitCount", queryLimitCount);
		return sqlSession.selectList("cashFlowSummary.select_WY_OTHER", paramMap);
	}
	
	/**
	 * 查询“停车费”数据
	 * 
	 * @param queryLimitCount 查询的数据条数
	 * @return
	 */
	@Override
	public List<CashFlowSummary> selectForParking(int queryLimitCount){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("limitCount", queryLimitCount);
		return sqlSession.selectList("cashFlowSummary.selectParking", paramMap);
	}
	
	/**
	 * 查询“其他代收费用”数据
	 * 
	 * @param queryLimitCount 查询的数据条数
	 * @return
	 */
	@Override
	public List<CashFlowSummary> selectForOther(int queryLimitCount){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("feeType", 2);
		paramMap.put("limitCount", queryLimitCount);
		return sqlSession.selectList("cashFlowSummary.select_WY_OTHER", paramMap);
	}

	/**
	 * 查询“楼下店”数据
	 * 
	 * @param queryLimitCount 查询的数据条数
	 * @return
	 */
	@Override
	public List<CashFlowSummary> selectForDown(int queryLimitCount){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("shopType", "down");
		paramMap.put("limitCount", queryLimitCount);
		return sqlSession.selectList("cashFlowSummary.select_shop", paramMap);
	}
	
	/**
	 * 查询“自营超市”数据
	 * 
	 * @param queryLimitCount 查询的数据条数
	 * @return
	 */
	@Override
	public List<CashFlowSummary> selectForSelfRun(int queryLimitCount){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("shopType", "selfRun");
		paramMap.put("limitCount", queryLimitCount);
		return sqlSession.selectList("cashFlowSummary.select_shop", paramMap);
	}
	
	/**
	 * 查询“上门维修”数据
	 * 
	 * @param queryLimitCount 查询的数据条数
	 * @return
	 */
	@Override
	public List<CashFlowSummary> selectForRepair(int queryLimitCount){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("limitCount", queryLimitCount);
		return sqlSession.selectList("cashFlowSummary.selectRepair", paramMap);
	}
	
	/**
	 * 查询“物业宝”数据
	 * 
	 * @param queryLimitCount 查询的数据条数
	 * @return
	 */
	@Override
	public List<CashFlowSummary> selectForWYB(int queryLimitCount){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("financeType", 1);
		paramMap.put("limitCount", queryLimitCount);
		return sqlSession.selectList("cashFlowSummary.select_WYB_ParkingB", paramMap);
	}
	
	/**
	 * 查询“停车宝”数据
	 * 
	 * @param queryLimitCount 查询的数据条数
	 * @return
	 */
	@Override
	public List<CashFlowSummary> selectForParkingB(int queryLimitCount){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("financeType", 2);
		paramMap.put("limitCount", queryLimitCount);
		return sqlSession.selectList("cashFlowSummary.select_WYB_ParkingB", paramMap);
	}
	
	/**
	 * 批量修改表t_finance_buy字段f_cash_status=2
	 * 
	 * @param srcIds
	 * @return
	 */
	@Override
	public int updateFinanceBuyCashStatusBatch(List<String> srcIds){
		return sqlSession.update("cashFlowSummary.update_finance_buy_cash_status_Batch", srcIds);
	}
	
	/**
	 * 批量修改表t_dredge_bill字段f_cash_status=2
	 * 
	 * @param srcIds
	 * @return
	 */
	@Override
	public int updateDredgeBillCashStatusBatch(List<String> srcIds){
		return sqlSession.update("cashFlowSummary.update_dredge_bill_cash_status_Batch", srcIds);
	}
	
	/**
	 * 批量修改表t_ebuy_order字段f_cash_status=2
	 * 
	 * @param srcIds
	 * @return
	 */
	@Override
	public int updateEbuyOrderCashStatusBatch(List<String> srcIds){
		return sqlSession.update("cashFlowSummary.update_ebuy_order_cash_status_Batch", srcIds);
	}
	
	/**
	 * 批量修改表t_car_num_pay_log字段f_cash_status=2
	 * 
	 * @param srcIds
	 * @return
	 */
	@Override
	public int updateCarNumPayLogCashStatusBatch(List<String> srcIds){
		return sqlSession.update("cashFlowSummary.update_car_num_pay_log_cash_status_Batch", srcIds);
	}
	/**
	 * 批量修改表t_pay_bill字段f_cash_status=2
	 * 
	 * @param srcIds
	 * @return
	 */
	@Override
	public int updatePayBillCashStatusBatch(List<String> srcIds){
		return sqlSession.update("cashFlowSummary.update_pay_bill_cash_status_Batch", srcIds);
	}
	
}
