package com.cnfantasia.server.domainbase.pointSourceRecord.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.pointSourceRecord.dao.IPointSourceRecordBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.pointSourceRecord.entity.PointSourceRecord;

/**
 * 描述:(积分来源记录表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PointSourceRecordBaseService implements IPointSourceRecordBaseService{
	
	private IPointSourceRecordBaseDao pointSourceRecordBaseDao;
	public void setPointSourceRecordBaseDao(IPointSourceRecordBaseDao pointSourceRecordBaseDao) {
		this.pointSourceRecordBaseDao = pointSourceRecordBaseDao;
	}
	/**
	 * 根据条件查询(积分来源记录表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PointSourceRecord> getPointSourceRecordByCondition(Map<String,Object> paramMap){
		return pointSourceRecordBaseDao.selectPointSourceRecordByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(积分来源记录表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PointSourceRecord> getPointSourceRecordByConditionDim(Map<String,Object> paramMap){
		return pointSourceRecordBaseDao.selectPointSourceRecordByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(积分来源记录表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PointSourceRecord> getPointSourceRecordByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return pointSourceRecordBaseDao.selectPointSourceRecordByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(积分来源记录表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PointSourceRecord> getPointSourceRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return pointSourceRecordBaseDao.selectPointSourceRecordByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(积分来源记录表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PointSourceRecord getPointSourceRecordBySeqId(java.math.BigInteger id){
		return pointSourceRecordBaseDao.selectPointSourceRecordBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(积分来源记录表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPointSourceRecordCount(Map<String,Object> paramMap){
		return pointSourceRecordBaseDao.selectPointSourceRecordCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(积分来源记录表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPointSourceRecordCountDim(Map<String,Object> paramMap){
		return pointSourceRecordBaseDao.selectPointSourceRecordCount(paramMap,true);
	}
	/**
	 * 往(积分来源记录表)新增一条记录
	 * @param pointSourceRecord
	 * @return
	 */
	@Override
	public int insertPointSourceRecord(PointSourceRecord pointSourceRecord){
		return pointSourceRecordBaseDao.insertPointSourceRecord(pointSourceRecord);
	}
	/**
	 * 批量新增(积分来源记录表)
	 * @param pointSourceRecordList
	 * @return
	 */
	@Override
	public int insertPointSourceRecordBatch(List<PointSourceRecord> pointSourceRecordList){
		return pointSourceRecordBaseDao.insertPointSourceRecordBatch(pointSourceRecordList);
	}
	/**
	 * 更新(积分来源记录表)信息
	 * @param pointSourceRecord
	 * @return
	 */
	@Override
	public int updatePointSourceRecord(PointSourceRecord pointSourceRecord){
		return pointSourceRecordBaseDao.updatePointSourceRecord(pointSourceRecord);
	}
	/**
	 * 批量更新(积分来源记录表)信息
	 * @param pointSourceRecordList
	 * @return
	 */
	@Override
	public int updatePointSourceRecordBatch(List<PointSourceRecord> pointSourceRecordList){
		return pointSourceRecordBaseDao.updatePointSourceRecordBatch(pointSourceRecordList);
	}
	/**
	 * 根据序列号删除(积分来源记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePointSourceRecordLogic(java.math.BigInteger id){
		return pointSourceRecordBaseDao.deletePointSourceRecordLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(积分来源记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePointSourceRecordLogicBatch(List<java.math.BigInteger> idList){
		return pointSourceRecordBaseDao.deletePointSourceRecordLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(积分来源记录表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePointSourceRecord(java.math.BigInteger id){
//		return pointSourceRecordBaseDao.deletePointSourceRecord(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(积分来源记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePointSourceRecordBatch(List<java.math.BigInteger> idList){
//		return pointSourceRecordBaseDao.deletePointSourceRecordBatch(idList);
//	}
	
}
