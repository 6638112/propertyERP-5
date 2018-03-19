package com.cnfantasia.server.domainbase.communitySupplyTmp.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.communitySupplyTmp.dao.ICommunitySupplyTmpBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communitySupplyTmp.entity.CommunitySupplyTmp;

/**
 * 描述:(店铺申请审核表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommunitySupplyTmpBaseService implements ICommunitySupplyTmpBaseService{
	
	private ICommunitySupplyTmpBaseDao communitySupplyTmpBaseDao;
	public void setCommunitySupplyTmpBaseDao(ICommunitySupplyTmpBaseDao communitySupplyTmpBaseDao) {
		this.communitySupplyTmpBaseDao = communitySupplyTmpBaseDao;
	}
	/**
	 * 根据条件查询(店铺申请审核表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunitySupplyTmp> getCommunitySupplyTmpByCondition(Map<String,Object> paramMap){
		return communitySupplyTmpBaseDao.selectCommunitySupplyTmpByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(店铺申请审核表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunitySupplyTmp> getCommunitySupplyTmpByConditionDim(Map<String,Object> paramMap){
		return communitySupplyTmpBaseDao.selectCommunitySupplyTmpByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(店铺申请审核表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunitySupplyTmp> getCommunitySupplyTmpByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return communitySupplyTmpBaseDao.selectCommunitySupplyTmpByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(店铺申请审核表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunitySupplyTmp> getCommunitySupplyTmpByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return communitySupplyTmpBaseDao.selectCommunitySupplyTmpByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(店铺申请审核表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunitySupplyTmp getCommunitySupplyTmpBySeqId(java.math.BigInteger id){
		return communitySupplyTmpBaseDao.selectCommunitySupplyTmpBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(店铺申请审核表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunitySupplyTmpCount(Map<String,Object> paramMap){
		return communitySupplyTmpBaseDao.selectCommunitySupplyTmpCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(店铺申请审核表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunitySupplyTmpCountDim(Map<String,Object> paramMap){
		return communitySupplyTmpBaseDao.selectCommunitySupplyTmpCount(paramMap,true);
	}
	/**
	 * 往(店铺申请审核表)新增一条记录
	 * @param communitySupplyTmp
	 * @return
	 */
	@Override
	public int insertCommunitySupplyTmp(CommunitySupplyTmp communitySupplyTmp){
		return communitySupplyTmpBaseDao.insertCommunitySupplyTmp(communitySupplyTmp);
	}
	/**
	 * 批量新增(店铺申请审核表)
	 * @param communitySupplyTmpList
	 * @return
	 */
	@Override
	public int insertCommunitySupplyTmpBatch(List<CommunitySupplyTmp> communitySupplyTmpList){
		return communitySupplyTmpBaseDao.insertCommunitySupplyTmpBatch(communitySupplyTmpList);
	}
	/**
	 * 更新(店铺申请审核表)信息
	 * @param communitySupplyTmp
	 * @return
	 */
	@Override
	public int updateCommunitySupplyTmp(CommunitySupplyTmp communitySupplyTmp){
		return communitySupplyTmpBaseDao.updateCommunitySupplyTmp(communitySupplyTmp);
	}
	/**
	 * 批量更新(店铺申请审核表)信息
	 * @param communitySupplyTmpList
	 * @return
	 */
	@Override
	public int updateCommunitySupplyTmpBatch(List<CommunitySupplyTmp> communitySupplyTmpList){
		return communitySupplyTmpBaseDao.updateCommunitySupplyTmpBatch(communitySupplyTmpList);
	}
	/**
	 * 根据序列号删除(店铺申请审核表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyTmpLogic(java.math.BigInteger id){
		return communitySupplyTmpBaseDao.deleteCommunitySupplyTmpLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(店铺申请审核表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyTmpLogicBatch(List<java.math.BigInteger> idList){
		return communitySupplyTmpBaseDao.deleteCommunitySupplyTmpLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(店铺申请审核表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyTmp(java.math.BigInteger id){
//		return communitySupplyTmpBaseDao.deleteCommunitySupplyTmp(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(店铺申请审核表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyTmpBatch(List<java.math.BigInteger> idList){
//		return communitySupplyTmpBaseDao.deleteCommunitySupplyTmpBatch(idList);
//	}
	
}
