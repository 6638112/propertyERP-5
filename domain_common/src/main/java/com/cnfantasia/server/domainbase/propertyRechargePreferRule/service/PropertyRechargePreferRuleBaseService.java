package com.cnfantasia.server.domainbase.propertyRechargePreferRule.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.propertyRechargePreferRule.dao.IPropertyRechargePreferRuleBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyRechargePreferRule.entity.PropertyRechargePreferRule;

/**
 * 描述:(物业预充值随机立减规则表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PropertyRechargePreferRuleBaseService implements IPropertyRechargePreferRuleBaseService{
	
	private IPropertyRechargePreferRuleBaseDao propertyRechargePreferRuleBaseDao;
	public void setPropertyRechargePreferRuleBaseDao(IPropertyRechargePreferRuleBaseDao propertyRechargePreferRuleBaseDao) {
		this.propertyRechargePreferRuleBaseDao = propertyRechargePreferRuleBaseDao;
	}
	/**
	 * 根据条件查询(物业预充值随机立减规则表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyRechargePreferRule> getPropertyRechargePreferRuleByCondition(Map<String,Object> paramMap){
		return propertyRechargePreferRuleBaseDao.selectPropertyRechargePreferRuleByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(物业预充值随机立减规则表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyRechargePreferRule> getPropertyRechargePreferRuleByConditionDim(Map<String,Object> paramMap){
		return propertyRechargePreferRuleBaseDao.selectPropertyRechargePreferRuleByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(物业预充值随机立减规则表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyRechargePreferRule> getPropertyRechargePreferRuleByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyRechargePreferRuleBaseDao.selectPropertyRechargePreferRuleByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(物业预充值随机立减规则表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyRechargePreferRule> getPropertyRechargePreferRuleByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyRechargePreferRuleBaseDao.selectPropertyRechargePreferRuleByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(物业预充值随机立减规则表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyRechargePreferRule getPropertyRechargePreferRuleBySeqId(java.math.BigInteger id){
		return propertyRechargePreferRuleBaseDao.selectPropertyRechargePreferRuleBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(物业预充值随机立减规则表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyRechargePreferRuleCount(Map<String,Object> paramMap){
		return propertyRechargePreferRuleBaseDao.selectPropertyRechargePreferRuleCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(物业预充值随机立减规则表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyRechargePreferRuleCountDim(Map<String,Object> paramMap){
		return propertyRechargePreferRuleBaseDao.selectPropertyRechargePreferRuleCount(paramMap,true);
	}
	/**
	 * 往(物业预充值随机立减规则表)新增一条记录
	 * @param propertyRechargePreferRule
	 * @return
	 */
	@Override
	public int insertPropertyRechargePreferRule(PropertyRechargePreferRule propertyRechargePreferRule){
		return propertyRechargePreferRuleBaseDao.insertPropertyRechargePreferRule(propertyRechargePreferRule);
	}
	/**
	 * 批量新增(物业预充值随机立减规则表)
	 * @param propertyRechargePreferRuleList
	 * @return
	 */
	@Override
	public int insertPropertyRechargePreferRuleBatch(List<PropertyRechargePreferRule> propertyRechargePreferRuleList){
		return propertyRechargePreferRuleBaseDao.insertPropertyRechargePreferRuleBatch(propertyRechargePreferRuleList);
	}
	/**
	 * 更新(物业预充值随机立减规则表)信息
	 * @param propertyRechargePreferRule
	 * @return
	 */
	@Override
	public int updatePropertyRechargePreferRule(PropertyRechargePreferRule propertyRechargePreferRule){
		return propertyRechargePreferRuleBaseDao.updatePropertyRechargePreferRule(propertyRechargePreferRule);
	}
	/**
	 * 批量更新(物业预充值随机立减规则表)信息
	 * @param propertyRechargePreferRuleList
	 * @return
	 */
	@Override
	public int updatePropertyRechargePreferRuleBatch(List<PropertyRechargePreferRule> propertyRechargePreferRuleList){
		return propertyRechargePreferRuleBaseDao.updatePropertyRechargePreferRuleBatch(propertyRechargePreferRuleList);
	}
	/**
	 * 根据序列号删除(物业预充值随机立减规则表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyRechargePreferRuleLogic(java.math.BigInteger id){
		return propertyRechargePreferRuleBaseDao.deletePropertyRechargePreferRuleLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(物业预充值随机立减规则表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyRechargePreferRuleLogicBatch(List<java.math.BigInteger> idList){
		return propertyRechargePreferRuleBaseDao.deletePropertyRechargePreferRuleLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(物业预充值随机立减规则表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyRechargePreferRule(java.math.BigInteger id){
//		return propertyRechargePreferRuleBaseDao.deletePropertyRechargePreferRule(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业预充值随机立减规则表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyRechargePreferRuleBatch(List<java.math.BigInteger> idList){
//		return propertyRechargePreferRuleBaseDao.deletePropertyRechargePreferRuleBatch(idList);
//	}
	
}
