package com.cnfantasia.server.domainbase.groupBuildingHasTCommunitySupplyTmp.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.groupBuildingHasTCommunitySupplyTmp.dao.IGroupBuildingHasTCommunitySupplyTmpBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.groupBuildingHasTCommunitySupplyTmp.entity.GroupBuildingHasTCommunitySupplyTmp;

/**
 * 描述:(小区临时商家关系表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class GroupBuildingHasTCommunitySupplyTmpBaseService implements IGroupBuildingHasTCommunitySupplyTmpBaseService{
	
	private IGroupBuildingHasTCommunitySupplyTmpBaseDao groupBuildingHasTCommunitySupplyTmpBaseDao;
	public void setGroupBuildingHasTCommunitySupplyTmpBaseDao(IGroupBuildingHasTCommunitySupplyTmpBaseDao groupBuildingHasTCommunitySupplyTmpBaseDao) {
		this.groupBuildingHasTCommunitySupplyTmpBaseDao = groupBuildingHasTCommunitySupplyTmpBaseDao;
	}
	/**
	 * 根据条件查询(小区临时商家关系表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<GroupBuildingHasTCommunitySupplyTmp> getGroupBuildingHasTCommunitySupplyTmpByCondition(Map<String,Object> paramMap){
		return groupBuildingHasTCommunitySupplyTmpBaseDao.selectGroupBuildingHasTCommunitySupplyTmpByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(小区临时商家关系表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<GroupBuildingHasTCommunitySupplyTmp> getGroupBuildingHasTCommunitySupplyTmpByConditionDim(Map<String,Object> paramMap){
		return groupBuildingHasTCommunitySupplyTmpBaseDao.selectGroupBuildingHasTCommunitySupplyTmpByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(小区临时商家关系表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<GroupBuildingHasTCommunitySupplyTmp> getGroupBuildingHasTCommunitySupplyTmpByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return groupBuildingHasTCommunitySupplyTmpBaseDao.selectGroupBuildingHasTCommunitySupplyTmpByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(小区临时商家关系表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<GroupBuildingHasTCommunitySupplyTmp> getGroupBuildingHasTCommunitySupplyTmpByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return groupBuildingHasTCommunitySupplyTmpBaseDao.selectGroupBuildingHasTCommunitySupplyTmpByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(小区临时商家关系表)信息
	 * @param id
	 * @return
	 */
	@Override
	public GroupBuildingHasTCommunitySupplyTmp getGroupBuildingHasTCommunitySupplyTmpBySeqId(java.math.BigInteger id){
		return groupBuildingHasTCommunitySupplyTmpBaseDao.selectGroupBuildingHasTCommunitySupplyTmpBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(小区临时商家关系表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getGroupBuildingHasTCommunitySupplyTmpCount(Map<String,Object> paramMap){
		return groupBuildingHasTCommunitySupplyTmpBaseDao.selectGroupBuildingHasTCommunitySupplyTmpCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(小区临时商家关系表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getGroupBuildingHasTCommunitySupplyTmpCountDim(Map<String,Object> paramMap){
		return groupBuildingHasTCommunitySupplyTmpBaseDao.selectGroupBuildingHasTCommunitySupplyTmpCount(paramMap,true);
	}
	/**
	 * 往(小区临时商家关系表)新增一条记录
	 * @param groupBuildingHasTCommunitySupplyTmp
	 * @return
	 */
	@Override
	public int insertGroupBuildingHasTCommunitySupplyTmp(GroupBuildingHasTCommunitySupplyTmp groupBuildingHasTCommunitySupplyTmp){
		return groupBuildingHasTCommunitySupplyTmpBaseDao.insertGroupBuildingHasTCommunitySupplyTmp(groupBuildingHasTCommunitySupplyTmp);
	}
	/**
	 * 批量新增(小区临时商家关系表)
	 * @param groupBuildingHasTCommunitySupplyTmpList
	 * @return
	 */
	@Override
	public int insertGroupBuildingHasTCommunitySupplyTmpBatch(List<GroupBuildingHasTCommunitySupplyTmp> groupBuildingHasTCommunitySupplyTmpList){
		return groupBuildingHasTCommunitySupplyTmpBaseDao.insertGroupBuildingHasTCommunitySupplyTmpBatch(groupBuildingHasTCommunitySupplyTmpList);
	}
	/**
	 * 更新(小区临时商家关系表)信息
	 * @param groupBuildingHasTCommunitySupplyTmp
	 * @return
	 */
	@Override
	public int updateGroupBuildingHasTCommunitySupplyTmp(GroupBuildingHasTCommunitySupplyTmp groupBuildingHasTCommunitySupplyTmp){
		return groupBuildingHasTCommunitySupplyTmpBaseDao.updateGroupBuildingHasTCommunitySupplyTmp(groupBuildingHasTCommunitySupplyTmp);
	}
	/**
	 * 批量更新(小区临时商家关系表)信息
	 * @param groupBuildingHasTCommunitySupplyTmpList
	 * @return
	 */
	@Override
	public int updateGroupBuildingHasTCommunitySupplyTmpBatch(List<GroupBuildingHasTCommunitySupplyTmp> groupBuildingHasTCommunitySupplyTmpList){
		return groupBuildingHasTCommunitySupplyTmpBaseDao.updateGroupBuildingHasTCommunitySupplyTmpBatch(groupBuildingHasTCommunitySupplyTmpList);
	}
	/**
	 * 根据序列号删除(小区临时商家关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteGroupBuildingHasTCommunitySupplyTmpLogic(java.math.BigInteger id){
		return groupBuildingHasTCommunitySupplyTmpBaseDao.deleteGroupBuildingHasTCommunitySupplyTmpLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(小区临时商家关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteGroupBuildingHasTCommunitySupplyTmpLogicBatch(List<java.math.BigInteger> idList){
		return groupBuildingHasTCommunitySupplyTmpBaseDao.deleteGroupBuildingHasTCommunitySupplyTmpLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(小区临时商家关系表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteGroupBuildingHasTCommunitySupplyTmp(java.math.BigInteger id){
//		return groupBuildingHasTCommunitySupplyTmpBaseDao.deleteGroupBuildingHasTCommunitySupplyTmp(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(小区临时商家关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteGroupBuildingHasTCommunitySupplyTmpBatch(List<java.math.BigInteger> idList){
//		return groupBuildingHasTCommunitySupplyTmpBaseDao.deleteGroupBuildingHasTCommunitySupplyTmpBatch(idList);
//	}
	
}
