package com.cnfantasia.server.domainbase.dredgeWorkerCancel.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.dredgeWorkerCancel.dao.IDredgeWorkerCancelBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeWorkerCancel.entity.DredgeWorkerCancel;

/**
 * 描述:(疏通师傅取消订单) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class DredgeWorkerCancelBaseService implements IDredgeWorkerCancelBaseService{
	
	private IDredgeWorkerCancelBaseDao dredgeWorkerCancelBaseDao;
	public void setDredgeWorkerCancelBaseDao(IDredgeWorkerCancelBaseDao dredgeWorkerCancelBaseDao) {
		this.dredgeWorkerCancelBaseDao = dredgeWorkerCancelBaseDao;
	}
	/**
	 * 根据条件查询(疏通师傅取消订单)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeWorkerCancel> getDredgeWorkerCancelByCondition(Map<String,Object> paramMap){
		return dredgeWorkerCancelBaseDao.selectDredgeWorkerCancelByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(疏通师傅取消订单)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeWorkerCancel> getDredgeWorkerCancelByConditionDim(Map<String,Object> paramMap){
		return dredgeWorkerCancelBaseDao.selectDredgeWorkerCancelByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(疏通师傅取消订单)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeWorkerCancel> getDredgeWorkerCancelByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeWorkerCancelBaseDao.selectDredgeWorkerCancelByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(疏通师傅取消订单)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeWorkerCancel> getDredgeWorkerCancelByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeWorkerCancelBaseDao.selectDredgeWorkerCancelByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(疏通师傅取消订单)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeWorkerCancel getDredgeWorkerCancelBySeqId(java.math.BigInteger id){
		return dredgeWorkerCancelBaseDao.selectDredgeWorkerCancelBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(疏通师傅取消订单)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeWorkerCancelCount(Map<String,Object> paramMap){
		return dredgeWorkerCancelBaseDao.selectDredgeWorkerCancelCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(疏通师傅取消订单)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeWorkerCancelCountDim(Map<String,Object> paramMap){
		return dredgeWorkerCancelBaseDao.selectDredgeWorkerCancelCount(paramMap,true);
	}
	/**
	 * 往(疏通师傅取消订单)新增一条记录
	 * @param dredgeWorkerCancel
	 * @return
	 */
	@Override
	public int insertDredgeWorkerCancel(DredgeWorkerCancel dredgeWorkerCancel){
		return dredgeWorkerCancelBaseDao.insertDredgeWorkerCancel(dredgeWorkerCancel);
	}
	/**
	 * 批量新增(疏通师傅取消订单)
	 * @param dredgeWorkerCancelList
	 * @return
	 */
	@Override
	public int insertDredgeWorkerCancelBatch(List<DredgeWorkerCancel> dredgeWorkerCancelList){
		return dredgeWorkerCancelBaseDao.insertDredgeWorkerCancelBatch(dredgeWorkerCancelList);
	}
	/**
	 * 更新(疏通师傅取消订单)信息
	 * @param dredgeWorkerCancel
	 * @return
	 */
	@Override
	public int updateDredgeWorkerCancel(DredgeWorkerCancel dredgeWorkerCancel){
		return dredgeWorkerCancelBaseDao.updateDredgeWorkerCancel(dredgeWorkerCancel);
	}
	/**
	 * 批量更新(疏通师傅取消订单)信息
	 * @param dredgeWorkerCancelList
	 * @return
	 */
	@Override
	public int updateDredgeWorkerCancelBatch(List<DredgeWorkerCancel> dredgeWorkerCancelList){
		return dredgeWorkerCancelBaseDao.updateDredgeWorkerCancelBatch(dredgeWorkerCancelList);
	}
	/**
	 * 根据序列号删除(疏通师傅取消订单)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteDredgeWorkerCancelLogic(java.math.BigInteger id){
		return dredgeWorkerCancelBaseDao.deleteDredgeWorkerCancelLogic(id);
	}
	 */
	/**
	 * 根据序列号批量删除(疏通师傅取消订单)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteDredgeWorkerCancelLogicBatch(List<java.math.BigInteger> idList){
		return dredgeWorkerCancelBaseDao.deleteDredgeWorkerCancelLogicBatch(idList);
	}
	 */
//	/**
//	 * 根据序列号删除(疏通师傅取消订单)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeWorkerCancel(java.math.BigInteger id){
//		return dredgeWorkerCancelBaseDao.deleteDredgeWorkerCancel(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(疏通师傅取消订单)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeWorkerCancelBatch(List<java.math.BigInteger> idList){
//		return dredgeWorkerCancelBaseDao.deleteDredgeWorkerCancelBatch(idList);
//	}
	
}
