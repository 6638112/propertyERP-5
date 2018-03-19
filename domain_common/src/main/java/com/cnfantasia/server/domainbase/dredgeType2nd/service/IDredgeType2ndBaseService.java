package com.cnfantasia.server.domainbase.dredgeType2nd.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.dredgeType2nd.entity.DredgeType2nd;

/**
 * 描述:(疏通二级类型) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDredgeType2ndBaseService {
	
	/**
	 * 根据条件查询(疏通二级类型)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<DredgeType2nd> getDredgeType2ndByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(疏通二级类型)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<DredgeType2nd> getDredgeType2ndByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(疏通二级类型)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DredgeType2nd> getDredgeType2ndByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(疏通二级类型)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DredgeType2nd> getDredgeType2ndByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(疏通二级类型)信息
	 * @param id
	 * @return
	 */
	public DredgeType2nd getDredgeType2ndBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(疏通二级类型)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getDredgeType2ndCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(疏通二级类型)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getDredgeType2ndCountDim(Map<String,Object> paramMap);
	/**
	 * 往(疏通二级类型)新增一条记录
	 * @param dredgeType2nd
	 * @return
	 */
	public int insertDredgeType2nd(DredgeType2nd dredgeType2nd);
	/**
	 * 批量新增(疏通二级类型)
	 * @param dredgeType2ndList
	 * @return
	 */
	public int insertDredgeType2ndBatch(List<DredgeType2nd> dredgeType2ndList);
	/**
	 * 更新(疏通二级类型)信息
	 * @param dredgeType2nd
	 * @return
	 */
	public int updateDredgeType2nd(DredgeType2nd dredgeType2nd);
	/**
	 * 批量更新(疏通二级类型)信息
	 * @param dredgeType2ndList
	 * @return
	 */
	public int updateDredgeType2ndBatch(List<DredgeType2nd> dredgeType2ndList);
	/**
	 * 根据序列号删除(疏通二级类型)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteDredgeType2ndLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(疏通二级类型)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteDredgeType2ndLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(疏通二级类型)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteDredgeType2nd(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(疏通二级类型)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteDredgeType2ndBatch(List<java.math.BigInteger> idList);
	
}
