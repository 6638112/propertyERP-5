package com.cnfantasia.server.domainbase.revenueTaskRecord.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.revenueTaskRecord.dao.IRevenueTaskRecordBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.revenueTaskRecord.entity.RevenueTaskRecord;

/**
 * 描述:(定时同步收益额记录表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class RevenueTaskRecordBaseService implements IRevenueTaskRecordBaseService{
	
	private IRevenueTaskRecordBaseDao revenueTaskRecordBaseDao;
	public void setRevenueTaskRecordBaseDao(IRevenueTaskRecordBaseDao revenueTaskRecordBaseDao) {
		this.revenueTaskRecordBaseDao = revenueTaskRecordBaseDao;
	}
	/**
	 * 根据条件查询(定时同步收益额记录表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RevenueTaskRecord> getRevenueTaskRecordByCondition(Map<String,Object> paramMap){
		return revenueTaskRecordBaseDao.selectRevenueTaskRecordByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(定时同步收益额记录表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RevenueTaskRecord> getRevenueTaskRecordByConditionDim(Map<String,Object> paramMap){
		return revenueTaskRecordBaseDao.selectRevenueTaskRecordByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(定时同步收益额记录表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RevenueTaskRecord> getRevenueTaskRecordByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return revenueTaskRecordBaseDao.selectRevenueTaskRecordByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(定时同步收益额记录表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RevenueTaskRecord> getRevenueTaskRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return revenueTaskRecordBaseDao.selectRevenueTaskRecordByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(定时同步收益额记录表)信息
	 * @param id
	 * @return
	 */
	@Override
	public RevenueTaskRecord getRevenueTaskRecordBySeqId(java.math.BigInteger id){
		return revenueTaskRecordBaseDao.selectRevenueTaskRecordBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(定时同步收益额记录表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRevenueTaskRecordCount(Map<String,Object> paramMap){
		return revenueTaskRecordBaseDao.selectRevenueTaskRecordCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(定时同步收益额记录表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRevenueTaskRecordCountDim(Map<String,Object> paramMap){
		return revenueTaskRecordBaseDao.selectRevenueTaskRecordCount(paramMap,true);
	}
	/**
	 * 往(定时同步收益额记录表)新增一条记录
	 * @param revenueTaskRecord
	 * @return
	 */
	@Override
	public int insertRevenueTaskRecord(RevenueTaskRecord revenueTaskRecord){
		return revenueTaskRecordBaseDao.insertRevenueTaskRecord(revenueTaskRecord);
	}
	/**
	 * 批量新增(定时同步收益额记录表)
	 * @param revenueTaskRecordList
	 * @return
	 */
	@Override
	public int insertRevenueTaskRecordBatch(List<RevenueTaskRecord> revenueTaskRecordList){
		return revenueTaskRecordBaseDao.insertRevenueTaskRecordBatch(revenueTaskRecordList);
	}
	/**
	 * 更新(定时同步收益额记录表)信息
	 * @param revenueTaskRecord
	 * @return
	 */
	@Override
	public int updateRevenueTaskRecord(RevenueTaskRecord revenueTaskRecord){
		return revenueTaskRecordBaseDao.updateRevenueTaskRecord(revenueTaskRecord);
	}
	/**
	 * 批量更新(定时同步收益额记录表)信息
	 * @param revenueTaskRecordList
	 * @return
	 */
	@Override
	public int updateRevenueTaskRecordBatch(List<RevenueTaskRecord> revenueTaskRecordList){
		return revenueTaskRecordBaseDao.updateRevenueTaskRecordBatch(revenueTaskRecordList);
	}
	/**
	 * 根据序列号删除(定时同步收益额记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteRevenueTaskRecordLogic(java.math.BigInteger id){
		return revenueTaskRecordBaseDao.deleteRevenueTaskRecordLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(定时同步收益额记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteRevenueTaskRecordLogicBatch(List<java.math.BigInteger> idList){
		return revenueTaskRecordBaseDao.deleteRevenueTaskRecordLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(定时同步收益额记录表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRevenueTaskRecord(java.math.BigInteger id){
//		return revenueTaskRecordBaseDao.deleteRevenueTaskRecord(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(定时同步收益额记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRevenueTaskRecordBatch(List<java.math.BigInteger> idList){
//		return revenueTaskRecordBaseDao.deleteRevenueTaskRecordBatch(idList);
//	}
	
}
