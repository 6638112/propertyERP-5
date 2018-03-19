package com.cnfantasia.server.domainbase.communityPinyipinReserve.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.communityPinyipinReserve.dao.ICommunityPinyipinReserveBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communityPinyipinReserve.entity.CommunityPinyipinReserve;

/**
 * 描述:(拼单预定信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommunityPinyipinReserveBaseService implements ICommunityPinyipinReserveBaseService{
	
	private ICommunityPinyipinReserveBaseDao communityPinyipinReserveBaseDao;
	public void setCommunityPinyipinReserveBaseDao(ICommunityPinyipinReserveBaseDao communityPinyipinReserveBaseDao) {
		this.communityPinyipinReserveBaseDao = communityPinyipinReserveBaseDao;
	}
	/**
	 * 根据条件查询(拼单预定信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunityPinyipinReserve> getCommunityPinyipinReserveByCondition(Map<String,Object> paramMap){
		return communityPinyipinReserveBaseDao.selectCommunityPinyipinReserveByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(拼单预定信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunityPinyipinReserve> getCommunityPinyipinReserveByConditionDim(Map<String,Object> paramMap){
		return communityPinyipinReserveBaseDao.selectCommunityPinyipinReserveByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(拼单预定信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunityPinyipinReserve> getCommunityPinyipinReserveByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return communityPinyipinReserveBaseDao.selectCommunityPinyipinReserveByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(拼单预定信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunityPinyipinReserve> getCommunityPinyipinReserveByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return communityPinyipinReserveBaseDao.selectCommunityPinyipinReserveByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(拼单预定信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunityPinyipinReserve getCommunityPinyipinReserveBySeqId(java.math.BigInteger id){
		return communityPinyipinReserveBaseDao.selectCommunityPinyipinReserveBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(拼单预定信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunityPinyipinReserveCount(Map<String,Object> paramMap){
		return communityPinyipinReserveBaseDao.selectCommunityPinyipinReserveCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(拼单预定信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunityPinyipinReserveCountDim(Map<String,Object> paramMap){
		return communityPinyipinReserveBaseDao.selectCommunityPinyipinReserveCount(paramMap,true);
	}
	/**
	 * 往(拼单预定信息)新增一条记录
	 * @param communityPinyipinReserve
	 * @return
	 */
	@Override
	public int insertCommunityPinyipinReserve(CommunityPinyipinReserve communityPinyipinReserve){
		return communityPinyipinReserveBaseDao.insertCommunityPinyipinReserve(communityPinyipinReserve);
	}
	/**
	 * 批量新增(拼单预定信息)
	 * @param communityPinyipinReserveList
	 * @return
	 */
	@Override
	public int insertCommunityPinyipinReserveBatch(List<CommunityPinyipinReserve> communityPinyipinReserveList){
		return communityPinyipinReserveBaseDao.insertCommunityPinyipinReserveBatch(communityPinyipinReserveList);
	}
	/**
	 * 更新(拼单预定信息)信息
	 * @param communityPinyipinReserve
	 * @return
	 */
	@Override
	public int updateCommunityPinyipinReserve(CommunityPinyipinReserve communityPinyipinReserve){
		return communityPinyipinReserveBaseDao.updateCommunityPinyipinReserve(communityPinyipinReserve);
	}
	/**
	 * 批量更新(拼单预定信息)信息
	 * @param communityPinyipinReserveList
	 * @return
	 */
	@Override
	public int updateCommunityPinyipinReserveBatch(List<CommunityPinyipinReserve> communityPinyipinReserveList){
		return communityPinyipinReserveBaseDao.updateCommunityPinyipinReserveBatch(communityPinyipinReserveList);
	}
	/**
	 * 根据序列号删除(拼单预定信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunityPinyipinReserveLogic(java.math.BigInteger id){
		return communityPinyipinReserveBaseDao.deleteCommunityPinyipinReserveLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(拼单预定信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunityPinyipinReserveLogicBatch(List<java.math.BigInteger> idList){
		return communityPinyipinReserveBaseDao.deleteCommunityPinyipinReserveLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(拼单预定信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityPinyipinReserve(java.math.BigInteger id){
//		return communityPinyipinReserveBaseDao.deleteCommunityPinyipinReserve(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(拼单预定信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityPinyipinReserveBatch(List<java.math.BigInteger> idList){
//		return communityPinyipinReserveBaseDao.deleteCommunityPinyipinReserveBatch(idList);
//	}
	
}
