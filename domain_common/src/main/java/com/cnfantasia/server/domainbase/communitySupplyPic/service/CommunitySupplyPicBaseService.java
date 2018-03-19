package com.cnfantasia.server.domainbase.communitySupplyPic.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.communitySupplyPic.dao.ICommunitySupplyPicBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communitySupplyPic.entity.CommunitySupplyPic;

/**
 * 描述:(商家图片) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommunitySupplyPicBaseService implements ICommunitySupplyPicBaseService{
	
	private ICommunitySupplyPicBaseDao communitySupplyPicBaseDao;
	public void setCommunitySupplyPicBaseDao(ICommunitySupplyPicBaseDao communitySupplyPicBaseDao) {
		this.communitySupplyPicBaseDao = communitySupplyPicBaseDao;
	}
	/**
	 * 根据条件查询(商家图片)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunitySupplyPic> getCommunitySupplyPicByCondition(Map<String,Object> paramMap){
		return communitySupplyPicBaseDao.selectCommunitySupplyPicByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(商家图片)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunitySupplyPic> getCommunitySupplyPicByConditionDim(Map<String,Object> paramMap){
		return communitySupplyPicBaseDao.selectCommunitySupplyPicByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(商家图片)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunitySupplyPic> getCommunitySupplyPicByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return communitySupplyPicBaseDao.selectCommunitySupplyPicByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(商家图片)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunitySupplyPic> getCommunitySupplyPicByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return communitySupplyPicBaseDao.selectCommunitySupplyPicByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(商家图片)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunitySupplyPic getCommunitySupplyPicBySeqId(java.math.BigInteger id){
		return communitySupplyPicBaseDao.selectCommunitySupplyPicBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(商家图片)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunitySupplyPicCount(Map<String,Object> paramMap){
		return communitySupplyPicBaseDao.selectCommunitySupplyPicCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(商家图片)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunitySupplyPicCountDim(Map<String,Object> paramMap){
		return communitySupplyPicBaseDao.selectCommunitySupplyPicCount(paramMap,true);
	}
	/**
	 * 往(商家图片)新增一条记录
	 * @param communitySupplyPic
	 * @return
	 */
	@Override
	public int insertCommunitySupplyPic(CommunitySupplyPic communitySupplyPic){
		return communitySupplyPicBaseDao.insertCommunitySupplyPic(communitySupplyPic);
	}
	/**
	 * 批量新增(商家图片)
	 * @param communitySupplyPicList
	 * @return
	 */
	@Override
	public int insertCommunitySupplyPicBatch(List<CommunitySupplyPic> communitySupplyPicList){
		return communitySupplyPicBaseDao.insertCommunitySupplyPicBatch(communitySupplyPicList);
	}
	/**
	 * 更新(商家图片)信息
	 * @param communitySupplyPic
	 * @return
	 */
	@Override
	public int updateCommunitySupplyPic(CommunitySupplyPic communitySupplyPic){
		return communitySupplyPicBaseDao.updateCommunitySupplyPic(communitySupplyPic);
	}
	/**
	 * 批量更新(商家图片)信息
	 * @param communitySupplyPicList
	 * @return
	 */
	@Override
	public int updateCommunitySupplyPicBatch(List<CommunitySupplyPic> communitySupplyPicList){
		return communitySupplyPicBaseDao.updateCommunitySupplyPicBatch(communitySupplyPicList);
	}
	/**
	 * 根据序列号删除(商家图片)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyPicLogic(java.math.BigInteger id){
		return communitySupplyPicBaseDao.deleteCommunitySupplyPicLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(商家图片)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyPicLogicBatch(List<java.math.BigInteger> idList){
		return communitySupplyPicBaseDao.deleteCommunitySupplyPicLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(商家图片)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyPic(java.math.BigInteger id){
//		return communitySupplyPicBaseDao.deleteCommunitySupplyPic(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(商家图片)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyPicBatch(List<java.math.BigInteger> idList){
//		return communitySupplyPicBaseDao.deleteCommunitySupplyPicBatch(idList);
//	}
	
}
