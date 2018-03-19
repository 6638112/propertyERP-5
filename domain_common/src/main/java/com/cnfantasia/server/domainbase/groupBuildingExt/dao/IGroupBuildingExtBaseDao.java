package com.cnfantasia.server.domainbase.groupBuildingExt.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.groupBuildingExt.entity.GroupBuildingExt;

/**
 * 描述:(小区信息扩展表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IGroupBuildingExtBaseDao {
	/**
	 * 根据条件查询(小区信息扩展表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<GroupBuildingExt> selectGroupBuildingExtByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(小区信息扩展表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<GroupBuildingExt> selectGroupBuildingExtByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(小区信息扩展表)信息
	 * @param id
	 * @return
	 */
	public GroupBuildingExt selectGroupBuildingExtBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(小区信息扩展表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectGroupBuildingExtCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(小区信息扩展表)新增一条记录
	 * @param groupBuildingExt
	 * @return
	 */
	public int insertGroupBuildingExt(GroupBuildingExt groupBuildingExt);
	
	/**
	 * 批量新增(小区信息扩展表)信息
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
	 * 根据Id 批量删除(小区信息扩展表)信息_逻辑删除
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
