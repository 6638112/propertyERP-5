package com.cnfantasia.server.domainbase.building.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.building.dao.IBuildingBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.building.entity.Building;

/**
 * 描述:(建筑单元) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class BuildingBaseService implements IBuildingBaseService{
	
	private IBuildingBaseDao buildingBaseDao;
	public void setBuildingBaseDao(IBuildingBaseDao buildingBaseDao) {
		this.buildingBaseDao = buildingBaseDao;
	}
	/**
	 * 根据条件查询(建筑单元)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<Building> getBuildingByCondition(Map<String,Object> paramMap){
		return buildingBaseDao.selectBuildingByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(建筑单元)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<Building> getBuildingByConditionDim(Map<String,Object> paramMap){
		return buildingBaseDao.selectBuildingByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(建筑单元)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<Building> getBuildingByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return buildingBaseDao.selectBuildingByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(建筑单元)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<Building> getBuildingByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return buildingBaseDao.selectBuildingByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(建筑单元)信息
	 * @param id
	 * @return
	 */
	@Override
	public Building getBuildingBySeqId(java.math.BigInteger id){
		return buildingBaseDao.selectBuildingBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(建筑单元)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getBuildingCount(Map<String,Object> paramMap){
		return buildingBaseDao.selectBuildingCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(建筑单元)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getBuildingCountDim(Map<String,Object> paramMap){
		return buildingBaseDao.selectBuildingCount(paramMap,true);
	}
	/**
	 * 往(建筑单元)新增一条记录
	 * @param building
	 * @return
	 */
	@Override
	public int insertBuilding(Building building){
		return buildingBaseDao.insertBuilding(building);
	}
	/**
	 * 批量新增(建筑单元)
	 * @param buildingList
	 * @return
	 */
	@Override
	public int insertBuildingBatch(List<Building> buildingList){
		return buildingBaseDao.insertBuildingBatch(buildingList);
	}
	/**
	 * 更新(建筑单元)信息
	 * @param building
	 * @return
	 */
	@Override
	public int updateBuilding(Building building){
		return buildingBaseDao.updateBuilding(building);
	}
	/**
	 * 批量更新(建筑单元)信息
	 * @param buildingList
	 * @return
	 */
	@Override
	public int updateBuildingBatch(List<Building> buildingList){
		return buildingBaseDao.updateBuildingBatch(buildingList);
	}
	/**
	 * 根据序列号删除(建筑单元)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteBuildingLogic(java.math.BigInteger id){
		return buildingBaseDao.deleteBuildingLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(建筑单元)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteBuildingLogicBatch(List<java.math.BigInteger> idList){
		return buildingBaseDao.deleteBuildingLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(建筑单元)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteBuilding(java.math.BigInteger id){
//		return buildingBaseDao.deleteBuilding(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(建筑单元)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteBuildingBatch(List<java.math.BigInteger> idList){
//		return buildingBaseDao.deleteBuildingBatch(idList);
//	}
	
}
