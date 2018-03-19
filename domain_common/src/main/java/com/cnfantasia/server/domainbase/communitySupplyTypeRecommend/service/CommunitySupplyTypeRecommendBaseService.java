package com.cnfantasia.server.domainbase.communitySupplyTypeRecommend.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.communitySupplyTypeRecommend.dao.ICommunitySupplyTypeRecommendBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communitySupplyTypeRecommend.entity.CommunitySupplyTypeRecommend;

/**
 * 描述:(推荐的商家类别) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommunitySupplyTypeRecommendBaseService implements ICommunitySupplyTypeRecommendBaseService{
	
	private ICommunitySupplyTypeRecommendBaseDao communitySupplyTypeRecommendBaseDao;
	public void setCommunitySupplyTypeRecommendBaseDao(ICommunitySupplyTypeRecommendBaseDao communitySupplyTypeRecommendBaseDao) {
		this.communitySupplyTypeRecommendBaseDao = communitySupplyTypeRecommendBaseDao;
	}
	/**
	 * 根据条件查询(推荐的商家类别)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunitySupplyTypeRecommend> getCommunitySupplyTypeRecommendByCondition(Map<String,Object> paramMap){
		return communitySupplyTypeRecommendBaseDao.selectCommunitySupplyTypeRecommendByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(推荐的商家类别)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunitySupplyTypeRecommend> getCommunitySupplyTypeRecommendByConditionDim(Map<String,Object> paramMap){
		return communitySupplyTypeRecommendBaseDao.selectCommunitySupplyTypeRecommendByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(推荐的商家类别)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunitySupplyTypeRecommend> getCommunitySupplyTypeRecommendByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return communitySupplyTypeRecommendBaseDao.selectCommunitySupplyTypeRecommendByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(推荐的商家类别)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunitySupplyTypeRecommend> getCommunitySupplyTypeRecommendByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return communitySupplyTypeRecommendBaseDao.selectCommunitySupplyTypeRecommendByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(推荐的商家类别)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunitySupplyTypeRecommend getCommunitySupplyTypeRecommendBySeqId(java.math.BigInteger id){
		return communitySupplyTypeRecommendBaseDao.selectCommunitySupplyTypeRecommendBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(推荐的商家类别)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunitySupplyTypeRecommendCount(Map<String,Object> paramMap){
		return communitySupplyTypeRecommendBaseDao.selectCommunitySupplyTypeRecommendCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(推荐的商家类别)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunitySupplyTypeRecommendCountDim(Map<String,Object> paramMap){
		return communitySupplyTypeRecommendBaseDao.selectCommunitySupplyTypeRecommendCount(paramMap,true);
	}
	/**
	 * 往(推荐的商家类别)新增一条记录
	 * @param communitySupplyTypeRecommend
	 * @return
	 */
	@Override
	public int insertCommunitySupplyTypeRecommend(CommunitySupplyTypeRecommend communitySupplyTypeRecommend){
		return communitySupplyTypeRecommendBaseDao.insertCommunitySupplyTypeRecommend(communitySupplyTypeRecommend);
	}
	/**
	 * 批量新增(推荐的商家类别)
	 * @param communitySupplyTypeRecommendList
	 * @return
	 */
	@Override
	public int insertCommunitySupplyTypeRecommendBatch(List<CommunitySupplyTypeRecommend> communitySupplyTypeRecommendList){
		return communitySupplyTypeRecommendBaseDao.insertCommunitySupplyTypeRecommendBatch(communitySupplyTypeRecommendList);
	}
	/**
	 * 更新(推荐的商家类别)信息
	 * @param communitySupplyTypeRecommend
	 * @return
	 */
	@Override
	public int updateCommunitySupplyTypeRecommend(CommunitySupplyTypeRecommend communitySupplyTypeRecommend){
		return communitySupplyTypeRecommendBaseDao.updateCommunitySupplyTypeRecommend(communitySupplyTypeRecommend);
	}
	/**
	 * 批量更新(推荐的商家类别)信息
	 * @param communitySupplyTypeRecommendList
	 * @return
	 */
	@Override
	public int updateCommunitySupplyTypeRecommendBatch(List<CommunitySupplyTypeRecommend> communitySupplyTypeRecommendList){
		return communitySupplyTypeRecommendBaseDao.updateCommunitySupplyTypeRecommendBatch(communitySupplyTypeRecommendList);
	}
	/**
	 * 根据序列号删除(推荐的商家类别)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyTypeRecommendLogic(java.math.BigInteger id){
		return communitySupplyTypeRecommendBaseDao.deleteCommunitySupplyTypeRecommendLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(推荐的商家类别)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyTypeRecommendLogicBatch(List<java.math.BigInteger> idList){
		return communitySupplyTypeRecommendBaseDao.deleteCommunitySupplyTypeRecommendLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(推荐的商家类别)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyTypeRecommend(java.math.BigInteger id){
//		return communitySupplyTypeRecommendBaseDao.deleteCommunitySupplyTypeRecommend(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(推荐的商家类别)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyTypeRecommendBatch(List<java.math.BigInteger> idList){
//		return communitySupplyTypeRecommendBaseDao.deleteCommunitySupplyTypeRecommendBatch(idList);
//	}
	
}
