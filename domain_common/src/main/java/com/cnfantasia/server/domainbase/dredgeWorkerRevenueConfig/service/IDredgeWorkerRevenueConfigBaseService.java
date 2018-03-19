package com.cnfantasia.server.domainbase.dredgeWorkerRevenueConfig.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.dredgeWorkerRevenueConfig.entity.DredgeWorkerRevenueConfig;

/**
 * 描述:(疏通师傅收益配置) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDredgeWorkerRevenueConfigBaseService {
	
	/**
	 * 根据条件查询(疏通师傅收益配置)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<DredgeWorkerRevenueConfig> getDredgeWorkerRevenueConfigByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(疏通师傅收益配置)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<DredgeWorkerRevenueConfig> getDredgeWorkerRevenueConfigByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(疏通师傅收益配置)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DredgeWorkerRevenueConfig> getDredgeWorkerRevenueConfigByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(疏通师傅收益配置)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DredgeWorkerRevenueConfig> getDredgeWorkerRevenueConfigByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(疏通师傅收益配置)信息
	 * @param id
	 * @return
	 */
	public DredgeWorkerRevenueConfig getDredgeWorkerRevenueConfigBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(疏通师傅收益配置)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getDredgeWorkerRevenueConfigCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(疏通师傅收益配置)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getDredgeWorkerRevenueConfigCountDim(Map<String,Object> paramMap);
	/**
	 * 往(疏通师傅收益配置)新增一条记录
	 * @param dredgeWorkerRevenueConfig
	 * @return
	 */
	public int insertDredgeWorkerRevenueConfig(DredgeWorkerRevenueConfig dredgeWorkerRevenueConfig);
	/**
	 * 批量新增(疏通师傅收益配置)
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
	 * 根据序列号批量删除(疏通师傅收益配置)信息_逻辑删除
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
