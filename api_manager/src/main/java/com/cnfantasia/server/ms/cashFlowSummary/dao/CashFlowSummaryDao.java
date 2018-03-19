package com.cnfantasia.server.ms.cashFlowSummary.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.ms.cashFlowSummary.entity.CashFlowSummaryDto;

/**
 * 现金流量汇总
 * 
 * @author liyulin
 * @version 1.0 2016年7月15日 下午2:27:42
 */
public class CashFlowSummaryDao extends AbstractBaseDao implements ICashFlowSummaryDao {
	
	/**
	 * 查询现金流量汇总记录条数
	 * 
	 * @param paramMap
	 * @return int
	 */
	@Override
	public int selectCashFlowSummaryForCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("cashFlowSummary.select_cashFlowSummary_count", paramMap);
	}

	/**
	 * 查询现金流量汇总
	 * 
	 * @param paramMap
	 * @return List<CashFlowSummaryDto>
	 */
	@Override
	public List<CashFlowSummaryDto> selectCashFlowSummaryForList(Map<String, Object> paramMap) {
		return sqlSession.selectList("cashFlowSummary.select_cashFlowSummary_withPage", paramMap);
	}

	/**
	 * 查询现金流量汇总的总金额
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public BigDecimal selectCashFlowSummaryForTotalAmount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("cashFlowSummary.select_cashFlowSummary_withTotalAmount", paramMap);
	}

}
