package com.cnfantasia.server.domainbase.cloudKeyUserData.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.cloudKeyUserData.dao.ICloudKeyUserDataBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.cloudKeyUserData.entity.CloudKeyUserData;

/**
 * 描述:(业主门禁认证（可配置）信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CloudKeyUserDataBaseService implements ICloudKeyUserDataBaseService{
	
	private ICloudKeyUserDataBaseDao cloudKeyUserDataBaseDao;
	public void setCloudKeyUserDataBaseDao(ICloudKeyUserDataBaseDao cloudKeyUserDataBaseDao) {
		this.cloudKeyUserDataBaseDao = cloudKeyUserDataBaseDao;
	}
	/**
	 * 根据条件查询(业主门禁认证（可配置）信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CloudKeyUserData> getCloudKeyUserDataByCondition(Map<String,Object> paramMap){
		return cloudKeyUserDataBaseDao.selectCloudKeyUserDataByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(业主门禁认证（可配置）信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CloudKeyUserData> getCloudKeyUserDataByConditionDim(Map<String,Object> paramMap){
		return cloudKeyUserDataBaseDao.selectCloudKeyUserDataByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(业主门禁认证（可配置）信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CloudKeyUserData> getCloudKeyUserDataByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return cloudKeyUserDataBaseDao.selectCloudKeyUserDataByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(业主门禁认证（可配置）信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CloudKeyUserData> getCloudKeyUserDataByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return cloudKeyUserDataBaseDao.selectCloudKeyUserDataByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(业主门禁认证（可配置）信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public CloudKeyUserData getCloudKeyUserDataBySeqId(java.math.BigInteger id){
		return cloudKeyUserDataBaseDao.selectCloudKeyUserDataBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(业主门禁认证（可配置）信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCloudKeyUserDataCount(Map<String,Object> paramMap){
		return cloudKeyUserDataBaseDao.selectCloudKeyUserDataCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(业主门禁认证（可配置）信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCloudKeyUserDataCountDim(Map<String,Object> paramMap){
		return cloudKeyUserDataBaseDao.selectCloudKeyUserDataCount(paramMap,true);
	}
	/**
	 * 往(业主门禁认证（可配置）信息)新增一条记录
	 * @param cloudKeyUserData
	 * @return
	 */
	@Override
	public int insertCloudKeyUserData(CloudKeyUserData cloudKeyUserData){
		return cloudKeyUserDataBaseDao.insertCloudKeyUserData(cloudKeyUserData);
	}
	/**
	 * 批量新增(业主门禁认证（可配置）信息)
	 * @param cloudKeyUserDataList
	 * @return
	 */
	@Override
	public int insertCloudKeyUserDataBatch(List<CloudKeyUserData> cloudKeyUserDataList){
		return cloudKeyUserDataBaseDao.insertCloudKeyUserDataBatch(cloudKeyUserDataList);
	}
	/**
	 * 更新(业主门禁认证（可配置）信息)信息
	 * @param cloudKeyUserData
	 * @return
	 */
	@Override
	public int updateCloudKeyUserData(CloudKeyUserData cloudKeyUserData){
		return cloudKeyUserDataBaseDao.updateCloudKeyUserData(cloudKeyUserData);
	}
	/**
	 * 批量更新(业主门禁认证（可配置）信息)信息
	 * @param cloudKeyUserDataList
	 * @return
	 */
	@Override
	public int updateCloudKeyUserDataBatch(List<CloudKeyUserData> cloudKeyUserDataList){
		return cloudKeyUserDataBaseDao.updateCloudKeyUserDataBatch(cloudKeyUserDataList);
	}
	/**
	 * 根据序列号删除(业主门禁认证（可配置）信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteCloudKeyUserDataLogic(java.math.BigInteger id){
		return cloudKeyUserDataBaseDao.deleteCloudKeyUserDataLogic(id);
	}
	 */
	/**
	 * 根据序列号批量删除(业主门禁认证（可配置）信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteCloudKeyUserDataLogicBatch(List<java.math.BigInteger> idList){
		return cloudKeyUserDataBaseDao.deleteCloudKeyUserDataLogicBatch(idList);
	}
	 */
//	/**
//	 * 根据序列号删除(业主门禁认证（可配置）信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCloudKeyUserData(java.math.BigInteger id){
//		return cloudKeyUserDataBaseDao.deleteCloudKeyUserData(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(业主门禁认证（可配置）信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCloudKeyUserDataBatch(List<java.math.BigInteger> idList){
//		return cloudKeyUserDataBaseDao.deleteCloudKeyUserDataBatch(idList);
//	}
	
}
