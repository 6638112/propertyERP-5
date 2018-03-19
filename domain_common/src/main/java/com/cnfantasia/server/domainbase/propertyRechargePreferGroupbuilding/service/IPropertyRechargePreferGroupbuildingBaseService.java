package com.cnfantasia.server.domainbase.propertyRechargePreferGroupbuilding.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.propertyRechargePreferGroupbuilding.entity.PropertyRechargePreferGroupbuilding;

/**
 * 描述:(物业预充值随机立减小区规则关联表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyRechargePreferGroupbuildingBaseService {
	
	/**
	 * 根据条件查询(物业预充值随机立减小区规则关联表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyRechargePreferGroupbuilding> getPropertyRechargePreferGroupbuildingByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(物业预充值随机立减小区规则关联表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyRechargePreferGroupbuilding> getPropertyRechargePreferGroupbuildingByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(物业预充值随机立减小区规则关联表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyRechargePreferGroupbuilding> getPropertyRechargePreferGroupbuildingByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(物业预充值随机立减小区规则关联表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyRechargePreferGroupbuilding> getPropertyRechargePreferGroupbuildingByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(物业预充值随机立减小区规则关联表)信息
	 * @param id
	 * @return
	 */
	public PropertyRechargePreferGroupbuilding getPropertyRechargePreferGroupbuildingBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业预充值随机立减小区规则关联表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyRechargePreferGroupbuildingCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(物业预充值随机立减小区规则关联表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyRechargePreferGroupbuildingCountDim(Map<String,Object> paramMap);
	/**
	 * 往(物业预充值随机立减小区规则关联表)新增一条记录
	 * @param propertyRechargePreferGroupbuilding
	 * @return
	 */
	public int insertPropertyRechargePreferGroupbuilding(PropertyRechargePreferGroupbuilding propertyRechargePreferGroupbuilding);
	/**
	 * 批量新增(物业预充值随机立减小区规则关联表)
	 * @param propertyRechargePreferGroupbuildingList
	 * @return
	 */
	public int insertPropertyRechargePreferGroupbuildingBatch(List<PropertyRechargePreferGroupbuilding> propertyRechargePreferGroupbuildingList);
	/**
	 * 更新(物业预充值随机立减小区规则关联表)信息
	 * @param propertyRechargePreferGroupbuilding
	 * @return
	 */
	public int updatePropertyRechargePreferGroupbuilding(PropertyRechargePreferGroupbuilding propertyRechargePreferGroupbuilding);
	/**
	 * 批量更新(物业预充值随机立减小区规则关联表)信息
	 * @param propertyRechargePreferGroupbuildingList
	 * @return
	 */
	public int updatePropertyRechargePreferGroupbuildingBatch(List<PropertyRechargePreferGroupbuilding> propertyRechargePreferGroupbuildingList);
	/**
	 * 根据序列号删除(物业预充值随机立减小区规则关联表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	public int deletePropertyRechargePreferGroupbuildingLogic(java.math.BigInteger id);
	 */
	/**
	 * 根据序列号批量删除(物业预充值随机立减小区规则关联表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	public int deletePropertyRechargePreferGroupbuildingLogicBatch(List<java.math.BigInteger> idList);
	 */
//	/**
//	 * 根据序列号删除(物业预充值随机立减小区规则关联表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePropertyRechargePreferGroupbuilding(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(物业预充值随机立减小区规则关联表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePropertyRechargePreferGroupbuildingBatch(List<java.math.BigInteger> idList);
	
}
