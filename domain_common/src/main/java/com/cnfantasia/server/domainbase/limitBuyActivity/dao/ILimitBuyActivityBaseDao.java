package com.cnfantasia.server.domainbase.limitBuyActivity.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.limitBuyActivity.entity.LimitBuyActivity;

/**
 * 描述:(限时促销) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ILimitBuyActivityBaseDao {
	/**
	 * 根据条件查询(限时促销)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<LimitBuyActivity> selectLimitBuyActivityByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(限时促销)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<LimitBuyActivity> selectLimitBuyActivityByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(限时促销)信息
	 * @param id
	 * @return
	 */
	public LimitBuyActivity selectLimitBuyActivityBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(限时促销)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectLimitBuyActivityCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(限时促销)新增一条记录
	 * @param limitBuyActivity
	 * @return
	 */
	public int insertLimitBuyActivity(LimitBuyActivity limitBuyActivity);
	
	/**
	 * 批量新增(限时促销)信息
	 * @param limitBuyActivityList
	 * @return
	 */
	public int insertLimitBuyActivityBatch(List<LimitBuyActivity> limitBuyActivityList);
	
	/**
	 * 更新(限时促销)信息
	 * @param limitBuyActivity
	 * @return
	 */
	public int updateLimitBuyActivity(LimitBuyActivity limitBuyActivity);
	
	/**
	 * 批量更新(限时促销)信息
	 * @param limitBuyActivityList
	 * @return
	 */
	public int updateLimitBuyActivityBatch(List<LimitBuyActivity> limitBuyActivityList);
	
	/**
	 * 根据序列号删除(限时促销)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteLimitBuyActivityLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(限时促销)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteLimitBuyActivityLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(限时促销)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteLimitBuyActivity(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(限时促销)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteLimitBuyActivityBatch(List<java.math.BigInteger> idList);
	
	
}
