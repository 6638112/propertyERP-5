package com.cnfantasia.server.ms.revenue.dao;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.revenueConfig.entity.RevenueConfig;
import com.cnfantasia.server.ms.revenue.entity.EbuyRevenueTotal;
import com.cnfantasia.server.ms.revenue.entity.FinanceRevenue;
import com.cnfantasia.server.ms.revenue.entity.FinanceRevenueSignalAmount;

public class RevenueFinanceDao extends AbstractBaseDao {

	public Integer updateForFinanceRevenue(Map<String, Object> paramMap) {
		return sqlSession.update("revenueFinance.updateForFinanceRevenue", paramMap);
	}
	
	public List<FinanceRevenue> selectFinanceRevenueList(Map<String, Object> paramMap) {
		return sqlSession.selectList("revenueFinance.selectFinanceRevenueList", paramMap);
	}
	
	public Integer updateFinanceRevenueStatus(Map<String, Object> paramMap) {
		return sqlSession.update("revenueFinance.updateFinanceRevenueStatus", paramMap);
	}
	
	public RevenueConfig selectRevenueConfig(Map<String, Object> paramMap) {
		return sqlSession.selectOne("revenueFinance.selectRevenueConfig", paramMap);
	}
	
	public int selectFinanceRevenueSaCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("revenueFinance.selectFinanceRevenueSaCount", paramMap);
	}
	
	public List<FinanceRevenueSignalAmount> selectFinanceRevenueSaList(Map<String, Object> paramMap) {
		return sqlSession.selectList("revenueFinance.selectFinanceRevenueSaList", paramMap);
	}
	
	public EbuyRevenueTotal selectFinanceRevenueTotal(Map<String, Object> paramMap) {
		return sqlSession.selectOne("revenueFinance.selectFinanceRevenueTotal", paramMap);
	}

}
