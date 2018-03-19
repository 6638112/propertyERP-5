package com.cnfantasia.server.domainbase.communityExchangePic.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.communityExchangePic.dao.ICommunityExchangePicBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communityExchangePic.entity.CommunityExchangePic;

/**
 * 描述:(换一换图片表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommunityExchangePicBaseService implements ICommunityExchangePicBaseService{
	
	private ICommunityExchangePicBaseDao communityExchangePicBaseDao;
	public void setCommunityExchangePicBaseDao(ICommunityExchangePicBaseDao communityExchangePicBaseDao) {
		this.communityExchangePicBaseDao = communityExchangePicBaseDao;
	}
	/**
	 * 根据条件查询(换一换图片表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunityExchangePic> getCommunityExchangePicByCondition(Map<String,Object> paramMap){
		return communityExchangePicBaseDao.selectCommunityExchangePicByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(换一换图片表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunityExchangePic> getCommunityExchangePicByConditionDim(Map<String,Object> paramMap){
		return communityExchangePicBaseDao.selectCommunityExchangePicByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(换一换图片表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunityExchangePic> getCommunityExchangePicByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return communityExchangePicBaseDao.selectCommunityExchangePicByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(换一换图片表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunityExchangePic> getCommunityExchangePicByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return communityExchangePicBaseDao.selectCommunityExchangePicByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(换一换图片表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunityExchangePic getCommunityExchangePicBySeqId(java.math.BigInteger id){
		return communityExchangePicBaseDao.selectCommunityExchangePicBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(换一换图片表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunityExchangePicCount(Map<String,Object> paramMap){
		return communityExchangePicBaseDao.selectCommunityExchangePicCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(换一换图片表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunityExchangePicCountDim(Map<String,Object> paramMap){
		return communityExchangePicBaseDao.selectCommunityExchangePicCount(paramMap,true);
	}
	/**
	 * 往(换一换图片表)新增一条记录
	 * @param communityExchangePic
	 * @return
	 */
	@Override
	public int insertCommunityExchangePic(CommunityExchangePic communityExchangePic){
		return communityExchangePicBaseDao.insertCommunityExchangePic(communityExchangePic);
	}
	/**
	 * 批量新增(换一换图片表)
	 * @param communityExchangePicList
	 * @return
	 */
	@Override
	public int insertCommunityExchangePicBatch(List<CommunityExchangePic> communityExchangePicList){
		return communityExchangePicBaseDao.insertCommunityExchangePicBatch(communityExchangePicList);
	}
	/**
	 * 更新(换一换图片表)信息
	 * @param communityExchangePic
	 * @return
	 */
	@Override
	public int updateCommunityExchangePic(CommunityExchangePic communityExchangePic){
		return communityExchangePicBaseDao.updateCommunityExchangePic(communityExchangePic);
	}
	/**
	 * 批量更新(换一换图片表)信息
	 * @param communityExchangePicList
	 * @return
	 */
	@Override
	public int updateCommunityExchangePicBatch(List<CommunityExchangePic> communityExchangePicList){
		return communityExchangePicBaseDao.updateCommunityExchangePicBatch(communityExchangePicList);
	}
	/**
	 * 根据序列号删除(换一换图片表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunityExchangePicLogic(java.math.BigInteger id){
		return communityExchangePicBaseDao.deleteCommunityExchangePicLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(换一换图片表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunityExchangePicLogicBatch(List<java.math.BigInteger> idList){
		return communityExchangePicBaseDao.deleteCommunityExchangePicLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(换一换图片表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityExchangePic(java.math.BigInteger id){
//		return communityExchangePicBaseDao.deleteCommunityExchangePic(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(换一换图片表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityExchangePicBatch(List<java.math.BigInteger> idList){
//		return communityExchangePicBaseDao.deleteCommunityExchangePicBatch(idList);
//	}
	
}
