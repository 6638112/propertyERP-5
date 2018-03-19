package com.cnfantasia.server.domainbase.prizeRecordTmpData.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.prizeRecordTmpData.dao.IPrizeRecordTmpDataBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.prizeRecordTmpData.entity.PrizeRecordTmpData;

/**
 * 描述:() 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PrizeRecordTmpDataBaseService implements IPrizeRecordTmpDataBaseService{
	
	private IPrizeRecordTmpDataBaseDao prizeRecordTmpDataBaseDao;
	public void setPrizeRecordTmpDataBaseDao(IPrizeRecordTmpDataBaseDao prizeRecordTmpDataBaseDao) {
		this.prizeRecordTmpDataBaseDao = prizeRecordTmpDataBaseDao;
	}
	/**
	 * 根据条件查询()信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PrizeRecordTmpData> getPrizeRecordTmpDataByCondition(Map<String,Object> paramMap){
		return prizeRecordTmpDataBaseDao.selectPrizeRecordTmpDataByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询()信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PrizeRecordTmpData> getPrizeRecordTmpDataByConditionDim(Map<String,Object> paramMap){
		return prizeRecordTmpDataBaseDao.selectPrizeRecordTmpDataByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询()信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PrizeRecordTmpData> getPrizeRecordTmpDataByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return prizeRecordTmpDataBaseDao.selectPrizeRecordTmpDataByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询()信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PrizeRecordTmpData> getPrizeRecordTmpDataByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return prizeRecordTmpDataBaseDao.selectPrizeRecordTmpDataByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	@Override
	public PrizeRecordTmpData getPrizeRecordTmpDataBySeqId(java.math.BigInteger id){
		return prizeRecordTmpDataBaseDao.selectPrizeRecordTmpDataBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的()记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPrizeRecordTmpDataCount(Map<String,Object> paramMap){
		return prizeRecordTmpDataBaseDao.selectPrizeRecordTmpDataCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的()记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPrizeRecordTmpDataCountDim(Map<String,Object> paramMap){
		return prizeRecordTmpDataBaseDao.selectPrizeRecordTmpDataCount(paramMap,true);
	}
	/**
	 * 往()新增一条记录
	 * @param prizeRecordTmpData
	 * @return
	 */
	@Override
	public int insertPrizeRecordTmpData(PrizeRecordTmpData prizeRecordTmpData){
		return prizeRecordTmpDataBaseDao.insertPrizeRecordTmpData(prizeRecordTmpData);
	}
	/**
	 * 批量新增()
	 * @param prizeRecordTmpDataList
	 * @return
	 */
	@Override
	public int insertPrizeRecordTmpDataBatch(List<PrizeRecordTmpData> prizeRecordTmpDataList){
		return prizeRecordTmpDataBaseDao.insertPrizeRecordTmpDataBatch(prizeRecordTmpDataList);
	}
	/**
	 * 更新()信息
	 * @param prizeRecordTmpData
	 * @return
	 */
	@Override
	public int updatePrizeRecordTmpData(PrizeRecordTmpData prizeRecordTmpData){
		return prizeRecordTmpDataBaseDao.updatePrizeRecordTmpData(prizeRecordTmpData);
	}
	/**
	 * 批量更新()信息
	 * @param prizeRecordTmpDataList
	 * @return
	 */
	@Override
	public int updatePrizeRecordTmpDataBatch(List<PrizeRecordTmpData> prizeRecordTmpDataList){
		return prizeRecordTmpDataBaseDao.updatePrizeRecordTmpDataBatch(prizeRecordTmpDataList);
	}
	/**
	 * 根据序列号删除()信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePrizeRecordTmpDataLogic(java.math.BigInteger id){
		return prizeRecordTmpDataBaseDao.deletePrizeRecordTmpDataLogic(id);
	}
	
	/**
	 * 根据序列号批量删除()信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePrizeRecordTmpDataLogicBatch(List<java.math.BigInteger> idList){
		return prizeRecordTmpDataBaseDao.deletePrizeRecordTmpDataLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除()信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePrizeRecordTmpData(java.math.BigInteger id){
//		return prizeRecordTmpDataBaseDao.deletePrizeRecordTmpData(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除()信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePrizeRecordTmpDataBatch(List<java.math.BigInteger> idList){
//		return prizeRecordTmpDataBaseDao.deletePrizeRecordTmpDataBatch(idList);
//	}
	
}
