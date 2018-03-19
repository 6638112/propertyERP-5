package com.cnfantasia.server.domainbase.msPrizeActivity.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.msPrizeActivity.entity.MsPrizeActivity;

/**
 * 描述:(抽奖活动表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMsPrizeActivityBaseService {
	
	/**
	 * 根据条件查询(抽奖活动表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<MsPrizeActivity> getMsPrizeActivityByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(抽奖活动表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<MsPrizeActivity> getMsPrizeActivityByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(抽奖活动表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<MsPrizeActivity> getMsPrizeActivityByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(抽奖活动表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<MsPrizeActivity> getMsPrizeActivityByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(抽奖活动表)信息
	 * @param id
	 * @return
	 */
	public MsPrizeActivity getMsPrizeActivityBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(抽奖活动表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getMsPrizeActivityCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(抽奖活动表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getMsPrizeActivityCountDim(Map<String,Object> paramMap);
	/**
	 * 往(抽奖活动表)新增一条记录
	 * @param msPrizeActivity
	 * @return
	 */
	public int insertMsPrizeActivity(MsPrizeActivity msPrizeActivity);
	/**
	 * 批量新增(抽奖活动表)
	 * @param msPrizeActivityList
	 * @return
	 */
	public int insertMsPrizeActivityBatch(List<MsPrizeActivity> msPrizeActivityList);
	/**
	 * 更新(抽奖活动表)信息
	 * @param msPrizeActivity
	 * @return
	 */
	public int updateMsPrizeActivity(MsPrizeActivity msPrizeActivity);
	/**
	 * 批量更新(抽奖活动表)信息
	 * @param msPrizeActivityList
	 * @return
	 */
	public int updateMsPrizeActivityBatch(List<MsPrizeActivity> msPrizeActivityList);
	/**
	 * 根据序列号删除(抽奖活动表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteMsPrizeActivityLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(抽奖活动表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteMsPrizeActivityLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(抽奖活动表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteMsPrizeActivity(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(抽奖活动表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteMsPrizeActivityBatch(List<java.math.BigInteger> idList);
	
}
