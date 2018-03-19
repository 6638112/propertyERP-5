package com.cnfantasia.server.domainbase.propertyRepairTypeBase.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.propertyRepairTypeBase.entity.PropertyRepairTypeBase;

/**
 * 描述:(物业报修类型(解放区预定义的)) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyRepairTypeBaseBaseService {
	
	/**
	 * 根据条件查询(物业报修类型(解放区预定义的))信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyRepairTypeBase> getPropertyRepairTypeBaseByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(物业报修类型(解放区预定义的))信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyRepairTypeBase> getPropertyRepairTypeBaseByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(物业报修类型(解放区预定义的))信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyRepairTypeBase> getPropertyRepairTypeBaseByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(物业报修类型(解放区预定义的))信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyRepairTypeBase> getPropertyRepairTypeBaseByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(物业报修类型(解放区预定义的))信息
	 * @param id
	 * @return
	 */
	public PropertyRepairTypeBase getPropertyRepairTypeBaseBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业报修类型(解放区预定义的))记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyRepairTypeBaseCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(物业报修类型(解放区预定义的))记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyRepairTypeBaseCountDim(Map<String,Object> paramMap);
	/**
	 * 往(物业报修类型(解放区预定义的))新增一条记录
	 * @param propertyRepairTypeBase
	 * @return
	 */
	public int insertPropertyRepairTypeBase(PropertyRepairTypeBase propertyRepairTypeBase);
	/**
	 * 批量新增(物业报修类型(解放区预定义的))
	 * @param propertyRepairTypeBaseList
	 * @return
	 */
	public int insertPropertyRepairTypeBaseBatch(List<PropertyRepairTypeBase> propertyRepairTypeBaseList);
	/**
	 * 更新(物业报修类型(解放区预定义的))信息
	 * @param propertyRepairTypeBase
	 * @return
	 */
	public int updatePropertyRepairTypeBase(PropertyRepairTypeBase propertyRepairTypeBase);
	/**
	 * 批量更新(物业报修类型(解放区预定义的))信息
	 * @param propertyRepairTypeBaseList
	 * @return
	 */
	public int updatePropertyRepairTypeBaseBatch(List<PropertyRepairTypeBase> propertyRepairTypeBaseList);
	/**
	 * 根据序列号删除(物业报修类型(解放区预定义的))信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePropertyRepairTypeBaseLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(物业报修类型(解放区预定义的))信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePropertyRepairTypeBaseLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(物业报修类型(解放区预定义的))信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePropertyRepairTypeBase(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(物业报修类型(解放区预定义的))信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePropertyRepairTypeBaseBatch(List<java.math.BigInteger> idList);
	
}
