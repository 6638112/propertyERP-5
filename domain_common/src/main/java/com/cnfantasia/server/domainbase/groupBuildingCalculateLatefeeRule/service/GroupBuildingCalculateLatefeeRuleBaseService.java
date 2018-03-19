package com.cnfantasia.server.domainbase.groupBuildingCalculateLatefeeRule.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.groupBuildingCalculateLatefeeRule.dao.IGroupBuildingCalculateLatefeeRuleBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.groupBuildingCalculateLatefeeRule.entity.GroupBuildingCalculateLatefeeRule;

/**
 * 描述:(小区滞纳金配置表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class GroupBuildingCalculateLatefeeRuleBaseService implements IGroupBuildingCalculateLatefeeRuleBaseService{
	
	private IGroupBuildingCalculateLatefeeRuleBaseDao groupBuildingCalculateLatefeeRuleBaseDao;
	public void setGroupBuildingCalculateLatefeeRuleBaseDao(IGroupBuildingCalculateLatefeeRuleBaseDao groupBuildingCalculateLatefeeRuleBaseDao) {
		this.groupBuildingCalculateLatefeeRuleBaseDao = groupBuildingCalculateLatefeeRuleBaseDao;
	}
	/**
	 * 根据条件查询(小区滞纳金配置表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<GroupBuildingCalculateLatefeeRule> getGroupBuildingCalculateLatefeeRuleByCondition(Map<String,Object> paramMap){
		return groupBuildingCalculateLatefeeRuleBaseDao.selectGroupBuildingCalculateLatefeeRuleByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(小区滞纳金配置表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<GroupBuildingCalculateLatefeeRule> getGroupBuildingCalculateLatefeeRuleByConditionDim(Map<String,Object> paramMap){
		return groupBuildingCalculateLatefeeRuleBaseDao.selectGroupBuildingCalculateLatefeeRuleByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(小区滞纳金配置表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<GroupBuildingCalculateLatefeeRule> getGroupBuildingCalculateLatefeeRuleByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return groupBuildingCalculateLatefeeRuleBaseDao.selectGroupBuildingCalculateLatefeeRuleByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(小区滞纳金配置表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<GroupBuildingCalculateLatefeeRule> getGroupBuildingCalculateLatefeeRuleByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return groupBuildingCalculateLatefeeRuleBaseDao.selectGroupBuildingCalculateLatefeeRuleByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(小区滞纳金配置表)信息
	 * @param id
	 * @return
	 */
	@Override
	public GroupBuildingCalculateLatefeeRule getGroupBuildingCalculateLatefeeRuleBySeqId(java.math.BigInteger id){
		return groupBuildingCalculateLatefeeRuleBaseDao.selectGroupBuildingCalculateLatefeeRuleBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(小区滞纳金配置表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getGroupBuildingCalculateLatefeeRuleCount(Map<String,Object> paramMap){
		return groupBuildingCalculateLatefeeRuleBaseDao.selectGroupBuildingCalculateLatefeeRuleCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(小区滞纳金配置表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getGroupBuildingCalculateLatefeeRuleCountDim(Map<String,Object> paramMap){
		return groupBuildingCalculateLatefeeRuleBaseDao.selectGroupBuildingCalculateLatefeeRuleCount(paramMap,true);
	}
	/**
	 * 往(小区滞纳金配置表)新增一条记录
	 * @param groupBuildingCalculateLatefeeRule
	 * @return
	 */
	@Override
	public int insertGroupBuildingCalculateLatefeeRule(GroupBuildingCalculateLatefeeRule groupBuildingCalculateLatefeeRule){
		return groupBuildingCalculateLatefeeRuleBaseDao.insertGroupBuildingCalculateLatefeeRule(groupBuildingCalculateLatefeeRule);
	}
	/**
	 * 批量新增(小区滞纳金配置表)
	 * @param groupBuildingCalculateLatefeeRuleList
	 * @return
	 */
	@Override
	public int insertGroupBuildingCalculateLatefeeRuleBatch(List<GroupBuildingCalculateLatefeeRule> groupBuildingCalculateLatefeeRuleList){
		return groupBuildingCalculateLatefeeRuleBaseDao.insertGroupBuildingCalculateLatefeeRuleBatch(groupBuildingCalculateLatefeeRuleList);
	}
	/**
	 * 更新(小区滞纳金配置表)信息
	 * @param groupBuildingCalculateLatefeeRule
	 * @return
	 */
	@Override
	public int updateGroupBuildingCalculateLatefeeRule(GroupBuildingCalculateLatefeeRule groupBuildingCalculateLatefeeRule){
		return groupBuildingCalculateLatefeeRuleBaseDao.updateGroupBuildingCalculateLatefeeRule(groupBuildingCalculateLatefeeRule);
	}
	/**
	 * 批量更新(小区滞纳金配置表)信息
	 * @param groupBuildingCalculateLatefeeRuleList
	 * @return
	 */
	@Override
	public int updateGroupBuildingCalculateLatefeeRuleBatch(List<GroupBuildingCalculateLatefeeRule> groupBuildingCalculateLatefeeRuleList){
		return groupBuildingCalculateLatefeeRuleBaseDao.updateGroupBuildingCalculateLatefeeRuleBatch(groupBuildingCalculateLatefeeRuleList);
	}
	/**
	 * 根据序列号删除(小区滞纳金配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteGroupBuildingCalculateLatefeeRuleLogic(java.math.BigInteger id){
		return groupBuildingCalculateLatefeeRuleBaseDao.deleteGroupBuildingCalculateLatefeeRuleLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(小区滞纳金配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteGroupBuildingCalculateLatefeeRuleLogicBatch(List<java.math.BigInteger> idList){
		return groupBuildingCalculateLatefeeRuleBaseDao.deleteGroupBuildingCalculateLatefeeRuleLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(小区滞纳金配置表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteGroupBuildingCalculateLatefeeRule(java.math.BigInteger id){
//		return groupBuildingCalculateLatefeeRuleBaseDao.deleteGroupBuildingCalculateLatefeeRule(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(小区滞纳金配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteGroupBuildingCalculateLatefeeRuleBatch(List<java.math.BigInteger> idList){
//		return groupBuildingCalculateLatefeeRuleBaseDao.deleteGroupBuildingCalculateLatefeeRuleBatch(idList);
//	}
	
}
