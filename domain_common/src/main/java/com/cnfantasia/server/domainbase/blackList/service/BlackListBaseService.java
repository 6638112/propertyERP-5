package com.cnfantasia.server.domainbase.blackList.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.blackList.dao.IBlackListBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.blackList.entity.BlackList;

/**
 * 描述:(黑名单) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class BlackListBaseService implements IBlackListBaseService{
	
	private IBlackListBaseDao blackListBaseDao;
	public void setBlackListBaseDao(IBlackListBaseDao blackListBaseDao) {
		this.blackListBaseDao = blackListBaseDao;
	}
	/**
	 * 根据条件查询(黑名单)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<BlackList> getBlackListByCondition(Map<String,Object> paramMap){
		return blackListBaseDao.selectBlackListByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(黑名单)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<BlackList> getBlackListByConditionDim(Map<String,Object> paramMap){
		return blackListBaseDao.selectBlackListByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(黑名单)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<BlackList> getBlackListByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return blackListBaseDao.selectBlackListByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(黑名单)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<BlackList> getBlackListByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return blackListBaseDao.selectBlackListByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(黑名单)信息
	 * @param id
	 * @return
	 */
	@Override
	public BlackList getBlackListBySeqId(java.math.BigInteger id){
		return blackListBaseDao.selectBlackListBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(黑名单)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getBlackListCount(Map<String,Object> paramMap){
		return blackListBaseDao.selectBlackListCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(黑名单)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getBlackListCountDim(Map<String,Object> paramMap){
		return blackListBaseDao.selectBlackListCount(paramMap,true);
	}
	/**
	 * 往(黑名单)新增一条记录
	 * @param blackList
	 * @return
	 */
	@Override
	public int insertBlackList(BlackList blackList){
		return blackListBaseDao.insertBlackList(blackList);
	}
	/**
	 * 批量新增(黑名单)
	 * @param blackListList
	 * @return
	 */
	@Override
	public int insertBlackListBatch(List<BlackList> blackListList){
		return blackListBaseDao.insertBlackListBatch(blackListList);
	}
	/**
	 * 更新(黑名单)信息
	 * @param blackList
	 * @return
	 */
	@Override
	public int updateBlackList(BlackList blackList){
		return blackListBaseDao.updateBlackList(blackList);
	}
	/**
	 * 批量更新(黑名单)信息
	 * @param blackListList
	 * @return
	 */
	@Override
	public int updateBlackListBatch(List<BlackList> blackListList){
		return blackListBaseDao.updateBlackListBatch(blackListList);
	}
	/**
	 * 根据序列号删除(黑名单)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteBlackListLogic(java.math.BigInteger id){
		return blackListBaseDao.deleteBlackListLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(黑名单)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteBlackListLogicBatch(List<java.math.BigInteger> idList){
		return blackListBaseDao.deleteBlackListLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(黑名单)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteBlackList(java.math.BigInteger id){
//		return blackListBaseDao.deleteBlackList(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(黑名单)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteBlackListBatch(List<java.math.BigInteger> idList){
//		return blackListBaseDao.deleteBlackListBatch(idList);
//	}
	
}
