package com.cnfantasia.server.domainbase.groupBuildingBillCycle.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.groupBuildingBillCycle.entity.GroupBuildingBillCycle;

/**
 * 描述:(账期管理) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IGroupBuildingBillCycleBaseDao {
	/**
	 * 根据条件查询(账期管理)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<GroupBuildingBillCycle> selectGroupBuildingBillCycleByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(账期管理)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<GroupBuildingBillCycle> selectGroupBuildingBillCycleByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(账期管理)信息
	 * @param id
	 * @return
	 */
	public GroupBuildingBillCycle selectGroupBuildingBillCycleBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(账期管理)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectGroupBuildingBillCycleCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(账期管理)新增一条记录
	 * @param groupBuildingBillCycle
	 * @return
	 */
	public int insertGroupBuildingBillCycle(GroupBuildingBillCycle groupBuildingBillCycle);
	
	/**
	 * 批量新增(账期管理)信息
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
	 * 根据Id 批量删除(账期管理)信息_逻辑删除
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
