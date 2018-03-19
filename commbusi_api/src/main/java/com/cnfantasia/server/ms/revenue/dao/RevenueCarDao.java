package com.cnfantasia.server.ms.revenue.dao;
import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.ms.revenue.entity.CarRevenue;
import com.cnfantasia.server.ms.revenue.entity.CarRevenueSignalAmount;
import com.cnfantasia.server.ms.revenue.entity.EbuyRevenueTotal;

import java.util.List;
import java.util.Map;

public class RevenueCarDao extends AbstractBaseDao {

	public List<CarRevenue> selectCarRevenueList(Map<String, Object> paramMap) {
		return sqlSession.selectList("revenueCar.selectCarRevenueList", paramMap);
	}
	
	public Integer updateCarRevenueStatus(Map<String, Object> paramMap) {
		return sqlSession.update("revenueCar.updateCarRevenueStatus", paramMap);
	}
	
	public List<CarRevenueSignalAmount> selectCarRevenueSaList(Map<String, Object> paramMap) {
		return sqlSession.selectList("revenueCar.selectCarRevenueSaList", paramMap);
	}
	
	public int selectCarRevenueSaCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("revenueCar.selectCarRevenueSaCount", paramMap);
	}
	
	public EbuyRevenueTotal selectCarRevenueTotal(Map<String, Object> paramMap) {
		return sqlSession.selectOne("revenueCar.selectCarRevenueTotal", paramMap);
	}

	public int updateCarRevnueSignalAmountToSettled(){
		return sqlSession.update("revenueCar.updateCarRevnueSignalAmountToSettled");
	}
}
