package com.cnfantasia.server.domainbase.prizeSurpriseGift.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.prizeSurpriseGift.entity.PrizeSurpriseGift;

/**
 * 描述:(抽奖结果的意外惊喜) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPrizeSurpriseGiftBaseDao {
	/**
	 * 根据条件查询(抽奖结果的意外惊喜)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PrizeSurpriseGift> selectPrizeSurpriseGiftByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(抽奖结果的意外惊喜)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PrizeSurpriseGift> selectPrizeSurpriseGiftByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(抽奖结果的意外惊喜)信息
	 * @param id
	 * @return
	 */
	public PrizeSurpriseGift selectPrizeSurpriseGiftBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(抽奖结果的意外惊喜)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPrizeSurpriseGiftCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(抽奖结果的意外惊喜)新增一条记录
	 * @param prizeSurpriseGift
	 * @return
	 */
	public int insertPrizeSurpriseGift(PrizeSurpriseGift prizeSurpriseGift);
	
	/**
	 * 批量新增(抽奖结果的意外惊喜)信息
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
	 * 根据Id 批量删除(抽奖结果的意外惊喜)信息_逻辑删除
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
