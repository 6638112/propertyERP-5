package com.cnfantasia.server.domainbase.commShareRecord.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.commShareRecord.dao.ICommShareRecordBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commShareRecord.entity.CommShareRecord;

/**
 * 描述:(分享记录表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommShareRecordBaseService implements ICommShareRecordBaseService{
	
	private ICommShareRecordBaseDao commShareRecordBaseDao;
	public void setCommShareRecordBaseDao(ICommShareRecordBaseDao commShareRecordBaseDao) {
		this.commShareRecordBaseDao = commShareRecordBaseDao;
	}
	/**
	 * 根据条件查询(分享记录表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommShareRecord> getCommShareRecordByCondition(Map<String,Object> paramMap){
		return commShareRecordBaseDao.selectCommShareRecordByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(分享记录表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommShareRecord> getCommShareRecordByConditionDim(Map<String,Object> paramMap){
		return commShareRecordBaseDao.selectCommShareRecordByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(分享记录表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommShareRecord> getCommShareRecordByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return commShareRecordBaseDao.selectCommShareRecordByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(分享记录表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommShareRecord> getCommShareRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return commShareRecordBaseDao.selectCommShareRecordByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(分享记录表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommShareRecord getCommShareRecordBySeqId(java.math.BigInteger id){
		return commShareRecordBaseDao.selectCommShareRecordBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(分享记录表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommShareRecordCount(Map<String,Object> paramMap){
		return commShareRecordBaseDao.selectCommShareRecordCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(分享记录表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommShareRecordCountDim(Map<String,Object> paramMap){
		return commShareRecordBaseDao.selectCommShareRecordCount(paramMap,true);
	}
	/**
	 * 往(分享记录表)新增一条记录
	 * @param commShareRecord
	 * @return
	 */
	@Override
	public int insertCommShareRecord(CommShareRecord commShareRecord){
		return commShareRecordBaseDao.insertCommShareRecord(commShareRecord);
	}
	/**
	 * 批量新增(分享记录表)
	 * @param commShareRecordList
	 * @return
	 */
	@Override
	public int insertCommShareRecordBatch(List<CommShareRecord> commShareRecordList){
		return commShareRecordBaseDao.insertCommShareRecordBatch(commShareRecordList);
	}
	/**
	 * 更新(分享记录表)信息
	 * @param commShareRecord
	 * @return
	 */
	@Override
	public int updateCommShareRecord(CommShareRecord commShareRecord){
		return commShareRecordBaseDao.updateCommShareRecord(commShareRecord);
	}
	/**
	 * 批量更新(分享记录表)信息
	 * @param commShareRecordList
	 * @return
	 */
	@Override
	public int updateCommShareRecordBatch(List<CommShareRecord> commShareRecordList){
		return commShareRecordBaseDao.updateCommShareRecordBatch(commShareRecordList);
	}
	/**
	 * 根据序列号删除(分享记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommShareRecordLogic(java.math.BigInteger id){
		return commShareRecordBaseDao.deleteCommShareRecordLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(分享记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommShareRecordLogicBatch(List<java.math.BigInteger> idList){
		return commShareRecordBaseDao.deleteCommShareRecordLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(分享记录表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommShareRecord(java.math.BigInteger id){
//		return commShareRecordBaseDao.deleteCommShareRecord(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(分享记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommShareRecordBatch(List<java.math.BigInteger> idList){
//		return commShareRecordBaseDao.deleteCommShareRecordBatch(idList);
//	}
	
}
