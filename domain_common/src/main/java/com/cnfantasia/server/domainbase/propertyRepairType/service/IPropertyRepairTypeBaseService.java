package com.cnfantasia.server.domainbase.propertyRepairType.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.propertyRepairType.entity.PropertyRepairType;

/**
 * 描述:(物业报修类型) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyRepairTypeBaseService {
	
	/**
	 * 根据条件查询(物业报修类型)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyRepairType> getPropertyRepairTypeByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(物业报修类型)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyRepairType> getPropertyRepairTypeByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(物业报修类型)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyRepairType> getPropertyRepairTypeByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(物业报修类型)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyRepairType> getPropertyRepairTypeByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(物业报修类型)信息
	 * @param id
	 * @return
	 */
	public PropertyRepairType getPropertyRepairTypeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业报修类型)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyRepairTypeCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(物业报修类型)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyRepairTypeCountDim(Map<String,Object> paramMap);
	/**
	 * 往(物业报修类型)新增一条记录
	 * @param propertyRepairType
	 * @return
	 */
	public int insertPropertyRepairType(PropertyRepairType propertyRepairType);
	/**
	 * 批量新增(物业报修类型)
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
	 * 根据序列号批量删除(物业报修类型)信息_逻辑删除
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
