package com.cnfantasia.server.domainbase.commonLock.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.commonLock.entity.CommonLock;

/**
 * 描述:(公用的锁表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommonLockBaseService {
	
	/**
	 * 根据条件查询(公用的锁表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommonLock> getCommonLockByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(公用的锁表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommonLock> getCommonLockByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(公用的锁表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommonLock> getCommonLockByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(公用的锁表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommonLock> getCommonLockByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(公用的锁表)信息
	 * @param id
	 * @return
	 */
	public CommonLock getCommonLockBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(公用的锁表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCommonLockCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(公用的锁表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCommonLockCountDim(Map<String,Object> paramMap);
	/**
	 * 往(公用的锁表)新增一条记录
	 * @param commonLock
	 * @return
	 */
	public int insertCommonLock(CommonLock commonLock);
	/**
	 * 批量新增(公用的锁表)
	 * @param commonLockList
	 * @return
	 */
	public int insertCommonLockBatch(List<CommonLock> commonLockList);
	/**
	 * 更新(公用的锁表)信息
	 * @param commonLock
	 * @return
	 */
	public int updateCommonLock(CommonLock commonLock);
	/**
	 * 批量更新(公用的锁表)信息
	 * @param commonLockList
	 * @return
	 */
	public int updateCommonLockBatch(List<CommonLock> commonLockList);
	/**
	 * 根据序列号删除(公用的锁表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	public int deleteCommonLockLogic(java.math.BigInteger id);
	 */
	/**
	 * 根据序列号批量删除(公用的锁表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	public int deleteCommonLockLogicBatch(List<java.math.BigInteger> idList);
	 */
//	/**
//	 * 根据序列号删除(公用的锁表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommonLock(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(公用的锁表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommonLockBatch(List<java.math.BigInteger> idList);
	
}
