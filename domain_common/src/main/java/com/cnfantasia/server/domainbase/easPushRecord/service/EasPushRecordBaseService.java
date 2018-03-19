package com.cnfantasia.server.domainbase.easPushRecord.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.easPushRecord.dao.IEasPushRecordBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.easPushRecord.entity.EasPushRecord;

/**
 * 描述:(EAS接口推送记录) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EasPushRecordBaseService implements IEasPushRecordBaseService{
	
	private IEasPushRecordBaseDao easPushRecordBaseDao;
	public void setEasPushRecordBaseDao(IEasPushRecordBaseDao easPushRecordBaseDao) {
		this.easPushRecordBaseDao = easPushRecordBaseDao;
	}
	/**
	 * 根据条件查询(EAS接口推送记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EasPushRecord> getEasPushRecordByCondition(Map<String,Object> paramMap){
		return easPushRecordBaseDao.selectEasPushRecordByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(EAS接口推送记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EasPushRecord> getEasPushRecordByConditionDim(Map<String,Object> paramMap){
		return easPushRecordBaseDao.selectEasPushRecordByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(EAS接口推送记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EasPushRecord> getEasPushRecordByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return easPushRecordBaseDao.selectEasPushRecordByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(EAS接口推送记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EasPushRecord> getEasPushRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return easPushRecordBaseDao.selectEasPushRecordByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(EAS接口推送记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public EasPushRecord getEasPushRecordBySeqId(java.math.BigInteger id){
		return easPushRecordBaseDao.selectEasPushRecordBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(EAS接口推送记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEasPushRecordCount(Map<String,Object> paramMap){
		return easPushRecordBaseDao.selectEasPushRecordCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(EAS接口推送记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEasPushRecordCountDim(Map<String,Object> paramMap){
		return easPushRecordBaseDao.selectEasPushRecordCount(paramMap,true);
	}
	/**
	 * 往(EAS接口推送记录)新增一条记录
	 * @param easPushRecord
	 * @return
	 */
	@Override
	public int insertEasPushRecord(EasPushRecord easPushRecord){
		return easPushRecordBaseDao.insertEasPushRecord(easPushRecord);
	}
	/**
	 * 批量新增(EAS接口推送记录)
	 * @param easPushRecordList
	 * @return
	 */
	@Override
	public int insertEasPushRecordBatch(List<EasPushRecord> easPushRecordList){
		return easPushRecordBaseDao.insertEasPushRecordBatch(easPushRecordList);
	}
	/**
	 * 更新(EAS接口推送记录)信息
	 * @param easPushRecord
	 * @return
	 */
	@Override
	public int updateEasPushRecord(EasPushRecord easPushRecord){
		return easPushRecordBaseDao.updateEasPushRecord(easPushRecord);
	}
	/**
	 * 批量更新(EAS接口推送记录)信息
	 * @param easPushRecordList
	 * @return
	 */
	@Override
	public int updateEasPushRecordBatch(List<EasPushRecord> easPushRecordList){
		return easPushRecordBaseDao.updateEasPushRecordBatch(easPushRecordList);
	}
	/**
	 * 根据序列号删除(EAS接口推送记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEasPushRecordLogic(java.math.BigInteger id){
		return easPushRecordBaseDao.deleteEasPushRecordLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(EAS接口推送记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEasPushRecordLogicBatch(List<java.math.BigInteger> idList){
		return easPushRecordBaseDao.deleteEasPushRecordLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(EAS接口推送记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEasPushRecord(java.math.BigInteger id){
//		return easPushRecordBaseDao.deleteEasPushRecord(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(EAS接口推送记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEasPushRecordBatch(List<java.math.BigInteger> idList){
//		return easPushRecordBaseDao.deleteEasPushRecordBatch(idList);
//	}
	
}
