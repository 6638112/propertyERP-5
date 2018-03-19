package com.cnfantasia.server.domainbase.flashDealBuyRecord.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.flashDealBuyRecord.dao.IFlashDealBuyRecordBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.flashDealBuyRecord.entity.FlashDealBuyRecord;

/**
 * 描述:(秒杀活动购买记录表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class FlashDealBuyRecordBaseService implements IFlashDealBuyRecordBaseService{
	
	private IFlashDealBuyRecordBaseDao flashDealBuyRecordBaseDao;
	public void setFlashDealBuyRecordBaseDao(IFlashDealBuyRecordBaseDao flashDealBuyRecordBaseDao) {
		this.flashDealBuyRecordBaseDao = flashDealBuyRecordBaseDao;
	}
	/**
	 * 根据条件查询(秒杀活动购买记录表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<FlashDealBuyRecord> getFlashDealBuyRecordByCondition(Map<String,Object> paramMap){
		return flashDealBuyRecordBaseDao.selectFlashDealBuyRecordByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(秒杀活动购买记录表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<FlashDealBuyRecord> getFlashDealBuyRecordByConditionDim(Map<String,Object> paramMap){
		return flashDealBuyRecordBaseDao.selectFlashDealBuyRecordByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(秒杀活动购买记录表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<FlashDealBuyRecord> getFlashDealBuyRecordByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return flashDealBuyRecordBaseDao.selectFlashDealBuyRecordByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(秒杀活动购买记录表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<FlashDealBuyRecord> getFlashDealBuyRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return flashDealBuyRecordBaseDao.selectFlashDealBuyRecordByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(秒杀活动购买记录表)信息
	 * @param id
	 * @return
	 */
	@Override
	public FlashDealBuyRecord getFlashDealBuyRecordBySeqId(java.math.BigInteger id){
		return flashDealBuyRecordBaseDao.selectFlashDealBuyRecordBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(秒杀活动购买记录表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getFlashDealBuyRecordCount(Map<String,Object> paramMap){
		return flashDealBuyRecordBaseDao.selectFlashDealBuyRecordCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(秒杀活动购买记录表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getFlashDealBuyRecordCountDim(Map<String,Object> paramMap){
		return flashDealBuyRecordBaseDao.selectFlashDealBuyRecordCount(paramMap,true);
	}
	/**
	 * 往(秒杀活动购买记录表)新增一条记录
	 * @param flashDealBuyRecord
	 * @return
	 */
	@Override
	public int insertFlashDealBuyRecord(FlashDealBuyRecord flashDealBuyRecord){
		return flashDealBuyRecordBaseDao.insertFlashDealBuyRecord(flashDealBuyRecord);
	}
	/**
	 * 批量新增(秒杀活动购买记录表)
	 * @param flashDealBuyRecordList
	 * @return
	 */
	@Override
	public int insertFlashDealBuyRecordBatch(List<FlashDealBuyRecord> flashDealBuyRecordList){
		return flashDealBuyRecordBaseDao.insertFlashDealBuyRecordBatch(flashDealBuyRecordList);
	}
	/**
	 * 更新(秒杀活动购买记录表)信息
	 * @param flashDealBuyRecord
	 * @return
	 */
	@Override
	public int updateFlashDealBuyRecord(FlashDealBuyRecord flashDealBuyRecord){
		return flashDealBuyRecordBaseDao.updateFlashDealBuyRecord(flashDealBuyRecord);
	}
	/**
	 * 批量更新(秒杀活动购买记录表)信息
	 * @param flashDealBuyRecordList
	 * @return
	 */
	@Override
	public int updateFlashDealBuyRecordBatch(List<FlashDealBuyRecord> flashDealBuyRecordList){
		return flashDealBuyRecordBaseDao.updateFlashDealBuyRecordBatch(flashDealBuyRecordList);
	}
	/**
	 * 根据序列号删除(秒杀活动购买记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteFlashDealBuyRecordLogic(java.math.BigInteger id){
		return flashDealBuyRecordBaseDao.deleteFlashDealBuyRecordLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(秒杀活动购买记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteFlashDealBuyRecordLogicBatch(List<java.math.BigInteger> idList){
		return flashDealBuyRecordBaseDao.deleteFlashDealBuyRecordLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(秒杀活动购买记录表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteFlashDealBuyRecord(java.math.BigInteger id){
//		return flashDealBuyRecordBaseDao.deleteFlashDealBuyRecord(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(秒杀活动购买记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteFlashDealBuyRecordBatch(List<java.math.BigInteger> idList){
//		return flashDealBuyRecordBaseDao.deleteFlashDealBuyRecordBatch(idList);
//	}
	
}
