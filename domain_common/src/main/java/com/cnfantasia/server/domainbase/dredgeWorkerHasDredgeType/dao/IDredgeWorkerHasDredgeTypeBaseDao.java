package com.cnfantasia.server.domainbase.dredgeWorkerHasDredgeType.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeWorkerHasDredgeType.entity.DredgeWorkerHasDredgeType;

/**
 * 描述:(疏通师傅支持的疏通类型) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDredgeWorkerHasDredgeTypeBaseDao {
	/**
	 * 根据条件查询(疏通师傅支持的疏通类型)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<DredgeWorkerHasDredgeType> selectDredgeWorkerHasDredgeTypeByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(疏通师傅支持的疏通类型)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<DredgeWorkerHasDredgeType> selectDredgeWorkerHasDredgeTypeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(疏通师傅支持的疏通类型)信息
	 * @param id
	 * @return
	 */
	public DredgeWorkerHasDredgeType selectDredgeWorkerHasDredgeTypeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(疏通师傅支持的疏通类型)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectDredgeWorkerHasDredgeTypeCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(疏通师傅支持的疏通类型)新增一条记录
	 * @param dredgeWorkerHasDredgeType
	 * @return
	 */
	public int insertDredgeWorkerHasDredgeType(DredgeWorkerHasDredgeType dredgeWorkerHasDredgeType);
	
	/**
	 * 批量新增(疏通师傅支持的疏通类型)信息
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
	 * 根据Id 批量删除(疏通师傅支持的疏通类型)信息_逻辑删除
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
