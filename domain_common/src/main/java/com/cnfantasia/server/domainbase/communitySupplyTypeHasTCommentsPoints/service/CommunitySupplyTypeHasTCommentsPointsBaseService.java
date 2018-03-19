package com.cnfantasia.server.domainbase.communitySupplyTypeHasTCommentsPoints.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.communitySupplyTypeHasTCommentsPoints.dao.ICommunitySupplyTypeHasTCommentsPointsBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communitySupplyTypeHasTCommentsPoints.entity.CommunitySupplyTypeHasTCommentsPoints;

/**
 * 描述:(商家类别支持哪些评分) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommunitySupplyTypeHasTCommentsPointsBaseService implements ICommunitySupplyTypeHasTCommentsPointsBaseService{
	
	private ICommunitySupplyTypeHasTCommentsPointsBaseDao communitySupplyTypeHasTCommentsPointsBaseDao;
	public void setCommunitySupplyTypeHasTCommentsPointsBaseDao(ICommunitySupplyTypeHasTCommentsPointsBaseDao communitySupplyTypeHasTCommentsPointsBaseDao) {
		this.communitySupplyTypeHasTCommentsPointsBaseDao = communitySupplyTypeHasTCommentsPointsBaseDao;
	}
	/**
	 * 根据条件查询(商家类别支持哪些评分)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunitySupplyTypeHasTCommentsPoints> getCommunitySupplyTypeHasTCommentsPointsByCondition(Map<String,Object> paramMap){
		return communitySupplyTypeHasTCommentsPointsBaseDao.selectCommunitySupplyTypeHasTCommentsPointsByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(商家类别支持哪些评分)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunitySupplyTypeHasTCommentsPoints> getCommunitySupplyTypeHasTCommentsPointsByConditionDim(Map<String,Object> paramMap){
		return communitySupplyTypeHasTCommentsPointsBaseDao.selectCommunitySupplyTypeHasTCommentsPointsByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(商家类别支持哪些评分)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunitySupplyTypeHasTCommentsPoints> getCommunitySupplyTypeHasTCommentsPointsByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return communitySupplyTypeHasTCommentsPointsBaseDao.selectCommunitySupplyTypeHasTCommentsPointsByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(商家类别支持哪些评分)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunitySupplyTypeHasTCommentsPoints> getCommunitySupplyTypeHasTCommentsPointsByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return communitySupplyTypeHasTCommentsPointsBaseDao.selectCommunitySupplyTypeHasTCommentsPointsByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(商家类别支持哪些评分)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunitySupplyTypeHasTCommentsPoints getCommunitySupplyTypeHasTCommentsPointsBySeqId(java.math.BigInteger id){
		return communitySupplyTypeHasTCommentsPointsBaseDao.selectCommunitySupplyTypeHasTCommentsPointsBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(商家类别支持哪些评分)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunitySupplyTypeHasTCommentsPointsCount(Map<String,Object> paramMap){
		return communitySupplyTypeHasTCommentsPointsBaseDao.selectCommunitySupplyTypeHasTCommentsPointsCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(商家类别支持哪些评分)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunitySupplyTypeHasTCommentsPointsCountDim(Map<String,Object> paramMap){
		return communitySupplyTypeHasTCommentsPointsBaseDao.selectCommunitySupplyTypeHasTCommentsPointsCount(paramMap,true);
	}
	/**
	 * 往(商家类别支持哪些评分)新增一条记录
	 * @param communitySupplyTypeHasTCommentsPoints
	 * @return
	 */
	@Override
	public int insertCommunitySupplyTypeHasTCommentsPoints(CommunitySupplyTypeHasTCommentsPoints communitySupplyTypeHasTCommentsPoints){
		return communitySupplyTypeHasTCommentsPointsBaseDao.insertCommunitySupplyTypeHasTCommentsPoints(communitySupplyTypeHasTCommentsPoints);
	}
	/**
	 * 批量新增(商家类别支持哪些评分)
	 * @param communitySupplyTypeHasTCommentsPointsList
	 * @return
	 */
	@Override
	public int insertCommunitySupplyTypeHasTCommentsPointsBatch(List<CommunitySupplyTypeHasTCommentsPoints> communitySupplyTypeHasTCommentsPointsList){
		return communitySupplyTypeHasTCommentsPointsBaseDao.insertCommunitySupplyTypeHasTCommentsPointsBatch(communitySupplyTypeHasTCommentsPointsList);
	}
	/**
	 * 更新(商家类别支持哪些评分)信息
	 * @param communitySupplyTypeHasTCommentsPoints
	 * @return
	 */
	@Override
	public int updateCommunitySupplyTypeHasTCommentsPoints(CommunitySupplyTypeHasTCommentsPoints communitySupplyTypeHasTCommentsPoints){
		return communitySupplyTypeHasTCommentsPointsBaseDao.updateCommunitySupplyTypeHasTCommentsPoints(communitySupplyTypeHasTCommentsPoints);
	}
	/**
	 * 批量更新(商家类别支持哪些评分)信息
	 * @param communitySupplyTypeHasTCommentsPointsList
	 * @return
	 */
	@Override
	public int updateCommunitySupplyTypeHasTCommentsPointsBatch(List<CommunitySupplyTypeHasTCommentsPoints> communitySupplyTypeHasTCommentsPointsList){
		return communitySupplyTypeHasTCommentsPointsBaseDao.updateCommunitySupplyTypeHasTCommentsPointsBatch(communitySupplyTypeHasTCommentsPointsList);
	}
	/**
	 * 根据序列号删除(商家类别支持哪些评分)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyTypeHasTCommentsPointsLogic(java.math.BigInteger id){
		return communitySupplyTypeHasTCommentsPointsBaseDao.deleteCommunitySupplyTypeHasTCommentsPointsLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(商家类别支持哪些评分)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyTypeHasTCommentsPointsLogicBatch(List<java.math.BigInteger> idList){
		return communitySupplyTypeHasTCommentsPointsBaseDao.deleteCommunitySupplyTypeHasTCommentsPointsLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(商家类别支持哪些评分)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyTypeHasTCommentsPoints(java.math.BigInteger id){
//		return communitySupplyTypeHasTCommentsPointsBaseDao.deleteCommunitySupplyTypeHasTCommentsPoints(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(商家类别支持哪些评分)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyTypeHasTCommentsPointsBatch(List<java.math.BigInteger> idList){
//		return communitySupplyTypeHasTCommentsPointsBaseDao.deleteCommunitySupplyTypeHasTCommentsPointsBatch(idList);
//	}
	
}
