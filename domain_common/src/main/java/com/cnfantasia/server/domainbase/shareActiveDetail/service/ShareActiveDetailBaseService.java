package com.cnfantasia.server.domainbase.shareActiveDetail.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.shareActiveDetail.dao.IShareActiveDetailBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.shareActiveDetail.entity.ShareActiveDetail;

/**
 * 描述:(分享活动详情表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class ShareActiveDetailBaseService implements IShareActiveDetailBaseService{
	
	private IShareActiveDetailBaseDao shareActiveDetailBaseDao;
	public void setShareActiveDetailBaseDao(IShareActiveDetailBaseDao shareActiveDetailBaseDao) {
		this.shareActiveDetailBaseDao = shareActiveDetailBaseDao;
	}
	/**
	 * 根据条件查询(分享活动详情表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<ShareActiveDetail> getShareActiveDetailByCondition(Map<String,Object> paramMap){
		return shareActiveDetailBaseDao.selectShareActiveDetailByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(分享活动详情表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<ShareActiveDetail> getShareActiveDetailByConditionDim(Map<String,Object> paramMap){
		return shareActiveDetailBaseDao.selectShareActiveDetailByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(分享活动详情表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<ShareActiveDetail> getShareActiveDetailByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return shareActiveDetailBaseDao.selectShareActiveDetailByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(分享活动详情表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<ShareActiveDetail> getShareActiveDetailByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return shareActiveDetailBaseDao.selectShareActiveDetailByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(分享活动详情表)信息
	 * @param id
	 * @return
	 */
	@Override
	public ShareActiveDetail getShareActiveDetailBySeqId(java.math.BigInteger id){
		return shareActiveDetailBaseDao.selectShareActiveDetailBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(分享活动详情表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getShareActiveDetailCount(Map<String,Object> paramMap){
		return shareActiveDetailBaseDao.selectShareActiveDetailCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(分享活动详情表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getShareActiveDetailCountDim(Map<String,Object> paramMap){
		return shareActiveDetailBaseDao.selectShareActiveDetailCount(paramMap,true);
	}
	/**
	 * 往(分享活动详情表)新增一条记录
	 * @param shareActiveDetail
	 * @return
	 */
	@Override
	public int insertShareActiveDetail(ShareActiveDetail shareActiveDetail){
		return shareActiveDetailBaseDao.insertShareActiveDetail(shareActiveDetail);
	}
	/**
	 * 批量新增(分享活动详情表)
	 * @param shareActiveDetailList
	 * @return
	 */
	@Override
	public int insertShareActiveDetailBatch(List<ShareActiveDetail> shareActiveDetailList){
		return shareActiveDetailBaseDao.insertShareActiveDetailBatch(shareActiveDetailList);
	}
	/**
	 * 更新(分享活动详情表)信息
	 * @param shareActiveDetail
	 * @return
	 */
	@Override
	public int updateShareActiveDetail(ShareActiveDetail shareActiveDetail){
		return shareActiveDetailBaseDao.updateShareActiveDetail(shareActiveDetail);
	}
	/**
	 * 批量更新(分享活动详情表)信息
	 * @param shareActiveDetailList
	 * @return
	 */
	@Override
	public int updateShareActiveDetailBatch(List<ShareActiveDetail> shareActiveDetailList){
		return shareActiveDetailBaseDao.updateShareActiveDetailBatch(shareActiveDetailList);
	}
	/**
	 * 根据序列号删除(分享活动详情表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteShareActiveDetailLogic(java.math.BigInteger id){
		return shareActiveDetailBaseDao.deleteShareActiveDetailLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(分享活动详情表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteShareActiveDetailLogicBatch(List<java.math.BigInteger> idList){
		return shareActiveDetailBaseDao.deleteShareActiveDetailLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(分享活动详情表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteShareActiveDetail(java.math.BigInteger id){
//		return shareActiveDetailBaseDao.deleteShareActiveDetail(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(分享活动详情表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteShareActiveDetailBatch(List<java.math.BigInteger> idList){
//		return shareActiveDetailBaseDao.deleteShareActiveDetailBatch(idList);
//	}
	
}
