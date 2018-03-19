package com.cnfantasia.server.domainbase.dredgeType.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeType.entity.DredgeType;

/**
 * 描述:(疏通类型) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDredgeTypeBaseDao {
	/**
	 * 根据条件查询(疏通类型)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<DredgeType> selectDredgeTypeByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(疏通类型)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<DredgeType> selectDredgeTypeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(疏通类型)信息
	 * @param id
	 * @return
	 */
	public DredgeType selectDredgeTypeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(疏通类型)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectDredgeTypeCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(疏通类型)新增一条记录
	 * @param dredgeType
	 * @return
	 */
	public int insertDredgeType(DredgeType dredgeType);
	
	/**
	 * 批量新增(疏通类型)信息
	 * @param dredgeTypeList
	 * @return
	 */
	public int insertDredgeTypeBatch(List<DredgeType> dredgeTypeList);
	
	/**
	 * 更新(疏通类型)信息
	 * @param dredgeType
	 * @return
	 */
	public int updateDredgeType(DredgeType dredgeType);
	
	/**
	 * 批量更新(疏通类型)信息
	 * @param dredgeTypeList
	 * @return
	 */
	public int updateDredgeTypeBatch(List<DredgeType> dredgeTypeList);
	
	/**
	 * 根据序列号删除(疏通类型)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteDredgeTypeLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(疏通类型)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteDredgeTypeLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(疏通类型)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteDredgeType(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(疏通类型)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteDredgeTypeBatch(List<java.math.BigInteger> idList);
	
	
}
