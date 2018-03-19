package com.cnfantasia.server.domainbase.msPrizeGiftCode.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.msPrizeGiftCode.dao.IMsPrizeGiftCodeBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.msPrizeGiftCode.entity.MsPrizeGiftCode;

/**
 * 描述:(抽奖奖品编码表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class MsPrizeGiftCodeBaseService implements IMsPrizeGiftCodeBaseService{
	
	private IMsPrizeGiftCodeBaseDao msPrizeGiftCodeBaseDao;
	public void setMsPrizeGiftCodeBaseDao(IMsPrizeGiftCodeBaseDao msPrizeGiftCodeBaseDao) {
		this.msPrizeGiftCodeBaseDao = msPrizeGiftCodeBaseDao;
	}
	/**
	 * 根据条件查询(抽奖奖品编码表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MsPrizeGiftCode> getMsPrizeGiftCodeByCondition(Map<String,Object> paramMap){
		return msPrizeGiftCodeBaseDao.selectMsPrizeGiftCodeByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(抽奖奖品编码表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MsPrizeGiftCode> getMsPrizeGiftCodeByConditionDim(Map<String,Object> paramMap){
		return msPrizeGiftCodeBaseDao.selectMsPrizeGiftCodeByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(抽奖奖品编码表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MsPrizeGiftCode> getMsPrizeGiftCodeByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return msPrizeGiftCodeBaseDao.selectMsPrizeGiftCodeByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(抽奖奖品编码表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MsPrizeGiftCode> getMsPrizeGiftCodeByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return msPrizeGiftCodeBaseDao.selectMsPrizeGiftCodeByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(抽奖奖品编码表)信息
	 * @param id
	 * @return
	 */
	@Override
	public MsPrizeGiftCode getMsPrizeGiftCodeBySeqId(java.math.BigInteger id){
		return msPrizeGiftCodeBaseDao.selectMsPrizeGiftCodeBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(抽奖奖品编码表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMsPrizeGiftCodeCount(Map<String,Object> paramMap){
		return msPrizeGiftCodeBaseDao.selectMsPrizeGiftCodeCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(抽奖奖品编码表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMsPrizeGiftCodeCountDim(Map<String,Object> paramMap){
		return msPrizeGiftCodeBaseDao.selectMsPrizeGiftCodeCount(paramMap,true);
	}
	/**
	 * 往(抽奖奖品编码表)新增一条记录
	 * @param msPrizeGiftCode
	 * @return
	 */
	@Override
	public int insertMsPrizeGiftCode(MsPrizeGiftCode msPrizeGiftCode){
		return msPrizeGiftCodeBaseDao.insertMsPrizeGiftCode(msPrizeGiftCode);
	}
	/**
	 * 批量新增(抽奖奖品编码表)
	 * @param msPrizeGiftCodeList
	 * @return
	 */
	@Override
	public int insertMsPrizeGiftCodeBatch(List<MsPrizeGiftCode> msPrizeGiftCodeList){
		return msPrizeGiftCodeBaseDao.insertMsPrizeGiftCodeBatch(msPrizeGiftCodeList);
	}
	/**
	 * 更新(抽奖奖品编码表)信息
	 * @param msPrizeGiftCode
	 * @return
	 */
	@Override
	public int updateMsPrizeGiftCode(MsPrizeGiftCode msPrizeGiftCode){
		return msPrizeGiftCodeBaseDao.updateMsPrizeGiftCode(msPrizeGiftCode);
	}
	/**
	 * 批量更新(抽奖奖品编码表)信息
	 * @param msPrizeGiftCodeList
	 * @return
	 */
	@Override
	public int updateMsPrizeGiftCodeBatch(List<MsPrizeGiftCode> msPrizeGiftCodeList){
		return msPrizeGiftCodeBaseDao.updateMsPrizeGiftCodeBatch(msPrizeGiftCodeList);
	}
	/**
	 * 根据序列号删除(抽奖奖品编码表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMsPrizeGiftCodeLogic(java.math.BigInteger id){
		return msPrizeGiftCodeBaseDao.deleteMsPrizeGiftCodeLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(抽奖奖品编码表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMsPrizeGiftCodeLogicBatch(List<java.math.BigInteger> idList){
		return msPrizeGiftCodeBaseDao.deleteMsPrizeGiftCodeLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(抽奖奖品编码表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMsPrizeGiftCode(java.math.BigInteger id){
//		return msPrizeGiftCodeBaseDao.deleteMsPrizeGiftCode(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(抽奖奖品编码表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMsPrizeGiftCodeBatch(List<java.math.BigInteger> idList){
//		return msPrizeGiftCodeBaseDao.deleteMsPrizeGiftCodeBatch(idList);
//	}
	
}
