package com.cnfantasia.server.domainbase.communitySupplyType.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.communitySupplyType.dao.ICommunitySupplyTypeBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;

/**
 * 描述:(社区商家类别) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommunitySupplyTypeBaseService implements ICommunitySupplyTypeBaseService{
	
	private ICommunitySupplyTypeBaseDao communitySupplyTypeBaseDao;
	public void setCommunitySupplyTypeBaseDao(ICommunitySupplyTypeBaseDao communitySupplyTypeBaseDao) {
		this.communitySupplyTypeBaseDao = communitySupplyTypeBaseDao;
	}
	/**
	 * 根据条件查询(社区商家类别)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunitySupplyType> getCommunitySupplyTypeByCondition(Map<String,Object> paramMap){
		return communitySupplyTypeBaseDao.selectCommunitySupplyTypeByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(社区商家类别)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunitySupplyType> getCommunitySupplyTypeByConditionDim(Map<String,Object> paramMap){
		return communitySupplyTypeBaseDao.selectCommunitySupplyTypeByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(社区商家类别)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunitySupplyType> getCommunitySupplyTypeByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return communitySupplyTypeBaseDao.selectCommunitySupplyTypeByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(社区商家类别)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunitySupplyType> getCommunitySupplyTypeByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return communitySupplyTypeBaseDao.selectCommunitySupplyTypeByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(社区商家类别)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunitySupplyType getCommunitySupplyTypeBySeqId(java.math.BigInteger id){
		return communitySupplyTypeBaseDao.selectCommunitySupplyTypeBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(社区商家类别)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunitySupplyTypeCount(Map<String,Object> paramMap){
		return communitySupplyTypeBaseDao.selectCommunitySupplyTypeCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(社区商家类别)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunitySupplyTypeCountDim(Map<String,Object> paramMap){
		return communitySupplyTypeBaseDao.selectCommunitySupplyTypeCount(paramMap,true);
	}
	/**
	 * 往(社区商家类别)新增一条记录
	 * @param communitySupplyType
	 * @return
	 */
	@Override
	public int insertCommunitySupplyType(CommunitySupplyType communitySupplyType){
		return communitySupplyTypeBaseDao.insertCommunitySupplyType(communitySupplyType);
	}
	/**
	 * 批量新增(社区商家类别)
	 * @param communitySupplyTypeList
	 * @return
	 */
	@Override
	public int insertCommunitySupplyTypeBatch(List<CommunitySupplyType> communitySupplyTypeList){
		return communitySupplyTypeBaseDao.insertCommunitySupplyTypeBatch(communitySupplyTypeList);
	}
	/**
	 * 更新(社区商家类别)信息
	 * @param communitySupplyType
	 * @return
	 */
	@Override
	public int updateCommunitySupplyType(CommunitySupplyType communitySupplyType){
		return communitySupplyTypeBaseDao.updateCommunitySupplyType(communitySupplyType);
	}
	/**
	 * 批量更新(社区商家类别)信息
	 * @param communitySupplyTypeList
	 * @return
	 */
	@Override
	public int updateCommunitySupplyTypeBatch(List<CommunitySupplyType> communitySupplyTypeList){
		return communitySupplyTypeBaseDao.updateCommunitySupplyTypeBatch(communitySupplyTypeList);
	}
	/**
	 * 根据序列号删除(社区商家类别)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyTypeLogic(java.math.BigInteger id){
		return communitySupplyTypeBaseDao.deleteCommunitySupplyTypeLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(社区商家类别)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyTypeLogicBatch(List<java.math.BigInteger> idList){
		return communitySupplyTypeBaseDao.deleteCommunitySupplyTypeLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(社区商家类别)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyType(java.math.BigInteger id){
//		return communitySupplyTypeBaseDao.deleteCommunitySupplyType(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(社区商家类别)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyTypeBatch(List<java.math.BigInteger> idList){
//		return communitySupplyTypeBaseDao.deleteCommunitySupplyTypeBatch(idList);
//	}
	
}
