package com.cnfantasia.server.domainbase.groupBuildingHasTCommunitySupply.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.groupBuildingHasTCommunitySupply.dao.IGroupBuildingHasTCommunitySupplyBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.groupBuildingHasTCommunitySupply.entity.GroupBuildingHasTCommunitySupply;

/**
 * 描述:(小区商家关系表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class GroupBuildingHasTCommunitySupplyBaseService implements IGroupBuildingHasTCommunitySupplyBaseService{
	
	private IGroupBuildingHasTCommunitySupplyBaseDao groupBuildingHasTCommunitySupplyBaseDao;
	public void setGroupBuildingHasTCommunitySupplyBaseDao(IGroupBuildingHasTCommunitySupplyBaseDao groupBuildingHasTCommunitySupplyBaseDao) {
		this.groupBuildingHasTCommunitySupplyBaseDao = groupBuildingHasTCommunitySupplyBaseDao;
	}
	/**
	 * 根据条件查询(小区商家关系表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<GroupBuildingHasTCommunitySupply> getGroupBuildingHasTCommunitySupplyByCondition(Map<String,Object> paramMap){
		return groupBuildingHasTCommunitySupplyBaseDao.selectGroupBuildingHasTCommunitySupplyByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(小区商家关系表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<GroupBuildingHasTCommunitySupply> getGroupBuildingHasTCommunitySupplyByConditionDim(Map<String,Object> paramMap){
		return groupBuildingHasTCommunitySupplyBaseDao.selectGroupBuildingHasTCommunitySupplyByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(小区商家关系表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<GroupBuildingHasTCommunitySupply> getGroupBuildingHasTCommunitySupplyByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return groupBuildingHasTCommunitySupplyBaseDao.selectGroupBuildingHasTCommunitySupplyByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(小区商家关系表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<GroupBuildingHasTCommunitySupply> getGroupBuildingHasTCommunitySupplyByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return groupBuildingHasTCommunitySupplyBaseDao.selectGroupBuildingHasTCommunitySupplyByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(小区商家关系表)信息
	 * @param id
	 * @return
	 */
	@Override
	public GroupBuildingHasTCommunitySupply getGroupBuildingHasTCommunitySupplyBySeqId(java.math.BigInteger id){
		return groupBuildingHasTCommunitySupplyBaseDao.selectGroupBuildingHasTCommunitySupplyBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(小区商家关系表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getGroupBuildingHasTCommunitySupplyCount(Map<String,Object> paramMap){
		return groupBuildingHasTCommunitySupplyBaseDao.selectGroupBuildingHasTCommunitySupplyCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(小区商家关系表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getGroupBuildingHasTCommunitySupplyCountDim(Map<String,Object> paramMap){
		return groupBuildingHasTCommunitySupplyBaseDao.selectGroupBuildingHasTCommunitySupplyCount(paramMap,true);
	}
	/**
	 * 往(小区商家关系表)新增一条记录
	 * @param groupBuildingHasTCommunitySupply
	 * @return
	 */
	@Override
	public int insertGroupBuildingHasTCommunitySupply(GroupBuildingHasTCommunitySupply groupBuildingHasTCommunitySupply){
		return groupBuildingHasTCommunitySupplyBaseDao.insertGroupBuildingHasTCommunitySupply(groupBuildingHasTCommunitySupply);
	}
	/**
	 * 批量新增(小区商家关系表)
	 * @param groupBuildingHasTCommunitySupplyList
	 * @return
	 */
	@Override
	public int insertGroupBuildingHasTCommunitySupplyBatch(List<GroupBuildingHasTCommunitySupply> groupBuildingHasTCommunitySupplyList){
		return groupBuildingHasTCommunitySupplyBaseDao.insertGroupBuildingHasTCommunitySupplyBatch(groupBuildingHasTCommunitySupplyList);
	}
	/**
	 * 更新(小区商家关系表)信息
	 * @param groupBuildingHasTCommunitySupply
	 * @return
	 */
	@Override
	public int updateGroupBuildingHasTCommunitySupply(GroupBuildingHasTCommunitySupply groupBuildingHasTCommunitySupply){
		return groupBuildingHasTCommunitySupplyBaseDao.updateGroupBuildingHasTCommunitySupply(groupBuildingHasTCommunitySupply);
	}
	/**
	 * 批量更新(小区商家关系表)信息
	 * @param groupBuildingHasTCommunitySupplyList
	 * @return
	 */
	@Override
	public int updateGroupBuildingHasTCommunitySupplyBatch(List<GroupBuildingHasTCommunitySupply> groupBuildingHasTCommunitySupplyList){
		return groupBuildingHasTCommunitySupplyBaseDao.updateGroupBuildingHasTCommunitySupplyBatch(groupBuildingHasTCommunitySupplyList);
	}
	/**
	 * 根据序列号删除(小区商家关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteGroupBuildingHasTCommunitySupplyLogic(java.math.BigInteger id){
		return groupBuildingHasTCommunitySupplyBaseDao.deleteGroupBuildingHasTCommunitySupplyLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(小区商家关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteGroupBuildingHasTCommunitySupplyLogicBatch(List<java.math.BigInteger> idList){
		return groupBuildingHasTCommunitySupplyBaseDao.deleteGroupBuildingHasTCommunitySupplyLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(小区商家关系表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteGroupBuildingHasTCommunitySupply(java.math.BigInteger id){
//		return groupBuildingHasTCommunitySupplyBaseDao.deleteGroupBuildingHasTCommunitySupply(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(小区商家关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteGroupBuildingHasTCommunitySupplyBatch(List<java.math.BigInteger> idList){
//		return groupBuildingHasTCommunitySupplyBaseDao.deleteGroupBuildingHasTCommunitySupplyBatch(idList);
//	}
	
}
