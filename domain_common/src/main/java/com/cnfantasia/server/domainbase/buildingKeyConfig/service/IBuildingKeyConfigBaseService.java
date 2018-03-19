package com.cnfantasia.server.domainbase.buildingKeyConfig.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.buildingKeyConfig.entity.BuildingKeyConfig;

/**
 * 描述:(门禁认证选项显示配置表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IBuildingKeyConfigBaseService {
	
	/**
	 * 根据条件查询(门禁认证选项显示配置表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<BuildingKeyConfig> getBuildingKeyConfigByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(门禁认证选项显示配置表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<BuildingKeyConfig> getBuildingKeyConfigByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(门禁认证选项显示配置表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<BuildingKeyConfig> getBuildingKeyConfigByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(门禁认证选项显示配置表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<BuildingKeyConfig> getBuildingKeyConfigByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(门禁认证选项显示配置表)信息
	 * @param id
	 * @return
	 */
	public BuildingKeyConfig getBuildingKeyConfigBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(门禁认证选项显示配置表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getBuildingKeyConfigCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(门禁认证选项显示配置表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getBuildingKeyConfigCountDim(Map<String,Object> paramMap);
	/**
	 * 往(门禁认证选项显示配置表)新增一条记录
	 * @param buildingKeyConfig
	 * @return
	 */
	public int insertBuildingKeyConfig(BuildingKeyConfig buildingKeyConfig);
	/**
	 * 批量新增(门禁认证选项显示配置表)
	 * @param buildingKeyConfigList
	 * @return
	 */
	public int insertBuildingKeyConfigBatch(List<BuildingKeyConfig> buildingKeyConfigList);
	/**
	 * 更新(门禁认证选项显示配置表)信息
	 * @param buildingKeyConfig
	 * @return
	 */
	public int updateBuildingKeyConfig(BuildingKeyConfig buildingKeyConfig);
	/**
	 * 批量更新(门禁认证选项显示配置表)信息
	 * @param buildingKeyConfigList
	 * @return
	 */
	public int updateBuildingKeyConfigBatch(List<BuildingKeyConfig> buildingKeyConfigList);
	/**
	 * 根据序列号删除(门禁认证选项显示配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteBuildingKeyConfigLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(门禁认证选项显示配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteBuildingKeyConfigLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(门禁认证选项显示配置表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteBuildingKeyConfig(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(门禁认证选项显示配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteBuildingKeyConfigBatch(List<java.math.BigInteger> idList);
	
}
