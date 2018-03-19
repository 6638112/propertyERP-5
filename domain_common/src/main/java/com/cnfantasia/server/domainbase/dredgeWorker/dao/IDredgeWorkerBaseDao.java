package com.cnfantasia.server.domainbase.dredgeWorker.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeWorker.entity.DredgeWorker;

/**
 * 描述:(疏通师傅) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDredgeWorkerBaseDao {
	/**
	 * 根据条件查询(疏通师傅)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<DredgeWorker> selectDredgeWorkerByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(疏通师傅)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<DredgeWorker> selectDredgeWorkerByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(疏通师傅)信息
	 * @param id
	 * @return
	 */
	public DredgeWorker selectDredgeWorkerBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(疏通师傅)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectDredgeWorkerCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(疏通师傅)新增一条记录
	 * @param dredgeWorker
	 * @return
	 */
	public int insertDredgeWorker(DredgeWorker dredgeWorker);
	
	/**
	 * 批量新增(疏通师傅)信息
	 * @param dredgeWorkerList
	 * @return
	 */
	public int insertDredgeWorkerBatch(List<DredgeWorker> dredgeWorkerList);
	
	/**
	 * 更新(疏通师傅)信息
	 * @param dredgeWorker
	 * @return
	 */
	public int updateDredgeWorker(DredgeWorker dredgeWorker);
	
	/**
	 * 批量更新(疏通师傅)信息
	 * @param dredgeWorkerList
	 * @return
	 */
	public int updateDredgeWorkerBatch(List<DredgeWorker> dredgeWorkerList);
	
	/**
	 * 根据序列号删除(疏通师傅)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteDredgeWorkerLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(疏通师傅)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteDredgeWorkerLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(疏通师傅)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteDredgeWorker(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(疏通师傅)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteDredgeWorkerBatch(List<java.math.BigInteger> idList);
	
	
}
