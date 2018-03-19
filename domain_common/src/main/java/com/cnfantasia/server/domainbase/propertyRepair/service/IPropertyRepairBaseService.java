package com.cnfantasia.server.domainbase.propertyRepair.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.propertyRepair.entity.PropertyRepair;

/**
 * 描述:(物业报修单) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyRepairBaseService {
	
	/**
	 * 根据条件查询(物业报修单)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyRepair> getPropertyRepairByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(物业报修单)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyRepair> getPropertyRepairByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(物业报修单)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyRepair> getPropertyRepairByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(物业报修单)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyRepair> getPropertyRepairByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(物业报修单)信息
	 * @param id
	 * @return
	 */
	public PropertyRepair getPropertyRepairBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业报修单)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyRepairCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(物业报修单)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyRepairCountDim(Map<String,Object> paramMap);
	/**
	 * 往(物业报修单)新增一条记录
	 * @param propertyRepair
	 * @return
	 */
	public int insertPropertyRepair(PropertyRepair propertyRepair);
	/**
	 * 批量新增(物业报修单)
	 * @param propertyRepairList
	 * @return
	 */
	public int insertPropertyRepairBatch(List<PropertyRepair> propertyRepairList);
	/**
	 * 更新(物业报修单)信息
	 * @param propertyRepair
	 * @return
	 */
	public int updatePropertyRepair(PropertyRepair propertyRepair);
	/**
	 * 批量更新(物业报修单)信息
	 * @param propertyRepairList
	 * @return
	 */
	public int updatePropertyRepairBatch(List<PropertyRepair> propertyRepairList);
	/**
	 * 根据序列号删除(物业报修单)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePropertyRepairLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(物业报修单)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePropertyRepairLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(物业报修单)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePropertyRepair(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(物业报修单)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePropertyRepairBatch(List<java.math.BigInteger> idList);
	
}
