package com.cnfantasia.server.domainbase.msPrizeGift.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.msPrizeGift.entity.MsPrizeGift;

/**
 * 描述:(奖品详情表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMsPrizeGiftBaseDao {
	/**
	 * 根据条件查询(奖品详情表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<MsPrizeGift> selectMsPrizeGiftByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(奖品详情表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<MsPrizeGift> selectMsPrizeGiftByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(奖品详情表)信息
	 * @param id
	 * @return
	 */
	public MsPrizeGift selectMsPrizeGiftBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(奖品详情表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectMsPrizeGiftCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(奖品详情表)新增一条记录
	 * @param msPrizeGift
	 * @return
	 */
	public int insertMsPrizeGift(MsPrizeGift msPrizeGift);
	
	/**
	 * 批量新增(奖品详情表)信息
	 * @param msPrizeGiftList
	 * @return
	 */
	public int insertMsPrizeGiftBatch(List<MsPrizeGift> msPrizeGiftList);
	
	/**
	 * 更新(奖品详情表)信息
	 * @param msPrizeGift
	 * @return
	 */
	public int updateMsPrizeGift(MsPrizeGift msPrizeGift);
	
	/**
	 * 批量更新(奖品详情表)信息
	 * @param msPrizeGiftList
	 * @return
	 */
	public int updateMsPrizeGiftBatch(List<MsPrizeGift> msPrizeGiftList);
	
	/**
	 * 根据序列号删除(奖品详情表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteMsPrizeGiftLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(奖品详情表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteMsPrizeGiftLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(奖品详情表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteMsPrizeGift(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(奖品详情表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteMsPrizeGiftBatch(List<java.math.BigInteger> idList);
	
	
}
