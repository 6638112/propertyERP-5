package com.cnfantasia.server.domainbase.groupBuildingExt.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.groupBuildingExt.entity.GroupBuildingExt;

/**
 * 描述:(小区信息扩展表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IGroupBuildingExtBaseService {
	
	/**
	 * 根据条件查询(小区信息扩展表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<GroupBuildingExt> getGroupBuildingExtByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(小区信息扩展表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<GroupBuildingExt> getGroupBuildingExtByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(小区信息扩展表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<GroupBuildingExt> getGroupBuildingExtByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(小区信息扩展表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<GroupBuildingExt> getGroupBuildingExtByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(小区信息扩展表)信息
	 * @param id
	 * @return
	 */
	public GroupBuildingExt getGroupBuildingExtBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(小区信息扩展表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getGroupBuildingExtCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(小区信息扩展表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getGroupBuildingExtCountDim(Map<String,Object> paramMap);
	/**
	 * 往(小区信息扩展表)新增一条记录
	 * @param groupBuildingExt
	 * @return
	 */
	public int insertGroupBuildingExt(GroupBuildingExt groupBuildingExt);
	/**
	 * 批量新增(小区信息扩展表)
	 * @param groupBuildingExtList
	 * @return
	 */
	public int insertGroupBuildingExtBatch(List<GroupBuildingExt> groupBuildingExtList);
	/**
	 * 更新(小区信息扩展表)信息
	 * @param groupBuildingExt
	 * @return
	 */
	public int updateGroupBuildingExt(GroupBuildingExt groupBuildingExt);
	/**
	 * 批量更新(小区信息扩展表)信息
	 * @param groupBuildingExtList
	 * @return
	 */
	public int updateGroupBuildingExtBatch(List<GroupBuildingExt> groupBuildingExtList);
	/**
	 * 根据序列号删除(小区信息扩展表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	public int deleteGroupBuildingExtLogic(java.math.BigInteger id);
	 */
	/**
	 * 根据序列号批量删除(小区信息扩展表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	public int deleteGroupBuildingExtLogicBatch(List<java.math.BigInteger> idList);
	 */
//	/**
//	 * 根据序列号删除(小区信息扩展表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteGroupBuildingExt(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(小区信息扩展表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteGroupBuildingExtBatch(List<java.math.BigInteger> idList);
	
}
