package com.cnfantasia.server.domainbase.propertyFeeDetailTemp.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyFeeDetailTemp.entity.PropertyFeeDetailTemp;

/**
 * 描述:(物业收费项费用明细临时表（生成账单使用）) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyFeeDetailTempBaseDao {
	/**
	 * 根据条件查询(物业收费项费用明细临时表（生成账单使用）)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	List<PropertyFeeDetailTemp> selectPropertyFeeDetailTempByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(物业收费项费用明细临时表（生成账单使用）)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	List<PropertyFeeDetailTemp> selectPropertyFeeDetailTempByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(物业收费项费用明细临时表（生成账单使用）)信息
	 * @param id
	 * @return
	 */
	PropertyFeeDetailTemp selectPropertyFeeDetailTempBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业收费项费用明细临时表（生成账单使用）)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	int selectPropertyFeeDetailTempCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(物业收费项费用明细临时表（生成账单使用）)新增一条记录
	 * @param propertyFeeDetailTemp
	 * @return
	 */
	int insertPropertyFeeDetailTemp(PropertyFeeDetailTemp propertyFeeDetailTemp);
	
	/**
	 * 批量新增(物业收费项费用明细临时表（生成账单使用）)信息
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
	 * 根据Id 批量删除(物业收费项费用明细临时表（生成账单使用）)信息_逻辑删除
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
