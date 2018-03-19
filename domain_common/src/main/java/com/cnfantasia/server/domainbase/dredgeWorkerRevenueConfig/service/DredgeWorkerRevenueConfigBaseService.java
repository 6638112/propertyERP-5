package com.cnfantasia.server.domainbase.dredgeWorkerRevenueConfig.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.dredgeWorkerRevenueConfig.dao.IDredgeWorkerRevenueConfigBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeWorkerRevenueConfig.entity.DredgeWorkerRevenueConfig;

/**
 * 描述:(疏通师傅收益配置) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class DredgeWorkerRevenueConfigBaseService implements IDredgeWorkerRevenueConfigBaseService{
	
	private IDredgeWorkerRevenueConfigBaseDao dredgeWorkerRevenueConfigBaseDao;
	public void setDredgeWorkerRevenueConfigBaseDao(IDredgeWorkerRevenueConfigBaseDao dredgeWorkerRevenueConfigBaseDao) {
		this.dredgeWorkerRevenueConfigBaseDao = dredgeWorkerRevenueConfigBaseDao;
	}
	/**
	 * 根据条件查询(疏通师傅收益配置)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeWorkerRevenueConfig> getDredgeWorkerRevenueConfigByCondition(Map<String,Object> paramMap){
		return dredgeWorkerRevenueConfigBaseDao.selectDredgeWorkerRevenueConfigByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(疏通师傅收益配置)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeWorkerRevenueConfig> getDredgeWorkerRevenueConfigByConditionDim(Map<String,Object> paramMap){
		return dredgeWorkerRevenueConfigBaseDao.selectDredgeWorkerRevenueConfigByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(疏通师傅收益配置)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeWorkerRevenueConfig> getDredgeWorkerRevenueConfigByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeWorkerRevenueConfigBaseDao.selectDredgeWorkerRevenueConfigByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(疏通师傅收益配置)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeWorkerRevenueConfig> getDredgeWorkerRevenueConfigByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeWorkerRevenueConfigBaseDao.selectDredgeWorkerRevenueConfigByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(疏通师傅收益配置)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeWorkerRevenueConfig getDredgeWorkerRevenueConfigBySeqId(java.math.BigInteger id){
		return dredgeWorkerRevenueConfigBaseDao.selectDredgeWorkerRevenueConfigBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(疏通师傅收益配置)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeWorkerRevenueConfigCount(Map<String,Object> paramMap){
		return dredgeWorkerRevenueConfigBaseDao.selectDredgeWorkerRevenueConfigCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(疏通师傅收益配置)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeWorkerRevenueConfigCountDim(Map<String,Object> paramMap){
		return dredgeWorkerRevenueConfigBaseDao.selectDredgeWorkerRevenueConfigCount(paramMap,true);
	}
	/**
	 * 往(疏通师傅收益配置)新增一条记录
	 * @param dredgeWorkerRevenueConfig
	 * @return
	 */
	@Override
	public int insertDredgeWorkerRevenueConfig(DredgeWorkerRevenueConfig dredgeWorkerRevenueConfig){
		return dredgeWorkerRevenueConfigBaseDao.insertDredgeWorkerRevenueConfig(dredgeWorkerRevenueConfig);
	}
	/**
	 * 批量新增(疏通师傅收益配置)
	 * @param dredgeWorkerRevenueConfigList
	 * @return
	 */
	@Override
	public int insertDredgeWorkerRevenueConfigBatch(List<DredgeWorkerRevenueConfig> dredgeWorkerRevenueConfigList){
		return dredgeWorkerRevenueConfigBaseDao.insertDredgeWorkerRevenueConfigBatch(dredgeWorkerRevenueConfigList);
	}
	/**
	 * 更新(疏通师傅收益配置)信息
	 * @param dredgeWorkerRevenueConfig
	 * @return
	 */
	@Override
	public int updateDredgeWorkerRevenueConfig(DredgeWorkerRevenueConfig dredgeWorkerRevenueConfig){
		return dredgeWorkerRevenueConfigBaseDao.updateDredgeWorkerRevenueConfig(dredgeWorkerRevenueConfig);
	}
	/**
	 * 批量更新(疏通师傅收益配置)信息
	 * @param dredgeWorkerRevenueConfigList
	 * @return
	 */
	@Override
	public int updateDredgeWorkerRevenueConfigBatch(List<DredgeWorkerRevenueConfig> dredgeWorkerRevenueConfigList){
		return dredgeWorkerRevenueConfigBaseDao.updateDredgeWorkerRevenueConfigBatch(dredgeWorkerRevenueConfigList);
	}
	/**
	 * 根据序列号删除(疏通师傅收益配置)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteDredgeWorkerRevenueConfigLogic(java.math.BigInteger id){
		return dredgeWorkerRevenueConfigBaseDao.deleteDredgeWorkerRevenueConfigLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(疏通师傅收益配置)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteDredgeWorkerRevenueConfigLogicBatch(List<java.math.BigInteger> idList){
		return dredgeWorkerRevenueConfigBaseDao.deleteDredgeWorkerRevenueConfigLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(疏通师傅收益配置)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeWorkerRevenueConfig(java.math.BigInteger id){
//		return dredgeWorkerRevenueConfigBaseDao.deleteDredgeWorkerRevenueConfig(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(疏通师傅收益配置)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeWorkerRevenueConfigBatch(List<java.math.BigInteger> idList){
//		return dredgeWorkerRevenueConfigBaseDao.deleteDredgeWorkerRevenueConfigBatch(idList);
//	}
	
}
