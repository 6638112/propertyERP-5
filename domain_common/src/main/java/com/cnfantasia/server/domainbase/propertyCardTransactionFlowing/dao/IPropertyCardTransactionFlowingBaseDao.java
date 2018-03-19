package com.cnfantasia.server.domainbase.propertyCardTransactionFlowing.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyCardTransactionFlowing.entity.PropertyCardTransactionFlowing;

/**
 * 描述:(物业代扣卡交易流水) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyCardTransactionFlowingBaseDao {
	/**
	 * 根据条件查询(物业代扣卡交易流水)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PropertyCardTransactionFlowing> selectPropertyCardTransactionFlowingByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(物业代扣卡交易流水)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PropertyCardTransactionFlowing> selectPropertyCardTransactionFlowingByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(物业代扣卡交易流水)信息
	 * @param id
	 * @return
	 */
	public PropertyCardTransactionFlowing selectPropertyCardTransactionFlowingBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业代扣卡交易流水)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPropertyCardTransactionFlowingCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(物业代扣卡交易流水)新增一条记录
	 * @param propertyCardTransactionFlowing
	 * @return
	 */
	public int insertPropertyCardTransactionFlowing(PropertyCardTransactionFlowing propertyCardTransactionFlowing);
	
	/**
	 * 批量新增(物业代扣卡交易流水)信息
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
	 * 根据Id 批量删除(物业代扣卡交易流水)信息_逻辑删除
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
