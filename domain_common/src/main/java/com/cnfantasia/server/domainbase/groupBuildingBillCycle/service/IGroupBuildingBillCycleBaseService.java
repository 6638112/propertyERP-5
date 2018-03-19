package com.cnfantasia.server.domainbase.groupBuildingBillCycle.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.groupBuildingBillCycle.entity.GroupBuildingBillCycle;

/**
 * 描述:(账期管理) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IGroupBuildingBillCycleBaseService {
	
	/**
	 * 根据条件查询(账期管理)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<GroupBuildingBillCycle> getGroupBuildingBillCycleByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(账期管理)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<GroupBuildingBillCycle> getGroupBuildingBillCycleByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(账期管理)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<GroupBuildingBillCycle> getGroupBuildingBillCycleByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(账期管理)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<GroupBuildingBillCycle> getGroupBuildingBillCycleByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(账期管理)信息
	 * @param id
	 * @return
	 */
	public GroupBuildingBillCycle getGroupBuildingBillCycleBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(账期管理)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getGroupBuildingBillCycleCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(账期管理)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getGroupBuildingBillCycleCountDim(Map<String,Object> paramMap);
	/**
	 * 往(账期管理)新增一条记录
	 * @param groupBuildingBillCycle
	 * @return
	 */
	public int insertGroupBuildingBillCycle(GroupBuildingBillCycle groupBuildingBillCycle);
	/**
	 * 批量新增(账期管理)
	 * @param groupBuildingBillCycleList
	 * @return
	 */
	public int insertGroupBuildingBillCycleBatch(List<GroupBuildingBillCycle> groupBuildingBillCycleList);
	/**
	 * 更新(账期管理)信息
	 * @param groupBuildingBillCycle
	 * @return
	 */
	public int updateGroupBuildingBillCycle(GroupBuildingBillCycle groupBuildingBillCycle);
	/**
	 * 批量更新(账期管理)信息
	 * @param groupBuildingBillCycleList
	 * @return
	 */
	public int updateGroupBuildingBillCycleBatch(List<GroupBuildingBillCycle> groupBuildingBillCycleList);
	/**
	 * 根据序列号删除(账期管理)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteGroupBuildingBillCycleLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(账期管理)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteGroupBuildingBillCycleLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(账期管理)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteGroupBuildingBillCycle(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(账期管理)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteGroupBuildingBillCycleBatch(List<java.math.BigInteger> idList);
	
}
