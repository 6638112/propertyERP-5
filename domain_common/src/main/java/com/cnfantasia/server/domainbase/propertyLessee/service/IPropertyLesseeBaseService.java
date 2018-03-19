package com.cnfantasia.server.domainbase.propertyLessee.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.propertyLessee.entity.PropertyLessee;

/**
 * 描述:(租户信息表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyLesseeBaseService {
	
	/**
	 * 根据条件查询(租户信息表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyLessee> getPropertyLesseeByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(租户信息表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyLessee> getPropertyLesseeByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(租户信息表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyLessee> getPropertyLesseeByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(租户信息表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyLessee> getPropertyLesseeByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(租户信息表)信息
	 * @param id
	 * @return
	 */
	public PropertyLessee getPropertyLesseeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(租户信息表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyLesseeCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(租户信息表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyLesseeCountDim(Map<String,Object> paramMap);
	/**
	 * 往(租户信息表)新增一条记录
	 * @param propertyLessee
	 * @return
	 */
	public int insertPropertyLessee(PropertyLessee propertyLessee);
	/**
	 * 批量新增(租户信息表)
	 * @param propertyLesseeList
	 * @return
	 */
	public int insertPropertyLesseeBatch(List<PropertyLessee> propertyLesseeList);
	/**
	 * 更新(租户信息表)信息
	 * @param propertyLessee
	 * @return
	 */
	public int updatePropertyLessee(PropertyLessee propertyLessee);
	/**
	 * 批量更新(租户信息表)信息
	 * @param propertyLesseeList
	 * @return
	 */
	public int updatePropertyLesseeBatch(List<PropertyLessee> propertyLesseeList);
	/**
	 * 根据序列号删除(租户信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePropertyLesseeLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(租户信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePropertyLesseeLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(租户信息表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePropertyLessee(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(租户信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePropertyLesseeBatch(List<java.math.BigInteger> idList);
	
}
