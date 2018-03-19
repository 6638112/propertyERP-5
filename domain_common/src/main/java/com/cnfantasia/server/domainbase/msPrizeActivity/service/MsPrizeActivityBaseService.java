package com.cnfantasia.server.domainbase.msPrizeActivity.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.msPrizeActivity.dao.IMsPrizeActivityBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.msPrizeActivity.entity.MsPrizeActivity;

/**
 * 描述:(抽奖活动表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class MsPrizeActivityBaseService implements IMsPrizeActivityBaseService{
	
	private IMsPrizeActivityBaseDao msPrizeActivityBaseDao;
	public void setMsPrizeActivityBaseDao(IMsPrizeActivityBaseDao msPrizeActivityBaseDao) {
		this.msPrizeActivityBaseDao = msPrizeActivityBaseDao;
	}
	/**
	 * 根据条件查询(抽奖活动表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MsPrizeActivity> getMsPrizeActivityByCondition(Map<String,Object> paramMap){
		return msPrizeActivityBaseDao.selectMsPrizeActivityByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(抽奖活动表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MsPrizeActivity> getMsPrizeActivityByConditionDim(Map<String,Object> paramMap){
		return msPrizeActivityBaseDao.selectMsPrizeActivityByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(抽奖活动表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MsPrizeActivity> getMsPrizeActivityByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return msPrizeActivityBaseDao.selectMsPrizeActivityByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(抽奖活动表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MsPrizeActivity> getMsPrizeActivityByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return msPrizeActivityBaseDao.selectMsPrizeActivityByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(抽奖活动表)信息
	 * @param id
	 * @return
	 */
	@Override
	public MsPrizeActivity getMsPrizeActivityBySeqId(java.math.BigInteger id){
		return msPrizeActivityBaseDao.selectMsPrizeActivityBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(抽奖活动表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMsPrizeActivityCount(Map<String,Object> paramMap){
		return msPrizeActivityBaseDao.selectMsPrizeActivityCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(抽奖活动表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMsPrizeActivityCountDim(Map<String,Object> paramMap){
		return msPrizeActivityBaseDao.selectMsPrizeActivityCount(paramMap,true);
	}
	/**
	 * 往(抽奖活动表)新增一条记录
	 * @param msPrizeActivity
	 * @return
	 */
	@Override
	public int insertMsPrizeActivity(MsPrizeActivity msPrizeActivity){
		return msPrizeActivityBaseDao.insertMsPrizeActivity(msPrizeActivity);
	}
	/**
	 * 批量新增(抽奖活动表)
	 * @param msPrizeActivityList
	 * @return
	 */
	@Override
	public int insertMsPrizeActivityBatch(List<MsPrizeActivity> msPrizeActivityList){
		return msPrizeActivityBaseDao.insertMsPrizeActivityBatch(msPrizeActivityList);
	}
	/**
	 * 更新(抽奖活动表)信息
	 * @param msPrizeActivity
	 * @return
	 */
	@Override
	public int updateMsPrizeActivity(MsPrizeActivity msPrizeActivity){
		return msPrizeActivityBaseDao.updateMsPrizeActivity(msPrizeActivity);
	}
	/**
	 * 批量更新(抽奖活动表)信息
	 * @param msPrizeActivityList
	 * @return
	 */
	@Override
	public int updateMsPrizeActivityBatch(List<MsPrizeActivity> msPrizeActivityList){
		return msPrizeActivityBaseDao.updateMsPrizeActivityBatch(msPrizeActivityList);
	}
	/**
	 * 根据序列号删除(抽奖活动表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMsPrizeActivityLogic(java.math.BigInteger id){
		return msPrizeActivityBaseDao.deleteMsPrizeActivityLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(抽奖活动表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMsPrizeActivityLogicBatch(List<java.math.BigInteger> idList){
		return msPrizeActivityBaseDao.deleteMsPrizeActivityLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(抽奖活动表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMsPrizeActivity(java.math.BigInteger id){
//		return msPrizeActivityBaseDao.deleteMsPrizeActivity(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(抽奖活动表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMsPrizeActivityBatch(List<java.math.BigInteger> idList){
//		return msPrizeActivityBaseDao.deleteMsPrizeActivityBatch(idList);
//	}
	
}
