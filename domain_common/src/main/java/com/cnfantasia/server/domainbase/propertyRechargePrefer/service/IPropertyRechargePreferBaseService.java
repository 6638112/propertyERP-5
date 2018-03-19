package com.cnfantasia.server.domainbase.propertyRechargePrefer.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.propertyRechargePrefer.entity.PropertyRechargePrefer;

/**
 * 描述:(物业充值随机立减记录表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyRechargePreferBaseService {
	
	/**
	 * 根据条件查询(物业充值随机立减记录表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyRechargePrefer> getPropertyRechargePreferByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(物业充值随机立减记录表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyRechargePrefer> getPropertyRechargePreferByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(物业充值随机立减记录表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyRechargePrefer> getPropertyRechargePreferByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(物业充值随机立减记录表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyRechargePrefer> getPropertyRechargePreferByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(物业充值随机立减记录表)信息
	 * @param id
	 * @return
	 */
	public PropertyRechargePrefer getPropertyRechargePreferBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业充值随机立减记录表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyRechargePreferCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(物业充值随机立减记录表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyRechargePreferCountDim(Map<String,Object> paramMap);
	/**
	 * 往(物业充值随机立减记录表)新增一条记录
	 * @param propertyRechargePrefer
	 * @return
	 */
	public int insertPropertyRechargePrefer(PropertyRechargePrefer propertyRechargePrefer);
	/**
	 * 批量新增(物业充值随机立减记录表)
	 * @param propertyRechargePreferList
	 * @return
	 */
	public int insertPropertyRechargePreferBatch(List<PropertyRechargePrefer> propertyRechargePreferList);
	/**
	 * 更新(物业充值随机立减记录表)信息
	 * @param propertyRechargePrefer
	 * @return
	 */
	public int updatePropertyRechargePrefer(PropertyRechargePrefer propertyRechargePrefer);
	/**
	 * 批量更新(物业充值随机立减记录表)信息
	 * @param propertyRechargePreferList
	 * @return
	 */
	public int updatePropertyRechargePreferBatch(List<PropertyRechargePrefer> propertyRechargePreferList);
	/**
	 * 根据序列号删除(物业充值随机立减记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePropertyRechargePreferLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(物业充值随机立减记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePropertyRechargePreferLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(物业充值随机立减记录表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePropertyRechargePrefer(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(物业充值随机立减记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePropertyRechargePreferBatch(List<java.math.BigInteger> idList);
	
}
