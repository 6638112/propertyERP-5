package com.cnfantasia.server.domainbase.commonLock.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.commonLock.dao.ICommonLockBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commonLock.entity.CommonLock;

/**
 * 描述:(公用的锁表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommonLockBaseService implements ICommonLockBaseService{
	
	private ICommonLockBaseDao commonLockBaseDao;
	public void setCommonLockBaseDao(ICommonLockBaseDao commonLockBaseDao) {
		this.commonLockBaseDao = commonLockBaseDao;
	}
	/**
	 * 根据条件查询(公用的锁表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommonLock> getCommonLockByCondition(Map<String,Object> paramMap){
		return commonLockBaseDao.selectCommonLockByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(公用的锁表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommonLock> getCommonLockByConditionDim(Map<String,Object> paramMap){
		return commonLockBaseDao.selectCommonLockByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(公用的锁表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommonLock> getCommonLockByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return commonLockBaseDao.selectCommonLockByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(公用的锁表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommonLock> getCommonLockByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return commonLockBaseDao.selectCommonLockByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(公用的锁表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommonLock getCommonLockBySeqId(java.math.BigInteger id){
		return commonLockBaseDao.selectCommonLockBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(公用的锁表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommonLockCount(Map<String,Object> paramMap){
		return commonLockBaseDao.selectCommonLockCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(公用的锁表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommonLockCountDim(Map<String,Object> paramMap){
		return commonLockBaseDao.selectCommonLockCount(paramMap,true);
	}
	/**
	 * 往(公用的锁表)新增一条记录
	 * @param commonLock
	 * @return
	 */
	@Override
	public int insertCommonLock(CommonLock commonLock){
		return commonLockBaseDao.insertCommonLock(commonLock);
	}
	/**
	 * 批量新增(公用的锁表)
	 * @param commonLockList
	 * @return
	 */
	@Override
	public int insertCommonLockBatch(List<CommonLock> commonLockList){
		return commonLockBaseDao.insertCommonLockBatch(commonLockList);
	}
	/**
	 * 更新(公用的锁表)信息
	 * @param commonLock
	 * @return
	 */
	@Override
	public int updateCommonLock(CommonLock commonLock){
		return commonLockBaseDao.updateCommonLock(commonLock);
	}
	/**
	 * 批量更新(公用的锁表)信息
	 * @param commonLockList
	 * @return
	 */
	@Override
	public int updateCommonLockBatch(List<CommonLock> commonLockList){
		return commonLockBaseDao.updateCommonLockBatch(commonLockList);
	}
	/**
	 * 根据序列号删除(公用的锁表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteCommonLockLogic(java.math.BigInteger id){
		return commonLockBaseDao.deleteCommonLockLogic(id);
	}
	 */
	/**
	 * 根据序列号批量删除(公用的锁表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteCommonLockLogicBatch(List<java.math.BigInteger> idList){
		return commonLockBaseDao.deleteCommonLockLogicBatch(idList);
	}
	 */
//	/**
//	 * 根据序列号删除(公用的锁表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommonLock(java.math.BigInteger id){
//		return commonLockBaseDao.deleteCommonLock(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(公用的锁表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommonLockBatch(List<java.math.BigInteger> idList){
//		return commonLockBaseDao.deleteCommonLockBatch(idList);
//	}
	
}
