package com.cnfantasia.server.domainbase.groupBuilding.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;

/**
 * 描述:(小区信息) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IGroupBuildingBaseDao {
	/**
	 * 根据条件查询(小区信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<GroupBuilding> selectGroupBuildingByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(小区信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<GroupBuilding> selectGroupBuildingByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(小区信息)信息
	 * @param id
	 * @return
	 */
	public GroupBuilding selectGroupBuildingBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(小区信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectGroupBuildingCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(小区信息)新增一条记录
	 * @param groupBuilding
	 * @return
	 */
	public int insertGroupBuilding(GroupBuilding groupBuilding);
	
	/**
	 * 批量新增(小区信息)信息
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
	 * 根据Id 批量删除(小区信息)信息_逻辑删除
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
