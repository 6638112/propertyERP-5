package com.cnfantasia.server.domainbase.omsPermiFunction.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.omsPermiFunction.entity.OmsPermiFunction;

/**
 * 描述:(OMS功能表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IOmsPermiFunctionBaseService {
	
	/**
	 * 根据条件查询(OMS功能表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<OmsPermiFunction> getOmsPermiFunctionByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(OMS功能表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<OmsPermiFunction> getOmsPermiFunctionByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(OMS功能表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<OmsPermiFunction> getOmsPermiFunctionByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(OMS功能表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<OmsPermiFunction> getOmsPermiFunctionByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(OMS功能表)信息
	 * @param id
	 * @return
	 */
	public OmsPermiFunction getOmsPermiFunctionBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(OMS功能表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getOmsPermiFunctionCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(OMS功能表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getOmsPermiFunctionCountDim(Map<String,Object> paramMap);
	/**
	 * 往(OMS功能表)新增一条记录
	 * @param omsPermiFunction
	 * @return
	 */
	public int insertOmsPermiFunction(OmsPermiFunction omsPermiFunction);
	/**
	 * 批量新增(OMS功能表)
	 * @param omsPermiFunctionList
	 * @return
	 */
	public int insertOmsPermiFunctionBatch(List<OmsPermiFunction> omsPermiFunctionList);
	/**
	 * 更新(OMS功能表)信息
	 * @param omsPermiFunction
	 * @return
	 */
	public int updateOmsPermiFunction(OmsPermiFunction omsPermiFunction);
	/**
	 * 批量更新(OMS功能表)信息
	 * @param omsPermiFunctionList
	 * @return
	 */
	public int updateOmsPermiFunctionBatch(List<OmsPermiFunction> omsPermiFunctionList);
	/**
	 * 根据序列号删除(OMS功能表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteOmsPermiFunctionLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(OMS功能表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteOmsPermiFunctionLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(OMS功能表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteOmsPermiFunction(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(OMS功能表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteOmsPermiFunctionBatch(List<java.math.BigInteger> idList);
	
}
