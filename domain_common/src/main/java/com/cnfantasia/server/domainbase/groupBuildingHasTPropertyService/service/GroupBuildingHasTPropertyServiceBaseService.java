package com.cnfantasia.server.domainbase.groupBuildingHasTPropertyService.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.groupBuildingHasTPropertyService.dao.IGroupBuildingHasTPropertyServiceBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.groupBuildingHasTPropertyService.entity.GroupBuildingHasTPropertyService;

/**
 * 描述:(小区服务关系表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class GroupBuildingHasTPropertyServiceBaseService implements IGroupBuildingHasTPropertyServiceBaseService{
	
	private IGroupBuildingHasTPropertyServiceBaseDao groupBuildingHasTPropertyServiceBaseDao;
	public void setGroupBuildingHasTPropertyServiceBaseDao(IGroupBuildingHasTPropertyServiceBaseDao groupBuildingHasTPropertyServiceBaseDao) {
		this.groupBuildingHasTPropertyServiceBaseDao = groupBuildingHasTPropertyServiceBaseDao;
	}
	/**
	 * 根据条件查询(小区服务关系表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<GroupBuildingHasTPropertyService> getGroupBuildingHasTPropertyServiceByCondition(Map<String,Object> paramMap){
		return groupBuildingHasTPropertyServiceBaseDao.selectGroupBuildingHasTPropertyServiceByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(小区服务关系表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<GroupBuildingHasTPropertyService> getGroupBuildingHasTPropertyServiceByConditionDim(Map<String,Object> paramMap){
		return groupBuildingHasTPropertyServiceBaseDao.selectGroupBuildingHasTPropertyServiceByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(小区服务关系表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<GroupBuildingHasTPropertyService> getGroupBuildingHasTPropertyServiceByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return groupBuildingHasTPropertyServiceBaseDao.selectGroupBuildingHasTPropertyServiceByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(小区服务关系表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<GroupBuildingHasTPropertyService> getGroupBuildingHasTPropertyServiceByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return groupBuildingHasTPropertyServiceBaseDao.selectGroupBuildingHasTPropertyServiceByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(小区服务关系表)信息
	 * @param id
	 * @return
	 */
	@Override
	public GroupBuildingHasTPropertyService getGroupBuildingHasTPropertyServiceBySeqId(java.math.BigInteger id){
		return groupBuildingHasTPropertyServiceBaseDao.selectGroupBuildingHasTPropertyServiceBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(小区服务关系表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getGroupBuildingHasTPropertyServiceCount(Map<String,Object> paramMap){
		return groupBuildingHasTPropertyServiceBaseDao.selectGroupBuildingHasTPropertyServiceCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(小区服务关系表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getGroupBuildingHasTPropertyServiceCountDim(Map<String,Object> paramMap){
		return groupBuildingHasTPropertyServiceBaseDao.selectGroupBuildingHasTPropertyServiceCount(paramMap,true);
	}
	/**
	 * 往(小区服务关系表)新增一条记录
	 * @param groupBuildingHasTPropertyService
	 * @return
	 */
	@Override
	public int insertGroupBuildingHasTPropertyService(GroupBuildingHasTPropertyService groupBuildingHasTPropertyService){
		return groupBuildingHasTPropertyServiceBaseDao.insertGroupBuildingHasTPropertyService(groupBuildingHasTPropertyService);
	}
	/**
	 * 批量新增(小区服务关系表)
	 * @param groupBuildingHasTPropertyServiceList
	 * @return
	 */
	@Override
	public int insertGroupBuildingHasTPropertyServiceBatch(List<GroupBuildingHasTPropertyService> groupBuildingHasTPropertyServiceList){
		return groupBuildingHasTPropertyServiceBaseDao.insertGroupBuildingHasTPropertyServiceBatch(groupBuildingHasTPropertyServiceList);
	}
	/**
	 * 更新(小区服务关系表)信息
	 * @param groupBuildingHasTPropertyService
	 * @return
	 */
	@Override
	public int updateGroupBuildingHasTPropertyService(GroupBuildingHasTPropertyService groupBuildingHasTPropertyService){
		return groupBuildingHasTPropertyServiceBaseDao.updateGroupBuildingHasTPropertyService(groupBuildingHasTPropertyService);
	}
	/**
	 * 批量更新(小区服务关系表)信息
	 * @param groupBuildingHasTPropertyServiceList
	 * @return
	 */
	@Override
	public int updateGroupBuildingHasTPropertyServiceBatch(List<GroupBuildingHasTPropertyService> groupBuildingHasTPropertyServiceList){
		return groupBuildingHasTPropertyServiceBaseDao.updateGroupBuildingHasTPropertyServiceBatch(groupBuildingHasTPropertyServiceList);
	}
	/**
	 * 根据序列号删除(小区服务关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteGroupBuildingHasTPropertyServiceLogic(java.math.BigInteger id){
		return groupBuildingHasTPropertyServiceBaseDao.deleteGroupBuildingHasTPropertyServiceLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(小区服务关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteGroupBuildingHasTPropertyServiceLogicBatch(List<java.math.BigInteger> idList){
		return groupBuildingHasTPropertyServiceBaseDao.deleteGroupBuildingHasTPropertyServiceLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(小区服务关系表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteGroupBuildingHasTPropertyService(java.math.BigInteger id){
//		return groupBuildingHasTPropertyServiceBaseDao.deleteGroupBuildingHasTPropertyService(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(小区服务关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteGroupBuildingHasTPropertyServiceBatch(List<java.math.BigInteger> idList){
//		return groupBuildingHasTPropertyServiceBaseDao.deleteGroupBuildingHasTPropertyServiceBatch(idList);
//	}
	
}
