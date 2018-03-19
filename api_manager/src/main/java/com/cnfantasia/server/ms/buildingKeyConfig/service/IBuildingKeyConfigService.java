package com.cnfantasia.server.ms.buildingKeyConfig.service;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.domainbase.buildingKeyConfig.entity.BuildingKeyConfig;

/**
 * 门禁认证选项配置管理Service接口
 *
 * @author Liyl
 * @version 1.0 2016年3月22日 下午5:57:52
 */
public interface IBuildingKeyConfigService {
	
	/**
	 * 新增(修改)配置信息
	 * @param groupBuildingId
	 * @param buildingKeyConfigList
	 * @return
	 */
	public int insertBuildingKeyConfigBatch(String groupBuildingId, List<BuildingKeyConfig> buildingKeyConfigList);
	
	/**
	 * 根据小区id查询配置
	 * @param groupBuildingId
	 * @return
	 */
	public List<BuildingKeyConfig> selectBuildingKeyConfigByCondition(String groupBuildingId);
	
	/**
	 * 根据id删除配置信息
	 * @param id
	 * @return
	 */
	public int deleteBuildingKeyConfig(java.math.BigInteger id);
	
	/**
	 * 配置项上移
	 * @param id
	 * @param order
	 * @return
	 */
	public JsonResponse upOrder(BigInteger id, BigInteger order);

	/**
	 * 配置项下移
	 * @param id
	 * @param order
	 * @return
	 */
	public JsonResponse downOrder(BigInteger id, BigInteger order);
}
