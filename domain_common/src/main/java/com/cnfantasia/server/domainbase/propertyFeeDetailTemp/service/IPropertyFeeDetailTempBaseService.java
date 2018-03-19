package com.cnfantasia.server.domainbase.propertyFeeDetailTemp.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.propertyFeeDetailTemp.entity.PropertyFeeDetailTemp;

/**
 * 描述:(物业收费项费用明细临时表（生成账单使用）) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyFeeDetailTempBaseService {
	
	/**
	 * 根据条件查询(物业收费项费用明细临时表（生成账单使用）)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	List<PropertyFeeDetailTemp> getPropertyFeeDetailTempByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(物业收费项费用明细临时表（生成账单使用）)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	List<PropertyFeeDetailTemp> getPropertyFeeDetailTempByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(物业收费项费用明细临时表（生成账单使用）)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<PropertyFeeDetailTemp> getPropertyFeeDetailTempByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(物业收费项费用明细临时表（生成账单使用）)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<PropertyFeeDetailTemp> getPropertyFeeDetailTempByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(物业收费项费用明细临时表（生成账单使用）)信息
	 * @param id
	 * @return
	 */
	PropertyFeeDetailTemp getPropertyFeeDetailTempBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业收费项费用明细临时表（生成账单使用）)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	int getPropertyFeeDetailTempCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(物业收费项费用明细临时表（生成账单使用）)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	int getPropertyFeeDetailTempCountDim(Map<String, Object> paramMap);
	/**
	 * 往(物业收费项费用明细临时表（生成账单使用）)新增一条记录
	 * @param propertyFeeDetailTemp
	 * @return
	 */
	int insertPropertyFeeDetailTemp(PropertyFeeDetailTemp propertyFeeDetailTemp);
	/**
	 * 批量新增(物业收费项费用明细临时表（生成账单使用）)
	 * @param propertyFeeDetailTempList
	 * @return
	 */
	int insertPropertyFeeDetailTempBatch(List<PropertyFeeDetailTemp> propertyFeeDetailTempList);
	/**
	 * 更新(物业收费项费用明细临时表（生成账单使用）)信息
	 * @param propertyFeeDetailTemp
	 * @return
	 */
	int updatePropertyFeeDetailTemp(PropertyFeeDetailTemp propertyFeeDetailTemp);
	/**
	 * 批量更新(物业收费项费用明细临时表（生成账单使用）)信息
	 * @param propertyFeeDetailTempList
	 * @return
	 */
	int updatePropertyFeeDetailTempBatch(List<PropertyFeeDetailTemp> propertyFeeDetailTempList);
	/**
	 * 根据序列号删除(物业收费项费用明细临时表（生成账单使用）)信息_逻辑删除
	 * @param id
	 * @return
	 */

	int deletePropertyFeeDetailTempLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(物业收费项费用明细临时表（生成账单使用）)信息_逻辑删除
	 * @param idList
	 * @return
	 */

	int deletePropertyFeeDetailTempLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(物业收费项费用明细临时表（生成账单使用）)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePropertyFeeDetailTemp(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(物业收费项费用明细临时表（生成账单使用）)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePropertyFeeDetailTempBatch(List<java.math.BigInteger> idList);
	
}
