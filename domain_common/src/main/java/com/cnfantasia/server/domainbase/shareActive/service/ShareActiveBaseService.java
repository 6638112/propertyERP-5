package com.cnfantasia.server.domainbase.shareActive.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.shareActive.dao.IShareActiveBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.shareActive.entity.ShareActive;

/**
 * 描述:(分享活动) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class ShareActiveBaseService implements IShareActiveBaseService{
	
	private IShareActiveBaseDao shareActiveBaseDao;
	public void setShareActiveBaseDao(IShareActiveBaseDao shareActiveBaseDao) {
		this.shareActiveBaseDao = shareActiveBaseDao;
	}
	/**
	 * 根据条件查询(分享活动)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<ShareActive> getShareActiveByCondition(Map<String,Object> paramMap){
		return shareActiveBaseDao.selectShareActiveByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(分享活动)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<ShareActive> getShareActiveByConditionDim(Map<String,Object> paramMap){
		return shareActiveBaseDao.selectShareActiveByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(分享活动)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<ShareActive> getShareActiveByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return shareActiveBaseDao.selectShareActiveByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(分享活动)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<ShareActive> getShareActiveByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return shareActiveBaseDao.selectShareActiveByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(分享活动)信息
	 * @param id
	 * @return
	 */
	@Override
	public ShareActive getShareActiveBySeqId(java.math.BigInteger id){
		return shareActiveBaseDao.selectShareActiveBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(分享活动)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getShareActiveCount(Map<String,Object> paramMap){
		return shareActiveBaseDao.selectShareActiveCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(分享活动)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getShareActiveCountDim(Map<String,Object> paramMap){
		return shareActiveBaseDao.selectShareActiveCount(paramMap,true);
	}
	/**
	 * 往(分享活动)新增一条记录
	 * @param shareActive
	 * @return
	 */
	@Override
	public int insertShareActive(ShareActive shareActive){
		return shareActiveBaseDao.insertShareActive(shareActive);
	}
	/**
	 * 批量新增(分享活动)
	 * @param shareActiveList
	 * @return
	 */
	@Override
	public int insertShareActiveBatch(List<ShareActive> shareActiveList){
		return shareActiveBaseDao.insertShareActiveBatch(shareActiveList);
	}
	/**
	 * 更新(分享活动)信息
	 * @param shareActive
	 * @return
	 */
	@Override
	public int updateShareActive(ShareActive shareActive){
		return shareActiveBaseDao.updateShareActive(shareActive);
	}
	/**
	 * 批量更新(分享活动)信息
	 * @param shareActiveList
	 * @return
	 */
	@Override
	public int updateShareActiveBatch(List<ShareActive> shareActiveList){
		return shareActiveBaseDao.updateShareActiveBatch(shareActiveList);
	}
	/**
	 * 根据序列号删除(分享活动)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteShareActiveLogic(java.math.BigInteger id){
		return shareActiveBaseDao.deleteShareActiveLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(分享活动)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteShareActiveLogicBatch(List<java.math.BigInteger> idList){
		return shareActiveBaseDao.deleteShareActiveLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(分享活动)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteShareActive(java.math.BigInteger id){
//		return shareActiveBaseDao.deleteShareActive(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(分享活动)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteShareActiveBatch(List<java.math.BigInteger> idList){
//		return shareActiveBaseDao.deleteShareActiveBatch(idList);
//	}
	
}
