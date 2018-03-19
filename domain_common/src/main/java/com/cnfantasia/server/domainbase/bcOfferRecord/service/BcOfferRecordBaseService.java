package com.cnfantasia.server.domainbase.bcOfferRecord.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.bcOfferRecord.dao.IBcOfferRecordBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.bcOfferRecord.entity.BcOfferRecord;

/**
 * 描述:(发盘记录) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class BcOfferRecordBaseService implements IBcOfferRecordBaseService{
	
	private IBcOfferRecordBaseDao bcOfferRecordBaseDao;
	public void setBcOfferRecordBaseDao(IBcOfferRecordBaseDao bcOfferRecordBaseDao) {
		this.bcOfferRecordBaseDao = bcOfferRecordBaseDao;
	}
	/**
	 * 根据条件查询(发盘记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<BcOfferRecord> getBcOfferRecordByCondition(Map<String,Object> paramMap){
		return bcOfferRecordBaseDao.selectBcOfferRecordByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(发盘记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<BcOfferRecord> getBcOfferRecordByConditionDim(Map<String,Object> paramMap){
		return bcOfferRecordBaseDao.selectBcOfferRecordByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(发盘记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<BcOfferRecord> getBcOfferRecordByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return bcOfferRecordBaseDao.selectBcOfferRecordByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(发盘记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<BcOfferRecord> getBcOfferRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return bcOfferRecordBaseDao.selectBcOfferRecordByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(发盘记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public BcOfferRecord getBcOfferRecordBySeqId(java.math.BigInteger id){
		return bcOfferRecordBaseDao.selectBcOfferRecordBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(发盘记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getBcOfferRecordCount(Map<String,Object> paramMap){
		return bcOfferRecordBaseDao.selectBcOfferRecordCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(发盘记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getBcOfferRecordCountDim(Map<String,Object> paramMap){
		return bcOfferRecordBaseDao.selectBcOfferRecordCount(paramMap,true);
	}
	/**
	 * 往(发盘记录)新增一条记录
	 * @param bcOfferRecord
	 * @return
	 */
	@Override
	public int insertBcOfferRecord(BcOfferRecord bcOfferRecord){
		return bcOfferRecordBaseDao.insertBcOfferRecord(bcOfferRecord);
	}
	/**
	 * 批量新增(发盘记录)
	 * @param bcOfferRecordList
	 * @return
	 */
	@Override
	public int insertBcOfferRecordBatch(List<BcOfferRecord> bcOfferRecordList){
		return bcOfferRecordBaseDao.insertBcOfferRecordBatch(bcOfferRecordList);
	}
	/**
	 * 更新(发盘记录)信息
	 * @param bcOfferRecord
	 * @return
	 */
	@Override
	public int updateBcOfferRecord(BcOfferRecord bcOfferRecord){
		return bcOfferRecordBaseDao.updateBcOfferRecord(bcOfferRecord);
	}
	/**
	 * 批量更新(发盘记录)信息
	 * @param bcOfferRecordList
	 * @return
	 */
	@Override
	public int updateBcOfferRecordBatch(List<BcOfferRecord> bcOfferRecordList){
		return bcOfferRecordBaseDao.updateBcOfferRecordBatch(bcOfferRecordList);
	}
	/**
	 * 根据序列号删除(发盘记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteBcOfferRecordLogic(java.math.BigInteger id){
		return bcOfferRecordBaseDao.deleteBcOfferRecordLogic(id);
	}
	 */
	/**
	 * 根据序列号批量删除(发盘记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteBcOfferRecordLogicBatch(List<java.math.BigInteger> idList){
		return bcOfferRecordBaseDao.deleteBcOfferRecordLogicBatch(idList);
	}
	 */
//	/**
//	 * 根据序列号删除(发盘记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteBcOfferRecord(java.math.BigInteger id){
//		return bcOfferRecordBaseDao.deleteBcOfferRecord(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(发盘记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteBcOfferRecordBatch(List<java.math.BigInteger> idList){
//		return bcOfferRecordBaseDao.deleteBcOfferRecordBatch(idList);
//	}
	
}
