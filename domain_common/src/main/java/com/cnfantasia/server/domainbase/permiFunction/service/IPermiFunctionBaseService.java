package com.cnfantasia.server.domainbase.permiFunction.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.permiFunction.entity.PermiFunction;

/**
 * 描述:(功能表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPermiFunctionBaseService {
	
	/**
	 * 根据条件查询(功能表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PermiFunction> getPermiFunctionByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(功能表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PermiFunction> getPermiFunctionByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(功能表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PermiFunction> getPermiFunctionByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(功能表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PermiFunction> getPermiFunctionByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(功能表)信息
	 * @param id
	 * @return
	 */
	public PermiFunction getPermiFunctionBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(功能表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPermiFunctionCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(功能表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPermiFunctionCountDim(Map<String,Object> paramMap);
	/**
	 * 往(功能表)新增一条记录
	 * @param permiFunction
	 * @return
	 */
	public int insertPermiFunction(PermiFunction permiFunction);
	/**
	 * 批量新增(功能表)
	 * @param permiFunctionList
	 * @return
	 */
	public int insertPermiFunctionBatch(List<PermiFunction> permiFunctionList);
	/**
	 * 更新(功能表)信息
	 * @param permiFunction
	 * @return
	 */
	public int updatePermiFunction(PermiFunction permiFunction);
	/**
	 * 批量更新(功能表)信息
	 * @param permiFunctionList
	 * @return
	 */
	public int updatePermiFunctionBatch(List<PermiFunction> permiFunctionList);
	/**
	 * 根据序列号删除(功能表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePermiFunctionLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(功能表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePermiFunctionLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(功能表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePermiFunction(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(功能表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePermiFunctionBatch(List<java.math.BigInteger> idList);
	
}
