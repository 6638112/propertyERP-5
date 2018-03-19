package com.cnfantasia.server.domainbase.propertyFeeDetailUnpaid.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.propertyFeeDetailUnpaid.entity.PropertyFeeDetailUnpaid;

/**
 * 描述:(账单欠费信息表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyFeeDetailUnpaidBaseService {
	
	/**
	 * 根据条件查询(账单欠费信息表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	List<PropertyFeeDetailUnpaid> getPropertyFeeDetailUnpaidByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(账单欠费信息表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	List<PropertyFeeDetailUnpaid> getPropertyFeeDetailUnpaidByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(账单欠费信息表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<PropertyFeeDetailUnpaid> getPropertyFeeDetailUnpaidByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(账单欠费信息表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<PropertyFeeDetailUnpaid> getPropertyFeeDetailUnpaidByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(账单欠费信息表)信息
	 * @param id
	 * @return
	 */
	PropertyFeeDetailUnpaid getPropertyFeeDetailUnpaidBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(账单欠费信息表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	int getPropertyFeeDetailUnpaidCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(账单欠费信息表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	int getPropertyFeeDetailUnpaidCountDim(Map<String, Object> paramMap);
	/**
	 * 往(账单欠费信息表)新增一条记录
	 * @param propertyFeeDetailUnpaid
	 * @return
	 */
	int insertPropertyFeeDetailUnpaid(PropertyFeeDetailUnpaid propertyFeeDetailUnpaid);
	/**
	 * 批量新增(账单欠费信息表)
	 * @param propertyFeeDetailUnpaidList
	 * @return
	 */
	int insertPropertyFeeDetailUnpaidBatch(List<PropertyFeeDetailUnpaid> propertyFeeDetailUnpaidList);
	/**
	 * 更新(账单欠费信息表)信息
	 * @param propertyFeeDetailUnpaid
	 * @return
	 */
	int updatePropertyFeeDetailUnpaid(PropertyFeeDetailUnpaid propertyFeeDetailUnpaid);
	/**
	 * 批量更新(账单欠费信息表)信息
	 * @param propertyFeeDetailUnpaidList
	 * @return
	 */
	int updatePropertyFeeDetailUnpaidBatch(List<PropertyFeeDetailUnpaid> propertyFeeDetailUnpaidList);
	/**
	 * 根据序列号删除(账单欠费信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	public int deletePropertyFeeDetailUnpaidLogic(java.math.BigInteger id);
	 */
	/**
	 * 根据序列号批量删除(账单欠费信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	public int deletePropertyFeeDetailUnpaidLogicBatch(List<java.math.BigInteger> idList);
	 */
//	/**
//	 * 根据序列号删除(账单欠费信息表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePropertyFeeDetailUnpaid(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(账单欠费信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePropertyFeeDetailUnpaidBatch(List<java.math.BigInteger> idList);
	
}
