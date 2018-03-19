package com.cnfantasia.server.domainbase.propertyCard.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.propertyCard.entity.PropertyCard;

/**
 * 描述:(物业代扣卡) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyCardBaseService {
	
	/**
	 * 根据条件查询(物业代扣卡)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyCard> getPropertyCardByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(物业代扣卡)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyCard> getPropertyCardByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(物业代扣卡)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyCard> getPropertyCardByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(物业代扣卡)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyCard> getPropertyCardByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(物业代扣卡)信息
	 * @param id
	 * @return
	 */
	public PropertyCard getPropertyCardBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业代扣卡)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyCardCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(物业代扣卡)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyCardCountDim(Map<String,Object> paramMap);
	/**
	 * 往(物业代扣卡)新增一条记录
	 * @param propertyCard
	 * @return
	 */
	public int insertPropertyCard(PropertyCard propertyCard);
	/**
	 * 批量新增(物业代扣卡)
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
	 * 根据序列号批量删除(物业代扣卡)信息_逻辑删除
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
