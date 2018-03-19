package com.cnfantasia.server.domainbase.flashDealActivity.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.flashDealActivity.dao.IFlashDealActivityBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.flashDealActivity.entity.FlashDealActivity;

/**
 * 描述:(秒杀活动表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class FlashDealActivityBaseService implements IFlashDealActivityBaseService{
	
	private IFlashDealActivityBaseDao flashDealActivityBaseDao;
	public void setFlashDealActivityBaseDao(IFlashDealActivityBaseDao flashDealActivityBaseDao) {
		this.flashDealActivityBaseDao = flashDealActivityBaseDao;
	}
	/**
	 * 根据条件查询(秒杀活动表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<FlashDealActivity> getFlashDealActivityByCondition(Map<String,Object> paramMap){
		return flashDealActivityBaseDao.selectFlashDealActivityByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(秒杀活动表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<FlashDealActivity> getFlashDealActivityByConditionDim(Map<String,Object> paramMap){
		return flashDealActivityBaseDao.selectFlashDealActivityByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(秒杀活动表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<FlashDealActivity> getFlashDealActivityByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return flashDealActivityBaseDao.selectFlashDealActivityByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(秒杀活动表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<FlashDealActivity> getFlashDealActivityByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return flashDealActivityBaseDao.selectFlashDealActivityByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(秒杀活动表)信息
	 * @param id
	 * @return
	 */
	@Override
	public FlashDealActivity getFlashDealActivityBySeqId(java.math.BigInteger id){
		return flashDealActivityBaseDao.selectFlashDealActivityBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(秒杀活动表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getFlashDealActivityCount(Map<String,Object> paramMap){
		return flashDealActivityBaseDao.selectFlashDealActivityCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(秒杀活动表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getFlashDealActivityCountDim(Map<String,Object> paramMap){
		return flashDealActivityBaseDao.selectFlashDealActivityCount(paramMap,true);
	}
	/**
	 * 往(秒杀活动表)新增一条记录
	 * @param flashDealActivity
	 * @return
	 */
	@Override
	public int insertFlashDealActivity(FlashDealActivity flashDealActivity){
		return flashDealActivityBaseDao.insertFlashDealActivity(flashDealActivity);
	}
	/**
	 * 批量新增(秒杀活动表)
	 * @param flashDealActivityList
	 * @return
	 */
	@Override
	public int insertFlashDealActivityBatch(List<FlashDealActivity> flashDealActivityList){
		return flashDealActivityBaseDao.insertFlashDealActivityBatch(flashDealActivityList);
	}
	/**
	 * 更新(秒杀活动表)信息
	 * @param flashDealActivity
	 * @return
	 */
	@Override
	public int updateFlashDealActivity(FlashDealActivity flashDealActivity){
		return flashDealActivityBaseDao.updateFlashDealActivity(flashDealActivity);
	}
	/**
	 * 批量更新(秒杀活动表)信息
	 * @param flashDealActivityList
	 * @return
	 */
	@Override
	public int updateFlashDealActivityBatch(List<FlashDealActivity> flashDealActivityList){
		return flashDealActivityBaseDao.updateFlashDealActivityBatch(flashDealActivityList);
	}
	/**
	 * 根据序列号删除(秒杀活动表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteFlashDealActivityLogic(java.math.BigInteger id){
		return flashDealActivityBaseDao.deleteFlashDealActivityLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(秒杀活动表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteFlashDealActivityLogicBatch(List<java.math.BigInteger> idList){
		return flashDealActivityBaseDao.deleteFlashDealActivityLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(秒杀活动表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteFlashDealActivity(java.math.BigInteger id){
//		return flashDealActivityBaseDao.deleteFlashDealActivity(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(秒杀活动表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteFlashDealActivityBatch(List<java.math.BigInteger> idList){
//		return flashDealActivityBaseDao.deleteFlashDealActivityBatch(idList);
//	}
	
}
