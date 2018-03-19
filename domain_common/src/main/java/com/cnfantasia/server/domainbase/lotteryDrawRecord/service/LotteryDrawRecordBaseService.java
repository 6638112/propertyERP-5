package com.cnfantasia.server.domainbase.lotteryDrawRecord.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.lotteryDrawRecord.dao.ILotteryDrawRecordBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.lotteryDrawRecord.entity.LotteryDrawRecord;

/**
 * 描述:(幸运抽奖记录表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class LotteryDrawRecordBaseService implements ILotteryDrawRecordBaseService{
	
	private ILotteryDrawRecordBaseDao lotteryDrawRecordBaseDao;
	public void setLotteryDrawRecordBaseDao(ILotteryDrawRecordBaseDao lotteryDrawRecordBaseDao) {
		this.lotteryDrawRecordBaseDao = lotteryDrawRecordBaseDao;
	}
	/**
	 * 根据条件查询(幸运抽奖记录表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<LotteryDrawRecord> getLotteryDrawRecordByCondition(Map<String,Object> paramMap){
		return lotteryDrawRecordBaseDao.selectLotteryDrawRecordByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(幸运抽奖记录表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<LotteryDrawRecord> getLotteryDrawRecordByConditionDim(Map<String,Object> paramMap){
		return lotteryDrawRecordBaseDao.selectLotteryDrawRecordByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(幸运抽奖记录表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<LotteryDrawRecord> getLotteryDrawRecordByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return lotteryDrawRecordBaseDao.selectLotteryDrawRecordByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(幸运抽奖记录表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<LotteryDrawRecord> getLotteryDrawRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return lotteryDrawRecordBaseDao.selectLotteryDrawRecordByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(幸运抽奖记录表)信息
	 * @param id
	 * @return
	 */
	@Override
	public LotteryDrawRecord getLotteryDrawRecordBySeqId(java.math.BigInteger id){
		return lotteryDrawRecordBaseDao.selectLotteryDrawRecordBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(幸运抽奖记录表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getLotteryDrawRecordCount(Map<String,Object> paramMap){
		return lotteryDrawRecordBaseDao.selectLotteryDrawRecordCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(幸运抽奖记录表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getLotteryDrawRecordCountDim(Map<String,Object> paramMap){
		return lotteryDrawRecordBaseDao.selectLotteryDrawRecordCount(paramMap,true);
	}
	/**
	 * 往(幸运抽奖记录表)新增一条记录
	 * @param lotteryDrawRecord
	 * @return
	 */
	@Override
	public int insertLotteryDrawRecord(LotteryDrawRecord lotteryDrawRecord){
		return lotteryDrawRecordBaseDao.insertLotteryDrawRecord(lotteryDrawRecord);
	}
	/**
	 * 批量新增(幸运抽奖记录表)
	 * @param lotteryDrawRecordList
	 * @return
	 */
	@Override
	public int insertLotteryDrawRecordBatch(List<LotteryDrawRecord> lotteryDrawRecordList){
		return lotteryDrawRecordBaseDao.insertLotteryDrawRecordBatch(lotteryDrawRecordList);
	}
	/**
	 * 更新(幸运抽奖记录表)信息
	 * @param lotteryDrawRecord
	 * @return
	 */
	@Override
	public int updateLotteryDrawRecord(LotteryDrawRecord lotteryDrawRecord){
		return lotteryDrawRecordBaseDao.updateLotteryDrawRecord(lotteryDrawRecord);
	}
	/**
	 * 批量更新(幸运抽奖记录表)信息
	 * @param lotteryDrawRecordList
	 * @return
	 */
	@Override
	public int updateLotteryDrawRecordBatch(List<LotteryDrawRecord> lotteryDrawRecordList){
		return lotteryDrawRecordBaseDao.updateLotteryDrawRecordBatch(lotteryDrawRecordList);
	}
	/**
	 * 根据序列号删除(幸运抽奖记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteLotteryDrawRecordLogic(java.math.BigInteger id){
		return lotteryDrawRecordBaseDao.deleteLotteryDrawRecordLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(幸运抽奖记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteLotteryDrawRecordLogicBatch(List<java.math.BigInteger> idList){
		return lotteryDrawRecordBaseDao.deleteLotteryDrawRecordLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(幸运抽奖记录表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteLotteryDrawRecord(java.math.BigInteger id){
//		return lotteryDrawRecordBaseDao.deleteLotteryDrawRecord(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(幸运抽奖记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteLotteryDrawRecordBatch(List<java.math.BigInteger> idList){
//		return lotteryDrawRecordBaseDao.deleteLotteryDrawRecordBatch(idList);
//	}
	
}
