package com.cnfantasia.server.ms.revenue.dao;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.ms.revenue.entity.EbuyRevenueTotal;
import com.cnfantasia.server.ms.revenue.entity.FinanceDeduRevenue;
import com.cnfantasia.server.ms.revenue.entity.FinanceDeduRevenueSignalAmount;

public class RevenueFinanceDeduDao extends AbstractBaseDao {

	public List<FinanceDeduRevenue> selectRevenueList(Map<String, Object> paramMap) {
		return sqlSession.selectList("revenueFinanceDedu.selectRevenueList", paramMap);
	}
	
	public Integer updateRevenueStatus(Map<String, Object> paramMap) {
		return sqlSession.update("revenueFinanceDedu.updateRevenueStatus", paramMap);
	}
	
	public List<FinanceDeduRevenueSignalAmount> selectRevenueSaList(Map<String, Object> paramMap) {
		return sqlSession.selectList("revenueFinanceDedu.selectRevenueSaList", paramMap);
	}
	
	public int selectRevenueSaCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("revenueFinanceDedu.selectRevenueSaCount", paramMap);
	}
	
	public EbuyRevenueTotal selectRevenueTotal(Map<String, Object> paramMap) {
		return sqlSession.selectOne("revenueFinanceDedu.selectRevenueTotal", paramMap);
	}
	
}
