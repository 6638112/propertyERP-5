package com.cnfantasia.server.domainbase.groupBuilding.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;

/**
 * 描述:(小区信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IGroupBuildingBaseService {
	
	/**
	 * 根据条件查询(小区信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<GroupBuilding> getGroupBuildingByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(小区信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<GroupBuilding> getGroupBuildingByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(小区信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<GroupBuilding> getGroupBuildingByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(小区信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<GroupBuilding> getGroupBuildingByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(小区信息)信息
	 * @param id
	 * @return
	 */
	public GroupBuilding getGroupBuildingBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(小区信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getGroupBuildingCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(小区信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getGroupBuildingCountDim(Map<String,Object> paramMap);
	/**
	 * 往(小区信息)新增一条记录
	 * @param groupBuilding
	 * @return
	 */
	public int insertGroupBuilding(GroupBuilding groupBuilding);
	/**
	 * 批量新增(小区信息)
	 * @param groupBuildingList
	 * @return
	 */
	public int insertGroupBuildingBatch(List<GroupBuilding> groupBuildingList);
	/**
	 * 更新(小区信息)信息
	 * @param groupBuilding
	 * @return
	 */
	public int updateGroupBuilding(GroupBuilding groupBuilding);
	/**
	 * 批量更新(小区信息)信息
	 * @param groupBuildingList
	 * @return
	 */
	public int updateGroupBuildingBatch(List<GroupBuilding> groupBuildingList);
	/**
	 * 根据序列号删除(小区信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteGroupBuildingLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(小区信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteGroupBuildingLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(小区信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteGroupBuilding(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(小区信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteGroupBuildingBatch(List<java.math.BigInteger> idList);
	
}
