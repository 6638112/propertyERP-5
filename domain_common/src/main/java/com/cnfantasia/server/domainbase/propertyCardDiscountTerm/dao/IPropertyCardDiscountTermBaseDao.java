package com.cnfantasia.server.domainbase.propertyCardDiscountTerm.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyCardDiscountTerm.entity.PropertyCardDiscountTerm;

/**
 * 描述:(物业代扣卡优惠方案) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyCardDiscountTermBaseDao {
	/**
	 * 根据条件查询(物业代扣卡优惠方案)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PropertyCardDiscountTerm> selectPropertyCardDiscountTermByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(物业代扣卡优惠方案)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PropertyCardDiscountTerm> selectPropertyCardDiscountTermByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(物业代扣卡优惠方案)信息
	 * @param id
	 * @return
	 */
	public PropertyCardDiscountTerm selectPropertyCardDiscountTermBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业代扣卡优惠方案)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPropertyCardDiscountTermCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(物业代扣卡优惠方案)新增一条记录
	 * @param propertyCardDiscountTerm
	 * @return
	 */
	public int insertPropertyCardDiscountTerm(PropertyCardDiscountTerm propertyCardDiscountTerm);
	
	/**
	 * 批量新增(物业代扣卡优惠方案)信息
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
	 * 根据Id 批量删除(物业代扣卡优惠方案)信息_逻辑删除
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
