package com.cnfantasia.server.domainbase.groupBuildingBillCycleConfig.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.groupBuildingBillCycleConfig.dao.IGroupBuildingBillCycleConfigBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.groupBuildingBillCycleConfig.entity.GroupBuildingBillCycleConfig;

/**
 * 描述:(收费时间管理配置（自动生成账期和账单的配置）) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class GroupBuildingBillCycleConfigBaseService implements IGroupBuildingBillCycleConfigBaseService{
	
	private IGroupBuildingBillCycleConfigBaseDao groupBuildingBillCycleConfigBaseDao;
	public void setGroupBuildingBillCycleConfigBaseDao(IGroupBuildingBillCycleConfigBaseDao groupBuildingBillCycleConfigBaseDao) {
		this.groupBuildingBillCycleConfigBaseDao = groupBuildingBillCycleConfigBaseDao;
	}
	/**
	 * 根据条件查询(收费时间管理配置（自动生成账期和账单的配置）)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<GroupBuildingBillCycleConfig> getGroupBuildingBillCycleConfigByCondition(Map<String,Object> paramMap){
		return groupBuildingBillCycleConfigBaseDao.selectGroupBuildingBillCycleConfigByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(收费时间管理配置（自动生成账期和账单的配置）)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<GroupBuildingBillCycleConfig> getGroupBuildingBillCycleConfigByConditionDim(Map<String,Object> paramMap){
		return groupBuildingBillCycleConfigBaseDao.selectGroupBuildingBillCycleConfigByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(收费时间管理配置（自动生成账期和账单的配置）)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<GroupBuildingBillCycleConfig> getGroupBuildingBillCycleConfigByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return groupBuildingBillCycleConfigBaseDao.selectGroupBuildingBillCycleConfigByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(收费时间管理配置（自动生成账期和账单的配置）)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<GroupBuildingBillCycleConfig> getGroupBuildingBillCycleConfigByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return groupBuildingBillCycleConfigBaseDao.selectGroupBuildingBillCycleConfigByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(收费时间管理配置（自动生成账期和账单的配置）)信息
	 * @param id
	 * @return
	 */
	@Override
	public GroupBuildingBillCycleConfig getGroupBuildingBillCycleConfigBySeqId(java.math.BigInteger id){
		return groupBuildingBillCycleConfigBaseDao.selectGroupBuildingBillCycleConfigBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(收费时间管理配置（自动生成账期和账单的配置）)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getGroupBuildingBillCycleConfigCount(Map<String,Object> paramMap){
		return groupBuildingBillCycleConfigBaseDao.selectGroupBuildingBillCycleConfigCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(收费时间管理配置（自动生成账期和账单的配置）)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getGroupBuildingBillCycleConfigCountDim(Map<String,Object> paramMap){
		return groupBuildingBillCycleConfigBaseDao.selectGroupBuildingBillCycleConfigCount(paramMap,true);
	}
	/**
	 * 往(收费时间管理配置（自动生成账期和账单的配置）)新增一条记录
	 * @param groupBuildingBillCycleConfig
	 * @return
	 */
	@Override
	public int insertGroupBuildingBillCycleConfig(GroupBuildingBillCycleConfig groupBuildingBillCycleConfig){
		return groupBuildingBillCycleConfigBaseDao.insertGroupBuildingBillCycleConfig(groupBuildingBillCycleConfig);
	}
	/**
	 * 批量新增(收费时间管理配置（自动生成账期和账单的配置）)
	 * @param groupBuildingBillCycleConfigList
	 * @return
	 */
	@Override
	public int insertGroupBuildingBillCycleConfigBatch(List<GroupBuildingBillCycleConfig> groupBuildingBillCycleConfigList){
		return groupBuildingBillCycleConfigBaseDao.insertGroupBuildingBillCycleConfigBatch(groupBuildingBillCycleConfigList);
	}
	/**
	 * 更新(收费时间管理配置（自动生成账期和账单的配置）)信息
	 * @param groupBuildingBillCycleConfig
	 * @return
	 */
	@Override
	public int updateGroupBuildingBillCycleConfig(GroupBuildingBillCycleConfig groupBuildingBillCycleConfig){
		return groupBuildingBillCycleConfigBaseDao.updateGroupBuildingBillCycleConfig(groupBuildingBillCycleConfig);
	}
	/**
	 * 批量更新(收费时间管理配置（自动生成账期和账单的配置）)信息
	 * @param groupBuildingBillCycleConfigList
	 * @return
	 */
	@Override
	public int updateGroupBuildingBillCycleConfigBatch(List<GroupBuildingBillCycleConfig> groupBuildingBillCycleConfigList){
		return groupBuildingBillCycleConfigBaseDao.updateGroupBuildingBillCycleConfigBatch(groupBuildingBillCycleConfigList);
	}
	/**
	 * 根据序列号删除(收费时间管理配置（自动生成账期和账单的配置）)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteGroupBuildingBillCycleConfigLogic(java.math.BigInteger id){
		return groupBuildingBillCycleConfigBaseDao.deleteGroupBuildingBillCycleConfigLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(收费时间管理配置（自动生成账期和账单的配置）)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteGroupBuildingBillCycleConfigLogicBatch(List<java.math.BigInteger> idList){
		return groupBuildingBillCycleConfigBaseDao.deleteGroupBuildingBillCycleConfigLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(收费时间管理配置（自动生成账期和账单的配置）)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteGroupBuildingBillCycleConfig(java.math.BigInteger id){
//		return groupBuildingBillCycleConfigBaseDao.deleteGroupBuildingBillCycleConfig(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(收费时间管理配置（自动生成账期和账单的配置）)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteGroupBuildingBillCycleConfigBatch(List<java.math.BigInteger> idList){
//		return groupBuildingBillCycleConfigBaseDao.deleteGroupBuildingBillCycleConfigBatch(idList);
//	}
	
}
