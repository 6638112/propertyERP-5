package com.cnfantasia.server.ms.revenue.dao;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.revenueConfig.entity.RevenueConfig;
import com.cnfantasia.server.ms.revenue.entity.DredgeBillRevenue;
import com.cnfantasia.server.ms.revenue.entity.EbuyRevenueSignalAmount;
import com.cnfantasia.server.ms.revenue.entity.EbuyRevenueTotal;

public class RevenueDredgeDao extends AbstractBaseDao {

	public Integer updateForDredgeRevenue(Map<String, Object> paramMap) {
		return sqlSession.update("revenueDredge.updateForDredgeRevenue", paramMap);
	}
	
	public List<DredgeBillRevenue> selectDredgeRevenueList(Map<String, Object> paramMap) {
		return sqlSession.selectList("revenueDredge.selectDredgeRevenueList", paramMap);
	}
	
	public Integer updateDredgeRevenueStatus(Map<String, Object> paramMap) {
		return sqlSession.update("revenueDredge.updateDredgeRevenueStatus", paramMap);
	}
	
	public RevenueConfig selectRevenueConfig(Map<String, Object> paramMap) {
		return sqlSession.selectOne("revenueDredge.selectRevenueConfig", paramMap);
	}
	
	public int selectDredgeRevenueSaCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("revenueDredge.selectDredgeRevenueSaCount", paramMap);
	}
	
	public List<EbuyRevenueSignalAmount> selectDredgeRevenueSaList(Map<String, Object> paramMap) {
		return sqlSession.selectList("revenueDredge.selectDredgeRevenueSaList", paramMap);
	}

	public EbuyRevenueTotal selectDredgeRevenueTotal(Map<String, Object> paramMap) {
		return sqlSession.selectOne("revenueDredge.selectDredgeRevenueTotal", paramMap);
	}

	public List<EbuyRevenueSignalAmount> selectDredgeRevenueSaManagementList(Map<String, Object> paramMap) {
		return sqlSession.selectList("revenueDredge.selectDredgeRevenueSaManagementList", paramMap);
	}

	public int selectDredgeRevenueSaManagementCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("revenueDredge.selectDredgeRevenueSaManagementCount", paramMap);
	}

	public EbuyRevenueTotal selectDredgeRevenueManagementTotal(Map<String, Object> paramMap) {
		return sqlSession.selectOne("revenueDredge.selectDredgeRevenueManagementTotal", paramMap);
	}

}
