package com.cnfantasia.server.domainbase.msPrizeActivity.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.msPrizeActivity.entity.MsPrizeActivity;

/**
 * 描述:(抽奖活动表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMsPrizeActivityBaseDao {
	/**
	 * 根据条件查询(抽奖活动表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<MsPrizeActivity> selectMsPrizeActivityByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(抽奖活动表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<MsPrizeActivity> selectMsPrizeActivityByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(抽奖活动表)信息
	 * @param id
	 * @return
	 */
	public MsPrizeActivity selectMsPrizeActivityBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(抽奖活动表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectMsPrizeActivityCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(抽奖活动表)新增一条记录
	 * @param msPrizeActivity
	 * @return
	 */
	public int insertMsPrizeActivity(MsPrizeActivity msPrizeActivity);
	
	/**
	 * 批量新增(抽奖活动表)信息
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
	 * 根据Id 批量删除(抽奖活动表)信息_逻辑删除
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
