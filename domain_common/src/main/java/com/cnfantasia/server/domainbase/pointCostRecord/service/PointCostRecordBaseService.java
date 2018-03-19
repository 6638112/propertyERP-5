package com.cnfantasia.server.domainbase.pointCostRecord.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.pointCostRecord.dao.IPointCostRecordBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.pointCostRecord.entity.PointCostRecord;

/**
 * 描述:(积分消费记录) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PointCostRecordBaseService implements IPointCostRecordBaseService{
	
	private IPointCostRecordBaseDao pointCostRecordBaseDao;
	public void setPointCostRecordBaseDao(IPointCostRecordBaseDao pointCostRecordBaseDao) {
		this.pointCostRecordBaseDao = pointCostRecordBaseDao;
	}
	/**
	 * 根据条件查询(积分消费记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PointCostRecord> getPointCostRecordByCondition(Map<String,Object> paramMap){
		return pointCostRecordBaseDao.selectPointCostRecordByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(积分消费记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PointCostRecord> getPointCostRecordByConditionDim(Map<String,Object> paramMap){
		return pointCostRecordBaseDao.selectPointCostRecordByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(积分消费记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PointCostRecord> getPointCostRecordByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return pointCostRecordBaseDao.selectPointCostRecordByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(积分消费记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PointCostRecord> getPointCostRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return pointCostRecordBaseDao.selectPointCostRecordByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(积分消费记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public PointCostRecord getPointCostRecordBySeqId(java.math.BigInteger id){
		return pointCostRecordBaseDao.selectPointCostRecordBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(积分消费记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPointCostRecordCount(Map<String,Object> paramMap){
		return pointCostRecordBaseDao.selectPointCostRecordCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(积分消费记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPointCostRecordCountDim(Map<String,Object> paramMap){
		return pointCostRecordBaseDao.selectPointCostRecordCount(paramMap,true);
	}
	/**
	 * 往(积分消费记录)新增一条记录
	 * @param pointCostRecord
	 * @return
	 */
	@Override
	public int insertPointCostRecord(PointCostRecord pointCostRecord){
		return pointCostRecordBaseDao.insertPointCostRecord(pointCostRecord);
	}
	/**
	 * 批量新增(积分消费记录)
	 * @param pointCostRecordList
	 * @return
	 */
	@Override
	public int insertPointCostRecordBatch(List<PointCostRecord> pointCostRecordList){
		return pointCostRecordBaseDao.insertPointCostRecordBatch(pointCostRecordList);
	}
	/**
	 * 更新(积分消费记录)信息
	 * @param pointCostRecord
	 * @return
	 */
	@Override
	public int updatePointCostRecord(PointCostRecord pointCostRecord){
		return pointCostRecordBaseDao.updatePointCostRecord(pointCostRecord);
	}
	/**
	 * 批量更新(积分消费记录)信息
	 * @param pointCostRecordList
	 * @return
	 */
	@Override
	public int updatePointCostRecordBatch(List<PointCostRecord> pointCostRecordList){
		return pointCostRecordBaseDao.updatePointCostRecordBatch(pointCostRecordList);
	}
	/**
	 * 根据序列号删除(积分消费记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePointCostRecordLogic(java.math.BigInteger id){
		return pointCostRecordBaseDao.deletePointCostRecordLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(积分消费记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePointCostRecordLogicBatch(List<java.math.BigInteger> idList){
		return pointCostRecordBaseDao.deletePointCostRecordLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(积分消费记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePointCostRecord(java.math.BigInteger id){
//		return pointCostRecordBaseDao.deletePointCostRecord(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(积分消费记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePointCostRecordBatch(List<java.math.BigInteger> idList){
//		return pointCostRecordBaseDao.deletePointCostRecordBatch(idList);
//	}
	
}
