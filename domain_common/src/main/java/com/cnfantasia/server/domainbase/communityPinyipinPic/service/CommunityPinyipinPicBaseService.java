package com.cnfantasia.server.domainbase.communityPinyipinPic.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.communityPinyipinPic.dao.ICommunityPinyipinPicBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communityPinyipinPic.entity.CommunityPinyipinPic;

/**
 * 描述:(拼一拼图片列表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommunityPinyipinPicBaseService implements ICommunityPinyipinPicBaseService{
	
	private ICommunityPinyipinPicBaseDao communityPinyipinPicBaseDao;
	public void setCommunityPinyipinPicBaseDao(ICommunityPinyipinPicBaseDao communityPinyipinPicBaseDao) {
		this.communityPinyipinPicBaseDao = communityPinyipinPicBaseDao;
	}
	/**
	 * 根据条件查询(拼一拼图片列表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunityPinyipinPic> getCommunityPinyipinPicByCondition(Map<String,Object> paramMap){
		return communityPinyipinPicBaseDao.selectCommunityPinyipinPicByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(拼一拼图片列表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunityPinyipinPic> getCommunityPinyipinPicByConditionDim(Map<String,Object> paramMap){
		return communityPinyipinPicBaseDao.selectCommunityPinyipinPicByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(拼一拼图片列表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunityPinyipinPic> getCommunityPinyipinPicByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return communityPinyipinPicBaseDao.selectCommunityPinyipinPicByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(拼一拼图片列表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunityPinyipinPic> getCommunityPinyipinPicByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return communityPinyipinPicBaseDao.selectCommunityPinyipinPicByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(拼一拼图片列表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunityPinyipinPic getCommunityPinyipinPicBySeqId(java.math.BigInteger id){
		return communityPinyipinPicBaseDao.selectCommunityPinyipinPicBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(拼一拼图片列表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunityPinyipinPicCount(Map<String,Object> paramMap){
		return communityPinyipinPicBaseDao.selectCommunityPinyipinPicCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(拼一拼图片列表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunityPinyipinPicCountDim(Map<String,Object> paramMap){
		return communityPinyipinPicBaseDao.selectCommunityPinyipinPicCount(paramMap,true);
	}
	/**
	 * 往(拼一拼图片列表)新增一条记录
	 * @param communityPinyipinPic
	 * @return
	 */
	@Override
	public int insertCommunityPinyipinPic(CommunityPinyipinPic communityPinyipinPic){
		return communityPinyipinPicBaseDao.insertCommunityPinyipinPic(communityPinyipinPic);
	}
	/**
	 * 批量新增(拼一拼图片列表)
	 * @param communityPinyipinPicList
	 * @return
	 */
	@Override
	public int insertCommunityPinyipinPicBatch(List<CommunityPinyipinPic> communityPinyipinPicList){
		return communityPinyipinPicBaseDao.insertCommunityPinyipinPicBatch(communityPinyipinPicList);
	}
	/**
	 * 更新(拼一拼图片列表)信息
	 * @param communityPinyipinPic
	 * @return
	 */
	@Override
	public int updateCommunityPinyipinPic(CommunityPinyipinPic communityPinyipinPic){
		return communityPinyipinPicBaseDao.updateCommunityPinyipinPic(communityPinyipinPic);
	}
	/**
	 * 批量更新(拼一拼图片列表)信息
	 * @param communityPinyipinPicList
	 * @return
	 */
	@Override
	public int updateCommunityPinyipinPicBatch(List<CommunityPinyipinPic> communityPinyipinPicList){
		return communityPinyipinPicBaseDao.updateCommunityPinyipinPicBatch(communityPinyipinPicList);
	}
	/**
	 * 根据序列号删除(拼一拼图片列表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunityPinyipinPicLogic(java.math.BigInteger id){
		return communityPinyipinPicBaseDao.deleteCommunityPinyipinPicLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(拼一拼图片列表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunityPinyipinPicLogicBatch(List<java.math.BigInteger> idList){
		return communityPinyipinPicBaseDao.deleteCommunityPinyipinPicLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(拼一拼图片列表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityPinyipinPic(java.math.BigInteger id){
//		return communityPinyipinPicBaseDao.deleteCommunityPinyipinPic(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(拼一拼图片列表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityPinyipinPicBatch(List<java.math.BigInteger> idList){
//		return communityPinyipinPicBaseDao.deleteCommunityPinyipinPicBatch(idList);
//	}
	
}
