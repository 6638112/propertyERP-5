package com.cnfantasia.server.domainbase.propertyCardDeductionDetail.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyCardDeductionDetail.entity.PropertyCardDeductionDetail;

/**
 * 描述:(物业代扣卡划扣详情) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyCardDeductionDetailBaseDao {
	/**
	 * 根据条件查询(物业代扣卡划扣详情)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PropertyCardDeductionDetail> selectPropertyCardDeductionDetailByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(物业代扣卡划扣详情)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PropertyCardDeductionDetail> selectPropertyCardDeductionDetailByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(物业代扣卡划扣详情)信息
	 * @param id
	 * @return
	 */
	public PropertyCardDeductionDetail selectPropertyCardDeductionDetailBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业代扣卡划扣详情)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPropertyCardDeductionDetailCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(物业代扣卡划扣详情)新增一条记录
	 * @param propertyCardDeductionDetail
	 * @return
	 */
	public int insertPropertyCardDeductionDetail(PropertyCardDeductionDetail propertyCardDeductionDetail);
	
	/**
	 * 批量新增(物业代扣卡划扣详情)信息
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
	 * 根据Id 批量删除(物业代扣卡划扣详情)信息_逻辑删除
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
