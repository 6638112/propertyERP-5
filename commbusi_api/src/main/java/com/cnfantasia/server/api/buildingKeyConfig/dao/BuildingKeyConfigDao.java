package com.cnfantasia.server.api.buildingKeyConfig.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.buildingKeyConfig.entity.BuildingKeyConfig;

/**
 * 门禁认证选项配置管理Dao
 *
 * @author Liyl
 * @version 1.0 2016年3月24日 下午4:47:16
 */
public class BuildingKeyConfigDao extends AbstractBaseDao implements IBuildingKeyConfigDao {
	
	/**
	 * 根据小区id查询配置
	 * @param tGroupBuildingFId
	 * @return
	 */
	@Override
	public List<BuildingKeyConfig> selectBuildingKeyConfigByCondition(BigInteger tGroupBuildingFId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(QUERY_TYPE_IF_DIM, true);
		paramMap.put("tGroupBuildingFId", tGroupBuildingFId);
		return sqlSession.selectList("buildingKeyConfigBase.select_buildingKeyConfig",paramMap);
	}
}
