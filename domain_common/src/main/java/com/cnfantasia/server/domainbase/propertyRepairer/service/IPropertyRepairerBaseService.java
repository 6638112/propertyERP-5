package com.cnfantasia.server.domainbase.propertyRepairer.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.propertyRepairer.entity.PropertyRepairer;

/**
 * 描述:(管理处维修工) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyRepairerBaseService {
	
	/**
	 * 根据条件查询(管理处维修工)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyRepairer> getPropertyRepairerByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(管理处维修工)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyRepairer> getPropertyRepairerByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(管理处维修工)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyRepairer> getPropertyRepairerByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(管理处维修工)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyRepairer> getPropertyRepairerByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(管理处维修工)信息
	 * @param id
	 * @return
	 */
	public PropertyRepairer getPropertyRepairerBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(管理处维修工)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyRepairerCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(管理处维修工)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyRepairerCountDim(Map<String,Object> paramMap);
	/**
	 * 往(管理处维修工)新增一条记录
	 * @param propertyRepairer
	 * @return
	 */
	public int insertPropertyRepairer(PropertyRepairer propertyRepairer);
	/**
	 * 批量新增(管理处维修工)
	 * @param propertyRepairerList
	 * @return
	 */
	public int insertPropertyRepairerBatch(List<PropertyRepairer> propertyRepairerList);
	/**
	 * 更新(管理处维修工)信息
	 * @param propertyRepairer
	 * @return
	 */
	public int updatePropertyRepairer(PropertyRepairer propertyRepairer);
	/**
	 * 批量更新(管理处维修工)信息
	 * @param propertyRepairerList
	 * @return
	 */
	public int updatePropertyRepairerBatch(List<PropertyRepairer> propertyRepairerList);
	/**
	 * 根据序列号删除(管理处维修工)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePropertyRepairerLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(管理处维修工)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePropertyRepairerLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(管理处维修工)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePropertyRepairer(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(管理处维修工)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePropertyRepairerBatch(List<java.math.BigInteger> idList);
	
}
