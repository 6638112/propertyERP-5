package com.cnfantasia.server.domainbase.dredgeBillFollowRecord.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.dredgeBillFollowRecord.dao.IDredgeBillFollowRecordBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeBillFollowRecord.entity.DredgeBillFollowRecord;

/**
 * 描述:(维修订单跟进记录) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class DredgeBillFollowRecordBaseService implements IDredgeBillFollowRecordBaseService{
	
	private IDredgeBillFollowRecordBaseDao dredgeBillFollowRecordBaseDao;
	public void setDredgeBillFollowRecordBaseDao(IDredgeBillFollowRecordBaseDao dredgeBillFollowRecordBaseDao) {
		this.dredgeBillFollowRecordBaseDao = dredgeBillFollowRecordBaseDao;
	}
	/**
	 * 根据条件查询(维修订单跟进记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeBillFollowRecord> getDredgeBillFollowRecordByCondition(Map<String,Object> paramMap){
		return dredgeBillFollowRecordBaseDao.selectDredgeBillFollowRecordByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(维修订单跟进记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeBillFollowRecord> getDredgeBillFollowRecordByConditionDim(Map<String,Object> paramMap){
		return dredgeBillFollowRecordBaseDao.selectDredgeBillFollowRecordByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(维修订单跟进记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeBillFollowRecord> getDredgeBillFollowRecordByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeBillFollowRecordBaseDao.selectDredgeBillFollowRecordByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(维修订单跟进记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeBillFollowRecord> getDredgeBillFollowRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeBillFollowRecordBaseDao.selectDredgeBillFollowRecordByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(维修订单跟进记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeBillFollowRecord getDredgeBillFollowRecordBySeqId(java.math.BigInteger id){
		return dredgeBillFollowRecordBaseDao.selectDredgeBillFollowRecordBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(维修订单跟进记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeBillFollowRecordCount(Map<String,Object> paramMap){
		return dredgeBillFollowRecordBaseDao.selectDredgeBillFollowRecordCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(维修订单跟进记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeBillFollowRecordCountDim(Map<String,Object> paramMap){
		return dredgeBillFollowRecordBaseDao.selectDredgeBillFollowRecordCount(paramMap,true);
	}
	/**
	 * 往(维修订单跟进记录)新增一条记录
	 * @param dredgeBillFollowRecord
	 * @return
	 */
	@Override
	public int insertDredgeBillFollowRecord(DredgeBillFollowRecord dredgeBillFollowRecord){
		return dredgeBillFollowRecordBaseDao.insertDredgeBillFollowRecord(dredgeBillFollowRecord);
	}
	/**
	 * 批量新增(维修订单跟进记录)
	 * @param dredgeBillFollowRecordList
	 * @return
	 */
	@Override
	public int insertDredgeBillFollowRecordBatch(List<DredgeBillFollowRecord> dredgeBillFollowRecordList){
		return dredgeBillFollowRecordBaseDao.insertDredgeBillFollowRecordBatch(dredgeBillFollowRecordList);
	}
	/**
	 * 更新(维修订单跟进记录)信息
	 * @param dredgeBillFollowRecord
	 * @return
	 */
	@Override
	public int updateDredgeBillFollowRecord(DredgeBillFollowRecord dredgeBillFollowRecord){
		return dredgeBillFollowRecordBaseDao.updateDredgeBillFollowRecord(dredgeBillFollowRecord);
	}
	/**
	 * 批量更新(维修订单跟进记录)信息
	 * @param dredgeBillFollowRecordList
	 * @return
	 */
	@Override
	public int updateDredgeBillFollowRecordBatch(List<DredgeBillFollowRecord> dredgeBillFollowRecordList){
		return dredgeBillFollowRecordBaseDao.updateDredgeBillFollowRecordBatch(dredgeBillFollowRecordList);
	}
	/**
	 * 根据序列号删除(维修订单跟进记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteDredgeBillFollowRecordLogic(java.math.BigInteger id){
		return dredgeBillFollowRecordBaseDao.deleteDredgeBillFollowRecordLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(维修订单跟进记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteDredgeBillFollowRecordLogicBatch(List<java.math.BigInteger> idList){
		return dredgeBillFollowRecordBaseDao.deleteDredgeBillFollowRecordLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(维修订单跟进记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeBillFollowRecord(java.math.BigInteger id){
//		return dredgeBillFollowRecordBaseDao.deleteDredgeBillFollowRecord(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(维修订单跟进记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeBillFollowRecordBatch(List<java.math.BigInteger> idList){
//		return dredgeBillFollowRecordBaseDao.deleteDredgeBillFollowRecordBatch(idList);
//	}
	
}
