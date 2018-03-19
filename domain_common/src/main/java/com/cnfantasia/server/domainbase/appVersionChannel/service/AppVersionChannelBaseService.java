package com.cnfantasia.server.domainbase.appVersionChannel.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.appVersionChannel.dao.IAppVersionChannelBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.appVersionChannel.entity.AppVersionChannel;

/**
 * 描述:(应用版本不同渠道信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class AppVersionChannelBaseService implements IAppVersionChannelBaseService{
	
	private IAppVersionChannelBaseDao appVersionChannelBaseDao;
	public void setAppVersionChannelBaseDao(IAppVersionChannelBaseDao appVersionChannelBaseDao) {
		this.appVersionChannelBaseDao = appVersionChannelBaseDao;
	}
	/**
	 * 根据条件查询(应用版本不同渠道信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<AppVersionChannel> getAppVersionChannelByCondition(Map<String,Object> paramMap){
		return appVersionChannelBaseDao.selectAppVersionChannelByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(应用版本不同渠道信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<AppVersionChannel> getAppVersionChannelByConditionDim(Map<String,Object> paramMap){
		return appVersionChannelBaseDao.selectAppVersionChannelByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(应用版本不同渠道信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<AppVersionChannel> getAppVersionChannelByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return appVersionChannelBaseDao.selectAppVersionChannelByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(应用版本不同渠道信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<AppVersionChannel> getAppVersionChannelByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return appVersionChannelBaseDao.selectAppVersionChannelByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(应用版本不同渠道信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public AppVersionChannel getAppVersionChannelBySeqId(java.math.BigInteger id){
		return appVersionChannelBaseDao.selectAppVersionChannelBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(应用版本不同渠道信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getAppVersionChannelCount(Map<String,Object> paramMap){
		return appVersionChannelBaseDao.selectAppVersionChannelCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(应用版本不同渠道信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getAppVersionChannelCountDim(Map<String,Object> paramMap){
		return appVersionChannelBaseDao.selectAppVersionChannelCount(paramMap,true);
	}
	/**
	 * 往(应用版本不同渠道信息)新增一条记录
	 * @param appVersionChannel
	 * @return
	 */
	@Override
	public int insertAppVersionChannel(AppVersionChannel appVersionChannel){
		return appVersionChannelBaseDao.insertAppVersionChannel(appVersionChannel);
	}
	/**
	 * 批量新增(应用版本不同渠道信息)
	 * @param appVersionChannelList
	 * @return
	 */
	@Override
	public int insertAppVersionChannelBatch(List<AppVersionChannel> appVersionChannelList){
		return appVersionChannelBaseDao.insertAppVersionChannelBatch(appVersionChannelList);
	}
	/**
	 * 更新(应用版本不同渠道信息)信息
	 * @param appVersionChannel
	 * @return
	 */
	@Override
	public int updateAppVersionChannel(AppVersionChannel appVersionChannel){
		return appVersionChannelBaseDao.updateAppVersionChannel(appVersionChannel);
	}
	/**
	 * 批量更新(应用版本不同渠道信息)信息
	 * @param appVersionChannelList
	 * @return
	 */
	@Override
	public int updateAppVersionChannelBatch(List<AppVersionChannel> appVersionChannelList){
		return appVersionChannelBaseDao.updateAppVersionChannelBatch(appVersionChannelList);
	}
	/**
	 * 根据序列号删除(应用版本不同渠道信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteAppVersionChannelLogic(java.math.BigInteger id){
		return appVersionChannelBaseDao.deleteAppVersionChannelLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(应用版本不同渠道信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteAppVersionChannelLogicBatch(List<java.math.BigInteger> idList){
		return appVersionChannelBaseDao.deleteAppVersionChannelLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(应用版本不同渠道信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteAppVersionChannel(java.math.BigInteger id){
//		return appVersionChannelBaseDao.deleteAppVersionChannel(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(应用版本不同渠道信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteAppVersionChannelBatch(List<java.math.BigInteger> idList){
//		return appVersionChannelBaseDao.deleteAppVersionChannelBatch(idList);
//	}
	
}
