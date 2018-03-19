package com.cnfantasia.server.domainbase.msPrizeActHasOpt.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.msPrizeActHasOpt.dao.IMsPrizeActHasOptBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.msPrizeActHasOpt.entity.MsPrizeActHasOpt;

/**
 * 描述:(抽奖活动与奖项关系表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class MsPrizeActHasOptBaseService implements IMsPrizeActHasOptBaseService{
	
	private IMsPrizeActHasOptBaseDao msPrizeActHasOptBaseDao;
	public void setMsPrizeActHasOptBaseDao(IMsPrizeActHasOptBaseDao msPrizeActHasOptBaseDao) {
		this.msPrizeActHasOptBaseDao = msPrizeActHasOptBaseDao;
	}
	/**
	 * 根据条件查询(抽奖活动与奖项关系表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MsPrizeActHasOpt> getMsPrizeActHasOptByCondition(Map<String,Object> paramMap){
		return msPrizeActHasOptBaseDao.selectMsPrizeActHasOptByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(抽奖活动与奖项关系表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MsPrizeActHasOpt> getMsPrizeActHasOptByConditionDim(Map<String,Object> paramMap){
		return msPrizeActHasOptBaseDao.selectMsPrizeActHasOptByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(抽奖活动与奖项关系表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MsPrizeActHasOpt> getMsPrizeActHasOptByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return msPrizeActHasOptBaseDao.selectMsPrizeActHasOptByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(抽奖活动与奖项关系表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MsPrizeActHasOpt> getMsPrizeActHasOptByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return msPrizeActHasOptBaseDao.selectMsPrizeActHasOptByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(抽奖活动与奖项关系表)信息
	 * @param id
	 * @return
	 */
	@Override
	public MsPrizeActHasOpt getMsPrizeActHasOptBySeqId(java.math.BigInteger id){
		return msPrizeActHasOptBaseDao.selectMsPrizeActHasOptBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(抽奖活动与奖项关系表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMsPrizeActHasOptCount(Map<String,Object> paramMap){
		return msPrizeActHasOptBaseDao.selectMsPrizeActHasOptCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(抽奖活动与奖项关系表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMsPrizeActHasOptCountDim(Map<String,Object> paramMap){
		return msPrizeActHasOptBaseDao.selectMsPrizeActHasOptCount(paramMap,true);
	}
	/**
	 * 往(抽奖活动与奖项关系表)新增一条记录
	 * @param msPrizeActHasOpt
	 * @return
	 */
	@Override
	public int insertMsPrizeActHasOpt(MsPrizeActHasOpt msPrizeActHasOpt){
		return msPrizeActHasOptBaseDao.insertMsPrizeActHasOpt(msPrizeActHasOpt);
	}
	/**
	 * 批量新增(抽奖活动与奖项关系表)
	 * @param msPrizeActHasOptList
	 * @return
	 */
	@Override
	public int insertMsPrizeActHasOptBatch(List<MsPrizeActHasOpt> msPrizeActHasOptList){
		return msPrizeActHasOptBaseDao.insertMsPrizeActHasOptBatch(msPrizeActHasOptList);
	}
	/**
	 * 更新(抽奖活动与奖项关系表)信息
	 * @param msPrizeActHasOpt
	 * @return
	 */
	@Override
	public int updateMsPrizeActHasOpt(MsPrizeActHasOpt msPrizeActHasOpt){
		return msPrizeActHasOptBaseDao.updateMsPrizeActHasOpt(msPrizeActHasOpt);
	}
	/**
	 * 批量更新(抽奖活动与奖项关系表)信息
	 * @param msPrizeActHasOptList
	 * @return
	 */
	@Override
	public int updateMsPrizeActHasOptBatch(List<MsPrizeActHasOpt> msPrizeActHasOptList){
		return msPrizeActHasOptBaseDao.updateMsPrizeActHasOptBatch(msPrizeActHasOptList);
	}
	/**
	 * 根据序列号删除(抽奖活动与奖项关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMsPrizeActHasOptLogic(java.math.BigInteger id){
		return msPrizeActHasOptBaseDao.deleteMsPrizeActHasOptLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(抽奖活动与奖项关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMsPrizeActHasOptLogicBatch(List<java.math.BigInteger> idList){
		return msPrizeActHasOptBaseDao.deleteMsPrizeActHasOptLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(抽奖活动与奖项关系表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMsPrizeActHasOpt(java.math.BigInteger id){
//		return msPrizeActHasOptBaseDao.deleteMsPrizeActHasOpt(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(抽奖活动与奖项关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMsPrizeActHasOptBatch(List<java.math.BigInteger> idList){
//		return msPrizeActHasOptBaseDao.deleteMsPrizeActHasOptBatch(idList);
//	}
	
}
