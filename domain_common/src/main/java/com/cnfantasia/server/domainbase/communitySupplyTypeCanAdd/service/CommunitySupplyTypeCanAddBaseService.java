package com.cnfantasia.server.domainbase.communitySupplyTypeCanAdd.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.communitySupplyTypeCanAdd.dao.ICommunitySupplyTypeCanAddBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communitySupplyTypeCanAdd.entity.CommunitySupplyTypeCanAdd;

/**
 * 描述:(新增商家时的可选类别) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommunitySupplyTypeCanAddBaseService implements ICommunitySupplyTypeCanAddBaseService{
	
	private ICommunitySupplyTypeCanAddBaseDao communitySupplyTypeCanAddBaseDao;
	public void setCommunitySupplyTypeCanAddBaseDao(ICommunitySupplyTypeCanAddBaseDao communitySupplyTypeCanAddBaseDao) {
		this.communitySupplyTypeCanAddBaseDao = communitySupplyTypeCanAddBaseDao;
	}
	/**
	 * 根据条件查询(新增商家时的可选类别)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunitySupplyTypeCanAdd> getCommunitySupplyTypeCanAddByCondition(Map<String,Object> paramMap){
		return communitySupplyTypeCanAddBaseDao.selectCommunitySupplyTypeCanAddByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(新增商家时的可选类别)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunitySupplyTypeCanAdd> getCommunitySupplyTypeCanAddByConditionDim(Map<String,Object> paramMap){
		return communitySupplyTypeCanAddBaseDao.selectCommunitySupplyTypeCanAddByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(新增商家时的可选类别)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunitySupplyTypeCanAdd> getCommunitySupplyTypeCanAddByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return communitySupplyTypeCanAddBaseDao.selectCommunitySupplyTypeCanAddByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(新增商家时的可选类别)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunitySupplyTypeCanAdd> getCommunitySupplyTypeCanAddByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return communitySupplyTypeCanAddBaseDao.selectCommunitySupplyTypeCanAddByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(新增商家时的可选类别)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunitySupplyTypeCanAdd getCommunitySupplyTypeCanAddBySeqId(java.math.BigInteger id){
		return communitySupplyTypeCanAddBaseDao.selectCommunitySupplyTypeCanAddBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(新增商家时的可选类别)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunitySupplyTypeCanAddCount(Map<String,Object> paramMap){
		return communitySupplyTypeCanAddBaseDao.selectCommunitySupplyTypeCanAddCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(新增商家时的可选类别)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunitySupplyTypeCanAddCountDim(Map<String,Object> paramMap){
		return communitySupplyTypeCanAddBaseDao.selectCommunitySupplyTypeCanAddCount(paramMap,true);
	}
	/**
	 * 往(新增商家时的可选类别)新增一条记录
	 * @param communitySupplyTypeCanAdd
	 * @return
	 */
	@Override
	public int insertCommunitySupplyTypeCanAdd(CommunitySupplyTypeCanAdd communitySupplyTypeCanAdd){
		return communitySupplyTypeCanAddBaseDao.insertCommunitySupplyTypeCanAdd(communitySupplyTypeCanAdd);
	}
	/**
	 * 批量新增(新增商家时的可选类别)
	 * @param communitySupplyTypeCanAddList
	 * @return
	 */
	@Override
	public int insertCommunitySupplyTypeCanAddBatch(List<CommunitySupplyTypeCanAdd> communitySupplyTypeCanAddList){
		return communitySupplyTypeCanAddBaseDao.insertCommunitySupplyTypeCanAddBatch(communitySupplyTypeCanAddList);
	}
	/**
	 * 更新(新增商家时的可选类别)信息
	 * @param communitySupplyTypeCanAdd
	 * @return
	 */
	@Override
	public int updateCommunitySupplyTypeCanAdd(CommunitySupplyTypeCanAdd communitySupplyTypeCanAdd){
		return communitySupplyTypeCanAddBaseDao.updateCommunitySupplyTypeCanAdd(communitySupplyTypeCanAdd);
	}
	/**
	 * 批量更新(新增商家时的可选类别)信息
	 * @param communitySupplyTypeCanAddList
	 * @return
	 */
	@Override
	public int updateCommunitySupplyTypeCanAddBatch(List<CommunitySupplyTypeCanAdd> communitySupplyTypeCanAddList){
		return communitySupplyTypeCanAddBaseDao.updateCommunitySupplyTypeCanAddBatch(communitySupplyTypeCanAddList);
	}
	/**
	 * 根据序列号删除(新增商家时的可选类别)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyTypeCanAddLogic(java.math.BigInteger id){
		return communitySupplyTypeCanAddBaseDao.deleteCommunitySupplyTypeCanAddLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(新增商家时的可选类别)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyTypeCanAddLogicBatch(List<java.math.BigInteger> idList){
		return communitySupplyTypeCanAddBaseDao.deleteCommunitySupplyTypeCanAddLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(新增商家时的可选类别)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyTypeCanAdd(java.math.BigInteger id){
//		return communitySupplyTypeCanAddBaseDao.deleteCommunitySupplyTypeCanAdd(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(新增商家时的可选类别)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyTypeCanAddBatch(List<java.math.BigInteger> idList){
//		return communitySupplyTypeCanAddBaseDao.deleteCommunitySupplyTypeCanAddBatch(idList);
//	}
	
}
