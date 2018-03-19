package com.cnfantasia.server.domainbase.prizeSurpriseGift.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.prizeSurpriseGift.entity.PrizeSurpriseGift;

/**
 * 描述:(抽奖结果的意外惊喜) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPrizeSurpriseGiftBaseService {
	
	/**
	 * 根据条件查询(抽奖结果的意外惊喜)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PrizeSurpriseGift> getPrizeSurpriseGiftByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(抽奖结果的意外惊喜)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PrizeSurpriseGift> getPrizeSurpriseGiftByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(抽奖结果的意外惊喜)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PrizeSurpriseGift> getPrizeSurpriseGiftByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(抽奖结果的意外惊喜)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PrizeSurpriseGift> getPrizeSurpriseGiftByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(抽奖结果的意外惊喜)信息
	 * @param id
	 * @return
	 */
	public PrizeSurpriseGift getPrizeSurpriseGiftBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(抽奖结果的意外惊喜)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPrizeSurpriseGiftCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(抽奖结果的意外惊喜)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPrizeSurpriseGiftCountDim(Map<String,Object> paramMap);
	/**
	 * 往(抽奖结果的意外惊喜)新增一条记录
	 * @param prizeSurpriseGift
	 * @return
	 */
	public int insertPrizeSurpriseGift(PrizeSurpriseGift prizeSurpriseGift);
	/**
	 * 批量新增(抽奖结果的意外惊喜)
	 * @param prizeSurpriseGiftList
	 * @return
	 */
	public int insertPrizeSurpriseGiftBatch(List<PrizeSurpriseGift> prizeSurpriseGiftList);
	/**
	 * 更新(抽奖结果的意外惊喜)信息
	 * @param prizeSurpriseGift
	 * @return
	 */
	public int updatePrizeSurpriseGift(PrizeSurpriseGift prizeSurpriseGift);
	/**
	 * 批量更新(抽奖结果的意外惊喜)信息
	 * @param prizeSurpriseGiftList
	 * @return
	 */
	public int updatePrizeSurpriseGiftBatch(List<PrizeSurpriseGift> prizeSurpriseGiftList);
	/**
	 * 根据序列号删除(抽奖结果的意外惊喜)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePrizeSurpriseGiftLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(抽奖结果的意外惊喜)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePrizeSurpriseGiftLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(抽奖结果的意外惊喜)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePrizeSurpriseGift(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(抽奖结果的意外惊喜)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePrizeSurpriseGiftBatch(List<java.math.BigInteger> idList);
	
}
