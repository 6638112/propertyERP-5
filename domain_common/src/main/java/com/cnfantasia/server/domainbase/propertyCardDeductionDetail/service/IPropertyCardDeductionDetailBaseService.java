package com.cnfantasia.server.domainbase.propertyCardDeductionDetail.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.propertyCardDeductionDetail.entity.PropertyCardDeductionDetail;

/**
 * 描述:(物业代扣卡划扣详情) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyCardDeductionDetailBaseService {
	
	/**
	 * 根据条件查询(物业代扣卡划扣详情)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyCardDeductionDetail> getPropertyCardDeductionDetailByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(物业代扣卡划扣详情)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyCardDeductionDetail> getPropertyCardDeductionDetailByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(物业代扣卡划扣详情)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyCardDeductionDetail> getPropertyCardDeductionDetailByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(物业代扣卡划扣详情)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyCardDeductionDetail> getPropertyCardDeductionDetailByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(物业代扣卡划扣详情)信息
	 * @param id
	 * @return
	 */
	public PropertyCardDeductionDetail getPropertyCardDeductionDetailBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业代扣卡划扣详情)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyCardDeductionDetailCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(物业代扣卡划扣详情)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyCardDeductionDetailCountDim(Map<String,Object> paramMap);
	/**
	 * 往(物业代扣卡划扣详情)新增一条记录
	 * @param propertyCardDeductionDetail
	 * @return
	 */
	public int insertPropertyCardDeductionDetail(PropertyCardDeductionDetail propertyCardDeductionDetail);
	/**
	 * 批量新增(物业代扣卡划扣详情)
	 * @param propertyCardDeductionDetailList
	 * @return
	 */
	public int insertPropertyCardDeductionDetailBatch(List<PropertyCardDeductionDetail> propertyCardDeductionDetailList);
	/**
	 * 更新(物业代扣卡划扣详情)信息
	 * @param propertyCardDeductionDetail
	 * @return
	 */
	public int updatePropertyCardDeductionDetail(PropertyCardDeductionDetail propertyCardDeductionDetail);
	/**
	 * 批量更新(物业代扣卡划扣详情)信息
	 * @param propertyCardDeductionDetailList
	 * @return
	 */
	public int updatePropertyCardDeductionDetailBatch(List<PropertyCardDeductionDetail> propertyCardDeductionDetailList);
	/**
	 * 根据序列号删除(物业代扣卡划扣详情)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePropertyCardDeductionDetailLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(物业代扣卡划扣详情)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePropertyCardDeductionDetailLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(物业代扣卡划扣详情)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePropertyCardDeductionDetail(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(物业代扣卡划扣详情)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePropertyCardDeductionDetailBatch(List<java.math.BigInteger> idList);
	
}
