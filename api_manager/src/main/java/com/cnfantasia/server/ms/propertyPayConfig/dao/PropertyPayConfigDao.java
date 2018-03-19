package com.cnfantasia.server.ms.propertyPayConfig.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.alterPeriodCfg.entity.AlterPeriodCfg;
import com.cnfantasia.server.domainbase.alterPeriodFeeItem.entity.AlterPeriodFeeItem;
import com.cnfantasia.server.domainbase.mrStandardBuilding.entity.MrStandardBuilding;
import com.cnfantasia.server.domainbase.mrStandardGroupBuilding.entity.MrStandardGroupBuilding;
import com.cnfantasia.server.ms.propertyPayConfig.entity.*;

public class PropertyPayConfigDao extends AbstractBaseDao implements IPropertyPayConfigDao {
	
	/**
	 * 查询配置信息（加锁，避免并发）
	 * 
	 * @param gbId
	 * @return
	 */
	@Override
	public AlterPeriodCfg getAlterPeriodCfgWithLock(BigInteger gbId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("gbId", gbId);
		return sqlSession.selectOne("propertyPayConfig.getAlterPeriodCfgWithLock", paramMap);
	}

	/**
	 * 根据条件查询(选择周期收费项)信息
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<AlterPeriodFeeItem> selectAlterPeriodFeeItemByCondition(Map<String, Object> paramMap) {
		paramMap.put(QUERY_TYPE_IF_DIM, true);
		return sqlSession.selectList("propertyPayConfig.select_alterPeriodFeeItem",paramMap);
	}
	
	/**
	 * 更新(选择周期配置)信息
	 * @param alterPeriodCfg
	 * @return
	 */
	@Override
	public int updateAlterPeriodCfg(AlterPeriodCfg alterPeriodCfg){
		return sqlSession.update("propertyPayConfig.update_alterPeriodCfg", alterPeriodCfg);
	}

	@Override
	public int deleteAlterRoomHasFeeItemByItemId(Map<String, Object> paramMap) {
		return sqlSession.update("propertyPayConfig.deleteAlterRoomHasFeeItemByItemId", paramMap);
	}

	@Override
	public int deleteAllDetailByGb(Map paraMap) {
		return sqlSession.update("propertyPayConfig.deleteAllDetailByGb", paraMap);
	}

	@Override
	public List<MrFeeItemEntity> getMrFeeItemEntityByGbId(BigInteger gbId) {
		return sqlSession.selectList("propertyPayConfig.getMrFeeItemEntityByGbId",gbId);
	}

	@Override
	public int delMrCalculateRuleCfg(BigInteger userId, BigInteger id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		paramMap.put("userId", userId);
		return sqlSession.update("propertyPayConfig.delMrCalculateRuleCfg", paramMap);
	}

	@Override
	public List<GroupBuildingTreeEntity> getRoomTreeByGbId(BigInteger gbId) {
		return sqlSession.selectList("propertyPayConfig.getRoomTreeByGbId", gbId);
	}

	@Override
	public List<MrStandardRoomEntity> getMrStandardRoomByRealRoomId(BigInteger realRoomId) {
		return sqlSession.selectList("propertyPayConfig.getMrStandardRoomByRealRoomId", realRoomId);
	}

	@Override
	public List<MrStandardBuildingEntity> getMrStandardBuildingByBuildingId(BigInteger buildingId) {
		return sqlSession.selectList("propertyPayConfig.getMrStandardBuildingByBuildingId", buildingId);
	}

	@Override
	public List<MrStandardGroupBuildingEntity> getMrStandardBuildingBygbId(BigInteger gbId) {
		return sqlSession.selectList("propertyPayConfig.getMrStandardBuildingBygbId", gbId);
	}

	@Override
	public List<CalculateRuleForStandar> getItemFeesHascalculateRules(BigInteger realRoomId) {
		return sqlSession.selectList("propertyPayConfig.getItemFeesHascalculateRules", realRoomId);
	}

	@Override
	public List<CalculateRuleEntity> getCalculateRuleByStandardRooId(BigInteger standardRoomId) {
		return sqlSession.selectList("propertyPayConfig.getCalculateRuleByStandardRooId", standardRoomId);
	}

	@Override
	public Integer getCalculatePriceTypeByStandardRoomId(BigInteger standardRoomId) {
		return sqlSession.selectOne("propertyPayConfig.getCalculatePriceTypeByStandardRoomId", standardRoomId);
	}

	@Override
	public Integer getCalculateRuleIsUseList(BigInteger id) {
		return sqlSession.selectOne("propertyPayConfig.getCalculateRuleIsUseList", id);
	}
}
