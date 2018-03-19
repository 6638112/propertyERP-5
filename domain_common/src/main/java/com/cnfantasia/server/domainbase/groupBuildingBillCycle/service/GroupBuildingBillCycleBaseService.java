package com.cnfantasia.server.domainbase.groupBuildingBillCycle.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.groupBuildingBillCycle.dao.IGroupBuildingBillCycleBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.groupBuildingBillCycle.entity.GroupBuildingBillCycle;

/**
 * 描述:(账期管理) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class GroupBuildingBillCycleBaseService implements IGroupBuildingBillCycleBaseService{
	
	private IGroupBuildingBillCycleBaseDao groupBuildingBillCycleBaseDao;
	public void setGroupBuildingBillCycleBaseDao(IGroupBuildingBillCycleBaseDao groupBuildingBillCycleBaseDao) {
		this.groupBuildingBillCycleBaseDao = groupBuildingBillCycleBaseDao;
	}
	/**
	 * 根据条件查询(账期管理)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<GroupBuildingBillCycle> getGroupBuildingBillCycleByCondition(Map<String,Object> paramMap){
		return groupBuildingBillCycleBaseDao.selectGroupBuildingBillCycleByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(账期管理)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<GroupBuildingBillCycle> getGroupBuildingBillCycleByConditionDim(Map<String,Object> paramMap){
		return groupBuildingBillCycleBaseDao.selectGroupBuildingBillCycleByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(账期管理)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<GroupBuildingBillCycle> getGroupBuildingBillCycleByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return groupBuildingBillCycleBaseDao.selectGroupBuildingBillCycleByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(账期管理)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<GroupBuildingBillCycle> getGroupBuildingBillCycleByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return groupBuildingBillCycleBaseDao.selectGroupBuildingBillCycleByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(账期管理)信息
	 * @param id
	 * @return
	 */
	@Override
	public GroupBuildingBillCycle getGroupBuildingBillCycleBySeqId(java.math.BigInteger id){
		return groupBuildingBillCycleBaseDao.selectGroupBuildingBillCycleBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(账期管理)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getGroupBuildingBillCycleCount(Map<String,Object> paramMap){
		return groupBuildingBillCycleBaseDao.selectGroupBuildingBillCycleCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(账期管理)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getGroupBuildingBillCycleCountDim(Map<String,Object> paramMap){
		return groupBuildingBillCycleBaseDao.selectGroupBuildingBillCycleCount(paramMap,true);
	}
	/**
	 * 往(账期管理)新增一条记录
	 * @param groupBuildingBillCycle
	 * @return
	 */
	@Override
	public int insertGroupBuildingBillCycle(GroupBuildingBillCycle groupBuildingBillCycle){
		return groupBuildingBillCycleBaseDao.insertGroupBuildingBillCycle(groupBuildingBillCycle);
	}
	/**
	 * 批量新增(账期管理)
	 * @param groupBuildingBillCycleList
	 * @return
	 */
	@Override
	public int insertGroupBuildingBillCycleBatch(List<GroupBuildingBillCycle> groupBuildingBillCycleList){
		return groupBuildingBillCycleBaseDao.insertGroupBuildingBillCycleBatch(groupBuildingBillCycleList);
	}
	/**
	 * 更新(账期管理)信息
	 * @param groupBuildingBillCycle
	 * @return
	 */
	@Override
	public int updateGroupBuildingBillCycle(GroupBuildingBillCycle groupBuildingBillCycle){
		return groupBuildingBillCycleBaseDao.updateGroupBuildingBillCycle(groupBuildingBillCycle);
	}
	/**
	 * 批量更新(账期管理)信息
	 * @param groupBuildingBillCycleList
	 * @return
	 */
	@Override
	public int updateGroupBuildingBillCycleBatch(List<GroupBuildingBillCycle> groupBuildingBillCycleList){
		return groupBuildingBillCycleBaseDao.updateGroupBuildingBillCycleBatch(groupBuildingBillCycleList);
	}
	/**
	 * 根据序列号删除(账期管理)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteGroupBuildingBillCycleLogic(java.math.BigInteger id){
		return groupBuildingBillCycleBaseDao.deleteGroupBuildingBillCycleLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(账期管理)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteGroupBuildingBillCycleLogicBatch(List<java.math.BigInteger> idList){
		return groupBuildingBillCycleBaseDao.deleteGroupBuildingBillCycleLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(账期管理)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteGroupBuildingBillCycle(java.math.BigInteger id){
//		return groupBuildingBillCycleBaseDao.deleteGroupBuildingBillCycle(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(账期管理)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteGroupBuildingBillCycleBatch(List<java.math.BigInteger> idList){
//		return groupBuildingBillCycleBaseDao.deleteGroupBuildingBillCycleBatch(idList);
//	}
	
}
