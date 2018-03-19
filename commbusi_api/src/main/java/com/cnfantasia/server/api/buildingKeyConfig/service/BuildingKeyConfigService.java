package com.cnfantasia.server.api.buildingKeyConfig.service;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.buildingKeyConfig.dao.IBuildingKeyConfigDao;
import com.cnfantasia.server.domainbase.buildingKeyConfig.entity.BuildingKeyConfig;

/**
 * 门禁认证选项配置管理Service
 *
 * @author Liyl
 * @version 1.0 2016年3月24日 下午4:47:16
 */
public class BuildingKeyConfigService implements IBuildingKeyConfigService {
	private IBuildingKeyConfigDao buildingKeyConfigDao;

	public IBuildingKeyConfigDao getBuildingKeyConfigDao() {
		return buildingKeyConfigDao;
	}

	public void setBuildingKeyConfigDao(IBuildingKeyConfigDao buildingKeyConfigDao) {
		this.buildingKeyConfigDao = buildingKeyConfigDao;
	}
	
	/**
	 * 根据小区id查询配置
	 * @param tGroupBuildingFId
	 * @return
	 */
	@Override
	public List<BuildingKeyConfig> selectBuildingKeyConfigByCondition(BigInteger tGroupBuildingFId){
		return buildingKeyConfigDao.selectBuildingKeyConfigByCondition(tGroupBuildingFId);
	}
	
}
