package com.cnfantasia.server.domainbase.dredgeWorkerRevenueConfig.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeWorkerRevenueConfig.entity.DredgeWorkerRevenueConfig;

/**
 * 描述:(疏通师傅收益配置) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDredgeWorkerRevenueConfigBaseDao {
	/**
	 * 根据条件查询(疏通师傅收益配置)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<DredgeWorkerRevenueConfig> selectDredgeWorkerRevenueConfigByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(疏通师傅收益配置)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<DredgeWorkerRevenueConfig> selectDredgeWorkerRevenueConfigByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(疏通师傅收益配置)信息
	 * @param id
	 * @return
	 */
	public DredgeWorkerRevenueConfig selectDredgeWorkerRevenueConfigBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(疏通师傅收益配置)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectDredgeWorkerRevenueConfigCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(疏通师傅收益配置)新增一条记录
	 * @param dredgeWorkerRevenueConfig
	 * @return
	 */
	public int insertDredgeWorkerRevenueConfig(DredgeWorkerRevenueConfig dredgeWorkerRevenueConfig);
	
	/**
	 * 批量新增(疏通师傅收益配置)信息
	 * @param dredgeWorkerRevenueConfigList
	 * @return
	 */
	public int insertDredgeWorkerRevenueConfigBatch(List<DredgeWorkerRevenueConfig> dredgeWorkerRevenueConfigList);
	
	/**
	 * 更新(疏通师傅收益配置)信息
	 * @param dredgeWorkerRevenueConfig
	 * @return
	 */
	public int updateDredgeWorkerRevenueConfig(DredgeWorkerRevenueConfig dredgeWorkerRevenueConfig);
	
	/**
	 * 批量更新(疏通师傅收益配置)信息
	 * @param dredgeWorkerRevenueConfigList
	 * @return
	 */
	public int updateDredgeWorkerRevenueConfigBatch(List<DredgeWorkerRevenueConfig> dredgeWorkerRevenueConfigList);
	
	/**
	 * 根据序列号删除(疏通师傅收益配置)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteDredgeWorkerRevenueConfigLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(疏通师傅收益配置)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteDredgeWorkerRevenueConfigLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(疏通师傅收益配置)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteDredgeWorkerRevenueConfig(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(疏通师傅收益配置)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteDredgeWorkerRevenueConfigBatch(List<java.math.BigInteger> idList);
	
	
}
