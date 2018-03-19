package com.cnfantasia.server.domainbase.groupBuilding.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.groupBuilding.dao.IGroupBuildingBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;

/**
 * 描述:(小区信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class GroupBuildingBaseService implements IGroupBuildingBaseService{
	
	private IGroupBuildingBaseDao groupBuildingBaseDao;
	public void setGroupBuildingBaseDao(IGroupBuildingBaseDao groupBuildingBaseDao) {
		this.groupBuildingBaseDao = groupBuildingBaseDao;
	}
	/**
	 * 根据条件查询(小区信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<GroupBuilding> getGroupBuildingByCondition(Map<String,Object> paramMap){
		return groupBuildingBaseDao.selectGroupBuildingByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(小区信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<GroupBuilding> getGroupBuildingByConditionDim(Map<String,Object> paramMap){
		return groupBuildingBaseDao.selectGroupBuildingByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(小区信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<GroupBuilding> getGroupBuildingByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return groupBuildingBaseDao.selectGroupBuildingByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(小区信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<GroupBuilding> getGroupBuildingByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return groupBuildingBaseDao.selectGroupBuildingByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(小区信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public GroupBuilding getGroupBuildingBySeqId(java.math.BigInteger id){
		return groupBuildingBaseDao.selectGroupBuildingBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(小区信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getGroupBuildingCount(Map<String,Object> paramMap){
		return groupBuildingBaseDao.selectGroupBuildingCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(小区信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getGroupBuildingCountDim(Map<String,Object> paramMap){
		return groupBuildingBaseDao.selectGroupBuildingCount(paramMap,true);
	}
	/**
	 * 往(小区信息)新增一条记录
	 * @param groupBuilding
	 * @return
	 */
	@Override
	public int insertGroupBuilding(GroupBuilding groupBuilding){
		return groupBuildingBaseDao.insertGroupBuilding(groupBuilding);
	}
	/**
	 * 批量新增(小区信息)
	 * @param groupBuildingList
	 * @return
	 */
	@Override
	public int insertGroupBuildingBatch(List<GroupBuilding> groupBuildingList){
		return groupBuildingBaseDao.insertGroupBuildingBatch(groupBuildingList);
	}
	/**
	 * 更新(小区信息)信息
	 * @param groupBuilding
	 * @return
	 */
	@Override
	public int updateGroupBuilding(GroupBuilding groupBuilding){
		return groupBuildingBaseDao.updateGroupBuilding(groupBuilding);
	}
	/**
	 * 批量更新(小区信息)信息
	 * @param groupBuildingList
	 * @return
	 */
	@Override
	public int updateGroupBuildingBatch(List<GroupBuilding> groupBuildingList){
		return groupBuildingBaseDao.updateGroupBuildingBatch(groupBuildingList);
	}
	/**
	 * 根据序列号删除(小区信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteGroupBuildingLogic(java.math.BigInteger id){
		return groupBuildingBaseDao.deleteGroupBuildingLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(小区信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteGroupBuildingLogicBatch(List<java.math.BigInteger> idList){
		return groupBuildingBaseDao.deleteGroupBuildingLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(小区信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteGroupBuilding(java.math.BigInteger id){
//		return groupBuildingBaseDao.deleteGroupBuilding(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(小区信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteGroupBuildingBatch(List<java.math.BigInteger> idList){
//		return groupBuildingBaseDao.deleteGroupBuildingBatch(idList);
//	}
	
}
