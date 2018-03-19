package com.cnfantasia.server.domainbase.propertyCardDiscountTerm.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.propertyCardDiscountTerm.entity.PropertyCardDiscountTerm;

/**
 * 描述:(物业代扣卡优惠方案) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyCardDiscountTermBaseService {
	
	/**
	 * 根据条件查询(物业代扣卡优惠方案)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyCardDiscountTerm> getPropertyCardDiscountTermByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(物业代扣卡优惠方案)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyCardDiscountTerm> getPropertyCardDiscountTermByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(物业代扣卡优惠方案)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyCardDiscountTerm> getPropertyCardDiscountTermByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(物业代扣卡优惠方案)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyCardDiscountTerm> getPropertyCardDiscountTermByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(物业代扣卡优惠方案)信息
	 * @param id
	 * @return
	 */
	public PropertyCardDiscountTerm getPropertyCardDiscountTermBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业代扣卡优惠方案)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyCardDiscountTermCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(物业代扣卡优惠方案)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyCardDiscountTermCountDim(Map<String,Object> paramMap);
	/**
	 * 往(物业代扣卡优惠方案)新增一条记录
	 * @param propertyCardDiscountTerm
	 * @return
	 */
	public int insertPropertyCardDiscountTerm(PropertyCardDiscountTerm propertyCardDiscountTerm);
	/**
	 * 批量新增(物业代扣卡优惠方案)
	 * @param propertyCardDiscountTermList
	 * @return
	 */
	public int insertPropertyCardDiscountTermBatch(List<PropertyCardDiscountTerm> propertyCardDiscountTermList);
	/**
	 * 更新(物业代扣卡优惠方案)信息
	 * @param propertyCardDiscountTerm
	 * @return
	 */
	public int updatePropertyCardDiscountTerm(PropertyCardDiscountTerm propertyCardDiscountTerm);
	/**
	 * 批量更新(物业代扣卡优惠方案)信息
	 * @param propertyCardDiscountTermList
	 * @return
	 */
	public int updatePropertyCardDiscountTermBatch(List<PropertyCardDiscountTerm> propertyCardDiscountTermList);
	/**
	 * 根据序列号删除(物业代扣卡优惠方案)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePropertyCardDiscountTermLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(物业代扣卡优惠方案)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePropertyCardDiscountTermLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(物业代扣卡优惠方案)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePropertyCardDiscountTerm(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(物业代扣卡优惠方案)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePropertyCardDiscountTermBatch(List<java.math.BigInteger> idList);
	
}
