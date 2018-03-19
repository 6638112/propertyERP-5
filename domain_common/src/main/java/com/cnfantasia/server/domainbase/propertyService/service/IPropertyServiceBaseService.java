package com.cnfantasia.server.domainbase.propertyService.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.propertyService.entity.PropertyService;

/**
 * 描述:(物业服务信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyServiceBaseService {
	
	/**
	 * 根据条件查询(物业服务信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyService> getPropertyServiceByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(物业服务信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyService> getPropertyServiceByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(物业服务信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyService> getPropertyServiceByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(物业服务信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyService> getPropertyServiceByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(物业服务信息)信息
	 * @param id
	 * @return
	 */
	public PropertyService getPropertyServiceBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业服务信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyServiceCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(物业服务信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyServiceCountDim(Map<String,Object> paramMap);
	/**
	 * 往(物业服务信息)新增一条记录
	 * @param propertyService
	 * @return
	 */
	public int insertPropertyService(PropertyService propertyService);
	/**
	 * 批量新增(物业服务信息)
	 * @param propertyServiceList
	 * @return
	 */
	public int insertPropertyServiceBatch(List<PropertyService> propertyServiceList);
	/**
	 * 更新(物业服务信息)信息
	 * @param propertyService
	 * @return
	 */
	public int updatePropertyService(PropertyService propertyService);
	/**
	 * 批量更新(物业服务信息)信息
	 * @param propertyServiceList
	 * @return
	 */
	public int updatePropertyServiceBatch(List<PropertyService> propertyServiceList);
	/**
	 * 根据序列号删除(物业服务信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePropertyServiceLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(物业服务信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePropertyServiceLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(物业服务信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePropertyService(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(物业服务信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePropertyServiceBatch(List<java.math.BigInteger> idList);
	
}
