package com.cnfantasia.server.domainbase.building.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.building.entity.Building;

/**
 * 描述:(建筑单元) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IBuildingBaseService {
	
	/**
	 * 根据条件查询(建筑单元)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<Building> getBuildingByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(建筑单元)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<Building> getBuildingByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(建筑单元)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<Building> getBuildingByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(建筑单元)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<Building> getBuildingByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(建筑单元)信息
	 * @param id
	 * @return
	 */
	public Building getBuildingBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(建筑单元)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getBuildingCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(建筑单元)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getBuildingCountDim(Map<String,Object> paramMap);
	/**
	 * 往(建筑单元)新增一条记录
	 * @param building
	 * @return
	 */
	public int insertBuilding(Building building);
	/**
	 * 批量新增(建筑单元)
	 * @param buildingList
	 * @return
	 */
	public int insertBuildingBatch(List<Building> buildingList);
	/**
	 * 更新(建筑单元)信息
	 * @param building
	 * @return
	 */
	public int updateBuilding(Building building);
	/**
	 * 批量更新(建筑单元)信息
	 * @param buildingList
	 * @return
	 */
	public int updateBuildingBatch(List<Building> buildingList);
	/**
	 * 根据序列号删除(建筑单元)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteBuildingLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(建筑单元)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteBuildingLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(建筑单元)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteBuilding(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(建筑单元)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteBuildingBatch(List<java.math.BigInteger> idList);
	
}
