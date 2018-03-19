package com.cnfantasia.server.ms.revenue.dao;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.ms.revenue.entity.CarDeduRevenue;
import com.cnfantasia.server.ms.revenue.entity.CarDeduRevenueSignalAmount;
import com.cnfantasia.server.ms.revenue.entity.EbuyRevenueTotal;
import com.cnfantasia.server.ms.revenue.entity.FinanceDeduRevenue;
import com.cnfantasia.server.ms.revenue.entity.FinanceDeduRevenueSignalAmount;

public class RevenueCarDeduDao extends AbstractBaseDao {

	public List<CarDeduRevenue> selectRevenueList(Map<String, Object> paramMap) {
		return sqlSession.selectList("revenueCarDedu.selectRevenueList", paramMap);
	}
	
	public Integer updateRevenueStatus(Map<String, Object> paramMap) {
		return sqlSession.update("revenueCarDedu.updateRevenueStatus", paramMap);
	}
	
	public List<CarDeduRevenueSignalAmount> selectRevenueSaList(Map<String, Object> paramMap) {
		return sqlSession.selectList("revenueCarDedu.selectRevenueSaList", paramMap);
	}
	
	public int selectRevenueSaCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("revenueCarDedu.selectRevenueSaCount", paramMap);
	}
	
	public EbuyRevenueTotal selectRevenueTotal(Map<String, Object> paramMap) {
		return sqlSession.selectOne("revenueCarDedu.selectRevenueTotal", paramMap);
	}
	
}
