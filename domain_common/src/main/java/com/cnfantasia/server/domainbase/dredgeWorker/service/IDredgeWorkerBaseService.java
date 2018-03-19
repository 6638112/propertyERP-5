package com.cnfantasia.server.domainbase.dredgeWorker.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.dredgeWorker.entity.DredgeWorker;

/**
 * 描述:(疏通师傅) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDredgeWorkerBaseService {
	
	/**
	 * 根据条件查询(疏通师傅)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<DredgeWorker> getDredgeWorkerByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(疏通师傅)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<DredgeWorker> getDredgeWorkerByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(疏通师傅)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DredgeWorker> getDredgeWorkerByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(疏通师傅)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DredgeWorker> getDredgeWorkerByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(疏通师傅)信息
	 * @param id
	 * @return
	 */
	public DredgeWorker getDredgeWorkerBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(疏通师傅)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getDredgeWorkerCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(疏通师傅)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getDredgeWorkerCountDim(Map<String,Object> paramMap);
	/**
	 * 往(疏通师傅)新增一条记录
	 * @param dredgeWorker
	 * @return
	 */
	public int insertDredgeWorker(DredgeWorker dredgeWorker);
	/**
	 * 批量新增(疏通师傅)
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
	 * 根据序列号批量删除(疏通师傅)信息_逻辑删除
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
