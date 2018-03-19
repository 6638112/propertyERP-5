package com.cnfantasia.server.domainbase.dredgeBillPushTime.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.dredgeBillPushTime.dao.IDredgeBillPushTimeBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeBillPushTime.entity.DredgeBillPushTime;

/**
 * 描述:(疏通工单推送时间记录表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class DredgeBillPushTimeBaseService implements IDredgeBillPushTimeBaseService{
	
	private IDredgeBillPushTimeBaseDao dredgeBillPushTimeBaseDao;
	public void setDredgeBillPushTimeBaseDao(IDredgeBillPushTimeBaseDao dredgeBillPushTimeBaseDao) {
		this.dredgeBillPushTimeBaseDao = dredgeBillPushTimeBaseDao;
	}
	/**
	 * 根据条件查询(疏通工单推送时间记录表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeBillPushTime> getDredgeBillPushTimeByCondition(Map<String,Object> paramMap){
		return dredgeBillPushTimeBaseDao.selectDredgeBillPushTimeByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(疏通工单推送时间记录表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeBillPushTime> getDredgeBillPushTimeByConditionDim(Map<String,Object> paramMap){
		return dredgeBillPushTimeBaseDao.selectDredgeBillPushTimeByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(疏通工单推送时间记录表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeBillPushTime> getDredgeBillPushTimeByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeBillPushTimeBaseDao.selectDredgeBillPushTimeByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(疏通工单推送时间记录表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeBillPushTime> getDredgeBillPushTimeByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeBillPushTimeBaseDao.selectDredgeBillPushTimeByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(疏通工单推送时间记录表)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeBillPushTime getDredgeBillPushTimeBySeqId(java.math.BigInteger id){
		return dredgeBillPushTimeBaseDao.selectDredgeBillPushTimeBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(疏通工单推送时间记录表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeBillPushTimeCount(Map<String,Object> paramMap){
		return dredgeBillPushTimeBaseDao.selectDredgeBillPushTimeCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(疏通工单推送时间记录表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeBillPushTimeCountDim(Map<String,Object> paramMap){
		return dredgeBillPushTimeBaseDao.selectDredgeBillPushTimeCount(paramMap,true);
	}
	/**
	 * 往(疏通工单推送时间记录表)新增一条记录
	 * @param dredgeBillPushTime
	 * @return
	 */
	@Override
	public int insertDredgeBillPushTime(DredgeBillPushTime dredgeBillPushTime){
		return dredgeBillPushTimeBaseDao.insertDredgeBillPushTime(dredgeBillPushTime);
	}
	/**
	 * 批量新增(疏通工单推送时间记录表)
	 * @param dredgeBillPushTimeList
	 * @return
	 */
	@Override
	public int insertDredgeBillPushTimeBatch(List<DredgeBillPushTime> dredgeBillPushTimeList){
		return dredgeBillPushTimeBaseDao.insertDredgeBillPushTimeBatch(dredgeBillPushTimeList);
	}
	/**
	 * 更新(疏通工单推送时间记录表)信息
	 * @param dredgeBillPushTime
	 * @return
	 */
	@Override
	public int updateDredgeBillPushTime(DredgeBillPushTime dredgeBillPushTime){
		return dredgeBillPushTimeBaseDao.updateDredgeBillPushTime(dredgeBillPushTime);
	}
	/**
	 * 批量更新(疏通工单推送时间记录表)信息
	 * @param dredgeBillPushTimeList
	 * @return
	 */
	@Override
	public int updateDredgeBillPushTimeBatch(List<DredgeBillPushTime> dredgeBillPushTimeList){
		return dredgeBillPushTimeBaseDao.updateDredgeBillPushTimeBatch(dredgeBillPushTimeList);
	}
	/**
	 * 根据序列号删除(疏通工单推送时间记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteDredgeBillPushTimeLogic(java.math.BigInteger id){
		return dredgeBillPushTimeBaseDao.deleteDredgeBillPushTimeLogic(id);
	}
	 */
	/**
	 * 根据序列号批量删除(疏通工单推送时间记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteDredgeBillPushTimeLogicBatch(List<java.math.BigInteger> idList){
		return dredgeBillPushTimeBaseDao.deleteDredgeBillPushTimeLogicBatch(idList);
	}
	 */
//	/**
//	 * 根据序列号删除(疏通工单推送时间记录表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeBillPushTime(java.math.BigInteger id){
//		return dredgeBillPushTimeBaseDao.deleteDredgeBillPushTime(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(疏通工单推送时间记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeBillPushTimeBatch(List<java.math.BigInteger> idList){
//		return dredgeBillPushTimeBaseDao.deleteDredgeBillPushTimeBatch(idList);
//	}
	
}
