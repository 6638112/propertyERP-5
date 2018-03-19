package com.cnfantasia.server.domainbase.prizeRecordTmp.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.prizeRecordTmp.dao.IPrizeRecordTmpBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.prizeRecordTmp.entity.PrizeRecordTmp;

/**
 * 描述:(临时用户中奖记录) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PrizeRecordTmpBaseService implements IPrizeRecordTmpBaseService{
	
	private IPrizeRecordTmpBaseDao prizeRecordTmpBaseDao;
	public void setPrizeRecordTmpBaseDao(IPrizeRecordTmpBaseDao prizeRecordTmpBaseDao) {
		this.prizeRecordTmpBaseDao = prizeRecordTmpBaseDao;
	}
	/**
	 * 根据条件查询(临时用户中奖记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PrizeRecordTmp> getPrizeRecordTmpByCondition(Map<String,Object> paramMap){
		return prizeRecordTmpBaseDao.selectPrizeRecordTmpByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(临时用户中奖记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PrizeRecordTmp> getPrizeRecordTmpByConditionDim(Map<String,Object> paramMap){
		return prizeRecordTmpBaseDao.selectPrizeRecordTmpByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(临时用户中奖记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PrizeRecordTmp> getPrizeRecordTmpByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return prizeRecordTmpBaseDao.selectPrizeRecordTmpByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(临时用户中奖记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PrizeRecordTmp> getPrizeRecordTmpByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return prizeRecordTmpBaseDao.selectPrizeRecordTmpByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(临时用户中奖记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public PrizeRecordTmp getPrizeRecordTmpBySeqId(java.math.BigInteger id){
		return prizeRecordTmpBaseDao.selectPrizeRecordTmpBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(临时用户中奖记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPrizeRecordTmpCount(Map<String,Object> paramMap){
		return prizeRecordTmpBaseDao.selectPrizeRecordTmpCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(临时用户中奖记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPrizeRecordTmpCountDim(Map<String,Object> paramMap){
		return prizeRecordTmpBaseDao.selectPrizeRecordTmpCount(paramMap,true);
	}
	/**
	 * 往(临时用户中奖记录)新增一条记录
	 * @param prizeRecordTmp
	 * @return
	 */
	@Override
	public int insertPrizeRecordTmp(PrizeRecordTmp prizeRecordTmp){
		return prizeRecordTmpBaseDao.insertPrizeRecordTmp(prizeRecordTmp);
	}
	/**
	 * 批量新增(临时用户中奖记录)
	 * @param prizeRecordTmpList
	 * @return
	 */
	@Override
	public int insertPrizeRecordTmpBatch(List<PrizeRecordTmp> prizeRecordTmpList){
		return prizeRecordTmpBaseDao.insertPrizeRecordTmpBatch(prizeRecordTmpList);
	}
	/**
	 * 更新(临时用户中奖记录)信息
	 * @param prizeRecordTmp
	 * @return
	 */
	@Override
	public int updatePrizeRecordTmp(PrizeRecordTmp prizeRecordTmp){
		return prizeRecordTmpBaseDao.updatePrizeRecordTmp(prizeRecordTmp);
	}
	/**
	 * 批量更新(临时用户中奖记录)信息
	 * @param prizeRecordTmpList
	 * @return
	 */
	@Override
	public int updatePrizeRecordTmpBatch(List<PrizeRecordTmp> prizeRecordTmpList){
		return prizeRecordTmpBaseDao.updatePrizeRecordTmpBatch(prizeRecordTmpList);
	}
	/**
	 * 根据序列号删除(临时用户中奖记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePrizeRecordTmpLogic(java.math.BigInteger id){
		return prizeRecordTmpBaseDao.deletePrizeRecordTmpLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(临时用户中奖记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePrizeRecordTmpLogicBatch(List<java.math.BigInteger> idList){
		return prizeRecordTmpBaseDao.deletePrizeRecordTmpLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(临时用户中奖记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePrizeRecordTmp(java.math.BigInteger id){
//		return prizeRecordTmpBaseDao.deletePrizeRecordTmp(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(临时用户中奖记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePrizeRecordTmpBatch(List<java.math.BigInteger> idList){
//		return prizeRecordTmpBaseDao.deletePrizeRecordTmpBatch(idList);
//	}
	
}
