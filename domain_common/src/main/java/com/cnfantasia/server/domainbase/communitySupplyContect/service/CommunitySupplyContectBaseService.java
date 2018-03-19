package com.cnfantasia.server.domainbase.communitySupplyContect.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.communitySupplyContect.dao.ICommunitySupplyContectBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communitySupplyContect.entity.CommunitySupplyContect;

/**
 * 描述:(社区商家联系方式) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommunitySupplyContectBaseService implements ICommunitySupplyContectBaseService{
	
	private ICommunitySupplyContectBaseDao communitySupplyContectBaseDao;
	public void setCommunitySupplyContectBaseDao(ICommunitySupplyContectBaseDao communitySupplyContectBaseDao) {
		this.communitySupplyContectBaseDao = communitySupplyContectBaseDao;
	}
	/**
	 * 根据条件查询(社区商家联系方式)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunitySupplyContect> getCommunitySupplyContectByCondition(Map<String,Object> paramMap){
		return communitySupplyContectBaseDao.selectCommunitySupplyContectByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(社区商家联系方式)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunitySupplyContect> getCommunitySupplyContectByConditionDim(Map<String,Object> paramMap){
		return communitySupplyContectBaseDao.selectCommunitySupplyContectByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(社区商家联系方式)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunitySupplyContect> getCommunitySupplyContectByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return communitySupplyContectBaseDao.selectCommunitySupplyContectByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(社区商家联系方式)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunitySupplyContect> getCommunitySupplyContectByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return communitySupplyContectBaseDao.selectCommunitySupplyContectByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(社区商家联系方式)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunitySupplyContect getCommunitySupplyContectBySeqId(java.math.BigInteger id){
		return communitySupplyContectBaseDao.selectCommunitySupplyContectBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(社区商家联系方式)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunitySupplyContectCount(Map<String,Object> paramMap){
		return communitySupplyContectBaseDao.selectCommunitySupplyContectCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(社区商家联系方式)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunitySupplyContectCountDim(Map<String,Object> paramMap){
		return communitySupplyContectBaseDao.selectCommunitySupplyContectCount(paramMap,true);
	}
	/**
	 * 往(社区商家联系方式)新增一条记录
	 * @param communitySupplyContect
	 * @return
	 */
	@Override
	public int insertCommunitySupplyContect(CommunitySupplyContect communitySupplyContect){
		return communitySupplyContectBaseDao.insertCommunitySupplyContect(communitySupplyContect);
	}
	/**
	 * 批量新增(社区商家联系方式)
	 * @param communitySupplyContectList
	 * @return
	 */
	@Override
	public int insertCommunitySupplyContectBatch(List<CommunitySupplyContect> communitySupplyContectList){
		return communitySupplyContectBaseDao.insertCommunitySupplyContectBatch(communitySupplyContectList);
	}
	/**
	 * 更新(社区商家联系方式)信息
	 * @param communitySupplyContect
	 * @return
	 */
	@Override
	public int updateCommunitySupplyContect(CommunitySupplyContect communitySupplyContect){
		return communitySupplyContectBaseDao.updateCommunitySupplyContect(communitySupplyContect);
	}
	/**
	 * 批量更新(社区商家联系方式)信息
	 * @param communitySupplyContectList
	 * @return
	 */
	@Override
	public int updateCommunitySupplyContectBatch(List<CommunitySupplyContect> communitySupplyContectList){
		return communitySupplyContectBaseDao.updateCommunitySupplyContectBatch(communitySupplyContectList);
	}
	/**
	 * 根据序列号删除(社区商家联系方式)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyContectLogic(java.math.BigInteger id){
		return communitySupplyContectBaseDao.deleteCommunitySupplyContectLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(社区商家联系方式)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyContectLogicBatch(List<java.math.BigInteger> idList){
		return communitySupplyContectBaseDao.deleteCommunitySupplyContectLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(社区商家联系方式)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyContect(java.math.BigInteger id){
//		return communitySupplyContectBaseDao.deleteCommunitySupplyContect(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(社区商家联系方式)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyContectBatch(List<java.math.BigInteger> idList){
//		return communitySupplyContectBaseDao.deleteCommunitySupplyContectBatch(idList);
//	}
	
}
