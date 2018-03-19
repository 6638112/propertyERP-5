package com.cnfantasia.server.domainbase.dredgeWorkerHasDredgeType.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.dredgeWorkerHasDredgeType.entity.DredgeWorkerHasDredgeType;

/**
 * 描述:(疏通师傅支持的疏通类型) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDredgeWorkerHasDredgeTypeBaseService {
	
	/**
	 * 根据条件查询(疏通师傅支持的疏通类型)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<DredgeWorkerHasDredgeType> getDredgeWorkerHasDredgeTypeByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(疏通师傅支持的疏通类型)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<DredgeWorkerHasDredgeType> getDredgeWorkerHasDredgeTypeByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(疏通师傅支持的疏通类型)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DredgeWorkerHasDredgeType> getDredgeWorkerHasDredgeTypeByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(疏通师傅支持的疏通类型)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DredgeWorkerHasDredgeType> getDredgeWorkerHasDredgeTypeByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(疏通师傅支持的疏通类型)信息
	 * @param id
	 * @return
	 */
	public DredgeWorkerHasDredgeType getDredgeWorkerHasDredgeTypeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(疏通师傅支持的疏通类型)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getDredgeWorkerHasDredgeTypeCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(疏通师傅支持的疏通类型)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getDredgeWorkerHasDredgeTypeCountDim(Map<String,Object> paramMap);
	/**
	 * 往(疏通师傅支持的疏通类型)新增一条记录
	 * @param dredgeWorkerHasDredgeType
	 * @return
	 */
	public int insertDredgeWorkerHasDredgeType(DredgeWorkerHasDredgeType dredgeWorkerHasDredgeType);
	/**
	 * 批量新增(疏通师傅支持的疏通类型)
	 * @param dredgeWorkerHasDredgeTypeList
	 * @return
	 */
	public int insertDredgeWorkerHasDredgeTypeBatch(List<DredgeWorkerHasDredgeType> dredgeWorkerHasDredgeTypeList);
	/**
	 * 更新(疏通师傅支持的疏通类型)信息
	 * @param dredgeWorkerHasDredgeType
	 * @return
	 */
	public int updateDredgeWorkerHasDredgeType(DredgeWorkerHasDredgeType dredgeWorkerHasDredgeType);
	/**
	 * 批量更新(疏通师傅支持的疏通类型)信息
	 * @param dredgeWorkerHasDredgeTypeList
	 * @return
	 */
	public int updateDredgeWorkerHasDredgeTypeBatch(List<DredgeWorkerHasDredgeType> dredgeWorkerHasDredgeTypeList);
	/**
	 * 根据序列号删除(疏通师傅支持的疏通类型)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	public int deleteDredgeWorkerHasDredgeTypeLogic(java.math.BigInteger id);
	 */
	/**
	 * 根据序列号批量删除(疏通师傅支持的疏通类型)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	public int deleteDredgeWorkerHasDredgeTypeLogicBatch(List<java.math.BigInteger> idList);
	 */
//	/**
//	 * 根据序列号删除(疏通师傅支持的疏通类型)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteDredgeWorkerHasDredgeType(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(疏通师傅支持的疏通类型)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteDredgeWorkerHasDredgeTypeBatch(List<java.math.BigInteger> idList);
	
}
