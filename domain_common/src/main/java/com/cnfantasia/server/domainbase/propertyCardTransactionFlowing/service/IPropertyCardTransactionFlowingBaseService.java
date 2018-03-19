package com.cnfantasia.server.domainbase.propertyCardTransactionFlowing.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.propertyCardTransactionFlowing.entity.PropertyCardTransactionFlowing;

/**
 * 描述:(物业代扣卡交易流水) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyCardTransactionFlowingBaseService {
	
	/**
	 * 根据条件查询(物业代扣卡交易流水)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyCardTransactionFlowing> getPropertyCardTransactionFlowingByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(物业代扣卡交易流水)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyCardTransactionFlowing> getPropertyCardTransactionFlowingByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(物业代扣卡交易流水)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyCardTransactionFlowing> getPropertyCardTransactionFlowingByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(物业代扣卡交易流水)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyCardTransactionFlowing> getPropertyCardTransactionFlowingByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(物业代扣卡交易流水)信息
	 * @param id
	 * @return
	 */
	public PropertyCardTransactionFlowing getPropertyCardTransactionFlowingBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业代扣卡交易流水)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyCardTransactionFlowingCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(物业代扣卡交易流水)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyCardTransactionFlowingCountDim(Map<String,Object> paramMap);
	/**
	 * 往(物业代扣卡交易流水)新增一条记录
	 * @param propertyCardTransactionFlowing
	 * @return
	 */
	public int insertPropertyCardTransactionFlowing(PropertyCardTransactionFlowing propertyCardTransactionFlowing);
	/**
	 * 批量新增(物业代扣卡交易流水)
	 * @param propertyCardTransactionFlowingList
	 * @return
	 */
	public int insertPropertyCardTransactionFlowingBatch(List<PropertyCardTransactionFlowing> propertyCardTransactionFlowingList);
	/**
	 * 更新(物业代扣卡交易流水)信息
	 * @param propertyCardTransactionFlowing
	 * @return
	 */
	public int updatePropertyCardTransactionFlowing(PropertyCardTransactionFlowing propertyCardTransactionFlowing);
	/**
	 * 批量更新(物业代扣卡交易流水)信息
	 * @param propertyCardTransactionFlowingList
	 * @return
	 */
	public int updatePropertyCardTransactionFlowingBatch(List<PropertyCardTransactionFlowing> propertyCardTransactionFlowingList);
	/**
	 * 根据序列号删除(物业代扣卡交易流水)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePropertyCardTransactionFlowingLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(物业代扣卡交易流水)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePropertyCardTransactionFlowingLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(物业代扣卡交易流水)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePropertyCardTransactionFlowing(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(物业代扣卡交易流水)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePropertyCardTransactionFlowingBatch(List<java.math.BigInteger> idList);
	
}
