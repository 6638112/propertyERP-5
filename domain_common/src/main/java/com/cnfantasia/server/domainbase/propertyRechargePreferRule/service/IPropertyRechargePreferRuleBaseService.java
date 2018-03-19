package com.cnfantasia.server.domainbase.propertyRechargePreferRule.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.propertyRechargePreferRule.entity.PropertyRechargePreferRule;

/**
 * 描述:(物业预充值随机立减规则表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyRechargePreferRuleBaseService {
	
	/**
	 * 根据条件查询(物业预充值随机立减规则表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyRechargePreferRule> getPropertyRechargePreferRuleByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(物业预充值随机立减规则表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyRechargePreferRule> getPropertyRechargePreferRuleByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(物业预充值随机立减规则表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyRechargePreferRule> getPropertyRechargePreferRuleByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(物业预充值随机立减规则表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyRechargePreferRule> getPropertyRechargePreferRuleByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(物业预充值随机立减规则表)信息
	 * @param id
	 * @return
	 */
	public PropertyRechargePreferRule getPropertyRechargePreferRuleBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业预充值随机立减规则表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyRechargePreferRuleCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(物业预充值随机立减规则表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyRechargePreferRuleCountDim(Map<String,Object> paramMap);
	/**
	 * 往(物业预充值随机立减规则表)新增一条记录
	 * @param propertyRechargePreferRule
	 * @return
	 */
	public int insertPropertyRechargePreferRule(PropertyRechargePreferRule propertyRechargePreferRule);
	/**
	 * 批量新增(物业预充值随机立减规则表)
	 * @param propertyRechargePreferRuleList
	 * @return
	 */
	public int insertPropertyRechargePreferRuleBatch(List<PropertyRechargePreferRule> propertyRechargePreferRuleList);
	/**
	 * 更新(物业预充值随机立减规则表)信息
	 * @param propertyRechargePreferRule
	 * @return
	 */
	public int updatePropertyRechargePreferRule(PropertyRechargePreferRule propertyRechargePreferRule);
	/**
	 * 批量更新(物业预充值随机立减规则表)信息
	 * @param propertyRechargePreferRuleList
	 * @return
	 */
	public int updatePropertyRechargePreferRuleBatch(List<PropertyRechargePreferRule> propertyRechargePreferRuleList);
	/**
	 * 根据序列号删除(物业预充值随机立减规则表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePropertyRechargePreferRuleLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(物业预充值随机立减规则表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePropertyRechargePreferRuleLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(物业预充值随机立减规则表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePropertyRechargePreferRule(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(物业预充值随机立减规则表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePropertyRechargePreferRuleBatch(List<java.math.BigInteger> idList);
	
}
