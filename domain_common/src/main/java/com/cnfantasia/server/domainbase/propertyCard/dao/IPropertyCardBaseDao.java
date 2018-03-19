package com.cnfantasia.server.domainbase.propertyCard.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyCard.entity.PropertyCard;

/**
 * 描述:(物业代扣卡) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyCardBaseDao {
	/**
	 * 根据条件查询(物业代扣卡)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PropertyCard> selectPropertyCardByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(物业代扣卡)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PropertyCard> selectPropertyCardByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(物业代扣卡)信息
	 * @param id
	 * @return
	 */
	public PropertyCard selectPropertyCardBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业代扣卡)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPropertyCardCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(物业代扣卡)新增一条记录
	 * @param propertyCard
	 * @return
	 */
	public int insertPropertyCard(PropertyCard propertyCard);
	
	/**
	 * 批量新增(物业代扣卡)信息
	 * @param propertyCardList
	 * @return
	 */
	public int insertPropertyCardBatch(List<PropertyCard> propertyCardList);
	
	/**
	 * 更新(物业代扣卡)信息
	 * @param propertyCard
	 * @return
	 */
	public int updatePropertyCard(PropertyCard propertyCard);
	
	/**
	 * 批量更新(物业代扣卡)信息
	 * @param propertyCardList
	 * @return
	 */
	public int updatePropertyCardBatch(List<PropertyCard> propertyCardList);
	
	/**
	 * 根据序列号删除(物业代扣卡)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePropertyCardLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(物业代扣卡)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePropertyCardLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(物业代扣卡)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePropertyCard(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(物业代扣卡)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePropertyCardBatch(List<java.math.BigInteger> idList);
	
	
}
