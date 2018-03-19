package com.cnfantasia.server.domainbase.groupBuildingExt.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.groupBuildingExt.dao.IGroupBuildingExtBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.groupBuildingExt.entity.GroupBuildingExt;

/**
 * 描述:(小区信息扩展表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class GroupBuildingExtBaseService implements IGroupBuildingExtBaseService{
	
	private IGroupBuildingExtBaseDao groupBuildingExtBaseDao;
	public void setGroupBuildingExtBaseDao(IGroupBuildingExtBaseDao groupBuildingExtBaseDao) {
		this.groupBuildingExtBaseDao = groupBuildingExtBaseDao;
	}
	/**
	 * 根据条件查询(小区信息扩展表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<GroupBuildingExt> getGroupBuildingExtByCondition(Map<String,Object> paramMap){
		return groupBuildingExtBaseDao.selectGroupBuildingExtByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(小区信息扩展表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<GroupBuildingExt> getGroupBuildingExtByConditionDim(Map<String,Object> paramMap){
		return groupBuildingExtBaseDao.selectGroupBuildingExtByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(小区信息扩展表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<GroupBuildingExt> getGroupBuildingExtByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return groupBuildingExtBaseDao.selectGroupBuildingExtByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(小区信息扩展表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<GroupBuildingExt> getGroupBuildingExtByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return groupBuildingExtBaseDao.selectGroupBuildingExtByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(小区信息扩展表)信息
	 * @param id
	 * @return
	 */
	@Override
	public GroupBuildingExt getGroupBuildingExtBySeqId(java.math.BigInteger id){
		return groupBuildingExtBaseDao.selectGroupBuildingExtBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(小区信息扩展表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getGroupBuildingExtCount(Map<String,Object> paramMap){
		return groupBuildingExtBaseDao.selectGroupBuildingExtCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(小区信息扩展表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getGroupBuildingExtCountDim(Map<String,Object> paramMap){
		return groupBuildingExtBaseDao.selectGroupBuildingExtCount(paramMap,true);
	}
	/**
	 * 往(小区信息扩展表)新增一条记录
	 * @param groupBuildingExt
	 * @return
	 */
	@Override
	public int insertGroupBuildingExt(GroupBuildingExt groupBuildingExt){
		return groupBuildingExtBaseDao.insertGroupBuildingExt(groupBuildingExt);
	}
	/**
	 * 批量新增(小区信息扩展表)
	 * @param groupBuildingExtList
	 * @return
	 */
	@Override
	public int insertGroupBuildingExtBatch(List<GroupBuildingExt> groupBuildingExtList){
		return groupBuildingExtBaseDao.insertGroupBuildingExtBatch(groupBuildingExtList);
	}
	/**
	 * 更新(小区信息扩展表)信息
	 * @param groupBuildingExt
	 * @return
	 */
	@Override
	public int updateGroupBuildingExt(GroupBuildingExt groupBuildingExt){
		return groupBuildingExtBaseDao.updateGroupBuildingExt(groupBuildingExt);
	}
	/**
	 * 批量更新(小区信息扩展表)信息
	 * @param groupBuildingExtList
	 * @return
	 */
	@Override
	public int updateGroupBuildingExtBatch(List<GroupBuildingExt> groupBuildingExtList){
		return groupBuildingExtBaseDao.updateGroupBuildingExtBatch(groupBuildingExtList);
	}
	/**
	 * 根据序列号删除(小区信息扩展表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteGroupBuildingExtLogic(java.math.BigInteger id){
		return groupBuildingExtBaseDao.deleteGroupBuildingExtLogic(id);
	}
	 */
	/**
	 * 根据序列号批量删除(小区信息扩展表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteGroupBuildingExtLogicBatch(List<java.math.BigInteger> idList){
		return groupBuildingExtBaseDao.deleteGroupBuildingExtLogicBatch(idList);
	}
	 */
//	/**
//	 * 根据序列号删除(小区信息扩展表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteGroupBuildingExt(java.math.BigInteger id){
//		return groupBuildingExtBaseDao.deleteGroupBuildingExt(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(小区信息扩展表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteGroupBuildingExtBatch(List<java.math.BigInteger> idList){
//		return groupBuildingExtBaseDao.deleteGroupBuildingExtBatch(idList);
//	}
	
}
