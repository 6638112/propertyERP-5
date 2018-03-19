package com.cnfantasia.server.msbase.omsPermiFunction.dao;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.msbase.omsPermiFunction.entity.OmsPermiFunction;

/**
 * 描述:(OMS功能表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IOmsPermiFunctionBaseDao {
	/**
	 * 根据条件查询(OMS功能表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<OmsPermiFunction> selectOmsPermiFunctionByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(OMS功能表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<OmsPermiFunction> selectOmsPermiFunctionByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(OMS功能表)信息
	 * @param id
	 * @return
	 */
	public OmsPermiFunction selectOmsPermiFunctionBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(OMS功能表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectOmsPermiFunctionCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(OMS功能表)新增一条记录
	 * @param omsPermiFunction
	 * @return
	 */
	public int insertOmsPermiFunction(OmsPermiFunction omsPermiFunction);
	
	/**
	 * 批量新增(OMS功能表)信息
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
	 * 根据Id 批量删除(OMS功能表)信息_逻辑删除
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
