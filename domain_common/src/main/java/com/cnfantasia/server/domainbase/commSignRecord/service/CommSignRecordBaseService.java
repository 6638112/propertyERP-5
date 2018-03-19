package com.cnfantasia.server.domainbase.commSignRecord.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.commSignRecord.dao.ICommSignRecordBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commSignRecord.entity.CommSignRecord;

/**
 * 描述:(签到记录) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommSignRecordBaseService implements ICommSignRecordBaseService{
	
	private ICommSignRecordBaseDao commSignRecordBaseDao;
	public void setCommSignRecordBaseDao(ICommSignRecordBaseDao commSignRecordBaseDao) {
		this.commSignRecordBaseDao = commSignRecordBaseDao;
	}
	/**
	 * 根据条件查询(签到记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommSignRecord> getCommSignRecordByCondition(Map<String,Object> paramMap){
		return commSignRecordBaseDao.selectCommSignRecordByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(签到记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommSignRecord> getCommSignRecordByConditionDim(Map<String,Object> paramMap){
		return commSignRecordBaseDao.selectCommSignRecordByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(签到记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommSignRecord> getCommSignRecordByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return commSignRecordBaseDao.selectCommSignRecordByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(签到记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommSignRecord> getCommSignRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return commSignRecordBaseDao.selectCommSignRecordByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(签到记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommSignRecord getCommSignRecordBySeqId(java.math.BigInteger id){
		return commSignRecordBaseDao.selectCommSignRecordBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(签到记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommSignRecordCount(Map<String,Object> paramMap){
		return commSignRecordBaseDao.selectCommSignRecordCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(签到记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommSignRecordCountDim(Map<String,Object> paramMap){
		return commSignRecordBaseDao.selectCommSignRecordCount(paramMap,true);
	}
	/**
	 * 往(签到记录)新增一条记录
	 * @param commSignRecord
	 * @return
	 */
	@Override
	public int insertCommSignRecord(CommSignRecord commSignRecord){
		return commSignRecordBaseDao.insertCommSignRecord(commSignRecord);
	}
	/**
	 * 批量新增(签到记录)
	 * @param commSignRecordList
	 * @return
	 */
	@Override
	public int insertCommSignRecordBatch(List<CommSignRecord> commSignRecordList){
		return commSignRecordBaseDao.insertCommSignRecordBatch(commSignRecordList);
	}
	/**
	 * 更新(签到记录)信息
	 * @param commSignRecord
	 * @return
	 */
	@Override
	public int updateCommSignRecord(CommSignRecord commSignRecord){
		return commSignRecordBaseDao.updateCommSignRecord(commSignRecord);
	}
	/**
	 * 批量更新(签到记录)信息
	 * @param commSignRecordList
	 * @return
	 */
	@Override
	public int updateCommSignRecordBatch(List<CommSignRecord> commSignRecordList){
		return commSignRecordBaseDao.updateCommSignRecordBatch(commSignRecordList);
	}
	/**
	 * 根据序列号删除(签到记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommSignRecordLogic(java.math.BigInteger id){
		return commSignRecordBaseDao.deleteCommSignRecordLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(签到记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommSignRecordLogicBatch(List<java.math.BigInteger> idList){
		return commSignRecordBaseDao.deleteCommSignRecordLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(签到记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommSignRecord(java.math.BigInteger id){
//		return commSignRecordBaseDao.deleteCommSignRecord(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(签到记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommSignRecordBatch(List<java.math.BigInteger> idList){
//		return commSignRecordBaseDao.deleteCommSignRecordBatch(idList);
//	}
	
}
