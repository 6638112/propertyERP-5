package com.cnfantasia.server.domainbase.limitBuyActivity.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.limitBuyActivity.entity.LimitBuyActivity;

/**
 * 描述:(限时促销) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ILimitBuyActivityBaseService {
	
	/**
	 * 根据条件查询(限时促销)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<LimitBuyActivity> getLimitBuyActivityByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(限时促销)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<LimitBuyActivity> getLimitBuyActivityByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(限时促销)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<LimitBuyActivity> getLimitBuyActivityByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(限时促销)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<LimitBuyActivity> getLimitBuyActivityByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(限时促销)信息
	 * @param id
	 * @return
	 */
	public LimitBuyActivity getLimitBuyActivityBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(限时促销)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getLimitBuyActivityCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(限时促销)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getLimitBuyActivityCountDim(Map<String,Object> paramMap);
	/**
	 * 往(限时促销)新增一条记录
	 * @param limitBuyActivity
	 * @return
	 */
	public int insertLimitBuyActivity(LimitBuyActivity limitBuyActivity);
	/**
	 * 批量新增(限时促销)
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
	 * 根据序列号批量删除(限时促销)信息_逻辑删除
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
