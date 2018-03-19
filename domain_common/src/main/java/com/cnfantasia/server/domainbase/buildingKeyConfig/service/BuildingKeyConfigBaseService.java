package com.cnfantasia.server.domainbase.buildingKeyConfig.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.buildingKeyConfig.dao.IBuildingKeyConfigBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.buildingKeyConfig.entity.BuildingKeyConfig;

/**
 * 描述:(门禁认证选项显示配置表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class BuildingKeyConfigBaseService implements IBuildingKeyConfigBaseService{
	
	private IBuildingKeyConfigBaseDao buildingKeyConfigBaseDao;
	public void setBuildingKeyConfigBaseDao(IBuildingKeyConfigBaseDao buildingKeyConfigBaseDao) {
		this.buildingKeyConfigBaseDao = buildingKeyConfigBaseDao;
	}
	/**
	 * 根据条件查询(门禁认证选项显示配置表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<BuildingKeyConfig> getBuildingKeyConfigByCondition(Map<String,Object> paramMap){
		return buildingKeyConfigBaseDao.selectBuildingKeyConfigByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(门禁认证选项显示配置表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<BuildingKeyConfig> getBuildingKeyConfigByConditionDim(Map<String,Object> paramMap){
		return buildingKeyConfigBaseDao.selectBuildingKeyConfigByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(门禁认证选项显示配置表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<BuildingKeyConfig> getBuildingKeyConfigByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return buildingKeyConfigBaseDao.selectBuildingKeyConfigByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(门禁认证选项显示配置表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<BuildingKeyConfig> getBuildingKeyConfigByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return buildingKeyConfigBaseDao.selectBuildingKeyConfigByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(门禁认证选项显示配置表)信息
	 * @param id
	 * @return
	 */
	@Override
	public BuildingKeyConfig getBuildingKeyConfigBySeqId(java.math.BigInteger id){
		return buildingKeyConfigBaseDao.selectBuildingKeyConfigBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(门禁认证选项显示配置表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getBuildingKeyConfigCount(Map<String,Object> paramMap){
		return buildingKeyConfigBaseDao.selectBuildingKeyConfigCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(门禁认证选项显示配置表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getBuildingKeyConfigCountDim(Map<String,Object> paramMap){
		return buildingKeyConfigBaseDao.selectBuildingKeyConfigCount(paramMap,true);
	}
	/**
	 * 往(门禁认证选项显示配置表)新增一条记录
	 * @param buildingKeyConfig
	 * @return
	 */
	@Override
	public int insertBuildingKeyConfig(BuildingKeyConfig buildingKeyConfig){
		return buildingKeyConfigBaseDao.insertBuildingKeyConfig(buildingKeyConfig);
	}
	/**
	 * 批量新增(门禁认证选项显示配置表)
	 * @param buildingKeyConfigList
	 * @return
	 */
	@Override
	public int insertBuildingKeyConfigBatch(List<BuildingKeyConfig> buildingKeyConfigList){
		return buildingKeyConfigBaseDao.insertBuildingKeyConfigBatch(buildingKeyConfigList);
	}
	/**
	 * 更新(门禁认证选项显示配置表)信息
	 * @param buildingKeyConfig
	 * @return
	 */
	@Override
	public int updateBuildingKeyConfig(BuildingKeyConfig buildingKeyConfig){
		return buildingKeyConfigBaseDao.updateBuildingKeyConfig(buildingKeyConfig);
	}
	/**
	 * 批量更新(门禁认证选项显示配置表)信息
	 * @param buildingKeyConfigList
	 * @return
	 */
	@Override
	public int updateBuildingKeyConfigBatch(List<BuildingKeyConfig> buildingKeyConfigList){
		return buildingKeyConfigBaseDao.updateBuildingKeyConfigBatch(buildingKeyConfigList);
	}
	/**
	 * 根据序列号删除(门禁认证选项显示配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteBuildingKeyConfigLogic(java.math.BigInteger id){
		return buildingKeyConfigBaseDao.deleteBuildingKeyConfigLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(门禁认证选项显示配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteBuildingKeyConfigLogicBatch(List<java.math.BigInteger> idList){
		return buildingKeyConfigBaseDao.deleteBuildingKeyConfigLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(门禁认证选项显示配置表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteBuildingKeyConfig(java.math.BigInteger id){
//		return buildingKeyConfigBaseDao.deleteBuildingKeyConfig(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(门禁认证选项显示配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteBuildingKeyConfigBatch(List<java.math.BigInteger> idList){
//		return buildingKeyConfigBaseDao.deleteBuildingKeyConfigBatch(idList);
//	}
	
}
