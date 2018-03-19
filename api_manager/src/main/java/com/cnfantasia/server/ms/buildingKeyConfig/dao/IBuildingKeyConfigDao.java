package com.cnfantasia.server.ms.buildingKeyConfig.dao;

import java.util.List;

import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.domainbase.buildingKeyConfig.entity.BuildingKeyConfig;

/**
 * 门禁认证选项配置管理Dao接口
 *
 * @author Liyl
 * @version 1.0 2016年3月22日 下午5:57:52
 */
public interface IBuildingKeyConfigDao {
	
	/**
	 * 根据条件查询满足条件的(门禁认证选项显示配置表)记录数
	 * @param groupBuildingId
	 * @return
	 */
	public int selectBuildingKeyConfigCount(String groupBuildingId);
	
	/**
	 * 新增配置信息
	 * @param buildingKeyConfigList
	 * @return
	 */
	public int insertBuildingKeyConfigBatch(List<BuildingKeyConfig> buildingKeyConfigList);
	
	/**
	 * 根据小区id查询配置
	 * @param groupBuildingId
	 * @return
	 */
	public List<BuildingKeyConfig> selectBuildingKeyConfigByCondition(String groupBuildingId);
	
	/**
	 * 根据Id 批量删除(门禁认证选项显示配置表)
	 * @param idList
	 * @return
	 */
	public int deleteBuildingKeyConfigBatch(List<java.math.BigInteger> idList);
	
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
	public JsonResponse upOrder(java.math.BigInteger id, java.math.BigInteger order);
	
	/**
	 * 配置项下移
	 * @param id
	 * @param order
	 * @return
	 */
	public JsonResponse downOrder(java.math.BigInteger id, java.math.BigInteger order);
}
