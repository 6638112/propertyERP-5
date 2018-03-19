package com.cnfantasia.server.domainbase.msPrizeGift.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.msPrizeGift.dao.IMsPrizeGiftBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.msPrizeGift.entity.MsPrizeGift;

/**
 * 描述:(奖品详情表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class MsPrizeGiftBaseService implements IMsPrizeGiftBaseService{
	
	private IMsPrizeGiftBaseDao msPrizeGiftBaseDao;
	public void setMsPrizeGiftBaseDao(IMsPrizeGiftBaseDao msPrizeGiftBaseDao) {
		this.msPrizeGiftBaseDao = msPrizeGiftBaseDao;
	}
	/**
	 * 根据条件查询(奖品详情表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MsPrizeGift> getMsPrizeGiftByCondition(Map<String,Object> paramMap){
		return msPrizeGiftBaseDao.selectMsPrizeGiftByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(奖品详情表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MsPrizeGift> getMsPrizeGiftByConditionDim(Map<String,Object> paramMap){
		return msPrizeGiftBaseDao.selectMsPrizeGiftByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(奖品详情表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MsPrizeGift> getMsPrizeGiftByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return msPrizeGiftBaseDao.selectMsPrizeGiftByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(奖品详情表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MsPrizeGift> getMsPrizeGiftByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return msPrizeGiftBaseDao.selectMsPrizeGiftByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(奖品详情表)信息
	 * @param id
	 * @return
	 */
	@Override
	public MsPrizeGift getMsPrizeGiftBySeqId(java.math.BigInteger id){
		return msPrizeGiftBaseDao.selectMsPrizeGiftBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(奖品详情表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMsPrizeGiftCount(Map<String,Object> paramMap){
		return msPrizeGiftBaseDao.selectMsPrizeGiftCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(奖品详情表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMsPrizeGiftCountDim(Map<String,Object> paramMap){
		return msPrizeGiftBaseDao.selectMsPrizeGiftCount(paramMap,true);
	}
	/**
	 * 往(奖品详情表)新增一条记录
	 * @param msPrizeGift
	 * @return
	 */
	@Override
	public int insertMsPrizeGift(MsPrizeGift msPrizeGift){
		return msPrizeGiftBaseDao.insertMsPrizeGift(msPrizeGift);
	}
	/**
	 * 批量新增(奖品详情表)
	 * @param msPrizeGiftList
	 * @return
	 */
	@Override
	public int insertMsPrizeGiftBatch(List<MsPrizeGift> msPrizeGiftList){
		return msPrizeGiftBaseDao.insertMsPrizeGiftBatch(msPrizeGiftList);
	}
	/**
	 * 更新(奖品详情表)信息
	 * @param msPrizeGift
	 * @return
	 */
	@Override
	public int updateMsPrizeGift(MsPrizeGift msPrizeGift){
		return msPrizeGiftBaseDao.updateMsPrizeGift(msPrizeGift);
	}
	/**
	 * 批量更新(奖品详情表)信息
	 * @param msPrizeGiftList
	 * @return
	 */
	@Override
	public int updateMsPrizeGiftBatch(List<MsPrizeGift> msPrizeGiftList){
		return msPrizeGiftBaseDao.updateMsPrizeGiftBatch(msPrizeGiftList);
	}
	/**
	 * 根据序列号删除(奖品详情表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMsPrizeGiftLogic(java.math.BigInteger id){
		return msPrizeGiftBaseDao.deleteMsPrizeGiftLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(奖品详情表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMsPrizeGiftLogicBatch(List<java.math.BigInteger> idList){
		return msPrizeGiftBaseDao.deleteMsPrizeGiftLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(奖品详情表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMsPrizeGift(java.math.BigInteger id){
//		return msPrizeGiftBaseDao.deleteMsPrizeGift(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(奖品详情表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMsPrizeGiftBatch(List<java.math.BigInteger> idList){
//		return msPrizeGiftBaseDao.deleteMsPrizeGiftBatch(idList);
//	}
	
}
