package com.cnfantasia.server.domainbase.communitySupplyCompanyPic.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.communitySupplyCompanyPic.dao.ICommunitySupplyCompanyPicBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communitySupplyCompanyPic.entity.CommunitySupplyCompanyPic;

/**
 * 描述:(商家图片) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommunitySupplyCompanyPicBaseService implements ICommunitySupplyCompanyPicBaseService{
	
	private ICommunitySupplyCompanyPicBaseDao communitySupplyCompanyPicBaseDao;
	public void setCommunitySupplyCompanyPicBaseDao(ICommunitySupplyCompanyPicBaseDao communitySupplyCompanyPicBaseDao) {
		this.communitySupplyCompanyPicBaseDao = communitySupplyCompanyPicBaseDao;
	}
	/**
	 * 根据条件查询(商家图片)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunitySupplyCompanyPic> getCommunitySupplyCompanyPicByCondition(Map<String,Object> paramMap){
		return communitySupplyCompanyPicBaseDao.selectCommunitySupplyCompanyPicByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(商家图片)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunitySupplyCompanyPic> getCommunitySupplyCompanyPicByConditionDim(Map<String,Object> paramMap){
		return communitySupplyCompanyPicBaseDao.selectCommunitySupplyCompanyPicByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(商家图片)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunitySupplyCompanyPic> getCommunitySupplyCompanyPicByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return communitySupplyCompanyPicBaseDao.selectCommunitySupplyCompanyPicByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(商家图片)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunitySupplyCompanyPic> getCommunitySupplyCompanyPicByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return communitySupplyCompanyPicBaseDao.selectCommunitySupplyCompanyPicByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(商家图片)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunitySupplyCompanyPic getCommunitySupplyCompanyPicBySeqId(java.math.BigInteger id){
		return communitySupplyCompanyPicBaseDao.selectCommunitySupplyCompanyPicBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(商家图片)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunitySupplyCompanyPicCount(Map<String,Object> paramMap){
		return communitySupplyCompanyPicBaseDao.selectCommunitySupplyCompanyPicCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(商家图片)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunitySupplyCompanyPicCountDim(Map<String,Object> paramMap){
		return communitySupplyCompanyPicBaseDao.selectCommunitySupplyCompanyPicCount(paramMap,true);
	}
	/**
	 * 往(商家图片)新增一条记录
	 * @param communitySupplyCompanyPic
	 * @return
	 */
	@Override
	public int insertCommunitySupplyCompanyPic(CommunitySupplyCompanyPic communitySupplyCompanyPic){
		return communitySupplyCompanyPicBaseDao.insertCommunitySupplyCompanyPic(communitySupplyCompanyPic);
	}
	/**
	 * 批量新增(商家图片)
	 * @param communitySupplyCompanyPicList
	 * @return
	 */
	@Override
	public int insertCommunitySupplyCompanyPicBatch(List<CommunitySupplyCompanyPic> communitySupplyCompanyPicList){
		return communitySupplyCompanyPicBaseDao.insertCommunitySupplyCompanyPicBatch(communitySupplyCompanyPicList);
	}
	/**
	 * 更新(商家图片)信息
	 * @param communitySupplyCompanyPic
	 * @return
	 */
	@Override
	public int updateCommunitySupplyCompanyPic(CommunitySupplyCompanyPic communitySupplyCompanyPic){
		return communitySupplyCompanyPicBaseDao.updateCommunitySupplyCompanyPic(communitySupplyCompanyPic);
	}
	/**
	 * 批量更新(商家图片)信息
	 * @param communitySupplyCompanyPicList
	 * @return
	 */
	@Override
	public int updateCommunitySupplyCompanyPicBatch(List<CommunitySupplyCompanyPic> communitySupplyCompanyPicList){
		return communitySupplyCompanyPicBaseDao.updateCommunitySupplyCompanyPicBatch(communitySupplyCompanyPicList);
	}
	/**
	 * 根据序列号删除(商家图片)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyCompanyPicLogic(java.math.BigInteger id){
		return communitySupplyCompanyPicBaseDao.deleteCommunitySupplyCompanyPicLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(商家图片)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyCompanyPicLogicBatch(List<java.math.BigInteger> idList){
		return communitySupplyCompanyPicBaseDao.deleteCommunitySupplyCompanyPicLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(商家图片)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyCompanyPic(java.math.BigInteger id){
//		return communitySupplyCompanyPicBaseDao.deleteCommunitySupplyCompanyPic(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(商家图片)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyCompanyPicBatch(List<java.math.BigInteger> idList){
//		return communitySupplyCompanyPicBaseDao.deleteCommunitySupplyCompanyPicBatch(idList);
//	}
	
}
