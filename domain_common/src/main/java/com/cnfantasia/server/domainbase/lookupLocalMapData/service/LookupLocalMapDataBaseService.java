package com.cnfantasia.server.domainbase.lookupLocalMapData.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.lookupLocalMapData.dao.ILookupLocalMapDataBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.lookupLocalMapData.entity.LookupLocalMapData;

/**
 * 描述:(本地地址信息数据) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class LookupLocalMapDataBaseService implements ILookupLocalMapDataBaseService{
	
	private ILookupLocalMapDataBaseDao lookupLocalMapDataBaseDao;
	public void setLookupLocalMapDataBaseDao(ILookupLocalMapDataBaseDao lookupLocalMapDataBaseDao) {
		this.lookupLocalMapDataBaseDao = lookupLocalMapDataBaseDao;
	}
	/**
	 * 根据条件查询(本地地址信息数据)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<LookupLocalMapData> getLookupLocalMapDataByCondition(Map<String,Object> paramMap){
		return lookupLocalMapDataBaseDao.selectLookupLocalMapDataByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(本地地址信息数据)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<LookupLocalMapData> getLookupLocalMapDataByConditionDim(Map<String,Object> paramMap){
		return lookupLocalMapDataBaseDao.selectLookupLocalMapDataByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(本地地址信息数据)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<LookupLocalMapData> getLookupLocalMapDataByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return lookupLocalMapDataBaseDao.selectLookupLocalMapDataByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(本地地址信息数据)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<LookupLocalMapData> getLookupLocalMapDataByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return lookupLocalMapDataBaseDao.selectLookupLocalMapDataByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(本地地址信息数据)信息
	 * @param id
	 * @return
	 */
	@Override
	public LookupLocalMapData getLookupLocalMapDataBySeqId(java.math.BigInteger id){
		return lookupLocalMapDataBaseDao.selectLookupLocalMapDataBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(本地地址信息数据)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getLookupLocalMapDataCount(Map<String,Object> paramMap){
		return lookupLocalMapDataBaseDao.selectLookupLocalMapDataCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(本地地址信息数据)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getLookupLocalMapDataCountDim(Map<String,Object> paramMap){
		return lookupLocalMapDataBaseDao.selectLookupLocalMapDataCount(paramMap,true);
	}
	/**
	 * 往(本地地址信息数据)新增一条记录
	 * @param lookupLocalMapData
	 * @return
	 */
	@Override
	public int insertLookupLocalMapData(LookupLocalMapData lookupLocalMapData){
		return lookupLocalMapDataBaseDao.insertLookupLocalMapData(lookupLocalMapData);
	}
	/**
	 * 批量新增(本地地址信息数据)
	 * @param lookupLocalMapDataList
	 * @return
	 */
	@Override
	public int insertLookupLocalMapDataBatch(List<LookupLocalMapData> lookupLocalMapDataList){
		return lookupLocalMapDataBaseDao.insertLookupLocalMapDataBatch(lookupLocalMapDataList);
	}
	/**
	 * 更新(本地地址信息数据)信息
	 * @param lookupLocalMapData
	 * @return
	 */
	@Override
	public int updateLookupLocalMapData(LookupLocalMapData lookupLocalMapData){
		return lookupLocalMapDataBaseDao.updateLookupLocalMapData(lookupLocalMapData);
	}
	/**
	 * 批量更新(本地地址信息数据)信息
	 * @param lookupLocalMapDataList
	 * @return
	 */
	@Override
	public int updateLookupLocalMapDataBatch(List<LookupLocalMapData> lookupLocalMapDataList){
		return lookupLocalMapDataBaseDao.updateLookupLocalMapDataBatch(lookupLocalMapDataList);
	}
	/**
	 * 根据序列号删除(本地地址信息数据)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteLookupLocalMapDataLogic(java.math.BigInteger id){
		return lookupLocalMapDataBaseDao.deleteLookupLocalMapDataLogic(id);
	}
	 */
	/**
	 * 根据序列号批量删除(本地地址信息数据)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteLookupLocalMapDataLogicBatch(List<java.math.BigInteger> idList){
		return lookupLocalMapDataBaseDao.deleteLookupLocalMapDataLogicBatch(idList);
	}
	 */
//	/**
//	 * 根据序列号删除(本地地址信息数据)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteLookupLocalMapData(java.math.BigInteger id){
//		return lookupLocalMapDataBaseDao.deleteLookupLocalMapData(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(本地地址信息数据)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteLookupLocalMapDataBatch(List<java.math.BigInteger> idList){
//		return lookupLocalMapDataBaseDao.deleteLookupLocalMapDataBatch(idList);
//	}
	
}
