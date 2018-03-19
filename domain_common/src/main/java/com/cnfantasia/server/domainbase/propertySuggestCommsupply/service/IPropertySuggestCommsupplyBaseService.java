package com.cnfantasia.server.domainbase.propertySuggestCommsupply.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.propertySuggestCommsupply.entity.PropertySuggestCommsupply;

/**
 * 描述:(物业推荐信息表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertySuggestCommsupplyBaseService {
	
	/**
	 * 根据条件查询(物业推荐信息表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertySuggestCommsupply> getPropertySuggestCommsupplyByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(物业推荐信息表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertySuggestCommsupply> getPropertySuggestCommsupplyByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(物业推荐信息表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertySuggestCommsupply> getPropertySuggestCommsupplyByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(物业推荐信息表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertySuggestCommsupply> getPropertySuggestCommsupplyByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(物业推荐信息表)信息
	 * @param id
	 * @return
	 */
	public PropertySuggestCommsupply getPropertySuggestCommsupplyBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业推荐信息表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertySuggestCommsupplyCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(物业推荐信息表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertySuggestCommsupplyCountDim(Map<String,Object> paramMap);
	/**
	 * 往(物业推荐信息表)新增一条记录
	 * @param propertySuggestCommsupply
	 * @return
	 */
	public int insertPropertySuggestCommsupply(PropertySuggestCommsupply propertySuggestCommsupply);
	/**
	 * 批量新增(物业推荐信息表)
	 * @param propertySuggestCommsupplyList
	 * @return
	 */
	public int insertPropertySuggestCommsupplyBatch(List<PropertySuggestCommsupply> propertySuggestCommsupplyList);
	/**
	 * 更新(物业推荐信息表)信息
	 * @param propertySuggestCommsupply
	 * @return
	 */
	public int updatePropertySuggestCommsupply(PropertySuggestCommsupply propertySuggestCommsupply);
	/**
	 * 批量更新(物业推荐信息表)信息
	 * @param propertySuggestCommsupplyList
	 * @return
	 */
	public int updatePropertySuggestCommsupplyBatch(List<PropertySuggestCommsupply> propertySuggestCommsupplyList);
	/**
	 * 根据序列号删除(物业推荐信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePropertySuggestCommsupplyLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(物业推荐信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePropertySuggestCommsupplyLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(物业推荐信息表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePropertySuggestCommsupply(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(物业推荐信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePropertySuggestCommsupplyBatch(List<java.math.BigInteger> idList);
	
}
