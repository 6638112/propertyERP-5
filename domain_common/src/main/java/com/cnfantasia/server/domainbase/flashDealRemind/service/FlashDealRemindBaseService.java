package com.cnfantasia.server.domainbase.flashDealRemind.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.flashDealRemind.dao.IFlashDealRemindBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.flashDealRemind.entity.FlashDealRemind;

/**
 * 描述:(一元Go提醒) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class FlashDealRemindBaseService implements IFlashDealRemindBaseService{
	
	private IFlashDealRemindBaseDao flashDealRemindBaseDao;
	public void setFlashDealRemindBaseDao(IFlashDealRemindBaseDao flashDealRemindBaseDao) {
		this.flashDealRemindBaseDao = flashDealRemindBaseDao;
	}
	/**
	 * 根据条件查询(一元Go提醒)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<FlashDealRemind> getFlashDealRemindByCondition(Map<String,Object> paramMap){
		return flashDealRemindBaseDao.selectFlashDealRemindByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(一元Go提醒)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<FlashDealRemind> getFlashDealRemindByConditionDim(Map<String,Object> paramMap){
		return flashDealRemindBaseDao.selectFlashDealRemindByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(一元Go提醒)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<FlashDealRemind> getFlashDealRemindByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return flashDealRemindBaseDao.selectFlashDealRemindByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(一元Go提醒)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<FlashDealRemind> getFlashDealRemindByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return flashDealRemindBaseDao.selectFlashDealRemindByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(一元Go提醒)信息
	 * @param id
	 * @return
	 */
	@Override
	public FlashDealRemind getFlashDealRemindBySeqId(java.math.BigInteger id){
		return flashDealRemindBaseDao.selectFlashDealRemindBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(一元Go提醒)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getFlashDealRemindCount(Map<String,Object> paramMap){
		return flashDealRemindBaseDao.selectFlashDealRemindCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(一元Go提醒)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getFlashDealRemindCountDim(Map<String,Object> paramMap){
		return flashDealRemindBaseDao.selectFlashDealRemindCount(paramMap,true);
	}
	/**
	 * 往(一元Go提醒)新增一条记录
	 * @param flashDealRemind
	 * @return
	 */
	@Override
	public int insertFlashDealRemind(FlashDealRemind flashDealRemind){
		return flashDealRemindBaseDao.insertFlashDealRemind(flashDealRemind);
	}
	/**
	 * 批量新增(一元Go提醒)
	 * @param flashDealRemindList
	 * @return
	 */
	@Override
	public int insertFlashDealRemindBatch(List<FlashDealRemind> flashDealRemindList){
		return flashDealRemindBaseDao.insertFlashDealRemindBatch(flashDealRemindList);
	}
	/**
	 * 更新(一元Go提醒)信息
	 * @param flashDealRemind
	 * @return
	 */
	@Override
	public int updateFlashDealRemind(FlashDealRemind flashDealRemind){
		return flashDealRemindBaseDao.updateFlashDealRemind(flashDealRemind);
	}
	/**
	 * 批量更新(一元Go提醒)信息
	 * @param flashDealRemindList
	 * @return
	 */
	@Override
	public int updateFlashDealRemindBatch(List<FlashDealRemind> flashDealRemindList){
		return flashDealRemindBaseDao.updateFlashDealRemindBatch(flashDealRemindList);
	}
	/**
	 * 根据序列号删除(一元Go提醒)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteFlashDealRemindLogic(java.math.BigInteger id){
		return flashDealRemindBaseDao.deleteFlashDealRemindLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(一元Go提醒)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteFlashDealRemindLogicBatch(List<java.math.BigInteger> idList){
		return flashDealRemindBaseDao.deleteFlashDealRemindLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(一元Go提醒)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteFlashDealRemind(java.math.BigInteger id){
//		return flashDealRemindBaseDao.deleteFlashDealRemind(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(一元Go提醒)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteFlashDealRemindBatch(List<java.math.BigInteger> idList){
//		return flashDealRemindBaseDao.deleteFlashDealRemindBatch(idList);
//	}
	
}
