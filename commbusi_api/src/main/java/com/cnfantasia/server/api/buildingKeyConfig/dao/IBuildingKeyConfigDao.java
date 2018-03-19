package com.cnfantasia.server.api.buildingKeyConfig.dao;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.domainbase.buildingKeyConfig.entity.BuildingKeyConfig;

/**
 * 门禁认证选项配置管理Dao接口
 *
 * @author Liyl
 * @version 1.0 2016年3月24日 下午4:47:16
 */
public interface IBuildingKeyConfigDao {
	
	/**
	 * 根据小区id查询配置
	 * @param tGroupBuildingFId
	 * @return
	 */
	public List<BuildingKeyConfig> selectBuildingKeyConfigByCondition(BigInteger tGroupBuildingFId);
	
	/*public int saveData();
	
	public int updateData();
	
	public */
}
