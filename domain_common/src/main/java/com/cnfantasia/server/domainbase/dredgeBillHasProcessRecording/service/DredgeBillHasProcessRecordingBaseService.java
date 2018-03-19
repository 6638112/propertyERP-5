package com.cnfantasia.server.domainbase.dredgeBillHasProcessRecording.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.dredgeBillHasProcessRecording.dao.IDredgeBillHasProcessRecordingBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeBillHasProcessRecording.entity.DredgeBillHasProcessRecording;

/**
 * 描述:(上门服务单流程记录) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class DredgeBillHasProcessRecordingBaseService implements IDredgeBillHasProcessRecordingBaseService{
	
	private IDredgeBillHasProcessRecordingBaseDao dredgeBillHasProcessRecordingBaseDao;
	public void setDredgeBillHasProcessRecordingBaseDao(IDredgeBillHasProcessRecordingBaseDao dredgeBillHasProcessRecordingBaseDao) {
		this.dredgeBillHasProcessRecordingBaseDao = dredgeBillHasProcessRecordingBaseDao;
	}
	/**
	 * 根据条件查询(上门服务单流程记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeBillHasProcessRecording> getDredgeBillHasProcessRecordingByCondition(Map<String,Object> paramMap){
		return dredgeBillHasProcessRecordingBaseDao.selectDredgeBillHasProcessRecordingByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(上门服务单流程记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeBillHasProcessRecording> getDredgeBillHasProcessRecordingByConditionDim(Map<String,Object> paramMap){
		return dredgeBillHasProcessRecordingBaseDao.selectDredgeBillHasProcessRecordingByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(上门服务单流程记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeBillHasProcessRecording> getDredgeBillHasProcessRecordingByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeBillHasProcessRecordingBaseDao.selectDredgeBillHasProcessRecordingByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(上门服务单流程记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeBillHasProcessRecording> getDredgeBillHasProcessRecordingByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeBillHasProcessRecordingBaseDao.selectDredgeBillHasProcessRecordingByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(上门服务单流程记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeBillHasProcessRecording getDredgeBillHasProcessRecordingBySeqId(java.math.BigInteger id){
		return dredgeBillHasProcessRecordingBaseDao.selectDredgeBillHasProcessRecordingBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(上门服务单流程记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeBillHasProcessRecordingCount(Map<String,Object> paramMap){
		return dredgeBillHasProcessRecordingBaseDao.selectDredgeBillHasProcessRecordingCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(上门服务单流程记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeBillHasProcessRecordingCountDim(Map<String,Object> paramMap){
		return dredgeBillHasProcessRecordingBaseDao.selectDredgeBillHasProcessRecordingCount(paramMap,true);
	}
	/**
	 * 往(上门服务单流程记录)新增一条记录
	 * @param dredgeBillHasProcessRecording
	 * @return
	 */
	@Override
	public int insertDredgeBillHasProcessRecording(DredgeBillHasProcessRecording dredgeBillHasProcessRecording){
		return dredgeBillHasProcessRecordingBaseDao.insertDredgeBillHasProcessRecording(dredgeBillHasProcessRecording);
	}
	/**
	 * 批量新增(上门服务单流程记录)
	 * @param dredgeBillHasProcessRecordingList
	 * @return
	 */
	@Override
	public int insertDredgeBillHasProcessRecordingBatch(List<DredgeBillHasProcessRecording> dredgeBillHasProcessRecordingList){
		return dredgeBillHasProcessRecordingBaseDao.insertDredgeBillHasProcessRecordingBatch(dredgeBillHasProcessRecordingList);
	}
	/**
	 * 更新(上门服务单流程记录)信息
	 * @param dredgeBillHasProcessRecording
	 * @return
	 */
	@Override
	public int updateDredgeBillHasProcessRecording(DredgeBillHasProcessRecording dredgeBillHasProcessRecording){
		return dredgeBillHasProcessRecordingBaseDao.updateDredgeBillHasProcessRecording(dredgeBillHasProcessRecording);
	}
	/**
	 * 批量更新(上门服务单流程记录)信息
	 * @param dredgeBillHasProcessRecordingList
	 * @return
	 */
	@Override
	public int updateDredgeBillHasProcessRecordingBatch(List<DredgeBillHasProcessRecording> dredgeBillHasProcessRecordingList){
		return dredgeBillHasProcessRecordingBaseDao.updateDredgeBillHasProcessRecordingBatch(dredgeBillHasProcessRecordingList);
	}
	/**
	 * 根据序列号删除(上门服务单流程记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteDredgeBillHasProcessRecordingLogic(java.math.BigInteger id){
		return dredgeBillHasProcessRecordingBaseDao.deleteDredgeBillHasProcessRecordingLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(上门服务单流程记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteDredgeBillHasProcessRecordingLogicBatch(List<java.math.BigInteger> idList){
		return dredgeBillHasProcessRecordingBaseDao.deleteDredgeBillHasProcessRecordingLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(上门服务单流程记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeBillHasProcessRecording(java.math.BigInteger id){
//		return dredgeBillHasProcessRecordingBaseDao.deleteDredgeBillHasProcessRecording(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(上门服务单流程记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeBillHasProcessRecordingBatch(List<java.math.BigInteger> idList){
//		return dredgeBillHasProcessRecordingBaseDao.deleteDredgeBillHasProcessRecordingBatch(idList);
//	}
	
}
