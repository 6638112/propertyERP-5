package com.cnfantasia.server.domainbase.propertyRepairType.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyRepairType.entity.PropertyRepairType;

/**
 * 描述:(物业报修类型) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyRepairTypeBaseDao {
	/**
	 * 根据条件查询(物业报修类型)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PropertyRepairType> selectPropertyRepairTypeByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(物业报修类型)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PropertyRepairType> selectPropertyRepairTypeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(物业报修类型)信息
	 * @param id
	 * @return
	 */
	public PropertyRepairType selectPropertyRepairTypeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业报修类型)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPropertyRepairTypeCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(物业报修类型)新增一条记录
	 * @param propertyRepairType
	 * @return
	 */
	public int insertPropertyRepairType(PropertyRepairType propertyRepairType);
	
	/**
	 * 批量新增(物业报修类型)信息
	 * @param propertyRepairTypeList
	 * @return
	 */
	public int insertPropertyRepairTypeBatch(List<PropertyRepairType> propertyRepairTypeList);
	
	/**
	 * 更新(物业报修类型)信息
	 * @param propertyRepairType
	 * @return
	 */
	public int updatePropertyRepairType(PropertyRepairType propertyRepairType);
	
	/**
	 * 批量更新(物业报修类型)信息
	 * @param propertyRepairTypeList
	 * @return
	 */
	public int updatePropertyRepairTypeBatch(List<PropertyRepairType> propertyRepairTypeList);
	
	/**
	 * 根据序列号删除(物业报修类型)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePropertyRepairTypeLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(物业报修类型)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePropertyRepairTypeLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(物业报修类型)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePropertyRepairType(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(物业报修类型)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePropertyRepairTypeBatch(List<java.math.BigInteger> idList);
	
	
}
