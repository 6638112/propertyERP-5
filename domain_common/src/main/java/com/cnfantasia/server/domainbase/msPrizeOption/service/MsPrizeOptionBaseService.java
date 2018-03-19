package com.cnfantasia.server.domainbase.msPrizeOption.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.msPrizeOption.dao.IMsPrizeOptionBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.msPrizeOption.entity.MsPrizeOption;

/**
 * 描述:(奖项表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class MsPrizeOptionBaseService implements IMsPrizeOptionBaseService{
	
	private IMsPrizeOptionBaseDao msPrizeOptionBaseDao;
	public void setMsPrizeOptionBaseDao(IMsPrizeOptionBaseDao msPrizeOptionBaseDao) {
		this.msPrizeOptionBaseDao = msPrizeOptionBaseDao;
	}
	/**
	 * 根据条件查询(奖项表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MsPrizeOption> getMsPrizeOptionByCondition(Map<String,Object> paramMap){
		return msPrizeOptionBaseDao.selectMsPrizeOptionByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(奖项表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MsPrizeOption> getMsPrizeOptionByConditionDim(Map<String,Object> paramMap){
		return msPrizeOptionBaseDao.selectMsPrizeOptionByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(奖项表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MsPrizeOption> getMsPrizeOptionByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return msPrizeOptionBaseDao.selectMsPrizeOptionByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(奖项表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MsPrizeOption> getMsPrizeOptionByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return msPrizeOptionBaseDao.selectMsPrizeOptionByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(奖项表)信息
	 * @param id
	 * @return
	 */
	@Override
	public MsPrizeOption getMsPrizeOptionBySeqId(java.math.BigInteger id){
		return msPrizeOptionBaseDao.selectMsPrizeOptionBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(奖项表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMsPrizeOptionCount(Map<String,Object> paramMap){
		return msPrizeOptionBaseDao.selectMsPrizeOptionCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(奖项表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMsPrizeOptionCountDim(Map<String,Object> paramMap){
		return msPrizeOptionBaseDao.selectMsPrizeOptionCount(paramMap,true);
	}
	/**
	 * 往(奖项表)新增一条记录
	 * @param msPrizeOption
	 * @return
	 */
	@Override
	public int insertMsPrizeOption(MsPrizeOption msPrizeOption){
		return msPrizeOptionBaseDao.insertMsPrizeOption(msPrizeOption);
	}
	/**
	 * 批量新增(奖项表)
	 * @param msPrizeOptionList
	 * @return
	 */
	@Override
	public int insertMsPrizeOptionBatch(List<MsPrizeOption> msPrizeOptionList){
		return msPrizeOptionBaseDao.insertMsPrizeOptionBatch(msPrizeOptionList);
	}
	/**
	 * 更新(奖项表)信息
	 * @param msPrizeOption
	 * @return
	 */
	@Override
	public int updateMsPrizeOption(MsPrizeOption msPrizeOption){
		return msPrizeOptionBaseDao.updateMsPrizeOption(msPrizeOption);
	}
	/**
	 * 批量更新(奖项表)信息
	 * @param msPrizeOptionList
	 * @return
	 */
	@Override
	public int updateMsPrizeOptionBatch(List<MsPrizeOption> msPrizeOptionList){
		return msPrizeOptionBaseDao.updateMsPrizeOptionBatch(msPrizeOptionList);
	}
	/**
	 * 根据序列号删除(奖项表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMsPrizeOptionLogic(java.math.BigInteger id){
		return msPrizeOptionBaseDao.deleteMsPrizeOptionLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(奖项表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMsPrizeOptionLogicBatch(List<java.math.BigInteger> idList){
		return msPrizeOptionBaseDao.deleteMsPrizeOptionLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(奖项表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMsPrizeOption(java.math.BigInteger id){
//		return msPrizeOptionBaseDao.deleteMsPrizeOption(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(奖项表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMsPrizeOptionBatch(List<java.math.BigInteger> idList){
//		return msPrizeOptionBaseDao.deleteMsPrizeOptionBatch(idList);
//	}
	
}
