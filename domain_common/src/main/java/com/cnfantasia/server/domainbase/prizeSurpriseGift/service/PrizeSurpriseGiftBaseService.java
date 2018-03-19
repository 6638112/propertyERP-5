package com.cnfantasia.server.domainbase.prizeSurpriseGift.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.prizeSurpriseGift.dao.IPrizeSurpriseGiftBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.prizeSurpriseGift.entity.PrizeSurpriseGift;

/**
 * 描述:(抽奖结果的意外惊喜) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PrizeSurpriseGiftBaseService implements IPrizeSurpriseGiftBaseService{
	
	private IPrizeSurpriseGiftBaseDao prizeSurpriseGiftBaseDao;
	public void setPrizeSurpriseGiftBaseDao(IPrizeSurpriseGiftBaseDao prizeSurpriseGiftBaseDao) {
		this.prizeSurpriseGiftBaseDao = prizeSurpriseGiftBaseDao;
	}
	/**
	 * 根据条件查询(抽奖结果的意外惊喜)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PrizeSurpriseGift> getPrizeSurpriseGiftByCondition(Map<String,Object> paramMap){
		return prizeSurpriseGiftBaseDao.selectPrizeSurpriseGiftByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(抽奖结果的意外惊喜)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PrizeSurpriseGift> getPrizeSurpriseGiftByConditionDim(Map<String,Object> paramMap){
		return prizeSurpriseGiftBaseDao.selectPrizeSurpriseGiftByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(抽奖结果的意外惊喜)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PrizeSurpriseGift> getPrizeSurpriseGiftByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return prizeSurpriseGiftBaseDao.selectPrizeSurpriseGiftByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(抽奖结果的意外惊喜)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PrizeSurpriseGift> getPrizeSurpriseGiftByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return prizeSurpriseGiftBaseDao.selectPrizeSurpriseGiftByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(抽奖结果的意外惊喜)信息
	 * @param id
	 * @return
	 */
	@Override
	public PrizeSurpriseGift getPrizeSurpriseGiftBySeqId(java.math.BigInteger id){
		return prizeSurpriseGiftBaseDao.selectPrizeSurpriseGiftBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(抽奖结果的意外惊喜)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPrizeSurpriseGiftCount(Map<String,Object> paramMap){
		return prizeSurpriseGiftBaseDao.selectPrizeSurpriseGiftCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(抽奖结果的意外惊喜)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPrizeSurpriseGiftCountDim(Map<String,Object> paramMap){
		return prizeSurpriseGiftBaseDao.selectPrizeSurpriseGiftCount(paramMap,true);
	}
	/**
	 * 往(抽奖结果的意外惊喜)新增一条记录
	 * @param prizeSurpriseGift
	 * @return
	 */
	@Override
	public int insertPrizeSurpriseGift(PrizeSurpriseGift prizeSurpriseGift){
		return prizeSurpriseGiftBaseDao.insertPrizeSurpriseGift(prizeSurpriseGift);
	}
	/**
	 * 批量新增(抽奖结果的意外惊喜)
	 * @param prizeSurpriseGiftList
	 * @return
	 */
	@Override
	public int insertPrizeSurpriseGiftBatch(List<PrizeSurpriseGift> prizeSurpriseGiftList){
		return prizeSurpriseGiftBaseDao.insertPrizeSurpriseGiftBatch(prizeSurpriseGiftList);
	}
	/**
	 * 更新(抽奖结果的意外惊喜)信息
	 * @param prizeSurpriseGift
	 * @return
	 */
	@Override
	public int updatePrizeSurpriseGift(PrizeSurpriseGift prizeSurpriseGift){
		return prizeSurpriseGiftBaseDao.updatePrizeSurpriseGift(prizeSurpriseGift);
	}
	/**
	 * 批量更新(抽奖结果的意外惊喜)信息
	 * @param prizeSurpriseGiftList
	 * @return
	 */
	@Override
	public int updatePrizeSurpriseGiftBatch(List<PrizeSurpriseGift> prizeSurpriseGiftList){
		return prizeSurpriseGiftBaseDao.updatePrizeSurpriseGiftBatch(prizeSurpriseGiftList);
	}
	/**
	 * 根据序列号删除(抽奖结果的意外惊喜)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePrizeSurpriseGiftLogic(java.math.BigInteger id){
		return prizeSurpriseGiftBaseDao.deletePrizeSurpriseGiftLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(抽奖结果的意外惊喜)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePrizeSurpriseGiftLogicBatch(List<java.math.BigInteger> idList){
		return prizeSurpriseGiftBaseDao.deletePrizeSurpriseGiftLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(抽奖结果的意外惊喜)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePrizeSurpriseGift(java.math.BigInteger id){
//		return prizeSurpriseGiftBaseDao.deletePrizeSurpriseGift(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(抽奖结果的意外惊喜)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePrizeSurpriseGiftBatch(List<java.math.BigInteger> idList){
//		return prizeSurpriseGiftBaseDao.deletePrizeSurpriseGiftBatch(idList);
//	}
	
}
