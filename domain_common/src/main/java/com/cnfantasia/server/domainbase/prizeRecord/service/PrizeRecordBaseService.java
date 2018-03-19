package com.cnfantasia.server.domainbase.prizeRecord.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.prizeRecord.dao.IPrizeRecordBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.prizeRecord.entity.PrizeRecord;

/**
 * 描述:(中奖记录) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PrizeRecordBaseService implements IPrizeRecordBaseService{
	
	private IPrizeRecordBaseDao prizeRecordBaseDao;
	public void setPrizeRecordBaseDao(IPrizeRecordBaseDao prizeRecordBaseDao) {
		this.prizeRecordBaseDao = prizeRecordBaseDao;
	}
	/**
	 * 根据条件查询(中奖记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PrizeRecord> getPrizeRecordByCondition(Map<String,Object> paramMap){
		return prizeRecordBaseDao.selectPrizeRecordByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(中奖记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PrizeRecord> getPrizeRecordByConditionDim(Map<String,Object> paramMap){
		return prizeRecordBaseDao.selectPrizeRecordByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(中奖记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PrizeRecord> getPrizeRecordByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return prizeRecordBaseDao.selectPrizeRecordByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(中奖记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PrizeRecord> getPrizeRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return prizeRecordBaseDao.selectPrizeRecordByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(中奖记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public PrizeRecord getPrizeRecordBySeqId(java.math.BigInteger id){
		return prizeRecordBaseDao.selectPrizeRecordBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(中奖记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPrizeRecordCount(Map<String,Object> paramMap){
		return prizeRecordBaseDao.selectPrizeRecordCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(中奖记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPrizeRecordCountDim(Map<String,Object> paramMap){
		return prizeRecordBaseDao.selectPrizeRecordCount(paramMap,true);
	}
	/**
	 * 往(中奖记录)新增一条记录
	 * @param prizeRecord
	 * @return
	 */
	@Override
	public int insertPrizeRecord(PrizeRecord prizeRecord){
		return prizeRecordBaseDao.insertPrizeRecord(prizeRecord);
	}
	/**
	 * 批量新增(中奖记录)
	 * @param prizeRecordList
	 * @return
	 */
	@Override
	public int insertPrizeRecordBatch(List<PrizeRecord> prizeRecordList){
		return prizeRecordBaseDao.insertPrizeRecordBatch(prizeRecordList);
	}
	/**
	 * 更新(中奖记录)信息
	 * @param prizeRecord
	 * @return
	 */
	@Override
	public int updatePrizeRecord(PrizeRecord prizeRecord){
		return prizeRecordBaseDao.updatePrizeRecord(prizeRecord);
	}
	/**
	 * 批量更新(中奖记录)信息
	 * @param prizeRecordList
	 * @return
	 */
	@Override
	public int updatePrizeRecordBatch(List<PrizeRecord> prizeRecordList){
		return prizeRecordBaseDao.updatePrizeRecordBatch(prizeRecordList);
	}
	/**
	 * 根据序列号删除(中奖记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePrizeRecordLogic(java.math.BigInteger id){
		return prizeRecordBaseDao.deletePrizeRecordLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(中奖记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePrizeRecordLogicBatch(List<java.math.BigInteger> idList){
		return prizeRecordBaseDao.deletePrizeRecordLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(中奖记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePrizeRecord(java.math.BigInteger id){
//		return prizeRecordBaseDao.deletePrizeRecord(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(中奖记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePrizeRecordBatch(List<java.math.BigInteger> idList){
//		return prizeRecordBaseDao.deletePrizeRecordBatch(idList);
//	}
	
}
