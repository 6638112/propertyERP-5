package com.cnfantasia.server.domainbase.communitySupplyBelong.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.communitySupplyBelong.dao.ICommunitySupplyBelongBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communitySupplyBelong.entity.CommunitySupplyBelong;

/**
 * 描述:(商家认领) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommunitySupplyBelongBaseService implements ICommunitySupplyBelongBaseService{
	
	private ICommunitySupplyBelongBaseDao communitySupplyBelongBaseDao;
	public void setCommunitySupplyBelongBaseDao(ICommunitySupplyBelongBaseDao communitySupplyBelongBaseDao) {
		this.communitySupplyBelongBaseDao = communitySupplyBelongBaseDao;
	}
	/**
	 * 根据条件查询(商家认领)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunitySupplyBelong> getCommunitySupplyBelongByCondition(Map<String,Object> paramMap){
		return communitySupplyBelongBaseDao.selectCommunitySupplyBelongByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(商家认领)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunitySupplyBelong> getCommunitySupplyBelongByConditionDim(Map<String,Object> paramMap){
		return communitySupplyBelongBaseDao.selectCommunitySupplyBelongByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(商家认领)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunitySupplyBelong> getCommunitySupplyBelongByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return communitySupplyBelongBaseDao.selectCommunitySupplyBelongByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(商家认领)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunitySupplyBelong> getCommunitySupplyBelongByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return communitySupplyBelongBaseDao.selectCommunitySupplyBelongByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(商家认领)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunitySupplyBelong getCommunitySupplyBelongBySeqId(java.math.BigInteger id){
		return communitySupplyBelongBaseDao.selectCommunitySupplyBelongBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(商家认领)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunitySupplyBelongCount(Map<String,Object> paramMap){
		return communitySupplyBelongBaseDao.selectCommunitySupplyBelongCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(商家认领)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunitySupplyBelongCountDim(Map<String,Object> paramMap){
		return communitySupplyBelongBaseDao.selectCommunitySupplyBelongCount(paramMap,true);
	}
	/**
	 * 往(商家认领)新增一条记录
	 * @param communitySupplyBelong
	 * @return
	 */
	@Override
	public int insertCommunitySupplyBelong(CommunitySupplyBelong communitySupplyBelong){
		return communitySupplyBelongBaseDao.insertCommunitySupplyBelong(communitySupplyBelong);
	}
	/**
	 * 批量新增(商家认领)
	 * @param communitySupplyBelongList
	 * @return
	 */
	@Override
	public int insertCommunitySupplyBelongBatch(List<CommunitySupplyBelong> communitySupplyBelongList){
		return communitySupplyBelongBaseDao.insertCommunitySupplyBelongBatch(communitySupplyBelongList);
	}
	/**
	 * 更新(商家认领)信息
	 * @param communitySupplyBelong
	 * @return
	 */
	@Override
	public int updateCommunitySupplyBelong(CommunitySupplyBelong communitySupplyBelong){
		return communitySupplyBelongBaseDao.updateCommunitySupplyBelong(communitySupplyBelong);
	}
	/**
	 * 批量更新(商家认领)信息
	 * @param communitySupplyBelongList
	 * @return
	 */
	@Override
	public int updateCommunitySupplyBelongBatch(List<CommunitySupplyBelong> communitySupplyBelongList){
		return communitySupplyBelongBaseDao.updateCommunitySupplyBelongBatch(communitySupplyBelongList);
	}
	/**
	 * 根据序列号删除(商家认领)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyBelongLogic(java.math.BigInteger id){
		return communitySupplyBelongBaseDao.deleteCommunitySupplyBelongLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(商家认领)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyBelongLogicBatch(List<java.math.BigInteger> idList){
		return communitySupplyBelongBaseDao.deleteCommunitySupplyBelongLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(商家认领)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyBelong(java.math.BigInteger id){
//		return communitySupplyBelongBaseDao.deleteCommunitySupplyBelong(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(商家认领)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyBelongBatch(List<java.math.BigInteger> idList){
//		return communitySupplyBelongBaseDao.deleteCommunitySupplyBelongBatch(idList);
//	}
	
}
