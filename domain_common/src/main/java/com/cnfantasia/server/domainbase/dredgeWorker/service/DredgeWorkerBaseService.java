package com.cnfantasia.server.domainbase.dredgeWorker.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.dredgeWorker.dao.IDredgeWorkerBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeWorker.entity.DredgeWorker;

/**
 * 描述:(疏通师傅) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class DredgeWorkerBaseService implements IDredgeWorkerBaseService{
	
	private IDredgeWorkerBaseDao dredgeWorkerBaseDao;
	public void setDredgeWorkerBaseDao(IDredgeWorkerBaseDao dredgeWorkerBaseDao) {
		this.dredgeWorkerBaseDao = dredgeWorkerBaseDao;
	}
	/**
	 * 根据条件查询(疏通师傅)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeWorker> getDredgeWorkerByCondition(Map<String,Object> paramMap){
		return dredgeWorkerBaseDao.selectDredgeWorkerByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(疏通师傅)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeWorker> getDredgeWorkerByConditionDim(Map<String,Object> paramMap){
		return dredgeWorkerBaseDao.selectDredgeWorkerByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(疏通师傅)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeWorker> getDredgeWorkerByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeWorkerBaseDao.selectDredgeWorkerByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(疏通师傅)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeWorker> getDredgeWorkerByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeWorkerBaseDao.selectDredgeWorkerByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(疏通师傅)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeWorker getDredgeWorkerBySeqId(java.math.BigInteger id){
		return dredgeWorkerBaseDao.selectDredgeWorkerBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(疏通师傅)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeWorkerCount(Map<String,Object> paramMap){
		return dredgeWorkerBaseDao.selectDredgeWorkerCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(疏通师傅)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeWorkerCountDim(Map<String,Object> paramMap){
		return dredgeWorkerBaseDao.selectDredgeWorkerCount(paramMap,true);
	}
	/**
	 * 往(疏通师傅)新增一条记录
	 * @param dredgeWorker
	 * @return
	 */
	@Override
	public int insertDredgeWorker(DredgeWorker dredgeWorker){
		return dredgeWorkerBaseDao.insertDredgeWorker(dredgeWorker);
	}
	/**
	 * 批量新增(疏通师傅)
	 * @param dredgeWorkerList
	 * @return
	 */
	@Override
	public int insertDredgeWorkerBatch(List<DredgeWorker> dredgeWorkerList){
		return dredgeWorkerBaseDao.insertDredgeWorkerBatch(dredgeWorkerList);
	}
	/**
	 * 更新(疏通师傅)信息
	 * @param dredgeWorker
	 * @return
	 */
	@Override
	public int updateDredgeWorker(DredgeWorker dredgeWorker){
		return dredgeWorkerBaseDao.updateDredgeWorker(dredgeWorker);
	}
	/**
	 * 批量更新(疏通师傅)信息
	 * @param dredgeWorkerList
	 * @return
	 */
	@Override
	public int updateDredgeWorkerBatch(List<DredgeWorker> dredgeWorkerList){
		return dredgeWorkerBaseDao.updateDredgeWorkerBatch(dredgeWorkerList);
	}
	/**
	 * 根据序列号删除(疏通师傅)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteDredgeWorkerLogic(java.math.BigInteger id){
		return dredgeWorkerBaseDao.deleteDredgeWorkerLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(疏通师傅)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteDredgeWorkerLogicBatch(List<java.math.BigInteger> idList){
		return dredgeWorkerBaseDao.deleteDredgeWorkerLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(疏通师傅)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeWorker(java.math.BigInteger id){
//		return dredgeWorkerBaseDao.deleteDredgeWorker(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(疏通师傅)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeWorkerBatch(List<java.math.BigInteger> idList){
//		return dredgeWorkerBaseDao.deleteDredgeWorkerBatch(idList);
//	}
	
}
