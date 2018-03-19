package com.cnfantasia.server.domainbase.permiFunction.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.permiFunction.entity.PermiFunction;

/**
 * 描述:(功能表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPermiFunctionBaseDao {
	/**
	 * 根据条件查询(功能表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PermiFunction> selectPermiFunctionByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(功能表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PermiFunction> selectPermiFunctionByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(功能表)信息
	 * @param id
	 * @return
	 */
	public PermiFunction selectPermiFunctionBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(功能表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPermiFunctionCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(功能表)新增一条记录
	 * @param permiFunction
	 * @return
	 */
	public int insertPermiFunction(PermiFunction permiFunction);
	
	/**
	 * 批量新增(功能表)信息
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
	 * 根据Id 批量删除(功能表)信息_逻辑删除
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
