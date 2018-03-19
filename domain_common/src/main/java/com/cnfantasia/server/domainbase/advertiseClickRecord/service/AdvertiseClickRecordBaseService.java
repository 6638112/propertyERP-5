package com.cnfantasia.server.domainbase.advertiseClickRecord.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.advertiseClickRecord.dao.IAdvertiseClickRecordBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.advertiseClickRecord.entity.AdvertiseClickRecord;

/**
 * 描述:(广告点击记录表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class AdvertiseClickRecordBaseService implements IAdvertiseClickRecordBaseService{
	
	private IAdvertiseClickRecordBaseDao advertiseClickRecordBaseDao;
	public void setAdvertiseClickRecordBaseDao(IAdvertiseClickRecordBaseDao advertiseClickRecordBaseDao) {
		this.advertiseClickRecordBaseDao = advertiseClickRecordBaseDao;
	}
	/**
	 * 根据条件查询(广告点击记录表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<AdvertiseClickRecord> getAdvertiseClickRecordByCondition(Map<String,Object> paramMap){
		return advertiseClickRecordBaseDao.selectAdvertiseClickRecordByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(广告点击记录表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<AdvertiseClickRecord> getAdvertiseClickRecordByConditionDim(Map<String,Object> paramMap){
		return advertiseClickRecordBaseDao.selectAdvertiseClickRecordByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(广告点击记录表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<AdvertiseClickRecord> getAdvertiseClickRecordByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return advertiseClickRecordBaseDao.selectAdvertiseClickRecordByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(广告点击记录表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<AdvertiseClickRecord> getAdvertiseClickRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return advertiseClickRecordBaseDao.selectAdvertiseClickRecordByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(广告点击记录表)信息
	 * @param id
	 * @return
	 */
	@Override
	public AdvertiseClickRecord getAdvertiseClickRecordBySeqId(java.math.BigInteger id){
		return advertiseClickRecordBaseDao.selectAdvertiseClickRecordBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(广告点击记录表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getAdvertiseClickRecordCount(Map<String,Object> paramMap){
		return advertiseClickRecordBaseDao.selectAdvertiseClickRecordCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(广告点击记录表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getAdvertiseClickRecordCountDim(Map<String,Object> paramMap){
		return advertiseClickRecordBaseDao.selectAdvertiseClickRecordCount(paramMap,true);
	}
	/**
	 * 往(广告点击记录表)新增一条记录
	 * @param advertiseClickRecord
	 * @return
	 */
	@Override
	public int insertAdvertiseClickRecord(AdvertiseClickRecord advertiseClickRecord){
		return advertiseClickRecordBaseDao.insertAdvertiseClickRecord(advertiseClickRecord);
	}
	/**
	 * 批量新增(广告点击记录表)
	 * @param advertiseClickRecordList
	 * @return
	 */
	@Override
	public int insertAdvertiseClickRecordBatch(List<AdvertiseClickRecord> advertiseClickRecordList){
		return advertiseClickRecordBaseDao.insertAdvertiseClickRecordBatch(advertiseClickRecordList);
	}
	/**
	 * 更新(广告点击记录表)信息
	 * @param advertiseClickRecord
	 * @return
	 */
	@Override
	public int updateAdvertiseClickRecord(AdvertiseClickRecord advertiseClickRecord){
		return advertiseClickRecordBaseDao.updateAdvertiseClickRecord(advertiseClickRecord);
	}
	/**
	 * 批量更新(广告点击记录表)信息
	 * @param advertiseClickRecordList
	 * @return
	 */
	@Override
	public int updateAdvertiseClickRecordBatch(List<AdvertiseClickRecord> advertiseClickRecordList){
		return advertiseClickRecordBaseDao.updateAdvertiseClickRecordBatch(advertiseClickRecordList);
	}
	/**
	 * 根据序列号删除(广告点击记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteAdvertiseClickRecordLogic(java.math.BigInteger id){
		return advertiseClickRecordBaseDao.deleteAdvertiseClickRecordLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(广告点击记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteAdvertiseClickRecordLogicBatch(List<java.math.BigInteger> idList){
		return advertiseClickRecordBaseDao.deleteAdvertiseClickRecordLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(广告点击记录表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteAdvertiseClickRecord(java.math.BigInteger id){
//		return advertiseClickRecordBaseDao.deleteAdvertiseClickRecord(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(广告点击记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteAdvertiseClickRecordBatch(List<java.math.BigInteger> idList){
//		return advertiseClickRecordBaseDao.deleteAdvertiseClickRecordBatch(idList);
//	}
	
}
